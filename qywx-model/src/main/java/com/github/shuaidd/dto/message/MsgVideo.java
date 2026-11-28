package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-08 17:22
 **/
public class MsgVideo {
    @JsonProperty("media_id")
    private String mediaId;

    private String title;

    private String description;

    private String name;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MsgVideo.class.getSimpleName() + "[", "]")
                .add("mediaId='" + mediaId + "'")
                .add("title='" + title + "'")
                .add("description='" + description + "'")
                .add("name='" + name + "'")
                .toString();
    }
}
