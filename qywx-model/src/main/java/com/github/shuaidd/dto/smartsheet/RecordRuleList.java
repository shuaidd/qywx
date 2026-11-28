package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * RecordRuleList
 */
@NoArgsConstructor
@Data
public class RecordRuleList {
    /**
     * fieldId
     */
    @JsonProperty("field_id")
    private String fieldId;
    /**
     * operType
     */
    @JsonProperty("oper_type")
    private Integer operType;
    /**
     * fieldType
     */
    @JsonProperty("field_type")
    private String fieldType;
    /**
     * value
     */
    @JsonProperty("value")
    private List<String> value;
    /**
     * otherPriv
     */
    @JsonProperty("other_priv")
    private Integer otherPriv;
}
