package com.github.shuaidd.dto.workbench;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 工作台网页型展示数据
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@Accessors(chain = true)
public class WorkbenchWebView {

    /**
     * 渲染展示的url
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

    /**
     * 高度：single_row、double_row
     */
    private String height;

    /**
     * 是否隐藏标题，默认false
     */
    @JsonProperty("hide_title")
    private Boolean hideTitle;

    /**
     * 是否开启webview内链接跳转，默认false
     */
    @JsonProperty("enable_webview_click")
    private Boolean enableWebviewClick;
}
