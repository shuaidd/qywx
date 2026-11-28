package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.CustomerStrategyTagGroup;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 创建客户联系规则组企业客户标签响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyTagCreateResponse extends AbstractBaseResponse {

    /**
     * 新建或命中的标签组；包含新建标签的 ID
     */
    @JsonProperty("tag_group")
    private CustomerStrategyTagGroup tagGroup;

    public CustomerStrategyTagGroup getTagGroup() {
        return tagGroup;
    }

    public void setTagGroup(CustomerStrategyTagGroup tagGroup) {
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
