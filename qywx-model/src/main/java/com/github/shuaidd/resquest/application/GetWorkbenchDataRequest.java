package com.github.shuaidd.resquest.application;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取应用在用户工作台展示的数据请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@Accessors(chain = true)
public class GetWorkbenchDataRequest {

    /**
     * 应用id
     */
    private Integer agentid;

    /**
     * 用户userid
     */
    private String userid;
}
