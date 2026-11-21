package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 收集表定时重复设置
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormTimedRepeatInfo {

    /**
     * 是否开启定时重复
     */
    @JsonProperty("enable")
    private Boolean enable;

    /**
     * 每周几重复，按bit组合；仅在按周重复时填写
     */
    @JsonProperty("week_flag")
    private Integer weekFlag;

    /**
     * 第一次提醒的时间戳
     */
    @JsonProperty("remind_time")
    private Long remindTime;

    /**
     * 重复类型；0-每周，1-每天，2-每月
     */
    @JsonProperty("repeat_type")
    private Integer repeatType;

    /**
     * 自动跳过节假日；仅在按天重复时填写
     */
    @JsonProperty("skip_holiday")
    private Boolean skipHoliday;

    /**
     * 每月的第几天，取值1到31；仅在按月重复时填写
     */
    @JsonProperty("day_of_month")
    private Integer dayOfMonth;

    /**
     * 补填规则；0-允许，1-仅当天，2-最后五天内，3-一个月内，4-下一次生成前
     */
    @JsonProperty("fork_finish_type")
    private Integer forkFinishType;

    /**
     * 规则创建时间戳
     */
    @JsonProperty("rule_ctime")
    private Long ruleCtime;

    /**
     * 规则修改时间戳
     */
    @JsonProperty("rule_mtime")
    private Long ruleMtime;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getWeekFlag() {
        return weekFlag;
    }

    public void setWeekFlag(Integer weekFlag) {
        this.weekFlag = weekFlag;
    }

    public Long getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Long remindTime) {
        this.remindTime = remindTime;
    }

    public Integer getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(Integer repeatType) {
        this.repeatType = repeatType;
    }

    public Boolean getSkipHoliday() {
        return skipHoliday;
    }

    public void setSkipHoliday(Boolean skipHoliday) {
        this.skipHoliday = skipHoliday;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Integer getForkFinishType() {
        return forkFinishType;
    }

    public void setForkFinishType(Integer forkFinishType) {
        this.forkFinishType = forkFinishType;
    }

    public Long getRuleCtime() {
        return ruleCtime;
    }

    public void setRuleCtime(Long ruleCtime) {
        this.ruleCtime = ruleCtime;
    }

    public Long getRuleMtime() {
        return ruleMtime;
    }

    public void setRuleMtime(Long ruleMtime) {
        this.ruleMtime = ruleMtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("enable", enable)
                .append("weekFlag", weekFlag)
                .append("remindTime", remindTime)
                .append("repeatType", repeatType)
                .append("skipHoliday", skipHoliday)
                .append("dayOfMonth", dayOfMonth)
                .append("forkFinishType", forkFinishType)
                .append("ruleCtime", ruleCtime)
                .append("ruleMtime", ruleMtime)
                .toString();
    }
}
