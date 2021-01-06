package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.MiniProgram;
import com.github.shuaidd.dto.MsgImage;
import com.github.shuaidd.dto.MsgLink;
import com.github.shuaidd.dto.MsgText;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-06 22:23
 **/
public class WelcomeMsgRequest {
    @JsonProperty("welcome_code")
    private String welcomeCode;
    private MsgText text;
    private MsgLink link;
    private MsgImage image;
    private MiniProgram miniprogram;

    public String getWelcomeCode() {
        return welcomeCode;
    }

    public void setWelcomeCode(String welcomeCode) {
        this.welcomeCode = welcomeCode;
    }

    public MsgText getText() {
        return text;
    }

    public void setText(MsgText text) {
        this.text = text;
    }

    public MsgLink getLink() {
        return link;
    }

    public void setLink(MsgLink link) {
        this.link = link;
    }

    public MsgImage getImage() {
        return image;
    }

    public void setImage(MsgImage image) {
        this.image = image;
    }

    public MiniProgram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(MiniProgram miniprogram) {
        this.miniprogram = miniprogram;
    }
}
