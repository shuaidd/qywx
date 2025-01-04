package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@Data
public class ViewProperty {

    @JsonProperty("auto_sort")
    private Boolean autoSort;

    @JsonProperty("sort_spec")
    private SortSpec sortSpec;

    @JsonProperty("filter_spec")
    private FilterSpec filterSpec;

    @JsonProperty("group_spec")
    private GroupSpec groupSpec;

    @JsonProperty("is_field_stat_enabled")
    private Boolean isFieldStatEnabled;

    @JsonProperty("field_visibility")
    private Map<String,Boolean> fieldVisibility;

    @JsonProperty("frozen_field_count")
    private Integer frozenFieldCount;
}
