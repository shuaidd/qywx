package com.github.shuaidd.response.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-03 06:22
 **/
public class FileShareResponse extends AbstractBaseResponse {

    @JsonProperty("share_url")
    private String shareUrl;

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FileShareResponse.class.getSimpleName() + "[", "]")
                .add("shareUrl='" + shareUrl + "'")
                .toString();
    }
}
