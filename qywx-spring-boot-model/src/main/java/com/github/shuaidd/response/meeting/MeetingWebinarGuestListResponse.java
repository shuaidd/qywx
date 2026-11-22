package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import com.github.shuaidd.dto.meeting.MeetingWebinarGuest;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 网络研讨会嘉宾列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingWebinarGuestListResponse extends AbstractBaseResponse {
    /**
     * 网络研讨会嘉宾列表
     **/
    @JsonProperty("guests")
    private List<MeetingWebinarGuest> guests;
}
