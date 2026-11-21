package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 获取已服务的外部联系人请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class ServedContactListRequest {

    /**
     * 分页查询游标，由上一次调用返回；首次调用可不填，游标具有有效期，请勿缓存后使用
     */
    @JsonProperty("cursor")
    private String cursor;

    /**
     * 返回的最大记录数，整型，默认为1000
     */
    @JsonProperty("limit")
    private Integer limit = 1000;

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("cursor", cursor)
                .append("limit", limit)
                .toString();
    }
}
