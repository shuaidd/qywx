package com.github.shuaidd.registry;

import com.github.shuaidd.client.CommonClient;
import com.github.shuaidd.client.WeChatMediaClient;
import com.github.shuaidd.client.decoder.CustomJacksonDecoder;
import com.github.shuaidd.client.decoder.WeChatMediaDecoder;
import com.github.shuaidd.client.interceptor.WeChatInterceptor;
import com.github.shuaidd.constant.Constant;
import com.github.shuaidd.support.ClassScaner;
import feign.Client;
import feign.form.spring.SpringFormEncoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Set;

/**
 * feign client 注入
 *
 * @author ddshuai
 */
@Configuration
@Order(-1)
@Component
public class FeignClientRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(FeignClientRegistryPostProcessor.class);
    private final ApplicationContext context;

    @Value("${qywx.url:https://qyapi.weixin.qq.com}")
    private String url;

    @Value("${qywx.public-path:cgi-bin}")
    private String path;

    public FeignClientRegistryPostProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        Set<Class> set = ClassScaner.scanForParent(new String[]{"com.github.shuaidd.client"}, CommonClient.class);
        set.forEach(cls -> {
           Object inst = new FeignClientBuilder(context).forType(cls, cls.getSimpleName()).customize(builder -> {

                if (cls == WeChatMediaClient.class) {
                    builder.decoder(new WeChatMediaDecoder())
                            .encoder(new SpringFormEncoder())
                            .requestInterceptor(new WeChatInterceptor());
                } else {
                    builder.decoder(new CustomJacksonDecoder())
                            .encoder(new JacksonEncoder())
                            .requestInterceptor(new WeChatInterceptor());
                }
            }).url(url).path(path).build();
            RootBeanDefinition bean = new RootBeanDefinition(inst);
            registry.registerBeanDefinition(name, bean);
        });
    }

    public Client getHttpClient() {
        String proxyHost = System.getenv(Constant.PROXY_HOST_ENV);
        String proxyPort = System.getenv(Constant.PROXY_PORT_ENV);
        if (StringUtils.isNoneEmpty(proxyHost, proxyPort)) {
            InetSocketAddress address = new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort));
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
            if (log.isInfoEnabled()) {
                log.info("构建 feign client，使用了代理 {}:{}", proxyHost, proxyPort);
            }
            return new Client.Proxied(null, null, proxy);
        }

        return new Client.Default(null, null);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
