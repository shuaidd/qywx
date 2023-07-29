package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.meeting.MeetingInviteResponse;
import com.github.shuaidd.resquest.meeting.MeetingInviteRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 预约会议高级管理
 *
 * @author ddshuai
 */
@FeignClient(name = "MeetingClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface MeetingClient extends CommonClient {

    /**
     * 获取会议受邀成员列表
     *
     * @param request req
     * @param app     app
     * @return 会议邀请人员列表
     */
    @PostMapping(value = "meeting/get_invitees", headers = HEAD)
    MeetingInviteResponse getInvitees(MeetingInviteRequest request, @RequestParam(HEAD_KEY) String app);
}
