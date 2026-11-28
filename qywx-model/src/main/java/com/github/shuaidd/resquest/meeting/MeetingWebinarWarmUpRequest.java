package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

/**
 * 设置网络研讨会暖场请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingWebinarWarmUpRequest {
    /**
     * 网络研讨会 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
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
