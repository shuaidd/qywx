package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.ViewProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateViewRequest {

    @JsonProperty("docid")
    private String docId;

    @JsonProperty("sheet_id")
    private String sheetId;

    @JsonProperty("view_id")
    private String viewId;

    @JsonProperty("view_title")
    private String viewTitle;

    private ViewProperty property;
}
