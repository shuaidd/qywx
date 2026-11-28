package com.github.shuaidd.service;

import com.github.shuaidd.QywxContext;
import com.github.shuaidd.client.WorkApplicationClient;
import com.github.shuaidd.dto.workbench.WorkbenchImage;
import com.github.shuaidd.dto.workbench.WorkbenchKeyData;
import com.github.shuaidd.dto.workbench.WorkbenchList;
import com.github.shuaidd.dto.workbench.WorkbenchWebView;
import com.github.shuaidd.enums.WorkbenchDataType;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.application.ApplicationButtonResponse;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.application.WeChatApplicationResponse;
import com.github.shuaidd.response.application.WorkbenchDataResponse;
import com.github.shuaidd.response.application.WorkbenchTemplateResponse;
import com.github.shuaidd.resquest.application.ApplicationButtonRequest;
import com.github.shuaidd.resquest.application.BatchSetWorkbenchDataRequest;
import com.github.shuaidd.resquest.application.GetWorkbenchDataRequest;
import com.github.shuaidd.resquest.application.GetWorkbenchTemplateRequest;
import com.github.shuaidd.resquest.application.SetWorkbenchDataRequest;
import com.github.shuaidd.resquest.application.SetWorkbenchTemplateRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 应用管理
 *
 * @author ddshuai
 * date 2019-04-06 21:53
 **/
public class ApplicationService extends AbstractBaseService {

    private final WorkApplicationClient applicationClient;

    public ApplicationService(QywxContext context, WorkApplicationClient applicationClient) {
        super(context);
        this.applicationClient = applicationClient;
    }

    /**
     * 获取应用
     *
     * @param agentId         应用ID
     * @param applicationName 应用名称
     * @return WeChatApplicationResponse
     */
    public WeChatApplicationResponse getApplication(String agentId, String applicationName) {
        if (StringUtils.isNotEmpty(agentId)) {
            return applicationClient.getApplication(agentId, applicationName);
        }

        throw new ParamCheckException("agentId 不能为空，请检查！！！");
    }

    /**
     * 创建应用菜单
     *
     * @param request         请求
     * @param agentId         应用ID
     * @param applicationName 应用名称
     */
    public void createApplicationButton(ApplicationButtonRequest request, String agentId, String applicationName) {
        if (Objects.nonNull(request) && CollectionUtils.isNotEmpty(request.getButtonList())) {
            applicationClient.createApplicationButton(request, agentId, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("应用菜单创建成功:{},{}", agentId, applicationName);
            }
        }
    }

    /**
     * 获取菜单
     *
     * @param agentId         应用ID
     * @param applicationName 应用名称
     * @return ApplicationButtonResponse
     */
    public ApplicationButtonResponse getApplicationButtons(String agentId, String applicationName) {

        ApplicationButtonResponse response = null;
        if (StringUtils.isNotEmpty(agentId)) {
            response = applicationClient.getApplicationButtons(agentId, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("应用菜单获取成功:{},{}", agentId, applicationName);
            }
        }
        return response;
    }

    /**
     * 删除菜单
     *
     * @param agentId         应用ID
     * @param applicationName 应用名称
     */
    public void deleteApplicationButtons(String agentId, String applicationName) {
        if (StringUtils.isNotEmpty(agentId)) {
            applicationClient.deleteApplicationButtons(agentId, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("应用菜单删除成功:{},{}", agentId, applicationName);
            }
        } else {
            throw new ParamCheckException("agentId 不能为空，请检查！！！");
        }
    }

    /**
     * 设置应用在工作台展示的模版
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return BaseResponse
     */
    public BaseResponse setWorkbenchTemplate(SetWorkbenchTemplateRequest request, String applicationName) {
        if (Objects.isNull(request) || Objects.isNull(request.getAgentid()) || Objects.isNull(request.getType())) {
            throw new ParamCheckException("agentid、type 不能为空，请检查！！！");
        }
        if (request.getType() != WorkbenchDataType.NORMAL) {
            validateWorkbenchPayload(request.getType(), request.getKeyData(), request.getImage(), request.getList(), request.getWebview());
        }
        BaseResponse response = applicationClient.setWorkbenchTemplate(request, applicationName);
        if (logger.isInfoEnabled()) {
            logger.info("应用工作台模版设置成功:{},{}", request.getAgentid(), applicationName);
        }
        return response;
    }

    /**
     * 获取应用在工作台展示的模版
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return WorkbenchTemplateResponse
     */
    public WorkbenchTemplateResponse getWorkbenchTemplate(GetWorkbenchTemplateRequest request, String applicationName) {
        if (Objects.isNull(request) || Objects.isNull(request.getAgentid())) {
            throw new ParamCheckException("agentid 不能为空，请检查！！！");
        }
        return applicationClient.getWorkbenchTemplate(request, applicationName);
    }

    /**
     * 设置应用在用户工作台展示的数据
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return BaseResponse
     */
    public BaseResponse setWorkbenchData(SetWorkbenchDataRequest request, String applicationName) {
        if (Objects.isNull(request) || Objects.isNull(request.getAgentid()) || StringUtils.isEmpty(request.getUserid()) || Objects.isNull(request.getType())) {
            throw new ParamCheckException("agentid、userid、type 不能为空，请检查！！！");
        }
        if (request.getType() == WorkbenchDataType.NORMAL) {
            throw new ParamCheckException("用户工作台数据不支持 normal 类型，请检查！！！");
        }
        validateWorkbenchPayload(request.getType(), request.getKeyData(), request.getImage(), request.getList(), request.getWebview());
        BaseResponse response = applicationClient.setWorkbenchData(request, applicationName);
        if (logger.isInfoEnabled()) {
            logger.info("用户工作台数据设置成功:{},{},{}", request.getAgentid(), request.getUserid(), applicationName);
        }
        return response;
    }

    /**
     * 批量设置应用在用户工作台展示的数据
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return BaseResponse
     */
    public BaseResponse batchSetWorkbenchData(BatchSetWorkbenchDataRequest request, String applicationName) {
        if (Objects.isNull(request) || Objects.isNull(request.getAgentid()) || Objects.isNull(request.getData()) || Objects.isNull(request.getData().getType())) {
            throw new ParamCheckException("agentid、data、type 不能为空，请检查！！！");
        }
        if (CollectionUtils.isEmpty(request.getUseridList())) {
            throw new ParamCheckException("userid_list 不能为空，请检查！！！");
        }
        if (request.getUseridList().size() > 1000) {
            throw new ParamCheckException("userid_list 最多支持1000个用户，请检查！！！");
        }
        if (request.getData().getType() == WorkbenchDataType.NORMAL) {
            throw new ParamCheckException("用户工作台数据不支持 normal 类型，请检查！！！");
        }
        validateWorkbenchPayload(request.getData().getType(), request.getData().getKeyData(), request.getData().getImage(), request.getData().getList(), request.getData().getWebview());
        BaseResponse response = applicationClient.batchSetWorkbenchData(request, applicationName);
        if (logger.isInfoEnabled()) {
            logger.info("批量用户工作台数据设置成功:{},{},{}", request.getAgentid(), request.getUseridList().size(), applicationName);
        }
        return response;
    }

    /**
     * 获取应用在用户工作台展示的数据
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return WorkbenchDataResponse
     */
    public WorkbenchDataResponse getWorkbenchData(GetWorkbenchDataRequest request, String applicationName) {
        if (Objects.isNull(request) || Objects.isNull(request.getAgentid()) || StringUtils.isEmpty(request.getUserid())) {
            throw new ParamCheckException("agentid、userid 不能为空，请检查！！！");
        }
        return applicationClient.getWorkbenchData(request, applicationName);
    }

    private void validateWorkbenchPayload(WorkbenchDataType type, WorkbenchKeyData keyData, WorkbenchImage image, WorkbenchList list, WorkbenchWebView webview) {
        if (type == WorkbenchDataType.KEY_DATA) {
            if (Objects.isNull(keyData) || CollectionUtils.isEmpty(keyData.getItems())) {
                throw new ParamCheckException("keydata.items 不能为空，请检查！！！");
            }
            if (keyData.getItems().size() > 4) {
                throw new ParamCheckException("keydata.items 最多支持4个，请检查！！！");
            }
            boolean invalid = keyData.getItems().stream().anyMatch(item -> Objects.isNull(item) || StringUtils.isEmpty(item.getData()));
            if (invalid) {
                throw new ParamCheckException("keydata.items.data 不能为空，请检查！！！");
            }
        } else if (type == WorkbenchDataType.IMAGE) {
            if (Objects.isNull(image) || StringUtils.isEmpty(image.getUrl())) {
                throw new ParamCheckException("image.url 不能为空，请检查！！！");
            }
        } else if (type == WorkbenchDataType.LIST) {
            if (Objects.isNull(list) || CollectionUtils.isEmpty(list.getItems())) {
                throw new ParamCheckException("list.items 不能为空，请检查！！！");
            }
            if (list.getItems().size() > 3) {
                throw new ParamCheckException("list.items 最多支持3个，请检查！！！");
            }
            boolean invalid = list.getItems().stream().anyMatch(item -> Objects.isNull(item) || StringUtils.isEmpty(item.getTitle()));
            if (invalid) {
                throw new ParamCheckException("list.items.title 不能为空，请检查！！！");
            }
        } else if (type == WorkbenchDataType.WEBVIEW) {
            if (Objects.isNull(webview) || StringUtils.isEmpty(webview.getUrl())) {
                throw new ParamCheckException("webview.url 不能为空，请检查！！！");
            }
        }
    }
}
