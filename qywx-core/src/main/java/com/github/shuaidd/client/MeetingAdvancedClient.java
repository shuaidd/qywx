package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.meeting.MeetingAdvancedLayoutAddResponse;
import com.github.shuaidd.response.meeting.MeetingAdvancedLayoutListResponse;
import com.github.shuaidd.response.meeting.MeetingAdvancedLayoutUserResponse;
import com.github.shuaidd.response.meeting.MeetingAttendeeListResponse;
import com.github.shuaidd.response.meeting.MeetingBackgroundAddResponse;
import com.github.shuaidd.response.meeting.MeetingBackgroundListResponse;
import com.github.shuaidd.response.meeting.MeetingCustomerShortUrlResponse;
import com.github.shuaidd.response.meeting.MeetingDeviceInMeetingResponse;
import com.github.shuaidd.response.meeting.MeetingEnrollApproveResponse;
import com.github.shuaidd.response.meeting.MeetingEnrollConfigResponse;
import com.github.shuaidd.response.meeting.MeetingEnrollDeleteResponse;
import com.github.shuaidd.response.meeting.MeetingEnrollIdListResponse;
import com.github.shuaidd.response.meeting.MeetingEnrollImportResponse;
import com.github.shuaidd.response.meeting.MeetingEnrollListResponse;
import com.github.shuaidd.response.meeting.MeetingGuestListResponse;
import com.github.shuaidd.response.meeting.MeetingLayoutAddResponse;
import com.github.shuaidd.response.meeting.MeetingLayoutTemplateListResponse;
import com.github.shuaidd.response.meeting.MeetingMraStatusResponse;
import com.github.shuaidd.response.meeting.MeetingPhoneCalloutResponse;
import com.github.shuaidd.response.meeting.MeetingPhoneCalloutStatusResponse;
import com.github.shuaidd.response.meeting.MeetingPhoneTmpOpenIdResponse;
import com.github.shuaidd.response.meeting.MeetingPollCreateResponse;
import com.github.shuaidd.response.meeting.MeetingPollDetailResponse;
import com.github.shuaidd.response.meeting.MeetingPollListResponse;
import com.github.shuaidd.response.meeting.MeetingPollStartResponse;
import com.github.shuaidd.response.meeting.MeetingPollThemeInfoResponse;
import com.github.shuaidd.response.meeting.MeetingQualityResponse;
import com.github.shuaidd.response.meeting.MeetingRecordFileDetailResponse;
import com.github.shuaidd.response.meeting.MeetingRecordFileListResponse;
import com.github.shuaidd.response.meeting.MeetingRecordListResponse;
import com.github.shuaidd.response.meeting.MeetingRecordStatisticsResponse;
import com.github.shuaidd.response.meeting.MeetingRoomsBookResponse;
import com.github.shuaidd.response.meeting.MeetingRoomsCallResponse;
import com.github.shuaidd.response.meeting.MeetingRoomsConfigResponse;
import com.github.shuaidd.response.meeting.MeetingRoomsControllerListResponse;
import com.github.shuaidd.response.meeting.MeetingRoomsDeviceListResponse;
import com.github.shuaidd.response.meeting.MeetingRoomsInfoResponse;
import com.github.shuaidd.response.meeting.MeetingRoomsInventoryResponse;
import com.github.shuaidd.response.meeting.MeetingRoomsListResponse;
import com.github.shuaidd.response.meeting.MeetingRoomsMeetingListResponse;
import com.github.shuaidd.response.meeting.MeetingRoomsResponseStatusResponse;
import com.github.shuaidd.response.meeting.MeetingStartListResponse;
import com.github.shuaidd.response.meeting.MeetingTranscriptDetailResponse;
import com.github.shuaidd.response.meeting.MeetingTranscriptParagraphListResponse;
import com.github.shuaidd.response.meeting.MeetingTranscriptSearchResponse;
import com.github.shuaidd.response.meeting.MeetingVipJobResultResponse;
import com.github.shuaidd.response.meeting.MeetingVipJobSubmitResponse;
import com.github.shuaidd.response.meeting.MeetingVipListResponse;
import com.github.shuaidd.response.meeting.MeetingWaitingRoomUserListResponse;
import com.github.shuaidd.response.meeting.MeetingWebinarCreateResponse;
import com.github.shuaidd.response.meeting.MeetingWebinarDetailResponse;
import com.github.shuaidd.response.meeting.MeetingWebinarGuestListResponse;
import com.github.shuaidd.resquest.meeting.MeetingAdvancedLayoutAddRequest;
import com.github.shuaidd.resquest.meeting.MeetingAdvancedLayoutApplyRequest;
import com.github.shuaidd.resquest.meeting.MeetingAdvancedLayoutListRequest;
import com.github.shuaidd.resquest.meeting.MeetingAdvancedLayoutUpdateRequest;
import com.github.shuaidd.resquest.meeting.MeetingAdvancedLayoutUserRequest;
import com.github.shuaidd.resquest.meeting.MeetingAttendeeQueryRequest;
import com.github.shuaidd.resquest.meeting.MeetingBackgroundAddRequest;
import com.github.shuaidd.resquest.meeting.MeetingBackgroundBatchDeleteRequest;
import com.github.shuaidd.resquest.meeting.MeetingBackgroundDefaultRequest;
import com.github.shuaidd.resquest.meeting.MeetingBackgroundDeleteRequest;
import com.github.shuaidd.resquest.meeting.MeetingBackgroundListRequest;
import com.github.shuaidd.resquest.meeting.MeetingCloseScreenShareRequest;
import com.github.shuaidd.resquest.meeting.MeetingCoHostRequest;
import com.github.shuaidd.resquest.meeting.MeetingCustomerShortUrlRequest;
import com.github.shuaidd.resquest.meeting.MeetingDeviceInMeetingRequest;
import com.github.shuaidd.resquest.meeting.MeetingDismissRequest;
import com.github.shuaidd.resquest.meeting.MeetingEnrollApproveRequest;
import com.github.shuaidd.resquest.meeting.MeetingEnrollConfigRequest;
import com.github.shuaidd.resquest.meeting.MeetingEnrollDeleteRequest;
import com.github.shuaidd.resquest.meeting.MeetingEnrollIdQueryRequest;
import com.github.shuaidd.resquest.meeting.MeetingEnrollImportRequest;
import com.github.shuaidd.resquest.meeting.MeetingEnrollListRequest;
import com.github.shuaidd.resquest.meeting.MeetingGuestRequest;
import com.github.shuaidd.resquest.meeting.MeetingInviteesRequest;
import com.github.shuaidd.resquest.meeting.MeetingKickoutUsersRequest;
import com.github.shuaidd.resquest.meeting.MeetingLayoutAddRequest;
import com.github.shuaidd.resquest.meeting.MeetingLayoutBatchDeleteRequest;
import com.github.shuaidd.resquest.meeting.MeetingLayoutDefaultRequest;
import com.github.shuaidd.resquest.meeting.MeetingLayoutUpdateRequest;
import com.github.shuaidd.resquest.meeting.MeetingMraDefaultLayoutRequest;
import com.github.shuaidd.resquest.meeting.MeetingMraHangupRequest;
import com.github.shuaidd.resquest.meeting.MeetingMraRaiseHandRequest;
import com.github.shuaidd.resquest.meeting.MeetingMraStatusRequest;
import com.github.shuaidd.resquest.meeting.MeetingMuteUserRequest;
import com.github.shuaidd.resquest.meeting.MeetingNicknameRequest;
import com.github.shuaidd.resquest.meeting.MeetingPhoneCalloutRequest;
import com.github.shuaidd.resquest.meeting.MeetingPhoneCalloutStatusRequest;
import com.github.shuaidd.resquest.meeting.MeetingPhoneTmpOpenIdRequest;
import com.github.shuaidd.resquest.meeting.MeetingPollCreateRequest;
import com.github.shuaidd.resquest.meeting.MeetingPollDeleteRequest;
import com.github.shuaidd.resquest.meeting.MeetingPollDetailRequest;
import com.github.shuaidd.resquest.meeting.MeetingPollFinishRequest;
import com.github.shuaidd.resquest.meeting.MeetingPollListRequest;
import com.github.shuaidd.resquest.meeting.MeetingPollStartRequest;
import com.github.shuaidd.resquest.meeting.MeetingPollThemeInfoRequest;
import com.github.shuaidd.resquest.meeting.MeetingPollUpdateRequest;
import com.github.shuaidd.resquest.meeting.MeetingQualityRequest;
import com.github.shuaidd.resquest.meeting.MeetingRealControlSettingRequest;
import com.github.shuaidd.resquest.meeting.MeetingRecordFileRequest;
import com.github.shuaidd.resquest.meeting.MeetingRecordListRequest;
import com.github.shuaidd.resquest.meeting.MeetingRecordRequest;
import com.github.shuaidd.resquest.meeting.MeetingRecordSharingUpdateRequest;
import com.github.shuaidd.resquest.meeting.MeetingRecordStatisticsRequest;
import com.github.shuaidd.resquest.meeting.MeetingRoomsBookRequest;
import com.github.shuaidd.resquest.meeting.MeetingRoomsCallRequest;
import com.github.shuaidd.resquest.meeting.MeetingRoomsCancelCallRequest;
import com.github.shuaidd.resquest.meeting.MeetingRoomsConfigRequest;
import com.github.shuaidd.resquest.meeting.MeetingRoomsControllerListRequest;
import com.github.shuaidd.resquest.meeting.MeetingRoomsDeviceListRequest;
import com.github.shuaidd.resquest.meeting.MeetingRoomsInfoRequest;
import com.github.shuaidd.resquest.meeting.MeetingRoomsInventoryRequest;
import com.github.shuaidd.resquest.meeting.MeetingRoomsListRequest;
import com.github.shuaidd.resquest.meeting.MeetingRoomsMeetingListRequest;
import com.github.shuaidd.resquest.meeting.MeetingRoomsReleaseRequest;
import com.github.shuaidd.resquest.meeting.MeetingRoomsResponseStatusRequest;
import com.github.shuaidd.resquest.meeting.MeetingStartListRequest;
import com.github.shuaidd.resquest.meeting.MeetingTranscriptDetailRequest;
import com.github.shuaidd.resquest.meeting.MeetingTranscriptSearchRequest;
import com.github.shuaidd.resquest.meeting.MeetingUserVideoRequest;
import com.github.shuaidd.resquest.meeting.MeetingVipJobResultRequest;
import com.github.shuaidd.resquest.meeting.MeetingVipListRequest;
import com.github.shuaidd.resquest.meeting.MeetingVipUserBatchRequest;
import com.github.shuaidd.resquest.meeting.MeetingWaitingRoomManageRequest;
import com.github.shuaidd.resquest.meeting.MeetingWaitingRoomUserListRequest;
import com.github.shuaidd.resquest.meeting.MeetingWebinarCancelRequest;
import com.github.shuaidd.resquest.meeting.MeetingWebinarCreateRequest;
import com.github.shuaidd.resquest.meeting.MeetingWebinarGuestRequest;
import com.github.shuaidd.resquest.meeting.MeetingWebinarQueryRequest;
import com.github.shuaidd.resquest.meeting.MeetingWebinarUpdateRequest;
import com.github.shuaidd.resquest.meeting.MeetingWebinarWarmUpRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 会议高级管理
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public interface MeetingAdvancedClient extends CommonClient {

    /**
     * 更新会议受邀成员列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/set_invitees?app={app}")
    @Headers(HEAD)
    BaseResponse setInvitees(MeetingInviteesRequest request, @Param("app") String app);

    /**
     * 创建用户专属参会链接
     *
     * @param request 请求
     * @param app     应用名
     * @return 用户专属参会链接
     */
    @RequestLine("POST /meeting/create_customer_short_url?app={app}")
    @Headers(HEAD)
    MeetingCustomerShortUrlResponse createCustomerShortUrl(MeetingCustomerShortUrlRequest request, @Param("app") String app);

    /**
     * 获取用户专属参会链接
     *
     * @param request 请求
     * @param app     应用名
     * @return 用户专属参会链接列表
     */
    @RequestLine("POST /meeting/get_customer_short_url?app={app}")
    @Headers(HEAD)
    MeetingCustomerShortUrlResponse getCustomerShortUrl(MeetingCustomerShortUrlRequest request, @Param("app") String app);

    /**
     * 获取实时会中成员列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 实时会中成员列表
     */
    @RequestLine("POST /meeting/get_realtime_attendee_list?app={app}")
    @Headers(HEAD)
    MeetingAttendeeListResponse getRealtimeAttendees(MeetingAttendeeQueryRequest request, @Param("app") String app);

    /**
     * 获取已参会成员列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 已参会成员列表
     */
    @RequestLine("POST /meeting/get_attendee_list?app={app}")
    @Headers(HEAD)
    MeetingAttendeeListResponse getAttendees(MeetingAttendeeQueryRequest request, @Param("app") String app);

    /**
     * 获取实时等候室成员列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 实时等候室成员列表
     */
    @RequestLine("POST /meeting/waitingroom/get_current_user_list?app={app}")
    @Headers(HEAD)
    MeetingWaitingRoomUserListResponse getCurrentWaitingRoomUsers(MeetingWaitingRoomUserListRequest request, @Param("app") String app);

    /**
     * 获取等候室成员记录
     *
     * @param request 请求
     * @param app     应用名
     * @return 等候室成员历史记录
     */
    @RequestLine("POST /meeting/waitingroom/get_user_list?app={app}")
    @Headers(HEAD)
    MeetingWaitingRoomUserListResponse getWaitingRoomUsers(MeetingWaitingRoomUserListRequest request, @Param("app") String app);

    /**
     * 获取成员设备是否入会
     *
     * @param request 请求
     * @param app     应用名
     * @return 设备入会检查结果
     */
    @RequestLine("POST /meeting/check_device_in_meeting?app={app}")
    @Headers(HEAD)
    MeetingDeviceInMeetingResponse checkDeviceInMeeting(MeetingDeviceInMeetingRequest request, @Param("app") String app);

    /**
     * 获取会议嘉宾列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 会议嘉宾列表
     */
    @RequestLine("POST /meeting/get_guests?app={app}")
    @Headers(HEAD)
    MeetingGuestListResponse getGuests(MeetingGuestRequest request, @Param("app") String app);

    /**
     * 更新会议嘉宾列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/set_guests?app={app}")
    @Headers(HEAD)
    BaseResponse setGuests(MeetingGuestRequest request, @Param("app") String app);

    /**
     * 获取会议健康度
     *
     * @param request 请求
     * @param app     应用名
     * @return 会议健康度
     */
    @RequestLine("POST /meeting/get_quality?app={app}")
    @Headers(HEAD)
    MeetingQualityResponse getQuality(MeetingQualityRequest request, @Param("app") String app);

    /**
     * 修改会议报名配置
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名配置和问题数量
     */
    @RequestLine("POST /meeting/enroll/set_config?app={app}")
    @Headers(HEAD)
    MeetingEnrollConfigResponse setEnrollConfig(MeetingEnrollConfigRequest request, @Param("app") String app);

    /**
     * 获取会议报名配置
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名配置
     */
    @RequestLine("POST /meeting/enroll/get_config?app={app}")
    @Headers(HEAD)
    MeetingEnrollConfigResponse getEnrollConfig(MeetingGuestRequest request, @Param("app") String app);

    /**
     * 获取会议成员报名 ID
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名 ID 映射列表
     */
    @RequestLine("POST /meeting/enroll/query_by_tmp_openid?app={app}")
    @Headers(HEAD)
    MeetingEnrollIdListResponse queryEnrollIds(MeetingEnrollIdQueryRequest request, @Param("app") String app);

    /**
     * 获取会议报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名记录列表
     */
    @RequestLine("POST /meeting/enroll/list?app={app}")
    @Headers(HEAD)
    MeetingEnrollListResponse listEnrolls(MeetingEnrollListRequest request, @Param("app") String app);

    /**
     * 审批会议报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 审批处理数量
     */
    @RequestLine("POST /meeting/enroll/approve?app={app}")
    @Headers(HEAD)
    MeetingEnrollApproveResponse approveEnrolls(MeetingEnrollApproveRequest request, @Param("app") String app);

    /**
     * 导入会议报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 导入后的报名列表
     */
    @RequestLine("POST /meeting/enroll/import?app={app}")
    @Headers(HEAD)
    MeetingEnrollImportResponse importEnrolls(MeetingEnrollImportRequest request, @Param("app") String app);

    /**
     * 删除会议报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 删除数量
     */
    @RequestLine("POST /meeting/enroll/delete?app={app}")
    @Headers(HEAD)
    MeetingEnrollDeleteResponse deleteEnrolls(MeetingEnrollDeleteRequest request, @Param("app") String app);

    /**
     * 获取会议发起记录
     *
     * @param request 请求
     * @param app     应用名
     * @return 会议发起记录列表
     */
    @RequestLine("POST /meeting/statistics/get_start_list?app={app}")
    @Headers(HEAD)
    MeetingStartListResponse getStartList(MeetingStartListRequest request, @Param("app") String app);

    /**
     * 设置进行中会议的会控参数
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/realcontrol/set?app={app}")
    @Headers(HEAD)
    BaseResponse setRealControl(MeetingRealControlSettingRequest request, @Param("app") String app);

    /**
     * 设置或撤销联席主持人
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/realcontrol/set_cohost?app={app}")
    @Headers(HEAD)
    BaseResponse setCoHost(MeetingCoHostRequest request, @Param("app") String app);

    /**
     * 设置或解除成员静音
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/realcontrol/mute_user?app={app}")
    @Headers(HEAD)
    BaseResponse muteUser(MeetingMuteUserRequest request, @Param("app") String app);

    /**
     * 开启或关闭成员视频
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/realcontrol/switch_user_video?app={app}")
    @Headers(HEAD)
    BaseResponse switchUserVideo(MeetingUserVideoRequest request, @Param("app") String app);

    /**
     * 停止成员屏幕共享
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/realcontrol/close_screen_share?app={app}")
    @Headers(HEAD)
    BaseResponse closeScreenShare(MeetingCloseScreenShareRequest request, @Param("app") String app);

    /**
     * 修改成员会中昵称
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/realcontrol/set_nicknames?app={app}")
    @Headers(HEAD)
    BaseResponse setNicknames(MeetingNicknameRequest request, @Param("app") String app);

    /**
     * 管理等候室成员
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/realcontrol/manage_waiting_room_users?app={app}")
    @Headers(HEAD)
    BaseResponse manageWaitingRoomUsers(MeetingWaitingRoomManageRequest request, @Param("app") String app);

    /**
     * 将成员移出会议
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/realcontrol/kickout_users?app={app}")
    @Headers(HEAD)
    BaseResponse kickoutUsers(MeetingKickoutUsersRequest request, @Param("app") String app);

    /**
     * 结束进行中的会议
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/realcontrol/dismiss?app={app}")
    @Headers(HEAD)
    BaseResponse dismissMeeting(MeetingDismissRequest request, @Param("app") String app);

    /**
     * 创建会议投票
     *
     * @param request 请求
     * @param app     应用名
     * @return 投票主题 ID
     */
    @RequestLine("POST /meeting/poll/create_theme?app={app}")
    @Headers(HEAD)
    MeetingPollCreateResponse createPoll(MeetingPollCreateRequest request, @Param("app") String app);

    /**
     * 全量更新会议投票主题
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/poll/update_theme?app={app}")
    @Headers(HEAD)
    BaseResponse updatePoll(MeetingPollUpdateRequest request, @Param("app") String app);

    /**
     * 获取会议投票列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 投票列表
     */
    @RequestLine("POST /meeting/poll/get_poll_list?app={app}")
    @Headers(HEAD)
    MeetingPollListResponse getPollList(MeetingPollListRequest request, @Param("app") String app);

    /**
     * 获取投票主题详情
     *
     * @param request 请求
     * @param app     应用名
     * @return 投票主题详情
     */
    @RequestLine("POST /meeting/poll/get_theme_info?app={app}")
    @Headers(HEAD)
    MeetingPollThemeInfoResponse getPollThemeInfo(MeetingPollThemeInfoRequest request, @Param("app") String app);

    /**
     * 获取投票详情和结果
     *
     * @param request 请求
     * @param app     应用名
     * @return 投票详情
     */
    @RequestLine("POST /meeting/poll/get_poll_detail?app={app}")
    @Headers(HEAD)
    MeetingPollDetailResponse getPollDetail(MeetingPollDetailRequest request, @Param("app") String app);

    /**
     * 删除投票或投票主题
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/poll/delete?app={app}")
    @Headers(HEAD)
    BaseResponse deletePoll(MeetingPollDeleteRequest request, @Param("app") String app);

    /**
     * 发起已有投票主题
     *
     * @param request 请求
     * @param app     应用名
     * @return 投票 ID
     */
    @RequestLine("POST /meeting/poll/start?app={app}")
    @Headers(HEAD)
    MeetingPollStartResponse startPoll(MeetingPollStartRequest request, @Param("app") String app);

    /**
     * 结束指定投票
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/poll/finish?app={app}")
    @Headers(HEAD)
    BaseResponse finishPoll(MeetingPollFinishRequest request, @Param("app") String app);

    /**
     * 创建网络研讨会
     *
     * @param request 请求
     * @param app     应用名
     * @return 网络研讨会信息
     */
    @RequestLine("POST /meeting/webinar/create?app={app}")
    @Headers(HEAD)
    MeetingWebinarCreateResponse createWebinar(MeetingWebinarCreateRequest request, @Param("app") String app);

    /**
     * 修改网络研讨会
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/webinar/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateWebinar(MeetingWebinarUpdateRequest request, @Param("app") String app);

    /**
     * 取消网络研讨会
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/webinar/cancel?app={app}")
    @Headers(HEAD)
    BaseResponse cancelWebinar(MeetingWebinarCancelRequest request, @Param("app") String app);

    /**
     * 获取网络研讨会详情
     *
     * @param request 请求
     * @param app     应用名
     * @return 网络研讨会详情
     */
    @RequestLine("POST /meeting/webinar/get?app={app}")
    @Headers(HEAD)
    MeetingWebinarDetailResponse getWebinar(MeetingWebinarQueryRequest request, @Param("app") String app);

    /**
     * 获取网络研讨会嘉宾列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 嘉宾列表
     */
    @RequestLine("POST /meeting/webinar/list_guest?app={app}")
    @Headers(HEAD)
    MeetingWebinarGuestListResponse getWebinarGuests(MeetingWebinarQueryRequest request, @Param("app") String app);

    /**
     * 更新网络研讨会嘉宾列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/webinar/update_guest_list?app={app}")
    @Headers(HEAD)
    BaseResponse updateWebinarGuests(MeetingWebinarGuestRequest request, @Param("app") String app);

    /**
     * 设置网络研讨会暖场配置
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/webinar/update_warm_up?app={app}")
    @Headers(HEAD)
    BaseResponse updateWebinarWarmUp(MeetingWebinarWarmUpRequest request, @Param("app") String app);

    /**
     * 修改网络研讨会报名配置
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名配置和问题数量
     */
    @RequestLine("POST /meeting/webinar/enroll/set_config?app={app}")
    @Headers(HEAD)
    MeetingEnrollConfigResponse setWebinarEnrollConfig(MeetingEnrollConfigRequest request, @Param("app") String app);

    /**
     * 获取网络研讨会报名配置
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名配置
     */
    @RequestLine("POST /meeting/webinar/enroll/get_config?app={app}")
    @Headers(HEAD)
    MeetingEnrollConfigResponse getWebinarEnrollConfig(MeetingGuestRequest request, @Param("app") String app);

    /**
     * 获取网络研讨会成员报名 ID
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名 ID 映射列表
     */
    @RequestLine("POST /meeting/webinar/enroll/query_by_tmp_openid?app={app}")
    @Headers(HEAD)
    MeetingEnrollIdListResponse queryWebinarEnrollIds(MeetingEnrollIdQueryRequest request, @Param("app") String app);

    /**
     * 获取网络研讨会报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 报名记录列表
     */
    @RequestLine("POST /meeting/webinar/enroll/list?app={app}")
    @Headers(HEAD)
    MeetingEnrollListResponse listWebinarEnrolls(MeetingEnrollListRequest request, @Param("app") String app);

    /**
     * 批量审批网络研讨会报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 审批处理数量
     */
    @RequestLine("POST /meeting/webinar/enroll/approve?app={app}")
    @Headers(HEAD)
    MeetingEnrollApproveResponse approveWebinarEnrolls(MeetingEnrollApproveRequest request, @Param("app") String app);

    /**
     * 导入网络研讨会报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 导入后的报名列表
     */
    @RequestLine("POST /meeting/webinar/enroll/import?app={app}")
    @Headers(HEAD)
    MeetingEnrollImportResponse importWebinarEnrolls(MeetingEnrollImportRequest request, @Param("app") String app);

    /**
     * 删除网络研讨会报名信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 删除数量
     */
    @RequestLine("POST /meeting/webinar/enroll/delete?app={app}")
    @Headers(HEAD)
    MeetingEnrollDeleteResponse deleteWebinarEnrolls(MeetingEnrollDeleteRequest request, @Param("app") String app);

    /**
     * 批量电话入会外呼
     *
     * @param request 请求
     * @param app     应用名
     * @return 外呼结果
     */
    @RequestLine("POST /meeting/phone/callout?app={app}")
    @Headers(HEAD)
    MeetingPhoneCalloutResponse calloutPhones(MeetingPhoneCalloutRequest request, @Param("app") String app);

    /**
     * 获取电话入会外呼状态
     *
     * @param request 请求
     * @param app     应用名
     * @return 外呼状态列表
     */
    @RequestLine("POST /meeting/phone/get_callout_status?app={app}")
    @Headers(HEAD)
    MeetingPhoneCalloutStatusResponse getPhoneCalloutStatus(MeetingPhoneCalloutStatusRequest request, @Param("app") String app);

    /**
     * 获取电话入会成员临时ID
     *
     * @param request 请求
     * @param app     应用名
     * @return 电话号码与临时ID列表
     */
    @RequestLine("POST /meeting/phone/get_tmp_openid?app={app}")
    @Headers(HEAD)
    MeetingPhoneTmpOpenIdResponse getPhoneTmpOpenIds(MeetingPhoneTmpOpenIdRequest request, @Param("app") String app);

    /**
     * 为会议预定Rooms会议室
     *
     * @param request 请求
     * @param app     应用名
     * @return Rooms会议室列表
     */
    @RequestLine("POST /meeting/rooms/book?app={app}")
    @Headers(HEAD)
    MeetingRoomsBookResponse bookRooms(MeetingRoomsBookRequest request, @Param("app") String app);

    /**
     * 释放会议已预定的Rooms会议室
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/rooms/release?app={app}")
    @Headers(HEAD)
    BaseResponse releaseRooms(MeetingRoomsReleaseRequest request, @Param("app") String app);

    /**
     * 获取Rooms会议室列表
     *
     * @param request 请求
     * @param app     应用名
     * @return Rooms会议室列表
     */
    @RequestLine("POST /meeting/rooms/list?app={app}")
    @Headers(HEAD)
    MeetingRoomsListResponse listRooms(MeetingRoomsListRequest request, @Param("app") String app);

    /**
     * 获取Rooms会议室详情
     *
     * @param request 请求
     * @param app     应用名
     * @return Rooms会议室详情
     */
    @RequestLine("POST /meeting/rooms/get_info?app={app}")
    @Headers(HEAD)
    MeetingRoomsInfoResponse getRoomsInfo(MeetingRoomsInfoRequest request, @Param("app") String app);

    /**
     * 获取Rooms会议室配置
     *
     * @param request 请求
     * @param app     应用名
     * @return Rooms会议室配置
     */
    @RequestLine("POST /meeting/rooms/get_config?app={app}")
    @Headers(HEAD)
    MeetingRoomsConfigResponse getRoomsConfig(MeetingRoomsConfigRequest request, @Param("app") String app);

    /**
     * 获取Rooms会议室下的会议列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 会议列表
     */
    @RequestLine("POST /meeting/rooms/list_meetings?app={app}")
    @Headers(HEAD)
    MeetingRoomsMeetingListResponse listRoomsMeetings(MeetingRoomsMeetingListRequest request, @Param("app") String app);

    /**
     * 获取Rooms设备列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 设备列表
     */
    @RequestLine("POST /meeting/rooms/list_devices?app={app}")
    @Headers(HEAD)
    MeetingRoomsDeviceListResponse listRoomsDevices(MeetingRoomsDeviceListRequest request, @Param("app") String app);

    /**
     * 获取Rooms控制器列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 控制器列表
     */
    @RequestLine("POST /meeting/rooms/list_controllers?app={app}")
    @Headers(HEAD)
    MeetingRoomsControllerListResponse listRoomsControllers(MeetingRoomsControllerListRequest request, @Param("app") String app);

    /**
     * 获取企业Rooms会议室资源
     *
     * @param request 请求
     * @param app     应用名
     * @return Rooms资源统计
     */
    @RequestLine("POST /meeting/rooms/get_inventory?app={app}")
    @Headers(HEAD)
    MeetingRoomsInventoryResponse getRoomsInventory(MeetingRoomsInventoryRequest request, @Param("app") String app);

    /**
     * 呼叫Rooms会议室入会
     *
     * @param request 请求
     * @param app     应用名
     * @return 呼叫ID
     */
    @RequestLine("POST /meeting/rooms/call?app={app}")
    @Headers(HEAD)
    MeetingRoomsCallResponse callRooms(MeetingRoomsCallRequest request, @Param("app") String app);

    /**
     * 取消呼叫Rooms会议室
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/rooms/cancel_call?app={app}")
    @Headers(HEAD)
    BaseResponse cancelRoomsCall(MeetingRoomsCancelCallRequest request, @Param("app") String app);

    /**
     * 获取Rooms会议室呼叫应答状态
     *
     * @param request 请求
     * @param app     应用名
     * @return 应答状态
     */
    @RequestLine("POST /meeting/rooms/get_response_status?app={app}")
    @Headers(HEAD)
    MeetingRoomsResponseStatusResponse getRoomsCallResponseStatus(MeetingRoomsResponseStatusRequest request, @Param("app") String app);

    /**
     * 获取MRA设备状态
     *
     * @param request 请求
     * @param app     应用名
     * @return MRA设备状态
     */
    @RequestLine("POST /meeting/mra/query_status?app={app}")
    @Headers(HEAD)
    MeetingMraStatusResponse queryMraStatus(MeetingMraStatusRequest request, @Param("app") String app);

    /**
     * 设置MRA设备默认布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/mra/set_default_layout?app={app}")
    @Headers(HEAD)
    BaseResponse setMraDefaultLayout(MeetingMraDefaultLayoutRequest request, @Param("app") String app);

    /**
     * 设置MRA设备举手状态
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/mra/set_raise_hand?app={app}")
    @Headers(HEAD)
    BaseResponse setMraRaiseHand(MeetingMraRaiseHandRequest request, @Param("app") String app);

    /**
     * 挂断MRA设备呼叫
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/mra/hangup?app={app}")
    @Headers(HEAD)
    BaseResponse hangupMra(MeetingMraHangupRequest request, @Param("app") String app);

    /**
     * 获取企业布局模板列表
     *
     * @param app 应用名
     * @return 布局模板列表
     */
    @RequestLine("GET /meeting/layout/list_template?app={app}")
    @Headers(HEAD)
    MeetingLayoutTemplateListResponse listMeetingLayoutTemplates(@Param("app") String app);

    /**
     * 添加会议基础布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 新增布局列表
     */
    @RequestLine("POST /meeting/layout/add?app={app}")
    @Headers(HEAD)
    MeetingLayoutAddResponse addMeetingLayouts(MeetingLayoutAddRequest request, @Param("app") String app);

    /**
     * 添加会议高级布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 新增高级布局列表
     */
    @RequestLine("POST /meeting/advanced_layout/add?app={app}")
    @Headers(HEAD)
    MeetingAdvancedLayoutAddResponse addMeetingAdvancedLayouts(MeetingAdvancedLayoutAddRequest request, @Param("app") String app);

    /**
     * 修改会议基础布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/layout/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateMeetingLayout(MeetingLayoutUpdateRequest request, @Param("app") String app);

    /**
     * 修改会议高级布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/advanced_layout/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateMeetingAdvancedLayout(MeetingAdvancedLayoutUpdateRequest request, @Param("app") String app);

    /**
     * 设置会议默认布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/layout/set_default?app={app}")
    @Headers(HEAD)
    BaseResponse setDefaultMeetingLayout(MeetingLayoutDefaultRequest request, @Param("app") String app);

    /**
     * 应用会议高级布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/advanced_layout/apply?app={app}")
    @Headers(HEAD)
    BaseResponse applyMeetingAdvancedLayout(MeetingAdvancedLayoutApplyRequest request, @Param("app") String app);

    /**
     * 获取会议基础和高级布局列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 布局列表
     */
    @RequestLine("POST /meeting/advanced_layout/list?app={app}")
    @Headers(HEAD)
    MeetingAdvancedLayoutListResponse listMeetingAdvancedLayouts(MeetingAdvancedLayoutListRequest request, @Param("app") String app);

    /**
     * 获取用户高级布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 用户布局
     */
    @RequestLine("POST /meeting/advanced_layout/get_user_layout?app={app}")
    @Headers(HEAD)
    MeetingAdvancedLayoutUserResponse getMeetingUserLayout(MeetingAdvancedLayoutUserRequest request, @Param("app") String app);

    /**
     * 批量删除会议布局
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/advanced_layout/batch_delete?app={app}")
    @Headers(HEAD)
    BaseResponse batchDeleteMeetingLayouts(MeetingLayoutBatchDeleteRequest request, @Param("app") String app);

    /**
     * 添加会议背景
     *
     * @param request 请求
     * @param app     应用名
     * @return 新增背景列表
     */
    @RequestLine("POST /meeting/layout/add_background?app={app}")
    @Headers(HEAD)
    MeetingBackgroundAddResponse addMeetingBackgrounds(MeetingBackgroundAddRequest request, @Param("app") String app);

    /**
     * 设置会议默认背景
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/layout/set_default_background?app={app}")
    @Headers(HEAD)
    BaseResponse setDefaultMeetingBackground(MeetingBackgroundDefaultRequest request, @Param("app") String app);

    /**
     * 获取会议背景列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 背景列表
     */
    @RequestLine("POST /meeting/layout/list_background?app={app}")
    @Headers(HEAD)
    MeetingBackgroundListResponse listMeetingBackgrounds(MeetingBackgroundListRequest request, @Param("app") String app);

    /**
     * 删除单个会议背景
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/layout/delete_background?app={app}")
    @Headers(HEAD)
    BaseResponse deleteMeetingBackground(MeetingBackgroundDeleteRequest request, @Param("app") String app);

    /**
     * 批量删除会议背景
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/layout/batch_delete_background?app={app}")
    @Headers(HEAD)
    BaseResponse batchDeleteMeetingBackgrounds(MeetingBackgroundBatchDeleteRequest request, @Param("app") String app);

    /**
     * 获取云录制记录
     *
     * @param request 请求
     * @param app     应用名
     * @return 云录制记录列表
     */
    @RequestLine("POST /meeting/record/list?app={app}")
    @Headers(HEAD)
    MeetingRecordListResponse listMeetingRecords(MeetingRecordListRequest request, @Param("app") String app);

    /**
     * 获取云录制访问统计数据
     *
     * @param request 请求
     * @param app     应用名
     * @return 云录制访问统计列表
     */
    @RequestLine("POST /meeting/record/get_statistics?app={app}")
    @Headers(HEAD)
    MeetingRecordStatisticsResponse getMeetingRecordStatistics(MeetingRecordStatisticsRequest request, @Param("app") String app);

    /**
     * 修改会议录制共享配置
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/record/update_sharing_config?app={app}")
    @Headers(HEAD)
    BaseResponse updateMeetingRecordSharingConfig(MeetingRecordSharingUpdateRequest request, @Param("app") String app);

    /**
     * 删除会议录制
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/record/delete?app={app}")
    @Headers(HEAD)
    BaseResponse deleteMeetingRecord(MeetingRecordRequest request, @Param("app") String app);

    /**
     * 删除单个录制文件
     *
     * @param request 请求
     * @param app     应用名
     * @return 基础响应
     */
    @RequestLine("POST /meeting/record/delete_file?app={app}")
    @Headers(HEAD)
    BaseResponse deleteMeetingRecordFile(MeetingRecordFileRequest request, @Param("app") String app);

    /**
     * 获取单个录制文件详情
     *
     * @param request 请求
     * @param app     应用名
     * @return 录制文件详情
     */
    @RequestLine("POST /meeting/record/get_file?app={app}")
    @Headers(HEAD)
    MeetingRecordFileDetailResponse getMeetingRecordFile(MeetingRecordFileRequest request, @Param("app") String app);

    /**
     * 获取会议录制文件列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 录制文件列表
     */
    @RequestLine("POST /meeting/record/get_file_list?app={app}")
    @Headers(HEAD)
    MeetingRecordFileListResponse listMeetingRecordFiles(MeetingRecordRequest request, @Param("app") String app);

    /**
     * 获取录制转写段落列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 转写段落列表
     */
    @RequestLine("POST /meeting/record/transcript/get_paragraph_list?app={app}")
    @Headers(HEAD)
    MeetingTranscriptParagraphListResponse listMeetingTranscriptParagraphs(MeetingRecordFileRequest request, @Param("app") String app);

    /**
     * 获取录制转写详情
     *
     * @param request 请求
     * @param app     应用名
     * @return 转写详情
     */
    @RequestLine("POST /meeting/record/transcript/get_detail?app={app}")
    @Headers(HEAD)
    MeetingTranscriptDetailResponse getMeetingTranscriptDetail(MeetingTranscriptDetailRequest request, @Param("app") String app);

    /**
     * 搜索录制转写内容
     *
     * @param request 请求
     * @param app     应用名
     * @return 转写搜索结果
     */
    @RequestLine("POST /meeting/record/transcript/search?app={app}")
    @Headers(HEAD)
    MeetingTranscriptSearchResponse searchMeetingTranscript(MeetingTranscriptSearchRequest request, @Param("app") String app);

    /**
     * 批量分配高级功能账号
     *
     * @param request 请求
     * @param app     应用名
     * @return 批量任务ID
     */
    @RequestLine("POST /meeting/vip/submit_batch_add_job?app={app}")
    @Headers(HEAD)
    MeetingVipJobSubmitResponse submitMeetingVipAccounts(MeetingVipUserBatchRequest request, @Param("app") String app);

    /**
     * 查询批量分配高级功能账号结果
     *
     * @param request 请求
     * @param app     应用名
     * @return 批量分配任务结果
     */
    @RequestLine("POST /meeting/vip/batch_add_job_result?app={app}")
    @Headers(HEAD)
    MeetingVipJobResultResponse getMeetingVipAddJobResult(MeetingVipJobResultRequest request, @Param("app") String app);

    /**
     * 批量撤销高级功能账号
     *
     * @param request 请求
     * @param app     应用名
     * @return 批量任务ID
     */
    @RequestLine("POST /meeting/vip/submit_batch_del_job?app={app}")
    @Headers(HEAD)
    MeetingVipJobSubmitResponse cancelMeetingVipAccounts(MeetingVipUserBatchRequest request, @Param("app") String app);

    /**
     * 查询批量撤销高级功能账号结果
     *
     * @param request 请求
     * @param app     应用名
     * @return 批量撤销任务结果
     */
    @RequestLine("POST /meeting/vip/batch_del_job_result?app={app}")
    @Headers(HEAD)
    MeetingVipJobResultResponse getMeetingVipDeleteJobResult(MeetingVipJobResultRequest request, @Param("app") String app);

    /**
     * 获取高级功能账号列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 高级功能账号列表
     */
    @RequestLine("POST /meeting/vip/list?app={app}")
    @Headers(HEAD)
    MeetingVipListResponse listMeetingVips(MeetingVipListRequest request, @Param("app") String app);
}
