package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.CustomerStrategyTagGroup;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 客户联系规则组企业客户标签列表响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyTagListResponse extends AbstractBaseResponse {

    /**
     * 标签组列表
     */
    @JsonProperty("tag_group")
    private List<CustomerStrategyTagGroup> tagGroup;

    public List<CustomerStrategyTagGroup> getTagGroup() {
        return tagGroup;
    }

    public void setTagGroup(List<CustomerStrategyTagGroup> tagGroup) {
        this.tagGroup = tagGroup;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("tagGroup", tagGroup)
                .toString();
    }
}
