package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Map;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SheetRecord {

    @JsonProperty("record_id")
    private String recordId;

    /**
     * 记录的具体内容，key 为字段标题或字段 ID ，value 详见(Value)
     */
    private Map<String,Object> values;

    /**
     * createTime
     */
    @JsonProperty("create_time")
    private Date createTime;
    /**
     * updateTime
     */
    @JsonProperty("update_time")
    private Date updateTime;

    /**
     * creatorName
     */
    @JsonProperty("creator_name")
    private String creatorName;
    /**
     * updaterName
     */
    @JsonProperty("updater_name")
    private String updaterName;
}
