package com.github.shuaidd.client;

import com.github.shuaidd.client.annotation.Part;
import com.github.shuaidd.file.QywxFile;
import com.github.shuaidd.response.payment.UploadPaymentImageResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 小程序对外收款图片上传
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public interface PaymentUploadClient extends CommonClient {

    /**
     * 提交小程序收款申请所需的图片
     *
     * @param image 图片文件
     * @param app   应用名
     * @return 微信支付图片 ID
     */
    @RequestLine("POST /miniapppay/upload_image?app={app}")
    @Headers(HEAD)
    UploadPaymentImageResponse uploadImage(@Part("image") QywxFile image, @Param("app") String app);
}
