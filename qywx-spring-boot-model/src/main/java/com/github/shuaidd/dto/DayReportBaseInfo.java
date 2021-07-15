package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.shuaidd.json.Date2LongSerializer;

import java.util.Date;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-15 11:24
 **/
public class DayReportBaseInfo {

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date date;

    @JsonProperty("record_type")
    private Integer recordType;

    private String name;

    @JsonProperty("name_ex")
    private String nameEx;

    @JsonProperty("departs_name")
    private String departsName;

    @JsonProperty("acctid")
    private String userId;

    @JsonProperty("day_type")
    private Integer dayType;

    @JsonProperty("rule_info")
    private DayReportRuleInfo reportRuleInfo;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEx() {
        return nameEx;
    }

    public void setNameEx(String nameEx) {
        this.nameEx = nameEx;
    }

    public String getDepartsName() {
        return departsName;
    }

    public void setDepartsName(String departsName) {
        this.departsName = departsName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getDayType() {
        return dayType;
    }

    public void setDayType(Integer dayType) {
        this.dayType = dayType;
    }

    public DayReportRuleInfo getReportRuleInfo() {
        return reportRuleInfo;
    }

    public void setReportRuleInfo(DayReportRuleInfo reportRuleInfo) {
        this.reportRuleInfo = reportRuleInfo;
    }
}
