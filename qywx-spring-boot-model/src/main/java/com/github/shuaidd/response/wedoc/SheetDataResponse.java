package com.github.shuaidd.response.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedoc.DocumentData;
import com.github.shuaidd.dto.wedoc.GridData;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author ddshuai
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class SheetDataResponse extends AbstractBaseResponse {

    /**
     * data
     */
    @JsonProperty("grid_data")
    private GridData data;
}
