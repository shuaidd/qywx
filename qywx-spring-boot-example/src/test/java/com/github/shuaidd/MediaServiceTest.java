package com.github.shuaidd;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.github.shuaidd.response.material.UploadByUrlResultResponse;
import com.github.shuaidd.resquest.job.JobIdRequest;
import com.github.shuaidd.resquest.media.UploadByUrlRequest;
import com.github.shuaidd.service.MediaService;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 描述 素材管理测试
 *
 * @author ddshuai
 * date 2021-07-21 10:48
 **/
public class MediaServiceTest extends AbstractTest {

    public static final String ADDRESS_BOOK = "address-book";

    /**
     * 客户联系功能编码 与配置文件内定义的别名【application-name】一致
     */
    public static final String CUSTOMER_CONTACT = "customer-contact";

    /**
     * 获取的结果 --- https://wework.qpic.cn/wwpic/495538_5AazgN0JRoCFypk_1626843955/0
     *
     * @throws FileNotFoundException 404
     */
    @Test
    public void upload() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:image/20659972.png");
        String url = weChatManager.mediaService().uploadImage(file, MediaType.IMAGE_PNG, ADDRESS_BOOK);
        logger.info("上传的图片地址--{}", url);
    }

    /**
     * 媒体文件类型，分别有图片（image）、语音（voice）、视频（video），普通文件（file）
     *
     * @throws FileNotFoundException 404
     */
    @Test
    public void uploadMaterial() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:video/1626849301272424.mp4");
        String mediaId = weChatManager.mediaService().uploadMaterial(file, MediaType.APPLICATION_OCTET_STREAM, MediaService.VIDEO, ADDRESS_BOOK);
        logger.info("上传的临时素材 media id--{}", mediaId);
    }

    @Test
    public void download() throws IOException {
        ResponseEntity<byte[]> responseEntity = weChatManager.mediaService().download("3S9f1Bg1H8D_dG4xuPWgaStwbec3AskA1eqBLWKb5GXysrq3Ik2LkuvL55-I41cdB", ADDRESS_BOOK);
        IOUtils.write(responseEntity.getBody(), new FileOutputStream(new File("download.mp4")));
    }

    @Test
    public void downloadFile() throws IOException {
        File downloadDir = ResourceUtils.getFile("classpath:download");
        File file = weChatManager.mediaService().downloadFile("3S9f1Bg1H8D_dG4xuPWgaStwbec3AskA1eqBLWKb5GXysrq3Ik2LkuvL55-I41cdB", downloadDir.getAbsolutePath() + "/test.mp4", ADDRESS_BOOK);
        logger.info("下载的文件路径--> {}", file.getAbsolutePath());
    }

    @Test
    public void downloadJsMedia() {
        ResponseEntity<byte[]> response = weChatManager.mediaService().downloadJsMedia("3S9f1Bg1H8D_dG4xuPWgaStwbec3AskA1eqBLWKb5GXysrq3Ik2LkuvL55-I41cdB", ADDRESS_BOOK);
    }

    @Test
    public void downloadJsMediaFile() {
        /*downloadFilePath 不填时 下载到临时文件夹*/
        File file = weChatManager.mediaService().downloadJsMediaFile("3S9f1Bg1H8D_dG4xuPWgaStwbec3AskA1eqBLWKb5GXysrq3Ik2LkuvL55-I41cdB", null, ADDRESS_BOOK);
    }

    /**
     * 场景值。1-客户联系入群欢迎语素材（目前仅支持1）。
     * 注意：每个场景值有对应的使用范围，详见上面的「使用场景说明」
     * <p>
     * 媒体文件类型。目前仅支持video-视频，file-普通文件
     * 不超过32字节。
     */
    @Test
    public void uploadByUrl() throws FileNotFoundException {
        File uploadFile = ResourceUtils.getFile("classpath:upload/运单导入模板.xls");
        String md5 = DigestUtil.md5Hex(uploadFile);
        UploadByUrlRequest request = new UploadByUrlRequest();
        request.setScene(1);
        request.setType("file");
        request.setFileName("出口商品归类管理.xlsx");
        request.setUrl("https://cftsp-public.oss-cn-beijing.aliyuncs.com/template/%E8%BF%90%E5%8D%95%E5%AF%BC%E5%85%A5%E6%A8%A1%E6%9D%BF.xls");
        request.setMd5(md5);
        String jobId = weChatManager.mediaService().uploadByUrl(request, CUSTOMER_CONTACT);
        logger.info("任务编号--{}", jobId);
    }

    /**
     * mediaId:  3*1*NDFqtsazjPS7U2vpyi7mretLBp8yGM9l2AtKJjIwyx06i6jf9tvrj3-ot-dB3zlK
     */
    @Test
    public void getUploadByUrlResult() {
        JobIdRequest request = new JobIdRequest();
        request.setJobId("job1666598429_IgpuZSMkLtSrtQ3iXb4NC8BlD9WozvvoZ4WzVn9Kvkk");
        UploadByUrlResultResponse response = weChatManager.mediaService().getUploadByUrlResult(request, CUSTOMER_CONTACT);
        logger.info("{}", response.getDetail().getCreatedAt());
    }
}
