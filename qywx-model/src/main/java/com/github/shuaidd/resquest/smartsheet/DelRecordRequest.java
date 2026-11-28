package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class DelRecordRequest {

    /**
     * 文档的docid
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * Smartsheet 子表ID
     */
    @JsonProperty("sheet_id")
    private String sheetId;
    /**
     * 要删除的记录 ID
     */
    @JsonProperty("record_ids")
    private List<String> recordIds;
}
