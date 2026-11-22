package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import com.github.shuaidd.dto.meeting.MeetingPhoneNumber;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 电话入会外呼状态响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingPhoneCalloutStatusResponse extends AbstractBaseResponse {
    /**
     * 外呼电话号码状态列表
     **/
    @JsonProperty("phone_numbers")
    private List<MeetingPhoneNumber> phoneNumbers;
    /**
     * 下一页分页游标
     **/
    @JsonProperty("next_cursor")
    private String nextCursor;
    /**
     * 是否还有待拉取数据
     **/
    @JsonProperty("has_more")
    private Boolean hasMore;
}
