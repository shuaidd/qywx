package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.MomentStrategyRange;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 客户朋友圈规则组管理范围响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentStrategyRangeResponse extends AbstractBaseResponse {

    /**
     * 管理范围内的成员和部门节点列表
     */
    @JsonProperty("range")
    private List<MomentStrategyRange> range;

    public List<MomentStrategyRange> getRange() {
        return range;
    }

    public void setRange(List<MomentStrategyRange> range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("range", range)
                .toString();
    }
}
