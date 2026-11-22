package com.github.shuaidd.response.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.security.FileOperRecord;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件操作记录列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class FileOperRecordListResponse extends AbstractBaseResponse {
    /**
     * 文件操作记录列表
     **/
    @JsonProperty("record_list")
    private List<FileOperRecord> recordList;
}
