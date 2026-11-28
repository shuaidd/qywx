package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.SheetField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class UpdateFieldRequest {

    @JsonProperty("docid")
    private String docId;
    @JsonProperty("sheet_id")
    private String sheetId;
    @JsonProperty("fields")
    private List<SheetField> fields;
}
