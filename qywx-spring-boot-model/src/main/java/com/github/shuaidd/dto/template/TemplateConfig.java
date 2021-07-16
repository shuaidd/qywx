package com.github.shuaidd.dto.template;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-15 15:44
 **/
public class TemplateConfig {

    private TemplateSelector selector;

    private TemplateDate date;

    private TemplateContact contact;

    private TemplateTable table;

    private TemplateAttendance attendance;

    public TemplateSelector getSelector() {
        return selector;
    }

    public void setSelector(TemplateSelector selector) {
        this.selector = selector;
    }

    public TemplateDate getDate() {
        return date;
    }

    public void setDate(TemplateDate date) {
        this.date = date;
    }

    public TemplateContact getContact() {
        return contact;
    }

    public void setContact(TemplateContact contact) {
        this.contact = contact;
    }

    public TemplateTable getTable() {
        return table;
    }

    public void setTable(TemplateTable table) {
        this.table = table;
    }

    public TemplateAttendance getAttendance() {
        return attendance;
    }

    public void setAttendance(TemplateAttendance attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TemplateConfig.class.getSimpleName() + "[", "]")
                .add("selector=" + selector)
                .toString();
    }
}
