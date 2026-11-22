package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingBackground;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会议背景添加响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingBackgroundAddResponse extends AbstractBaseResponse {
    /**
     * 会议应用的背景ID
     **/
    @JsonProperty("selected_background_id")
    private String selectedBackgroundId;
    /**
     * 新增背景列表
     **/
    @JsonProperty("background_list")
    private List<MeetingBackground> backgroundList;
}
