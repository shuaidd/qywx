package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 收集表答案记录
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormAnswerRecord {

    /**
     * 答案id
     */
    @JsonProperty("answer_id")
    private Long answerId;

    /**
     * 用户名
     */
    @JsonProperty("user_name")
    private String userName;

    /**
     * 创建时间戳
     */
    @JsonProperty("ctime")
    private Long ctime;

    /**
     * 修改时间戳
     */
    @JsonProperty("mtime")
    private Long mtime;

    /**
     * 该用户的答案明细
     */
    @JsonProperty("reply")
    private FormAnswerReply reply;

    /**
     * 答案状态；1-正常，3-统计者移除或删除
     */
    @JsonProperty("answer_status")
    private Integer answerStatus;

    /**
     * 外部用户临时id，匿名填写时不返回
     */
    @JsonProperty("tmp_external_userid")
    private String tmpExternalUserId;

    /**
     * 企业成员id，匿名填写时不返回
     */
    @JsonProperty("userid")
    private String userid;

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

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getMtime() {
        return mtime;
    }

    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }

    public FormAnswerReply getReply() {
        return reply;
    }

    public void setReply(FormAnswerReply reply) {
        this.reply = reply;
    }

    public Integer getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(Integer answerStatus) {
        this.answerStatus = answerStatus;
    }

    public String getTmpExternalUserId() {
        return tmpExternalUserId;
    }

    public void setTmpExternalUserId(String tmpExternalUserId) {
        this.tmpExternalUserId = tmpExternalUserId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("answerId", answerId)
                .append("userName", userName)
                .append("ctime", ctime)
                .append("mtime", mtime)
                .append("reply", reply)
                .append("answerStatus", answerStatus)
                .append("tmpExternalUserId", tmpExternalUserId)
                .append("userid", userid)
                .toString();
    }
}
