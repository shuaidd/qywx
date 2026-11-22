package com.github.shuaidd.client;

import com.github.shuaidd.client.config.MultipartSupportConfig;
import com.github.shuaidd.response.payment.UploadPaymentImageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 小程序对外收款图片上传
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@FeignClient(name = "PaymentUploadClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = MultipartSupportConfig.class)
public interface PaymentUploadClient extends CommonClient {

    /**
     * 提交小程序收款申请所需的图片
     *
     * @param image 图片文件
     * @param app   应用名
     * @return 微信支付图片 ID
     */
    @PostMapping(value = "/miniapppay/upload_image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, headers = HEAD)
    UploadPaymentImageResponse uploadImage(@RequestPart(value = "image") MultipartFile image, @RequestParam(HEAD_KEY) String app);
}
