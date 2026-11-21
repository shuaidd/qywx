package com.github.shuaidd.resquest.application;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取应用在工作台展示的模版请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@Accessors(chain = true)
public class GetWorkbenchTemplateRequest {

    /**
     * 应用id
     */
    private Integer agentid;
}
