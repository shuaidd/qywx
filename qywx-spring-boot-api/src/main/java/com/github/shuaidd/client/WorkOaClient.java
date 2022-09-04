package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.oa.*;
import com.github.shuaidd.response.tool.AddScheduleResponse;
import com.github.shuaidd.response.tool.CalendarResponse;
import com.github.shuaidd.response.tool.GetCalendarResponse;
import com.github.shuaidd.response.tool.GetScheduleResponse;
import com.github.shuaidd.resquest.JournalReportStatRequest;
import com.github.shuaidd.resquest.kf.PunchCorrectionRequest;
import com.github.shuaidd.resquest.oa.*;
import com.github.shuaidd.resquest.tool.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * OA相关功能
 *
 * @author ddshuai
 * date 2022-08-31 22:28
 **/
@FeignClient(name = "WorkOAClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface WorkOaClient extends CommonClient {
    /**
     * 创建日历
     *
     * @param request 请求体
     * @param app     应用名
     * @return CalendarResponse
     */
    @PostMapping(value = "oa/calendar/add", headers = HEAD)
    CalendarResponse addCalendar(CalendarRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新日历
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "oa/calendar/update", headers = HEAD)
    BaseResponse updateCalendar(CalendarRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取日历详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetCalendarResponse
     */
    @PostMapping(value = "oa/calendar/get", headers = HEAD)
    GetCalendarResponse getCalendar(GetCalendarRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除日历
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "oa/calendar/del", headers = HEAD)
    BaseResponse delCalendar(DeleteCalendarRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建日程
     *
     * @param request 请求体
     * @param app     应用名
     * @return AddScheduleResponse
     */
    @PostMapping(value = "oa/schedule/add", headers = HEAD)
    AddScheduleResponse addSchedule(ScheduleRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新日程
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "oa/schedule/update", headers = HEAD)
    BaseResponse updateSchedule(ScheduleRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取日程详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetScheduleResponse
     */
    @PostMapping(value = "oa/schedule/get", headers = HEAD)
    GetScheduleResponse getSchedules(GetScheduleRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 取消日程
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "oa/schedule/del", headers = HEAD)
    BaseResponse delSchedule(CancelScheduleRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取日历下的日程列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetScheduleResponse
     */
    @PostMapping(value = "oa/schedule/get_by_calendar", headers = HEAD)
    GetScheduleResponse getScheduleByCalendar(GetScheduleByCalendarRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取审批模板详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return ApproveTemplateResponse
     */
    @PostMapping(value = "oa/gettemplatedetail", headers = HEAD)
    ApproveTemplateResponse getTemplateDetail(TemplateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 提交审批申请
     *
     * @param request 请求体
     * @param app     应用名
     * @return ApplyEventResponse
     */
    @PostMapping(value = "oa/applyevent", headers = HEAD)
    ApplyEventResponse applyEvent(ApplyEventRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量获取审批单号
     *
     * @param request 请求体
     * @param app     应用名
     * @return SpNoResponse
     */
    @PostMapping(value = "oa/getapprovalinfo", headers = HEAD)
    SpNoResponse getApprovalInfo(GetApprovalNoRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取审批申请详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return ApprovalDetailResponse
     */
    @PostMapping(value = "oa/getapprovaldetail", headers = HEAD)
    ApprovalDetailResponse getApprovalDetail(ApprovalDetailRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取企业假期管理配置
     *
     * @param app 应用名
     * @return VacationConfigResponse
     */
    @GetMapping(value = "oa/vacation/getcorpconf", headers = HEAD)
    VacationConfigResponse getCorpConf(@RequestParam(HEAD_KEY) String app);

    /**
     * 获取成员假期余额
     *
     * @param request 请求体
     * @param app     应用名
     * @return UserVacationQuotaResponse
     */
    @PostMapping(value = "oa/vacation/getuservacationquota", headers = HEAD)
    UserVacationQuotaResponse getUserVacationQuota(UserIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改成员假期余额
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "oa/vacation/setoneuserquota", headers = HEAD)
    BaseResponse setOneUserQuota(UpdateUserQuotaRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量获取汇报记录单号
     *
     * @param request 请求体
     * @param app     应用名
     * @return JournalRecordResponse
     */
    @PostMapping(value = "oa/journal/get_record_list", headers = HEAD)
    JournalRecordResponse getJournalRecordList(JournalRecordRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取汇报记录详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return JournalRecordResponse
     */
    @PostMapping(value = "oa/journal/get_record_detail", headers = HEAD)
    JournalReportDetailResponse getJournalRecordDetail(JournalReportDetailRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取汇报统计数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return JournalReportStatResponse
     */
    @PostMapping(value = "oa/journal/get_stat_list", headers = HEAD)
    JournalReportStatResponse getJournalStatList(JournalReportStatRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 添加会议室
     *
     * @param request 请求体
     * @param app     应用名
     * @return JournalReportStatResponse
     */
    @PostMapping(value = "oa/meetingroom/add", headers = HEAD)
    AddMeetingRoomResponse addMeetingRoom(MeetingRoomRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询会议室
     *
     * @param request 请求体
     * @param app     应用名
     * @return MeetingRoomListResponse
     */
    @PostMapping(value = "oa/meetingroom/list", headers = HEAD)
    MeetingRoomListResponse searchMeetingRoom(MeetingRoomRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 编辑会议室
     *
     * @param request 请求体
     * @param app     应用名
     * @return MeetingRoomListResponse
     */
    @PostMapping(value = "oa/meetingroom/edit", headers = HEAD)
    BaseResponse updateMeetingRoom(MeetingRoomRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除会议室
     *
     * @param request 请求体
     * @param app     应用名
     * @return MeetingRoomListResponse
     */
    @PostMapping(value = "oa/meetingroom/del", headers = HEAD)
    BaseResponse delMeetingRoom(MeetingRoomRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询会议室的预定信息
     *
     * @param request 请求体
     * @param app     应用名
     * @return BookingInfoResponse
     */
    @PostMapping(value = "oa/meetingroom/get_booking_info", headers = HEAD)
    BookingInfoResponse getBookingInfo(GetBookingInfoRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 预定会议室
     *
     * @param request 请求体
     * @param app     应用名
     * @return BookMeetingRoomResponse
     */
    @PostMapping(value = "oa/meetingroom/book", headers = HEAD)
    BookMeetingRoomResponse bookMeetingRoom(BookMeetingRoomRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 取消预定会议室
     *
     * @param request 请求体
     * @param app     应用名
     * @return BookMeetingRoomResponse
     */
    @PostMapping(value = "oa/meetingroom/cancel_book", headers = HEAD)
    BaseResponse cancelBookMeetingRoom(CancelBookRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取打卡数据
     * <p>
     * 获取记录时间跨度不超过一个月
     * 用户列表不超过100个。若用户超过100个，请分批获取
     * 有打卡记录即可获取打卡数据，与当前”打卡应用”是否开启无关
     *
     * @param request 请求体
     * @param app     应用名
     * @return CheckInDataResponse
     */
    @PostMapping(value = "checkin/getcheckindata", headers = HEAD)
    CheckInDataResponse getCheckInData(CheckInDataRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取打卡规则
     * <p>
     * 用户列表不超过100个，若用户超过100个，请分批获取。
     * 用户在不同日期的规则不一定相同，请按天获取。
     *
     * @param request 请求体
     * @param app     应用名
     * @return CheckInRuleResponse
     */
    @PostMapping(value = "checkin/getcheckinoption", headers = HEAD)
    CheckInRuleResponse getCheckInOption(CheckInRuleRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取审批数据
     * <p>
     * 获取审批记录请求参数endtime需要大于startime， 切起始时间跨度不要超过一个月；
     * 一次请求返回的审批记录上限是100条，超过100条记录请使用next_spnum进行分页拉取。
     *
     * @param request 请求体
     * @param app     应用名
     * @return ApprovalDataResponse
     */
    @PostMapping(value = "corp/getapprovaldata", headers = HEAD)
    ApprovalDataResponse getApprovalData(ApprovalDataRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取企业所有打卡规则
     *
     * @param app 应用名
     * @return CheckInOptionResponse
     */
    @PostMapping(value = "checkin/getcorpcheckinoption", headers = HEAD)
    CheckInOptionResponse getCorpCheckInOption(@RequestParam(HEAD_KEY) String app);

    /**
     * 获取打卡日报数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return CheckInDayReportResponse
     */
    @PostMapping(value = "checkin/getcheckin_daydata", headers = HEAD)
    CheckInDayReportResponse getCheckInDayData(CommonOaRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取打卡月报数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return CheckInDayReportResponse
     */
    @PostMapping(value = "checkin/getcheckin_monthdata", headers = HEAD)
    CheckInDayReportResponse getCheckInMonthData(CommonOaRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取打卡人员排班信息
     *
     * @param request 请求体
     * @param app     应用名
     * @return CheckInScheduleResponse
     */
    @PostMapping(value = "checkin/getcheckinschedulist", headers = HEAD)
    CheckInScheduleResponse getCheckInScheduList(CommonOaRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 为打卡人员排班
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "checkin/setcheckinschedulist", headers = HEAD)
    BaseResponse setCheckInScheduleList(SetCheckInScheduleRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 录入打卡人员人脸信息
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "checkin/addcheckinuserface", headers = HEAD)
    BaseResponse addCheckInUserFace(AddCheckInUserFaceRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 发起语音电话
     *
     * @param request 请求体
     * @param app     应用名
     * @return EmergencyCallResponse
     */
    @PostMapping(value = "pstncc/call", headers = HEAD)
    EmergencyCallResponse pstnccCall(EmergencyCallRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取接听状态
     *
     * @param request 请求体
     * @param app     应用名
     * @return EmergencyCallResponse
     */
    @PostMapping(value = "pstncc/getstates", headers = HEAD)
    GetCallStateResponse pstnccCallState(GetCallStateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 为打卡人员补卡
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作接活
     */
    @PostMapping(value = "checkin/punch_correction", headers = HEAD)
    BaseResponse punchCorrection(PunchCorrectionRequest request, @RequestParam(HEAD_KEY) String app);
}
