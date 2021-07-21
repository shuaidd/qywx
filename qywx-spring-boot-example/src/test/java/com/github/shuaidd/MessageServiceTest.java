package com.github.shuaidd;

import com.github.shuaidd.dto.message.*;
import com.github.shuaidd.enums.MsgType;
import com.github.shuaidd.resquest.message.SendMessageRequest;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collections;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-21 12:49
 **/
public class MessageServiceTest extends AbstractTest {

    public static final String OY = "oy";

    @Test
    public void sendTextCardMsg() {

        /*
         * 文本卡片消息
         */
        SendMessageRequest textCardRequest = buildSendMessageRequest();
        textCardRequest.setMsgType(MsgType.TEXTCARD);

        MsgTextCard textCard = new MsgTextCard();
        textCard.setTitle("领奖通知");
        textCard.setDescription("<div class=\"gray\">2016年9月26日</div> <div class=\"normal\">恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div class=\"highlight\">请于2016年10月10日前联系行政同事领取</div>");
        textCard.setUrl("http://www.baidu.com");
        textCard.setBtntxt("更多");
        textCardRequest.setTextcard(textCard);

        weChatManager.messageService().sendMessage(textCardRequest, OY);
    }

    @Test
    public void sendTxtMsg() {
        /*
         * 文本消息
         */
        SendMessageRequest textRequest = buildSendMessageRequest();
        textRequest.setMsgType(MsgType.TEXT);

        MsgText text = new MsgText();
        text.setContent("明天下午五点开会");
        textRequest.setText(text);
        weChatManager.messageService().sendMessage(textRequest, OY);
    }

    @Test
    public void sendFileMsg() {

        /*
         * 文件消息
         */
        SendMessageRequest fileRequest = buildSendMessageRequest();
        fileRequest.setMsgType(MsgType.FILE);

        MsgFile msgFile = new MsgFile();
        msgFile.setMediaId("3bQm1Wvbn2QIcxDsWcVlkqsxV8CsVryX1k2cBKTXkO7U");
        fileRequest.setFile(msgFile);

        weChatManager.messageService().sendMessage(fileRequest, OY);
    }

    @Test
    public void sendImgMsg() {

        /*
         * 图片消息
         */
        SendMessageRequest imgRequest = buildSendMessageRequest();
        imgRequest.setMsgType(MsgType.IMAGE);

        MsgImage imgFile = new MsgImage();
        imgFile.setMediaId("3vTVBKnGTItDyDlt-x4mXImcfCQ_tl2fGbi9wTwtOu0I");
        imgRequest.setImage(imgFile);

        weChatManager.messageService().sendMessage(imgRequest, OY);
    }

    @Test
    public void sendVoiceMsg() {

        /*
         * 语音消息
         */
        SendMessageRequest voiceRequest = buildSendMessageRequest();
        voiceRequest.setMsgType(MsgType.VOICE);

        MsgVoice voiceFile = new MsgVoice();
        voiceFile.setMediaId("3F7poS9bUWHXwbIGuJ3FXf5xLa5Uk770IqBKBX93KMS8");
        voiceRequest.setVoice(voiceFile);

        weChatManager.messageService().sendMessage(voiceRequest, OY);
    }

    @Test
    public void sendVideoMsg() {

        /*
         * 视频消息
         */
        SendMessageRequest videoRequest = buildSendMessageRequest();
        videoRequest.setMsgType(MsgType.VIDEO);

        MsgVideo videoFile = new MsgVideo();
        videoFile.setMediaId("3p8NHhN5H2_P5YcOP494KnLliqNHYNn_d90GIDDJtD81dn-A-MRu5Qnt8Z9WT0z7a");
        videoRequest.setVideo(videoFile);

        weChatManager.messageService().sendMessage(videoRequest, OY);
    }

    @Test
    public void sendNewsMsg() {

        /*
         * 图文消息
         */
        SendMessageRequest request = buildSendMessageRequest();
        request.setMsgType(MsgType.NEWS);

        MsgNews msgNews = new MsgNews();
        NewsArticle article = new NewsArticle();
        article.setDescription("今年中秋节公司有豪礼相送");
        article.setTitle("中秋节礼品领取");
        article.setUrl("http://www.baidu.com");
        article.setPicUrl("https://wework.qpic.cn/wwpic/495538_5AazgN0JRoCFypk_1626843955/0");
        msgNews.setArticles(Collections.singletonList(article));
        request.setNews(msgNews);

        weChatManager.messageService().sendMessage(request, OY);
    }

    @Test
    public void sendMarkDownMsg() {

        /*
         * 视频消息
         */
        SendMessageRequest request = buildSendMessageRequest();
        request.setMsgType(MsgType.MARKDOWN);

        MsgMarkdown msgMarkdown = new MsgMarkdown("您的会议室已经预定，稍后会同步到`邮箱` " +
                "\n> **事项详情** " +
                "\n> 事　项：<font color=\\\"info\\\">开会</font> ");
        request.setMarkdown(msgMarkdown);

        weChatManager.messageService().sendMessage(request, OY);
    }

    /**
     * 应用必须要配置事件回调地址，才可以发送此类消息
     */
    @Test
    public void sendInteractiveTaskCardMsg() {

        /*
         * 任务卡片消息
         */
        SendMessageRequest request = buildSendMessageRequest();
        request.setMsgType(MsgType.INTERACTIVE_TASK_CARD);

        InteractiveTaskCard taskCard = new InteractiveTaskCard();
        taskCard.setTitle("赵明登的礼物申请");
        taskCard.setDescription("礼品：A31茶具套装\\n用途：赠与小黑科技张总经理");
        taskCard.setUrl("http://www.baidu.com");
        taskCard.setTaskId("20210721151326");

        TaskCardBtn btn = new TaskCardBtn();
        btn.setKey("key111");
        btn.setName("批准");
        btn.setBold(true);
        btn.setColor("red");

        TaskCardBtn disbtn = new TaskCardBtn();
        disbtn.setKey("key1112");
        disbtn.setName("驳回");
        taskCard.setBtn(Lists.newArrayList(btn,disbtn));

        request.setInteractiveTaskCard(taskCard);

        weChatManager.messageService().sendMessage(request, OY);
    }


    private SendMessageRequest buildSendMessageRequest() {
        SendMessageRequest request = new SendMessageRequest();
        request.setAgentId("1000010");
        request.setToUser("20170410022717");

        return request;
    }
}
