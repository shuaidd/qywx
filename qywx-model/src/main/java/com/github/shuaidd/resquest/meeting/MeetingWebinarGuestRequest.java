package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingWebinarGuest;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 更新网络研讨会嘉宾列表请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingWebinarGuestRequest {
    /**
     * 网络研讨会 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 网络研讨会嘉宾列表
     **/
    @JsonProperty("guests")
    private List<MeetingWebinarGuest> guests;
}
