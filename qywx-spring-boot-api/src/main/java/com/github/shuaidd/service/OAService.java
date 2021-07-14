package com.github.shuaidd.service;

import com.github.shuaidd.dto.CheckInData;
import com.github.shuaidd.dto.CheckInRule;
import com.github.shuaidd.dto.DialRecord;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.response.*;
import com.github.shuaidd.resquest.ApprovalDataRequest;
import com.github.shuaidd.resquest.CheckInDataRequest;
import com.github.shuaidd.resquest.CheckInRuleRequest;
import com.github.shuaidd.resquest.DialRecordRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 获取打卡数据
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return List<CheckInData>
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
     * 获取打卡规则
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return List<CheckInRule>
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
     * @return List<DialRecord>
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
}
