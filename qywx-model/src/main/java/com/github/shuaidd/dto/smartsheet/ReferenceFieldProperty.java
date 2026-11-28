package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReferenceFieldProperty {

    @JsonProperty("sub_id")
    private String subId;
    @JsonProperty("filed_id")
    private String filedId;
    @JsonProperty("is_multiple")
    private Boolean isMultiple;
    @JsonProperty("view_id")
    private String viewId;
}
