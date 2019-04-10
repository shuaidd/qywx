package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.AllowParty;
import com.github.shuaidd.dto.AllowTag;
import com.github.shuaidd.dto.AllowUser;

import java.util.StringJoiner;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-06 22:17
 **/
public class WeChatApplicationResponse extends AbstractBaseResponse {

    @JsonProperty("agentid")
    private String agentId;

    private String name;

    @JsonProperty("square_logo_url")
    private String squareLogoUrl;

    private String description;

    private Integer close;

    @JsonProperty("redirect_domain")
    private String redirectDomain;

    @JsonProperty("isreportenter")
    private Integer isReportenter;

    @JsonProperty("home_url")
    private String homeUrl;

    @JsonProperty("allow_userinfos")
    private AllowUser allowUser;

    @JsonProperty("allow_partys")
    private AllowParty allowParty;

    @JsonProperty("allow_tags")
    private AllowTag allowTag;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSquareLogoUrl() {
        return squareLogoUrl;
    }

    public void setSquareLogoUrl(String squareLogoUrl) {
        this.squareLogoUrl = squareLogoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getClose() {
        return close;
    }

    public void setClose(Integer close) {
        this.close = close;
    }

    public String getRedirectDomain() {
        return redirectDomain;
    }

    public void setRedirectDomain(String redirectDomain) {
        this.redirectDomain = redirectDomain;
    }

    public Integer getIsReportenter() {
        return isReportenter;
    }

    public void setIsReportenter(Integer isReportenter) {
        this.isReportenter = isReportenter;
    }

    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    public AllowUser getAllowUser() {
        return allowUser;
    }

    public void setAllowUser(AllowUser allowUser) {
        this.allowUser = allowUser;
    }

    public AllowParty getAllowParty() {
        return allowParty;
    }

    public void setAllowParty(AllowParty allowParty) {
        this.allowParty = allowParty;
    }

    public AllowTag getAllowTag() {
        return allowTag;
    }

    public void setAllowTag(AllowTag allowTag) {
        this.allowTag = allowTag;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WeChatApplicationResponse.class.getSimpleName() + "[", "]")
                .add("agenId='" + agentId + "'")
                .add("name='" + name + "'")
                .add("squareLogoUrl='" + squareLogoUrl + "'")
                .add("description='" + description + "'")
                .add("close=" + close)
                .add("redirectDomain='" + redirectDomain + "'")
                .add("isReportenter=" + isReportenter)
                .add("homeUrl='" + homeUrl + "'")
                .add("allowUser=" + allowUser)
                .add("allowParty=" + allowParty)
                .add("allowTag=" + allowTag)
                .toString();
    }
}
