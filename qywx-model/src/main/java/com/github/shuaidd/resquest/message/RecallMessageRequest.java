package com.github.shuaidd.resquest.message;

/**
 * 撤回应用消息请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class RecallMessageRequest {

    private String msgid;

    public String getMsgid() { return msgid; }
    public void setMsgid(String msgid) { this.msgid = msgid; }
}