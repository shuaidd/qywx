package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-08 18:08
 **/
public class SendMessageResponse extends AbstractBaseResponse {

    @JsonProperty("invaliduser")
    private String inValidUser;

    @JsonProperty("invalidparty")
    private String inValidParty;

    @JsonProperty("invalidtag")
    private String inValidTag;

    public String getInValidUser() {
        return inValidUser;
    }

    public void setInValidUser(String inValidUser) {
        this.inValidUser = inValidUser;
    }

    public String getInValidParty() {
        return inValidParty;
    }

    public void setInValidParty(String inValidParty) {
        this.inValidParty = inValidParty;
    }

    public String getInValidTag() {
        return inValidTag;
    }

    public void setInValidTag(String inValidTag) {
        this.inValidTag = inValidTag;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SendMessageResponse.class.getSimpleName() + "[", "]")
                .add("inValidUser='" + inValidUser + "'")
                .add("inValidParty='" + inValidParty + "'")
                .add("inValidTag='" + inValidTag + "'")
                .toString();
    }
}
