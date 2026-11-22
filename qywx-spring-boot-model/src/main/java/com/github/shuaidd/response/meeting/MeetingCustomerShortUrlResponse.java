package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingCustomerData;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * 用户专属参会链接响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingCustomerShortUrlResponse extends AbstractBaseResponse {

    /**
     * 创建接口返回的用户专属参会链接
     **/
    @JsonProperty("meeting_short_url_customer_data")
    private MeetingCustomerData meetingShortUrlCustomerData;

    /**
     * 查询接口返回的用户专属参会链接列表
     **/
    @JsonProperty("meeting_short_url_customer_data_list")
    private List<MeetingCustomerData> meetingShortUrlCustomerDataList;
}