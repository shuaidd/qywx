package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.meeting.*;
import com.github.shuaidd.resquest.meeting.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
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

    /**
     * 设置进行中会议的会控参数
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/realcontrol/set", headers = HEAD)
    BaseResponse setRealControl(MeetingRealControlSettingRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 设置或撤销联席主持人
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/realcontrol/set_cohost", headers = HEAD)
    BaseResponse setCoHost(MeetingCoHostRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 设置或解除成员静音
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/realcontrol/mute_user", headers = HEAD)
    BaseResponse muteUser(MeetingMuteUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 开启或关闭成员视频
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/realcontrol/switch_user_video", headers = HEAD)
    BaseResponse switchUserVideo(MeetingUserVideoRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 停止成员屏幕共享
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/realcontrol/close_screen_share", headers = HEAD)
    BaseResponse closeScreenShare(MeetingCloseScreenShareRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改成员会中昵称
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/realcontrol/set_nicknames", headers = HEAD)
    BaseResponse setNicknames(MeetingNicknameRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 管理等候室成员
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/realcontrol/manage_waiting_room_users", headers = HEAD)
    BaseResponse manageWaitingRoomUsers(MeetingWaitingRoomManageRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 将成员移出会议
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/realcontrol/kickout_users", headers = HEAD)
    BaseResponse kickoutUsers(MeetingKickoutUsersRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 结束进行中的会议
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/realcontrol/dismiss", headers = HEAD)
    BaseResponse dismissMeeting(MeetingDismissRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建会议投票
     *
     * @param request 请求
     * @param app     应用名
     * @return 投票主题 ID
     */
    @PostMapping(value = "meeting/poll/create_theme", headers = HEAD)
    MeetingPollCreateResponse createPoll(MeetingPollCreateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 全量更新会议投票主题
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/poll/update_theme", headers = HEAD)
    BaseResponse updatePoll(MeetingPollUpdateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取会议投票列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 投票列表
     */
    @PostMapping(value = "meeting/poll/get_poll_list", headers = HEAD)
    MeetingPollListResponse getPollList(MeetingPollListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取投票主题详情
     *
     * @param request 请求
     * @param app     应用名
     * @return 投票主题详情
     */
    @PostMapping(value = "meeting/poll/get_theme_info", headers = HEAD)
    MeetingPollThemeInfoResponse getPollThemeInfo(MeetingPollThemeInfoRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取投票详情和结果
     *
     * @param request 请求
     * @param app     应用名
     * @return 投票详情
     */
    @PostMapping(value = "meeting/poll/get_poll_detail", headers = HEAD)
    MeetingPollDetailResponse getPollDetail(MeetingPollDetailRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除投票或投票主题
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/poll/delete", headers = HEAD)
    BaseResponse deletePoll(MeetingPollDeleteRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 发起已有投票主题
     *
     * @param request 请求
     * @param app     应用名
     * @return 投票 ID
     */
    @PostMapping(value = "meeting/poll/start", headers = HEAD)
    MeetingPollStartResponse startPoll(MeetingPollStartRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 结束指定投票
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/poll/finish", headers = HEAD)
    BaseResponse finishPoll(MeetingPollFinishRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建网络研讨会
     *
     * @param request 请求
     * @param app     应用名
     * @return 网络研讨会信息
     */
    @PostMapping(value = "meeting/webinar/create", headers = HEAD)
    MeetingWebinarCreateResponse createWebinar(MeetingWebinarCreateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改网络研讨会
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/webinar/update", headers = HEAD)
    BaseResponse updateWebinar(MeetingWebinarUpdateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 取消网络研讨会
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/webinar/cancel", headers = HEAD)
    BaseResponse cancelWebinar(MeetingWebinarCancelRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取网络研讨会详情
     *
     * @param request 请求
     * @param app     应用名
     * @return 网络研讨会详情
     */
    @PostMapping(value = "meeting/webinar/get", headers = HEAD)
    MeetingWebinarDetailResponse getWebinar(MeetingWebinarQueryRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取网络研讨会嘉宾列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 嘉宾列表
     */
    @PostMapping(value = "meeting/webinar/list_guest", headers = HEAD)
    MeetingWebinarGuestListResponse getWebinarGuests(MeetingWebinarQueryRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新网络研讨会嘉宾列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/webinar/update_guest_list", headers = HEAD)
    BaseResponse updateWebinarGuests(MeetingWebinarGuestRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 设置网络研讨会暖场配置
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/webinar/update_warm_up", headers = HEAD)
    BaseResponse updateWebinarWarmUp(MeetingWebinarWarmUpRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改网络研讨会报名配置
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名配置和问题数量
     */
    @PostMapping(value = "meeting/webinar/enroll/set_config", headers = HEAD)
    MeetingEnrollConfigResponse setWebinarEnrollConfig(MeetingEnrollConfigRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取网络研讨会报名配置
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名配置
     */
    @PostMapping(value = "meeting/webinar/enroll/get_config", headers = HEAD)
    MeetingEnrollConfigResponse getWebinarEnrollConfig(MeetingGuestRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取网络研讨会成员报名 ID
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名 ID 映射列表
     */
    @PostMapping(value = "meeting/webinar/enroll/query_by_tmp_openid", headers = HEAD)
    MeetingEnrollIdListResponse queryWebinarEnrollIds(MeetingEnrollIdQueryRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取网络研讨会报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名记录列表
     */
    @PostMapping(value = "meeting/webinar/enroll/list", headers = HEAD)
    MeetingEnrollListResponse listWebinarEnrolls(MeetingEnrollListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量审批网络研讨会报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 审批处理数量
     */
    @PostMapping(value = "meeting/webinar/enroll/approve", headers = HEAD)
    MeetingEnrollApproveResponse approveWebinarEnrolls(MeetingEnrollApproveRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 导入网络研讨会报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 导入后的报名列表
     */
    @PostMapping(value = "meeting/webinar/enroll/import", headers = HEAD)
    MeetingEnrollImportResponse importWebinarEnrolls(MeetingEnrollImportRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除网络研讨会报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 删除数量
     */
    @PostMapping(value = "meeting/webinar/enroll/delete", headers = HEAD)
    MeetingEnrollDeleteResponse deleteWebinarEnrolls(MeetingEnrollDeleteRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量电话入会外呼
     *
     * @param request 请求
     * @param app     应用名
     * @return 外呼结果
     */
    @PostMapping(value = "meeting/phone/callout", headers = HEAD)
    MeetingPhoneCalloutResponse calloutPhones(MeetingPhoneCalloutRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取电话入会外呼状态
     *
     * @param request 请求
     * @param app     应用名
     * @return 外呼状态列表
     */
    @PostMapping(value = "meeting/phone/get_callout_status", headers = HEAD)
    MeetingPhoneCalloutStatusResponse getPhoneCalloutStatus(MeetingPhoneCalloutStatusRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取电话入会成员临时ID
     *
     * @param request 请求
     * @param app     应用名
     * @return 电话号码与临时ID列表
     */
    @PostMapping(value = "meeting/phone/get_tmp_openid", headers = HEAD)
    MeetingPhoneTmpOpenIdResponse getPhoneTmpOpenIds(MeetingPhoneTmpOpenIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 为会议预定Rooms会议室
     *
     * @param request 请求
     * @param app     应用名
     * @return Rooms会议室列表
     */
    @PostMapping(value = "meeting/rooms/book", headers = HEAD)
    MeetingRoomsBookResponse bookRooms(MeetingRoomsBookRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 释放会议已预定的Rooms会议室
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/rooms/release", headers = HEAD)
    BaseResponse releaseRooms(MeetingRoomsReleaseRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取Rooms会议室列表
     *
     * @param request 请求
     * @param app     应用名
     * @return Rooms会议室列表
     */
    @PostMapping(value = "meeting/rooms/list", headers = HEAD)
    MeetingRoomsListResponse listRooms(MeetingRoomsListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取Rooms会议室详情
     *
     * @param request 请求
     * @param app     应用名
     * @return Rooms会议室详情
     */
    @PostMapping(value = "meeting/rooms/get_info", headers = HEAD)
    MeetingRoomsInfoResponse getRoomsInfo(MeetingRoomsInfoRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取Rooms会议室配置
     *
     * @param request 请求
     * @param app     应用名
     * @return Rooms会议室配置
     */
    @PostMapping(value = "meeting/rooms/get_config", headers = HEAD)
    MeetingRoomsConfigResponse getRoomsConfig(MeetingRoomsConfigRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取Rooms会议室下的会议列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 会议列表
     */
    @PostMapping(value = "meeting/rooms/list_meetings", headers = HEAD)
    MeetingRoomsMeetingListResponse listRoomsMeetings(MeetingRoomsMeetingListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取Rooms设备列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 设备列表
     */
    @PostMapping(value = "meeting/rooms/list_devices", headers = HEAD)
    MeetingRoomsDeviceListResponse listRoomsDevices(MeetingRoomsDeviceListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取Rooms控制器列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 控制器列表
     */
    @PostMapping(value = "meeting/rooms/list_controllers", headers = HEAD)
    MeetingRoomsControllerListResponse listRoomsControllers(MeetingRoomsControllerListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取企业Rooms会议室资源
     *
     * @param request 请求
     * @param app     应用名
     * @return Rooms资源统计
     */
    @PostMapping(value = "meeting/rooms/get_inventory", headers = HEAD)
    MeetingRoomsInventoryResponse getRoomsInventory(MeetingRoomsInventoryRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 呼叫Rooms会议室入会
     *
     * @param request 请求
     * @param app     应用名
     * @return 呼叫ID
     */
    @PostMapping(value = "meeting/rooms/call", headers = HEAD)
    MeetingRoomsCallResponse callRooms(MeetingRoomsCallRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 取消呼叫Rooms会议室
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/rooms/cancel_call", headers = HEAD)
    BaseResponse cancelRoomsCall(MeetingRoomsCancelCallRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取Rooms会议室呼叫应答状态
     *
     * @param request 请求
     * @param app     应用名
     * @return 应答状态
     */
    @PostMapping(value = "meeting/rooms/get_response_status", headers = HEAD)
    MeetingRoomsResponseStatusResponse getRoomsCallResponseStatus(MeetingRoomsResponseStatusRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取MRA设备状态
     *
     * @param request 请求
     * @param app     应用名
     * @return MRA设备状态
     */
    @PostMapping(value = "meeting/mra/query_status", headers = HEAD)
    MeetingMraStatusResponse queryMraStatus(MeetingMraStatusRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 设置MRA设备默认布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/mra/set_default_layout", headers = HEAD)
    BaseResponse setMraDefaultLayout(MeetingMraDefaultLayoutRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 设置MRA设备举手状态
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/mra/set_raise_hand", headers = HEAD)
    BaseResponse setMraRaiseHand(MeetingMraRaiseHandRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 挂断MRA设备呼叫
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/mra/hangup", headers = HEAD)
    BaseResponse hangupMra(MeetingMraHangupRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取企业布局模板列表
     *
     * @param app 应用名
     * @return 布局模板列表
     */
    @GetMapping(value = "meeting/layout/list_template", headers = HEAD)
    MeetingLayoutTemplateListResponse listMeetingLayoutTemplates(@RequestParam(HEAD_KEY) String app);

    /**
     * 添加会议基础布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 新增布局列表
     */
    @PostMapping(value = "meeting/layout/add", headers = HEAD)
    MeetingLayoutAddResponse addMeetingLayouts(MeetingLayoutAddRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 添加会议高级布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 新增高级布局列表
     */
    @PostMapping(value = "meeting/advanced_layout/add", headers = HEAD)
    MeetingAdvancedLayoutAddResponse addMeetingAdvancedLayouts(MeetingAdvancedLayoutAddRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改会议基础布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/layout/update", headers = HEAD)
    BaseResponse updateMeetingLayout(MeetingLayoutUpdateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改会议高级布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/advanced_layout/update", headers = HEAD)
    BaseResponse updateMeetingAdvancedLayout(MeetingAdvancedLayoutUpdateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 设置会议默认布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/layout/set_default", headers = HEAD)
    BaseResponse setDefaultMeetingLayout(MeetingLayoutDefaultRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 应用会议高级布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/advanced_layout/apply", headers = HEAD)
    BaseResponse applyMeetingAdvancedLayout(MeetingAdvancedLayoutApplyRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取会议基础和高级布局列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 布局列表
     */
    @PostMapping(value = "meeting/advanced_layout/list", headers = HEAD)
    MeetingAdvancedLayoutListResponse listMeetingAdvancedLayouts(MeetingAdvancedLayoutListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取用户高级布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 用户布局
     */
    @PostMapping(value = "meeting/advanced_layout/get_user_layout", headers = HEAD)
    MeetingAdvancedLayoutUserResponse getMeetingUserLayout(MeetingAdvancedLayoutUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量删除会议布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/advanced_layout/batch_delete", headers = HEAD)
    BaseResponse batchDeleteMeetingLayouts(MeetingLayoutBatchDeleteRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 添加会议背景
     *
     * @param request 请求
     * @param app     应用名
     * @return 新增背景列表
     */
    @PostMapping(value = "meeting/layout/add_background", headers = HEAD)
    MeetingBackgroundAddResponse addMeetingBackgrounds(MeetingBackgroundAddRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 设置会议默认背景
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/layout/set_default_background", headers = HEAD)
    BaseResponse setDefaultMeetingBackground(MeetingBackgroundDefaultRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取会议背景列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 背景列表
     */
    @PostMapping(value = "meeting/layout/list_background", headers = HEAD)
    MeetingBackgroundListResponse listMeetingBackgrounds(MeetingBackgroundListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除单个会议背景
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/layout/delete_background", headers = HEAD)
    BaseResponse deleteMeetingBackground(MeetingBackgroundDeleteRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量删除会议背景
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "meeting/layout/batch_delete_background", headers = HEAD)
    BaseResponse batchDeleteMeetingBackgrounds(MeetingBackgroundBatchDeleteRequest request, @RequestParam(HEAD_KEY) String app);
}
