package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SheetPrivInfo {

    /**
     * sheetId
     */
    @JsonProperty("sheet_id")
    private String sheetId;
    /**
     * priv
     */
    @JsonProperty("priv")
    private Integer priv;
    /**
     * canInsertRecord
     */
    @JsonProperty("can_insert_record")
    private Boolean canInsertRecord;
    /**
     * canDeleteRecord
     */
    @JsonProperty("can_delete_record")
    private Boolean canDeleteRecord;
    /**
     * canCreateModifyDeleteView
     */
    @JsonProperty("can_create_modify_delete_view")
    private Boolean canCreateModifyDeleteView;
    /**
     * fieldPriv
     */
    @JsonProperty("field_priv")
    private FieldPriv fieldPriv;
    /**
     * recordPriv
     */
    @JsonProperty("record_priv")
    private RecordPriv recordPriv;
    /**
     * clear
     */
    @JsonProperty("clear")
    private Boolean clear;
}
