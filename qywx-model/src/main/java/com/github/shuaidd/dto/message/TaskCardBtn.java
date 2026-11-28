package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-13 13:21
 **/
public class TaskCardBtn {
    private String key;
    private String name;
    private String color;
    @JsonProperty("is_bold")
    private Boolean isBold;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getBold() {
        return isBold;
    }

    public void setBold(Boolean bold) {
        isBold = bold;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TaskCardBtn.class.getSimpleName() + "[", "]")
                .add("key='" + key + "'")
                .add("name='" + name + "'")
                .add("color='" + color + "'")
                .add("isBold=" + isBold)
                .toString();
    }
}
