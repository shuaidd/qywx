package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ddshuai
 */
@Data
public class BatchUpdateSheetRequest {

    /**
     * 文档的docid
     */
    @JsonProperty("docid")
    private String docId;

    private List<UpdateSheetRequest> requests;
}
