package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 设置进行中会议参数请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingRealControlSettingRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 是否全体静音
     **/
    @JsonProperty("mute_all")
    private Boolean muteAll;

    /**
     * 全体静音时是否允许成员自行取消静音
     **/
    @JsonProperty("allow_unmute_self")
    private Boolean allowUnmuteSelf;

    /**
     * 入会静音方式：0 关闭、1 开启、2 超过 6 人自动开启
     **/
    @JsonProperty("enable_enter_mute")
    private Integer enableEnterMute;

    /**
     * 是否锁定会议
     **/
    @JsonProperty("meeting_locked")
    private Boolean meetingLocked;

    /**
     * 是否隐藏会议号和密码
     **/
    @JsonProperty("hide_meeting_code_password")
    private Boolean hideMeetingCodePassword;

    /**
     * 参会者聊天权限：0 自由聊天、1 仅公开聊天、2 仅可私聊主持人
     **/
    @JsonProperty("allow_chat")
    private Integer allowChat;

    /**
     * 是否允许参会者发起屏幕共享
     **/
    @JsonProperty("allow_share_screen")
    private Boolean allowShareScreen;

    /**
     * 是否仅企业成员可入会
     **/
    @JsonProperty("allow_external_user")
    private Boolean allowExternalUser;

    /**
     * 成员入会时是否播放提示音
     **/
    @JsonProperty("play_ivr_on_join")
    private Boolean playIvrOnJoin;

    /**
     * 是否开启等候室
     **/
    @JsonProperty("enable_waiting_room")
    private Boolean enableWaitingRoom;
}
