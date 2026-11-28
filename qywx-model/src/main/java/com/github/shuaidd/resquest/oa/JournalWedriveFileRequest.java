package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 汇报下载微盘文件请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class JournalWedriveFileRequest {
    /**
     * 汇报记录id
     **/
    @JsonProperty("journaluuid")
    private String journaluuid;

    /**
     * 微盘fileid；必须是journaluuid对应的汇报关联的wedrive_files中的微盘附件
     **/
    @JsonProperty("fileid")
    private String fileid;
}