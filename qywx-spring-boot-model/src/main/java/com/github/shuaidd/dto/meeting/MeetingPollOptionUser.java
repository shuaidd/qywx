package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会议投票选项投票人
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingPollOptionUser {

    /**
     * 同企业实名投票人的成员 userId，非同企业或匿名投票时可能不返回
     **/
    @JsonProperty("userid")
    private String userId;

    /**
     * 投票人在当前会议的临时 ID，匿名投票时不返回
     **/
    @JsonProperty("tmp_openid")
    private String tmpOpenid;
}
