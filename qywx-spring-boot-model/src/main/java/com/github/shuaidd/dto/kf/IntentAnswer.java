package com.github.shuaidd.dto.kf;

import com.github.shuaidd.dto.externalcontact.V2GroupMsg;
import com.github.shuaidd.dto.message.MsgText;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-01 17:12
 **/
public class IntentAnswer {

    private MsgText text;

    private List<V2GroupMsg> attachments;

    public MsgText getText() {
        return text;
    }

    public void setText(MsgText text) {
        this.text = text;
    }

    public List<V2GroupMsg> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<V2GroupMsg> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", IntentAnswer.class.getSimpleName() + "[", "]")
                .add("text=" + text)
                .add("attachments=" + attachments)
                .toString();
    }
}
