package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.Comment;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-06 21:28
 **/
public class CommentsResponse extends AbstractBaseResponse {
    @JsonProperty("comment_list")
    private List<Comment> commentList;
    @JsonProperty("like_list")
    private List<Comment> likeList;

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Comment> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Comment> likeList) {
        this.likeList = likeList;
    }
}
