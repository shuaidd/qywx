package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * MemberRange
 */
@NoArgsConstructor
@Data
public class MemberRange {
    /**
     * useridList
     */
    @JsonProperty("userid_list")
    private List<String> useridList;
}
