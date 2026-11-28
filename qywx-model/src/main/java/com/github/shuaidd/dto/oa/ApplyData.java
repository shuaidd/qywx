package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.shuaidd.json.ApplyFormControlDeserializer;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-16 13:40
 **/
public class ApplyData {

    @JsonDeserialize(using = ApplyFormControlDeserializer.class)
    private List<ApplyDataContent> contents;

    public List<ApplyDataContent> getContents() {
        return contents;
    }

    public void setContents(List<ApplyDataContent> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApplyData.class.getSimpleName() + "[", "]")
                .add("contents=" + contents)
                .toString();
    }
}
