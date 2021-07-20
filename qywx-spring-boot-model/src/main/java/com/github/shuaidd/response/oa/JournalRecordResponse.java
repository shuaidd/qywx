package com.github.shuaidd.response.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-20 09:27
 **/
public class JournalRecordResponse extends AbstractBaseResponse {

    @JsonProperty("journaluuid_list")
    private List<String> lists;

    /**
     * 0代表还有数据，1代表已无数据
     */
    @JsonProperty("endflag")
    private Integer endFlag;

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public Integer getEndFlag() {
        return endFlag;
    }

    public void setEndFlag(Integer endFlag) {
        this.endFlag = endFlag;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JournalRecordResponse.class.getSimpleName() + "[", "]")
                .add("lists=" + lists)
                .add("endFlag=" + endFlag)
                .toString();
    }
}
