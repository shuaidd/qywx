package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会议报名 ID 引用
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingEnrollIdRef {

    /**
     * 会议报名 ID
     **/
    @JsonProperty("enroll_id")
    private String enrollId;
}