package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Rooms会议室会议配置
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class RoomsMeetingSettings {
    /**
     * 水印：0关闭、1单排、2多排
     **/
    @JsonProperty("water_mark")
    private Integer waterMark;
    /**
     * 自动接听：1开启、2关闭
     **/
    @JsonProperty("auto_response")
    private Integer autoResponse;
    /**
     * 是否开启字幕
     **/
    @JsonProperty("caption")
    private Boolean caption;
    /**
     * 是否开启专属ID
     **/
    @JsonProperty("room_pmi")
    private Boolean roomPmi;
    /**
     * Rooms屏幕是否展示消息通知
     **/
    @JsonProperty("room_notification")
    private Boolean roomNotification;
}
