package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.shuaidd.json.Long2DateDeserializer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 企业微信获客链接详情
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionLink {

    /**
     * 获客链接 ID
     */
    @JsonProperty("link_id")
    private String linkId;

    /**
     * 获客链接名称
     */
    @JsonProperty("link_name")
    private String linkName;

    /**
     * 供客户扫码或点击进入的获客链接地址
     */
    @JsonProperty("url")
    private String url;

    /**
     * 获客链接创建时间
     */
    @JsonProperty("create_time")
    @JsonDeserialize(using = Long2DateDeserializer.class)
    private Date createTime;

    /**
     * 通过该链接添加客户时，是否免于企业成员验证
     */
    @JsonProperty("skip_verify")
    private Boolean skipVerify;

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getSkipVerify() {
        return skipVerify;
    }

    public void setSkipVerify(Boolean skipVerify) {
        this.skipVerify = skipVerify;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("linkId", linkId)
                .append("linkName", linkName)
                .append("url", url)
                .append("createTime", createTime)
                .append("skipVerify", skipVerify)
                .toString();
    }
}