package com.github.shuaidd.dto.workbench;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 工作台关键数据项
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@Accessors(chain = true)
public class WorkbenchKeyDataItem {

    /**
     * 关键数据名称，不超过64个字符
     */
    private String key;

    /**
     * 关键数据，不超过64个字符
     */
    private String data;

    /**
     * 点击跳转url，不超过1024个字符
     */
    @JsonProperty("jump_url")
    private String jumpUrl;

    /**
     * 小程序页面路径，不超过1024个字符
     */
    private String pagepath;
}
