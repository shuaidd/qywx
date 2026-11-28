package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DeleteSheetRequest {

    @JsonProperty("docid")
    private String docId;
    @JsonProperty("sheet_id")
    private String sheetId;
}
