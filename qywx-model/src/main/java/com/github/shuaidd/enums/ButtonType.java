package com.github.shuaidd.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ButtonType {
    /**
     * 应用按钮类型
     */
    CLICK("click", "点击推事件"),
    VIEW("view", "跳转URL"),
    SCANCODE_PUSH("scancode_push", "扫码推事件"),
    SCANCODE_WAITMSG("scancode_waitmsg", "扫码推事件 且弹出“消息接收中”提示框"),
    PIC_SYSPHOTO("pic_sysphoto", "弹出系统拍照发图"),
    PIC_PHOTO_OR_ALBUM("pic_photo_or_album", "弹出拍照或者相册发图"),
    PIC_WEIXIN("pic_weixin", "弹出企业微信相册发图器"),
    LOCATION_SELECT("location_select", "弹出地理位置选择器"),
    ;
    private String type;
    private String description;

    ButtonType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
