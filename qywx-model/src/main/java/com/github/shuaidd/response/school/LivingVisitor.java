package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 观看直播的游客统计
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingVisitor {

    /**
     * 微信昵称
     */
    @JsonProperty("nickname")
    private String nickname;

    /**
     * 观看时长，单位为秒
     */
    @JsonProperty("watch_time")
    private Long watchTime;

    /**
     * 首次进入直播时间戳
     */
    @JsonProperty("enter_time")
    private Long enterTime;

    /**
     * 最后离开直播时间戳
     */
    @JsonProperty("leave_time")
    private Long leaveTime;

    /**
     * 是否评论。1表示评论，0表示没有评论
     */
    @JsonProperty("is_comment")
    private Integer isComment;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(Long watchTime) {
        this.watchTime = watchTime;
    }

    public Long getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Long enterTime) {
        this.enterTime = enterTime;
    }

    public Long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Long leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Integer getIsComment() {
        return isComment;
    }

    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }
}
