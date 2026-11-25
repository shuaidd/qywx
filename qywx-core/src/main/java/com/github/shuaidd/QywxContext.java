package com.github.shuaidd;

import com.github.shuaidd.client.WeChatClient;
import com.github.shuaidd.config.QywxConfig;

import java.util.Objects;

/**
 * 描述 客户端上下文：持有 service 层共用的配置与基础 client，替代 5.x 中各 service 从 Spring 容器注入的字段
 * <p>
 * 每个 {@link QywxClient} 持有一个实例，service 构造器首参接收它。本阶段只有 {@code properties} 与 {@code weChatClient}
 * 两个依赖；其余 3 个基类 client（{@code WorkOaClient}、{@code AddressBookClient}、{@code LinkedCorpClient}）
 * 随 P2.2 迁移后补齐。
 *
 * @author ddshuai
 * date 2026-11-26 06:40
 **/
public final class QywxContext {

    private final QywxConfig properties;

    private final WeChatClient weChatClient;

    public QywxContext(QywxConfig properties, WeChatClient weChatClient) {
        this.properties = Objects.requireNonNull(properties, "properties");
        this.weChatClient = Objects.requireNonNull(weChatClient, "weChatClient");
    }

    public QywxConfig properties() {
        return properties;
    }

    public WeChatClient weChatClient() {
        return weChatClient;
    }
}
