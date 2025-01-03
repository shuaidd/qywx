package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Watermark
 */
@NoArgsConstructor
@Data
public class Watermark {
    /**
     * marginType
     */
    @JsonProperty("margin_type")
    private Integer marginType;
    /**
     * showVisitorName
     */
    @JsonProperty("show_visitor_name")
    private Boolean showVisitorName;
    /**
     * showText
     */
    @JsonProperty("show_text")
    private Boolean showText;
    /**
     * text
     */
    @JsonProperty("text")
    private String text;
}
