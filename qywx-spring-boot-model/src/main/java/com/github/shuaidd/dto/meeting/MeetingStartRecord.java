package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 员工会议发起记录
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingStartRecord {

    /**
     * 会议发起者 userid
     **/
    @JsonProperty("userid")
    private String userId;

    /**
     * 会议发起时间戳，单位秒
     **/
    @JsonProperty("start_time")
    private Long startTime;
}