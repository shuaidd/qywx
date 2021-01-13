package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-12 13:26
 **/
public class LivingInfoData {
    private String theme;
    @JsonProperty("living_start")
    private Date livingStart;

    /**
     * 直播时长，单位为秒
     */
    @JsonProperty("living_duration")
    private Integer livingDuration;

    /**
     * 直播的状态，0：待开始，1：直播中，2：已结束，3：已过期，4：已取消
     */
    private Integer status;
    @JsonProperty("reserve_living_start")
    private Date reserveLivingStart;
    @JsonProperty("reserve_living_duration")
    private Integer reserveLivingDuration;
    private String description;
    @JsonProperty("anchor_userid")
    private String anchorUserId;
    @JsonProperty("main_department")
    private Integer mainDepartment;

    /**
     * 观看直播总人数
     */
    @JsonProperty("viewer_num")
    private Integer viewerNum;

    /**
     * comment_num
     */
    @JsonProperty("comment_num")
    private Integer commentNum;

    /**
     * 连麦发言人数
     */
    @JsonProperty("mic_num")
    private Integer micNum;

    /**
     * 是否开启回放，1表示开启，0表示关闭
     */
    @JsonProperty("open_replay")
    private Integer openReplay;

    /**
     * open_replay为1时才返回该字段。0表示生成成功，1表示生成中，2表示回放已删除，3表示生成失败
     */
    @JsonProperty("replay_status")
    private Integer replayStatus;

    /**
     * 直播的类型，0：通用直播，1：小班课，2：大班课，3：企业培训，4：活动直播
     */
    private Integer type;

    /**
     * 推流地址，仅直播类型为活动直播并且直播状态是待开播返回该字段
     */
    @JsonProperty("push_stream_url")
    private String pushStreamUrl;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getReserveLivingStart() {
        return reserveLivingStart;
    }

    public void setReserveLivingStart(Date reserveLivingStart) {
        this.reserveLivingStart = reserveLivingStart;
    }

    public Integer getReserveLivingDuration() {
        return reserveLivingDuration;
    }

    public void setReserveLivingDuration(Integer reserveLivingDuration) {
        this.reserveLivingDuration = reserveLivingDuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnchorUserId() {
        return anchorUserId;
    }

    public void setAnchorUserId(String anchorUserId) {
        this.anchorUserId = anchorUserId;
    }

    public Integer getMainDepartment() {
        return mainDepartment;
    }

    public void setMainDepartment(Integer mainDepartment) {
        this.mainDepartment = mainDepartment;
    }

    public Integer getViewerNum() {
        return viewerNum;
    }

    public void setViewerNum(Integer viewerNum) {
        this.viewerNum = viewerNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getMicNum() {
        return micNum;
    }

    public void setMicNum(Integer micNum) {
        this.micNum = micNum;
    }

    public Integer getOpenReplay() {
        return openReplay;
    }

    public void setOpenReplay(Integer openReplay) {
        this.openReplay = openReplay;
    }

    public Integer getReplayStatus() {
        return replayStatus;
    }

    public void setReplayStatus(Integer replayStatus) {
        this.replayStatus = replayStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPushStreamUrl() {
        return pushStreamUrl;
    }

    public void setPushStreamUrl(String pushStreamUrl) {
        this.pushStreamUrl = pushStreamUrl;
    }
}
