package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class SortSpec {

    @JsonProperty("sort_infos")
    private List<SortInfo> sortInfos;
}
