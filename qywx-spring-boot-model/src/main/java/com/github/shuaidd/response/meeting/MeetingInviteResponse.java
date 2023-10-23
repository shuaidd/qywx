package com.github.shuaidd.response.meeting;

import com.github.shuaidd.dto.addressbook.UserId;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

public class MeetingInviteResponse extends AbstractBaseResponse {

    private List<UserId> invitees;

    public List<UserId> getInvitees() {
        return invitees;
    }

    public void setInvitees(List<UserId> invitees) {
        this.invitees = invitees;
    }
}
