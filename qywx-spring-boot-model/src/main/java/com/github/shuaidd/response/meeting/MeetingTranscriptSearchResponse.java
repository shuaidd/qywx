package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import com.github.shuaidd.dto.meeting.MeetingTranscriptSearchHit;
import com.github.shuaidd.dto.meeting.MeetingTranscriptSearchTimeline;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MeetingTranscriptSearchResponse
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingTranscriptSearchResponse extends AbstractBaseResponse {
    /**
     * 搜索结果列表
     **/
    @JsonProperty("hits")
    private List<MeetingTranscriptSearchHit> hits;

    /**
     * 搜索结果时间轴列表
     **/
    @JsonProperty("timelines")
    private List<MeetingTranscriptSearchTimeline> timelines;

}
