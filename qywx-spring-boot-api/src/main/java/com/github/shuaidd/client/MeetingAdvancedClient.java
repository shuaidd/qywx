package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.meeting.*;
import com.github.shuaidd.resquest.meeting.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 会议高级管理
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@FeignClient(name = "MeetingAdvancedClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface MeetingAdvancedClient extends CommonClient {

    /**
     * 更新会议受邀成员列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/set_invitees", headers = HEAD)
    BaseResponse setInvitees(MeetingInviteesRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建用户专属参会链接
     *
     * @param request 请求
     * @param app     应用名
     * @return 用户专属参会链接
     */
    @PostMapping(value = "meeting/create_customer_short_url", headers = HEAD)
    MeetingCustomerShortUrlResponse createCustomerShortUrl(MeetingCustomerShortUrlRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取用户专属参会链接
     *
     * @param request 请求
     * @param app     应用名
     * @return 用户专属参会链接列表
     */
    @PostMapping(value = "meeting/get_customer_short_url", headers = HEAD)
    MeetingCustomerShortUrlResponse getCustomerShortUrl(MeetingCustomerShortUrlRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取实时会中成员列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 实时会中成员列表
     */
    @PostMapping(value = "meeting/get_realtime_attendee_list", headers = HEAD)
    MeetingAttendeeListResponse getRealtimeAttendees(MeetingAttendeeQueryRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取已参会成员列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 已参会成员列表
     */
    @PostMapping(value = "meeting/get_attendee_list", headers = HEAD)
    MeetingAttendeeListResponse getAttendees(MeetingAttendeeQueryRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取实时等候室成员列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 实时等候室成员列表
     */
    @PostMapping(value = "meeting/waitingroom/get_current_user_list", headers = HEAD)
    MeetingWaitingRoomUserListResponse getCurrentWaitingRoomUsers(MeetingWaitingRoomUserListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取等候室成员记录
     *
     * @param request 请求
     * @param app     应用名
     * @return 等候室成员历史记录
     */
    @PostMapping(value = "meeting/waitingroom/get_user_list", headers = HEAD)
    MeetingWaitingRoomUserListResponse getWaitingRoomUsers(MeetingWaitingRoomUserListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取成员设备是否入会
     *
     * @param request 请求
     * @param app     应用名
     * @return 设备入会检查结果
     */
    @PostMapping(value = "meeting/check_device_in_meeting", headers = HEAD)
    MeetingDeviceInMeetingResponse checkDeviceInMeeting(MeetingDeviceInMeetingRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取会议嘉宾列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 会议嘉宾列表
     */
    @PostMapping(value = "meeting/get_guests", headers = HEAD)
    MeetingGuestListResponse getGuests(MeetingGuestRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新会议嘉宾列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/set_guests", headers = HEAD)
    BaseResponse setGuests(MeetingGuestRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取会议健康度
     *
     * @param request 请求
     * @param app     应用名
     * @return 会议健康度
     */
    @PostMapping(value = "meeting/get_quality", headers = HEAD)
    MeetingQualityResponse getQuality(MeetingQualityRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改会议报名配置
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名配置和问题数量
     */
    @PostMapping(value = "meeting/enroll/set_config", headers = HEAD)
    MeetingEnrollConfigResponse setEnrollConfig(MeetingEnrollConfigRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取会议报名配置
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名配置
     */
    @PostMapping(value = "meeting/enroll/get_config", headers = HEAD)
    MeetingEnrollConfigResponse getEnrollConfig(MeetingGuestRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取会议成员报名 ID
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名 ID 映射列表
     */
    @PostMapping(value = "meeting/enroll/query_by_tmp_openid", headers = HEAD)
    MeetingEnrollIdListResponse queryEnrollIds(MeetingEnrollIdQueryRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取会议报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名记录列表
     */
    @PostMapping(value = "meeting/enroll/list", headers = HEAD)
    MeetingEnrollListResponse listEnrolls(MeetingEnrollListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 审批会议报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 审批处理数量
     */
    @PostMapping(value = "meeting/enroll/approve", headers = HEAD)
    MeetingEnrollApproveResponse approveEnrolls(MeetingEnrollApproveRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 导入会议报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 导入后的报名列表
     */
    @PostMapping(value = "meeting/enroll/import", headers = HEAD)
    MeetingEnrollImportResponse importEnrolls(MeetingEnrollImportRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除会议报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 删除数量
     */
    @PostMapping(value = "meeting/enroll/delete", headers = HEAD)
    MeetingEnrollDeleteResponse deleteEnrolls(MeetingEnrollDeleteRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取会议发起记录
     *
     * @param request 请求
     * @param app     应用名
     * @return 会议发起记录列表
     */
    @PostMapping(value = "meeting/statistics/get_start_list", headers = HEAD)
    MeetingStartListResponse getStartList(MeetingStartListRequest request, @RequestParam(HEAD_KEY) String app);
}
