package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-19 10:09
 **/
public class SpRecord {

    @JsonProperty("sp_status")
    private Integer spStatus;

    @JsonProperty("approverattr")
    private Integer approverAttr;

    @JsonProperty("details")
    private List<SpRecordDetail> details;
}
