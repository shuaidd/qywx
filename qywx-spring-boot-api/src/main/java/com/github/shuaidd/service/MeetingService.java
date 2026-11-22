package com.github.shuaidd.service;

import com.github.shuaidd.client.MeetingAdvancedClient;
import com.github.shuaidd.client.MeetingClient;
import com.github.shuaidd.dto.meeting.MeetingPollQuestion;
import com.github.shuaidd.dto.meeting.MeetingRealControlUser;
import com.github.shuaidd.dto.meeting.MeetingRecordSharingConfig;
import com.github.shuaidd.dto.meeting.MeetingAdvancedLayout;
import com.github.shuaidd.dto.meeting.MeetingAdvancedLayoutPage;
import com.github.shuaidd.dto.meeting.MeetingAdvancedLayoutSeat;
import com.github.shuaidd.dto.meeting.MeetingBackgroundImage;
import com.github.shuaidd.dto.meeting.MeetingLayoutPage;
import com.github.shuaidd.dto.meeting.MeetingLayoutGridUser;
import com.github.shuaidd.dto.meeting.MeetingLayoutPollingSetting;
import com.github.shuaidd.dto.meeting.MeetingLayoutResult;
import com.github.shuaidd.dto.meeting.MeetingLayoutSeat;
import com.github.shuaidd.dto.meeting.MeetingLayoutUser;
import com.github.shuaidd.dto.meeting.MeetingMraDevice;
import com.github.shuaidd.dto.meeting.RoomsMra;
import com.github.shuaidd.dto.meeting.MeetingPhoneNumber;
import com.github.shuaidd.dto.meeting.MeetingWebinarGuest;
import com.github.shuaidd.dto.meeting.MeetingWebinarHost;
import com.github.shuaidd.dto.meeting.MeetingWebinarMediaSetting;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.meeting.*;
import com.github.shuaidd.resquest.meeting.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 预约会议管理
 *
 * @author ddshuai
 */
@Service
public class MeetingService extends AbstractBaseService {

    private final MeetingClient meetingClient;
    private final MeetingAdvancedClient meetingAdvancedClient;

    public MeetingService(MeetingClient meetingClient, MeetingAdvancedClient meetingAdvancedClient) {
        this.meetingClient = meetingClient;
        this.meetingAdvancedClient = meetingAdvancedClient;
    }

    /**
     * 获取会议受邀成员列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 邀请人员列表
     */
    public MeetingInviteResponse getMeetingInvites(MeetingInviteRequest request, String applicationName) {
        return meetingClient.getInvitees(request, applicationName);
    }

    /**
     * 更新会议受邀成员列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setMeetingInvitees(MeetingInviteesRequest request, String applicationName) {
        checkMeetingRequest(request);
        return meetingAdvancedClient.setInvitees(request, applicationName);
    }

    /**
     * 创建用户专属参会链接
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 用户专属参会链接
     */
    public MeetingCustomerShortUrlResponse createCustomerShortUrl(MeetingCustomerShortUrlRequest request, String applicationName) {
        checkMeetingRequest(request);
        if (StringUtils.isEmpty(request.getCustomerData())) {
            throw new ParamCheckException("用户专属字段customerData不能为空");
        }
        return meetingAdvancedClient.createCustomerShortUrl(request, applicationName);
    }

    /**
     * 获取用户专属参会链接
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 用户专属参会链接列表
     */
    public MeetingCustomerShortUrlResponse getCustomerShortUrl(MeetingCustomerShortUrlRequest request, String applicationName) {
        checkMeetingRequest(request);
        if (StringUtils.isEmpty(request.getCustomerData())) {
            throw new ParamCheckException("用户专属字段customerData不能为空");
        }
        return meetingAdvancedClient.getCustomerShortUrl(request, applicationName);
    }

    /**
     * 获取实时会中成员列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 实时会中成员列表
     */
    public MeetingAttendeeListResponse getRealtimeAttendees(MeetingAttendeeQueryRequest request, String applicationName) {
        checkMeetingSubMeetingRequest(request);
        return meetingAdvancedClient.getRealtimeAttendees(request, applicationName);
    }

    /**
     * 获取已参会成员列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 已参会成员列表
     */
    public MeetingAttendeeListResponse getAttendees(MeetingAttendeeQueryRequest request, String applicationName) {
        checkMeetingSubMeetingRequest(request);
        return meetingAdvancedClient.getAttendees(request, applicationName);
    }

    /**
     * 获取实时等候室成员列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 实时等候室成员列表
     */
    public MeetingWaitingRoomUserListResponse getCurrentWaitingRoomUsers(MeetingWaitingRoomUserListRequest request, String applicationName) {
        checkMeetingRequest(request);
        return meetingAdvancedClient.getCurrentWaitingRoomUsers(request, applicationName);
    }

    /**
     * 获取等候室成员记录
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 等候室成员历史记录
     */
    public MeetingWaitingRoomUserListResponse getWaitingRoomUsers(MeetingWaitingRoomUserListRequest request, String applicationName) {
        checkMeetingRequest(request);
        return meetingAdvancedClient.getWaitingRoomUsers(request, applicationName);
    }

    /**
     * 获取成员设备是否入会
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 设备入会检查结果
     */
    public MeetingDeviceInMeetingResponse checkDeviceInMeeting(MeetingDeviceInMeetingRequest request, String applicationName) {
        if (request == null || StringUtils.isEmpty(request.getUserId())) {
            throw new ParamCheckException("成员userId不能为空");
        }
        if (CollectionUtils.isEmpty(request.getInstanceIdList()) && CollectionUtils.isEmpty(request.getMeetingIdList())) {
            throw new ParamCheckException("设备类型列表和会议ID列表不能同时为空");
        }
        return meetingAdvancedClient.checkDeviceInMeeting(request, applicationName);
    }

    /**
     * 获取会议嘉宾列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 会议嘉宾列表
     */
    public MeetingGuestListResponse getGuests(MeetingGuestRequest request, String applicationName) {
        checkMeetingRequest(request);
        return meetingAdvancedClient.getGuests(request, applicationName);
    }

    /**
     * 更新会议嘉宾列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setGuests(MeetingGuestRequest request, String applicationName) {
        checkMeetingRequest(request);
        if (CollectionUtils.isEmpty(request.getGuests())) {
            throw new ParamCheckException("会议嘉宾列表不能为空");
        }
        return meetingAdvancedClient.setGuests(request, applicationName);
    }

    /**
     * 获取会议健康度
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 会议健康度
     */
    public MeetingQualityResponse getQuality(MeetingQualityRequest request, String applicationName) {
        checkMeetingRequest(request);
        if (StringUtils.isEmpty(request.getSubMeetingId())) {
            throw new ParamCheckException("周期性会议子会议ID不能为空");
        }
        return meetingAdvancedClient.getQuality(request, applicationName);
    }

    /**
     * 修改会议报名配置
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 报名配置和问题数量
     */
    public MeetingEnrollConfigResponse setEnrollConfig(MeetingEnrollConfigRequest request, String applicationName) {
        checkMeetingRequest(request);
        if (request.getApproveType() == null) {
            throw new ParamCheckException("报名审批方式不能为空");
        }
        if (CollectionUtils.isEmpty(request.getQuestionList())) {
            throw new ParamCheckException("报名问题列表不能为空");
        }
        return meetingAdvancedClient.setEnrollConfig(request, applicationName);
    }

    /**
     * 获取会议报名配置
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 报名配置
     */
    public MeetingEnrollConfigResponse getEnrollConfig(MeetingGuestRequest request, String applicationName) {
        checkMeetingRequest(request);
        return meetingAdvancedClient.getEnrollConfig(request, applicationName);
    }

    /**
     * 获取会议成员报名 ID
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 报名 ID 映射列表
     */
    public MeetingEnrollIdListResponse queryEnrollIds(MeetingEnrollIdQueryRequest request, String applicationName) {
        checkMeetingRequest(request);
        if (CollectionUtils.isEmpty(request.getTmpOpenidList())) {
            throw new ParamCheckException("成员临时OpenID列表不能为空");
        }
        return meetingAdvancedClient.queryEnrollIds(request, applicationName);
    }

    /**
     * 获取会议报名信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 报名记录列表
     */
    public MeetingEnrollListResponse listEnrolls(MeetingEnrollListRequest request, String applicationName) {
        checkMeetingRequest(request);
        return meetingAdvancedClient.listEnrolls(request, applicationName);
    }

    /**
     * 审批会议报名信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 审批处理数量
     */
    public MeetingEnrollApproveResponse approveEnrolls(MeetingEnrollApproveRequest request, String applicationName) {
        checkMeetingRequest(request);
        if (request.getAction() == null) {
            throw new ParamCheckException("报名审批操作类型不能为空");
        }
        if (CollectionUtils.isEmpty(request.getEnrollIdList())) {
            throw new ParamCheckException("报名ID列表不能为空");
        }
        return meetingAdvancedClient.approveEnrolls(request, applicationName);
    }

    /**
     * 导入会议报名信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 导入后的报名列表
     */
    public MeetingEnrollImportResponse importEnrolls(MeetingEnrollImportRequest request, String applicationName) {
        checkMeetingRequest(request);
        if (CollectionUtils.isEmpty(request.getEnrollList())) {
            throw new ParamCheckException("导入的报名信息列表不能为空");
        }
        return meetingAdvancedClient.importEnrolls(request, applicationName);
    }

    /**
     * 删除会议报名信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 删除数量
     */
    public MeetingEnrollDeleteResponse deleteEnrolls(MeetingEnrollDeleteRequest request, String applicationName) {
        checkMeetingRequest(request);
        if (CollectionUtils.isEmpty(request.getEnrollIdList())) {
            throw new ParamCheckException("待删除的报名ID列表不能为空");
        }
        return meetingAdvancedClient.deleteEnrolls(request, applicationName);
    }

    /**
     * 获取会议发起记录
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 会议发起记录列表
     */
    public MeetingStartListResponse getStartList(MeetingStartListRequest request, String applicationName) {
        if (request == null || request.getType() == null) {
            throw new ParamCheckException("会议发起记录查询类型不能为空");
        }
        if (request.getBeginTime() == null || request.getEndTime() == null) {
            throw new ParamCheckException("会议发起记录查询时间范围不能为空");
        }
        if (request.getEndTime() <= request.getBeginTime()) {
            throw new ParamCheckException("会议发起记录查询结束时间必须大于开始时间");
        }
        return meetingAdvancedClient.getStartList(request, applicationName);
    }


    /**
     * 设置进行中会议的会控参数
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setRealControl(MeetingRealControlSettingRequest request, String applicationName) {
        checkMeetingRequest(request);
        checkMeetingId(request.getMeetingId());
        return meetingAdvancedClient.setRealControl(request, applicationName);
    }

    /**
     * 设置或撤销联席主持人
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setCoHost(MeetingCoHostRequest request, String applicationName) {
        checkRealControlRequest(request);
        if (request.getAction() == null) {
            throw new ParamCheckException("联席主持人操作类型不能为空");
        }
        return meetingAdvancedClient.setCoHost(request, applicationName);
    }

    /**
     * 设置或解除成员静音
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse muteUser(MeetingMuteUserRequest request, String applicationName) {
        checkRealControlRequest(request);
        if (request.getOption() == null) {
            throw new ParamCheckException("成员静音操作类型不能为空");
        }
        return meetingAdvancedClient.muteUser(request, applicationName);
    }

    /**
     * 开启或关闭成员视频
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse switchUserVideo(MeetingUserVideoRequest request, String applicationName) {
        checkRealControlRequest(request);
        return meetingAdvancedClient.switchUserVideo(request, applicationName);
    }

    /**
     * 停止成员屏幕共享
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse closeScreenShare(MeetingCloseScreenShareRequest request, String applicationName) {
        checkRealControlRequest(request);
        return meetingAdvancedClient.closeScreenShare(request, applicationName);
    }

    /**
     * 修改成员会中昵称
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setNicknames(MeetingNicknameRequest request, String applicationName) {
        checkMeetingRequest(request);
        checkMeetingId(request.getMeetingId());
        if (CollectionUtils.isEmpty(request.getOperatedUsers())) {
            throw new ParamCheckException("待修改昵称的成员列表不能为空");
        }
        for (MeetingRealControlUser user : request.getOperatedUsers()) {
            checkRealControlUser(user);
            if (StringUtils.isNotEmpty(user.getNickname()) && StringUtils.length(user.getNickname()) > 20) {
                throw new ParamCheckException("成员会中昵称不能超过20个字符");
            }
        }
        return meetingAdvancedClient.setNicknames(request, applicationName);
    }

    /**
     * 管理等候室成员
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse manageWaitingRoomUsers(MeetingWaitingRoomManageRequest request, String applicationName) {
        checkMeetingRequest(request);
        checkMeetingId(request.getMeetingId());
        if (request.getOperateType() == null || request.getOperateType() < 1 || request.getOperateType() > 3) {
            throw new ParamCheckException("等候室成员操作类型必须为1、2或3");
        }
        if (request.getOperateType() != 3 && request.getAllowRejoin() != null) {
            throw new ParamCheckException("仅移出等候室时允许设置是否再次加入");
        }
        checkRealControlUsers(request.getOperatedUsers(), "等候室被操作成员列表");
        return meetingAdvancedClient.manageWaitingRoomUsers(request, applicationName);
    }

    /**
     * 将成员移出会议
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse kickoutUsers(MeetingKickoutUsersRequest request, String applicationName) {
        checkMeetingRequest(request);
        checkMeetingId(request.getMeetingId());
        if (request.getAllowRejoin() == null) {
            throw new ParamCheckException("是否允许再次入会不能为空");
        }
        checkRealControlUsers(request.getOperatedUsers(), "待移出的会议成员列表");
        return meetingAdvancedClient.kickoutUsers(request, applicationName);
    }

    /**
     * 结束进行中的会议
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse dismissMeeting(MeetingDismissRequest request, String applicationName) {
        checkMeetingRequest(request);
        checkMeetingId(request.getMeetingId());
        if (request.getForceDismiss() != null && request.getForceDismiss() != 0 && request.getForceDismiss() != 1) {
            throw new ParamCheckException("是否强制结束会议只能为0或1");
        }
        if (request.getRetrieveCode() != null && request.getRetrieveCode() != 0 && request.getRetrieveCode() != 1) {
            throw new ParamCheckException("是否回收会议号只能为0或1");
        }
        return meetingAdvancedClient.dismissMeeting(request, applicationName);
    }

    /**
     * 创建会议投票
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 投票主题 ID
     */
    public MeetingPollCreateResponse createPoll(MeetingPollCreateRequest request, String applicationName) {
        checkPollRequest(request);
        checkPollTopic(request.getPollTopic(), request.getPollDesc());
        checkPollQuestions(request.getPollQuestions(), true);
        return meetingAdvancedClient.createPoll(request, applicationName);
    }

    /**
     * 全量更新会议投票主题
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse updatePoll(MeetingPollUpdateRequest request, String applicationName) {
        checkPollRequest(request);
        if (StringUtils.isEmpty(request.getPollThemeId())) {
            throw new ParamCheckException("投票主题ID不能为空");
        }
        checkPollTopic(request.getPollTopic(), request.getPollDesc());
        checkPollQuestions(request.getPollQuestions(), false);
        return meetingAdvancedClient.updatePoll(request, applicationName);
    }

    /**
     * 获取会议投票列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 投票列表
     */
    public MeetingPollListResponse getPollList(MeetingPollListRequest request, String applicationName) {
        checkPollRequest(request);
        return meetingAdvancedClient.getPollList(request, applicationName);
    }

    /**
     * 获取投票主题详情
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 投票主题详情
     */
    public MeetingPollThemeInfoResponse getPollThemeInfo(MeetingPollThemeInfoRequest request, String applicationName) {
        checkPollRequest(request);
        if (StringUtils.isEmpty(request.getPollThemeId())) {
            throw new ParamCheckException("投票主题ID不能为空");
        }
        return meetingAdvancedClient.getPollThemeInfo(request, applicationName);
    }

    /**
     * 获取投票详情和结果
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 投票详情
     */
    public MeetingPollDetailResponse getPollDetail(MeetingPollDetailRequest request, String applicationName) {
        checkPollRequest(request);
        if (StringUtils.isEmpty(request.getPollId())) {
            throw new ParamCheckException("投票ID不能为空");
        }
        return meetingAdvancedClient.getPollDetail(request, applicationName);
    }

    /**
     * 删除投票或投票主题
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse deletePoll(MeetingPollDeleteRequest request, String applicationName) {
        checkPollRequest(request);
        if (StringUtils.isEmpty(request.getPollThemeId()) && StringUtils.isEmpty(request.getPollId())) {
            throw new ParamCheckException("投票主题ID和投票ID不能同时为空");
        }
        return meetingAdvancedClient.deletePoll(request, applicationName);
    }

    /**
     * 发起已有投票主题
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 投票 ID
     */
    public MeetingPollStartResponse startPoll(MeetingPollStartRequest request, String applicationName) {
        checkPollRequest(request);
        if (StringUtils.isEmpty(request.getPollThemeId())) {
            throw new ParamCheckException("投票主题ID不能为空");
        }
        return meetingAdvancedClient.startPoll(request, applicationName);
    }

    /**
     * 结束指定投票
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse finishPoll(MeetingPollFinishRequest request, String applicationName) {
        checkPollRequest(request);
        if (StringUtils.isEmpty(request.getPollThemeId())) {
            throw new ParamCheckException("投票主题ID不能为空");
        }
        if (StringUtils.isEmpty(request.getPollId())) {
            throw new ParamCheckException("投票ID不能为空");
        }
        return meetingAdvancedClient.finishPoll(request, applicationName);
    }

    /**
     * 为会议预定Rooms会议室
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return Rooms会议室列表
     */
    public MeetingRoomsBookResponse bookRooms(MeetingRoomsBookRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (CollectionUtils.isEmpty(request.getMeetingRoomIdList())) {
            throw new ParamCheckException("待预定的Rooms会议室列表不能为空");
        }
        return meetingAdvancedClient.bookRooms(request, applicationName);
    }

    /**
     * 释放会议已预定的Rooms会议室
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse releaseRooms(MeetingRoomsReleaseRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (CollectionUtils.isEmpty(request.getMeetingRoomIdList())) {
            throw new ParamCheckException("待释放的Rooms会议室列表不能为空");
        }
        return meetingAdvancedClient.releaseRooms(request, applicationName);
    }

    /**
     * 获取Rooms会议室列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return Rooms会议室列表
     */
    public MeetingRoomsListResponse listRooms(MeetingRoomsListRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkRoomsLimit(request.getLimit(), 50, "Rooms会议室分页");
        return meetingAdvancedClient.listRooms(request, applicationName);
    }

    /**
     * 获取Rooms会议室详情
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return Rooms会议室详情
     */
    public MeetingRoomsInfoResponse getRoomsInfo(MeetingRoomsInfoRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        if (StringUtils.isEmpty(request.getMeetingRoomId())) {
            throw new ParamCheckException("Rooms会议室ID不能为空");
        }
        return meetingAdvancedClient.getRoomsInfo(request, applicationName);
    }

    /**
     * 获取Rooms会议室配置
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return Rooms会议室配置
     */
    public MeetingRoomsConfigResponse getRoomsConfig(MeetingRoomsConfigRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        if (StringUtils.isEmpty(request.getMeetingRoomId())) {
            throw new ParamCheckException("Rooms会议室ID不能为空");
        }
        return meetingAdvancedClient.getRoomsConfig(request, applicationName);
    }

    /**
     * 获取Rooms会议室下的会议列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 会议列表
     */
    public MeetingRoomsMeetingListResponse listRoomsMeetings(MeetingRoomsMeetingListRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        if (StringUtils.isEmpty(request.getMeetingRoomId()) == StringUtils.isEmpty(request.getRoomsId())) {
            throw new ParamCheckException("Rooms会议室ID和Rooms设备ID必须二选一");
        }
        if (request.getStartTime() != null && request.getEndTime() != null) {
            if (request.getStartTime() > request.getEndTime()) {
                throw new ParamCheckException("Rooms会议查询起始时间不能晚于结束时间");
            }
            if (request.getEndTime() - request.getStartTime() > 90L * 24 * 60 * 60) {
                throw new ParamCheckException("Rooms会议查询时间区间不能超过90天");
            }
        }
        checkRoomsLimit(request.getLimit(), 20, "Rooms会议分页");
        return meetingAdvancedClient.listRoomsMeetings(request, applicationName);
    }

    /**
     * 获取Rooms设备列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 设备列表
     */
    public MeetingRoomsDeviceListResponse listRoomsDevices(MeetingRoomsDeviceListRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkRoomsLimit(request.getLimit(), 50, "Rooms设备分页");
        return meetingAdvancedClient.listRoomsDevices(request, applicationName);
    }

    /**
     * 获取Rooms控制器列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 控制器列表
     */
    public MeetingRoomsControllerListResponse listRoomsControllers(MeetingRoomsControllerListRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkRoomsLimit(request.getLimit(), 50, "Rooms控制器分页");
        return meetingAdvancedClient.listRoomsControllers(request, applicationName);
    }

    /**
     * 获取企业Rooms会议室资源
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return Rooms资源统计
     */
    public MeetingRoomsInventoryResponse getRoomsInventory(MeetingRoomsInventoryRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        return meetingAdvancedClient.getRoomsInventory(request, applicationName);
    }

    /**
     * 呼叫Rooms会议室入会
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 呼叫ID
     */
    public MeetingRoomsCallResponse callRooms(MeetingRoomsCallRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        checkRoomsCallTarget(request.getMeetingRoomId(), request.getMraAddress());
        return meetingAdvancedClient.callRooms(request, applicationName);
    }

    /**
     * 取消呼叫Rooms会议室
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse cancelRoomsCall(MeetingRoomsCancelCallRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (StringUtils.isEmpty(request.getInviteId())) {
            throw new ParamCheckException("Rooms呼叫ID不能为空");
        }
        checkRoomsCallTarget(request.getMeetingRoomId(), request.getMraAddress());
        return meetingAdvancedClient.cancelRoomsCall(request, applicationName);
    }

    /**
     * 获取Rooms会议室呼叫应答状态
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 应答状态
     */
    public MeetingRoomsResponseStatusResponse getRoomsCallResponseStatus(MeetingRoomsResponseStatusRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        checkRoomsCallTarget(request.getMeetingRoomId(), request.getMraAddress());
        return meetingAdvancedClient.getRoomsCallResponseStatus(request, applicationName);
    }


    /**
     * 获取云录制记录
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 云录制记录列表
     */
    public MeetingRecordListResponse listMeetingRecords(MeetingRecordListRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        int queryKeyCount = 0;
        queryKeyCount += StringUtils.isNotEmpty(request.getMeetingId()) ? 1 : 0;
        queryKeyCount += StringUtils.isNotEmpty(request.getMeetingCode()) ? 1 : 0;
        queryKeyCount += StringUtils.isNotEmpty(request.getUserId()) ? 1 : 0;
        if (queryKeyCount != 1) {
            throw new ParamCheckException("会议ID、会议Code和查询成员userid必须且只能选择一项");
        }
        checkRecordTimeRange(request.getStartTime(), request.getEndTime(), true);
        checkRoomsLimit(request.getLimit(), 20, "单次返回录制数量");
        return meetingAdvancedClient.listMeetingRecords(request, applicationName);
    }

    /**
     * 获取云录制访问统计数据
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 云录制访问统计列表
     */
    public MeetingRecordStatisticsResponse getMeetingRecordStatistics(MeetingRecordStatisticsRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        if (StringUtils.isEmpty(request.getMeetingRecordId()) || StringUtils.isEmpty(request.getMeetingId())) {
            throw new ParamCheckException("会议录制ID和会议ID不能为空");
        }
        if (request.getStartTime() != null && request.getEndTime() != null) {
            checkRecordTimeRange(request.getStartTime(), request.getEndTime(), false);
        }
        return meetingAdvancedClient.getMeetingRecordStatistics(request, applicationName);
    }

    /**
     * 修改会议录制共享配置
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse updateMeetingRecordSharingConfig(MeetingRecordSharingUpdateRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        if (StringUtils.isEmpty(request.getMeetingRecordId()) || StringUtils.isEmpty(request.getMeetingId())) {
            throw new ParamCheckException("会议录制ID和会议ID不能为空");
        }
        checkRecordSharingConfig(request.getSharingConfig());
        return meetingAdvancedClient.updateMeetingRecordSharingConfig(request, applicationName);
    }

    /**
     * 删除会议录制
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse deleteMeetingRecord(MeetingRecordRequest request, String applicationName) {
        checkMeetingRecordRequest(request);
        return meetingAdvancedClient.deleteMeetingRecord(request, applicationName);
    }

    /**
     * 删除单个录制文件
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse deleteMeetingRecordFile(MeetingRecordFileRequest request, String applicationName) {
        checkMeetingRecordFileRequest(request);
        return meetingAdvancedClient.deleteMeetingRecordFile(request, applicationName);
    }

    /**
     * 获取单个录制文件详情
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 录制文件详情
     */
    public MeetingRecordFileDetailResponse getMeetingRecordFile(MeetingRecordFileRequest request, String applicationName) {
        checkMeetingRecordFileRequest(request);
        return meetingAdvancedClient.getMeetingRecordFile(request, applicationName);
    }

    /**
     * 获取会议录制文件列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 录制文件列表
     */
    public MeetingRecordFileListResponse listMeetingRecordFiles(MeetingRecordRequest request, String applicationName) {
        checkMeetingRecordRequest(request);
        return meetingAdvancedClient.listMeetingRecordFiles(request, applicationName);
    }

    /**
     * 获取录制转写段落列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 转写段落列表
     */
    public MeetingTranscriptParagraphListResponse listMeetingTranscriptParagraphs(MeetingRecordFileRequest request, String applicationName) {
        checkMeetingRecordFileRequest(request);
        return meetingAdvancedClient.listMeetingTranscriptParagraphs(request, applicationName);
    }

    /**
     * 获取录制转写详情
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 转写详情
     */
    public MeetingTranscriptDetailResponse getMeetingTranscriptDetail(MeetingTranscriptDetailRequest request, String applicationName) {
        checkMeetingRecordFileRequest(request.getRecordFileId(), request.getMeetingId());
        if (request.getLimit() != null && request.getLimit() <= 0) {
            throw new ParamCheckException("查询转写段落数必须大于0");
        }
        return meetingAdvancedClient.getMeetingTranscriptDetail(request, applicationName);
    }

    /**
     * 搜索录制转写内容
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 转写搜索结果
     */
    public MeetingTranscriptSearchResponse searchMeetingTranscript(MeetingTranscriptSearchRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingRecordFileRequest(request.getRecordFileId(), request.getMeetingId());
        if (StringUtils.isEmpty(request.getText())) {
            throw new ParamCheckException("搜索文本不能为空");
        }
        return meetingAdvancedClient.searchMeetingTranscript(request, applicationName);
    }

    private void checkRecordTimeRange(Integer startTime, Integer endTime, boolean required) {
        if (required && (startTime == null || endTime == null)) {
            throw new ParamCheckException("查询开始和结束时间不能为空");
        }
        if (startTime == null || endTime == null) {
            return;
        }
        if (startTime > endTime) {
            throw new ParamCheckException("查询开始时间不能晚于结束时间");
        }
        if (endTime - startTime > 31L * 24 * 60 * 60) {
            throw new ParamCheckException("查询时间区间不能超过31天");
        }
    }

    private void checkRecordSharingConfig(MeetingRecordSharingConfig config) {
        if (config == null) {
            return;
        }
        boolean sharingDisabled = Boolean.FALSE.equals(config.getEnableSharing());
        if (sharingDisabled && (config.getSharingAuthType() != null
                || config.getEnablePassword() != null
                || config.getPassword() != null
                || config.getEnableSharingExpire() != null
                || config.getSharingExpire() != null
                || config.getAllowDownload() != null)) {
            throw new ParamCheckException("关闭录制共享时不允许携带其他共享配置");
        }
        if (config.getSharingAuthType() != null && (config.getSharingAuthType() < 0 || config.getSharingAuthType() > 5)) {
            throw new ParamCheckException("录制共享权限类型不符合要求");
        }
        if (Boolean.TRUE.equals(config.getEnablePassword()) && StringUtils.isEmpty(config.getPassword())) {
            throw new ParamCheckException("开启共享密码时必须传入共享密码");
        }
        if (Boolean.FALSE.equals(config.getEnablePassword()) && config.getPassword() != null) {
            throw new ParamCheckException("关闭共享密码时不允许传入共享密码");
        }
        if (Boolean.TRUE.equals(config.getEnableSharingExpire()) && config.getSharingExpire() == null) {
            throw new ParamCheckException("开启共享有效期时必须传入共享有效期");
        }
        if (Boolean.FALSE.equals(config.getEnableSharingExpire()) && config.getSharingExpire() != null) {
            throw new ParamCheckException("关闭共享有效期时不允许传入共享有效期");
        }
    }

    private void checkMeetingRecordRequest(MeetingRecordRequest request) {
        checkMeetingRequest((Object) request);
        checkMeetingRecordFileRequest(request.getMeetingRecordId(), request.getMeetingId());
    }

    private void checkMeetingRecordFileRequest(MeetingRecordFileRequest request) {
        checkMeetingRequest((Object) request);
        checkMeetingRecordFileRequest(request.getRecordFileId(), request.getMeetingId());
    }

    private void checkMeetingRecordFileRequest(String recordFileId, String meetingId) {
        if (StringUtils.isEmpty(recordFileId)) {
            throw new ParamCheckException("录制文件ID不能为空");
        }
        checkMeetingId(meetingId);
    }

    private void checkMeetingRequest(Object request) {
        if (request == null) {
            throw new ParamCheckException("请求参数不能为空");
        }
    }

    private void checkRoomsLimit(Integer limit, int maxSize, String label) {
        if (limit != null && (limit < 1 || limit > maxSize)) {
            throw new ParamCheckException(label + "大小必须在1到" + maxSize + "之间");
        }
    }

    private void checkRoomsCallTarget(String meetingRoomId, RoomsMra mraAddress) {
        if (StringUtils.isEmpty(meetingRoomId) == (mraAddress == null)) {
            throw new ParamCheckException("Rooms会议室ID和MRA地址必须二选一");
        }
        if (mraAddress == null) {
            return;
        }
        if (mraAddress.getProtocol() == null || mraAddress.getProtocol() < 1 || mraAddress.getProtocol() > 2) {
            throw new ParamCheckException("Rooms MRA信令协议只允许1或2");
        }
        if (StringUtils.isEmpty(mraAddress.getDialString())) {
            throw new ParamCheckException("Rooms MRA信令地址不能为空");
        }
    }

    private void checkMeetingId(String meetingId) {
        if (StringUtils.isEmpty(meetingId)) {
            throw new ParamCheckException("会议ID不能为空");
        }
    }

    private void checkMeetingRequest(com.github.shuaidd.resquest.meeting.MeetingInviteesRequest request) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
    }

    private void checkMeetingRequest(MeetingCustomerShortUrlRequest request) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
    }

    private void checkMeetingSubMeetingRequest(MeetingAttendeeQueryRequest request) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (StringUtils.isEmpty(request.getSubMeetingId())) {
            throw new ParamCheckException("周期性会议子会议ID不能为空");
        }
    }

    private void checkMeetingRequest(MeetingWaitingRoomUserListRequest request) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
    }

    private void checkMeetingRequest(MeetingGuestRequest request) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
    }

    private void checkMeetingRequest(MeetingQualityRequest request) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
    }

    private void checkMeetingRequest(MeetingEnrollConfigRequest request) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
    }

    private void checkMeetingRequest(MeetingEnrollIdQueryRequest request) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
    }

    private void checkMeetingRequest(MeetingEnrollListRequest request) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
    }

    private void checkMeetingRequest(MeetingEnrollApproveRequest request) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
    }

    private void checkMeetingRequest(MeetingEnrollImportRequest request) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
    }

    private void checkMeetingRequest(MeetingEnrollDeleteRequest request) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
    }


    private void checkRealControlRequest(MeetingCoHostRequest request) {
        checkRealControlBase(request.getMeetingId(), request.getOperatedUser());
    }

    private void checkRealControlRequest(MeetingMuteUserRequest request) {
        checkRealControlBase(request.getMeetingId(), request.getOperatedUser());
    }

    private void checkRealControlRequest(MeetingUserVideoRequest request) {
        checkRealControlBase(request.getMeetingId(), request.getOperatedUser());
    }

    private void checkRealControlRequest(MeetingCloseScreenShareRequest request) {
        checkRealControlBase(request.getMeetingId(), request.getOperatedUser());
    }

    private void checkRealControlBase(String meetingId, MeetingRealControlUser user) {
        checkMeetingId(meetingId);
        checkRealControlUser(user);
    }

    private void checkRealControlUsers(java.util.List<MeetingRealControlUser> users, String fieldName) {
        if (CollectionUtils.isEmpty(users)) {
            throw new ParamCheckException(fieldName + "不能为空");
        }
        for (MeetingRealControlUser user : users) {
            checkRealControlUser(user);
        }
    }

    private void checkRealControlUser(MeetingRealControlUser user) {
        if (user == null || StringUtils.isEmpty(user.getTmpOpenid()) || user.getInstanceId() == null) {
            throw new ParamCheckException("被操作成员的临时ID和终端设备类型不能为空");
        }
    }

    private void checkPollRequest(MeetingPollListRequest request) {
        checkPollOperator(request.getOperatorUserId(), request.getInstanceId(), request.getMeetingId());
    }

    private void checkPollRequest(MeetingPollThemeInfoRequest request) {
        checkPollOperator(request.getOperatorUserId(), request.getInstanceId(), request.getMeetingId());
    }

    private void checkPollRequest(MeetingPollDetailRequest request) {
        checkPollOperator(request.getOperatorUserId(), request.getInstanceId(), request.getMeetingId());
    }

    private void checkPollRequest(MeetingPollDeleteRequest request) {
        checkPollOperator(request.getOperatorUserId(), request.getInstanceId(), request.getMeetingId());
    }

    private void checkPollRequest(MeetingPollStartRequest request) {
        checkPollOperator(request.getOperatorUserId(), request.getInstanceId(), request.getMeetingId());
    }

    private void checkPollRequest(MeetingPollFinishRequest request) {
        checkPollOperator(request.getOperatorUserId(), request.getInstanceId(), request.getMeetingId());
    }

    private void checkPollRequest(MeetingPollCreateRequest request) {
        checkPollOperator(request.getOperatorUserId(), request.getInstanceId(), request.getMeetingId());
    }

    private void checkPollRequest(MeetingPollUpdateRequest request) {
        checkPollOperator(request.getOperatorUserId(), request.getInstanceId(), request.getMeetingId());
    }

    private void checkPollOperator(String operatorUserId, Integer instanceId, String meetingId) {
        checkMeetingId(meetingId);
        if (StringUtils.isEmpty(operatorUserId)) {
            throw new ParamCheckException("投票操作者openid不能为空");
        }
        if (instanceId == null) {
            throw new ParamCheckException("投票操作者终端设备类型不能为空");
        }
    }

    private void checkPollTopic(String topic, String desc) {
        if (StringUtils.isEmpty(topic)) {
            throw new ParamCheckException("投票主题不能为空");
        }
        if (StringUtils.length(topic) > 50) {
            throw new ParamCheckException("投票主题不能超过50个字符");
        }
        if (StringUtils.isNotEmpty(desc) && StringUtils.length(desc) > 100) {
            throw new ParamCheckException("投票描述不能超过100个字符");
        }
    }

    private void checkPollQuestions(java.util.List<MeetingPollQuestion> questions, boolean create) {
        if (CollectionUtils.isEmpty(questions)) {
            throw new ParamCheckException("投票问题列表不能为空");
        }
        if (questions.size() > 10) {
            throw new ParamCheckException("每个投票最多包含10个问题");
        }
        for (MeetingPollQuestion question : questions) {
            if (question == null || StringUtils.isEmpty(question.getQuestionDesc())) {
                throw new ParamCheckException("投票问题描述不能为空");
            }
            if (StringUtils.length(question.getQuestionDesc()) > 50) {
                throw new ParamCheckException("投票问题描述不能超过50个字符");
            }
            if (question.getQuestionType() == null || question.getQuestionType() < 0 || question.getQuestionType() > 1) {
                throw new ParamCheckException("投票问题类型必须为单选或多选");
            }
            if (CollectionUtils.isEmpty(question.getPollOption())
                    || question.getPollOption().size() > 10
                    || (create && question.getPollOption().size() < 2)) {
                throw new ParamCheckException("投票问题选项数量不符合要求");
            }
            for (String option : question.getPollOption()) {
                if (StringUtils.isEmpty(option) || StringUtils.length(option) > 36) {
                    throw new ParamCheckException("投票选项不能为空且不能超过36个字符");
                }
            }
        }
    }

    /**
     * 创建网络研讨会
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 网络研讨会信息
     */
    public MeetingWebinarCreateResponse createWebinar(MeetingWebinarCreateRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkWebinarSetting(request);
        if (StringUtils.isEmpty(request.getAdminUserId())) {
            throw new ParamCheckException("网络研讨会管理员userid不能为空");
        }
        return meetingAdvancedClient.createWebinar(request, applicationName);
    }

    /**
     * 修改网络研讨会
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse updateWebinar(MeetingWebinarUpdateRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        checkWebinarSetting(request);
        return meetingAdvancedClient.updateWebinar(request, applicationName);
    }

    /**
     * 取消网络研讨会
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse cancelWebinar(MeetingWebinarCancelRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        return meetingAdvancedClient.cancelWebinar(request, applicationName);
    }

    /**
     * 获取网络研讨会详情
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 网络研讨会详情
     */
    public MeetingWebinarDetailResponse getWebinar(MeetingWebinarQueryRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkWebinarQuery(request);
        return meetingAdvancedClient.getWebinar(request, applicationName);
    }

    /**
     * 获取网络研讨会嘉宾列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 网络研讨会嘉宾列表
     */
    public MeetingWebinarGuestListResponse getWebinarGuests(MeetingWebinarQueryRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkWebinarQuery(request);
        return meetingAdvancedClient.getWebinarGuests(request, applicationName);
    }

    /**
     * 更新网络研讨会嘉宾列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse updateWebinarGuests(MeetingWebinarGuestRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        checkWebinarGuests(request.getGuests());
        return meetingAdvancedClient.updateWebinarGuests(request, applicationName);
    }

    /**
     * 设置网络研讨会暖场配置
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse updateWebinarWarmUp(MeetingWebinarWarmUpRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (StringUtils.isNotEmpty(request.getWarmUpPicture()) && StringUtils.isNotEmpty(request.getWarmUpVideo())) {
            throw new ParamCheckException("暖场图片和暖场视频只能选择一个");
        }
        return meetingAdvancedClient.updateWebinarWarmUp(request, applicationName);
    }

    /**
     * 修改网络研讨会报名配置
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 报名配置和问题数量
     */
    public MeetingEnrollConfigResponse setWebinarEnrollConfig(MeetingEnrollConfigRequest request, String applicationName) {
        checkMeetingRequest(request);
        if (request.getApproveType() == null) {
            throw new ParamCheckException("网络研讨会报名审批方式不能为空");
        }
        if (CollectionUtils.isEmpty(request.getQuestionList())) {
            throw new ParamCheckException("网络研讨会报名问题列表不能为空");
        }
        return meetingAdvancedClient.setWebinarEnrollConfig(request, applicationName);
    }

    /**
     * 获取网络研讨会报名配置
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 网络研讨会报名配置
     */
    public MeetingEnrollConfigResponse getWebinarEnrollConfig(MeetingGuestRequest request, String applicationName) {
        checkMeetingRequest(request);
        return meetingAdvancedClient.getWebinarEnrollConfig(request, applicationName);
    }

    /**
     * 获取网络研讨会成员报名 ID
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 报名 ID 映射列表
     */
    public MeetingEnrollIdListResponse queryWebinarEnrollIds(MeetingEnrollIdQueryRequest request, String applicationName) {
        checkMeetingRequest(request);
        if (CollectionUtils.isEmpty(request.getTmpOpenidList())) {
            throw new ParamCheckException("网络研讨会成员临时OpenID列表不能为空");
        }
        return meetingAdvancedClient.queryWebinarEnrollIds(request, applicationName);
    }

    /**
     * 获取网络研讨会报名信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 网络研讨会报名记录列表
     */
    public MeetingEnrollListResponse listWebinarEnrolls(MeetingEnrollListRequest request, String applicationName) {
        checkMeetingRequest(request);
        return meetingAdvancedClient.listWebinarEnrolls(request, applicationName);
    }

    /**
     * 批量审批网络研讨会报名信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 审批处理数量
     */
    public MeetingEnrollApproveResponse approveWebinarEnrolls(MeetingEnrollApproveRequest request, String applicationName) {
        checkMeetingRequest(request);
        if (request.getAction() == null) {
            throw new ParamCheckException("网络研讨会报名审批操作类型不能为空");
        }
        if (CollectionUtils.isEmpty(request.getEnrollIdList())) {
            throw new ParamCheckException("网络研讨会报名ID列表不能为空");
        }
        return meetingAdvancedClient.approveWebinarEnrolls(request, applicationName);
    }

    /**
     * 导入网络研讨会报名信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 导入后的报名列表
     */
    public MeetingEnrollImportResponse importWebinarEnrolls(MeetingEnrollImportRequest request, String applicationName) {
        checkMeetingRequest(request);
        if (CollectionUtils.isEmpty(request.getEnrollList())) {
            throw new ParamCheckException("网络研讨会导入报名信息列表不能为空");
        }
        return meetingAdvancedClient.importWebinarEnrolls(request, applicationName);
    }

    /**
     * 删除网络研讨会报名信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 删除数量
     */
    public MeetingEnrollDeleteResponse deleteWebinarEnrolls(MeetingEnrollDeleteRequest request, String applicationName) {
        checkMeetingRequest(request);
        if (CollectionUtils.isEmpty(request.getEnrollIdList())) {
            throw new ParamCheckException("网络研讨会待删除报名ID列表不能为空");
        }
        return meetingAdvancedClient.deleteWebinarEnrolls(request, applicationName);
    }

    /**
     * 批量电话入会外呼
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 外呼结果
     */
    public MeetingPhoneCalloutResponse calloutPhones(MeetingPhoneCalloutRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        checkPhoneNumbers(request.getPhoneNumbers(), 50, "待外呼电话号码");
        return meetingAdvancedClient.calloutPhones(request, applicationName);
    }

    /**
     * 获取电话入会外呼状态
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 外呼状态列表
     */
    public MeetingPhoneCalloutStatusResponse getPhoneCalloutStatus(MeetingPhoneCalloutStatusRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (request.getLimit() != null && (request.getLimit() < 1 || request.getLimit() > 100)) {
            throw new ParamCheckException("电话外呼状态分页大小必须在1~100之间");
        }
        return meetingAdvancedClient.getPhoneCalloutStatus(request, applicationName);
    }

    /**
     * 获取电话入会成员临时ID
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 电话号码与临时ID列表
     */
    public MeetingPhoneTmpOpenIdResponse getPhoneTmpOpenIds(MeetingPhoneTmpOpenIdRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        checkPhoneNumbers(request.getPhoneNumbers(), 20, "待查询电话号码");
        return meetingAdvancedClient.getPhoneTmpOpenIds(request, applicationName);
    }

    /**
     * 获取MRA设备状态
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return MRA设备状态
     */
    public MeetingMraStatusResponse getMraStatus(MeetingMraStatusRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (StringUtils.isEmpty(request.getTmpOpenid())) {
            throw new ParamCheckException("被查询MRA成员临时身份ID不能为空");
        }
        return meetingAdvancedClient.queryMraStatus(request, applicationName);
    }

    /**
     * 设置MRA设备默认布局
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setMraDefaultLayout(MeetingMraDefaultLayoutRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        checkMraDevice(request.getMra());
        checkMraEnum(request.getDefaultLayout(), 1, 3, "MRA默认分屏设置");
        checkMraEnum(request.getDefaultNoVideoUser(), 1, 2, "MRA默认非视频与会者显示方式");
        return meetingAdvancedClient.setMraDefaultLayout(request, applicationName);
    }

    /**
     * 设置MRA设备举手状态
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setMraRaiseHand(MeetingMraRaiseHandRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        checkMraDevice(request.getMra());
        if (request.getRaiseHand() == null) {
            throw new ParamCheckException("MRA举手操作不能为空");
        }
        return meetingAdvancedClient.setMraRaiseHand(request, applicationName);
    }

    /**
     * 挂断MRA设备呼叫
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse hangupMra(MeetingMraHangupRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        checkMraDevice(request.getMra());
        return meetingAdvancedClient.hangupMra(request, applicationName);
    }

    /**
     * 获取企业布局模板列表
     *
     * @param applicationName 应用名称
     * @return 布局模板列表
     */
    public MeetingLayoutTemplateListResponse listMeetingLayoutTemplates(String applicationName) {
        return meetingAdvancedClient.listMeetingLayoutTemplates(applicationName);
    }

    /**
     * 添加会议基础布局
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 新增布局列表
     */
    public MeetingLayoutAddResponse addMeetingLayouts(MeetingLayoutAddRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (CollectionUtils.isEmpty(request.getLayoutList()) || request.getLayoutList().size() > 10) {
            throw new ParamCheckException("会议布局列表必须为1~10个");
        }
        if (request.getDefaultLayoutOrder() != null
                && (request.getDefaultLayoutOrder() < 1 || request.getDefaultLayoutOrder() > request.getLayoutList().size())) {
            throw new ParamCheckException("需要应用的布局序号不符合要求");
        }
        for (MeetingLayoutResult layout : request.getLayoutList()) {
            checkMeetingLayoutPages(layout == null ? null : layout.getPageList());
        }
        return meetingAdvancedClient.addMeetingLayouts(request, applicationName);
    }

    /**
     * 添加会议高级布局
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 新增高级布局列表
     */
    public MeetingAdvancedLayoutAddResponse addMeetingAdvancedLayouts(MeetingAdvancedLayoutAddRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (CollectionUtils.isEmpty(request.getLayoutList()) || request.getLayoutList().size() > 20) {
            throw new ParamCheckException("会议高级布局列表必须为1~20个");
        }
        for (MeetingAdvancedLayout layout : request.getLayoutList()) {
            checkMeetingAdvancedLayoutPages(layout == null ? null : layout.getPageList());
        }
        return meetingAdvancedClient.addMeetingAdvancedLayouts(request, applicationName);
    }

    /**
     * 修改会议基础布局
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse updateMeetingLayout(MeetingLayoutUpdateRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (StringUtils.isEmpty(request.getLayoutId())) {
            throw new ParamCheckException("布局ID不能为空");
        }
        checkMeetingLayoutPages(request.getPageList());
        return meetingAdvancedClient.updateMeetingLayout(request, applicationName);
    }

    /**
     * 修改会议高级布局
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse updateMeetingAdvancedLayout(MeetingAdvancedLayoutUpdateRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (StringUtils.isEmpty(request.getLayoutId())) {
            throw new ParamCheckException("布局ID不能为空");
        }
        checkMeetingAdvancedLayoutPages(request.getPageList());
        return meetingAdvancedClient.updateMeetingAdvancedLayout(request, applicationName);
    }

    /**
     * 设置会议默认布局
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setDefaultMeetingLayout(MeetingLayoutDefaultRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (request.getSelectedLayoutId() == null) {
            throw new ParamCheckException("布局ID不能为null，空字符串表示恢复默认原始布局");
        }
        return meetingAdvancedClient.setDefaultMeetingLayout(request, applicationName);
    }

    /**
     * 应用会议高级布局
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse applyMeetingAdvancedLayout(MeetingAdvancedLayoutApplyRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (request.getLayoutId() == null) {
            throw new ParamCheckException("布局ID不能为null，空字符串表示恢复默认布局");
        }
        if (CollectionUtils.isNotEmpty(request.getUserList())) {
            if (request.getUserList().size() > 20) {
                throw new ParamCheckException("个性布局用户列表最多20个");
            }
            for (MeetingLayoutUser user : request.getUserList()) {
                if (user == null || StringUtils.isEmpty(user.getTmpOpenid())) {
                    throw new ParamCheckException("个性布局用户的会议临时ID不能为空");
                }
            }
        }
        return meetingAdvancedClient.applyMeetingAdvancedLayout(request, applicationName);
    }

    /**
     * 获取会议基础和高级布局列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 布局列表
     */
    public MeetingAdvancedLayoutListResponse listMeetingAdvancedLayouts(MeetingAdvancedLayoutListRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        return meetingAdvancedClient.listMeetingAdvancedLayouts(request, applicationName);
    }

    /**
     * 获取用户高级布局
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 用户布局
     */
    public MeetingAdvancedLayoutUserResponse getMeetingUserLayout(MeetingAdvancedLayoutUserRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (StringUtils.isEmpty(request.getTmpOpenid())) {
            throw new ParamCheckException("被查询用户的会议临时ID不能为空");
        }
        checkMeetingInstanceId(request.getInstanceId());
        return meetingAdvancedClient.getMeetingUserLayout(request, applicationName);
    }

    /**
     * 批量删除会议布局
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse batchDeleteMeetingLayouts(MeetingLayoutBatchDeleteRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (CollectionUtils.isEmpty(request.getLayoutIdList()) || request.getLayoutIdList().size() > 20) {
            throw new ParamCheckException("待删除布局ID列表必须为1~20个");
        }
        for (String layoutId : request.getLayoutIdList()) {
            if (StringUtils.isEmpty(layoutId)) {
                throw new ParamCheckException("待删除布局ID不能为空");
            }
        }
        return meetingAdvancedClient.batchDeleteMeetingLayouts(request, applicationName);
    }

    /**
     * 添加会议背景
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 新增背景列表
     */
    public MeetingBackgroundAddResponse addMeetingBackgrounds(MeetingBackgroundAddRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (CollectionUtils.isEmpty(request.getImageList()) || request.getImageList().size() > 7) {
            throw new ParamCheckException("会议背景图片列表必须为1~7个");
        }
        if (request.getDefaultImageOrder() != null
                && (request.getDefaultImageOrder() < 1 || request.getDefaultImageOrder() > request.getImageList().size())) {
            throw new ParamCheckException("需要使用的背景图片序号不符合要求");
        }
        for (MeetingBackgroundImage image : request.getImageList()) {
            if (image == null || StringUtils.isEmpty(image.getImageMd5()) || StringUtils.isEmpty(image.getImageUrl())) {
                throw new ParamCheckException("背景图片MD5和URL不能为空");
            }
        }
        return meetingAdvancedClient.addMeetingBackgrounds(request, applicationName);
    }

    /**
     * 设置会议默认背景
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setDefaultMeetingBackground(MeetingBackgroundDefaultRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (request.getSelectedBackgroundId() == null) {
            throw new ParamCheckException("背景ID不能为null，空字符串表示恢复默认黑色背景");
        }
        return meetingAdvancedClient.setDefaultMeetingBackground(request, applicationName);
    }

    /**
     * 获取会议背景列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 背景列表
     */
    public MeetingBackgroundListResponse listMeetingBackgrounds(MeetingBackgroundListRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        return meetingAdvancedClient.listMeetingBackgrounds(request, applicationName);
    }

    /**
     * 删除单个会议背景
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse deleteMeetingBackground(MeetingBackgroundDeleteRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (StringUtils.isEmpty(request.getBackgroundId())) {
            throw new ParamCheckException("待删除背景ID不能为空");
        }
        return meetingAdvancedClient.deleteMeetingBackground(request, applicationName);
    }

    /**
     * 批量删除会议背景
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse batchDeleteMeetingBackgrounds(MeetingBackgroundBatchDeleteRequest request, String applicationName) {
        checkMeetingRequest((Object) request);
        checkMeetingId(request.getMeetingId());
        if (CollectionUtils.isEmpty(request.getBackgroundIdList()) || request.getBackgroundIdList().size() > 7) {
            throw new ParamCheckException("待删除背景ID列表必须为1~7个");
        }
        for (String backgroundId : request.getBackgroundIdList()) {
            if (StringUtils.isEmpty(backgroundId)) {
                throw new ParamCheckException("待删除背景ID不能为空");
            }
        }
        return meetingAdvancedClient.batchDeleteMeetingBackgrounds(request, applicationName);
    }

    private void checkMraDevice(MeetingMraDevice mra) {
        if (mra == null || StringUtils.isEmpty(mra.getTmpOpenid())) {
            throw new ParamCheckException("MRA设备的会中临时ID不能为空");
        }
    }

    private void checkMraEnum(Integer value, int minValue, int maxValue, String label) {
        if (value == null || value < minValue || value > maxValue) {
            throw new ParamCheckException(label + "不符合要求");
        }
    }

    private void checkMeetingLayoutPages(java.util.List<MeetingLayoutPage> pageList) {
        if (CollectionUtils.isEmpty(pageList)) {
            throw new ParamCheckException("布局单页列表不能为空");
        }
        for (MeetingLayoutPage page : pageList) {
            if (page == null || StringUtils.isEmpty(page.getLayoutTemplateId())) {
                throw new ParamCheckException("布局模板ID不能为空");
            }
            if (CollectionUtils.isEmpty(page.getUserSeatList())) {
                continue;
            }
            for (MeetingLayoutSeat seat : page.getUserSeatList()) {
                if (seat == null || StringUtils.isEmpty(seat.getGridId())) {
                    throw new ParamCheckException("布局座次宫格ID不能为空");
                }
                if (seat.getGridType() == null || seat.getGridType() < 1 || seat.getGridType() > 3) {
                    throw new ParamCheckException("布局宫格类型只允许1~3");
                }
                int identityCount = 0;
                identityCount += StringUtils.isNotEmpty(seat.getUserId()) ? 1 : 0;
                identityCount += StringUtils.isNotEmpty(seat.getTmpOpenid()) ? 1 : 0;
                identityCount += StringUtils.isNotEmpty(seat.getToolSdkId()) ? 1 : 0;
                if (identityCount != 1) {
                    throw new ParamCheckException("布局座次的userid、tmpOpenid和toolSdkid必须三选一");
                }
                if (seat.getGridType() == 1 && StringUtils.isEmpty(seat.getNickName())) {
                    throw new ParamCheckException("视频宫格的展示昵称不能为空");
                }
            }
        }
    }

    private void checkMeetingAdvancedLayoutPages(java.util.List<MeetingAdvancedLayoutPage> pageList) {
        if (CollectionUtils.isEmpty(pageList)) {
            throw new ParamCheckException("高级布局单页列表不能为空");
        }
        for (MeetingAdvancedLayoutPage page : pageList) {
            if (page == null || StringUtils.isEmpty(page.getLayoutTemplateId())) {
                throw new ParamCheckException("高级布局模板ID不能为空");
            }
            if (Boolean.TRUE.equals(page.getEnablePolling())) {
                MeetingLayoutPollingSetting setting = page.getPollingSetting();
                if (setting == null) {
                    throw new ParamCheckException("开启轮询后轮询设置不能为空");
                }
                if (setting.getPollingIntervalUnit() == null
                        || setting.getPollingIntervalUnit() < 1
                        || setting.getPollingIntervalUnit() > 2) {
                    throw new ParamCheckException("轮询间隔时间类型只允许1或2");
                }
                if (setting.getPollingInterval() == null
                        || setting.getPollingInterval() < 1
                        || setting.getPollingInterval() > 999999) {
                    throw new ParamCheckException("轮询间隔时长必须在1~999999之间");
                }
                if (setting.getIgnoreUserNoVideo() == null || setting.getIgnoreUserAbsence() == null) {
                    throw new ParamCheckException("开启轮询后忽略成员设置不能为空");
                }
            }
            if (CollectionUtils.isEmpty(page.getUserSeatList())) {
                throw new ParamCheckException("高级布局用户座次列表不能为空");
            }
            for (MeetingAdvancedLayoutSeat seat : page.getUserSeatList()) {
                if (seat == null || StringUtils.isEmpty(seat.getGridId())) {
                    throw new ParamCheckException("高级布局座次宫格ID不能为空");
                }
                if (seat.getGridType() == null || seat.getGridType() < 1 || seat.getGridType() > 2) {
                    throw new ParamCheckException("高级布局宫格类型只允许1或2");
                }
                if (seat.getVideoType() != null && (seat.getVideoType() < 1 || seat.getVideoType() > 3)) {
                    throw new ParamCheckException("高级布局视频画面来源只允许1~3");
                }
                if (seat.getVideoType() != null && seat.getVideoType() == 3 && CollectionUtils.isEmpty(seat.getUserList())) {
                    throw new ParamCheckException("指定人员画面必须传入用户列表");
                }
                if (CollectionUtils.isNotEmpty(seat.getUserList())) {
                    for (MeetingLayoutGridUser user : seat.getUserList()) {
                        if (user == null || (StringUtils.isEmpty(user.getUserId()) && StringUtils.isEmpty(user.getTmpOpenid()))) {
                            throw new ParamCheckException("高级布局用户的userid和tmpOpenid必填其一");
                        }
                        if (seat.getVideoType() != null && seat.getVideoType() == 3
                                && (StringUtils.isEmpty(user.getTmpOpenid()) || StringUtils.isEmpty(user.getNickName()))) {
                            throw new ParamCheckException("指定人员画面的tmpOpenid和Base64昵称不能为空");
                        }
                    }
                }
            }
        }
    }

    private void checkMeetingInstanceId(Integer instanceId) {
        int[] allowed = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 21, 22, 30, 32, 33};
        if (instanceId == null) {
            throw new ParamCheckException("用户终端设备类型不能为空");
        }
        for (int item : allowed) {
            if (item == instanceId) {
                return;
            }
        }
        throw new ParamCheckException("用户终端设备类型不符合要求");
    }

    private void checkWebinarSetting(MeetingWebinarSettingRequest request) {
        if (StringUtils.isEmpty(request.getTitle())) {
            throw new ParamCheckException("网络研讨会主题不能为空");
        }
        if (StringUtils.length(request.getTitle()) > 255) {
            throw new ParamCheckException("网络研讨会主题不能超过255个字符");
        }
        if (StringUtils.isNotEmpty(request.getSponsor()) && StringUtils.length(request.getSponsor()) > 40) {
            throw new ParamCheckException("网络研讨会主办方名称不能超过40个字符");
        }
        if (StringUtils.isEmpty(request.getStartTime()) || StringUtils.isEmpty(request.getEndTime())) {
            throw new ParamCheckException("网络研讨会开始和结束时间不能为空");
        }
        if (request.getAdmissionType() == null || request.getAdmissionType() < 0 || request.getAdmissionType() > 2) {
            throw new ParamCheckException("网络研讨会观众观看限制类型不符合要求");
        }
        if (request.getAdmissionType() == 2 && (StringUtils.length(request.getPassword()) < 4 || StringUtils.length(request.getPassword()) > 6)) {
            throw new ParamCheckException("密码观看方式必须传入4~6位数字密码");
        }
        if (CollectionUtils.isNotEmpty(request.getHosts())) {
            for (MeetingWebinarHost host : request.getHosts()) {
                if (host == null || StringUtils.isEmpty(host.getUserId())) {
                    throw new ParamCheckException("网络研讨会主持人userid不能为空");
                }
            }
        }
        if (CollectionUtils.isNotEmpty(request.getSensitiveWords())) {
            if (request.getSensitiveWords().size() > 50) {
                throw new ParamCheckException("网络研讨会敏感词最多50个");
            }
            for (String word : request.getSensitiveWords()) {
                if (StringUtils.isEmpty(word) || StringUtils.length(word) > 10) {
                    throw new ParamCheckException("网络研讨会敏感词不能为空且不能超过10个字符");
                }
            }
        }
        if (Boolean.TRUE.equals(request.getPlaybackForAudience())) {
            MeetingWebinarMediaSetting mediaSetting = request.getMediaSetting();
            if (mediaSetting == null || !"cloud".equals(mediaSetting.getAutoRecordType())) {
                throw new ParamCheckException("允许观众观看回放时必须开启云录制");
            }
        }
    }

    private void checkPhoneNumbers(java.util.List<MeetingPhoneNumber> phoneNumbers, int maxSize, String label) {
        if (CollectionUtils.isEmpty(phoneNumbers)) {
            throw new ParamCheckException(label + "列表不能为空");
        }
        if (phoneNumbers.size() > maxSize) {
            throw new ParamCheckException(label + "列表最多" + maxSize + "个");
        }
        for (MeetingPhoneNumber phone : phoneNumbers) {
            if (phone == null || phone.getArea() == null || StringUtils.isEmpty(phone.getPhone())) {
                throw new ParamCheckException(label + "的国家区号和号码不能为空");
            }
        }
    }

    private void checkWebinarQuery(MeetingWebinarQueryRequest request) {
        if (StringUtils.isEmpty(request.getMeetingId()) && StringUtils.isEmpty(request.getMeetingCode())) {
            throw new ParamCheckException("网络研讨会ID和会议号不能同时为空");
        }
    }

    private void checkWebinarGuests(java.util.List<MeetingWebinarGuest> guests) {
        if (CollectionUtils.isEmpty(guests)) {
            throw new ParamCheckException("网络研讨会嘉宾列表不能为空");
        }
        for (MeetingWebinarGuest guest : guests) {
            if (guest == null || guest.getGuestType() == null) {
                throw new ParamCheckException("网络研讨会嘉宾类型不能为空");
            }
            if (guest.getGuestType() == 1 && StringUtils.isEmpty(guest.getUserId())) {
                throw new ParamCheckException("网络研讨会内部嘉宾userid不能为空");
            }
            if (guest.getGuestType() == 2 && (StringUtils.isEmpty(guest.getArea())
                    || StringUtils.isEmpty(guest.getPhoneNumber())
                    || StringUtils.isEmpty(guest.getGuestName())
                    || StringUtils.length(guest.getGuestName()) > 16)) {
                throw new ParamCheckException("网络研讨会外部嘉宾地区、手机号和不超过16位名称必填");
            }
        }
    }
}
