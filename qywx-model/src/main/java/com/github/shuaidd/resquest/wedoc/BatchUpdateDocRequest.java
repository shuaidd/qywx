package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class BatchUpdateDocRequest {

    /**
     * 文档的docid
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * 操作的文档版本, 该参数可以通过获取文档内容接口获得。操作后文档版本将更新一版。要更新的文档版本与最新文档版本相差不能超过100个。
     */
    @JsonProperty("version")
    private Integer version;
    /**
     * 更新操作列表，详见 UpdateRequest
     */
    @JsonProperty("requests")
    private List<UpdateRequest> requests;
}
