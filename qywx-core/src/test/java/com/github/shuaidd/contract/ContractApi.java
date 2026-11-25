package com.github.shuaidd.contract;

import com.github.shuaidd.client.CommonClient;
import com.github.shuaidd.client.annotation.Part;
import com.github.shuaidd.file.QywxFile;
import com.github.shuaidd.resquest.addressbook.BatchDeleteUserRequest;
import com.github.shuaidd.resquest.addressbook.CreateUserRequest;
import com.github.shuaidd.resquest.auth.ProviderTokenRequest;
import com.github.shuaidd.resquest.meeting.MeetingInviteRequest;
import com.github.shuaidd.resquest.meeting.MeetingRoomsInventoryRequest;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.auth.ProviderTokenResponse;
import com.github.shuaidd.response.meeting.MeetingInviteResponse;
import com.github.shuaidd.response.meeting.MeetingRoomsInventoryResponse;
import com.github.shuaidd.response.material.UploadImageResponse;
import com.github.shuaidd.response.material.WeChatMediaUploadResponse;
import com.github.shuaidd.response.payment.UploadPaymentImageResponse;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import com.github.shuaidd.response.tool.ApiDomainIpResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 描述 契约回放用的 client 接口：从 5.2.0 契约快照里挑出有代表性的请求形态，按原生 OpenFeign 注解声明
 * <p>
 * {@code @RequestLine} 的路径与查询参数、请求头按快照逐条对应；应用名同时走 query（{@code app={app}}）与请求头（{@code app: {app}}）。
 *
 * @author ddshuai
 * date 2026-11-26 00:30
 **/
public interface ContractApi extends CommonClient {

    /**
     * 获取 access_token：不传应用名请求头，由 TokenManager 通过取号器调用
     *
     * @param corpId   企业 ID
     * @param corpSecret 应用密钥
     * @return 令牌响应
     */
    @RequestLine("GET /gettoken?corpid={corpid}&corpsecret={corpsecret}")
    AccessTokenResponse getAccessToken(@Param("corpid") String corpId, @Param("corpsecret") String corpSecret);

    @RequestLine("GET /get_api_domain_ip?app={app}")
    @Headers(HEAD)
    ApiDomainIpResponse apiDomainIp(@Param("app") String app);

    @RequestLine("GET /getcallbackip?app={app}")
    @Headers(HEAD)
    ApiDomainIpResponse callBackIp(@Param("app") String app);

    @RequestLine("POST /meeting/get_invitees?app={app}")
    @Headers(HEAD)
    MeetingInviteResponse getInvitees(MeetingInviteRequest request, @Param("app") String app);

    @RequestLine("POST /user/batchdelete?app={app}")
    @Headers(HEAD)
    BaseResponse batchDelete(BatchDeleteUserRequest request, @Param("app") String app);

    /**
     * 接口上已声明 Content-Type，验证 encoder 写入的头能覆盖它且只保留一个值
     */
    @RequestLine("POST /user/batchdelete?app={app}")
    @Headers({HEAD, "Content-Type: text/plain"})
    BaseResponse batchDeleteWithContentType(BatchDeleteUserRequest request, @Param("app") String app);

    @RequestLine("POST /user/create?app={app}")
    @Headers(HEAD)
    BaseResponse createUser(CreateUserRequest request, @Param("app") String app);

    /**
     * ServiceProviderClient：不传应用名，请求体为紧凑 JSON
     */
    @RequestLine("POST /service/get_provider_token")
    ProviderTokenResponse getProviderToken(ProviderTokenRequest request);

    @RequestLine("POST /media/upload?type={type}&app={app}")
    @Headers(HEAD)
    WeChatMediaUploadResponse uploadMaterial(@Part("media") QywxFile file, @Param("type") String type,
                                             @Param("app") String app);

    @RequestLine("POST /media/upload_attachment?media_type={media_type}&attachment_type={attachment_type}&app={app}")
    @Headers(HEAD)
    WeChatMediaUploadResponse uploadAttachment(@Part("media") QywxFile file,
                                               @Param("media_type") String mediaType,
                                               @Param("attachment_type") Integer attachmentType,
                                               @Param("app") String app);

    @RequestLine("POST /media/uploadimg?app={app}")
    @Headers(HEAD)
    UploadImageResponse uploadImage(@Part("media") QywxFile file, @Param("app") String app);

    @RequestLine("POST /miniapppay/upload_image?app={app}")
    @Headers(HEAD)
    UploadPaymentImageResponse paymentUploadImage(@Part("image") QywxFile file, @Param("app") String app);

    /**
     * 文件参数没有 {@code @Part}，编码时应报错
     */
    @RequestLine("POST /media/uploadimg?app={app}")
    @Headers(HEAD)
    UploadImageResponse uploadWithoutPart(QywxFile file, @Param("app") String app);

    /**
     * 5.2.0 在这个无字段请求类上抛 EncodeException（FAIL_ON_EMPTY_BEANS），core 应能正常发出空对象
     */
    @RequestLine("POST /meeting/rooms/get_inventory?app={app}")
    @Headers(HEAD)
    MeetingRoomsInventoryResponse getRoomsInventory(MeetingRoomsInventoryRequest request, @Param("app") String app);
}
