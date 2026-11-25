package com.github.shuaidd.config;

import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.client.config.CallbackProperties;
import com.github.shuaidd.token.TokenStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 描述 企业微信客户端配置（不依赖 Spring，可在纯 Java 环境直接使用）。
 * <p>
 * 字段与 5.x 的 {@code qywx.*} 配置项一一对应；Spring 环境下由
 * {@code WeChatConfigurationProperties} 继承本类完成属性绑定。
 *
 * @author ddshuai
 * date 2026-11-25 22:32
 **/
public class QywxConfig {

    /**
     * 默认的企业微信 API 域名
     */
    public static final String DEFAULT_URL = "https://qyapi.weixin.qq.com";

    /**
     * 默认的企业微信公共目录
     */
    public static final String DEFAULT_PUBLIC_PATH = "cgi-bin";

    /**
     * 默认的建连超时时间（毫秒），与 Feign 默认值一致
     */
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 10000;

    /**
     * 默认的读取超时时间（毫秒），与 Feign 默认值一致
     */
    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 60000;

    /**
     * 企业号
     */
    private String corpId;

    /**
     * 企业微信api域名
     */
    private String url = DEFAULT_URL;

    /**
     * 企业微信公共目录
     */
    private String publicPath = DEFAULT_PUBLIC_PATH;

    /**
     * 使用debug模式调用接口
     */
    private Boolean debugMode = false;

    /**
     * 应用列表
     */
    private List<ApplicationProperties> applicationList = new ArrayList<>();

    /**
     * 回调应用配置列表
     */
    private List<CallbackProperties> callbackList = new ArrayList<>();

    /**
     * 建立连接的超时时间（毫秒）
     */
    private int connectTimeoutMillis = DEFAULT_CONNECT_TIMEOUT_MILLIS;

    /**
     * 读取响应的超时时间（毫秒）
     */
    private int readTimeoutMillis = DEFAULT_READ_TIMEOUT_MILLIS;

    /**
     * access_token 缓存组件；为 null 时使用进程内缓存（{@link com.github.shuaidd.token.InMemoryTokenStore}）
     */
    private TokenStore tokenStore;

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicPath() {
        return publicPath;
    }

    public void setPublicPath(String publicPath) {
        this.publicPath = publicPath;
    }

    public Boolean getDebugMode() {
        return debugMode;
    }

    public void setDebugMode(Boolean debugMode) {
        this.debugMode = debugMode;
    }

    public List<ApplicationProperties> getApplicationList() {
        return applicationList;
    }

    /**
     * 设置应用列表；传入 null 时按空列表处理，保证列表字段永不为 null
     *
     * @param applicationList 应用列表
     */
    public void setApplicationList(List<ApplicationProperties> applicationList) {
        this.applicationList = applicationList == null ? new ArrayList<>() : applicationList;
    }

    public List<CallbackProperties> getCallbackList() {
        return callbackList;
    }

    /**
     * 设置回调应用配置列表；传入 null 时按空列表处理，保证列表字段永不为 null
     *
     * @param callbackList 回调应用配置列表
     */
    public void setCallbackList(List<CallbackProperties> callbackList) {
        this.callbackList = callbackList == null ? new ArrayList<>() : callbackList;
    }

    public int getConnectTimeoutMillis() {
        return connectTimeoutMillis;
    }

    public void setConnectTimeoutMillis(int connectTimeoutMillis) {
        this.connectTimeoutMillis = connectTimeoutMillis;
    }

    public int getReadTimeoutMillis() {
        return readTimeoutMillis;
    }

    public void setReadTimeoutMillis(int readTimeoutMillis) {
        this.readTimeoutMillis = readTimeoutMillis;
    }

    public TokenStore getTokenStore() {
        return tokenStore;
    }

    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    /**
     * 按应用别名查找应用配置
     *
     * @param applicationName 应用别名
     * @return 应用配置；未配置时返回 null
     */
    public ApplicationProperties findApplication(String applicationName) {
        for (ApplicationProperties application : applicationList) {
            if (application != null && Objects.equals(applicationName, application.getApplicationName())) {
                return application;
            }
        }
        return null;
    }

    /**
     * 计算接口基础地址：{@code url + "/" + publicPath}，默认 {@code https://qyapi.weixin.qq.com/cgi-bin}。
     * <ul>
     * <li>url 为空时使用默认域名；未写协议时补 {@code http://}</li>
     * <li>url 末尾、publicPath 两端的 {@code /} 会被去掉；publicPath 为空时基础地址即 url</li>
     * </ul>
     *
     * @return 接口基础地址，不以 / 结尾
     */
    public String baseUrl() {
        String base = url == null ? "" : url.trim();
        if (base.isEmpty()) {
            base = DEFAULT_URL;
        }
        boolean hasScheme = base.regionMatches(true, 0, "http://", 0, 7)
                || base.regionMatches(true, 0, "https://", 0, 8);
        if (!hasScheme) {
            base = "http://" + base;
        }
        base = stripTrailingSlash(base);

        String path = publicPath == null ? "" : publicPath.trim();
        path = stripLeadingSlash(stripTrailingSlash(path));
        return path.isEmpty() ? base : base + "/" + path;
    }

    private static String stripTrailingSlash(String value) {
        String result = value;
        while (result.endsWith("/")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    private static String stripLeadingSlash(String value) {
        String result = value;
        while (result.startsWith("/")) {
            result = result.substring(1);
        }
        return result;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private final QywxConfig config = new QywxConfig();

        private Builder() {
        }

        public Builder corpId(String corpId) {
            config.setCorpId(corpId);
            return this;
        }

        public Builder url(String url) {
            config.setUrl(url);
            return this;
        }

        public Builder publicPath(String publicPath) {
            config.setPublicPath(publicPath);
            return this;
        }

        public Builder debugMode(boolean debugMode) {
            config.setDebugMode(debugMode);
            return this;
        }

        /**
         * 追加一个应用配置
         *
         * @param application 应用配置
         * @return this
         */
        public Builder application(ApplicationProperties application) {
            config.getApplicationList().add(application);
            return this;
        }

        public Builder applicationList(List<ApplicationProperties> applicationList) {
            config.setApplicationList(applicationList);
            return this;
        }

        /**
         * 追加一个回调应用配置
         *
         * @param callback 回调应用配置
         * @return this
         */
        public Builder callback(CallbackProperties callback) {
            config.getCallbackList().add(callback);
            return this;
        }

        public Builder callbackList(List<CallbackProperties> callbackList) {
            config.setCallbackList(callbackList);
            return this;
        }

        public Builder connectTimeoutMillis(int connectTimeoutMillis) {
            config.setConnectTimeoutMillis(connectTimeoutMillis);
            return this;
        }

        public Builder readTimeoutMillis(int readTimeoutMillis) {
            config.setReadTimeoutMillis(readTimeoutMillis);
            return this;
        }

        /**
         * 指定 access_token 缓存组件（不指定则使用进程内缓存）
         *
         * @param tokenStore 缓存组件
         * @return this
         */
        public Builder tokenStore(TokenStore tokenStore) {
            config.setTokenStore(tokenStore);
            return this;
        }

        public QywxConfig build() {
            return config;
        }
    }
}
