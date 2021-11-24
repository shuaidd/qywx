package com.github.shuaidd;

import com.github.shuaidd.response.kf.KfAccountListResponse;
import com.github.shuaidd.service.MediaService;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 描述 客服中心
 *
 * @author ddshuai
 * @date 2021-11-24 16:30
 **/
public class WeChatCallCenterServiceTest extends AbstractTest {

    public static final String appName = "wx-kf";

    /**
     * 添加客服人员 wkCRbQBwAAV_N3R2vqsNweyBbIHuJaRg
     */
    @Test
    public void addKf() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:image/20659972.png");
        String mediaId = weChatManager.mediaService().uploadMaterial(file, MediaType.APPLICATION_OCTET_STREAM, MediaService.IMAGE, appName);
        String kfId = weChatManager.callCenterService().addKfAccount("小二客服", mediaId, appName);
        logger.info("创建的客服编号-{}", kfId);
    }

    @Test
    public void getAccountList() {
        KfAccountListResponse response = weChatManager.callCenterService().getAccountList(appName);
        logger.info("客服列表-{}", response);
    }

    @Test
    public void kfContactWay() {
        String url = weChatManager.callCenterService().kfContactWay("wkCRbQBwAAV_N3R2vqsNweyBbIHuJaRg", "sdss3ed", appName);
        logger.info("客服联系地址-{}", url);
    }
}
