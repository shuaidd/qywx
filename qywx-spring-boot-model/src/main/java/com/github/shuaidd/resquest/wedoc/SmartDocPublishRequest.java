package com.github.shuaidd.resquest.wedoc;

import com.github.shuaidd.dto.wedoc.SmartDocPublishAuth;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 发布智能文档或修改发布范围请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SmartDocPublishRequest {

    /**
     * 智能文档的文档 ID
     */
    @JsonProperty("docid")
    private String docId;

    /**
     * 发布范围：1-企业内可见，3-企业内外可见，4-指定成员可见
     */
    @JsonProperty("publish_range")
    private Integer publishRange;

    /**
     * 指定成员可见时的可见权限列表
     */
    @JsonProperty("auth_list")
    private List<SmartDocPublishAuth> authList;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public Integer getPublishRange() {
        return publishRange;
    }

    public void setPublishRange(Integer publishRange) {
        this.publishRange = publishRange;
    }

    public List<SmartDocPublishAuth> getAuthList() {
        return authList;
    }

    public void setAuthList(List<SmartDocPublishAuth> authList) {
        this.authList = authList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("docId", docId)
                .append("publishRange", publishRange)
                .append("authList", authList)
                .toString();
    }
}