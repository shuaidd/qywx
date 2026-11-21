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
 * 设置应用在工作台展示的模版请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@Accessors(chain = true)
public class SetWorkbenchTemplateRequest {

    /**
     * 应用id
     */
    private Integer agentid;

    /**
     * 模版类型；normal表示取消自定义模式
     */
    private WorkbenchDataType type;

    /**
     * 关键数据型模版数据
     */
    @JsonProperty("keydata")
    private WorkbenchKeyData keyData;

    /**
     * 图片型模版数据
     */
    private WorkbenchImage image;

    /**
     * 列表型模版数据
     */
    private WorkbenchList list;

    /**
     * 网页型模版数据
     */
    private WorkbenchWebView webview;

    /**
     * 是否覆盖所有用户当前设置的数据，默认false
     */
    @JsonProperty("replace_user_data")
    private Boolean replaceUserData;
}
