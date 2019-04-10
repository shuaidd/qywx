package com.github.shuaidd.response;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2019-04-06 16:56
 **/
public class UploadImageResponse extends AbstractBaseResponse {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
