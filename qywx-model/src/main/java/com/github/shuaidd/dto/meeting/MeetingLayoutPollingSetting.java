package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会议高级布局轮询设置
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingLayoutPollingSetting {
    /**
     * 轮询间隔时间类型：1 秒、2 分钟
     **/
    @JsonProperty("polling_interval_unit")
    private Integer pollingIntervalUnit;
    /**
     * 轮询间隔时长
     **/
    @JsonProperty("polling_interval")
    private Integer pollingInterval;
    /**
     * 是否忽略未开启视频的成员
     **/
    @JsonProperty("ignore_user_novideo")
    private Boolean ignoreUserNoVideo;
    /**
     * 是否忽略未入会成员
     **/
    @JsonProperty("ignore_user_absence")
    private Boolean ignoreUserAbsence;
}
