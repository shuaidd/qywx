package com.github.shuaidd.response.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-19 09:43
 **/
public class SpNoResponse extends AbstractBaseResponse {

    @JsonProperty("sp_no_list")
    private List<String> spNoList;

    public List<String> getSpNoList() {
        return spNoList;
    }

    public void setSpNoList(List<String> spNoList) {
        this.spNoList = spNoList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SpNoResponse.class.getSimpleName() + "[", "]")
                .add("spNoList=" + spNoList)
                .toString();
    }
}
