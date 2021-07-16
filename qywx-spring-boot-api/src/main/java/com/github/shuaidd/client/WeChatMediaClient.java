package com.github.shuaidd.client;

import com.github.shuaidd.client.config.MultipartSupportConfig;
import com.github.shuaidd.response.material.UploadImageResponse;
import com.github.shuaidd.response.material.WeChatMediaUploadResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 描述 enjoy your life
 *
 * @author ddshuai
 * date 2018-11-28 17:34
 **/
@SuppressWarnings("all")
@FeignClient(name = "weChatUploadClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = MultipartSupportConfig.class)
public interface WeChatMediaClient {

    /**
     * 素材上传得到media_id，该media_id仅三天内有效
     * media_id在同一企业内应用之间可以共享
     * <p>
     * 上传临时素材
     * @param media 素材
     * @param type 类型
     * @param app 应用名
     * @return WeChatMediaUploadResponse
     */
    @PostMapping(value = "media/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, headers = WeChatClient.HEAD)
    WeChatMediaUploadResponse uploadMaterial(@RequestPart(value = "media") MultipartFile media, @RequestParam("type") String type, @RequestParam(WeChatClient.HEAD_KEY) String app);

    /**
     * 上传图片
     *
     * @param media 素材
     * @param app 应用名
     * @return UploadImageResponse
     */
    @PostMapping(value = "media/uploadimg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, headers = WeChatClient.HEAD)
    UploadImageResponse uploadImage(@RequestPart(value = "media") MultipartFile media, @RequestParam(WeChatClient.HEAD_KEY) String app);

    /**
     * 获取临时素材
     *
     * @param mediaId 素材编号
     * @param app 应用名
     * @return ResponseEntity
     */
    @GetMapping(value = "media/get", headers = WeChatClient.HEAD)
    ResponseEntity<byte[]> download(@RequestParam("media_id") String mediaId, @RequestParam(WeChatClient.HEAD_KEY) String app);

    /**
     * 获取高清语音素材
     *
     * @param mediaId 通过JSSDK的uploadVoice接口上传的语音文件id
     * @param app 应用名
     * @return ResponseEntity
     */
    @GetMapping(value = "media/get/jssdk", headers = WeChatClient.HEAD)
    ResponseEntity<byte[]> downloadJsMedia(@RequestParam("media_id") String mediaId, @RequestParam(WeChatClient.HEAD_KEY) String app);
}
