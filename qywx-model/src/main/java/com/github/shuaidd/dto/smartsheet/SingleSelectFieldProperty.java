package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class SingleSelectFieldProperty {

    @JsonProperty("is_quick_add")
    private Boolean isQuickAdd;
    @JsonProperty("options")
    private List<Option> options;
}
