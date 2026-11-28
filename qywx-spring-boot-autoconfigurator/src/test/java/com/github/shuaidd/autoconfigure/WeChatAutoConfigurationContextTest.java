package com.github.shuaidd.autoconfigure;

import com.github.shuaidd.QywxClient;
import com.github.shuaidd.callback.AesException;
import com.github.shuaidd.client.config.CallbackProperties;
import com.github.shuaidd.client.config.WeChatConfigurationProperties;
import com.github.shuaidd.support.CallBackManager;
import com.github.shuaidd.support.WeChatContextHolder;
import com.github.shuaidd.support.WeChatManager;
import com.github.shuaidd.token.InMemoryTokenStore;
import com.github.shuaidd.token.TokenEntry;
import com.github.shuaidd.token.TokenManager;
import com.github.shuaidd.token.TokenStore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.MethodMetadata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 {@link WeChatAutoConfiguration} 的真实上下文装配契约：用 {@link ApplicationContextRunner} 起一个真正的
 * Spring 容器（不 mock 任何 bean），验证适配层经 {@code AutoConfiguration.imports} 自动装配、{@code @EnableWeChat}
 * 不重复注册、用户的 {@link CacheManager} / {@link TokenStore} / {@link WeChatContextHolder} 让适配层的同类型 bean 让位，
 * 并把 57 个 bean 回放成契约快照 {@code classpath:contract/spring-beans.txt}（bean名=返回类型全限定名）：
 * 快照是 Spring 侧装配清单的机器可读基线，增删 bean、改方法名或返回类型都会让这里失败
 * <p>
 * {@code @ConditionalOnMissingBean} 在 {@code @Bean} 方法上是 REGISTER_BEAN 阶段按配置类的登记顺序求值的：
 * 走 {@code .imports} 时适配层排在全部用户配置之后，用户 bean 能正确让位；走 {@code @EnableWeChat}（{@code @Import}）
 * 时适配层与用户配置同轮登记、顺序没有保证，所以 {@link EnableWeChatConfig} 用例只守卫「不重复注册」，不守卫让位顺序
 *
 * @author ddshuai
 * date 2026-11-28 08:10
 **/
public class WeChatAutoConfigurationContextTest {

    /** README / example 用到的全部 qywx.* 键（最小集）：一个应用 + 一个回调应用 */
    private static final String[] PROPERTIES = {
            "qywx.corp-id=corp-1",
            "qywx.url=https://qyapi.example.invalid",
            "qywx.public-path=cgi-bin",
            "qywx.application-list[0].secret=s-1",
            "qywx.application-list[0].agent-id=1000002",
            "qywx.application-list[0].application-name=app-1",
            "qywx.callback-list[0].application-name=cb-app",
            "qywx.callback-list[0].token=token",
            "qywx.callback-list[0].encoding-aes-key=too-short",
            "qywx.callback-list[0].receive-id=receive"};

    @AfterEach
    public void resetStaticEntrances() {
        //静态入口是进程级的：QywxClient 构造时登记过 WeChatManager 与回调配置，测试里动过就清掉，避免污染其他用例
        WeChatContextHolder.setWeChatManager(null);
        CallBackManager.properties(new ArrayList<CallbackProperties>());
    }

    /**
     * 不标 {@code @EnableWeChat}：{@code qywx.*} 绑定成功、57 个 bean 全部登记且与快照逐条一致、
     * 静态入口 {@link WeChatContextHolder} 拿到的就是容器里的 {@link WeChatManager}、回调配置已登记到 {@link CallBackManager}
     */
    @Test
    public void autoConfiguresEveryBeanWithoutEnableWeChat() {
        onlyAutoConfiguration().run(context -> {
            assertThat(context).hasNotFailed();
            assertBeansReplaySnapshot(context);

            WeChatConfigurationProperties properties = context.getBean(WeChatConfigurationProperties.class);
            assertEquals("corp-1", properties.getCorpId());
            assertEquals(1, properties.getApplicationList().size());
            assertEquals("app-1", properties.getApplicationList().get(0).getApplicationName());

            assertSame(context.getBean(WeChatManager.class), WeChatContextHolder.getWeChatManager());

            //回调配置经 QywxClient 登记到 CallBackManager：cb-app 能被找到，密钥长度不对所以在加解密处抛 AesException，而不是「未配置」返回空串
            assertThrows(AesException.class, () -> CallBackManager.verifyUrl("cb-app", "sig", "ts", "nonce", "echo"));
        });
    }

    /**
     * 显式标注 {@code @EnableWeChat}（{@code @Import} 进来的与自动配置登记的是同一个配置类）不会叠加：配置类恰好一个，57 个 bean 照常
     */
    @Test
    public void enableWeChatDoesNotRegisterBeansTwice() {
        withUserConfiguration(EnableWeChatConfig.class).run(context -> {
            assertThat(context).hasNotFailed();
            assertThat(context).hasSingleBean(WeChatAutoConfiguration.class);
            assertBeansReplaySnapshot(context);
        });
    }

    /**
     * 容器里什么缓存都没配：适配层自带的本地 {@link ConcurrentMapCacheManager} 兜底（只建 {@link SpringCacheTokenStore#CACHE_NAME} 一个缓存），
     * 选中的 store 写回 {@code properties.tokenStore}，{@link TokenManager} bean 与 {@link QywxClient#tokenManager()} 是同一个，
     * 预置的未过期 token 直接命中、不走网络
     */
    @Test
    public void usesLocalConcurrentMapCacheWhenNothingIsConfigured() {
        onlyAutoConfiguration().run(context -> {
            assertThat(context).hasNotFailed();
            assertThat(context).hasBean("concurrentMapCacheManager");

            CacheManager cacheManager = context.getBean(CacheManager.class);
            assertTrue(cacheManager instanceof ConcurrentMapCacheManager);
            assertThat(((ConcurrentMapCacheManager) cacheManager).getCacheNames())
                    .containsExactly(SpringCacheTokenStore.CACHE_NAME);

            QywxClient client = context.getBean(QywxClient.class);
            assertTrue(client.config().getTokenStore() instanceof SpringCacheTokenStore);
            assertSame(client.tokenManager(), context.getBean(TokenManager.class));

            putUnexpiredToken(cacheManager, "local-cache-token");
            assertEquals("local-cache-token", client.tokenManager().getAccessToken("app-1"));
        });
    }

    /**
     * 用户自带 {@link CacheManager}：适配层的本地兜底让位（不登记 concurrentMapCacheManager），token 走用户的缓存
     */
    @Test
    public void userCacheManagerTakesPrecedenceOverLocalFallback() {
        withUserConfiguration(UserCacheConfig.class).run(context -> {
            assertThat(context).hasNotFailed();
            assertThat(context).doesNotHaveBean("concurrentMapCacheManager");
            assertThat(context).hasSingleBean(CacheManager.class);
            assertBeansReplaySnapshot(context, "concurrentMapCacheManager");

            CacheManager userCacheManager = context.getBean("cacheManager", CacheManager.class);
            assertSame(userCacheManager, context.getBean(CacheManager.class));

            QywxClient client = context.getBean(QywxClient.class);
            assertTrue(client.config().getTokenStore() instanceof SpringCacheTokenStore);
            putUnexpiredToken(userCacheManager, "user-cache-token");
            assertEquals("user-cache-token", client.tokenManager().getAccessToken("app-1"));
        });
    }

    /**
     * Boot 自带的 {@link CacheAutoConfiguration}（配合 {@code @EnableCaching}）排在适配层之前（{@code @AutoConfiguration(after = ...)}）：
     * 它登记的 {@code cacheManager} 让本地兜底让位，动态建出的 {@code qywx} 缓存同样能装 token
     */
    @Test
    public void bootCacheAutoConfigurationTakesPrecedenceOverLocalFallback() {
        new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(WeChatAutoConfiguration.class, CacheAutoConfiguration.class))
                .withUserConfiguration(CachingConfig.class)
                .withPropertyValues(PROPERTIES)
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    assertThat(context).doesNotHaveBean("concurrentMapCacheManager");
                    assertThat(context).hasBean("cacheManager");
                    assertThat(context).hasSingleBean(CacheManager.class);
                    assertBeansReplaySnapshot(context, "concurrentMapCacheManager");

                    QywxClient client = context.getBean(QywxClient.class);
                    assertTrue(client.config().getTokenStore() instanceof SpringCacheTokenStore);
                    putUnexpiredToken(context.getBean(CacheManager.class), "boot-cache-token");
                    assertEquals("boot-cache-token", client.tokenManager().getAccessToken("app-1"));
                });
    }

    /**
     * 用户自带 {@link TokenStore}：优先级高于 CacheManager，适配层不把它包成 {@link SpringCacheTokenStore}；
     * 本地缓存兜底照常登记，快照仍是完整的 57 条
     */
    @Test
    public void userTokenStoreTakesPrecedenceOverCacheManager() {
        withUserConfiguration(UserTokenStoreConfig.class).run(context -> {
            assertThat(context).hasNotFailed();
            assertThat(context).hasBean("concurrentMapCacheManager");
            assertBeansReplaySnapshot(context);

            assertThat(context).hasSingleBean(TokenStore.class);
            TokenStore userStore = context.getBean(TokenStore.class);
            assertThat(userStore).isNotInstanceOf(SpringCacheTokenStore.class);
            assertSame(userStore, context.getBean(QywxClient.class).config().getTokenStore());
        });
    }

    /**
     * 用户自带 {@link WeChatContextHolder}：适配层的同名 bean 让位（快照少 1 条），其余 56 个照常
     */
    @Test
    public void userWeChatContextHolderTakesPrecedence() {
        withUserConfiguration(UserContextHolderConfig.class).run(context -> {
            assertThat(context).hasNotFailed();
            assertThat(context).hasSingleBean(WeChatContextHolder.class);
            assertThat(context.getBean(WeChatContextHolder.class)).isInstanceOf(UserContextHolder.class);
            assertBeansReplaySnapshot(context, "weChatContextHolder");
        });
    }

    /**
     * 容器里既没有 {@link TokenStore} 也没有 {@link CacheManager}（编程方式直接调 bean 方法）：适配层不写回 tokenStore，
     * 交给 {@link QywxClient} 构造器内置的进程内 {@link InMemoryTokenStore}
     */
    @Test
    public void fallsBackToCoreInMemoryStoreWhenNothingIsAvailable() {
        DefaultListableBeanFactory empty = new DefaultListableBeanFactory();
        ObjectProvider<TokenStore> tokenStores = empty.getBeanProvider(TokenStore.class);
        ObjectProvider<CacheManager> cacheManagers = empty.getBeanProvider(CacheManager.class);
        assertNull(tokenStores.getIfAvailable());
        assertNull(cacheManagers.getIfAvailable());

        WeChatConfigurationProperties properties = new WeChatConfigurationProperties();
        properties.setCorpId("corp-1");
        QywxClient client = new WeChatAutoConfiguration(properties).qywxClient(properties, tokenStores, cacheManagers);

        assertSame(properties, client.config());
        assertNull(properties.getTokenStore());
        assertNotNull(client.tokenManager());
    }

    private ApplicationContextRunner onlyAutoConfiguration() {
        return new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(WeChatAutoConfiguration.class))
                .withPropertyValues(PROPERTIES);
    }

    private ApplicationContextRunner withUserConfiguration(Class<?>... classes) {
        return onlyAutoConfiguration().withUserConfiguration(classes);
    }

    /**
     * 往适配层选中的缓存里预置一枚一小时后才过期的 token，后续 getAccessToken 直接命中、不走网络
     */
    private static void putUnexpiredToken(CacheManager cacheManager, String token) {
        Cache cache = cacheManager.getCache(SpringCacheTokenStore.CACHE_NAME);
        assertNotNull(cache, "CacheManager 里应当有 " + SpringCacheTokenStore.CACHE_NAME + " 缓存");
        cache.put(SpringCacheTokenStore.key("app-1"), new TokenEntry(token, System.currentTimeMillis() + 3600_000L));
    }

    /**
     * 读取契约快照：每行 {@code bean名=返回类型全限定名}，{@code #} 开头是注释
     */
    private static Map<String, String> loadBeanSnapshot() throws IOException {
        InputStream in = WeChatAutoConfigurationContextTest.class.getResourceAsStream("/contract/spring-beans.txt");
        assertNotNull(in, "契约快照 classpath:contract/spring-beans.txt 应在测试资源里");

        Map<String, String> snapshot = new TreeMap<String, String>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                int index = line.indexOf('=');
                assertTrue(index > 0, "快照行应为 bean名=类型：" + line);
                snapshot.put(line.substring(0, index), line.substring(index + 1));
            }
        }
        return snapshot;
    }

    /**
     * 收集由 {@link WeChatAutoConfiguration} 的 {@code @Bean} 方法登记的 bean：配置类经自动配置 / {@code @Import} 登记时 bean 名是全限定名，
     * 这里按类型取而不写死；再筛出 factoryBeanName 等于它的定义，值取工厂方法的返回类型
     */
    private static Map<String, String> beansDeclaredByAutoConfiguration(ConfigurableApplicationContext context) {
        String[] configurationNames = context.getBeanNamesForType(WeChatAutoConfiguration.class);
        assertEquals(1, configurationNames.length, "WeChatAutoConfiguration 应恰好登记一次：" + Arrays.toString(configurationNames));
        String configurationName = configurationNames[0];

        Map<String, String> declared = new TreeMap<String, String>();
        for (String name : context.getBeanFactory().getBeanDefinitionNames()) {
            BeanDefinition definition = context.getBeanFactory().getBeanDefinition(name);
            if (configurationName.equals(definition.getFactoryBeanName())) {
                MethodMetadata factoryMethod = definition instanceof AnnotatedBeanDefinition
                        ? ((AnnotatedBeanDefinition) definition).getFactoryMethodMetadata() : null;
                String returnTypeName = factoryMethod == null ? null : factoryMethod.getReturnTypeName();
                Class<?> type = context.getType(name);
                declared.put(name, returnTypeName != null ? returnTypeName : (type == null ? null : type.getName()));
            }
        }
        return declared;
    }

    /**
     * 回放契约快照：适配层登记的 bean 与快照（去掉 {@code skippedBeans} 指定的让位项）逐条相等，
     * 且每个 bean 都能按快照里的类型取到唯一实例
     */
    private static void assertBeansReplaySnapshot(ConfigurableApplicationContext context, String... skippedBeans) throws Exception {
        Map<String, String> declared = beansDeclaredByAutoConfiguration(context);
        Map<String, String> expected = loadBeanSnapshot();
        for (String skipped : skippedBeans) {
            assertThat(expected.remove(skipped)).as("快照里应当有 " + skipped).isNotNull();
        }
        assertThat(declared).isEqualTo(expected);

        ClassLoader classLoader = WeChatAutoConfigurationContextTest.class.getClassLoader();
        for (Map.Entry<String, String> entry : declared.entrySet()) {
            Class<?> type = Class.forName(entry.getValue(), false, classLoader);
            assertTrue(type.isInstance(context.getBean(entry.getKey())), entry.getKey() + " 不是 " + entry.getValue());
            assertEquals(1, context.getBeansOfType(type).size(), entry.getValue() + " 应恰好登记一个 bean");
        }
    }

    /** 显式标注 @EnableWeChat：@Import 进来的与自动配置是同一个配置类 */
    @Configuration(proxyBeanMethods = false)
    @EnableWeChat
    static class EnableWeChatConfig {
    }

    /** 用户自带的 CacheManager：让适配层的本地兜底让位 */
    @Configuration(proxyBeanMethods = false)
    static class UserCacheConfig {

        @Bean
        public ConcurrentMapCacheManager cacheManager() {
            return new ConcurrentMapCacheManager(SpringCacheTokenStore.CACHE_NAME);
        }
    }

    /** 打开缓存注解：Boot 的 CacheAutoConfiguration 以容器里有 CacheAspectSupport 为前提 */
    @Configuration(proxyBeanMethods = false)
    @EnableCaching
    static class CachingConfig {
    }

    /** 用户自带的 TokenStore */
    @Configuration(proxyBeanMethods = false)
    static class UserTokenStoreConfig {

        @Bean
        public InMemoryTokenStore tokenStore() {
            return new InMemoryTokenStore();
        }
    }

    /** 用户自带的 WeChatContextHolder 子类 */
    @Configuration(proxyBeanMethods = false)
    static class UserContextHolderConfig {

        @Bean
        public WeChatContextHolder weChatContextHolder() {
            return new UserContextHolder();
        }
    }

    static class UserContextHolder extends WeChatContextHolder {
    }
}