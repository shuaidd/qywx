package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DateTimeFieldProperty {

    @JsonProperty("format")
    private String format;
    @JsonProperty("auto_fill")
    private Boolean autoFill;
}
