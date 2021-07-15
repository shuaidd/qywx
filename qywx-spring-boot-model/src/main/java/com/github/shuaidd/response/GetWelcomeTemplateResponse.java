package com.github.shuaidd.response;

import com.github.shuaidd.dto.MiniProgram;
import com.github.shuaidd.dto.message.MsgImage;
import com.github.shuaidd.dto.message.MsgLink;
import com.github.shuaidd.dto.message.MsgText;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-06 22:35
 **/
public class GetWelcomeTemplateResponse extends AbstractBaseResponse {
    private MsgText text;
    private MsgImage image;
    private MsgLink link;
    private MiniProgram miniprogram;

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

    public MiniProgram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(MiniProgram miniprogram) {
        this.miniprogram = miniprogram;
    }
}
