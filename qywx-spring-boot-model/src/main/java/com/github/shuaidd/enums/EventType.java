package com.github.shuaidd.enums;

/**
 * 企业微信事件类型
 * @author shuaidongdong
 */

public enum EventType {
    /**
     * 企业微信事件类型
     */
    CHANGE_CONTACT("change_contact","成员变更通知");
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
