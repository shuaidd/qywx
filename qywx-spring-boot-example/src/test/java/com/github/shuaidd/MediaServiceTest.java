package com.github.shuaidd;

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
        File file = weChatManager.mediaService().downloadFile("3S9f1Bg1H8D_dG4xuPWgaStwbec3AskA1eqBLWKb5GXysrq3Ik2LkuvL55-I41cdB",downloadDir.getAbsolutePath()+"/test.mp4", ADDRESS_BOOK);
        logger.info("下载的文件路径--> {}",file.getAbsolutePath());
    }

    @Test
    public void downloadJsMedia() {
        weChatManager.mediaService().downloadJsMedia("3S9f1Bg1H8D_dG4xuPWgaStwbec3AskA1eqBLWKb5GXysrq3Ik2LkuvL55-I41cdB", ADDRESS_BOOK);
    }

    @Test
    public void downloadJsMediaFile() {
        /*downloadFilePath 不填时 下载到临时文件夹*/
        weChatManager.mediaService().downloadJsMediaFile("3S9f1Bg1H8D_dG4xuPWgaStwbec3AskA1eqBLWKb5GXysrq3Ik2LkuvL55-I41cdB",null, ADDRESS_BOOK);
    }
}
