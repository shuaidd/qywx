package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 收集表设置
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormSetting {

    /**
     * 填写权限；0-所有人，1-企业内指定人/部门，4-家校所有范围
     */
    @JsonProperty("fill_out_auth")
    private Integer fillOutAuth;

    /**
     * 指定的可填写的人/部门
     */
    @JsonProperty("fill_in_range")
    private FormFillRange fillInRange;

    /**
     * 收集表管理员范围
     */
    @JsonProperty("setting_manager_range")
    private FormFillRange settingManagerRange;

    /**
     * 定时重复设置
     */
    @JsonProperty("timed_repeat_info")
    private FormTimedRepeatInfo timedRepeatInfo;

    /**
     * 是否允许每人提交多份，默认false
     */
    @JsonProperty("allow_multi_fill")
    private Boolean allowMultiFill;

    /**
     * 定时关闭时间；定时重复与定时结束互斥，若都填则优先定时重复
     */
    @JsonProperty("timed_finish")
    private Integer timedFinish;

    /**
     * 是否支持匿名填写，默认false
     */
    @JsonProperty("can_anonymous")
    private Boolean canAnonymous;

    /**
     * 是否有回复时提醒，默认false
     */
    @JsonProperty("can_notify_submit")
    private Boolean canNotifySubmit;

    public Integer getFillOutAuth() {
        return fillOutAuth;
    }

    public void setFillOutAuth(Integer fillOutAuth) {
        this.fillOutAuth = fillOutAuth;
    }

    public FormFillRange getFillInRange() {
        return fillInRange;
    }

    public void setFillInRange(FormFillRange fillInRange) {
        this.fillInRange = fillInRange;
    }

    public FormFillRange getSettingManagerRange() {
        return settingManagerRange;
    }

    public void setSettingManagerRange(FormFillRange settingManagerRange) {
        this.settingManagerRange = settingManagerRange;
    }

    public FormTimedRepeatInfo getTimedRepeatInfo() {
        return timedRepeatInfo;
    }

    public void setTimedRepeatInfo(FormTimedRepeatInfo timedRepeatInfo) {
        this.timedRepeatInfo = timedRepeatInfo;
    }

    public Boolean getAllowMultiFill() {
        return allowMultiFill;
    }

    public void setAllowMultiFill(Boolean allowMultiFill) {
        this.allowMultiFill = allowMultiFill;
    }

    public Integer getTimedFinish() {
        return timedFinish;
    }

    public void setTimedFinish(Integer timedFinish) {
        this.timedFinish = timedFinish;
    }

    public Boolean getCanAnonymous() {
        return canAnonymous;
    }

    public void setCanAnonymous(Boolean canAnonymous) {
        this.canAnonymous = canAnonymous;
    }

    public Boolean getCanNotifySubmit() {
        return canNotifySubmit;
    }

    public void setCanNotifySubmit(Boolean canNotifySubmit) {
        this.canNotifySubmit = canNotifySubmit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fillOutAuth", fillOutAuth)
                .append("fillInRange", fillInRange)
                .append("settingManagerRange", settingManagerRange)
                .append("timedRepeatInfo", timedRepeatInfo)
                .append("allowMultiFill", allowMultiFill)
                .append("timedFinish", timedFinish)
                .append("canAnonymous", canAnonymous)
                .append("canNotifySubmit", canNotifySubmit)
                .toString();
    }
}
