package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 设置会议默认背景请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingBackgroundDefaultRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 会议应用背景ID，空字符串恢复默认黑色背景
     **/
    @JsonProperty("selected_background_id")
    private String selectedBackgroundId;
}
