package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.shuaidd.dto.message.MsgImage;
import com.github.shuaidd.dto.message.MsgLink;
import com.github.shuaidd.dto.message.MsgText;
import com.github.shuaidd.json.Long2DateDeserializer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-05 21:22
 **/
public class MomentDetail {
    @JsonProperty("moment_id")
    private String momentId;
    private String creator;
    @JsonProperty("create_time")
    @JsonDeserialize(using = Long2DateDeserializer.class)
    private Date createTime;
    @JsonProperty("create_type")
    private Integer createType;
    @JsonProperty("visible_type")
    private Integer visibleType;
    private MsgText text;
    @JsonProperty("image")
    private List<MsgImage> images;
    private MomentVideo video;
    private MsgLink link;
    private MomentLocation location;

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateType() {
        return createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    public Integer getVisibleType() {
        return visibleType;
    }

    public void setVisibleType(Integer visibleType) {
        this.visibleType = visibleType;
    }

    public MsgText getText() {
        return text;
    }

    public void setText(MsgText text) {
        this.text = text;
    }

    public List<MsgImage> getImages() {
        return images;
    }

    public void setImages(List<MsgImage> images) {
        this.images = images;
    }

    public MomentVideo getVideo() {
        return video;
    }

    public void setVideo(MomentVideo video) {
        this.video = video;
    }

    public MsgLink getLink() {
        return link;
    }

    public void setLink(MsgLink link) {
        this.link = link;
    }

    public MomentLocation getLocation() {
        return location;
    }

    public void setLocation(MomentLocation location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("momentId", momentId)
                .append("creator", creator)
                .append("createTime", createTime)
                .append("createType", createType)
                .append("visibleType", visibleType)
                .append("text", text)
                .append("images", images)
                .append("video", video)
                .append("link", link)
                .append("location", location)
                .toString();
    }
}
