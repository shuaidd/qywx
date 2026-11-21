package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 收集表已提交用户
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormSubmitUser {

    /**
     * 企业内成员的id，匿名填写时不返回
     */
    @JsonProperty("userid")
    private String userid;

    /**
     * 外部用户临时id，匿名填写时不返回
     */
    @JsonProperty("tmp_external_userid")
    private String tmpExternalUserId;

    /**
     * 提交时间戳
     */
    @JsonProperty("submit_time")
    private Long submitTime;

    /**
     * 答案id
     */
    @JsonProperty("answer_id")
    private Long answerId;

    /**
     * 填写人名字，匿名填写时不返回
     */
    @JsonProperty("user_name")
    private String userName;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTmpExternalUserId() {
        return tmpExternalUserId;
    }

    public void setTmpExternalUserId(String tmpExternalUserId) {
        this.tmpExternalUserId = tmpExternalUserId;
    }

    public Long getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Long submitTime) {
        this.submitTime = submitTime;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userid", userid)
                .append("tmpExternalUserId", tmpExternalUserId)
                .append("submitTime", submitTime)
                .append("answerId", answerId)
                .append("userName", userName)
                .toString();
    }
}
