package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.oa.AddMeetingRoomResponse;
import com.github.shuaidd.response.oa.ApplyEventResponse;
import com.github.shuaidd.response.oa.ApprovalDataResponse;
import com.github.shuaidd.response.oa.ApprovalDetailResponse;
import com.github.shuaidd.response.oa.ApproveTemplateResponse;
import com.github.shuaidd.response.oa.BookingInfoResponse;
import com.github.shuaidd.response.oa.BookMeetingRoomResponse;
import com.github.shuaidd.response.oa.CheckInDataResponse;
import com.github.shuaidd.response.oa.CheckInDayReportResponse;
import com.github.shuaidd.response.oa.CheckInOptionResponse;
import com.github.shuaidd.response.oa.CheckInRuleResponse;
import com.github.shuaidd.response.oa.CheckInScheduleResponse;
import com.github.shuaidd.response.oa.CreateApprovalTemplateResponse;
import com.github.shuaidd.response.oa.EmergencyCallResponse;
import com.github.shuaidd.response.oa.GetCallStateResponse;
import com.github.shuaidd.response.oa.HardwareCheckInDataResponse;
import com.github.shuaidd.response.oa.JournalRecordResponse;
import com.github.shuaidd.response.oa.JournalReportDetailResponse;
import com.github.shuaidd.response.oa.JournalReportStatResponse;
import com.github.shuaidd.response.oa.JournalWedriveFileResponse;
import com.github.shuaidd.response.oa.MeetingRoomListResponse;
import com.github.shuaidd.response.oa.SpNoResponse;
import com.github.shuaidd.response.oa.TodoDetailResponse;
import com.github.shuaidd.response.oa.UserVacationQuotaResponse;
import com.github.shuaidd.response.oa.VacationConfigResponse;
import com.github.shuaidd.response.tool.AddScheduleResponse;
import com.github.shuaidd.response.tool.CalendarResponse;
import com.github.shuaidd.response.tool.GetCalendarResponse;
import com.github.shuaidd.response.tool.GetScheduleResponse;
import com.github.shuaidd.resquest.JournalReportStatRequest;
import com.github.shuaidd.resquest.kf.PunchCorrectionRequest;
import com.github.shuaidd.resquest.oa.AddCheckInRecordRequest;
import com.github.shuaidd.resquest.oa.AddCheckInUserFaceRequest;
import com.github.shuaidd.resquest.oa.ApplyEventRequest;
import com.github.shuaidd.resquest.oa.ApprovalDataRequest;
import com.github.shuaidd.resquest.oa.ApprovalDetailRequest;
import com.github.shuaidd.resquest.oa.BookMeetingRoomRequest;
import com.github.shuaidd.resquest.oa.CalendarRequest;
import com.github.shuaidd.resquest.oa.CancelBookRequest;
import com.github.shuaidd.resquest.oa.CheckInDataRequest;
import com.github.shuaidd.resquest.oa.CheckInRuleOptionRequest;
import com.github.shuaidd.resquest.oa.CheckInRuleRequest;
import com.github.shuaidd.resquest.oa.ClearCheckInRuleArrayFieldRequest;
import com.github.shuaidd.resquest.oa.CommonOaRequest;
import com.github.shuaidd.resquest.oa.CreateApprovalTemplateRequest;
import com.github.shuaidd.resquest.oa.DeleteCheckInRuleRequest;
import com.github.shuaidd.resquest.oa.EmergencyCallRequest;
import com.github.shuaidd.resquest.oa.GetApprovalNoRequest;
import com.github.shuaidd.resquest.oa.GetBookingInfoRequest;
import com.github.shuaidd.resquest.oa.GetCallStateRequest;
import com.github.shuaidd.resquest.oa.HardwareCheckInDataRequest;
import com.github.shuaidd.resquest.oa.JournalRecordRequest;
import com.github.shuaidd.resquest.oa.JournalReportDetailRequest;
import com.github.shuaidd.resquest.oa.JournalWedriveFileRequest;
import com.github.shuaidd.resquest.oa.MeetingRoomRequest;
import com.github.shuaidd.resquest.oa.SetCheckInScheduleRequest;
import com.github.shuaidd.resquest.oa.TemplateRequest;
import com.github.shuaidd.resquest.oa.TodoDetailRequest;
import com.github.shuaidd.resquest.oa.UpdateApprovalTemplateRequest;
import com.github.shuaidd.resquest.oa.UpdateTodoRequest;
import com.github.shuaidd.resquest.oa.UpdateUserQuotaRequest;
import com.github.shuaidd.resquest.oa.UserIdRequest;
import com.github.shuaidd.resquest.tool.CancelScheduleRequest;
import com.github.shuaidd.resquest.tool.DeleteCalendarRequest;
import com.github.shuaidd.resquest.tool.GetCalendarRequest;
import com.github.shuaidd.resquest.tool.GetScheduleByCalendarRequest;
import com.github.shuaidd.resquest.tool.GetScheduleRequest;
import com.github.shuaidd.resquest.tool.ScheduleAttendeesRequest;
import com.github.shuaidd.resquest.tool.ScheduleRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * OA相关功能
 *
 * @author ddshuai
 * date 2022-08-31 22:28
 **/
public interface WorkOaClient extends CommonClient {
    /**
     * 创建日历
     *
     * @param request 请求体
     * @param app     应用名
     * @return CalendarResponse
     */
    @RequestLine("POST /oa/calendar/add?app={app}")
    @Headers(HEAD)
    CalendarResponse addCalendar(CalendarRequest request, @Param("app") String app);

    /**
     * 更新日历
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /oa/calendar/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateCalendar(CalendarRequest request, @Param("app") String app);

    /**
     * 获取日历详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetCalendarResponse
     */
    @RequestLine("POST /oa/calendar/get?app={app}")
    @Headers(HEAD)
    GetCalendarResponse getCalendar(GetCalendarRequest request, @Param("app") String app);

    /**
     * 删除日历
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /oa/calendar/del?app={app}")
    @Headers(HEAD)
    BaseResponse delCalendar(DeleteCalendarRequest request, @Param("app") String app);

    /**
     * 创建日程
     *
     * @param request 请求体
     * @param app     应用名
     * @return AddScheduleResponse
     */
    @RequestLine("POST /oa/schedule/add?app={app}")
    @Headers(HEAD)
    AddScheduleResponse addSchedule(ScheduleRequest request, @Param("app") String app);

    /**
     * 更新日程
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /oa/schedule/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateSchedule(ScheduleRequest request, @Param("app") String app);

    /**
     * 获取日程详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetScheduleResponse
     */
    @RequestLine("POST /oa/schedule/get?app={app}")
    @Headers(HEAD)
    GetScheduleResponse getSchedules(GetScheduleRequest request, @Param("app") String app);

    /**
     * 取消日程
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /oa/schedule/del?app={app}")
    @Headers(HEAD)
    BaseResponse delSchedule(CancelScheduleRequest request, @Param("app") String app);

    /**
     * 获取日历下的日程列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetScheduleResponse
     */
    @RequestLine("POST /oa/schedule/get_by_calendar?app={app}")
    @Headers(HEAD)
    GetScheduleResponse getScheduleByCalendar(GetScheduleByCalendarRequest request, @Param("app") String app);

    /**
     * 新增日程参与者
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /oa/schedule/add_attendees?app={app}")
    @Headers(HEAD)
    BaseResponse addScheduleAttendees(ScheduleAttendeesRequest request, @Param("app") String app);

    /**
     * 删除日程参与者
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /oa/schedule/del_attendees?app={app}")
    @Headers(HEAD)
    BaseResponse delScheduleAttendees(ScheduleAttendeesRequest request, @Param("app") String app);

    /**
     * 获取审批模板详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return ApproveTemplateResponse
     */
    @RequestLine("POST /oa/gettemplatedetail?app={app}")
    @Headers(HEAD)
    ApproveTemplateResponse getTemplateDetail(TemplateRequest request, @Param("app") String app);

    /**
     * 创建审批模板
     *
     * @param request 请求体
     * @param app     应用名
     * @return CreateApprovalTemplateResponse
     */
    @RequestLine("POST /oa/approval/create_template?app={app}")
    @Headers(HEAD)
    CreateApprovalTemplateResponse createApprovalTemplate(CreateApprovalTemplateRequest request, @Param("app") String app);

    /**
     * 更新审批模板
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /oa/approval/update_template?app={app}")
    @Headers(HEAD)
    BaseResponse updateApprovalTemplate(UpdateApprovalTemplateRequest request, @Param("app") String app);

    /**
     * 获取待办详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return TodoDetailResponse
     */
    @RequestLine("POST /todo/get?app={app}")
    @Headers(HEAD)
    TodoDetailResponse getTodoDetail(TodoDetailRequest request, @Param("app") String app);

    /**
     * 更新待办状态
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /todo/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateTodo(UpdateTodoRequest request, @Param("app") String app);

    /**
     * 提交审批申请
     *
     * @param request 请求体
     * @param app     应用名
     * @return ApplyEventResponse
     */
    @RequestLine("POST /oa/applyevent?app={app}")
    @Headers(HEAD)
    ApplyEventResponse applyEvent(ApplyEventRequest request, @Param("app") String app);

    /**
     * 批量获取审批单号
     *
     * @param request 请求体
     * @param app     应用名
     * @return SpNoResponse
     */
    @RequestLine("POST /oa/getapprovalinfo?app={app}")
    @Headers(HEAD)
    SpNoResponse getApprovalInfo(GetApprovalNoRequest request, @Param("app") String app);

    /**
     * 获取审批申请详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return ApprovalDetailResponse
     */
    @RequestLine("POST /oa/getapprovaldetail?app={app}")
    @Headers(HEAD)
    ApprovalDetailResponse getApprovalDetail(ApprovalDetailRequest request, @Param("app") String app);

    /**
     * 获取企业假期管理配置
     *
     * @param app 应用名
     * @return VacationConfigResponse
     */
    @RequestLine("GET /oa/vacation/getcorpconf?app={app}")
    @Headers(HEAD)
    VacationConfigResponse getCorpConf(@Param("app") String app);

    /**
     * 获取成员假期余额
     *
     * @param request 请求体
     * @param app     应用名
     * @return UserVacationQuotaResponse
     */
    @RequestLine("POST /oa/vacation/getuservacationquota?app={app}")
    @Headers(HEAD)
    UserVacationQuotaResponse getUserVacationQuota(UserIdRequest request, @Param("app") String app);

    /**
     * 修改成员假期余额
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /oa/vacation/setoneuserquota?app={app}")
    @Headers(HEAD)
    BaseResponse setOneUserQuota(UpdateUserQuotaRequest request, @Param("app") String app);

    /**
     * 批量获取汇报记录单号
     *
     * @param request 请求体
     * @param app     应用名
     * @return JournalRecordResponse
     */
    @RequestLine("POST /oa/journal/get_record_list?app={app}")
    @Headers(HEAD)
    JournalRecordResponse getJournalRecordList(JournalRecordRequest request, @Param("app") String app);

    /**
     * 获取汇报记录详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return JournalRecordResponse
     */
    @RequestLine("POST /oa/journal/get_record_detail?app={app}")
    @Headers(HEAD)
    JournalReportDetailResponse getJournalRecordDetail(JournalReportDetailRequest request, @Param("app") String app);

    /**
     * 获取汇报统计数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return JournalReportStatResponse
     */
    @RequestLine("POST /oa/journal/get_stat_list?app={app}")
    @Headers(HEAD)
    JournalReportStatResponse getJournalStatList(JournalReportStatRequest request, @Param("app") String app);

    /**
     * 下载汇报关联的微盘文件
     *
     * @param request 请求体
     * @param app     应用名
     * @return JournalWedriveFileResponse
     */
    @RequestLine("POST /oa/journal/download_wedrive_file?app={app}")
    @Headers(HEAD)
    JournalWedriveFileResponse downloadJournalWedriveFile(JournalWedriveFileRequest request, @Param("app") String app);

    /**
     * 添加会议室
     *
     * @param request 请求体
     * @param app     应用名
     * @return JournalReportStatResponse
     */
    @RequestLine("POST /oa/meetingroom/add?app={app}")
    @Headers(HEAD)
    AddMeetingRoomResponse addMeetingRoom(MeetingRoomRequest request, @Param("app") String app);

    /**
     * 查询会议室
     *
     * @param request 请求体
     * @param app     应用名
     * @return MeetingRoomListResponse
     */
    @RequestLine("POST /oa/meetingroom/list?app={app}")
    @Headers(HEAD)
    MeetingRoomListResponse searchMeetingRoom(MeetingRoomRequest request, @Param("app") String app);

    /**
     * 编辑会议室
     *
     * @param request 请求体
     * @param app     应用名
     * @return MeetingRoomListResponse
     */
    @RequestLine("POST /oa/meetingroom/edit?app={app}")
    @Headers(HEAD)
    BaseResponse updateMeetingRoom(MeetingRoomRequest request, @Param("app") String app);

    /**
     * 删除会议室
     *
     * @param request 请求体
     * @param app     应用名
     * @return MeetingRoomListResponse
     */
    @RequestLine("POST /oa/meetingroom/del?app={app}")
    @Headers(HEAD)
    BaseResponse delMeetingRoom(MeetingRoomRequest request, @Param("app") String app);

    /**
     * 查询会议室的预定信息
     *
     * @param request 请求体
     * @param app     应用名
     * @return BookingInfoResponse
     */
    @RequestLine("POST /oa/meetingroom/get_booking_info?app={app}")
    @Headers(HEAD)
    BookingInfoResponse getBookingInfo(GetBookingInfoRequest request, @Param("app") String app);

    /**
     * 预定会议室
     *
     * @param request 请求体
     * @param app     应用名
     * @return BookMeetingRoomResponse
     */
    @RequestLine("POST /oa/meetingroom/book?app={app}")
    @Headers(HEAD)
    BookMeetingRoomResponse bookMeetingRoom(BookMeetingRoomRequest request, @Param("app") String app);

    /**
     * 取消预定会议室
     *
     * @param request 请求体
     * @param app     应用名
     * @return BookMeetingRoomResponse
     */
    @RequestLine("POST /oa/meetingroom/cancel_book?app={app}")
    @Headers(HEAD)
    BaseResponse cancelBookMeetingRoom(CancelBookRequest request, @Param("app") String app);

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
    @RequestLine("POST /checkin/getcheckindata?app={app}")
    @Headers(HEAD)
    CheckInDataResponse getCheckInData(CheckInDataRequest request, @Param("app") String app);

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
    @RequestLine("POST /checkin/getcheckinoption?app={app}")
    @Headers(HEAD)
    CheckInRuleResponse getCheckInOption(CheckInRuleRequest request, @Param("app") String app);

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
    @RequestLine("POST /corp/getapprovaldata?app={app}")
    @Headers(HEAD)
    ApprovalDataResponse getApprovalData(ApprovalDataRequest request, @Param("app") String app);

    /**
     * 获取企业所有打卡规则
     *
     * @param app 应用名
     * @return CheckInOptionResponse
     */
    @RequestLine("POST /checkin/getcorpcheckinoption?app={app}")
    @Headers(HEAD)
    CheckInOptionResponse getCorpCheckInOption(@Param("app") String app);

    /**
     * 获取打卡日报数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return CheckInDayReportResponse
     */
    @RequestLine("POST /checkin/getcheckin_daydata?app={app}")
    @Headers(HEAD)
    CheckInDayReportResponse getCheckInDayData(CommonOaRequest request, @Param("app") String app);

    /**
     * 获取打卡月报数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return CheckInDayReportResponse
     */
    @RequestLine("POST /checkin/getcheckin_monthdata?app={app}")
    @Headers(HEAD)
    CheckInDayReportResponse getCheckInMonthData(CommonOaRequest request, @Param("app") String app);

    /**
     * 获取打卡人员排班信息
     *
     * @param request 请求体
     * @param app     应用名
     * @return CheckInScheduleResponse
     */
    @RequestLine("POST /checkin/getcheckinschedulist?app={app}")
    @Headers(HEAD)
    CheckInScheduleResponse getCheckInScheduList(CommonOaRequest request, @Param("app") String app);

    /**
     * 为打卡人员排班
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /checkin/setcheckinschedulist?app={app}")
    @Headers(HEAD)
    BaseResponse setCheckInScheduleList(SetCheckInScheduleRequest request, @Param("app") String app);

    /**
     * 录入打卡人员人脸信息
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /checkin/addcheckinuserface?app={app}")
    @Headers(HEAD)
    BaseResponse addCheckInUserFace(AddCheckInUserFaceRequest request, @Param("app") String app);

    /**
     * 添加打卡记录
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /checkin/add_checkin_record?app={app}")
    @Headers(HEAD)
    BaseResponse addCheckInRecord(AddCheckInRecordRequest request, @Param("app") String app);

    /**
     * 获取考勤设备打卡数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return HardwareCheckInDataResponse
     */
    @RequestLine("POST /hardware/get_hardware_checkin_data?app={app}")
    @Headers(HEAD)
    HardwareCheckInDataResponse getHardwareCheckInData(HardwareCheckInDataRequest request, @Param("app") String app);

    /**
     * 创建打卡规则
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /checkin/add_checkin_option?app={app}")
    @Headers(HEAD)
    BaseResponse createCheckInRule(CheckInRuleOptionRequest request, @Param("app") String app);

    /**
     * 修改打卡规则
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /checkin/update_checkin_option?app={app}")
    @Headers(HEAD)
    BaseResponse updateCheckInRule(CheckInRuleOptionRequest request, @Param("app") String app);

    /**
     * 清空打卡规则数组元素
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /checkin/clear_checkin_option_array_field?app={app}")
    @Headers(HEAD)
    BaseResponse clearCheckInRuleArrayField(ClearCheckInRuleArrayFieldRequest request, @Param("app") String app);

    /**
     * 删除打卡规则
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /checkin/del_checkin_option?app={app}")
    @Headers(HEAD)
    BaseResponse deleteCheckInRule(DeleteCheckInRuleRequest request, @Param("app") String app);

    /**
     * 发起语音电话
     *
     * @param request 请求体
     * @param app     应用名
     * @return EmergencyCallResponse
     */
    @RequestLine("POST /pstncc/call?app={app}")
    @Headers(HEAD)
    EmergencyCallResponse pstnccCall(EmergencyCallRequest request, @Param("app") String app);

    /**
     * 获取接听状态
     *
     * @param request 请求体
     * @param app     应用名
     * @return EmergencyCallResponse
     */
    @RequestLine("POST /pstncc/getstates?app={app}")
    @Headers(HEAD)
    GetCallStateResponse pstnccCallState(GetCallStateRequest request, @Param("app") String app);

    /**
     * 为打卡人员补卡
     *
     * @param request 请求
     * @param app     应用名
     * @return 操作接活
     */
    @RequestLine("POST /checkin/punch_correction?app={app}")
    @Headers(HEAD)
    BaseResponse punchCorrection(PunchCorrectionRequest request, @Param("app") String app);
}
