package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 收集表时长答案
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormDurationReply {

    /**
     * 开始时间戳
     */
    @JsonProperty("begin_time")
    private Long beginTime;

    /**
     * 结束时间戳
     */
    @JsonProperty("end_time")
    private Long endTime;

    /**
     * 时间刻度；1-按天，2-按小时
     */
    @JsonProperty("time_scale")
    private Integer timeScale;

    /**
     * 按小时统计时的单位换算值，表示多少小时/天
     */
    @JsonProperty("day_range")
    private Integer dayRange;

    /**
     * 天数，按天统计时返回
     */
    @JsonProperty("days")
    private Double days;

    /**
     * 小时数，按小时统计时返回
     */
    @JsonProperty("hours")
    private Double hours;

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getTimeScale() {
        return timeScale;
    }

    public void setTimeScale(Integer timeScale) {
        this.timeScale = timeScale;
    }

    public Integer getDayRange() {
        return dayRange;
    }

    public void setDayRange(Integer dayRange) {
        this.dayRange = dayRange;
    }

    public Double getDays() {
        return days;
    }

    public void setDays(Double days) {
        this.days = days;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("beginTime", beginTime)
                .append("endTime", endTime)
                .append("timeScale", timeScale)
                .append("dayRange", dayRange)
                .append("days", days)
                .append("hours", hours)
                .toString();
    }
}
