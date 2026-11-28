package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CellAttachmentValue {

    /**
     * 文件类型，用于区分文件夹和文件
     */
    @JsonProperty("doc_type")
    private Integer docType;
    /**
     * 文件扩展名。文件夹为空，文件为对应文件拓展名，收集表为FORM，文档为DOC，表格为SHEET，幻灯片为SLIDE，思维导图为MIND，流程图为FLOWCHART，智能表为SMARTSHEET
     */
    @JsonProperty("file_ext")
    private String fileExt;
    /**
     * 文件ID
     */
    @JsonProperty("file_id")
    private String fileId;
    /**
     * 文件类型。文件夹为Folder，微盘文件为Wedrive，收集表为30，文档为50，表格是51，幻灯片为52，思维导图为54，流程图为55，智能表为70
     */
    @JsonProperty("file_type")
    private String fileType;
    /**
     * 文件url ，如果是微盘文档则通过获取分享链接获得，如果是文档，则为文档url
     */
    @JsonProperty("file_url")
    private String fileUrl;
    /**
     * 文件名
     */
    @JsonProperty("name")
    private String name;
    /**
     * 文件大小
     */
    @JsonProperty("size")
    private Integer size;
}
