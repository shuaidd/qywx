package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-06 07:58
 **/
public class TagUserResponse extends AbstractBaseResponse {

    @JsonProperty("invalidlist")
    private String inValidList;

    @JsonProperty("invalidparty")
    private List<Integer> inValidParty;

    public String getInValidList() {
        return inValidList;
    }

    public void setInValidList(String inValidList) {
        this.inValidList = inValidList;
    }

    public List<Integer> getInValidParty() {
        return inValidParty;
    }

    public void setInValidParty(List<Integer> inValidParty) {
        this.inValidParty = inValidParty;
    }
}
