package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 获客链接 ID 列表响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionLinkListResponse extends AbstractBaseResponse {

    /**
     * 当前页获客链接 ID 列表
     */
    @JsonProperty("link_id_list")
    private List<String> linkIdList;

    public List<String> getLinkIdList() {
        return linkIdList;
    }

    public void setLinkIdList(List<String> linkIdList) {
        this.linkIdList = linkIdList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("linkIdList", linkIdList)
                .toString();
    }
}