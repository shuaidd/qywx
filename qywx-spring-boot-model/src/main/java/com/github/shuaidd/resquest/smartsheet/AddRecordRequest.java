package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.SheetRecord;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class AddRecordRequest {

    /**
     * 文档的docid
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * Smartsheet子表ID
     */
    @JsonProperty("sheet_id")
    private String sheetId;
    /**
     * 返回记录中单元格的key类型，默认用标题
     */
    @JsonProperty("key_type")
    private String keyType;
    /**
     * 需要添加的记录的具体内容组成的JSON数组
     */
    @JsonProperty("records")
    private List<SheetRecord> records;
}
