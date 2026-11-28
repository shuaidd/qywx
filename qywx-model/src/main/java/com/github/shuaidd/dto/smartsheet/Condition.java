package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Condition {

    @JsonProperty("field_id")
    private String fieldId;

    @JsonProperty("operator")
    private String operator;

    @JsonProperty("string_value")
    private ConditionStringValue stringValue;

    @JsonProperty("user_value")
    private ConditionStringValue userValue;

    @JsonProperty("number_value")
    private ConditionNumValue numberValue;

    @JsonProperty("bool_value")
    private ConditionBooleanValue boolValue;
    
    @JsonProperty("date_time_value")
    private ConditionDateValue dateTimeValue;
}
