package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import com.github.shuaidd.dto.meeting.MeetingRecord;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MeetingRecordListResponse
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingRecordListResponse extends AbstractBaseResponse {
    /**
     * 是否还有更多数据
     **/
    @JsonProperty("has_more")
    private Boolean hasMore;

    /**
     * 下一页游标
     **/
    @JsonProperty("next_cursor")
    private String nextCursor;

    /**
     * 会议录制列表
     **/
    @JsonProperty("record_list")
    private List<MeetingRecord> recordList;

}
