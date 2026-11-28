package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.resquest.OffsetPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class GetViewRequest extends OffsetPageRequest {

    /**
     * docid
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * Smartsheet子表ID
     */
    @JsonProperty("sheet_id")
    private String sheetId;
    /**
     * viewIds
     */
    @JsonProperty("view_ids")
    private List<String> viewIds;
}
