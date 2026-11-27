package com.github.shuaidd.support;

import feign.Client;
import feign.FeignException;
import feign.Request;
import feign.Response;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * 描述 原样下载：用 core 的 Feign {@link Client} 直接 GET 一个地址并读出全部字节
 * <p>
 * 替代 5.x 中 {@code AddressBookService.getDecryptExportData} 里临时 new 出来的 {@code RestTemplate}：
 * 该地址是企微返回的导出结果下载地址，不是 client 接口上登记的路由，所以不经 Feign 代理，
 * 但复用同一套 HTTP 实现与超时，纯 Java 环境下不再依赖 Spring Web。
 * 非 2xx 响应与 5.x 的 RestTemplate 一样抛出（类型由 {@code HttpClientErrorException} 换成 Feign 的 {@link FeignException}）。
 *
 * @author ddshuai
 * date 2026-11-27 23:30
 **/
public class RawDownloader {

    private final Client http;

    private final Request.Options options;

    public RawDownloader(Client http, Request.Options options) {
        this.http = Objects.requireNonNull(http, "http");
        this.options = Objects.requireNonNull(options, "options");
    }

    /**
     * 下载地址指向的全部内容
     *
     * @param url 下载地址
     * @return 响应体；没有响应体时为 null
     * @throws IOException 网络或读取失败
     * @throws FeignException HTTP 状态码不是 2xx
     */
    public byte[] download(String url) throws IOException {
        Request request = Request.create(Request.HttpMethod.GET, url,
                Collections.<String, Collection<String>>emptyMap(), null, StandardCharsets.UTF_8, null);
        try (Response response = http.execute(request, options)) {
            if (response.status() < 200 || response.status() >= 300) {
                throw FeignException.errorStatus("RawDownloader.download", response);
            }
            if (response.body() == null) {
                return null;
            }
            try (InputStream in = response.body().asInputStream()) {
                return IOUtils.toByteArray(in);
            }
        }
    }
}
