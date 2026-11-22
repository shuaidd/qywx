package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会议等候室成员信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingWaitingRoomUser {

    /**
     * 等候室企业内成员 userid
     **/
    @JsonProperty("userid")
    private String userId;

    /**
     * 会议中为成员授予的临时 ID
     **/
    @JsonProperty("tmp_openid")
    private String tmpOpenid;

    /**
     * 成员终端设备类型
     **/
    @JsonProperty("instance_id")
    private Integer instanceId;

    /**
     * 用户专属信息
     **/
    @JsonProperty("customer_data")
    private String customerData;

    /**
     * 加入等候室时间，单位毫秒
     **/
    @JsonProperty("join_time")
    private Long joinTime;

    /**
     * 离开等候室时间，单位毫秒
     **/
    @JsonProperty("quit_time")
    private Long quitTime;
}