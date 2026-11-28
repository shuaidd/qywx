package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 班级群创建方式响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class ChatCreateModeResponse extends AbstractBaseResponse {

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
