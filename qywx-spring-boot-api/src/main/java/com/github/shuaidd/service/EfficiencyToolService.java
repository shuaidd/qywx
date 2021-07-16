package com.github.shuaidd.service;

import com.github.shuaidd.response.*;
import com.github.shuaidd.response.tool.*;
import com.github.shuaidd.resquest.oa.CalendarRequest;
import com.github.shuaidd.resquest.tool.*;
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
     * @param request 请求体
     * @param applicationName 应用名称
     * @return CalendarResponse
     */
    public CalendarResponse addCalendar(CalendarRequest request, String applicationName) {
        CalendarResponse response = weChatClient.addCalendar(request, applicationName);
        return getResponse(response);
    }

    /**
     * 更新日历
     *
     * @param request 请求体
     * @param applicationName 应用名称
     */
    public void updateCalendar(CalendarRequest request, String applicationName) {
        BaseResponse response = weChatClient.updateCalendar(request, applicationName);
        getResponse(response);
    }

    /**
     * 获取日历详情
     *
     * @param request 请求体
     * @param applicationName 应用名称
     * @return GetCalendarResponse
     */
    public GetCalendarResponse getCalendar(GetCalendarRequest request, String applicationName) {
        GetCalendarResponse response = weChatClient.getCalendar(request, applicationName);
        return getResponse(response);
    }

    /**
     * 删除日历
     *
     * @param request 请求体
     * @param applicationName 应用名称
     */
    public void delCalendar(DeleteCalendarRequest request, String applicationName) {
        BaseResponse response = weChatClient.delCalendar(request, applicationName);
        getResponse(response);
    }

    /**
     * 创建日程
     *
     * @param request 请求体
     * @param applicationName 应用名称
     * @return AddScheduleResponse
     */
    public AddScheduleResponse addSchedule(ScheduleRequest request, String applicationName) {
        AddScheduleResponse response = weChatClient.addSchedule(request, applicationName);
        return getResponse(response);
    }

    /**
     * 更新日程
     *
     * @param request 请求体
     * @param applicationName 应用名称
     */
    public void updateSchedule(ScheduleRequest request, String applicationName) {
        BaseResponse response = weChatClient.updateSchedule(request, applicationName);
        getResponse(response);
    }

    /**
     * 获取日程详情
     *
     * @param request 请求体
     * @param applicationName 应用名称
     * @return GetScheduleResponse
     */
    public GetScheduleResponse getSchedules(GetScheduleRequest request, String applicationName) {
        GetScheduleResponse response = weChatClient.getSchedules(request, applicationName);
        return getResponse(response);
    }

    /**
     * 取消日程
     *
     * @param request 请求体
     * @param applicationName 应用名称
     */
    public void delSchedule(CancelScheduleRequest request, String applicationName) {
        BaseResponse response = weChatClient.delSchedule(request, applicationName);
        getResponse(response);
    }

    /**
     * 获取日历下的日程列表
     *
     * @param request 请求体
     * @param applicationName 应用名称
     * @return GetScheduleResponse
     */
    public GetScheduleResponse getScheduleByCalendar(GetScheduleByCalendarRequest request, String applicationName) {
        GetScheduleResponse response = weChatClient.getScheduleByCalendar(request, applicationName);
        return getResponse(response);
    }

    /**
     * 创建预约会议
     *
     * @param request 请求体
     * @param applicationName 应用名称
     * @return CreateMeetingResponse
     */
    CreateMeetingResponse createMeeting(MeetingRequest request, String applicationName) {
        CreateMeetingResponse response = weChatClient.createMeeting(request, applicationName);
        return getResponse(response);
    }

    /**
     * 修改预约会议
     *
     * @param request 请求体
     * @param applicationName 应用名称
     */
    public void updateMeeting(MeetingRequest request, String applicationName) {
        BaseResponse response = weChatClient.updateMeeting(request, applicationName);
        getResponse(response);
    }

    /**
     * 取消预约会议
     *
     * @param request 请求体
     * @param applicationName 应用名称
     */
    public void cancelMeeting(CancelMeetingRequest request, String applicationName) {
        BaseResponse response = weChatClient.cancelMeeting(request, applicationName);
        getResponse(response);
    }

    /**
     * 获取成员会议ID列表
     *
     * @param request 请求体
     * @param applicationName 应用名称
     * @return GetMeetingForUserResponse
     */
    public GetMeetingForUserResponse getUserMeetingId(GetMeetingForUserRequest request, String applicationName) {
        GetMeetingForUserResponse response = weChatClient.getUserMeetingId(request, applicationName);
        return getResponse(response);
    }

    /**
     * 获取会议详情
     *
     * @param request 请求体
     * @param applicationName 应用名称
     * @return MeetingDetailResponse
     */
    public MeetingDetailResponse getUserMeetingInfo(GetMeetingDetailRequest request, String applicationName) {
        MeetingDetailResponse response = weChatClient.getMeetingInfo(request, applicationName);
        return getResponse(response);
    }

    /**
     * 创建预约直播
     * @param request 请求体
     * @param applicationName 应用名称
     * @return CreateLivingResponse
     */
    public CreateLivingResponse createLiving(LivingRequest request, String applicationName) {
        CreateLivingResponse response = weChatClient.createLiving(request,applicationName);
        return getResponse(response);
    }

    /**
     * 创建预约直播
     * @param request 请求体
     * @param applicationName 应用名
     */
    public void modifyLiving(LivingRequest request, String applicationName) {
        BaseResponse baseResponse = weChatClient.modifyLiving(request,applicationName);
        getResponse(baseResponse);
    }

    /**
     * 取消预约直播
     * @param request 请求体
     * @param applicationName 应用名
     */
    public void cancelLiving(CancelLivingRequest request, String applicationName) {
        BaseResponse baseResponse = weChatClient.cancelLiving(request,applicationName);
        getResponse(baseResponse);
    }

    /**
     * 删除直播回放
     * @param request 请求体
     * @param applicationName 应用名
     */
    public void delLiving(DeleteLivingRequest request, String applicationName) {
        BaseResponse baseResponse = weChatClient.delLiving(request,applicationName);
        getResponse(baseResponse);
    }

    /**
     * 获取微信观看直播凭证
     * @param request 请求体
     * @param applicationName 应用名
     * @return LivingCodeResponse
     */
    public LivingCodeResponse getLivingCode(LivingCodeRequest request, String applicationName){
        LivingCodeResponse response = weChatClient.getLivingCode(request,applicationName);
        return getResponse(response);
    }

    /**
     * 获取成员直播ID列表
     * @param request 请求体
     * @param applicationName 应用名
     * @return GetUserLivingResponse
     */
    public GetUserLivingResponse getUserAllLivingId(GetUserLivingRequest request, String applicationName){
        GetUserLivingResponse response = weChatClient.getUserAllLivingId(request,applicationName);
        return getResponse(response);
    }

    /**
     * 获取直播详情
     * @param livingId 直播编号
     * @param applicationName 应用名
     * @return LivingInfoResponse
     */
    public LivingInfoResponse getLivingInfo(String livingId, String applicationName){
        LivingInfoResponse livingInfoResponse = weChatClient.getLivingInfo(livingId,applicationName);
        return getResponse(livingInfoResponse);
    }

    /**
     * 获取成员直播ID列表
     * @param request 请求体
     * @param applicationName 应用名
     * @return WatchStatInfoResponse
     */
    public WatchStatInfoResponse getWatchStat(GetWatchStatRequest request, String applicationName) {
        WatchStatInfoResponse response = weChatClient.getWatchStat(request,applicationName);
        return getResponse(response);
    }

    /**
     * 获取跳转小程序商城的直播观众信息
     * @param request 请求体
     * @param applicationName 应用名
     * @return LivingShareResponse
     */
    public LivingShareResponse getLivingShareInfo(LivingShareRequest request, String applicationName){
        LivingShareResponse response = weChatClient.getLivingShareInfo(request,applicationName);
        return getResponse(response);
    }
}
