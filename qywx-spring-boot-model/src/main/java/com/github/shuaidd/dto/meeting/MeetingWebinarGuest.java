package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingGuest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 网络研讨会嘉宾信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingWebinarGuest extends MeetingGuest {
    /**
     * 嘉宾类型：1 内部、2 外部、3 链接嘉宾
     **/
    @JsonProperty("guest_type")
    private Integer guestType;
    /**
     * 内部嘉宾 userid
     **/
    @JsonProperty("userid")
    private String userId;
    /**
     * 外部嘉宾邮箱
     **/
    @JsonProperty("email")
    private String email;
}
