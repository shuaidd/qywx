package com.github.shuaidd.response.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.SheetField;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class GetFieldResponse extends AbstractBaseResponse {

    /**
     * total
     */
    @JsonProperty("total")
    private Integer total;
    /**
     * fields
     */
    @JsonProperty("fields")
    private List<SheetField> fields;
}
