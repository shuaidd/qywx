package com.github.shuaidd.response.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-02 17:54
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class DownloadFileResponse extends AbstractBaseResponse {

    @JsonProperty("download_url")
    private String downloadUrl;

    @JsonProperty("cookie_name")
    private String cookieName;

    @JsonProperty("cookie_value")
    private String cookieValue;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DownloadFileResponse.class.getSimpleName() + "[", "]")
                .add("downloadUrl='" + downloadUrl + "'")
                .add("cookieName='" + cookieName + "'")
                .add("cookieValue='" + cookieValue + "'")
                .toString();
    }
}
