package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import com.github.shuaidd.dto.meeting.MeetingPhoneNumber;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 批量电话入会外呼响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingPhoneCalloutResponse extends AbstractBaseResponse {
    /**
     * 成功外呼的电话号码列表
     **/
    @JsonProperty("phone_numbers")
    private List<MeetingPhoneNumber> phoneNumbers;
    /**
     * 不合法的外呼电话号码列表
     **/
    @JsonProperty("invalid_phone_numbers")
    private List<MeetingPhoneNumber> invalidPhoneNumbers;
}
