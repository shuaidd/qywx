package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-07 21:31
 **/
public class CalendarData {
    @JsonProperty("cal_id")
    private String calId;
    private String organizer;
    private Integer readonly;
    @JsonProperty("set_as_default")
    private Integer setAsDefault;
    private String summary;
    private String color;
    private String description;
    private List<CalendarShare> shares;

    public String getCalId() {
        return calId;
    }

    public void setCalId(String calId) {
        this.calId = calId;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public Integer getReadonly() {
        return readonly;
    }

    public void setReadonly(Integer readonly) {
        this.readonly = readonly;
    }

    public Integer getSetAsDefault() {
        return setAsDefault;
    }

    public void setSetAsDefault(Integer setAsDefault) {
        this.setAsDefault = setAsDefault;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CalendarShare> getShares() {
        return shares;
    }

    public void setShares(List<CalendarShare> shares) {
        this.shares = shares;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("calId", calId)
                .append("organizer", organizer)
                .append("readonly", readonly)
                .append("setAsDefault", setAsDefault)
                .append("summary", summary)
                .append("color", color)
                .append("description", description)
                .append("shares", shares)
                .toString();
    }
}
