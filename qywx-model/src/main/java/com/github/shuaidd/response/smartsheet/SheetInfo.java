package com.github.shuaidd.response.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SheetInfo
 */
@NoArgsConstructor
@Data
public class SheetInfo {
    /**
     * 子表id
     */
    @JsonProperty("sheet_id")
    private String sheetId;
    /**
     * 子表名称
     */
    @JsonProperty("title")
    private String title;
    /**
     * 子表是否可见
     */
    @JsonProperty("is_visible")
    private Boolean isVisible;
    /**
     * 子表类型。"dashboard"仪表盘。"external"说明页，"smartsheet"智能表
     */
    @JsonProperty("type")
    private String type;
}
