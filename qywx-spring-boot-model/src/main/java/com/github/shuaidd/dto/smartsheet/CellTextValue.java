package com.github.shuaidd.dto.smartsheet;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CellTextValue {
    private String type;
    private String text;
    private String link;
}
