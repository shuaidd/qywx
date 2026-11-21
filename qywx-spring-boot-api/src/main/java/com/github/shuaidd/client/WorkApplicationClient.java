package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
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
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 企业微信应用
 *
 * @author ddshuai
 * date 2022-09-01 09:24
 **/
@FeignClient(name = "WorkApplicationClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface WorkApplicationClient extends CommonClient {

    /**
     * 获取应用
     *
     * @param agentId 应用编号
     * @param app     应用名
     * @return WeChatApplicationResponse
     */
    @GetMapping(value = "agent/get", headers = HEAD)
    WeChatApplicationResponse getApplication(@RequestParam("agentid") String agentId, @RequestParam(HEAD_KEY) String app);

    /**
     * 设置应用
     *
     * @param reuqest 请求
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "agent/set", headers = HEAD)
    BaseResponse applicationSetting(ApplicationSettingReuqest reuqest, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建菜单
     *
     * @param request 请求体
     * @param agentid 应用编号
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "menu/create", headers = HEAD)
    BaseResponse createApplicationButton(ApplicationButtonRequest request, @RequestParam("agentid") String agentid, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取菜单
     *
     * @param agentid 应用编号
     * @param app     应用名
     * @return ApplicationButtonResponse
     */
    @GetMapping(value = "menu/get", headers = HEAD)
    ApplicationButtonResponse getApplicationButtons(@RequestParam("agentid") String agentid, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除菜单
     *
     * @param agentid 应用编号
     * @param app     应用名
     * @return BaseResponse
     */
    @GetMapping(value = "menu/delete", headers = HEAD)
    BaseResponse deleteApplicationButtons(@RequestParam("agentid") String agentid, @RequestParam(HEAD_KEY) String app);

    /**
     * 设置应用在工作台展示的模版
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "agent/set_workbench_template", headers = HEAD)
    BaseResponse setWorkbenchTemplate(SetWorkbenchTemplateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取应用在工作台展示的模版
     *
     * @param request 请求体
     * @param app     应用名
     * @return WorkbenchTemplateResponse
     */
    @PostMapping(value = "agent/get_workbench_template", headers = HEAD)
    WorkbenchTemplateResponse getWorkbenchTemplate(GetWorkbenchTemplateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 设置应用在用户工作台展示的数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "agent/set_workbench_data", headers = HEAD)
    BaseResponse setWorkbenchData(SetWorkbenchDataRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量设置应用在用户工作台展示的数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "agent/batch_set_workbench_data", headers = HEAD)
    BaseResponse batchSetWorkbenchData(BatchSetWorkbenchDataRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取应用在用户工作台展示的数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return WorkbenchDataResponse
     */
    @PostMapping(value = "agent/get_workbench_data", headers = HEAD)
    WorkbenchDataResponse getWorkbenchData(GetWorkbenchDataRequest request, @RequestParam(HEAD_KEY) String app);
}
