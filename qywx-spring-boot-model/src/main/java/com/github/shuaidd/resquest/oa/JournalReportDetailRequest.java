package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-20 09:47
 **/
public class JournalReportDetailRequest {

    @JsonProperty("journaluuid")
    private String journalId;

    public String getJournalId() {
        return journalId;
    }

    public void setJournalId(String journalId) {
        this.journalId = journalId;
    }
}
