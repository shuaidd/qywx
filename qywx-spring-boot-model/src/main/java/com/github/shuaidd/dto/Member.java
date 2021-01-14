package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-05 21:10
 **/
public class Member {

    @JsonProperty("userid")
    private String userId;

    private Integer type;

    @JsonProperty("unionid")
    private String unionId;

    @JsonProperty("join_time")
    private Date joinTime;

    /**
     * 入群方式。
     * 1 - 由成员邀请入群（直接邀请入群）
     * 2 - 由成员邀请入群（通过邀请链接入群）
     * 3 - 通过扫描群二维码入群
     */
    @JsonProperty("join_scene")
    private Integer joinScene;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Integer getJoinScene() {
        return joinScene;
    }

    public void setJoinScene(Integer joinScene) {
        this.joinScene = joinScene;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("type", type)
                .append("unionId", unionId)
                .append("joinTime", joinTime)
                .append("joinScene", joinScene)
                .toString();
    }
}
