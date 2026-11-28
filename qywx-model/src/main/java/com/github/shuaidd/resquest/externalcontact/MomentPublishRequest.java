package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 企业发表内容到客户的朋友圈请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentPublishRequest {

    /**
     * 朋友圈文本内容
     */
    @JsonProperty("text")
    private MomentPublishText text;

    /**
     * 朋友圈附件列表；图片、视频和链接只能三选一
     */
    @JsonProperty("attachments")
    private List<MomentPublishAttachment> attachments;

    /**
     * 指定的发表范围；不填表示推送给应用可见范围内所有成员
     */
    @JsonProperty("visible_range")
    private MomentPublishVisibleRange visibleRange;

    public MomentPublishText getText() {
        return text;
    }

    public void setText(MomentPublishText text) {
        this.text = text;
    }

    public List<MomentPublishAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<MomentPublishAttachment> attachments) {
        this.attachments = attachments;
    }

    public MomentPublishVisibleRange getVisibleRange() {
        return visibleRange;
    }

    public void setVisibleRange(MomentPublishVisibleRange visibleRange) {
        this.visibleRange = visibleRange;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("text", text)
                .append("attachments", attachments)
                .append("visibleRange", visibleRange)
                .toString();
    }
}