package com.github.shuaidd.dto.wedoc;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author ddshuai
 */
@Data
@Accessors(chain = true)
public class RowData {

    private List<CellData> values;
}
