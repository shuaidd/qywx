package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class AutoNumberFieldProperty {

    @JsonProperty("type")
    private String type;
    @JsonProperty("reformat_existing_record")
    private Boolean reformatExistingRecord;
    @JsonProperty("rules")
    private List<NumberRule> rules;
}
