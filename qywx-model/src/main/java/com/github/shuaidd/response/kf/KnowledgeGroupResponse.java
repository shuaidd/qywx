package com.github.shuaidd.response.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.kf.KnowledgeGroup;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-01 16:06
 **/
public class KnowledgeGroupResponse {

    @JsonProperty(value = "group_list")
    private List<KnowledgeGroup> list;

    public List<KnowledgeGroup> getList() {
        return list;
    }

    public void setList(List<KnowledgeGroup> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", KnowledgeGroupResponse.class.getSimpleName() + "[", "]")
                .add("list=" + list)
                .toString();
    }
}
