package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会议基础布局座次
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingLayoutSeat {
    /**
     * 宫格ID
     **/
    @JsonProperty("grid_id")
    private String gridId;
    /**
     * 宫格类型：1 视频、2 共享画面、3 拓展应用
     **/
    @JsonProperty("grid_type")
    private Integer gridType;
    /**
     * 当场会议企业成员userid
     **/
    @JsonProperty("userid")
    private String userId;
    /**
     * 当场会议用户临时ID
     **/
    @JsonProperty("tmp_openid")
    private String tmpOpenid;
    /**
     * 视频画面展示名称
     **/
    @JsonProperty("nick_name")
    private String nickName;
    /**
     * 拓展应用ID
     **/
    @JsonProperty("tool_sdkid")
    private String toolSdkId;
}
