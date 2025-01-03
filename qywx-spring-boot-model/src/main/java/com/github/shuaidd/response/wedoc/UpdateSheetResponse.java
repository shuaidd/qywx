package com.github.shuaidd.response.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedoc.SheetProperties;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author ddshuai
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UpdateSheetResponse extends AbstractBaseResponse {

    /**
     * addSheetResponse
     */
    @JsonProperty("add_sheet_response")
    private AddSheetResponse addSheetResponse;
    /**
     * updateRangeResponse
     */
    @JsonProperty("update_range_response")
    private UpdateRangeResponse updateRangeResponse;
    /**
     * deleteDimensionResponse
     */
    @JsonProperty("delete_dimension_response")
    private DeleteDimensionResponse deleteDimensionResponse;
    /**
     * deleteSheetResponse
     */
    @JsonProperty("delete_sheet_response")
    private DeleteSheetResponse deleteSheetResponse;

    @NoArgsConstructor
    @Data
    static class AddSheetResponse {

        /**
         * properties
         */
        @JsonProperty("properties")
        private SheetProperties properties;
    }

    @NoArgsConstructor
    @Data
    static class DeleteSheetResponse {

        /**
         * sheetId
         */
        @JsonProperty("sheet_id")
        private String sheetId;
    }

    @NoArgsConstructor
    @Data
    static class UpdateRangeResponse {

        /**
         * 数据更新的成功的单元格数量
         */
        @JsonProperty("updated_cells")
        private Integer updatedCells;
    }

    @NoArgsConstructor
    @Data
    static class DeleteDimensionResponse {

        /**
         * 被删除的行数（或列数）
         */
        @JsonProperty("deleted")
        private Integer deleted;
    }
}
