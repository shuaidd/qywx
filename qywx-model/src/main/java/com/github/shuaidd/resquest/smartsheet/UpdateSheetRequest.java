package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.Properties;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateSheetRequest {

    @JsonProperty("docid")
    private String docId;

    @JsonProperty("properties")
    private Properties properties;
}
