package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 会议投票主题及其投票实例
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingPollThemeInfo {

    /**
     * 投票主题 ID
     **/
    @JsonProperty("poll_theme_id")
    private String pollThemeId;

    /**
     * 主题下的投票实例列表
     **/
    @JsonProperty("polls_info")
    private List<MeetingPollInfo> pollsInfo;
}
