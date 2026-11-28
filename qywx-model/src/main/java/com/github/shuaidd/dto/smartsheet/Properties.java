package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Properties {

    @JsonProperty("title")
    private String title;

    @JsonProperty("index")
    private Integer index;

    @JsonProperty("sheet_id")
    private String sheetId;
}