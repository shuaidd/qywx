package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 高级布局宫格用户
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingLayoutGridUser {
    /**
     * 本场会议企业成员userid
     **/
    @JsonProperty("userid")
    private String userId;
    /**
     * 用户当前会议临时ID
     **/
    @JsonProperty("tmp_openid")
    private String tmpOpenid;
    /**
     * 视频画面展示昵称，Base64编码
     **/
    @JsonProperty("nick_name")
    private String nickName;
}
