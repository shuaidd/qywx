package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Properties {

    @JsonProperty("title")
    private String title;

    @JsonProperty("index")
    private Integer index;

    @JsonProperty("sheet_id")
    private String sheetId;
}