package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.message.*;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-08-31 15:18
 **/
public class V2GroupMsg {

    @JsonProperty(value = "msgtype")
    private String msgType;
    private MsgImage image;
    private MsgLink link;
    private MiniProgram miniprogram;
    private MsgVideo video;
    private MsgFile file;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public MsgImage getImage() {
        return image;
    }

    public void setImage(MsgImage image) {
        this.image = image;
    }

    public MsgLink getLink() {
        return link;
    }

    public void setLink(MsgLink link) {
        this.link = link;
    }

    public MiniProgram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(MiniProgram miniprogram) {
        this.miniprogram = miniprogram;
    }

    public MsgVideo getVideo() {
        return video;
    }

    public void setVideo(MsgVideo video) {
        this.video = video;
    }

    public MsgFile getFile() {
        return file;
    }

    public void setFile(MsgFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", V2GroupMsg.class.getSimpleName() + "[", "]")
                .add("msgType='" + msgType + "'")
                .add("image=" + image)
                .add("link=" + link)
                .add("miniprogram=" + miniprogram)
                .add("video=" + video)
                .add("file=" + file)
                .toString();
    }
}
