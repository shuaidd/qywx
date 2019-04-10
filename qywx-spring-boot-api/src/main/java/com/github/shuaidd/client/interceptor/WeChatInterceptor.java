package com.github.shuaidd.client.interceptor;


import com.github.shuaidd.client.WeChatClient;
import com.github.shuaidd.support.WeChatManager;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 处理所有请求的access_token参数
 *
 * @author ddshuai
 * @date 2019-04-04 16:20
 **/

public class WeChatInterceptor implements RequestInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ApplicationContext applicationContext;

    public WeChatInterceptor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 统一处理AccessToken
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        //需要把获取token的请求过滤掉
        if (!template.url().startsWith(WeChatClient.GET_TOKEN)) {
            String app = "";
            Collection<String> headerApp = template.headers().get(WeChatClient.HEAD_KEY);
            if (CollectionUtils.isNotEmpty(headerApp)) {
                String realApp = new ArrayList<>(headerApp).get(0);
                if (StringUtils.isNotEmpty(realApp)) {
                    app = realApp;
                }
            }

            WeChatManager weChatManager = applicationContext.getBean(WeChatManager.class);
            String accessToken = weChatManager.tokenService().getAccessToken(app);
            template.query(WeChatClient.ACCESS_TOKEN, accessToken);
        }

        if (logger.isInfoEnabled()) {
            logger.info("实际请求地址：{}", template.url());
        }
    }
}
