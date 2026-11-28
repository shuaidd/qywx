package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author ddshuai
 */
@Setter
@Getter
@Accessors(chain = true)
public class CreateDocRequest {

    /**
     * 空间spaceid。若指定spaceid，则fatherid也要同时指定
     */
    @JsonProperty("spaceid")
    private String spaceId;
    /**
     * 父目录fileid, 在根目录时为空间spaceid
     */
    @JsonProperty("fatherid")
    private String fatherId;
    /**
     * 文档类型, 3:文档 4:表格 10:智能表格
     */
    @JsonProperty("doc_type")
    private Integer docType;
    /**
     * 文档名字（注意：文件名最多填255个字符, 超过255个字符会被截断）
     */
    @JsonProperty("doc_name")
    private String docName;
    /**
     * 文档管理员userid
     */
    @JsonProperty("admin_users")
    private List<String> adminUsers;

}
