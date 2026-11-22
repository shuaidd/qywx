package com.github.shuaidd.service;

import com.github.shuaidd.client.MeetingAdvancedClient;
import com.github.shuaidd.client.MeetingClient;
import com.github.shuaidd.dto.meeting.MeetingPollQuestion;
import com.github.shuaidd.dto.meeting.MeetingRealControlUser;
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
}
