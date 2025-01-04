package com.github.shuaidd.response.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class GetSheetResponse extends AbstractBaseResponse {

    /**
     * sheetList
     */
    @JsonProperty("sheet_list")
    private List<SheetInfo> sheetList;
}
