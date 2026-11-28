package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FieldRuleList
 */
@NoArgsConstructor
@Data
public class FieldRuleList {
    /**
     * fieldId
     */
    @JsonProperty("field_id")
    private String fieldId;
    /**
     * fieldType
     */
    @JsonProperty("field_type")
    private String fieldType;
    /**
     * canEdit
     */
    @JsonProperty("can_edit")
    private Boolean canEdit;
    /**
     * canInsert
     */
    @JsonProperty("can_insert")
    private Boolean canInsert;
    /**
     * canView
     */
    @JsonProperty("can_view")
    private Boolean canView;
}
