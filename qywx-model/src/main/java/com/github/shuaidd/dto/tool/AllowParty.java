package com.github.shuaidd.dto.tool;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-06 22:47
 **/
public class AllowParty {
    @JsonProperty("partyid")
    private List<Integer> partys;

    @Override
    public String toString() {
        return new StringJoiner(", ", AllowParty.class.getSimpleName() + "[", "]")
                .add("partys=" + partys)
                .toString();
    }
}
