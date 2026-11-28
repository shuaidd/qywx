package com.github.shuaidd.resquest.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 员工花名册字段标识
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class StaffFieldId {
    /**
     * 需要获取的员工字段id
     **/
    @JsonProperty("fieldid")
    private Integer fieldId;

    /**
     * 可重复字段组的下标；非可重复字段组填0，不填默认0
     **/
    @JsonProperty("sub_idx")
    private Integer subIdx;
}
