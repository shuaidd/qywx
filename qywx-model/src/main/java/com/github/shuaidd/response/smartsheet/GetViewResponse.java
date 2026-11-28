package com.github.shuaidd.response.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.smartsheet.View;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class GetViewResponse extends AbstractBaseResponse {

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
    /**
     * views
     */
    @JsonProperty("views")
    private List<View> views;
}
