package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.UserId;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-19 10:18
 **/
public class ApprovalComment {

    private UserId commentUserInfo;

    @JsonProperty("commenttime")
    private Long commentTime;

    @JsonProperty("commentcontent")
    private String commentContent;

    @JsonProperty("commentid")
    private String commentId;

    @JsonProperty("media_id")
    private List<String> mediaIds;

    public UserId getCommentUserInfo() {
        return commentUserInfo;
    }

    public void setCommentUserInfo(UserId commentUserInfo) {
        this.commentUserInfo = commentUserInfo;
    }

    public Long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Long commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public List<String> getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(List<String> mediaIds) {
        this.mediaIds = mediaIds;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApprovalComment.class.getSimpleName() + "[", "]")
                .add("commentUserInfo=" + commentUserInfo)
                .add("commentTime=" + commentTime)
                .add("commentContent='" + commentContent + "'")
                .add("commentId='" + commentId + "'")
                .add("mediaIds=" + mediaIds)
                .toString();
    }
}
