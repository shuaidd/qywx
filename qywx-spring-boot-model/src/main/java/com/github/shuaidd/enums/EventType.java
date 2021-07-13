package com.github.shuaidd.enums;

/**
 * 企业微信事件类型
 * @author shuaidongdong
 */

public enum EventType {
    /**
     * 企业微信事件类型
     */
    CHANGE_CONTACT("change_contact","通讯录变更通知"),
    CHANGE_EXTERNAL_CONTACT("change_external_contact","外部联系人变更通知"),
    BATCH_JOB_RESULT("batch_job_result","异步任务完成通知");
    private final String event;
    private final String eventName;

    EventType(String event, String eventName) {
        this.event = event;
        this.eventName = eventName;
    }

    public String getEvent() {
        return event;
    }

    public String getEventName() {
        return eventName;
    }
}
