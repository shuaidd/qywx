package com.github.shuaidd.resquest.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.workbench.WorkbenchImage;
import com.github.shuaidd.dto.workbench.WorkbenchKeyData;
import com.github.shuaidd.dto.workbench.WorkbenchList;
import com.github.shuaidd.dto.workbench.WorkbenchWebView;
import com.github.shuaidd.enums.WorkbenchDataType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 设置应用在用户工作台展示的数据请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@Accessors(chain = true)
public class SetWorkbenchDataRequest {

    /**
     * 应用id
     */
    private Integer agentid;

    /**
     * 用户userid
     */
    private String userid;

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
