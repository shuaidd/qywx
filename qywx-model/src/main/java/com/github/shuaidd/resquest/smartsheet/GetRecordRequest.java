package com.github.shuaidd.resquest.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.SortInfo;
import com.github.shuaidd.resquest.OffsetPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class GetRecordRequest extends OffsetPageRequest {

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
     * recordIds
     */
    @JsonProperty("record_ids")
    private List<String> recordIds;
    /**
     * keyType
     */
    @JsonProperty("key_type")
    private String keyType;
    /**
     * fieldTitles
     */
    @JsonProperty("field_titles")
    private List<String> fieldTitles;
    /**
     * fieldIds
     */
    @JsonProperty("field_ids")
    private List<String> fieldIds;
    /**
     * sort
     */
    @JsonProperty("sort")
    private List<SortInfo> sort;
}
