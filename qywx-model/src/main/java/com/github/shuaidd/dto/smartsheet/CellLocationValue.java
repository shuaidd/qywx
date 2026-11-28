package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CellLocationValue {

    /**
     * 地点ID
     */
    @JsonProperty("id")
    private String id;
    /**
     * 纬度
     */
    @JsonProperty("latitude")
    private String latitude;
    /**
     * 经度
     */
    @JsonProperty("longitude")
    private String longitude;
    /**
     * 填1，表示来源为腾讯地图。目前只支持腾讯地图来源
     */
    @JsonProperty("source_type")
    private Integer sourceType;
    /**
     * 地点名称
     */
    @JsonProperty("title")
    private String title;
}
