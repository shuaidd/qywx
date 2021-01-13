package com.github.shuaidd.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-02 21:36
 **/
public class MobileHashCodeResponse extends AbstractBaseResponse {
    private String hashcode;

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("hashcode", hashcode)
                .toString();
    }
}
