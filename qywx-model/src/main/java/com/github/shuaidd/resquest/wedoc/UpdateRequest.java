package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedoc.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class UpdateRequest {

    /**
     * 替换指定位置文本内容
     */
    @JsonProperty("replace_text")
    private ReplaceText replaceText;
    /**
     * 删除指定位置内容
     */
    @JsonProperty("delete_content")
    private DeleteContent deleteContent;
    /**
     * 在指定位置插入文本内容
     */
    @JsonProperty("insert_text")
    private InsertText insertText;
    /**
     * 在指定位置插入图片
     */
    @JsonProperty("insert_image")
    private InsertImage insertImage;
    /**
     * 在指定位置插入分页符
     */
    @JsonProperty("insert_page_break")
    private InsertPageBreak insertPageBreak;
    /**
     * 在指定位置插入表格
     */
    @JsonProperty("insert_table")
    private InsertTable insertTable;
    /**
     * 在指定位置插入段落
     */
    @JsonProperty("insert_paragraph")
    private InsertParagraph insertParagraph;
    /**
     * 更新指定位置文本属性
     */
    @JsonProperty("update_text_property")
    private UpdateTextProperty updateTextProperty;
}
