package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingAttendee;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * 会议健康度响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingQualityResponse extends AbstractBaseResponse {

    /**
     * 会议整体健康度
     **/
    @JsonProperty("quality")
    private Integer quality;

    /**
     * 会议音频质量等级
     **/
    @JsonProperty("audio_quality")
    private Integer audioQuality;

    /**
     * 会议视频质量等级
     **/
    @JsonProperty("video_quality")
    private Integer videoQuality;

    /**
     * 会议屏幕共享质量等级
     **/
    @JsonProperty("screen_share_quality")
    private Integer screenShareQuality;

    /**
     * 会议网络质量等级
     **/
    @JsonProperty("network_quality")
    private Integer networkQuality;

    /**
     * 会议质量告警问题列表
     **/
    @JsonProperty("problems")
    private List<String> problems;

    /**
     * 参会成员健康度列表
     **/
    @JsonProperty("attendees")
    private List<MeetingAttendee> attendees;
}
