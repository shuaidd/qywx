package com.github.shuaidd.client;

import com.github.shuaidd.file.DownloadContext;
import feign.Contract;
import feign.MethodMetadata;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 描述 qywx 的 Feign 契约：在 OpenFeign 默认契约之上，把「只在本地使用、不随请求发送」的参数移出表单参数
 * <p>
 * 背景：原生 Feign 把标注了 {@code @Param}、却没有在 {@code @RequestLine} / {@code @Headers} 模板里引用的参数记入
 * {@link MethodMetadata#formParams()}，调用时会自动加上 {@code Content-Type: application/x-www-form-urlencoded}
 * 并把这些参数写成表单体；方法上如果还有请求体（JSON），请求体会被静默丢弃。5.x 里 {@code downloadFilePath}
 * 是放在请求头发给服务端的，core 改为只在本地使用（落盘目标），所以接口上保留该参数、模板与 header 都不引用它。
 * <p>
 * 处理：
 * <ul>
 * <li>{@code @Param("downloadFilePath")}（{@link DownloadContext#DOWNLOAD_FILE_PATH}）从表单参数里移除：
 * 它的实参由 {@code TokenRetryInvocationHandlerFactory} 在调用期间交给 {@link DownloadContext}，
 * {@code indexToName} 仍然保留，所以调用时照常读取该位置的实参</li>
 * <li>其余没有被模板引用的 {@code @Param}：创建代理时即抛出 {@link IllegalStateException}，
 * 避免 codemod 漏写引用时把参数悄悄发成表单体（5.x 的 Spring MVC 契约会把这些 {@code @RequestParam} 追加到查询串，不存在这种静默失败）</li>
 * </ul>
 * 行为说明：方法带请求体时，只要存在未引用的 {@code @Param}（不论参数顺序），Feign 基类先报
 * {@code Body parameters cannot be used with form parameters.}，轮不到本类处理，因此带请求体的方法不能同时声明落盘目标参数；
 * 没有请求体时由本类报错。两种失败都发生在 {@code Feign.Builder#target} 创建代理时，不会发出错误的请求。
 * 基类对每个方法新建一份 {@link MethodMetadata}，本钩子可重复执行。
 * <p>
 * 装配时必须 {@code Feign.Builder#contract(new QywxContract())}，否则下载请求会多出表单体与 {@code Content-Type}
 * （契约回放实测：{@code WeChatMediaDecoderTest#replaysDownloadFileContracts} 曾因此失败）。
 *
 * @author ddshuai
 * date 2026-11-26 05:10
 **/
public class QywxContract extends Contract.Default {

    /** 不随请求发送、只在调用期间传给 {@link DownloadContext} 的参数名 */
    private static final List<String> OUT_OF_BAND_PARAMS = Collections.unmodifiableList(
            Arrays.asList(DownloadContext.DOWNLOAD_FILE_PATH));

    @Override
    protected MethodMetadata parseAndValidateMetadata(Class<?> targetType, Method method) {
        MethodMetadata data = super.parseAndValidateMetadata(targetType, method);
        List<String> formParams = data.formParams();
        if (!formParams.isEmpty()) {
            formParams.removeAll(OUT_OF_BAND_PARAMS);
        }
        if (!formParams.isEmpty()) {
            throw new IllegalStateException("方法 " + data.configKey() + " 的参数 " + formParams
                    + " 标注了 @Param 但没有在 @RequestLine/@Headers 模板里引用：Feign 会把它们发成表单体（方法上的 JSON 请求体会被丢弃），"
                    + "请删除该参数或用 {参数名} 引用它");
        }
        return data;
    }
}
