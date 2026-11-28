package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * MeetingRecordDailyStatistic
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingRecordDailyStatistic {
    /**
     * 统计日期，格式yyyy-MM-dd
     **/
    @JsonProperty("date")
    private String date;

    /**
     * 当天观看次数
     **/
    @JsonProperty("view_count")
    private Integer viewCount;

    /**
     * 当天下载次数
     **/
    @JsonProperty("download_count")
    private Integer downloadCount;

}
