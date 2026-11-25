package com.github.shuaidd.client;

import com.github.shuaidd.file.DownloadContext;
import com.github.shuaidd.response.BaseResponse;
import feign.Contract;
import feign.Feign;
import feign.Headers;
import feign.MethodMetadata;
import feign.Param;
import feign.RequestLine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 {@link QywxContract} 的单元测试：落盘目标参数移出表单体、未引用的 {@code @Param} 在构建代理时报错、显式引用时不受影响、
 * 钩子重复解析互不串扰
 *
 * @author ddshuai
 * date 2026-11-26 05:12
 **/
class QywxContractTest {

    /**
     * 落盘目标参数 {@code downloadFilePath} 不在模板里引用，只在本地使用
     */
    interface DownloadApi extends CommonClient {

        @RequestLine("GET /media/get?media_id={media_id}&app={app}")
        @Headers(HEAD)
        File downloadFile(@Param("media_id") String mediaId,
                          @Param(DownloadContext.DOWNLOAD_FILE_PATH) String downloadFilePath,
                          @Param("app") String app);

        /**
         * 对照：模板显式引用了 downloadFilePath
         */
        @RequestLine("GET /media/get?media_id={media_id}&downloadFilePath={downloadFilePath}&app={app}")
        @Headers(HEAD)
        File downloadToExplicitPath(@Param("media_id") String mediaId,
                                    @Param(DownloadContext.DOWNLOAD_FILE_PATH) String downloadFilePath,
                                    @Param("app") String app);

        @RequestLine("GET /ping?app={app}")
        @Headers(HEAD)
        BaseResponse ping(@Param("app") String app);
    }

    /**
     * 带请求体，同时声明了模板没有引用的 {@code @Param}（参数顺序各一个）
     */
    interface StrayApi extends CommonClient {

        class Body {
        }

        @RequestLine("POST /x?app={app}")
        @Headers(HEAD)
        BaseResponse bodyThenStray(Body body, @Param("app") String app, @Param("stray") String stray);

        @RequestLine("POST /x?app={app}")
        @Headers(HEAD)
        BaseResponse strayThenBody(@Param("stray") String stray, Body body, @Param("app") String app);
    }

    /**
     * 没有请求体，声明了模板没有引用的 {@code @Param}（不是落盘目标）
     */
    interface StrayGetApi extends CommonClient {

        @RequestLine("GET /media/get?media_id={media_id}&app={app}")
        @Headers(HEAD)
        BaseResponse call(@Param("media_id") String mediaId, @Param("stray") String stray,
                          @Param("app") String app);
    }

    /**
     * 基线：OpenFeign 默认契约把没有在模板里引用的 downloadFilePath 记为表单参数（调用时会多出表单体与 Content-Type）
     */
    @Test
    void defaultContractTreatsDownloadTargetAsFormParam() {
        MethodMetadata data = parse(new Contract.Default(), DownloadApi.class, "downloadFile");

        assertEquals(Collections.singletonList(DownloadContext.DOWNLOAD_FILE_PATH), data.formParams());
        assertNull(data.bodyIndex());
    }

    /**
     * QywxContract 把 downloadFilePath 移出表单参数；参数名映射、模板、请求头、configKey 都保持不变
     */
    @Test
    void dropsDownloadTargetOutOfFormParams() {
        MethodMetadata data = parse(new QywxContract(), DownloadApi.class, "downloadFile");

        assertTrue(data.formParams().isEmpty());
        assertNull(data.bodyIndex());
        assertEquals("DownloadApi#downloadFile(String,String,String)", data.configKey());
        assertEquals("/media/get?media_id={media_id}&app={app}", data.template().url());
        assertEquals(Collections.singletonList("media_id"), names(data, 0));
        assertEquals(Collections.singletonList(DownloadContext.DOWNLOAD_FILE_PATH), names(data, 1));
        assertEquals(Collections.singletonList("app"), names(data, 2));
        assertEquals(Collections.singletonList("{app}"), header(data, CommonClient.HEAD_KEY));
    }

    /**
     * 模板显式引用了 downloadFilePath 时它本来就不进表单参数，QywxContract 不改动模板
     */
    @Test
    void keepsExplicitlyReferencedParamInTemplate() {
        MethodMetadata data = parse(new QywxContract(), DownloadApi.class, "downloadToExplicitPath");

        assertTrue(data.formParams().isEmpty());
        assertEquals("/media/get?media_id={media_id}&downloadFilePath={downloadFilePath}&app={app}",
                data.template().url());
    }

    /**
     * 带请求体 + 未引用的 @Param：不论参数顺序，Feign 基类先报错（Body parameters cannot be used with form parameters），
     * 默认契约与 QywxContract 的表现一致
     */
    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"bodyThenStray", "strayThenBody"})
    void rejectsUnreferencedParamOnBodyMethods(String methodName) {
        IllegalStateException byDefault = assertThrows(IllegalStateException.class,
                () -> parse(new Contract.Default(), StrayApi.class, methodName));
        IllegalStateException byQywx = assertThrows(IllegalStateException.class,
                () -> parse(new QywxContract(), StrayApi.class, methodName));

        assertTrue(byDefault.getMessage().contains("form parameters"), byDefault.getMessage());
        assertTrue(byQywx.getMessage().contains("form parameters"), byQywx.getMessage());
    }

    /**
     * 没有请求体 + 未引用的 @Param：默认契约悄悄通过（stray 会被发成表单体），QywxContract 报错并指出参数名
     */
    @Test
    void rejectsUnreferencedParamOnMethodsWithoutBody() {
        MethodMetadata byDefault = parse(new Contract.Default(), StrayGetApi.class, "call");
        assertEquals(Collections.singletonList("stray"), byDefault.formParams());

        IllegalStateException thrown = assertThrows(IllegalStateException.class,
                () -> parse(new QywxContract(), StrayGetApi.class, "call"));

        assertTrue(thrown.getMessage().contains("stray"), thrown.getMessage());
        assertTrue(thrown.getMessage().contains("@Param"), thrown.getMessage());
        assertTrue(thrown.getMessage().contains("StrayGetApi#call(String,String,String)"), thrown.getMessage());
    }

    /**
     * 基类对每个方法新建一份 MethodMetadata：同一接口解析两次，结果逐项相等且不是同一批对象
     */
    @Test
    void parsesSameInterfaceTwiceIndependently() {
        QywxContract contract = new QywxContract();
        MethodMetadata first = parse(contract, DownloadApi.class, "downloadFile");
        MethodMetadata second = parse(contract, DownloadApi.class, "downloadFile");

        assertEquals(first.configKey(), second.configKey());
        assertEquals(first.formParams(), second.formParams());
        assertEquals(first.indexToName(), second.indexToName());
        assertEquals(first.bodyIndex(), second.bodyIndex());
        assertEquals(first.template().url(), second.template().url());
        assertNotSame(first, second);
        assertNotSame(first.formParams(), second.formParams());
    }

    /**
     * 端到端：创建代理时即解析契约，未引用的 @Param 让 target 抛错；合法的下载接口可以正常创建
     */
    @Test
    void failsWhenCreatingProxyWithUnreferencedParam() {
        assertThrows(IllegalStateException.class, () -> Feign.builder().contract(new QywxContract())
                .target(StrayGetApi.class, "http://contract.invalid"));

        DownloadApi api = Feign.builder().contract(new QywxContract())
                .target(DownloadApi.class, "http://contract.invalid");

        assertNotNull(api);
    }

    /**
     * 按方法名取契约解析结果；取不到说明夹具与预期不符
     */
    private static MethodMetadata parse(Contract contract, Class<?> type, String methodName) {
        for (MethodMetadata data : contract.parseAndValidateMetadata(type)) {
            if (methodName.equals(data.method().getName())) {
                return data;
            }
        }
        throw new AssertionError("契约没有解析出方法：" + type.getSimpleName() + "#" + methodName);
    }

    private static List<String> names(MethodMetadata data, int index) {
        return new ArrayList<>(data.indexToName().get(index));
    }

    /**
     * 取模板里的请求头（键不分大小写）
     */
    private static List<String> header(MethodMetadata data, String name) {
        for (Map.Entry<String, Collection<String>> entry : data.template().headers().entrySet()) {
            if (entry.getKey().equalsIgnoreCase(name)) {
                return new ArrayList<>(entry.getValue());
            }
        }
        throw new AssertionError("模板里没有请求头：" + name);
    }
}
