package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NumberRule {

    @JsonProperty("type")
    private String type;
    @JsonProperty("value")
    private String value;
}
