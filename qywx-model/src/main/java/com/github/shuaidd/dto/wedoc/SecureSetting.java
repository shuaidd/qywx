package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SecureSetting
 */
@NoArgsConstructor
@Data
public class SecureSetting {
    /**
     * enableReadonlyCopy
     */
    @JsonProperty("enable_readonly_copy")
    private Boolean enableReadonlyCopy;
    /**
     * watermark
     */
    @JsonProperty("watermark")
    private Watermark watermark;
    /**
     * enableReadonlyComment
     */
    @JsonProperty("enable_readonly_comment")
    private Boolean enableReadonlyComment;
}
