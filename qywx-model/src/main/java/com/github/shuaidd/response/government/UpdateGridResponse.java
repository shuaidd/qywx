package com.github.shuaidd.response.government;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 编辑网格响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class UpdateGridResponse extends AbstractBaseResponse {

    /**
     * 不合法的负责人或成员userid列表
     */
    @JsonProperty("invalid_userids")
    private List<String> invalidUserids;

    public List<String> getInvalidUserids() {
        return invalidUserids;
    }

    public void setInvalidUserids(List<String> invalidUserids) {
        this.invalidUserids = invalidUserids;
    }
}
