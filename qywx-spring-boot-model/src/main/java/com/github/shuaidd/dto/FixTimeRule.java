package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-14 16:18
 **/
public class FixTimeRule {

    @JsonProperty("fix_time_begin_sec")
    private Integer fixTimeBeginSec;

    @JsonProperty("fix_time_end_sec")
    private Integer fixTimeEndSec;

    public Integer getFixTimeBeginSec() {
        return fixTimeBeginSec;
    }

    public void setFixTimeBeginSec(Integer fixTimeBeginSec) {
        this.fixTimeBeginSec = fixTimeBeginSec;
    }

    public Integer getFixTimeEndSec() {
        return fixTimeEndSec;
    }

    public void setFixTimeEndSec(Integer fixTimeEndSec) {
        this.fixTimeEndSec = fixTimeEndSec;
    }
}
