package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class DeleteSheet {

    /**
     * 工作表唯一标识
     */
    @JsonProperty("sheet_id")
    private String sheetId;
}
