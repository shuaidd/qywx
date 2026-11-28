package com.github.shuaidd.response.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * @author ddshuai
 */
public class ShareDocResponse extends AbstractBaseResponse {

    /**
     * 文档分享链接
     */
    @JsonProperty("share_url")
    private String shareUrl;

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }
}
