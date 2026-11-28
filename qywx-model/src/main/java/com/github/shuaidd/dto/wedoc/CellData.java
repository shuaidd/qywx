package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ddshuai
 */
@Data
@Accessors(chain = true)
public class CellData {

    @JsonProperty(value = "cell_value")
    private CellValue cellValue;

    @JsonProperty(value = "cell_format")
    private CellFormat cellFormat;
}
