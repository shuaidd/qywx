package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会议MRA状态响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingMraStatusResponse extends AbstractBaseResponse {
    /**
     * 被查询成员当场会议的成员临时ID
     **/
    @JsonProperty("tmp_openid")
    private String tmpOpenid;
    /**
     * 终端设备类型，9表示MRA设备
     **/
    @JsonProperty("instance_id")
    private Integer instanceId;
    /**
     * 成员角色
     **/
    @JsonProperty("user_role")
    private Integer userRole;
    /**
     * 网络研讨会成员角色
     **/
    @JsonProperty("webinar_member_role")
    private Integer webinarMemberRole;
    /**
     * 成员IP地址
     **/
    @JsonProperty("ip")
    private String ip;
    /**
     * 成员当前显示名称
     **/
    @JsonProperty("name")
    private String name;
    /**
     * 麦克风状态
     **/
    @JsonProperty("audio_state")
    private Boolean audioState;
    /**
     * 摄像头状态
     **/
    @JsonProperty("video_state")
    private Boolean videoState;
    /**
     * 屏幕共享状态
     **/
    @JsonProperty("screen_shared_state")
    private Boolean screenSharedState;
    /**
     * 当前成员默认分屏设置
     **/
    @JsonProperty("default_layout")
    private Integer defaultLayout;
    /**
     * 举手状态
     **/
    @JsonProperty("raise_hands_state")
    private Boolean raiseHandsState;
}
