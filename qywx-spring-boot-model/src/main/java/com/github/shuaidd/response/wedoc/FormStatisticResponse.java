package com.github.shuaidd.response.wedoc;

import com.github.shuaidd.dto.wedoc.FormSubmitUser;
import com.github.shuaidd.dto.wedoc.FormUnfillUser;
import com.github.shuaidd.response.AbstractBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.List;

/**
 * 收集表统计信息响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormStatisticResponse extends AbstractBaseResponse {

    /**
     * 已填写次数
     */
    @JsonProperty("fill_cnt")
    private Long fillCnt;

    /**
     * 已填写人数
     */
    @JsonProperty("fill_user_cnt")
    private Long fillUserCnt;

    /**
     * 未填写人数
     */
    @JsonProperty("unfill_user_cnt")
    private Long unfillUserCnt;

    /**
     * 已填写人列表
     */
    @JsonProperty("submit_users")
    private List<FormSubmitUser> submitUsers;

    /**
     * 未填写人列表
     */
    @JsonProperty("unfill_users")
    private List<FormUnfillUser> unfillUsers;

    /**
     * 下次分页拉取使用的游标
     */
    @JsonProperty("cursor")
    private Long cursor;

    public Long getFillCnt() {
        return fillCnt;
    }

    public void setFillCnt(Long fillCnt) {
        this.fillCnt = fillCnt;
    }

    public Long getFillUserCnt() {
        return fillUserCnt;
    }

    public void setFillUserCnt(Long fillUserCnt) {
        this.fillUserCnt = fillUserCnt;
    }

    public Long getUnfillUserCnt() {
        return unfillUserCnt;
    }

    public void setUnfillUserCnt(Long unfillUserCnt) {
        this.unfillUserCnt = unfillUserCnt;
    }

    public List<FormSubmitUser> getSubmitUsers() {
        return submitUsers;
    }

    public void setSubmitUsers(List<FormSubmitUser> submitUsers) {
        this.submitUsers = submitUsers;
    }

    public List<FormUnfillUser> getUnfillUsers() {
        return unfillUsers;
    }

    public void setUnfillUsers(List<FormUnfillUser> unfillUsers) {
        this.unfillUsers = unfillUsers;
    }

    public Long getCursor() {
        return cursor;
    }

    public void setCursor(Long cursor) {
        this.cursor = cursor;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fillCnt", fillCnt)
                .append("fillUserCnt", fillUserCnt)
                .append("unfillUserCnt", unfillUserCnt)
                .append("submitUsers", submitUsers)
                .append("unfillUsers", unfillUsers)
                .append("cursor", cursor)
                .toString();
    }
}
