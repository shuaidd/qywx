package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.resquest.meeting.MeetingWebinarSettingRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 修改网络研讨会请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingWebinarUpdateRequest extends MeetingWebinarSettingRequest {
    /**
     * 网络研讨会 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
}
