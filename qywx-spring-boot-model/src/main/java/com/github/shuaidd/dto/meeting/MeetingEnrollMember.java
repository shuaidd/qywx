package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会议报名成员信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingEnrollMember {

    /**
     * 会议报名 ID
     **/
    @JsonProperty("enroll_id")
    private String enrollId;

    /**
     * 企业内报名成员 userid
     **/
    @JsonProperty("userid")
    private String userId;

    /**
     * 国家或地区代码，例如中国传 86
     **/
    @JsonProperty("area")
    private String area;

    /**
     * 报名成员手机号
     **/
    @JsonProperty("phone_number")
    private String phoneNumber;

    /**
     * 报名展示昵称
     **/
    @JsonProperty("nick_name")
    private String nickName;

    /**
     * 电话入会报名码
     **/
    @JsonProperty("enroll_code")
    private String enrollCode;
}