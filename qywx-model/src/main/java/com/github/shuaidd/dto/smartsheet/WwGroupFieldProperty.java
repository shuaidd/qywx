package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WwGroupFieldProperty {

    @JsonProperty("allow_multiple")
    private Boolean allowMultiple;
}
