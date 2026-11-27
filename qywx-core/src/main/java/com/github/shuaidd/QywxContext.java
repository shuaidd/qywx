package com.github.shuaidd;

import com.github.shuaidd.client.AddressBookClient;
import com.github.shuaidd.client.LinkedCorpClient;
import com.github.shuaidd.client.WeChatClient;
import com.github.shuaidd.client.WorkOaClient;
import com.github.shuaidd.config.QywxConfig;

import java.util.Objects;

/**
 * 描述 客户端上下文：持有 service 层共用的配置与基础 client，替代 5.x 中各 service 从 Spring 容器注入的字段
 * <p>
 * 每个 {@link QywxClient} 持有一个实例，service 构造器首参接收它。持有 {@code properties} 与 5.x 基类
 * {@code AbstractBaseService} 字段注入的 4 个基础 client（{@code weChatClient}、{@code workOaClient}、
 * {@code addressBookClient}、{@code linkedCorpClient}）；各 service 自己的 client 仍由构造器其余参数传入。
 *
 * @author ddshuai
 * date 2026-11-26 06:40
 **/
public final class QywxContext {

    private final QywxConfig properties;

    private final WeChatClient weChatClient;

    private final WorkOaClient workOaClient;

    private final AddressBookClient addressBookClient;

    private final LinkedCorpClient linkedCorpClient;

    public QywxContext(QywxConfig properties, WeChatClient weChatClient, WorkOaClient workOaClient,
                       AddressBookClient addressBookClient, LinkedCorpClient linkedCorpClient) {
        this.properties = Objects.requireNonNull(properties, "properties");
        this.weChatClient = Objects.requireNonNull(weChatClient, "weChatClient");
        this.workOaClient = Objects.requireNonNull(workOaClient, "workOaClient");
        this.addressBookClient = Objects.requireNonNull(addressBookClient, "addressBookClient");
        this.linkedCorpClient = Objects.requireNonNull(linkedCorpClient, "linkedCorpClient");
    }

    public QywxConfig properties() {
        return properties;
    }

    public WeChatClient weChatClient() {
        return weChatClient;
    }

    public WorkOaClient workOaClient() {
        return workOaClient;
    }

    public AddressBookClient addressBookClient() {
        return addressBookClient;
    }

    public LinkedCorpClient linkedCorpClient() {
        return linkedCorpClient;
    }
}
