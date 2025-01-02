package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author ddshuai
 */
@Data
public class CellData {

    @JsonProperty(value = "cell_value")
    private CellValue cellValue;

    @JsonProperty(value = "cell_format")
    private CellFormat cellFormat;
}
