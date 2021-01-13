package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-02 16:06
 **/
public class CustomListResponse extends AbstractBaseResponse {

    @JsonProperty("external_userid")
    private List<String> externalUserIds;

    public List<String> getExternalUserIds() {
        return externalUserIds;
    }

    public void setExternalUserIds(List<String> externalUserIds) {
        this.externalUserIds = externalUserIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("externalUserIds", externalUserIds)
                .toString();
    }
}
