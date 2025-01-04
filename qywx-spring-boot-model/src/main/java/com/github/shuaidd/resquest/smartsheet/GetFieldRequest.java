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
public class GetFieldRequest extends OffsetPageRequest {

    /**
     * docid
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * sheetId
     */
    @JsonProperty("sheet_id")
    private String sheetId;
    /**
     * viewId
     */
    @JsonProperty("view_id")
    private String viewId;
    /**
     * 由字段ID组成的JSON数组
     */
    @JsonProperty("field_ids")
    private List<String> fieldIds;
    /**
     * 由字段标题组成的JSON数组
     */
    @JsonProperty("field_titles")
    private List<String> fieldTitles;
}
