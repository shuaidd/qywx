package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class SheetDataRequest {

    /**
     * 在线表格唯一标识
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * 工作表ID，工作表的唯一标识
     */
    @JsonProperty("sheet_id")
    private String sheetId;
    /**
     * 查询的范围，格式遵循 A1表示法
     */
    @JsonProperty("range")
    private String range;
}
