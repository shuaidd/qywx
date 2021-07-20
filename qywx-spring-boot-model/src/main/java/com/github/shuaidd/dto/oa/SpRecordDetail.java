package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.UserId;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-19 10:12
 **/
public class SpRecordDetail {

    private UserId approver;
    private String speech;

    @JsonProperty("sptime")
    private Long spTime;

    @JsonProperty("sp_status")
    private Integer spStatus;

    @JsonProperty("media_id")
    private List<String> mediaIds;

    public UserId getApprover() {
        return approver;
    }

    public void setApprover(UserId approver) {
        this.approver = approver;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public Long getSpTime() {
        return spTime;
    }

    public void setSpTime(Long spTime) {
        this.spTime = spTime;
    }

    public Integer getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(Integer spStatus) {
        this.spStatus = spStatus;
    }

    public List<String> getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(List<String> mediaIds) {
        this.mediaIds = mediaIds;
    }
}
