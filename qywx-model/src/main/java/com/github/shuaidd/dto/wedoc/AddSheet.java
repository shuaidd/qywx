package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddSheet {

    /**
     * 工作表名称
     */
    @JsonProperty("title")
    private String title;
    /**
     * 新增工作表的初始行数
     */
    @JsonProperty("row_count")
    private Integer rowCount;
    /**
     * 新增工作表的初始列数
     */
    @JsonProperty("column_count")
    private Integer columnCount;
}
