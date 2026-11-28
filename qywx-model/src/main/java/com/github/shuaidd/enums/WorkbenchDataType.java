package com.github.shuaidd.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 企业微信工作台自定义展示数据类型
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public enum WorkbenchDataType {

    /**
     * 普通模式，仅在设置工作台模版时可用于取消自定义模式
     */
    NORMAL("normal", "普通模式"),

    /**
     * 关键数据型
     */
    KEY_DATA("keydata", "关键数据型"),

    /**
     * 图片型
     */
    IMAGE("image", "图片型"),

    /**
     * 列表型
     */
    LIST("list", "列表型"),

    /**
     * 网页型
     */
    WEBVIEW("webview", "网页型");

    @JsonValue
    private final String type;
    private final String description;

    WorkbenchDataType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
