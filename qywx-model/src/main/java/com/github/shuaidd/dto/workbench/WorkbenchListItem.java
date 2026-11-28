package com.github.shuaidd.dto.workbench;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 工作台列表展示项
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@Accessors(chain = true)
public class WorkbenchListItem {

    /**
     * 列表显示文字，不超过128个字节
     */
    private String title;

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
