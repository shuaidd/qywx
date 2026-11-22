package com.github.shuaidd.service;

import com.github.shuaidd.client.MeetingAdvancedClient;
import com.github.shuaidd.client.MeetingClient;
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
}
