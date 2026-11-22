package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 设置班级群创建方式的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SetChatCreateModeRequest {

    /**
     * 班级群创建模式：0-自动创建；1-手动创建
     */
    @JsonProperty("create_mode")
    private Integer createMode;

    public Integer getCreateMode() {
        return createMode;
    }

    public void setCreateMode(Integer createMode) {
        this.createMode = createMode;
    }
}
