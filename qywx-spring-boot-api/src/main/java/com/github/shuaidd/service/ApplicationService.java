package com.github.shuaidd.service;

import com.github.shuaidd.response.ApplicationButtonResponse;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.WeChatApplicationResponse;
import com.github.shuaidd.resquest.ApplicationButtonRequest;
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
     * @param agentId 应用ID
     * @param applicationName 应用名称
     * @return WeChatApplicationResponse
     */
    public final WeChatApplicationResponse getApplication(String agentId, String applicationName) {
        checkApplication(applicationName);
        WeChatApplicationResponse response = null;
        if (StringUtils.isNotEmpty(agentId)) {
            response = weChatClient.getApplication(agentId, applicationName);
            if (isSuccess(response)) {
                logger.info("应用获取成功");
            }
        }

        return response;
    }

    /**
     * 创建应用
     *
     * @param request 请求
     * @param agentId 应用ID
     * @param applicationName 应用名称
     */
    public final void createApplicationButton(ApplicationButtonRequest request, String agentId, String applicationName) {
        checkApplication(applicationName);
        if (Objects.nonNull(request) && CollectionUtils.isNotEmpty(request.getButtonList())) {
            BaseResponse response = weChatClient.createApplicationButton(request, agentId, applicationName);
            if (isSuccess(response)) {
                logger.info("应用菜单创建成功:{},{}", agentId, applicationName);
            }
        }
    }

    /**
     * 获取菜单
     *
     * @param agentId 应用ID
     * @param applicationName 应用名称
     * @return ApplicationButtonResponse
     */
    public final ApplicationButtonResponse getApplicationButtons(String agentId, String applicationName) {
        checkApplication(applicationName);
        ApplicationButtonResponse response = null;
        if (StringUtils.isNotEmpty(agentId)) {
            response = weChatClient.getApplicationButtons(agentId, applicationName);
            if (isSuccess(response)) {
                logger.info("应用菜单获取成功:{},{}", agentId, applicationName);
            }
        }
        return response;
    }

    /**
     * 删除菜单
     *
     * @param agentId 应用ID
     * @param applicationName 应用名称
     */
    public final void deleteApplicationButtons(String agentId, String applicationName) {
        checkApplication(applicationName);
        if (StringUtils.isNotEmpty(agentId)) {
            BaseResponse response = weChatClient.deleteApplicationButtons(agentId, applicationName);
            if (isSuccess(response)) {
                logger.info("应用菜单删除成功:{},{}", agentId, applicationName);
            }
        }
    }
}
