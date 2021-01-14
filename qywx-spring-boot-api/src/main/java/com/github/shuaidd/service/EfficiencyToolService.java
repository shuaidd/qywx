package com.github.shuaidd.service;

import com.github.shuaidd.response.*;
import com.github.shuaidd.resquest.*;
import org.springframework.stereotype.Service;

/**
 * 描述 效率工具
 *
 * @author ddshuai
 * date 2021-01-07 21:04
 **/
@Service
public class EfficiencyToolService extends AbstractBaseService {

    /**
     * 创建日历
     *
     * @param request
     * @param applicationName
     * @return CalendarResponse
     */
    public CalendarResponse addCalendar(CalendarRequest request, String applicationName) {
        CalendarResponse response = weChatClient.addCalendar(request, applicationName);
        return getResponse(response);
    }

    /**
     * 更新日历
     *
     * @param request
     * @param applicationName
     */
    public void updateCalendar(CalendarRequest request, String applicationName) {
        BaseResponse response = weChatClient.updateCalendar(request, applicationName);
        getResponse(response);
    }

    /**
     * 获取日历详情
     *
     * @param request
     * @param applicationName
     * @return GetCalendarResponse
     */
    public GetCalendarResponse getCalendar(GetCalendarRequest request, String applicationName) {
        GetCalendarResponse response = weChatClient.getCalendar(request, applicationName);
        return getResponse(response);
    }

    /**
     * 删除日历
     *
     * @param request
     * @param applicationName
     */
    public void delCalendar(DeleteCalendarRequest request, String applicationName) {
        BaseResponse response = weChatClient.delCalendar(request, applicationName);
        getResponse(response);
    }

    /**
     * 创建日程
     *
     * @param request
     * @param applicationName
     * @return AddScheduleResponse
     */
    public AddScheduleResponse addSchedule(ScheduleRequest request, String applicationName) {
        AddScheduleResponse response = weChatClient.addSchedule(request, applicationName);
        return getResponse(response);
    }

    /**
     * 更新日程
     *
     * @param request
     * @param applicationName
     */
    public void updateSchedule(ScheduleRequest request, String applicationName) {
        BaseResponse response = weChatClient.updateSchedule(request, applicationName);
        getResponse(response);
    }

    /**
     * 获取日程详情
     *
     * @param request
     * @param applicationName
     * @return GetScheduleResponse
     */
    public GetScheduleResponse getSchedules(GetScheduleRequest request, String applicationName) {
        GetScheduleResponse response = weChatClient.getSchedules(request, applicationName);
        return getResponse(response);
    }

    /**
     * 取消日程
     *
     * @param request
     * @param applicationName
     */
    public void delSchedule(CancelScheduleRequest request, String applicationName) {
        BaseResponse response = weChatClient.delSchedule(request, applicationName);
        getResponse(response);
    }

    /**
     * 获取日历下的日程列表
     *
     * @param request
     * @param applicationName
     * @return GetScheduleResponse
     */
    public GetScheduleResponse getScheduleByCalendar(GetScheduleByCalendarRequest request, String applicationName) {
        GetScheduleResponse response = weChatClient.getScheduleByCalendar(request, applicationName);
        return getResponse(response);
    }

    /**
     * 创建预约会议
     *
     * @param request
     * @param applicationName
     * @return CreateMeetingResponse
     */
    CreateMeetingResponse createMeeting(MeetingRequest request, String applicationName) {
        CreateMeetingResponse response = weChatClient.createMeeting(request, applicationName);
        return getResponse(response);
    }

    /**
     * 修改预约会议
     *
     * @param request
     * @param applicationName
     */
    public void updateMeeting(MeetingRequest request, String applicationName) {
        BaseResponse response = weChatClient.updateMeeting(request, applicationName);
        getResponse(response);
    }

    /**
     * 取消预约会议
     *
     * @param request
     * @param applicationName
     */
    public void cancelMeeting(CancelMeetingRequest request, String applicationName) {
        BaseResponse response = weChatClient.cancelMeeting(request, applicationName);
        getResponse(response);
    }

    /**
     * 获取成员会议ID列表
     *
     * @param request
     * @param applicationName
     * @return GetMeetingForUserResponse
     */
    public GetMeetingForUserResponse getUserMeetingId(GetMeetingForUserRequest request, String applicationName) {
        GetMeetingForUserResponse response = weChatClient.getUserMeetingId(request, applicationName);
        return getResponse(response);
    }

    /**
     * 获取会议详情
     *
     * @param request
     * @param applicationName
     * @return MeetingDetailResponse
     */
    public MeetingDetailResponse getUserMeetingInfo(GetMeetingDetailRequest request, String applicationName) {
        MeetingDetailResponse response = weChatClient.getMeetingInfo(request, applicationName);
        return getResponse(response);
    }
}
