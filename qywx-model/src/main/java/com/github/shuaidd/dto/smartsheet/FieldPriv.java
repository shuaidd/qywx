package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * FieldPriv
 */
@NoArgsConstructor
@Data
public class FieldPriv {
    /**
     * fieldRangeType
     */
    @JsonProperty("field_range_type")
    private Integer fieldRangeType;
    /**
     * fieldRuleList
     */
    @JsonProperty("field_rule_list")
    private List<FieldRuleList> fieldRuleList;
    /**
     * fieldDefaultRule
     */
    @JsonProperty("field_default_rule")
    private FieldRuleList fieldDefaultRule;
}
