package com.github.shuaidd.client;

import com.github.shuaidd.response.meeting.MeetingInviteResponse;
import com.github.shuaidd.resquest.meeting.MeetingInviteRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 预约会议高级管理
 *
 * @author ddshuai
 */
public interface MeetingClient extends CommonClient {

    /**
     * 获取会议受邀成员列表
     *
     * @param request req
     * @param app     app
     * @return 会议邀请人员列表
     */
    @RequestLine("POST /meeting/get_invitees?app={app}")
    @Headers(HEAD)
    MeetingInviteResponse getInvitees(MeetingInviteRequest request, @Param("app") String app);
}
