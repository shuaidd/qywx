package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import com.github.shuaidd.dto.meeting.MeetingRecordDailyStatistic;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MeetingRecordStatisticsResponse
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingRecordStatisticsResponse extends AbstractBaseResponse {
    /**
     * 按天统计结果列表
     **/
    @JsonProperty("summaries")
    private List<MeetingRecordDailyStatistic> summaries;

}
