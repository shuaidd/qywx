package com.github.shuaidd.dto.tool;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-11 13:14
 **/
public class Comm {

    @JsonProperty("apply_data")
    private String applyData;

    public String getApplyData() {
        return applyData;
    }

    public void setApplyData(String applyData) {
        this.applyData = applyData;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Comm.class.getSimpleName() + "[", "]")
                .add("applyData='" + applyData + "'")
                .toString();
    }
}
