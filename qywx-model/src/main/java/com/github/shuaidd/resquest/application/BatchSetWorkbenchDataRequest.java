package com.github.shuaidd.resquest.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.workbench.WorkbenchData;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 批量设置应用在用户工作台展示的数据请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@Accessors(chain = true)
public class BatchSetWorkbenchDataRequest {

    /**
     * 应用id
     */
    private Integer agentid;

    /**
     * 用户userid列表，最多1000个
     */
    @JsonProperty("userid_list")
    private List<String> useridList;

    /**
     * 用户展示数据
     */
    private WorkbenchData data;
}
