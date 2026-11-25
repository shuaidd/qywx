package com.github.shuaidd.config;

import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.client.config.CallbackProperties;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 QywxConfig 基础地址计算与 Builder 单元测试
 *
 * @author ddshuai
 * date 2026-11-25 22:32
 **/
public class QywxConfigTest {

    /**
     * 默认配置指向企业微信官方地址，超时与 Feign 默认值一致
     */
    @Test
    public void defaultConfig() {
        QywxConfig config = new QywxConfig();
        assertEquals("https://qyapi.weixin.qq.com/cgi-bin", config.baseUrl());
        assertFalse(config.getDebugMode());
        assertEquals(10000, config.getConnectTimeoutMillis());
        assertEquals(60000, config.getReadTimeoutMillis());
        assertTrue(config.getApplicationList().isEmpty());
        assertTrue(config.getCallbackList().isEmpty());
        assertNull(config.getCorpId());
    }

    /**
     * url 末尾与 publicPath 两端的斜杠会被归一
     */
    @Test
    public void baseUrlNormalizesSlashes() {
        QywxConfig config = new QywxConfig();
        config.setUrl("https://example.invalid/");
        config.setPublicPath("/cgi-bin/");
        assertEquals("https://example.invalid/cgi-bin", config.baseUrl());
    }

    /**
     * url 为空时回退到官方域名；未写协议时补 http://
     */
    @Test
    public void baseUrlFallsBackAndAddsScheme() {
        QywxConfig config = new QywxConfig();
        config.setUrl("  ");
        assertEquals("https://qyapi.weixin.qq.com/cgi-bin", config.baseUrl());

        config.setUrl("localhost:8080");
        assertEquals("http://localhost:8080/cgi-bin", config.baseUrl());
    }

    /**
     * publicPath 为空时基础地址即 url
     */
    @Test
    public void baseUrlWithoutPublicPath() {
        QywxConfig config = new QywxConfig();
        config.setUrl("https://example.invalid");
        config.setPublicPath(" / ");
        assertEquals("https://example.invalid", config.baseUrl());
    }

    /**
     * Builder 能写入全部字段，application/callback 为追加语义
     */
    @Test
    public void builderPopulatesAllFields() {
        ApplicationProperties application = new ApplicationProperties();
        application.setApplicationName("crm");
        application.setSecret("s");
        CallbackProperties callback = new CallbackProperties();
        callback.setApplicationName("crm");

        List<ApplicationProperties> applications = new ArrayList<>();
        QywxConfig config = QywxConfig.builder()
                .corpId("ww-corp")
                .url("https://example.invalid")
                .publicPath("cgi-bin")
                .debugMode(true)
                .applicationList(applications)
                .application(application)
                .callback(callback)
                .connectTimeoutMillis(3000)
                .readTimeoutMillis(9000)
                .build();

        assertEquals("ww-corp", config.getCorpId());
        assertEquals("https://example.invalid/cgi-bin", config.baseUrl());
        assertTrue(config.getDebugMode());
        assertSame(applications, config.getApplicationList());
        assertEquals(1, config.getApplicationList().size());
        assertSame(application, config.getApplicationList().get(0));
        assertEquals(1, config.getCallbackList().size());
        assertSame(callback, config.getCallbackList().get(0));
        assertEquals(3000, config.getConnectTimeoutMillis());
        assertEquals(9000, config.getReadTimeoutMillis());
    }

    /**
     * 列表字段置为 null 后仍返回空列表，避免使用方判空
     */
    @Test
    public void listsNeverNull() {
        QywxConfig config = new QywxConfig();
        config.setApplicationList(null);
        config.setCallbackList(null);
        assertTrue(config.getApplicationList().isEmpty());
        assertTrue(config.getCallbackList().isEmpty());
    }
}