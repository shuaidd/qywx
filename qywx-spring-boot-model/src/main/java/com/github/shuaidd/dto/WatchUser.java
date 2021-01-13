package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-12 16:48
 **/
public class WatchUser {
    @JsonProperty("userid")
    private String userId;
    @JsonProperty("external_userid")
    private String externalUserId;
    private String name;
    @JsonProperty("watch_time")
    private Integer watchTime;
    @JsonProperty("is_comment")
    private Integer isComment;
    @JsonProperty("is_mic")
    private Integer isMic;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(Integer watchTime) {
        this.watchTime = watchTime;
    }

    public Integer getIsComment() {
        return isComment;
    }

    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }

    public Integer getIsMic() {
        return isMic;
    }

    public void setIsMic(Integer isMic) {
        this.isMic = isMic;
    }
}
