package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import com.github.shuaidd.dto.meeting.MeetingWebinarHost;
import com.github.shuaidd.dto.meeting.MeetingWebinarMediaSetting;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 网络研讨会详情响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingWebinarDetailResponse extends MeetingWebinarCreateResponse {
    /**
     * 网络研讨会当前状态
     **/
    @JsonProperty("status")
    private String status;
    /**
     * 主办方名称
     **/
    @JsonProperty("sponsor")
    private String sponsor;
    /**
     * 网络研讨会主持人列表
     **/
    @JsonProperty("hosts")
    private List<MeetingWebinarHost> hosts;
    /**
     * 活动页封面图片地址
     **/
    @JsonProperty("cover_url")
    private String coverUrl;
    /**
     * 网络研讨会描述
     **/
    @JsonProperty("description")
    private String description;
    /**
     * 是否允许邀请链接入会成员自动成为嘉宾
     **/
    @JsonProperty("enable_guest_invite_link")
    private Boolean enableGuestInviteLink;
    /**
     * 网络研讨会媒体参数配置
     **/
    @JsonProperty("media_setting")
    private MeetingWebinarMediaSetting mediaSetting;
    /**
     * 是否开启问答
     **/
    @JsonProperty("enable_qa")
    private Boolean enableQa;
    /**
     * 是否开启活动页
     **/
    @JsonProperty("activity_page")
    private Boolean activityPage;
    /**
     * 活动页人数展示方式：0 不展示、1 展示
     **/
    @JsonProperty("display_number_of_attendees")
    private Integer displayNumberOfAttendees;
    /**
     * 是否允许观众观看回放
     **/
    @JsonProperty("playback_for_audience")
    private Boolean playbackForAudience;
    /**
     * 网络研讨会回放地址
     **/
    @JsonProperty("playback_url")
    private String playbackUrl;
    /**
     * 是否开启准备模式
     **/
    @JsonProperty("preparation_mode")
    private Boolean preparationMode;
    /**
     * 暖场图片地址
     **/
    @JsonProperty("warm_up_picture")
    private String warmUpPicture;
    /**
     * 暖场视频地址
     **/
    @JsonProperty("warm_up_video")
    private String warmUpVideo;
    /**
     * 暖场中是否允许参会者邀请其他成员
     **/
    @JsonProperty("allow_attendees_invite_others")
    private Boolean allowAttendeesInviteOthers;
}
