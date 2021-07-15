package com.github.shuaidd;

import com.github.shuaidd.dto.CustomTag;
import com.github.shuaidd.dto.message.MsgText;
import com.github.shuaidd.response.*;
import com.github.shuaidd.resquest.*;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-13 13:43
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

    @Test
    public void closeTempChat() {
        CloseTempChatRequest request = new CloseTempChatRequest();
        request.setExternalUserId("wmCRbQBwAAkqZa5eZQdUNFXgVIFr_DmA");
        request.setUserId("20170410022717");
        weChatManager.customContactService().closeTempChat(request, appName);
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
    public void editCorpTag() {
        EditCorpTagRequest request = new EditCorpTagRequest();
        request.setId("etCRbQBwAAl2--7nYobBfKpM9UxPSNeg");
        request.setName("奇威客户-edit");
        weChatManager.customContactService().editCorpTag(request, appName);
    }

    @Test
    public void delCorpTag() {
        DelCorpTagRequest request = new DelCorpTagRequest();
        request.setTagIds(Collections.singletonList("etCRbQBwAAl2--7nYobBfKpM9UxPSNeg"));
        weChatManager.customContactService().delCorpTag(request, appName);
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
    public void transfer() {
        TransferRequest request = new TransferRequest();
        request.setHandoverUserId("20170410022717");
        request.setTakeoverUserId("SiGuangMeng");
        request.setExternalUserId("wmCRbQBwAAkqZa5eZQdUNFXgVIFr_DmA");
        request.setTransferSuccessMsg("您好，您的服务已升级，后续将由我的同事李四@腾讯接替我的工作，继续为您服务。");
        weChatManager.customContactService().transfer(request, appName);
    }

    @Test
    public void getTransferResult() {
        TransferResultRequest request = new TransferResultRequest();
        request.setHandoverUserId("20170410022717");
        request.setTakeoverUserId("SiGuangMeng");
        request.setExternalUserId("wmCRbQBwAAkqZa5eZQdUNFXgVIFr_DmA");
        TransferResultResponse response = weChatManager.customContactService().getTransferResult(request, appName);
        logger.info("{}", response);
    }

    @Test
    public void getExternalContact() {
        ExternalContactResponse response = weChatManager.customContactService().getExternalContact("wmCRbQBwAAkqZa5eZQdUNFXgVIFr_DmA", appName);
        logger.info("{}", response);
    }

    @Test
    public void getBatchExternalContact() {
        BatchExternalContactRequest request = new BatchExternalContactRequest();
        request.setUserId("20170410022717");
        BatchExternalContactResponse response = weChatManager.customContactService().getBatchExternalContact(request, appName);
        logger.info("{}", response);
    }

    @Test
    public void updateCustomRemark() {
        UpdateRemarkRequest remarkRequest = new UpdateRemarkRequest();
        remarkRequest.setRemark("修改客户备注信息");
        remarkRequest.setExternalUserId("wmCRbQBwAAkqZa5eZQdUNFXgVIFr_DmA");
        remarkRequest.setUserId("20170410022717");
        weChatManager.customContactService().updateCustomRemark(remarkRequest, appName);
    }

    @Test
    public void getMobileHashcode() {
        MobileHashCodeRequest request = new MobileHashCodeRequest();
        request.setMobile("152xxxxxxxx");
        MobileHashCodeResponse response = weChatManager.customContactService().getMobileHashcode(request, appName);
        logger.info("{}", response);
    }


    /**
     * chatId = wrCRbQBwAAoFDTwM9TY8_tZpG8SJvS1Q
     */
    @Test
    public void groupChatList() {
        GroupChatListRequest request = new GroupChatListRequest();
        request.setLimit(100);
        GroupChatListResponse response = weChatManager.customContactService().groupChatList(request, appName);
        logger.info("{}", response);
    }

    @Test
    public void groupChatDetail() {
        GroupChatDetailRequest request = new GroupChatDetailRequest();
        request.setChatId("wrCRbQBwAAoFDTwM9TY8_tZpG8SJvS1Q");
        GroupChatDetailResponse response = weChatManager.customContactService().groupChatDetail(request, appName);
        logger.info("{}", response);
    }

    @Test
    public void getMomentList() throws ParseException {
        MomentListRequest request = new MomentListRequest();
        request.setStartTime(DateUtils.parseDate("2021-01-13", "yyyy-MM-dd"));
        request.setEndTime(DateUtils.parseDate("2021-01-15", "yyyy-MM-dd"));
        MomentListResponse response = weChatManager.customContactService().getMomentList(request, appName);
        logger.info("{}", response);
    }

    /**
     * msgId = msgCRbQBwAAUfGe9nf4saYGTPgAobR8jA
     */
    @Test
    public void addMsgTemplate() {
        MsgTemplateRequest request = new MsgTemplateRequest();
        request.setChatType("single");
        request.setExternalUserId(Collections.singletonList("wmCRbQBwAAkqZa5eZQdUNFXgVIFr_DmA"));
        MsgText text = new MsgText("测试消息发送");
        request.setText(text);
        MsgTemplateResponse response = weChatManager.customContactService().addMsgTemplate(request, appName);
        logger.info("{}", response);
    }

    @Test
    public void getGroupMsgList() throws ParseException {
        GroupMsgRequest request = new GroupMsgRequest();
        request.setStartTime(DateUtils.parseDate("2021-01-13", "yyyy-MM-dd"));
        request.setEndTime(DateUtils.parseDate("2021-01-14", "yyyy-MM-dd"));
        request.setChatType("single");
        GroupMsgResponse response = weChatManager.customContactService().getGroupMsgList(request, appName);
        logger.info("{}", response);
    }
}
