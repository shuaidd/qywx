package com.github.shuaidd.dto.workbench;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.enums.WorkbenchDataType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 应用在工作台展示的数据
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@Accessors(chain = true)
public class WorkbenchData {

    /**
     * 数据类型
     */
    private WorkbenchDataType type;

    /**
     * 关键数据型数据
     */
    @JsonProperty("keydata")
    private WorkbenchKeyData keyData;

    /**
     * 图片型数据
     */
    private WorkbenchImage image;

    /**
     * 列表型数据
     */
    private WorkbenchList list;

    /**
     * 网页型数据
     */
    private WorkbenchWebView webview;
}
