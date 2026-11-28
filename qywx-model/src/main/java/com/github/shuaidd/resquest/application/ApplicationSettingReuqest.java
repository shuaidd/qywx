package com.github.shuaidd.resquest.application;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-08 16:03
 **/
public class ApplicationSettingReuqest {

    @JsonProperty("agentid")
    private String agentId;

    private String name;

    private String description;

    @JsonProperty("redirect_domain")
    private String redirectDomain;

    @JsonProperty("isreportenter")
    private Integer isReportenter;

    @JsonProperty("home_url")
    private String homeUrl;

    @JsonProperty("report_location_flag")
    private Integer reportLocationFlag;

    @JsonProperty("logo_mediaid")
    private String logoMediaId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getReportLocationFlag() {
        return reportLocationFlag;
    }

    public void setReportLocationFlag(Integer reportLocationFlag) {
        this.reportLocationFlag = reportLocationFlag;
    }

    public String getLogoMediaId() {
        return logoMediaId;
    }

    public void setLogoMediaId(String logoMediaId) {
        this.logoMediaId = logoMediaId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApplicationSettingReuqest.class.getSimpleName() + "[", "]")
                .add("agentId='" + agentId + "'")
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("redirectDomain='" + redirectDomain + "'")
                .add("isReportenter=" + isReportenter)
                .add("homeUrl='" + homeUrl + "'")
                .add("reportLocationFlag=" + reportLocationFlag)
                .add("logoMediaId='" + logoMediaId + "'")
                .toString();
    }
}
