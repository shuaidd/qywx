package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.tool.Comm;
import com.github.shuaidd.dto.tool.Expense;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-11 13:07
 **/
public class ApprovalData {

    @JsonProperty("spname")
    private String spName;

    @JsonProperty("apply_name")
    private String applyName;

    @JsonProperty("apply_org")
    private String applyOrg;

    @JsonProperty("approval_name")
    private List<String> approvalName;

    @JsonProperty("notify_name")
    private List<String> notifyName;

    @JsonProperty("sp_status")
    private Integer spStatus;

    @JsonProperty("sp_num")
    private String spNum;

    @JsonProperty("mediaids")
    private List<String> mediaIds;

    @JsonProperty("apply_time")
    private Long applyTime;

    @JsonProperty("apply_user_id")
    private String applyUserId;

    private Expense expense;

    private Comm comm;

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyOrg() {
        return applyOrg;
    }

    public void setApplyOrg(String applyOrg) {
        this.applyOrg = applyOrg;
    }

    public List<String> getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(List<String> approvalName) {
        this.approvalName = approvalName;
    }

    public List<String> getNotifyName() {
        return notifyName;
    }

    public void setNotifyName(List<String> notifyName) {
        this.notifyName = notifyName;
    }

    public Integer getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(Integer spStatus) {
        this.spStatus = spStatus;
    }

    public String getSpNum() {
        return spNum;
    }

    public void setSpNum(String spNum) {
        this.spNum = spNum;
    }

    public List<String> getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(List<String> mediaIds) {
        this.mediaIds = mediaIds;
    }

    public Long getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Long applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Comm getComm() {
        return comm;
    }

    public void setComm(Comm comm) {
        this.comm = comm;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApprovalData.class.getSimpleName() + "[", "]")
                .add("spName='" + spName + "'")
                .add("applyName='" + applyName + "'")
                .add("applyOrg='" + applyOrg + "'")
                .add("approvalName=" + approvalName)
                .add("notifyName=" + notifyName)
                .add("spStatus=" + spStatus)
                .add("spNum='" + spNum + "'")
                .add("mediaIds=" + mediaIds)
                .add("applyTime=" + applyTime)
                .add("applyUserId='" + applyUserId + "'")
                .add("expense=" + expense)
                .add("comm=" + comm)
                .toString();
    }
}
