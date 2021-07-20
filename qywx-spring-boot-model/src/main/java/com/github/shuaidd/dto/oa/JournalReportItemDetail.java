package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-20 10:18
 **/
public class JournalReportItemDetail {

    @JsonProperty("journaluuid")
    private String journalUuid;

    @JsonProperty("reportime")
    private Long reportTime;

    private Integer flag;

    public String getJournalUuid() {
        return journalUuid;
    }

    public void setJournalUuid(String journalUuid) {
        this.journalUuid = journalUuid;
    }

    public Long getReportTime() {
        return reportTime;
    }

    public void setReportTime(Long reportTime) {
        this.reportTime = reportTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JournalReportItemDetail.class.getSimpleName() + "[", "]")
                .add("journalUuid='" + journalUuid + "'")
                .add("reportTime=" + reportTime)
                .add("flag=" + flag)
                .toString();
    }
}
