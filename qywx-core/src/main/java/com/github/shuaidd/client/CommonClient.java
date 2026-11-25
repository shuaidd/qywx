package com.github.shuaidd.client;

/**
 * 描述 客户端公共常量：应用名请求头、access_token 参数与调试参数
 * <p>
 * 与 5.x 的同名接口保持一致，供各 client 接口继承。原 {@code DOWNLOAD_FILE_PATH}（把本地路径放进请求头）已移除，
 * 下载目标目录改由 {@code DownloadContext} 传递。
 *
 * @author ddshuai
 * date 2026-11-25 23:40
 **/
public interface CommonClient {

    /** 应用名请求头名称 */
    String HEAD_KEY = "app";

    /**
     * 应用名请求头模板，需配合 {@code @Param("app")} 使用。
     * <p>
     * OpenFeign 原生 {@code @Headers} 使用「名称: 值」格式；5.x 的 {@code app={app}} 是 Spring MVC 契约写法，
     * 原生 Feign 解析时会因找不到冒号而抛出 {@code StringIndexOutOfBoundsException}。
     */
    String HEAD = HEAD_KEY + ": {app}";

    /** access_token 查询参数名 */
    String ACCESS_TOKEN = "access_token";

    /** 获取 access_token 的接口路径（不含 baseUrl 中的 cgi-bin 前缀） */
    String GET_TOKEN = "/gettoken";

    /** 调试模式查询参数名 */
    String DEBUG = "debug";
}
