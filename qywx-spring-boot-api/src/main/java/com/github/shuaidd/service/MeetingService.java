package com.github.shuaidd.service;

import com.github.shuaidd.client.MeetingClient;
import com.github.shuaidd.response.meeting.MeetingInviteResponse;
import com.github.shuaidd.resquest.meeting.MeetingInviteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 预约会议管理
 *
 * @author ddshuai
 */
@Service
public class MeetingService extends AbstractBaseService {

    private final MeetingClient meetingClient;

    public MeetingService(MeetingClient meetingClient) {
        this.meetingClient = meetingClient;
    }

    /**
     * 获取会议受邀成员列表
     *
     * @param request         req
     * @param applicationName 应用名称
     * @return 邀请人员列表
     */
    public MeetingInviteResponse getMeetingInvites(MeetingInviteRequest request, String applicationName) {
        return meetingClient.getInvitees(request, applicationName);
    }
}
