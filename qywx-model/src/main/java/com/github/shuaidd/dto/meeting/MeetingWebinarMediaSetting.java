package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

/**
 * 网络研讨会媒体参数配置
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingWebinarMediaSetting {
    /**
     * 入会时是否静音
     **/
    @JsonProperty("enable_enter_mute")
    private Boolean enableEnterMute;
    /**
     * 是否允许参会者自行取消静音
     **/
    @JsonProperty("allow_unmute_self")
    private Boolean allowUnmuteSelf;
    /**
     * 主持人进会前是否允许嘉宾入会
     **/
    @JsonProperty("allow_enter_before_host")
    private Boolean allowEnterBeforeHost;
    /**
     * 是否开启屏幕共享水印
     **/
    @JsonProperty("enable_screen_watermark")
    private Boolean enableScreenWatermark;
    /**
     * 屏幕水印样式：0 单排、1 多排
     **/
    @JsonProperty("watermark_type")
    private Integer watermarkType;
    /**
     * 是否允许企业外部成员入会
     **/
    @JsonProperty("allow_external_user")
    private Boolean allowExternalUser;
    /**
     * 自动录制类型：none 禁用、local 本地、cloud 云录制
     **/
    @JsonProperty("auto_record_type")
    private String autoRecordType;
    /**
     * 参会成员入会时是否自动开启云录制
     **/
    @JsonProperty("attendee_join_auto_record")
    private Boolean attendeeJoinAutoRecord;
    /**
     * 主持人是否允许暂停或停止云录制
     **/
    @JsonProperty("enable_host_pause_auto_record")
    private Boolean enableHostPauseAutoRecord;
}
