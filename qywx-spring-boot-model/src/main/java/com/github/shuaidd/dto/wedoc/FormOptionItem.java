package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 收集表问题选项
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormOptionItem {

    /**
     * 选项key，从1开始
     */
    @JsonProperty("key")
    private Integer key;

    /**
     * 选项内容
     */
    @JsonProperty("value")
    private String value;

    /**
     * 选项状态；1-正常，2-被删除
     */
    @JsonProperty("status")
    private Integer status;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("key", key)
                .append("value", value)
                .append("status", status)
                .toString();
    }
}
