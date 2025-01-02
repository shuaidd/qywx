package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author ddshuai
 */
@Data
public class CellFormat {

    @JsonProperty(value = "text_format")
    private TextFormat textFormat;
}
