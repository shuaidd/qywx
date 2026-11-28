package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * MeetingTranscriptSpeaker
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingTranscriptSpeaker {
    /**
     * 同企业发言人userid
     **/
    @JsonProperty("userid")
    private String userId;

}
