package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * RecordPriv
 */
@NoArgsConstructor
@Data
public class RecordPriv {
    /**
     * recordRangeType
     */
    @JsonProperty("record_range_type")
    private Integer recordRangeType;
    /**
     * recordRuleList
     */
    @JsonProperty("record_rule_list")
    private List<RecordRuleList> recordRuleList;
}
