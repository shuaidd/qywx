package com.github.shuaidd.support;

/**
 * 描述 企业微信上下文工具类
 * <p>
 * 由 5.x 的同名类迁移而来：去掉 {@code ApplicationContextAware}，改为由 {@code QywxClient.create} 在装配完成后
 * 调用 {@link #setWeChatManager} 登记；Spring 环境下由适配层在容器里登记。
 *
 * @author ddshuai
 * date 2022-01-12 09:57
 **/
public class WeChatContextHolder {

    private static volatile WeChatManager WE_CHAT_MANAGER;

    public static void setWeChatManager(WeChatManager weChatManager) {
        WE_CHAT_MANAGER = weChatManager;
    }

    public static WeChatManager getWeChatManager() {
        return WE_CHAT_MANAGER;
    }
}
