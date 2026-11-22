package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建会议投票响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingPollCreateResponse extends AbstractBaseResponse {

    /**
     * 新创建的投票主题 ID
     **/
    @JsonProperty("poll_theme_id")
    private String pollThemeId;
}
