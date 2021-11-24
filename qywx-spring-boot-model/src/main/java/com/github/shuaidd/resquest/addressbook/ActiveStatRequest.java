package com.github.shuaidd.resquest.addressbook;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-11-24 13:12
 **/
public class ActiveStatRequest {

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ActiveStatRequest.class.getSimpleName() + "[", "]")
                .add("date='" + date + "'")
                .toString();
    }
}
