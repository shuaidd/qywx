package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import com.github.shuaidd.dto.meeting.MeetingPhoneNumber;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 电话入会成员临时ID响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingPhoneTmpOpenIdResponse extends AbstractBaseResponse {
    /**
     * 电话号码对应的会议成员临时ID列表
     **/
    @JsonProperty("tmp_openid_list")
    private List<MeetingPhoneNumber> tmpOpenidList;
}
