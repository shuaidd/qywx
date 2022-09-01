package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.tool.*;
import com.github.shuaidd.resquest.tool.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 效率工具
 *
 * @author ddshuai
 * date 2022-09-01 09:37
 **/
@FeignClient(name = "EfficiencyToolClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface EfficiencyToolClient extends CommonClient {

    /**
     * 创建预约直播
     *
     * @param request 请求体
     * @param app     应用名
     * @return CreateLivingResponse
     */
    @PostMapping(value = "living/create", headers = HEAD)
    CreateLivingResponse createLiving(LivingRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改预约直播
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "living/modify", headers = HEAD)
    BaseResponse modifyLiving(LivingRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 取消预约直播
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "living/cancel", headers = HEAD)
    BaseResponse cancelLiving(CancelLivingRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除直播回放
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "living/delete_replay_data", headers = HEAD)
    BaseResponse delLiving(DeleteLivingRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取微信观看直播凭证
     *
     * @param request 请求体
     * @param app     应用名
     * @return LivingCodeResponse
     */
    @PostMapping(value = "living/get_living_code", headers = HEAD)
    LivingCodeResponse getLivingCode(LivingCodeRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取成员直播ID列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetUserLivingResponse
     */
    @PostMapping(value = "living/get_user_all_livingid", headers = HEAD)
    GetUserLivingResponse getUserAllLivingId(GetUserLivingRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取直播详情
     *
     * @param livingId 直播编号
     * @param app      应用名
     * @return LivingInfoResponse
     */
    @GetMapping(value = "living/get_living_info", headers = HEAD)
    LivingInfoResponse getLivingInfo(@RequestParam("livingid") String livingId, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取直播观看明细
     *
     * @param request 请求体
     * @param app     应用名
     * @return WatchStatInfoResponse
     */
    @PostMapping(value = "living/get_watch_stat", headers = HEAD)
    WatchStatInfoResponse getWatchStat(GetWatchStatRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取跳转小程序商城的直播观众信息
     *
     * @param request 请求体
     * @param app     应用名
     * @return LivingShareResponse
     */
    @PostMapping(value = "living/get_living_share_info", headers = HEAD)
    LivingShareResponse getLivingShareInfo(LivingShareRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建预约会议
     *
     * @param request 请求体
     * @param app     应用名
     * @return CreateMeetingResponse
     */
    @PostMapping(value = "meeting/create", headers = HEAD)
    CreateMeetingResponse createMeeting(MeetingRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改预约会议
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse updateMeeting(MeetingRequest request, @RequestParam(HEAD_KEY) String app);
     */
    @PostMapping(value = "meeting/update", headers = HEAD)
    BaseResponse updateMeeting(MeetingRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 取消预约会议
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "meeting/cancel", headers = HEAD)
    BaseResponse cancelMeeting(CancelMeetingRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取成员会议ID列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetMeetingForUserResponse
     */
    @PostMapping(value = "meeting/get_user_meetingid", headers = HEAD)
    GetMeetingForUserResponse getUserMeetingId(GetMeetingForUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取会议详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return MeetingDetailResponse
     */
    @PostMapping(value = "meeting/get_info", headers = HEAD)
    MeetingDetailResponse getMeetingInfo(GetMeetingDetailRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取公费电话拨打记录
     *
     * @param request 请求体
     * @param app     应用名
     * @return DialRecordResponse
     */
    @PostMapping(value = "dial/get_dial_record", headers = HEAD)
    DialRecordResponse getDialRecord(DialRecordRequest request, @RequestParam(HEAD_KEY) String app);
}
