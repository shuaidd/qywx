package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会议临时 ID 与报名 ID 映射
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingEnrollIdMapping {

    /**
     * 当场会议的成员临时 ID
     **/
    @JsonProperty("tmp_openid")
    private String tmpOpenid;

    /**
     * 成员报名 ID
     **/
    @JsonProperty("enroll_id")
    private String enrollId;
}