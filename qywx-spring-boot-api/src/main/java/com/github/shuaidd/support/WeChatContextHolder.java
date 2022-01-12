package com.github.shuaidd.support;

/**
 * 描述 企业微信上下文工具类
 *
 * @author ddshuai
 * date 2022-01-12 09:57
 **/
public class WeChatContextHolder {

    private static WeChatManager WE_CHAT_MANAGER;

    public static void setWeChatManager(WeChatManager weChatManager){
        WE_CHAT_MANAGER = weChatManager;
    }

    public static WeChatManager getWeChatManager(){
        return WE_CHAT_MANAGER;
    }
}
