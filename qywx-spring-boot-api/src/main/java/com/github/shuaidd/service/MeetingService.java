package com.github.shuaidd.service;

import com.github.shuaidd.client.MeetingAdvancedClient;
import com.github.shuaidd.client.MeetingClient;
import com.github.shuaidd.dto.meeting.MeetingPollQuestion;
import com.github.shuaidd.dto.meeting.MeetingRealControlUser;
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

    private void checkMeetingRequest(Object request) {
        if (request == null) {
            throw new ParamCheckException("请求参数不能为空");
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
