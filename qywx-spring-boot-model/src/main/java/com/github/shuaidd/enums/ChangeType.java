package com.github.shuaidd.enums;

/**
 * 描述 企业微信 事件变更类型
 *
 * @author ddshuai
 * @date 2021-07-13 16:25
 **/
public enum  ChangeType {

    /**
     * 事件变更类型
     */
    CREATE_USER(EventType.CHANGE_CONTACT,"create_user","新增成员事件"),
    UPDATE_USER(EventType.CHANGE_CONTACT,"update_user","更新成员事件"),
    DELETE_USER(EventType.CHANGE_CONTACT,"delete_user","删除成员事件"),
    CREATE_PARTY(EventType.CHANGE_CONTACT,"create_party","新增部门事件"),
    UPDATE_PARTY(EventType.CHANGE_CONTACT,"update_party","更新部门事件"),
    DELETE_PARTY(EventType.CHANGE_CONTACT,"delete_party","删除部门事件"),
    UPDATE_TAG(EventType.CHANGE_CONTACT,"update_tag","标签变更通知"),
    ADD_EXTERNAL_CONTACT(EventType.CHANGE_EXTERNAL_CONTACT,"add_external_contact","添加企业客户事件")
    ;

    private final EventType eventType;
    private final String changeType;
    private final String changeTypeName;

    ChangeType(EventType eventType, String changeType, String changeTypeName) {
        this.eventType = eventType;
        this.changeType = changeType;
        this.changeTypeName = changeTypeName;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getChangeType() {
        return changeType;
    }

    public String getChangeTypeName() {
        return changeTypeName;
    }
}
