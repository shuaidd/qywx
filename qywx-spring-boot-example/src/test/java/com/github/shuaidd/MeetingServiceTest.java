package com.github.shuaidd;

import com.github.shuaidd.resquest.meeting.MeetingInviteRequest;
import com.github.shuaidd.service.MeetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MeetingServiceTest extends AbstractTest {

    private MeetingService meetingService;
    public static final String APP = "robbot";

    @BeforeEach
    public void setUp() {
        meetingService = weChatManager.meetingService();
    }

    @Test
    public void getMeetingInvites() {
        MeetingInviteRequest request = new MeetingInviteRequest();
        request.setMeetingId("122");
        meetingService.getMeetingInvites(request, APP);
    }
}
