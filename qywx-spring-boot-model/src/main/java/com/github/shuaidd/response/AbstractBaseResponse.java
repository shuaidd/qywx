package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-03 20:30
 **/
public abstract class AbstractBaseResponse {

    @JsonProperty("errcode")
    private String errCode;

    @JsonProperty("errmsg")
    private String errMsg;

    @JsonProperty("next_cursor")
    private String nextCursor;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("errCode", errCode)
                .append("errMsg", errMsg)
                .append("nextCursor", nextCursor)
                .toString();
    }
}
