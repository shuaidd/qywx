package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.message.MsgImage;
import com.github.shuaidd.dto.message.MsgLink;
import com.github.shuaidd.dto.message.MsgText;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-02 15:14
 **/
public class ConclusionData {
    private MsgText text;
    private MsgImage image;
    private MsgLink link;
    @JsonProperty("miniprogram")
    private MiniProgram miniProgram;

    public MsgText getText() {
        return text;
    }

    public void setText(MsgText text) {
        this.text = text;
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

    public MiniProgram getMiniProgram() {
        return miniProgram;
    }

    public void setMiniProgram(MiniProgram miniProgram) {
        this.miniProgram = miniProgram;
    }
}
