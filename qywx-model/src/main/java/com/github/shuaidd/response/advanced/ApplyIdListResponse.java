package com.github.shuaidd.response.advanced;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 批量获取申请单ID响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ApplyIdListResponse extends AbstractBaseResponse {
    /**
     * 分页请求的下一页游标
     **/
    @JsonProperty("next_cursor")
    private String nextCursor;

    /**
     * 申请id列表
     **/
    @JsonProperty("apply_id_list")
    private List<String> applyIdList;

    /**
     * 是否还有下一页数据
     **/
    @JsonProperty("has_more")
    private Boolean hasMore;
}