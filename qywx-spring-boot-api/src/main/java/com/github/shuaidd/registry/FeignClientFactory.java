package com.github.shuaidd.registry;

import com.github.shuaidd.constant.Constant;
import feign.Client;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignBuilderCustomizer;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Objects;

/**
 * @author ddshuai
 */

@Component
public class FeignClientFactory {
    private static final Logger log = LoggerFactory.getLogger(FeignClientFactory.class);
    private final ApplicationContext context;

    public FeignClientFactory(ApplicationContext context) {
        this.context = context;
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

    public <T> T buildClient(Class<T> client, ClientProperties properties, String defaultName, String defaultPath) {
        return buildClient(client, properties, defaultName, defaultPath, null);
    }

    public <T> T buildClient(Class<T> client, ClientProperties properties, String defaultName, String defaultPath, FeignBuilderCustomizer customizer) {
        if (Objects.isNull(customizer)) {
            customizer = builder -> builder.client(getHttpClient());
        }

        if (Objects.nonNull(properties)) {
            return new FeignClientBuilder(context).forType(client, StringUtils.defaultString(properties.getName(), defaultName)).customize(customizer).url(properties.getUrl()).path(StringUtils.defaultString(properties.getPublicPath(), defaultPath)).build();
        }

        return new FeignClientBuilder(context).forType(client, defaultName).customize(customizer).path(defaultPath).build();
    }

    public <T> T buildClientWithUri(Class<T> client, ClientProperties properties, String defaultName, String uri) {
        FeignBuilderCustomizer customizer = builder -> builder.client(getHttpClient());
        if (Objects.nonNull(properties)) {
            return new FeignClientBuilder(context).forType(client, StringUtils.defaultString(properties.getName(), defaultName)).customize(customizer).url(properties.getUrl()).path(StringUtils.defaultString(properties.getPublicPath(), "") + uri).build();
        }

        return new FeignClientBuilder(context).forType(client, defaultName).customize(customizer).path(uri).build();
    }

    public <T> T buildClient(Class<T> client, ClientProperties properties, String defaultName, FeignBuilderCustomizer customizer) {
        return buildClient(client, properties, defaultName, null, customizer);
    }

    public <T> T buildClient(Class<T> client, ClientProperties properties, String defaultName) {
        return buildClient(client, properties, defaultName, null, null);
    }
}
