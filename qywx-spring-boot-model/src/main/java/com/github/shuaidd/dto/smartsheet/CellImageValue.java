package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CellImageValue {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("height")
    private Integer height;
}
