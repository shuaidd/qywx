package com.github.shuaidd.service;

import com.github.shuaidd.client.*;
import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.client.config.WeChatConfigurationProperties;
import com.github.shuaidd.exception.ParamCheckException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-04 14:24
 **/
public abstract class AbstractBaseService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected WeChatClient weChatClient;

    @Autowired
    protected ExternalContactClient externalContactClient;

    @Autowired
    protected WorkOaClient workOaClient;

    @Autowired
    protected WeChatCallCenterClient callCenterClient;

    @Autowired
    protected AddressBookClient addressBookClient;

    @Autowired
    protected WeChatMediaClient weChatMediaClient;

    @Autowired
    protected WorkApplicationClient applicationClient;

    @Autowired
    protected MessageClient messageClient;

    @Autowired
    protected EfficiencyToolClient efficiencyToolClient;

    @Autowired
    protected LinkedCorpClient linkedCorpClient;

    @Autowired
    protected ServiceProviderClient serviceProviderClient;

    @Autowired
    protected AuthClient authClient;

    @Autowired
    protected EnterpriseEmailClient emailClient;

    @Autowired
    protected ElectronicInvoiceClient electronicInvoiceClient;

    @Autowired
    protected EnterpriseInterConnectionClient enterpriseInterConnectionClient;

    @Autowired
    protected WeChatConfigurationProperties properties;

    @Autowired
    protected MeetingClient meetingClient;

    @Autowired
    protected FrontEndToolsClient frontEndToolsClient;

    /**
     * 获取应用密匙
     *
     * @param applicationName 应用名称
     * @return String
     */
    String getApplicationSecret(String applicationName) {
        String secret = "";
        List<ApplicationProperties> list = properties.getApplicationList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ApplicationProperties applicationProperties : list) {
                if (Objects.equals(applicationName, applicationProperties.getApplicationName())) {
                    secret = applicationProperties.getSecret();
                }
            }
        }
        if (StringUtils.isEmpty(secret)) {
            //不存在的应用 则抛出异常
            throw new ParamCheckException(applicationName + "应用不存在密匙");
        }

        return secret;
    }

    /**
     * 获取应用配置信息
     *
     * @param applicationName 应用
     * @return config
     */
    ApplicationProperties getApp(String applicationName) {
        List<ApplicationProperties> list = properties.getApplicationList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ApplicationProperties applicationProperties : list) {
                if (Objects.equals(applicationName, applicationProperties.getApplicationName())) {
                    return applicationProperties;
                }
            }
        }

        return null;
    }

    public void checkApplication(String appName) {
        if (StringUtils.isEmpty(appName)) {
            throw new ParamCheckException("调用接口的应用名称不能为空");
        }

        boolean exist = false;
        for (ApplicationProperties applicationProperties : properties.getApplicationList()) {
            if (appName.equals(applicationProperties.getApplicationName())) {
                exist = true;
                break;
            }
        }

        List<String> validAppNames = properties.getApplicationList().stream().map(ApplicationProperties::getApplicationName).collect(Collectors.toList());
        if (!exist) {
            throw new ParamCheckException("应用名称【" + appName + "】无效，不在有效配置列表之内-->" + validAppNames);
        }
    }
}
