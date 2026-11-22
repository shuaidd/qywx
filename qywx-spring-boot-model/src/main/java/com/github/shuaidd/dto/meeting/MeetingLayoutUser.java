package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 高级布局应用用户
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingLayoutUser {
    /**
     * 用户当前会议临时ID
     **/
    @JsonProperty("tmp_openid")
    private String tmpOpenid;
}
