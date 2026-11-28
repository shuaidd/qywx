package com.github.shuaidd.response.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 高级功能账号列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class VipAccountListResponse extends AbstractBaseResponse {
    /**
     * 是否还有更多数据未获取
     **/
    @JsonProperty("has_more")
    private Boolean hasMore;

    /**
     * 下一次请求的分页游标
     **/
    @JsonProperty("next_cursor")
    private String nextCursor;

    /**
     * 符合条件的企业成员userid列表
     **/
    @JsonProperty("userid_list")
    private List<String> useridList;
}
