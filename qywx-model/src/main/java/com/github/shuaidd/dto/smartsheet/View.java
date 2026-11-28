package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class View {

    @JsonProperty("view_id")
    private String viewId;

    @JsonProperty("view_title")
    private String viewTitle;

    @JsonProperty("view_type")
    private String viewType;

    private ViewProperty property;
}
