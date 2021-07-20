package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 描述 审批备注
 *
 * @author ddshuai
 *date 2021-07-19 09:14
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class ApprovalComment {

    @XmlElement(name = "CommentUserInfo")
    private XmlUserId commentUserInfo;

    @XmlElement(name = "CommentTime")
    private Long commentTime;

    @XmlElement(name = "CommentContent")
    private String commentContent;

    @XmlElement(name = "CommentId")
    private String commentId;

    @XmlElement(name = "Attach")
    private String attach;

    public XmlUserId getCommentUserInfo() {
        return commentUserInfo;
    }

    public void setCommentUserInfo(XmlUserId commentUserInfo) {
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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
