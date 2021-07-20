package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述 客户群事件
 *
 * @author ddshuai
 * date 2021-07-14 09:44
 **/
@XmlRootElement(name = "xml")
public class ExternalContactChatEventData extends BaseEventData {

    @XmlElement(name = "ChatId")
    private String chatId;

    @XmlElement(name = "UpdateDetail")
    private String updateDetail;

    @XmlElement(name = "JoinScene")
    private Integer joinScene;

    @XmlElement(name = "QuitScene")
    private Integer quitScene;

    @XmlElement(name = "MemChangeCnt")
    private Integer memChangeCnt;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUpdateDetail() {
        return updateDetail;
    }

    public void setUpdateDetail(String updateDetail) {
        this.updateDetail = updateDetail;
    }

    public Integer getJoinScene() {
        return joinScene;
    }

    public void setJoinScene(Integer joinScene) {
        this.joinScene = joinScene;
    }

    public Integer getQuitScene() {
        return quitScene;
    }

    public void setQuitScene(Integer quitScene) {
        this.quitScene = quitScene;
    }

    public Integer getMemChangeCnt() {
        return memChangeCnt;
    }

    public void setMemChangeCnt(Integer memChangeCnt) {
        this.memChangeCnt = memChangeCnt;
    }
}
