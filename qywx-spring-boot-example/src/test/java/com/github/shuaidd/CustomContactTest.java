package com.github.shuaidd;

import com.github.shuaidd.dto.CustomTag;
import com.github.shuaidd.response.*;
import com.github.shuaidd.resquest.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-13 13:43
 **/
public class CustomContactTest extends AbstractTest {

    /**
     * 客户联系功能编码 与配置文件内定义的别名【application-name】一致
     */
    public static final String appName = "customer-contact";

    /**
     * externalUserId = wmCRbQBwAA2XZEynsl_hDoWnL_Ptb04Q
     */
    @Test
    public void unassignedList() {
        PageRequest request = new PageRequest();
        UnassignedListResponse response = weChatManager.customContactService().unassignedList(request, appName);
        logger.info("{}", response);
    }

    @Test
    public void getFollowUserList() {
        FollowUserResponse followUserResponse = weChatManager.customContactService().getFollowUserList(appName);
        logger.info("{}", followUserResponse);
    }

    /**
     * configId=8378a992fb470b9d6a4654e50a66580e
     * qrCode=https://wework.qpic.cn/wwpic/426899_56J5rK9OTiOMuSN_1610518083/0
     */
    @Test
    public void addContactWay() {
        AddContactWayRequest request = new AddContactWayRequest();
        request.setType(2);
        request.setScene(2);
        request.setRemark("渠道客户");
        request.setState("channel-code");
        request.setUsers(Arrays.asList("CaoMuJiangDeXiaoLu", "20170410022717"));
        AddContactWayResponse response = weChatManager.customContactService().addContactWay(request, appName);
        logger.info("{}", response);
    }

    @Test
    public void getContactWay() {
        GetContactWayRequest request = new GetContactWayRequest();
        request.setConfigId("8378a992fb470b9d6a4654e50a66580e");
        ContactWayResponse response = weChatManager.customContactService().getContactWay(request, appName);
        logger.info("{}", response);
    }

    @Test
    public void updateContactWay() {
        UpdateContactWayRequest request = new UpdateContactWayRequest();
        request.setConfigId("8378a992fb470b9d6a4654e50a66580e");
        request.setRemark("更新一下备注信息");
        weChatManager.customContactService().updateContactWay(request, appName);
    }

    @Test
    public void deleteContactWay() {
        GetContactWayRequest request = new GetContactWayRequest();
        request.setConfigId("8378a992fb470b9d6a4654e50a66580e");
        weChatManager.customContactService().deleteContactWay(request, appName);
    }

    /**
     * groupId=etCRbQBwAAii_NXtxqQBMOnEuWV-_aEw
     */
    @Test
    public void addCorpTag() {
        AddCorpTagRequest request = new AddCorpTagRequest();
        request.setGroupName("渠道客户");

        CustomTag customTag = new CustomTag();
        customTag.setName("奇威客户");
        request.setTags(Collections.singletonList(customTag));
        AddCorpTagResponse response = weChatManager.customContactService().addCorpTag(request, appName);
        logger.info("{}", response);
    }

    @Test
    public void getCorpTagList() {
        TagGroupRequest request = new TagGroupRequest();
        TagGroupResponse response = weChatManager.customContactService().getCorpTagList(request, appName);
        logger.info("{}", response);
    }

    @Test
    public void markTag() {
        MarkTagRequest request = new MarkTagRequest();
        request.setUserId("20170410022717");
        request.setAddTags(Collections.singletonList("etCRbQBwAAoLAm885Ob1ODeCPwi0Mtug"));
        request.setExternalUserId("wmCRbQBwAAkqZa5eZQdUNFXgVIFr_DmA");
        weChatManager.customContactService().markTag(request, appName);
    }

    @Test
    public void getCustomList() {
        CustomListRequest request = new CustomListRequest();
        request.setUserId("20170410022717");
        CustomListResponse response = weChatManager.customContactService().getCustomList(request, appName);
        logger.info("{}", response);
    }

    @Test
    public void a() {

    }


}
