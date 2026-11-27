package com.github.shuaidd.service;

import com.github.shuaidd.QywxContext;
import com.github.shuaidd.client.AddressBookClient;
import com.github.shuaidd.client.LinkedCorpClient;
import com.github.shuaidd.client.WeChatClient;
import com.github.shuaidd.client.WorkOaClient;
import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.exception.ParamCheckException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 描述 service 层基类：持有 5.x 中由 Spring 容器字段注入的 4 个基础 client 与配置
 * <p>
 * 由 5.x 的同名抽象类迁移而来。依赖改为构造器注入 {@link QywxContext}（字段名与 5.x 一致，子类无需改动）；
 * {@code getApplicationSecret}、{@code getApp}、{@code checkApplication} 的逻辑与 5.x 逐一对应、保持包内可见，
 * 只把 Java 16+ 的 {@code Stream.toList()} 换成 {@code Collectors.toList()} 以守住 Java 8 语法基线。
 *
 * @author ddshuai
 * date 2019-04-04 14:24
 **/
public abstract class AbstractBaseService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final WeChatClient weChatClient;

    protected final WorkOaClient workOaClient;

    protected final AddressBookClient addressBookClient;

    protected final LinkedCorpClient linkedCorpClient;

    protected final QywxConfig properties;

    protected AbstractBaseService(QywxContext context) {
        Objects.requireNonNull(context, "context");
        this.properties = context.properties();
        this.weChatClient = context.weChatClient();
        this.workOaClient = context.workOaClient();
        this.addressBookClient = context.addressBookClient();
        this.linkedCorpClient = context.linkedCorpClient();
    }

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

        List<String> validAppNames = properties.getApplicationList().stream()
                .map(ApplicationProperties::getApplicationName)
                .collect(Collectors.toList());
        if (!exist) {
            throw new ParamCheckException("应用名称【" + appName + "】无效，不在有效配置列表之内-->" + validAppNames);
        }
    }
}
