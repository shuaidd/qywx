package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会议用户专属参会链接
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingCustomerData {

    /**
     * 用户专属字段，用于区分不同入会链接
     **/
    @JsonProperty("customer_data")
    private String customerData;

    /**
     * 用户专属参会链接
     **/
    @JsonProperty("meeting_short_url")
    private String meetingShortUrl;
}