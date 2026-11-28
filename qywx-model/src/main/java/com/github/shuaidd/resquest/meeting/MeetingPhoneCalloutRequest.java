package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import com.github.shuaidd.dto.meeting.MeetingPhoneNumber;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

/**
 * 批量电话入会外呼请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingPhoneCalloutRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 待外呼电话号码列表，单次最多50路
     **/
    @JsonProperty("phone_numbers")
    private List<MeetingPhoneNumber> phoneNumbers;
}
