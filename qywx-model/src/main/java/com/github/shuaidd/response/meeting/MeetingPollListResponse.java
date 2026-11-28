package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingPollThemeInfo;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 会议投票列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingPollListResponse extends AbstractBaseResponse {

    /**
     * 投票主题及其实例列表
     **/
    @JsonProperty("polls_theme_info")
    private List<MeetingPollThemeInfo> pollsThemeInfo;
}
