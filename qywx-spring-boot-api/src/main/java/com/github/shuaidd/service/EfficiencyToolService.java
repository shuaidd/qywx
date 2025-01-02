package com.github.shuaidd.service;

import com.github.shuaidd.client.EfficiencyToolClient;
import com.github.shuaidd.dto.tool.DialRecord;
import com.github.shuaidd.dto.wedrive.SpaceInfo;
import com.github.shuaidd.response.tool.*;
import com.github.shuaidd.response.wedrive.*;
import com.github.shuaidd.resquest.oa.CalendarRequest;
import com.github.shuaidd.resquest.tool.*;
import com.github.shuaidd.resquest.wedrive.*;
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

    private final EfficiencyToolClient efficiencyToolClient;

    public EfficiencyToolService(EfficiencyToolClient efficiencyToolClient) {
        this.efficiencyToolClient = efficiencyToolClient;
    }

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
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 空间信息
     */
    public SpaceInfo spaceInfo(SpaceInfoRequest request, String applicationName) {
        return efficiencyToolClient.spaceInfo(request, applicationName).getSpaceInfo();
    }

    /**
     * 添加成员/部门
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void addSpaceAcl(SpaceAclRequest request, String applicationName) {
        efficiencyToolClient.addSpaceAcl(request, applicationName);
    }

    /**
     * 移除成员/部门
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void delSpaceAcl(SpaceAclRequest request, String applicationName) {
        efficiencyToolClient.delSpaceAcl(request, applicationName);
    }

    /**
     * 安全设置
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void spaceSetting(SpaceSettingRequest request, String applicationName) {
        efficiencyToolClient.spaceSetting(request, applicationName);
    }

    /**
     * 获取邀请链接
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 分享连接
     */
    public String spaceShare(CommonSpaceRequest request, String applicationName) {
        return efficiencyToolClient.spaceShare(request, applicationName).getSpaceShareUrl();
    }

    /**
     * 获取文件列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 文件列表
     */
    public SpaceFileResponse listFile(QueryFileRequest request, String applicationName) {
        return efficiencyToolClient.listFile(request, applicationName);
    }

    /**
     * 新建文件/文档
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 文件编号
     */
    public CreateFileResponse createFile(CreateFileRequest request, String applicationName) {
        return efficiencyToolClient.createFile(request, applicationName);
    }

    /**
     * 上传文件
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 文件编号
     */
    public CreateFileResponse uploadFile(UploadFileRequest request, String applicationName) {
        return efficiencyToolClient.uploadFile(request, applicationName);
    }

    /**
     * 下载文件
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 文件编号
     */
    public DownloadFileResponse downloadFile(DownloadFileRequest request, String applicationName) {
        return efficiencyToolClient.downloadFile(request, applicationName);
    }

    /**
     * 重命名文件
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 文件信息
     */
    public RenameFileResponse renameFile(RenameFileRequest request, String applicationName) {
        return efficiencyToolClient.renameFile(request, applicationName);
    }

    /**
     * 移动文件
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 文件信息
     */
    public MoveFileResponse moveFile(MoveFileRequest request, String applicationName) {
        return efficiencyToolClient.moveFile(request, applicationName);
    }

    /**
     * 删除文件
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void deleteFile(DeleteFileRequest request, String applicationName) {
        efficiencyToolClient.deleteFile(request, applicationName);
    }

    /**
     * 文件信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 文件信息
     */
    public FileInfoResponse fileInfo(FileInfoRequest request, String applicationName) {
        return efficiencyToolClient.fileInfo(request, applicationName);
    }

    /**
     * 文件权限 新增成员
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void addFileAcl(FileAclRequest request, String applicationName) {
        efficiencyToolClient.addFileAcl(request, applicationName);
    }

    /**
     * 文件权限 删除成员
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void delFileAcl(FileAclRequest request, String applicationName) {
        efficiencyToolClient.delFileAcl(request, applicationName);
    }

    /**
     * 文件权限 文件设置
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void fileSetting(FileSettingRequest request, String applicationName) {
        efficiencyToolClient.fileSetting(request, applicationName);
    }

    /**
     * 文件权限 获取分享链接
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 文件信息
     */
    public FileShareResponse getFileShare(FileInfoRequest request, String applicationName) {
        return efficiencyToolClient.getFileShare(request, applicationName);
    }

    /**
     * 获取盘专业版信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 文件信息
     */
    public ProInfoResponse proInfo(ProInfoRequest request, String applicationName) {
        return efficiencyToolClient.proInfo(request, applicationName);
    }

    /**
     * 获取盘专业版信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 文件信息
     */
    public CapacityResponse capacity(ProInfoRequest request, String applicationName) {
        return efficiencyToolClient.capacity(request, applicationName);
    }

    /**
     * 分块上传初始化
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 初始化信息
     */
    public InitUploadFileResponse initUploadFile(InitUploadFileRequest request, String applicationName) {
        return efficiencyToolClient.initUploadFile(request, applicationName);
    }

    /**
     * 分块上传
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void fileUploadPart(FileUploadPartRequest request, String applicationName) {
        efficiencyToolClient.fileUploadPart(request, applicationName);
    }

    /**
     * 分块上传完成
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 文件编号
     */
    public FileUploadFinishResponse fileUploadFinish(FileUploadFinishRequest request, String applicationName) {
        return efficiencyToolClient.fileUploadFinish(request, applicationName);
    }
}
