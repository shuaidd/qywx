package com.github.shuaidd.response.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.FieldGroup;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 获取智能表格编组响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class GetFieldGroupsResponse extends AbstractBaseResponse {

    /**
     * 编组总数
     */
    @JsonProperty("total")
    private Integer total;

    /**
     * 是否还有更多数据
     */
    @JsonProperty("has_more")
    private Boolean hasMore;

    /**
     * 下一页偏移量
     */
    @JsonProperty("next")
    private Integer next;

    /**
     * 编组列表
     */
    @JsonProperty("field_groups")
    private List<FieldGroup> fieldGroups;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public List<FieldGroup> getFieldGroups() {
        return fieldGroups;
    }

    public void setFieldGroups(List<FieldGroup> fieldGroups) {
        this.fieldGroups = fieldGroups;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("total", total)
                .append("hasMore", hasMore)
                .append("next", next)
                .append("fieldGroups", fieldGroups)
                .toString();
    }
}
