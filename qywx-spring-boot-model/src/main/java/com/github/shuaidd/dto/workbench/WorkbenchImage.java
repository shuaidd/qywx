package com.github.shuaidd.dto.workbench;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 工作台图片型展示数据
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@Accessors(chain = true)
public class WorkbenchImage {

    /**
     * 图片url，最佳比例为3.35:1
     */
    private String url;

    /**
     * 点击跳转url
     */
    @JsonProperty("jump_url")
    private String jumpUrl;

    /**
     * 小程序页面路径
     */
    private String pagepath;
}
