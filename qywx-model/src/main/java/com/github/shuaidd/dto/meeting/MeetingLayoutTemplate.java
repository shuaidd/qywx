package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会议布局模板
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingLayoutTemplate {
    /**
     * 布局模板ID
     **/
    @JsonProperty("layout_template_id")
    private String layoutTemplateId;
    /**
     * 缩略图URL
     **/
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
    /**
     * 布局图URL
     **/
    @JsonProperty("picture_url")
    private String pictureUrl;
    /**
     * 成员个人渲染布局的规则说明
     **/
    @JsonProperty("render_rule")
    private String renderRule;
}
