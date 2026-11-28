package com.github.shuaidd.dto.wedoc;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ddshuai
 */
@Data
@Accessors(chain = true)
public class CellValue {

    private Link link;

    private String text;
}
