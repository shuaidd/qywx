package com.github.shuaidd.dto.oa.formcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-16 17:43
 **/
public class AttendanceDateRange {

    private String type;

    @JsonProperty("new_begin")
    private Long newBegin;

    @JsonProperty("new_end")
    private Long newEnd;

    @JsonProperty("new_duration")
    private Long newDuration;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getNewBegin() {
        return newBegin;
    }

    public void setNewBegin(Long newBegin) {
        this.newBegin = newBegin;
    }

    public Long getNewEnd() {
        return newEnd;
    }

    public void setNewEnd(Long newEnd) {
        this.newEnd = newEnd;
    }

    public Long getNewDuration() {
        return newDuration;
    }

    public void setNewDuration(Long newDuration) {
        this.newDuration = newDuration;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AttendanceDateRange.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .add("newBegin=" + newBegin)
                .add("newEnd=" + newEnd)
                .add("newDuration=" + newDuration)
                .toString();
    }
}
