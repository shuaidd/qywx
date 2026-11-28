package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 企业朋友圈附件
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentPublishAttachment {

    /**
     * 附件类型；仅允许 image、video 或 link
     */
    @JsonProperty("msgtype")
    private String msgType;

    /**
     * 图片附件；msgType 为 image 时必填
     */
    @JsonProperty("image")
    private MomentPublishMedia image;

    /**
     * 视频附件；msgType 为 video 时必填
     */
    @JsonProperty("video")
    private MomentPublishMedia video;

    /**
     * 图文链接附件；msgType 为 link 时必填
     */
    @JsonProperty("link")
    private MomentPublishLink link;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public MomentPublishMedia getImage() {
        return image;
    }

    public void setImage(MomentPublishMedia image) {
        this.image = image;
    }

    public MomentPublishMedia getVideo() {
        return video;
    }

    public void setVideo(MomentPublishMedia video) {
        this.video = video;
    }

    public MomentPublishLink getLink() {
        return link;
    }

    public void setLink(MomentPublishLink link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("msgType", msgType)
                .append("image", image)
                .append("video", video)
                .append("link", link)
                .toString();
    }
}