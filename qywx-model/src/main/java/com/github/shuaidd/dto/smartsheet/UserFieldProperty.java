package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserFieldProperty {

    @JsonProperty("is_multiple")
    private Boolean isMultiple;
    @JsonProperty("is_notified")
    private Boolean isNotified;
}
