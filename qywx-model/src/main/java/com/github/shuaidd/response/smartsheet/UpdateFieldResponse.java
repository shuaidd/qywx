package com.github.shuaidd.response.smartsheet;

import com.github.shuaidd.dto.smartsheet.SheetField;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateFieldResponse extends AbstractBaseResponse {

    private List<SheetField> fields;
}
