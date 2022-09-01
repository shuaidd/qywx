package com.github.shuaidd.service;

import com.github.shuaidd.dto.tool.DialRecord;
import com.github.shuaidd.dto.wedrive.SpaceInfo;
import com.github.shuaidd.response.tool.*;
import com.github.shuaidd.resquest.oa.CalendarRequest;
import com.github.shuaidd.resquest.tool.*;
import com.github.shuaidd.resquest.wedrive.CreateSpaceRequest;
import com.github.shuaidd.resquest.wedrive.DismissSpaceRequest;
import com.github.shuaidd.resquest.wedrive.RenameSpaceRequest;
import com.github.shuaidd.resquest.wedrive.SpaceInfoRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
     * @param request         请求体
     * @param applicationName 应用名称
     * @return CalendarResponse
     */
    public CalendarResponse addCalendar(CalendarRequest request, String applicationName) {
        return workOaClient.addCalendar(request, applicationName);
    }

    /**
     * 更新日历
     *
     * @param request         请求体
     * @param applicationName 应用名称
     */
    public void updateCalendar(CalendarRequest request, String applicationName) {
        workOaClient.updateCalendar(request, applicationName);
    }

    /**
     * 获取日历详情
     *
     * @param request         请求体
     * @param applicationName 应用名称
     * @return GetCalendarResponse
     */
    public GetCalendarResponse getCalendar(GetCalendarRequest request, String applicationName) {
        return workOaClient.getCalendar(request, applicationName);
    }

    /**
     * 删除日历
     *
     * @param request         请求体
     * @param applicationName 应用名称
     */
    public void delCalendar(DeleteCalendarRequest request, String applicationName) {
        workOaClient.delCalendar(request, applicationName);
    }

    /**
     * 创建日程
     *
     * @param request         请求体
     * @param applicationName 应用名称
     * @return AddScheduleResponse
     */
    public AddScheduleResponse addSchedule(ScheduleRequest request, String applicationName) {
        return workOaClient.addSchedule(request, applicationName);
    }

    /**
     * 更新日程
     *
     * @param request         请求体
     * @param applicationName 应用名称
     */
    public void updateSchedule(ScheduleRequest request, String applicationName) {
        workOaClient.updateSchedule(request, applicationName);
    }

    /**
     * 获取日程详情
     *
     * @param request         请求体
     * @param applicationName 应用名称
     * @return GetScheduleResponse
     */
    public GetScheduleResponse getSchedules(GetScheduleRequest request, String applicationName) {
        return workOaClient.getSchedules(request, applicationName);
    }

    /**
     * 取消日程
     *
     * @param request         请求体
     * @param applicationName 应用名称
     */
    public void delSchedule(CancelScheduleRequest request, String applicationName) {
        workOaClient.delSchedule(request, applicationName);
    }

    /**
     * 获取日历下的日程列表
     *
     * @param request         请求体
     * @param applicationName 应用名称
     * @return GetScheduleResponse
     */
    public GetScheduleResponse getScheduleByCalendar(GetScheduleByCalendarRequest request, String applicationName) {
        return workOaClient.getScheduleByCalendar(request, applicationName);
    }

    /**
     * 创建预约会议
     *
     * @param request         请求体
     * @param applicationName 应用名称
     * @return CreateMeetingResponse
     */
    CreateMeetingResponse createMeeting(MeetingRequest request, String applicationName) {
        return efficiencyToolClient.createMeeting(request, applicationName);
    }

    /**
     * 修改预约会议
     *
     * @param request         请求体
     * @param applicationName 应用名称
     */
    public void updateMeeting(MeetingRequest request, String applicationName) {
        efficiencyToolClient.updateMeeting(request, applicationName);
    }

    /**
     * 取消预约会议
     *
     * @param request         请求体
     * @param applicationName 应用名称
     */
    public void cancelMeeting(CancelMeetingRequest request, String applicationName) {
        efficiencyToolClient.cancelMeeting(request, applicationName);
    }

    /**
     * 获取成员会议ID列表
     *
     * @param request         请求体
     * @param applicationName 应用名称
     * @return GetMeetingForUserResponse
     */
    public GetMeetingForUserResponse getUserMeetingId(GetMeetingForUserRequest request, String applicationName) {
        return efficiencyToolClient.getUserMeetingId(request, applicationName);
    }

    /**
     * 获取会议详情
     *
     * @param request         请求体
     * @param applicationName 应用名称
     * @return MeetingDetailResponse
     */
    public MeetingDetailResponse getUserMeetingInfo(GetMeetingDetailRequest request, String applicationName) {
        return efficiencyToolClient.getMeetingInfo(request, applicationName);
    }

    /**
     * 创建预约直播
     *
     * @param request         请求体
     * @param applicationName 应用名称
     * @return CreateLivingResponse
     */
    public CreateLivingResponse createLiving(LivingRequest request, String applicationName) {
        return efficiencyToolClient.createLiving(request, applicationName);
    }

    /**
     * 创建预约直播
     *
     * @param request         请求体
     * @param applicationName 应用名
     */
    public void modifyLiving(LivingRequest request, String applicationName) {
        efficiencyToolClient.modifyLiving(request, applicationName);
    }

    /**
     * 取消预约直播
     *
     * @param request         请求体
     * @param applicationName 应用名
     */
    public void cancelLiving(CancelLivingRequest request, String applicationName) {
        efficiencyToolClient.cancelLiving(request, applicationName);
    }

    /**
     * 删除直播回放
     *
     * @param request         请求体
     * @param applicationName 应用名
     */
    public void delLiving(DeleteLivingRequest request, String applicationName) {
        efficiencyToolClient.delLiving(request, applicationName);
    }

    /**
     * 获取微信观看直播凭证
     *
     * @param request         请求体
     * @param applicationName 应用名
     * @return LivingCodeResponse
     */
    public LivingCodeResponse getLivingCode(LivingCodeRequest request, String applicationName) {
        return efficiencyToolClient.getLivingCode(request, applicationName);
    }

    /**
     * 获取成员直播ID列表
     *
     * @param request         请求体
     * @param applicationName 应用名
     * @return GetUserLivingResponse
     */
    public GetUserLivingResponse getUserAllLivingId(GetUserLivingRequest request, String applicationName) {
        return efficiencyToolClient.getUserAllLivingId(request, applicationName);
    }

    /**
     * 获取直播详情
     *
     * @param livingId        直播编号
     * @param applicationName 应用名
     * @return LivingInfoResponse
     */
    public LivingInfoResponse getLivingInfo(String livingId, String applicationName) {
        return efficiencyToolClient.getLivingInfo(livingId, applicationName);
    }

    /**
     * 获取成员直播ID列表
     *
     * @param request         请求体
     * @param applicationName 应用名
     * @return WatchStatInfoResponse
     */
    public WatchStatInfoResponse getWatchStat(GetWatchStatRequest request, String applicationName) {
        return efficiencyToolClient.getWatchStat(request, applicationName);
    }

    /**
     * 获取跳转小程序商城的直播观众信息
     *
     * @param request         请求体
     * @param applicationName 应用名
     * @return LivingShareResponse
     */
    public LivingShareResponse getLivingShareInfo(LivingShareRequest request, String applicationName) {
        return efficiencyToolClient.getLivingShareInfo(request, applicationName);
    }

    /**
     * 获取公费电话拨打记录
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return DialRecord
     */
    public List<DialRecord> getDialRecord(DialRecordRequest request, String applicationName) {
        Objects.requireNonNull(request, "参数为空");
        return efficiencyToolClient.getDialRecord(request, applicationName).getRecords();
    }

    /**
     * 创建空间
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 空间编号
     */
    public String createSpace(CreateSpaceRequest request, String applicationName) {
        return efficiencyToolClient.createSpace(request, applicationName).getSpaceId();
    }

    /**
     * 重命名空间
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void renameSpace(RenameSpaceRequest request, String applicationName) {
        efficiencyToolClient.renameSpace(request, applicationName);
    }

    /**
     * 解散空间
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void dismissSpace(DismissSpaceRequest request, String applicationName) {
        efficiencyToolClient.dismissSpace(request, applicationName);
    }

    /**
     * 获取空间/相册信息
     * @param request 请求
     * @param applicationName 应用名称
     * @return 空间信息
     */
    public SpaceInfo spaceInfo(SpaceInfoRequest request, String applicationName) {
        return efficiencyToolClient.spaceInfo(request, applicationName).getSpaceInfo();
    }
}
