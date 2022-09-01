package com.github.shuaidd.service;

import com.github.shuaidd.response.application.ApplicationButtonResponse;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.application.WeChatApplicationResponse;
import com.github.shuaidd.resquest.application.ApplicationButtonRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 应用管理
 *
 * @author ddshuai
 * date 2019-04-06 21:53
 **/
@Service
public class ApplicationService extends AbstractBaseService {

    /**
     * 获取应用
     *
     * @param agentId         应用ID
     * @param applicationName 应用名称
     * @return WeChatApplicationResponse
     */
    public final WeChatApplicationResponse getApplication(String agentId, String applicationName) {
        checkApplication(applicationName);
        WeChatApplicationResponse response = null;
        if (StringUtils.isNotEmpty(agentId)) {
            response = applicationClient.getApplication(agentId, applicationName);
            if (isSuccess(response)) {
                logger.info("应用获取成功");
            }
        }

        return response;
    }

    /**
     * 创建应用菜单
     *
     * @param request         请求
     * @param agentId         应用ID
     * @param applicationName 应用名称
     */
    public final void createApplicationButton(ApplicationButtonRequest request, String agentId, String applicationName) {
        checkApplication(applicationName);
        if (Objects.nonNull(request) && CollectionUtils.isNotEmpty(request.getButtonList())) {
            BaseResponse response = applicationClient.createApplicationButton(request, agentId, applicationName);
            if (isSuccess(response)) {
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
    public final ApplicationButtonResponse getApplicationButtons(String agentId, String applicationName) {
        checkApplication(applicationName);
        ApplicationButtonResponse response = null;
        if (StringUtils.isNotEmpty(agentId)) {
            response = applicationClient.getApplicationButtons(agentId, applicationName);
            if (isSuccess(response)) {
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
    public final void deleteApplicationButtons(String agentId, String applicationName) {
        checkApplication(applicationName);
        if (StringUtils.isNotEmpty(agentId)) {
            BaseResponse response = applicationClient.deleteApplicationButtons(agentId, applicationName);
            if (isSuccess(response)) {
                logger.info("应用菜单删除成功:{},{}", agentId, applicationName);
            }
        }
    }
}
