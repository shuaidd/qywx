package com.github.shuaidd.response.application;

import com.github.shuaidd.dto.workbench.WorkbenchData;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 应用在用户工作台展示的数据响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class WorkbenchDataResponse extends AbstractBaseResponse {

    /**
     * 用户当前展示数据
     */
    private WorkbenchData data;
}

