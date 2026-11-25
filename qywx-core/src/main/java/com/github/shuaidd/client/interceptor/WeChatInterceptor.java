package com.github.shuaidd.client.interceptor;

import com.github.shuaidd.client.CommonClient;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.token.TokenManager;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 描述 统一为请求追加 access_token：按请求头中的应用名从 {@link TokenManager} 取令牌
 * <p>
 * 替代 5.x 中依赖 Spring 静态持有者 {@code WeChatContextHolder} 的实现，规则保持一致：
 * <ul>
 * <li>获取令牌的请求（路径以 {@code /gettoken} 开头）不注入令牌</li>
 * <li>请求头 {@code app} 为空时抛出 {@link RuntimeException}，提示未传递应用名</li>
 * <li>access_token 追加在已有查询参数之后；调试模式下再追加 {@code debug=1}</li>
 * </ul>
 * 与 5.x 的差异：打印请求地址时把 access_token、corpsecret 的值替换为 {@code ***}。
 *
 * @author ddshuai
 * date 2026-11-25 23:40
 **/
public class WeChatInterceptor implements RequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(WeChatInterceptor.class);

    private static final String MASK = "***";

    private static final Pattern ACCESS_TOKEN = Pattern.compile("(access_token=)[^&]*");

    private static final Pattern CORP_SECRET = Pattern.compile("(corpsecret=)[^&]*");

    private final TokenManager tokenManager;

    private final QywxConfig config;

    public WeChatInterceptor(TokenManager tokenManager, QywxConfig config) {
        this.tokenManager = Objects.requireNonNull(tokenManager, "tokenManager");
        this.config = Objects.requireNonNull(config, "config");
    }

    /**
     * 统一处理 access_token
     *
     * @param template 请求模板
     */
    @Override
    public void apply(RequestTemplate template) {
        //需要把获取token的请求过滤掉
        if (!template.url().startsWith(CommonClient.GET_TOKEN)) {
            String app = applicationName(template);
            if (StringUtils.isEmpty(app)) {
                throw new RuntimeException("请求未传递应用名，无法调起接口");
            }
            template.query(CommonClient.ACCESS_TOKEN, tokenManager.getAccessToken(app));
            if (Boolean.TRUE.equals(config.getDebugMode())) {
                template.query(CommonClient.DEBUG, "1");
            }
        }

        if (logger.isInfoEnabled()) {
            logger.info("实际请求地址：{}", mask(template.url()));
        }
    }

    /**
     * 取请求头 app 的首个非空值
     */
    private static String applicationName(RequestTemplate template) {
        Collection<String> values = template.headers().get(CommonClient.HEAD_KEY);
        if (values == null) {
            return null;
        }
        for (String value : values) {
            if (StringUtils.isNotEmpty(value)) {
                return value;
            }
        }
        return null;
    }

    /**
     * 日志脱敏：把 access_token、corpsecret 的值替换为 ***
     *
     * @param url 请求地址
     * @return 脱敏后的地址
     */
    static String mask(String url) {
        if (url == null) {
            return null;
        }
        String masked = ACCESS_TOKEN.matcher(url).replaceAll("$1" + MASK);
        return CORP_SECRET.matcher(masked).replaceAll("$1" + MASK);
    }
}
