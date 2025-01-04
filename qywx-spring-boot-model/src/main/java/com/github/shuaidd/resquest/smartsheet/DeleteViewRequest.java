package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class DeleteViewRequest {

    @JsonProperty("docid")
    private String docId;

    @JsonProperty("sheet_id")
    private String sheetId;

    @JsonProperty("view_ids")
    private List<String> viewIds;
}
