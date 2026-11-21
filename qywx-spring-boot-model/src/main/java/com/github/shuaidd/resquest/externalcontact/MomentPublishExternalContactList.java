package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 企业朋友圈目标客户标签列表
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentPublishExternalContactList {

    /**
     * 可见到该朋友圈的企业客户标签 ID 列表；不支持规则组标签
     */
    @JsonProperty("tag_list")
    private List<String> tagList;

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("tagList", tagList)
                .toString();
    }
}