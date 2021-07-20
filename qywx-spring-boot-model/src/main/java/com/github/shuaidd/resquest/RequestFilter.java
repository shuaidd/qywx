package com.github.shuaidd.resquest;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-19 09:40
 **/
public class RequestFilter {

    private String key;

    private String value;

    public RequestFilter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
