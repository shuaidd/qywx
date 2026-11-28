package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class DeleteDimension {

    /**
     * 工作表唯一标识
     */
    @JsonProperty("sheet_id")
    private String sheetId;
    /**
     * 声明删除的维度为行或者列。详见 Dimension
     */
    @JsonProperty("dimension")
    private String dimension;
    /**
     * 删除行列的起始序号（从1开始）
     */
    @JsonProperty("start_index")
    private Integer startIndex;
    /**
     * 删除行列的终止序号（从1开始）
     */
    @JsonProperty("end_index")
    private Integer endIndex;
}
