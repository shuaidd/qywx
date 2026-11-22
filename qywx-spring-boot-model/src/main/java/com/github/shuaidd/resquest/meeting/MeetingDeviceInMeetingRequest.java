package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

/**
 * 查询成员设备是否入会请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingDeviceInMeetingRequest {

    /**
     * 企业成员 userid
     **/
    @JsonProperty("userid")
    private String userId;

    /**
     * 要查询的终端设备类型列表
     **/
    @JsonProperty("instance_id_list")
    private List<Integer> instanceIdList;

    /**
     * 会议 ID 列表
     **/
    @JsonProperty("meetingid_list")
    private List<String> meetingIdList;
}