package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-03 20:30
 **/
@Setter
@Getter
public abstract class AbstractBaseResponse {

    @JsonProperty("errcode")
    private String errCode;

    @JsonProperty("errmsg")
    private String errMsg;

    @JsonProperty("next_cursor")
    private String nextCursor;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("errCode", errCode)
                .append("errMsg", errMsg)
                .append("nextCursor", nextCursor)
                .toString();
    }
}
