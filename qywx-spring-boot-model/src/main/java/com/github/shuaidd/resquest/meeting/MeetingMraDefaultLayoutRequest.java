package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingMraDevice;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 设置会议MRA默认布局请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingMraDefaultLayoutRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 默认分屏设置：1 等分模式、2 全屏模式、3 1+N
     **/
    @JsonProperty("default_layout")
    private Integer defaultLayout;
    /**
     * 默认非视频与会者显示方式：1 显示、2 隐藏
     **/
    @JsonProperty("default_novideo_user")
    private Integer defaultNoVideoUser;
    /**
     * 被操作MRA设备
     **/
    @JsonProperty("mra")
    private MeetingMraDevice mra;
}
