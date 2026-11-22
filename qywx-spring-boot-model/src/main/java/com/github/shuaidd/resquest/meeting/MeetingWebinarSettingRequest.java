package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingWebinarHost;
import com.github.shuaidd.dto.meeting.MeetingWebinarMediaSetting;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 网络研讨会基础设置请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public abstract class MeetingWebinarSettingRequest {
    /**
     * 网络研讨会主题，1~255 个字符
     **/
    @JsonProperty("title")
    private String title;
    /**
     * 主办方名称，最长 40 个字符
     **/
    @JsonProperty("sponsor")
    private String sponsor;
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
     * 网络研讨会主持人列表
     **/
    @JsonProperty("hosts")
    private List<MeetingWebinarHost> hosts;
    /**
     * 观众观看密码，4~6 位数字
     **/
    @JsonProperty("password")
    private String password;
    /**
     * 活动页封面图片地址
     **/
    @JsonProperty("cover_url")
    private String coverUrl;
    /**
     * 网络研讨会纯文本描述，最长 5000 个字符
     **/
    @JsonProperty("description")
    private String description;
    /**
     * 是否允许邀请链接入会成员自动成为嘉宾
     **/
    @JsonProperty("enable_guest_invite_link")
    private Boolean enableGuestInviteLink;
    /**
     * 网络研讨会媒体参数配置
     **/
    @JsonProperty("media_setting")
    private MeetingWebinarMediaSetting mediaSetting;
    /**
     * 是否开启问答
     **/
    @JsonProperty("enable_qa")
    private Boolean enableQa;
    /**
     * 观众公共聊天敏感词列表
     **/
    @JsonProperty("sensitive_words")
    private List<String> sensitiveWords;
    /**
     * 是否开启人工审核
     **/
    @JsonProperty("enable_manual_check")
    private Boolean enableManualCheck;
    /**
     * 是否开启网络研讨会活动页
     **/
    @JsonProperty("activity_page")
    private Boolean activityPage;
    /**
     * 活动页人数展示方式：0 不展示、1 展示
     **/
    @JsonProperty("display_number_of_attendees")
    private Integer displayNumberOfAttendees;
    /**
     * 是否允许观众观看回放
     **/
    @JsonProperty("playback_for_audience")
    private Boolean playbackForAudience;
    /**
     * 是否开启准备模式
     **/
    @JsonProperty("preparation_mode")
    private Boolean preparationMode;
}
