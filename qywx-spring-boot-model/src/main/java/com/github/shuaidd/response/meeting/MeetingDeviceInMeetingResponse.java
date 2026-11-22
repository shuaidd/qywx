package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingDeviceInMeetingResult;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * 成员设备入会检查响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingDeviceInMeetingResponse extends AbstractBaseResponse {

    /**
     * 成员设备入会检查结果列表
     **/
    @JsonProperty("result_list")
    private List<MeetingDeviceInMeetingResult> resultList;
}