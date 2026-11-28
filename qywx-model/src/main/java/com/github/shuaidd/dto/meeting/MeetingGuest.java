package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会议嘉宾信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingGuest {

    /**
     * 国家或地区代码，例如中国传 86
     **/
    @JsonProperty("area")
    private String area;

    /**
     * 嘉宾手机号
     **/
    @JsonProperty("phone_number")
    private String phoneNumber;

    /**
     * 嘉宾名称
     **/
    @JsonProperty("guest_name")
    private String guestName;
}