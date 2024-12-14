package com.github.shuaidd.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 描述 企业微信上下文工具类
 *
 * @author ddshuai
 * date 2022-01-12 09:57
 **/
public class WeChatContextHolder implements ApplicationContextAware {

    private static WeChatManager WE_CHAT_MANAGER;

    public static void setWeChatManager(WeChatManager weChatManager){
        WE_CHAT_MANAGER = weChatManager;
    }

    public static WeChatManager getWeChatManager(){
        return WE_CHAT_MANAGER;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WE_CHAT_MANAGER = applicationContext.getBean(WeChatManager.class);
    }
}
