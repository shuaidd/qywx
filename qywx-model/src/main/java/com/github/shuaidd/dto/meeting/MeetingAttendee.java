package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * 会议参会成员信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingAttendee {

    /**
     * 企业内参会成员 userid
     **/
    @JsonProperty("userid")
    private String userId;

    /**
     * 会议中为成员授予的临时 ID
     **/
    @JsonProperty("tmp_openid")
    private String tmpOpenid;

    /**
     * 加入会议时间戳，单位秒
     **/
    @JsonProperty("join_time")
    private String joinTime;

    /**
     * 离开会议时间戳，单位秒
     **/
    @JsonProperty("quit_time")
    private String quitTime;

    /**
     * 成员终端设备类型
     **/
    @JsonProperty("instance_id")
    private Integer instanceId;

    /**
     * 成员在普通会议中的角色
     **/
    @JsonProperty("role")
    private Integer role;

    /**
     * 成员在网络研讨会中的角色
     **/
    @JsonProperty("webinar_role")
    private Integer webinarRole;

    /**
     * 成员入会方式
     **/
    @JsonProperty("join_type")
    private Integer joinType;

    /**
     * 成员使用的网络类型
     **/
    @JsonProperty("net")
    private String net;

    /**
     * 麦克风是否开启
     **/
    @JsonProperty("audio_state")
    private Boolean audioState;

    /**
     * 摄像头是否开启
     **/
    @JsonProperty("video_state")
    private Boolean videoState;

    /**
     * 屏幕共享是否开启
     **/
    @JsonProperty("screen_shared_state")
    private Boolean screenSharedState;

    /**
     * 通过专属链接入会时返回的用户专属字段
     **/
    @JsonProperty("customer_data")
    private String customerData;

    /**
     * 成员会议健康度
     **/
    @JsonProperty("quality")
    private Integer quality;

    /**
     * 成员音频质量等级
     **/
    @JsonProperty("audio_quality")
    private Integer audioQuality;

    /**
     * 成员视频质量等级
     **/
    @JsonProperty("video_quality")
    private Integer videoQuality;

    /**
     * 成员屏幕共享质量等级
     **/
    @JsonProperty("screen_share_quality")
    private Integer screenShareQuality;

    /**
     * 成员网络质量等级
     **/
    @JsonProperty("network_quality")
    private Integer networkQuality;

    /**
     * 成员会议质量告警问题列表
     **/
    @JsonProperty("problems")
    private List<String> problems;
}