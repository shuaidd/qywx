package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import com.github.shuaidd.dto.meeting.MeetingPhoneNumber;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取电话入会成员临时ID请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingPhoneTmpOpenIdRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 待查询电话号码列表，上限20个
     **/
    @JsonProperty("phone_numbers")
    private List<MeetingPhoneNumber> phoneNumbers;
}
