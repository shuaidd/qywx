package com.github.shuaidd;

import com.github.shuaidd.enums.MsgType;
import com.github.shuaidd.response.kf.*;
import com.github.shuaidd.resquest.kf.*;
import com.github.shuaidd.service.MediaService;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 描述 客服中心
 *
 * @author ddshuai
 * date 2021-11-24 16:30
 **/
public class WeChatCallCenterServiceTest extends AbstractTest {

    public static final String appName = "wx-kf";
    public static final String kfId = "wkCRbQBwAAQF-PTMPq6BLzSwK1098Ezg";

    /**
     * 添加客服人员 wkCRbQBwAAQF-PTMPq6BLzSwK1098Ezg
     */
    @Test
    public void addKf() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:image/20659972.png");
        String mediaId = weChatManager.mediaService().uploadMaterial(file, MediaType.APPLICATION_OCTET_STREAM, MediaService.IMAGE, appName);
        String kfId = weChatManager.callCenterService().addKfAccount("帅小二客服", mediaId, appName);
        logger.info("创建的客服编号-{}", kfId);
    }

    @Test
    public void updateKf() {
        KfAccountRequest request = new KfAccountRequest();
        request.setOpenKfId("wkCRbQBwAAV_N3R2vqsNweyBbIHuJaRg");
        request.setName("小六客服");
        weChatManager.callCenterService().updateKfAccount(request, appName);
        logger.info("更新客服信息成功");
    }

    @Test
    public void delKf() {
        weChatManager.callCenterService().delKfAccount("wkCRbQBwAAV_N3R2vqsNweyBbIHuJaRg", appName);
        logger.info("删除客服信息成功");
    }

    @Test
    public void getAccountList() {
        KfAccountListResponse response = weChatManager.callCenterService().getAccountList(appName);
        logger.info("客服列表-{}", response);
    }

    @Test
    public void kfContactWay() {
        String url = weChatManager.callCenterService().kfContactWay(kfId, "sdss3ed", appName);
        logger.info("客服联系地址-{}", url);
    }

    @Test
    public void addServicer() {
        ServicerRequest request = new ServicerRequest();
        request.setOpenKfId(kfId);
        request.setUserIds(Collections.singletonList("20170410022717"));
        weChatManager.callCenterService().addServicer(request, appName);
        logger.info("添加接待人员成功");
    }

    @Test
    public void delServicer() {
        ServicerRequest request = new ServicerRequest();
        request.setOpenKfId(kfId);
        request.setUserIds(Collections.singletonList("20170410022717"));
        weChatManager.callCenterService().delServicer(request, appName);
        logger.info("删除接待人员成功");
    }

    @Test
    public void getServicerList() {
        List<ServicerListResponse.Servicer> servicers = weChatManager.callCenterService().getServicerList(kfId, appName);
        logger.info("获取的接待人员列表--{}", servicers);
    }

    @Test
    public void getServiceState() {
        ServiceStateRequest request = new ServiceStateRequest();
        request.setExternalUserId("wmCRbQBwAAL_1TiinJjjTBnIaemb4yeg");
        request.setOpenKfId("wkCRbQBwAAV_N3R2vqsNweyBbIHuJaRg");
        ServiceStateResponse response = weChatManager.callCenterService().getServiceState(request, appName);
        logger.info("获取会话状态成功--{}", response);
    }

    @Test
    public void changeServiceState() {
        ChangeServiceStateRequest request = new ChangeServiceStateRequest();
        request.setExternalUserId("wmCRbQBwAAL_1TiinJjjTBnIaemb4yeg");
        request.setOpenKfId(kfId);
        request.setServiceState(3);
        request.setServicerUserId("20170410022717");
        String msgCode = weChatManager.callCenterService().changeServiceState(request, appName);
        logger.info("更新会话状态成功--{}", msgCode);
    }

    @Test
    public void syncMsg() {
        SyncMsgRequest request = new SyncMsgRequest();
        request.setToken("ENCHvQAn794mxmFqTTrJHoZCnkqPYgUCRpLBYbvoEk4bw3j");
        request.setLimit(100);
        SyncMsgResponse response = weChatManager.callCenterService().syncMsg(request, appName);
        logger.info("读取到了消息--{}", response);
    }

    @Test
    public void sendMsg() {
        SendMsgRequest request = new SendMsgRequest();
        request.setToUser("wmCRbQBwAAL_1TiinJjjTBnIaemb4yeg");
        request.setOpenKfId(kfId);
        request.setMsgType(MsgType.TEXT.getType());

        Map<String, String> map = Maps.newHashMap();
        map.put("content", "你购买的物品已发货，可点击链接查看物流状态http://work.weixin.qq.com/xxxxxx");
        request.setText(map);
        weChatManager.callCenterService().sendMsg(request, appName);
    }

    @Test
    public void sendMsgOnEvent() {
        SendMsgOnEventRequest request = new SendMsgOnEventRequest();
        weChatManager.callCenterService().sendMsgOnEvent(request, appName);
    }

    @Test
    public void getCustomer() {
        GetCustomerRequest request = new GetCustomerRequest();
        request.setExternalUserIdList(Collections.singletonList("wmCRbQBwAAL_1TiinJjjTBnIaemb4yeg"));
        GetCustomerResponse response = weChatManager.callCenterService().getCustomer(request, appName);
        logger.info("获取到的客户信息--{}", response);
    }

    @Test
    public void getUpgradeServiceConfig() {
        UpgradeServiceConfigResponse response = weChatManager.callCenterService().getUpgradeServiceConfig(appName);
        logger.info("获取到的配置信息--{}", response);
    }

    @Test
    public void upgradeService() {
        UpgradeServiceRequest request = new UpgradeServiceRequest();
        request.setExternalUserId("wmCRbQBwAAL_1TiinJjjTBnIaemb4yeg");
        request.setOpenKfId(kfId);
        request.setType(1);
        UpgradeServiceRequest.Member member = new UpgradeServiceRequest.Member();
        member.setUserId("20170410022717");
        member.setWording("欢迎光临");
        request.setMember(member);
        weChatManager.callCenterService().upgradeService(request, appName);
    }

    @Test
    public void cancelUpgradeService() {
        CancelUpgradeServiceRequest request = new CancelUpgradeServiceRequest();
        request.setExternalUserId("wmCRbQBwAAL_1TiinJjjTBnIaemb4yeg");
        request.setOpenKfId(kfId);
        weChatManager.callCenterService().cancelUpgradeService(request, appName);
    }
}
