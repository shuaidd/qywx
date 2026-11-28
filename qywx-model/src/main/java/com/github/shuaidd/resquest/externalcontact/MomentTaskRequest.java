package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.resquest.CursorPageRequest;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-05 21:40
 **/
public class MomentTaskRequest extends CursorPageRequest {

    @JsonProperty("moment_id")
    private String momentId;

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }
}
