package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class SheetRecord {

    @JsonProperty("record_id")
    private String recordId;

    /**
     * 记录的具体内容，key 为字段标题或字段 ID ，value 详见(Value)
     */
    private Map<String,Object> values;
}
