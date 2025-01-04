package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CurrencyFieldProperty {

    @JsonProperty("currency_type")
    private String currencyType;
    @JsonProperty("use_separate")
    private Boolean useSeparate;
    @JsonProperty("decimal_places")
    private Integer decimalPlaces;
}
