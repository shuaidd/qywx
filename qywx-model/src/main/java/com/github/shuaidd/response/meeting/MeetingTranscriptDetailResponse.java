package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import com.github.shuaidd.dto.meeting.MeetingTranscriptDetail;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MeetingTranscriptDetailResponse
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingTranscriptDetailResponse extends AbstractBaseResponse {
    /**
     * 是否还有更多数据
     **/
    @JsonProperty("has_more")
    private Boolean hasMore;

    /**
     * 录制转写详情
     **/
    @JsonProperty("transcripts")
    private MeetingTranscriptDetail transcripts;

}
