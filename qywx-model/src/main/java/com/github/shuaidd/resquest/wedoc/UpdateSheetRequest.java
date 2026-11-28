package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedoc.AddSheet;
import com.github.shuaidd.dto.wedoc.DeleteDimension;
import com.github.shuaidd.dto.wedoc.DeleteSheet;
import com.github.shuaidd.dto.wedoc.UpdateSheetRange;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class UpdateSheetRequest {

    /**
     * 新增工作表
     */
    @JsonProperty("add_sheet_request")
    private AddSheet addSheetRequest;
    /**
     * 删除工作表
     */
    @JsonProperty("delete_sheet_request")
    private DeleteSheet deleteSheetRequest;
    /**
     * 更新范围内单元格内容
     */
    @JsonProperty("update_range_request")
    private UpdateSheetRange updateRangeRequest;
    /**
     * 删除表格连续的行或列
     */
    @JsonProperty("delete_dimension_request")
    private DeleteDimension deleteDimensionRequest;
}
