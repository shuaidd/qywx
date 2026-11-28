package com.github.shuaidd.dto.tool;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-11 13:46
 **/
public class ReminderData {
    @JsonProperty("is_remind")
    private Integer isRemind;
    @JsonProperty("remind_before_event_secs")
    private Integer remindBeforeEventSecs;
    @JsonProperty("is_repeat")
    private Integer isRepeat;
    @JsonProperty("repeat_type")
    private Integer repeatType;
    @JsonProperty("repeat_until")
    private Date repeatUntil;
    @JsonProperty("is_custom_repeat")
    private Integer isCustomRepeat;
    @JsonProperty("repeat_interval")
    private Integer repeatInterval;
    @JsonProperty("repeat_day_of_week")
    private List<Integer> repeatDayOfWeek;
    @JsonProperty("repeat_day_of_month")
    private List<Integer> repeatDayOfMonth;
    private Integer timezone;

    public Integer getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(Integer isRemind) {
        this.isRemind = isRemind;
    }

    public Integer getRemindBeforeEventSecs() {
        return remindBeforeEventSecs;
    }

    public void setRemindBeforeEventSecs(Integer remindBeforeEventSecs) {
        this.remindBeforeEventSecs = remindBeforeEventSecs;
    }

    public Integer getIsRepeat() {
        return isRepeat;
    }

    public void setIsRepeat(Integer isRepeat) {
        this.isRepeat = isRepeat;
    }

    public Integer getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(Integer repeatType) {
        this.repeatType = repeatType;
    }

    public Date getRepeatUntil() {
        return repeatUntil;
    }

    public void setRepeatUntil(Date repeatUntil) {
        this.repeatUntil = repeatUntil;
    }

    public Integer getIsCustomRepeat() {
        return isCustomRepeat;
    }

    public void setIsCustomRepeat(Integer isCustomRepeat) {
        this.isCustomRepeat = isCustomRepeat;
    }

    public Integer getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public List<Integer> getRepeatDayOfWeek() {
        return repeatDayOfWeek;
    }

    public void setRepeatDayOfWeek(List<Integer> repeatDayOfWeek) {
        this.repeatDayOfWeek = repeatDayOfWeek;
    }

    public List<Integer> getRepeatDayOfMonth() {
        return repeatDayOfMonth;
    }

    public void setRepeatDayOfMonth(List<Integer> repeatDayOfMonth) {
        this.repeatDayOfMonth = repeatDayOfMonth;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }
}
