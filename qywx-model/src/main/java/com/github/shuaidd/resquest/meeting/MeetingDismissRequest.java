package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 结束进行中会议请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingDismissRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 是否强制结束会议：0 有参会者时不结束、1 强制结束
     **/
    @JsonProperty("force_dismiss")
    private Integer forceDismiss;

    /**
     * 是否回收会议号：0 不回收、1 回收
     **/
    @JsonProperty("retrieve_code")
    private Integer retrieveCode;
}
