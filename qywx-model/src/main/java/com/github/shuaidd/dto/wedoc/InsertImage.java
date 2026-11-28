package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class InsertImage {

    /**
     * 图片url，通过上传图片接口获得
     */
    @JsonProperty("image_id")
    private String imageId;
    /**
     * 插入的位置
     */
    @JsonProperty("location")
    private Location location;
    /**
     * 图片的宽，单位是像素（px）， 不传默认为图片原始宽度
     */
    @JsonProperty("width")
    private Integer width;
    /**
     * 图片的高， 单位是像素（px），不传默认为图片原始高度
     */
    @JsonProperty("height")
    private Integer height;
}
