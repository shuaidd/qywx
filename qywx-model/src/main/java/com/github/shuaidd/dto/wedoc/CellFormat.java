package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ddshuai
 */
@Data
@Accessors(chain = true)
public class CellFormat {

    @JsonProperty(value = "text_format")
    private TextFormat textFormat;
}
