package com.github.shuaidd.service;

import com.github.shuaidd.dto.checkin.CheckInData;
import com.github.shuaidd.dto.checkin.CheckInRecord;
import com.github.shuaidd.dto.checkin.CheckInRule;
import com.github.shuaidd.dto.checkin.CheckInRuleGroupRange;
import com.github.shuaidd.dto.checkin.HardwareCheckInRecord;
import com.github.shuaidd.dto.todo.TodoAttendee;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.response.oa.*;
import com.github.shuaidd.resquest.JournalReportStatRequest;
import com.github.shuaidd.resquest.kf.PunchCorrectionRequest;
import com.github.shuaidd.resquest.oa.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 描述 OA
 *
 * @author ddshuai
 * date 2019-04-11 10:15
 **/
@Service
public class OAService extends AbstractBaseService {
    private static final int LIMIT_USER_COUNT = 100;
    private static final int MAX_CHECK_IN_RECORD_COUNT = 200;
    private static final int MAX_CHECK_IN_MEDIA_COUNT = 1;
    private static final int CHECK_IN_TEXT_LENGTH_LIMIT = 1024;
    private static final int MAX_CHECK_IN_DEVICE_DETAIL_LENGTH = 40;
    private static final int MIN_CHECK_IN_DEVICE_TYPE = 1;
    private static final int MAX_CHECK_IN_DEVICE_TYPE = 3;
    private static final long MIN_CHECK_IN_LATITUDE = -90000000L;
    private static final long MAX_CHECK_IN_LATITUDE = 90000000L;
    private static final long MIN_CHECK_IN_LONGITUDE = -180000000L;
    private static final long MAX_CHECK_IN_LONGITUDE = 180000000L;
    private static final Pattern WIFI_MAC_PATTERN = Pattern.compile(
            "^[A-Fa-f0-9]{2}(:[A-Fa-f0-9]{2}){5}$");

    /**
     * 获取打卡记录数据
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CheckInData
     */
    public List<CheckInData> getCheckInData(CheckInDataRequest request, String applicationName) {
        checkRequest(request);
        return workOaClient.getCheckInData(request, applicationName).getCheckInDataList();
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
    public List<CheckInRule> getCheckInOption(CheckInRuleRequest request, String applicationName) {
        List<CheckInRule> checkInRules = new ArrayList<>(1);
        if (Objects.nonNull(request) && Objects.nonNull(request.getDateTime()) && CollectionUtils.isNotEmpty(request.getUserIdList())) {
            if (request.getUserIdList().size() <= LIMIT_USER_COUNT) {
                return workOaClient.getCheckInOption(request, applicationName).getCheckInRules();
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
    public ApprovalDataResponse getApprovalData(ApprovalDataRequest request, String applicationName) {
        Objects.requireNonNull(request);
        Objects.requireNonNull(request.getEndTime());
        Objects.requireNonNull(request.getStartTime());
        ApprovalDataResponse response = workOaClient.getApprovalData(request, applicationName);
        logger.info("获取审批数据成功：total:{},count:{},next:{}", response.getTotal(), response.getCount(), response.getNextSpNum());
        return response;
    }

    /**
     * 获取企业所有打卡规则
     *
     * @param applicationName 应用名称
     * @return CheckInOptionResponse
     */
    public CheckInOptionResponse getCorpCheckInOption(String applicationName) {
        return workOaClient.getCorpCheckInOption(applicationName);
    }

    /**
     * 获取打卡日报数据
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CheckInDayReportResponse
     */
    public CheckInDayReportResponse getCheckInDayData(CommonOaRequest request, String applicationName) {
        return workOaClient.getCheckInDayData(request, applicationName);
    }

    /**
     * 获取打卡月报数据
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CheckInDayReportResponse
     */
    public CheckInDayReportResponse getCheckInMonthData(CommonOaRequest request, String applicationName) {
        return workOaClient.getCheckInMonthData(request, applicationName);
    }

    /**
     * 获取打卡人员排班信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CheckInDayReportResponse
     */
    public CheckInScheduleResponse getCheckInScheduleList(CommonOaRequest request, String applicationName) {
        return workOaClient.getCheckInScheduList(request, applicationName);
    }

    /**
     * 为打卡人员排班
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void setCheckInScheduleList(SetCheckInScheduleRequest request, String applicationName) {
        workOaClient.setCheckInScheduleList(request, applicationName);
    }

    /**
     * 录入打卡人员人脸信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void addCheckInUserFace(AddCheckInUserFaceRequest request, String applicationName) {
        workOaClient.addCheckInUserFace(request, applicationName);
    }

    /**
     * 获取考勤设备打卡数据
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 考勤设备打卡记录列表
     */
    public List<HardwareCheckInRecord> getHardwareCheckInData(HardwareCheckInDataRequest request, String applicationName) {
        checkHardwareCheckInData(request);
        return workOaClient.getHardwareCheckInData(request, applicationName).getCheckInDataList();
    }

    /**
     * 添加打卡记录
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void addCheckInRecord(AddCheckInRecordRequest request, String applicationName) {
        checkAddCheckInRecord(request);
        workOaClient.addCheckInRecord(request, applicationName);
    }

    /**
     * 创建打卡规则
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void createCheckInRule(CheckInRuleOptionRequest request, String applicationName) {
        checkCheckInRuleOption(request, false);
        workOaClient.createCheckInRule(request, applicationName);
    }

    /**
     * 修改打卡规则
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void updateCheckInRule(CheckInRuleOptionRequest request, String applicationName) {
        checkCheckInRuleOption(request, true);
        workOaClient.updateCheckInRule(request, applicationName);
    }

    /**
     * 清空打卡规则数组元素
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void clearCheckInRuleArrayField(ClearCheckInRuleArrayFieldRequest request, String applicationName) {
        checkClearCheckInRuleArrayField(request);
        workOaClient.clearCheckInRuleArrayField(request, applicationName);
    }

    /**
     * 删除打卡规则
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void deleteCheckInRule(DeleteCheckInRuleRequest request, String applicationName) {
        checkDeleteCheckInRule(request);
        workOaClient.deleteCheckInRule(request, applicationName);
    }

    private void checkAddCheckInRecord(AddCheckInRecordRequest request) {
        if (Objects.isNull(request) || CollectionUtils.isEmpty(request.getRecords())) {
            throw new ParamCheckException("records 不能为空，请检查！！！");
        }
        if (request.getRecords().size() > MAX_CHECK_IN_RECORD_COUNT) {
            throw new ParamCheckException("records 一批最多允许200条，请检查！！！");
        }

        for (CheckInRecord record : request.getRecords()) {
            if (StringUtils.isBlank(record.getUserId())) {
                throw new ParamCheckException("records.userid 不能为空，请检查！！！");
            }
            if (Objects.isNull(record.getCheckinTime())) {
                throw new ParamCheckException("records.checkin_time 不能为空，请检查！！！");
            }
            if (StringUtils.isBlank(record.getLocationTitle())) {
                throw new ParamCheckException("records.location_title 不能为空，请检查！！！");
            }
            if (StringUtils.length(record.getLocationTitle()) > CHECK_IN_TEXT_LENGTH_LIMIT) {
                throw new ParamCheckException("records.location_title 最多1024个字符，请检查！！！");
            }
            if (StringUtils.isBlank(record.getLocationDetail())) {
                throw new ParamCheckException("records.location_detail 不能为空，请检查！！！");
            }
            if (StringUtils.length(record.getLocationDetail()) > CHECK_IN_TEXT_LENGTH_LIMIT) {
                throw new ParamCheckException("records.location_detail 最多1024个字符，请检查！！！");
            }
            if (StringUtils.length(record.getNotes()) > CHECK_IN_TEXT_LENGTH_LIMIT) {
                throw new ParamCheckException("records.notes 最多1024个字符，请检查！！！");
            }
            if (StringUtils.length(record.getWifiName()) > CHECK_IN_TEXT_LENGTH_LIMIT) {
                throw new ParamCheckException("records.wifiname 最多1024个字符，请检查！！！");
            }
            if (StringUtils.isNotBlank(record.getWifiName()) && (StringUtils.isBlank(record.getWifiMac()) || !WIFI_MAC_PATTERN.matcher(record.getWifiMac()).matches())) {
                throw new ParamCheckException("传入 records.wifiname 时 records.wifimac 必须为合法 MAC 地址/BSSID，请检查！！！");
            }
            if (CollectionUtils.isNotEmpty(record.getMediaIds())) {
                if (record.getMediaIds().size() > MAX_CHECK_IN_MEDIA_COUNT) {
                    throw new ParamCheckException("records.mediaids 当前最多允许1个，请检查！！！");
                }
                if (record.getMediaIds().stream().anyMatch(StringUtils::isBlank)) {
                    throw new ParamCheckException("records.mediaids 不能包含空 media_id，请检查！！！");
                }
            }
            if (Objects.nonNull(record.getLatitude())
                    && (record.getLatitude() < MIN_CHECK_IN_LATITUDE || record.getLatitude() > MAX_CHECK_IN_LATITUDE)) {
                throw new ParamCheckException("records.lat 超出官方允许范围，请检查！！！");
            }
            if (Objects.nonNull(record.getLongitude())
                    && (record.getLongitude() < MIN_CHECK_IN_LONGITUDE || record.getLongitude() > MAX_CHECK_IN_LONGITUDE)) {
                throw new ParamCheckException("records.lng 超出官方允许范围，请检查！！！");
            }
            if (Objects.isNull(record.getDeviceType())
                    || record.getDeviceType() < MIN_CHECK_IN_DEVICE_TYPE || record.getDeviceType() > MAX_CHECK_IN_DEVICE_TYPE) {
                throw new ParamCheckException("records.device_type 只能为 1、2 或 3，请检查！！！");
            }
            if (StringUtils.isBlank(record.getDeviceDetail())) {
                throw new ParamCheckException("records.device_detail 不能为空，请检查！！！");
            }
            if (StringUtils.length(record.getDeviceDetail()) > MAX_CHECK_IN_DEVICE_DETAIL_LENGTH) {
                throw new ParamCheckException("records.device_detail 最多40个字符，请检查！！！");
            }
        }
    }

    /**
     * 获取审批模板详情
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return ApproveTemplateResponse
     */
    public ApproveTemplateResponse getTemplateDetail(TemplateRequest request, String applicationName) {
        return workOaClient.getTemplateDetail(request, applicationName);
    }

    /**
     * 创建审批模板
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CreateApprovalTemplateResponse
     */
    public CreateApprovalTemplateResponse createApprovalTemplate(CreateApprovalTemplateRequest request, String applicationName) {
        checkApprovalTemplate(request);
        return workOaClient.createApprovalTemplate(request, applicationName);
    }

    /**
     * 更新审批模板
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void updateApprovalTemplate(UpdateApprovalTemplateRequest request, String applicationName) {
        checkApprovalTemplate(request);
        if (StringUtils.isEmpty(request.getTemplateId())) {
            throw new ParamCheckException("templateId 不能为空，请检查！！！");
        }
        workOaClient.updateApprovalTemplate(request, applicationName);
    }

    /**
     * 获取待办详情
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return TodoDetailResponse
     */
    public TodoDetailResponse getTodoDetail(TodoDetailRequest request, String applicationName) {
        if (request == null || StringUtils.isEmpty(request.getTodoId())) {
            throw new ParamCheckException("todoId 不能为空，请检查！！！");
        }
        return workOaClient.getTodoDetail(request, applicationName);
    }

    /**
     * 更新待办状态
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void updateTodo(UpdateTodoRequest request, String applicationName) {
        if (request == null || StringUtils.isEmpty(request.getTodoId())) {
            throw new ParamCheckException("todoId 不能为空，请检查！！！");
        }
        if (Objects.nonNull(request.getStatus()) && request.getStatus() != 0 && request.getStatus() != 1) {
            throw new ParamCheckException("status 只能为0或1，请检查！！！");
        }
        checkTodoAttendees(request.getAttendees());
        workOaClient.updateTodo(request, applicationName);
    }

    private void checkTodoAttendees(List<TodoAttendee> attendees) {
        if (CollectionUtils.isEmpty(attendees)) {
            return;
        }
        if (attendees.size() > 20) {
            throw new ParamCheckException("attendees 最多只能有20个参与人，请检查！！！");
        }
        for (TodoAttendee attendee : attendees) {
            if (attendee == null || StringUtils.isEmpty(attendee.getUserId()) || Objects.isNull(attendee.getStatus())) {
                throw new ParamCheckException("attendees.userid、attendees.status 不能为空，请检查！！！");
            }
            if (attendee.getStatus() != 0 && attendee.getStatus() != 1) {
                throw new ParamCheckException("attendees.status 只能为0或1，请检查！！！");
            }
        }
    }

    private void checkApprovalTemplate(CreateApprovalTemplateRequest request) {
        if (request == null || CollectionUtils.isEmpty(request.getTemplateName())
                || request.getTemplateContent() == null
                || CollectionUtils.isEmpty(request.getTemplateContent().getControls())) {
            throw new ParamCheckException("templateName、templateContent.controls 不能为空，请检查！！！");
        }
    }

    private void checkApprovalTemplate(UpdateApprovalTemplateRequest request) {
        if (request == null || CollectionUtils.isEmpty(request.getTemplateName())
                || request.getTemplateContent() == null
                || CollectionUtils.isEmpty(request.getTemplateContent().getControls())) {
            throw new ParamCheckException("templateName、templateContent.controls 不能为空，请检查！！！");
        }
    }

    /**
     * 提交审批申请
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return ApplyEventResponse
     */
    public ApplyEventResponse applyEvent(ApplyEventRequest request, String applicationName) {
        return workOaClient.applyEvent(request, applicationName);
    }

    /**
     * 批量获取审批单号
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return SpNoResponse
     */
    public SpNoResponse getApprovalInfo(GetApprovalNoRequest request, String applicationName) {
        return workOaClient.getApprovalInfo(request, applicationName);
    }

    /**
     * 获取审批申请详情
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return ApprovalDetailResponse
     */
    public ApprovalDetailResponse getApprovalDetail(ApprovalDetailRequest request, String applicationName) {
        return workOaClient.getApprovalDetail(request, applicationName);
    }

    /**
     * 获取企业假期管理配置
     *
     * @param applicationName 应用名称
     * @return VacationConfigResponse
     */
    public VacationConfigResponse getCorpConf(String applicationName) {
        return workOaClient.getCorpConf(applicationName);
    }

    /**
     * 获取成员假期余额
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return UserVacationQuotaResponse
     */
    public UserVacationQuotaResponse getUserVacationQuota(UserIdRequest request, String applicationName) {
        return workOaClient.getUserVacationQuota(request, applicationName);
    }

    /**
     * 修改成员假期余额
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void setOneUserQuota(UpdateUserQuotaRequest request, String applicationName) {
        workOaClient.setOneUserQuota(request, applicationName);
    }

    /**
     * 批量获取汇报记录单号
     *
     * @param recordRequest   请求
     * @param applicationName 应用名称
     * @return UserVacationQuotaResponse
     */
    public JournalRecordResponse getJournalRecordList(JournalRecordRequest recordRequest, String applicationName) {
        return workOaClient.getJournalRecordList(recordRequest, applicationName);
    }

    /**
     * 获取汇报记录详情
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return UserVacationQuotaResponse
     */
    public JournalReportDetailResponse getJournalRecordDetail(JournalReportDetailRequest request, String applicationName) {
        return workOaClient.getJournalRecordDetail(request, applicationName);
    }

    /**
     * 获取汇报统计数据
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return UserVacationQuotaResponse
     */
    public JournalReportStatResponse getJournalStatList(JournalReportStatRequest request, String applicationName) {
        return workOaClient.getJournalStatList(request, applicationName);
    }

    /**
     * 添加会议室
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return AddMeetingRoomResponse
     */
    public AddMeetingRoomResponse addMeetingRoom(MeetingRoomRequest request, String applicationName) {
        return workOaClient.addMeetingRoom(request, applicationName);
    }

    /**
     * 编辑会议室
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void updateMeetingRoom(MeetingRoomRequest request, String applicationName) {
        workOaClient.updateMeetingRoom(request, applicationName);
    }

    /**
     * 删除会议室
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void delMeetingRoom(MeetingRoomRequest request, String applicationName) {
        workOaClient.delMeetingRoom(request, applicationName);
    }

    /**
     * 查询会议室
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return MeetingRoomListResponse
     */
    public MeetingRoomListResponse searchMeetingRoom(MeetingRoomRequest request, String applicationName) {
        return workOaClient.searchMeetingRoom(request, applicationName);
    }

    /**
     * 查询会议室的预定信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return BookingInfoResponse
     */
    public BookingInfoResponse getBookingInfo(GetBookingInfoRequest request, String applicationName) {
        return workOaClient.getBookingInfo(request, applicationName);
    }

    /**
     * 预定会议室
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return BookMeetingRoomResponse
     */
    public BookMeetingRoomResponse bookMeetingRoom(BookMeetingRoomRequest request, String applicationName) {
        return workOaClient.bookMeetingRoom(request, applicationName);
    }

    /**
     * 取消预定会议室
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void cancelBookMeetingRoom(CancelBookRequest request, String applicationName) {
        workOaClient.cancelBookMeetingRoom(request, applicationName);
    }

    /**
     * 发起语音电话
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return EmergencyCallResponse
     */
    public EmergencyCallResponse pstnccCall(EmergencyCallRequest request, String applicationName) {
        return workOaClient.pstnccCall(request, applicationName);
    }

    /**
     * 获取接听状态
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return GetCallStateResponse
     */
    public GetCallStateResponse pstnccCallState(GetCallStateRequest request, String applicationName) {
        return workOaClient.pstnccCallState(request, applicationName);
    }

    /**
     * 为打卡人员补卡
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void punchCorrection(PunchCorrectionRequest request, String applicationName) {
        workOaClient.punchCorrection(request, applicationName);
    }

    private void checkCheckInRuleOption(CheckInRuleOptionRequest request, boolean update) {
        if (Objects.isNull(request) || Objects.isNull(request.getGroup())) {
            throw new ParamCheckException("group 不能为空，请检查！！！");
        }
        if (update && Objects.isNull(request.getGroup().getGroupId())) {
            throw new ParamCheckException("group.groupid 不能为空，请检查！！！");
        }
        Integer groupType = request.getGroup().getGroupType();
        if (Objects.isNull(groupType) || groupType < 1 || groupType > 3) {
            throw new ParamCheckException("group.grouptype 只能为 1、2 或 3，请检查！！！");
        }
        if (StringUtils.isBlank(request.getGroup().getGroupName())) {
            throw new ParamCheckException("group.groupname 不能为空，请检查！！！");
        }
        if (StringUtils.length(request.getGroup().getGroupName()) > 40) {
            throw new ParamCheckException("group.groupname 最多40个字符，请检查！！！");
        }
        if (groupType == 1 && CollectionUtils.isEmpty(request.getGroup().getCheckInDates())) {
            throw new ParamCheckException("group.checkindate 不能为空，请检查！！！");
        }
        if (groupType == 2 && CollectionUtils.isEmpty(request.getGroup().getScheduleList())) {
            throw new ParamCheckException("group.schedulelist 不能为空，请检查！！！");
        }
        CheckInRuleGroupRange range = request.getGroup().getRange();
        if (Objects.isNull(range)
                || (CollectionUtils.isEmpty(range.getPartyId())
                && CollectionUtils.isEmpty(range.getUserId())
                && CollectionUtils.isEmpty(range.getTagId()))) {
            throw new ParamCheckException("group.range 中部门、人员和标签不能同时为空，请检查！！！");
        }
        if (CollectionUtils.isEmpty(request.getGroup().getWifiMacInfos())
                && CollectionUtils.isEmpty(request.getGroup().getLocations())) {
            throw new ParamCheckException("group.wifimac_infos 和 group.loc_infos 不能同时为空，请检查！！！");
        }
    }

    private void checkClearCheckInRuleArrayField(ClearCheckInRuleArrayFieldRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("request 不能为空，请检查！！！");
        }
        if (Objects.isNull(request.getGroupId())) {
            throw new ParamCheckException("groupid 不能为空，请检查！！！");
        }
        if (CollectionUtils.isEmpty(request.getClearFields())) {
            throw new ParamCheckException("clear_field 不能为空，请检查！！！");
        }
        if (request.getClearFields().stream().anyMatch(field -> Objects.isNull(field) || field < 1 || field > 4)) {
            throw new ParamCheckException("clear_field 只能为 1、2、3 或 4，请检查！！！");
        }
        if (request.getClearFields().size() != request.getClearFields().stream().distinct().count()) {
            throw new ParamCheckException("clear_field 不能包含重复值，请检查！！！");
        }
        if (request.getClearFields().contains(3) && request.getClearFields().contains(4)) {
            throw new ParamCheckException("wifimac_infos 和 loc_infos 不能同时清空，请检查！！！");
        }
    }

    private void checkDeleteCheckInRule(DeleteCheckInRuleRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("request 不能为空，请检查！！！");
        }
        if (Objects.isNull(request.getGroupId())) {
            throw new ParamCheckException("groupid 不能为空，请检查！！！");
        }
    }

    private void checkHardwareCheckInData(HardwareCheckInDataRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("request 不能为空，请检查！！！");
        }
        if (Objects.nonNull(request.getFilterType())
                && (request.getFilterType() < 1 || request.getFilterType() > 2)) {
            throw new ParamCheckException("filter_type 只能为 1 或 2，请检查！！！");
        }
        if (Objects.isNull(request.getStartTime()) || Objects.isNull(request.getEndTime())) {
            throw new ParamCheckException("starttime 和 endtime 不能为空，请检查！！！");
        }
        if (request.getStartTime() > request.getEndTime()) {
            throw new ParamCheckException("starttime 不能晚于 endtime，请检查！！！");
        }

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate startDate = Instant.ofEpochSecond(request.getStartTime()).atZone(zoneId).toLocalDate();
        LocalDate endDate = Instant.ofEpochSecond(request.getEndTime()).atZone(zoneId).toLocalDate();
        if (ChronoUnit.MONTHS.between(startDate, endDate) > 0) {
            throw new ParamCheckException("打卡记录时间跨度不能超过一个月，请检查！！！");
        }
        if (CollectionUtils.isEmpty(request.getUserIdList())) {
            throw new ParamCheckException("useridlist 不能为空，请检查！！！");
        }
        if (request.getUserIdList().size() > LIMIT_USER_COUNT) {
            throw new ParamCheckException("useridlist 最多允许100个用户，请检查！！！");
        }
        if (request.getUserIdList().stream().anyMatch(StringUtils::isBlank)) {
            throw new ParamCheckException("useridlist 不能包含空 userid，请检查！！！");
        }
    }
}
