package com.github.shuaidd.client.config;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-20 16:22
 **/
public class CallbackProperties {

    private String applicationName;

    private String token;

    private String encodingAesKey;

    private String receiveId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    public void setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
