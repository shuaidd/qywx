package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetSheetRequest {

    /**
     * 文档的docid
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * 指定子表ID查询
     */
    @JsonProperty("sheet_id")
    private String sheetId;
    /**
     * 获取所有类型子表
     */
    @JsonProperty("need_all_type_sheet")
    private Boolean needAllTypeSheet;
}
