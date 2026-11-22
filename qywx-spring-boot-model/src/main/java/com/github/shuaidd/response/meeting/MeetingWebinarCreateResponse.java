package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建网络研讨会响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingWebinarCreateResponse extends AbstractBaseResponse {
    /**
     * 网络研讨会主题
     **/
    @JsonProperty("title")
    private String title;
    /**
     * 网络研讨会 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 网络研讨会会议号
     **/
    @JsonProperty("meeting_code")
    private String meetingCode;
    /**
     * 会议开始时间戳，单位秒
     **/
    @JsonProperty("start_time")
    private String startTime;
    /**
     * 会议结束时间戳，单位秒
     **/
    @JsonProperty("end_time")
    private String endTime;
    /**
     * 观众观看限制类型：0 公开、1 报名、2 密码
     **/
    @JsonProperty("admission_type")
    private Integer admissionType;
    /**
     * 观众观看密码
     **/
    @JsonProperty("password")
    private String password;
    /**
     * 观众入会链接
     **/
    @JsonProperty("audience_join_link")
    private String audienceJoinLink;
    /**
     * 嘉宾入会链接
     **/
    @JsonProperty("guest_join_link")
    private String guestJoinLink;
    /**
     * 人工审核链接
     **/
    @JsonProperty("manual_check_link")
    private String manualCheckLink;
    /**
     * 人工审核密码
     **/
    @JsonProperty("manual_check_password")
    private String manualCheckPassword;
}
