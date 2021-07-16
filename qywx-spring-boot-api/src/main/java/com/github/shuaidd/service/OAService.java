package com.github.shuaidd.service;

import com.github.shuaidd.dto.checkin.CheckInData;
import com.github.shuaidd.dto.checkin.CheckInRule;
import com.github.shuaidd.dto.tool.DialRecord;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.response.*;
import com.github.shuaidd.response.oa.*;
import com.github.shuaidd.response.tool.DialRecordResponse;
import com.github.shuaidd.resquest.oa.*;
import com.github.shuaidd.resquest.tool.DialRecordRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 描述 OA
 *
 * @author ddshuai
 * date 2019-04-11 10:15
 **/
@Service
public class OAService extends AbstractBaseService {
    private static final int LIMIT_USER_COUNT = 100;

    /**
     * 获取打卡记录数据
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CheckInData
     */
    public final List<CheckInData> getCheckInData(CheckInDataRequest request, String applicationName) {
        checkApplication(applicationName);
        checkRequest(request);
        List<CheckInData> checkInData = new ArrayList<>(1);
        CheckInDataResponse response = weChatClient.getCheckInData(request, applicationName);
        if (isSuccess(response)) {
            checkInData = response.getCheckInDataList();
        }

        return checkInData;
    }

    private void checkRequest(CheckInDataRequest request) {
        Objects.requireNonNull(request);
        Objects.requireNonNull(request.getEndTime());
        Objects.requireNonNull(request.getOpenCheckInDataType());
        Objects.requireNonNull(request.getStartTime());
        Objects.requireNonNull(request.getUserIdList());
        if (CollectionUtils.isNotEmpty(request.getUserIdList())) {
            if (request.getUserIdList().size() > LIMIT_USER_COUNT) {
                throw new WeChatException("一次查询人数不能超过100，请分批获取");
            }
        } else {
            throw new WeChatException("查询的人员列表不能为空");
        }
    }

    /**
     * 获取员工打卡规则
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CheckInRule
     */
    public final List<CheckInRule> getCheckInOption(CheckInRuleRequest request, String applicationName) {
        checkApplication(applicationName);
        List<CheckInRule> checkInRules = new ArrayList<>(1);
        if (Objects.nonNull(request) && Objects.nonNull(request.getDateTime()) && CollectionUtils.isNotEmpty(request.getUserIdList())) {
            if (request.getUserIdList().size() <= LIMIT_USER_COUNT) {
                CheckInRuleResponse response = weChatClient.getCheckInOption(request, applicationName);
                if (isSuccess(response)) {
                    checkInRules = response.getCheckInRules();
                }
            } else {
                throw new WeChatException("一次查询人数不能超过100，请分批获取");
            }
        }

        return checkInRules;
    }

    /**
     * 获取审批数据
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return ApprovalDataResponse
     */
    public final ApprovalDataResponse getApprovalData(ApprovalDataRequest request, String applicationName) {
        checkApplication(applicationName);
        Objects.requireNonNull(request);
        Objects.requireNonNull(request.getEndTime());
        Objects.requireNonNull(request.getStartTime());
        ApprovalDataResponse response = weChatClient.getApprovalData(request, applicationName);
        if (isSuccess(response)) {
            logger.info("获取审批数据成功：total:{},count:{},next:{}", response.getTotal(), response.getCount(), response.getNextSpNum());
        }
        return response;
    }

    /**
     * 获取公费电话拨打记录
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return DialRecord
     */
    public final List<DialRecord> getDialRecord(DialRecordRequest request, String applicationName) {
        Objects.requireNonNull(request, "参数为空");
        List<DialRecord> records = new ArrayList<>(1);
        DialRecordResponse response = weChatClient.getDialRecord(request, applicationName);
        if (isSuccess(response)) {
            records = response.getRecords();
        }
        return records;
    }

    /**
     * 获取企业所有打卡规则
     * @param applicationName 应用名称
     * @return CheckInOptionResponse
     */
    public CheckInOptionResponse getCorpCheckInOption(String applicationName) {
        CheckInOptionResponse response = weChatClient.getCorpCheckInOption(applicationName);
        return getResponse(response);
    }

    /**
     * 获取打卡日报数据
     * @param request 请求
     * @param applicationName 应用名称
     * @return CheckInDayReportResponse
     */
    public CheckInDayReportResponse getCheckInDayData(CommonOaRequest request, String applicationName){
        CheckInDayReportResponse reportResponse = weChatClient.getCheckInDayData(request,applicationName);
        return getResponse(reportResponse);
    }

    /**
     * 获取打卡月报数据
     * @param request 请求
     * @param applicationName 应用名称
     * @return CheckInDayReportResponse
     */
    public CheckInDayReportResponse getCheckInMonthData(CommonOaRequest request, String applicationName){
        CheckInDayReportResponse reportResponse = weChatClient.getCheckInMonthData(request,applicationName);
        return getResponse(reportResponse);
    }

    /**
     * 获取打卡人员排班信息
     * @param request 请求
     * @param applicationName 应用名称
     * @return CheckInDayReportResponse
     */
    public CheckInScheduleResponse getCheckInScheduleList(CommonOaRequest request, String applicationName){
        CheckInScheduleResponse reportResponse = weChatClient.getCheckInScheduList(request,applicationName);
        return getResponse(reportResponse);
    }

    /**
     * 为打卡人员排班
     * @param request 请求
     * @param applicationName 应用名称
     */
    public void setCheckInScheduleList(SetCheckInScheduleRequest request,String applicationName){
        BaseResponse response = weChatClient.setCheckInScheduleList(request,applicationName);
        getResponse(response);
    }

    /**
     * 录入打卡人员人脸信息
     * @param request 请求
     * @param applicationName 应用名称
     */
    public void addCheckInUserFace(AddCheckInUserFaceRequest request, String applicationName){
        BaseResponse response = weChatClient.addCheckInUserFace(request,applicationName);
        getResponse(response);
    }
}
