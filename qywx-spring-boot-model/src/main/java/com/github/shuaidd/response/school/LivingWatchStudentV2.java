package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 观看直播的学生统计（v2协议）
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingWatchStudentV2 {

    /**
     * 学生的userid
     */
    @JsonProperty("student_userid")
    private String studentUserid;

    /**
     * 学生所在的班级id列表
     */
    @JsonProperty("partyids")
    private List<Integer> partyids;

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

    public String getStudentUserid() {
        return studentUserid;
    }

    public void setStudentUserid(String studentUserid) {
        this.studentUserid = studentUserid;
    }

    public List<Integer> getPartyids() {
        return partyids;
    }

    public void setPartyids(List<Integer> partyids) {
        this.partyids = partyids;
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
