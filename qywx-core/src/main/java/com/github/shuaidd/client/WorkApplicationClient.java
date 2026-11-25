package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.application.ApplicationButtonResponse;
import com.github.shuaidd.response.application.WeChatApplicationResponse;
import com.github.shuaidd.response.application.WorkbenchDataResponse;
import com.github.shuaidd.response.application.WorkbenchTemplateResponse;
import com.github.shuaidd.resquest.application.ApplicationButtonRequest;
import com.github.shuaidd.resquest.application.ApplicationSettingReuqest;
import com.github.shuaidd.resquest.application.BatchSetWorkbenchDataRequest;
import com.github.shuaidd.resquest.application.GetWorkbenchDataRequest;
import com.github.shuaidd.resquest.application.GetWorkbenchTemplateRequest;
import com.github.shuaidd.resquest.application.SetWorkbenchDataRequest;
import com.github.shuaidd.resquest.application.SetWorkbenchTemplateRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 企业微信应用
 *
 * @author ddshuai
 * date 2022-09-01 09:24
 **/
public interface WorkApplicationClient extends CommonClient {

    /**
     * 获取应用
     *
     * @param agentId 应用编号
     * @param app     应用名
     * @return WeChatApplicationResponse
     */
    @RequestLine("GET /agent/get?agentid={agentid}&app={app}")
    @Headers(HEAD)
    WeChatApplicationResponse getApplication(@Param("agentid") String agentId, @Param("app") String app);

    /**
     * 设置应用
     *
     * @param reuqest 请求
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /agent/set?app={app}")
    @Headers(HEAD)
    BaseResponse applicationSetting(ApplicationSettingReuqest reuqest, @Param("app") String app);

    /**
     * 创建菜单
     *
     * @param request 请求体
     * @param agentid 应用编号
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /menu/create?agentid={agentid}&app={app}")
    @Headers(HEAD)
    BaseResponse createApplicationButton(ApplicationButtonRequest request, @Param("agentid") String agentid, @Param("app") String app);

    /**
     * 获取菜单
     *
     * @param agentid 应用编号
     * @param app     应用名
     * @return ApplicationButtonResponse
     */
    @RequestLine("GET /menu/get?agentid={agentid}&app={app}")
    @Headers(HEAD)
    ApplicationButtonResponse getApplicationButtons(@Param("agentid") String agentid, @Param("app") String app);

    /**
     * 删除菜单
     *
     * @param agentid 应用编号
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("GET /menu/delete?agentid={agentid}&app={app}")
    @Headers(HEAD)
    BaseResponse deleteApplicationButtons(@Param("agentid") String agentid, @Param("app") String app);

    /**
     * 设置应用在工作台展示的模版
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /agent/set_workbench_template?app={app}")
    @Headers(HEAD)
    BaseResponse setWorkbenchTemplate(SetWorkbenchTemplateRequest request, @Param("app") String app);

    /**
     * 获取应用在工作台展示的模版
     *
     * @param request 请求体
     * @param app     应用名
     * @return WorkbenchTemplateResponse
     */
    @RequestLine("POST /agent/get_workbench_template?app={app}")
    @Headers(HEAD)
    WorkbenchTemplateResponse getWorkbenchTemplate(GetWorkbenchTemplateRequest request, @Param("app") String app);

    /**
     * 设置应用在用户工作台展示的数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /agent/set_workbench_data?app={app}")
    @Headers(HEAD)
    BaseResponse setWorkbenchData(SetWorkbenchDataRequest request, @Param("app") String app);

    /**
     * 批量设置应用在用户工作台展示的数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /agent/batch_set_workbench_data?app={app}")
    @Headers(HEAD)
    BaseResponse batchSetWorkbenchData(BatchSetWorkbenchDataRequest request, @Param("app") String app);

    /**
     * 获取应用在用户工作台展示的数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return WorkbenchDataResponse
     */
    @RequestLine("POST /agent/get_workbench_data?app={app}")
    @Headers(HEAD)
    WorkbenchDataResponse getWorkbenchData(GetWorkbenchDataRequest request, @Param("app") String app);
}
