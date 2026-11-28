package com.github.shuaidd.dto.oa.formcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-16 16:54
 **/
public class DateFormControlData {

    private String type;

    @JsonProperty("s_timestamp")
    private String timestamp;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DateFormControlData.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .add("timestamp='" + timestamp + "'")
                .toString();
    }
}
