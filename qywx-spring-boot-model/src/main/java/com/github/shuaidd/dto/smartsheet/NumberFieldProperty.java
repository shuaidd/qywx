package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NumberFieldProperty {

    @JsonProperty("decimal_places")
    private Integer decimalPlaces;
    @JsonProperty("use_separate")
    private Boolean useSeparate;
}
