package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 上课直播信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingInfo {

    /**
     * 直播主题
     */
    @JsonProperty("theme")
    private String theme;

    /**
     * 直播开始时间戳
     */
    @JsonProperty("living_start")
    private Long livingStart;

    /**
     * 直播时长，单位为秒
     */
    @JsonProperty("living_duration")
    private Long livingDuration;

    /**
     * 主播的userid
     */
    @JsonProperty("anchor_userid")
    private String anchorUserid;

    /**
     * 直播范围
     */
    @JsonProperty("living_range")
    private LivingRange livingRange;

    /**
     * 观看直播总人数
     */
    @JsonProperty("viewer_num")
    private Integer viewerNum;

    /**
     * 评论数
     */
    @JsonProperty("comment_num")
    private Integer commentNum;

    /**
     * 是否开启回放。1表示开启，0表示关闭
     */
    @JsonProperty("open_replay")
    private Integer openReplay;

    /**
     * 推流地址，仅直播类型为活动直播并且直播状态是待开播时返回
     */
    @JsonProperty("push_stream_url")
    private String pushStreamUrl;

    /**
     * 当前在线观看人数
     */
    @JsonProperty("online_count")
    private Integer onlineCount;

    /**
     * 直播预约人数
     */
    @JsonProperty("subscribe_count")
    private Integer subscribeCount;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Long getLivingStart() {
        return livingStart;
    }

    public void setLivingStart(Long livingStart) {
        this.livingStart = livingStart;
    }

    public Long getLivingDuration() {
        return livingDuration;
    }

    public void setLivingDuration(Long livingDuration) {
        this.livingDuration = livingDuration;
    }

    public String getAnchorUserid() {
        return anchorUserid;
    }

    public void setAnchorUserid(String anchorUserid) {
        this.anchorUserid = anchorUserid;
    }

    public LivingRange getLivingRange() {
        return livingRange;
    }

    public void setLivingRange(LivingRange livingRange) {
        this.livingRange = livingRange;
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

    public Integer getOpenReplay() {
        return openReplay;
    }

    public void setOpenReplay(Integer openReplay) {
        this.openReplay = openReplay;
    }

    public String getPushStreamUrl() {
        return pushStreamUrl;
    }

    public void setPushStreamUrl(String pushStreamUrl) {
        this.pushStreamUrl = pushStreamUrl;
    }

    public Integer getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(Integer onlineCount) {
        this.onlineCount = onlineCount;
    }

    public Integer getSubscribeCount() {
        return subscribeCount;
    }

    public void setSubscribeCount(Integer subscribeCount) {
        this.subscribeCount = subscribeCount;
    }
}
