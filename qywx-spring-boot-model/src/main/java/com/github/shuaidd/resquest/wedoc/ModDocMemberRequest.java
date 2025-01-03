package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedoc.DocMember;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class ModDocMemberRequest {

    /**
     * 操作的文档id
     */
    @JsonProperty("docid")
    private String docId;
    /**
     * 更新文档通知范围的列表, 批次大小最大100
     */
    @JsonProperty("update_file_member_list")
    private List<DocMember> updateFileMemberList;
    /**
     * 删除的文档通知范围列表，批次大小最大一百
     */
    @JsonProperty("del_file_member_list")
    private List<DocMember> delFileMemberList;
}
