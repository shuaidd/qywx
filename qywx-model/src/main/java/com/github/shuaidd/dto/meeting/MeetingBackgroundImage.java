package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会议背景图片
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingBackgroundImage {
    /**
     * 背景图片内容的十六进制MD5
     **/
    @JsonProperty("image_md5")
    private String imageMd5;
    /**
     * 背景图片URL
     **/
    @JsonProperty("image_url")
    private String imageUrl;
}
