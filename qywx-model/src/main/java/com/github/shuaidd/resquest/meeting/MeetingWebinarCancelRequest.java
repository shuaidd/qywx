package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 取消网络研讨会请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingWebinarCancelRequest {
    /**
     * 网络研讨会 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
}
