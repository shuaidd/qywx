package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 电话入会电话号码信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingPhoneNumber {
    /**
     * 国家/地区代码，例如中国是86
     **/
    @JsonProperty("area")
    private Integer area;
    /**
     * 电话号码或固定电话总机号
     **/
    @JsonProperty("phone")
    private String phone;
    /**
     * 固定电话分机号
     **/
    @JsonProperty("extension_number")
    private String extensionNumber;
    /**
     * 外呼状态
     **/
    @JsonProperty("status")
    private String status;
    /**
     * 当场会议的成员临时ID，可用于会控操作
     **/
    @JsonProperty("tmp_openid")
    private String tmpOpenid;
}
