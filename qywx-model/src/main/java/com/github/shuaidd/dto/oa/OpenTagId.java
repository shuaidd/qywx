package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述 新通教育
 *
 * @author ddshuai
 * date 2021-07-20 10:10
 **/
public class OpenTagId {

    @JsonProperty("open_tagid")
    private String openTagId;

    public String getOpenTagId() {
        return openTagId;
    }

    public void setOpenTagId(String openTagId) {
        this.openTagId = openTagId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OpenTagId.class.getSimpleName() + "[", "]")
                .add("openTagId='" + openTagId + "'")
                .toString();
    }
}
