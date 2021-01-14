package com.github.shuaidd.resquest;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-11 14:44
 **/
public class CursorPageRequest {
    private String cursor;
    private Integer limit = 100;

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
