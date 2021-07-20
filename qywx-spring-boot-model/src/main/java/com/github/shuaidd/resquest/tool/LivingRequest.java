package com.github.shuaidd.resquest.tool;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.tool.ActivityDetail;

import java.util.Date;

/**
 * 描述
 * @author ddshuai
 * date 2021-01-11 22:08
 **/
public class LivingRequest {

    @JsonProperty("livingid")
    private String livingId;

    @JsonProperty("anchor_userid")
    private String anchorUserId;

    private String theme;

    @JsonProperty("living_start")
    private Date livingStart;

    @JsonProperty("living_duration")
    private Integer livingDuration;

    private String description;
    private Integer type;

    @JsonProperty("agentid")
    private String agentId;

    @JsonProperty("remind_time")
    private Integer remindTime;

    @JsonProperty("activity_cover_mediaid")
    private String activityCoverMediaId;

    @JsonProperty("activity_share_mediaid")
    private String activityShareMediaId;

    @JsonProperty("activity_detail")
    private ActivityDetail activityDetail;

    public String getAnchorUserId() {
        return anchorUserId;
    }

    public void setAnchorUserId(String anchorUserId) {
        this.anchorUserId = anchorUserId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getLivingStart() {
        return livingStart;
    }

    public void setLivingStart(Date livingStart) {
        this.livingStart = livingStart;
    }

    public Integer getLivingDuration() {
        return livingDuration;
    }

    public void setLivingDuration(Integer livingDuration) {
        this.livingDuration = livingDuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public Integer getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Integer remindTime) {
        this.remindTime = remindTime;
    }

    public String getActivityCoverMediaId() {
        return activityCoverMediaId;
    }

    public void setActivityCoverMediaId(String activityCoverMediaId) {
        this.activityCoverMediaId = activityCoverMediaId;
    }

    public String getActivityShareMediaId() {
        return activityShareMediaId;
    }

    public void setActivityShareMediaId(String activityShareMediaId) {
        this.activityShareMediaId = activityShareMediaId;
    }

    public ActivityDetail getActivityDetail() {
        return activityDetail;
    }

    public void setActivityDetail(ActivityDetail activityDetail) {
        this.activityDetail = activityDetail;
    }

    public String getLivingId() {
        return livingId;
    }

    public void setLivingId(String livingId) {
        this.livingId = livingId;
    }
}
