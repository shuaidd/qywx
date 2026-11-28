package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ddshuai
 */
@Data
@Accessors(chain = true)
public class Link {

    /**
     * 链接url
     */
    @JsonProperty("url")
    private String url;
    /**
     * 链接标题
     */
    @JsonProperty("text")
    private String text;
}
