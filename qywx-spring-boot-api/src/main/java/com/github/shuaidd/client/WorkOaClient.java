package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.oa.*;
import com.github.shuaidd.response.tool.AddScheduleResponse;
import com.github.shuaidd.response.tool.CalendarResponse;
import com.github.shuaidd.response.tool.GetCalendarResponse;
import com.github.shuaidd.response.tool.GetScheduleResponse;
import com.github.shuaidd.resquest.JournalReportStatRequest;
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
}
