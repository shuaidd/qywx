package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
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
