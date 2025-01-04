package com.github.shuaidd.response.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.SheetRecord;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetRecordResponse extends AbstractBaseResponse {

    /**
     * total
     */
    @JsonProperty("total")
    private Integer total;
    /**
     * next
     */
    @JsonProperty("next")
    private Integer next;

    private List<SheetRecord> records;
}
