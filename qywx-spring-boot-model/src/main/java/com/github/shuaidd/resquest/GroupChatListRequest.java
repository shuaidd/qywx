package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.OwnerFilter;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-05 20:52
 **/
public class GroupChatListRequest {
    private String cursor;
    private Integer limit;

    @JsonProperty("status_filter")
    private Integer statusFilter;

    @JsonProperty("owner_filter")
    private OwnerFilter ownerFilter;

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

    public Integer getStatusFilter() {
        return statusFilter;
    }

    public void setStatusFilter(Integer statusFilter) {
        this.statusFilter = statusFilter;
    }

    public OwnerFilter getOwnerFilter() {
        return ownerFilter;
    }

    public void setOwnerFilter(OwnerFilter ownerFilter) {
        this.ownerFilter = ownerFilter;
    }
}
