package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DocumentData
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class DocumentData {
    /**
     * result
     */
    @JsonProperty("result")
    private GridData result;
}
