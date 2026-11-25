package com.github.shuaidd.client;

import com.github.shuaidd.response.addressbook.AsyncJobResponse;
import com.github.shuaidd.response.addressbook.ExportResultResponse;
import com.github.shuaidd.response.addressbook.MobileHashCodeResponse;
import com.github.shuaidd.response.material.UploadByUrlResultResponse;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import com.github.shuaidd.response.tool.ApiDomainIpResponse;
import com.github.shuaidd.response.tool.OpenUserId2UserIdResponse;
import com.github.shuaidd.response.tool.TmpExternalUserIdConvertResponse;
import com.github.shuaidd.response.tool.TransformExternalUserIdResponse;
import com.github.shuaidd.resquest.addressbook.MobileHashCodeRequest;
import com.github.shuaidd.resquest.job.JobIdRequest;
import com.github.shuaidd.resquest.media.UploadByUrlRequest;
import com.github.shuaidd.resquest.tool.OpenUserId2UserIdRequest;
import com.github.shuaidd.resquest.tool.TmpExternalUserIdConvertRequest;
import com.github.shuaidd.resquest.tool.TransformExternalUserIdRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 描述 企业微信基础接口：获取 access_token、API 域名 IP、手机号随机串、异步任务与账号转换
 * <p>
 * 由 5.x 的同名 {@code @FeignClient} 迁移为原生 OpenFeign 接口，方法签名与请求形态保持不变：
 * {@code @GetMapping}/{@code @PostMapping} 改为 {@code @RequestLine}（路径不含 baseUrl 里的 cgi-bin），
 * {@code @RequestParam} 改为 {@code @Param}，应用名请求头由 {@code headers = HEAD} 改为原生 {@code @Headers(HEAD)}，
 * 请求体参数不加注解。{@code transformExternalUserId} 与 {@code openUserId2UserId} 的路径相同（5.2.0 快照如此），原样保留。
 *
 * @author ddshuai
 * date 2026-11-26 06:40
 **/
public interface WeChatClient extends CommonClient {

    /**
     * 获取应用 access token
     *
     * @param corpId 企业号
     * @param secret 密匙
     * @return AccessTokenResponse
     */
    @RequestLine("GET " + GET_TOKEN + "?corpid={corpid}&corpsecret={corpsecret}")
    AccessTokenResponse getAccessToken(@Param("corpid") String corpId, @Param("corpsecret") String secret);

    /**
     * 获取手机号随机串
     *
     * @param request 请求体
     * @param app     应用名
     * @return MobileHashCodeResponse
     */
    @RequestLine("POST /user/get_mobile_hashcode?app={app}")
    @Headers(HEAD)
    MobileHashCodeResponse getMobileHashcode(MobileHashCodeRequest request, @Param("app") String app);

    /**
     * 获取企业微信API域名IP段
     *
     * @param app 应用名
     * @return ApiDomainIpResponse
     */
    @RequestLine("GET /get_api_domain_ip?app={app}")
    @Headers(HEAD)
    ApiDomainIpResponse apiDomainIp(@Param("app") String app);

    /**
     * 获取企业微信服务器回调的ip段
     *
     * @param app 应用名
     * @return ApiDomainIpResponse
     */
    @RequestLine("GET /getcallbackip?app={app}")
    @Headers(HEAD)
    ApiDomainIpResponse callBackIp(@Param("app") String app);

    /**
     * 获取导出结果
     *
     * @param jobId 任务编号
     * @param app   应用名
     * @return 结果
     */
    @RequestLine("GET /export/get_result?jobid={jobid}&app={app}")
    @Headers(HEAD)
    ExportResultResponse getExportResult(@Param("jobid") String jobId, @Param("app") String app);

    /**
     * userid转换
     * 将代开发应用或第三方应用获取的密文open_userid转换为明文userid。
     * @param request 请求
     * @param app   应用名
     * @return 结果
     */
    @RequestLine("POST /batch/openuserid_to_userid?app={app}")
    @Headers(HEAD)
    OpenUserId2UserIdResponse openUserId2UserId(OpenUserId2UserIdRequest request, @Param("app") String app);

    /**
     * external_userid转换
     * 将代开发应用或第三方应用获取的externaluserid转换成自建应用的externaluserid。
     * @param request 请求
     * @param app   应用名
     * @return 结果
     */
    @RequestLine("POST /batch/openuserid_to_userid?app={app}")
    @Headers(HEAD)
    TransformExternalUserIdResponse transformExternalUserId(TransformExternalUserIdRequest request,
                                                            @Param("app") String app);

    /**
     * 生成异步上传任务
     * @param request 请求
     * @param app 应用
     * @return 异步任务编号
     */
    @RequestLine("POST /media/upload_by_url?app={app}")
    @Headers(HEAD)
    AsyncJobResponse uploadByUrl(UploadByUrlRequest request, @Param("app") String app);

    /**
     * 查询异步任务结果
     * @param request 请求
     * @param app 应用
     * @return 异步任务执行结果
     */
    @RequestLine("POST /media/get_upload_by_url_result?app={app}")
    @Headers(HEAD)
    UploadByUrlResultResponse getUploadByUrlResult(JobIdRequest request, @Param("app") String app);

    /**
     * tmp_external_userid转换
     *
     * @param request 请求
     * @param app     应用名
     * @return 转换结果
     */
    @RequestLine("POST /idconvert/convert_tmp_external_userid?app={app}")
    @Headers(HEAD)
    TmpExternalUserIdConvertResponse convertTmpExternalUserId(TmpExternalUserIdConvertRequest request,
                                                              @Param("app") String app);
}
