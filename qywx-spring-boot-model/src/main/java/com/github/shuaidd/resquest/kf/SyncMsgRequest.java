package com.github.shuaidd.resquest.kf;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-11-25 11:21
 **/
public class SyncMsgRequest {

    private String cursor;

    private String token;

    private Integer limit;

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
