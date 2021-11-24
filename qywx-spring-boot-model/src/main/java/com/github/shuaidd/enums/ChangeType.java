package com.github.shuaidd.enums;

/**
 * 描述 企业微信 事件变更类型
 *
 * @author ddshuai
 * date 2021-07-13 16:25
 **/
public enum ChangeType {

    /**
     * 通讯录事件变更类型
     */
    CREATE_USER(EventType.CHANGE_CONTACT, "create_user", "新增成员事件"),
    UPDATE_USER(EventType.CHANGE_CONTACT, "update_user", "更新成员事件"),
    DELETE_USER(EventType.CHANGE_CONTACT, "delete_user", "删除成员事件"),
    CREATE_PARTY(EventType.CHANGE_CONTACT, "create_party", "新增部门事件"),
    UPDATE_PARTY(EventType.CHANGE_CONTACT, "update_party", "更新部门事件"),
    DELETE_PARTY(EventType.CHANGE_CONTACT, "delete_party", "删除部门事件"),
    UPDATE_TAG(EventType.CHANGE_CONTACT, "update_tag", "标签变更通知"),


    BATCH_JOB_RESULT(EventType.BATCH_JOB_RESULT, "", "异步任务完成通知"),

    /**
     * 客户联系变更通知
     */
    ADD_EXTERNAL_CONTACT(EventType.CHANGE_EXTERNAL_CONTACT, "add_external_contact", "添加企业客户事件"),
    EDIT_EXTERNAL_CONTACT(EventType.CHANGE_EXTERNAL_CONTACT, "edit_external_contact", "编辑企业客户事件"),
    ADD_HALF_EXTERNAL_CONTACT(EventType.CHANGE_EXTERNAL_CONTACT, "add_half_external_contact", "外部联系人免验证添加成员事件"),
    DEL_EXTERNAL_CONTACT(EventType.CHANGE_EXTERNAL_CONTACT, "del_external_contact", "删除企业客户事件"),
    DEL_FOLLOW_USER(EventType.CHANGE_EXTERNAL_CONTACT, "del_follow_user", "删除跟进成员事件"),
    TRANSFER_FAIL(EventType.CHANGE_EXTERNAL_CONTACT, "transfer_fail", "客户接替失败事件"),

    /**
     * 客户群变更通知
     */
    CREATE_CHAT(EventType.CHANGE_EXTERNAL_CHAT, "create", "客户群创建事件"),
    UPDATE_CHAT(EventType.CHANGE_EXTERNAL_CHAT, "update", "客户群变更事件"),
    DISMISS_CHAT(EventType.CHANGE_EXTERNAL_CHAT, "dismiss", "客户群解散事件"),

    /**
     * 企业客户标签变更事件
     */
    CREATE_EXTERNAL_TAG(EventType.CHANGE_EXTERNAL_TAG, "create", "企业客户标签创建事件"),
    UPDATE_EXTERNAL_TAG(EventType.CHANGE_EXTERNAL_TAG, "update", "企业客户标签变更事件"),
    DELETE_EXTERNAL_TAG(EventType.CHANGE_EXTERNAL_TAG, "delete", "企业客户标签删除事件"),

    SUBSCRIBE(EventType.SUBSCRIBE, "", "关注"),
    UNSUBSCRIBE(EventType.UNSUBSCRIBE, "", "取消关注"),
    ENTER_AGENT(EventType.ENTER_AGENT, "", "进入应用"),
    LOCATION(EventType.LOCATION, "", "上报地理位置"),

    CLICK(EventType.CLICK, "", "菜单事件"),
    VIEW(EventType.VIEW, "", "菜单事件"),
    SCAN_CODE_PUSH(EventType.SCAN_CODE_PUSH, "", "扫码推事件的事件推送"),
    SCAN_CODE_WAIT_MSG(EventType.SCAN_CODE_WAIT_MSG, "", "扫码推事件且弹出“消息接收中”提示框的事件推送"),
    PIC_SYS_PHOTO(EventType.PIC_SYS_PHOTO, "", "扫码推事件且弹出“消息接收中”提示框的事件推送"),
    PIC_PHOTO_OR_ALBUM(EventType.PIC_PHOTO_OR_ALBUM, "", "弹出拍照或者相册发图的事件推送"),
    PIC_WEI_XIN(EventType.PIC_WEI_XIN, "", "弹出拍照或者相册发图的事件推送"),
    LOCATION_SELECT(EventType.LOCATION_SELECT, "", "弹出地理位置选择器的事件推送"),
    OPEN_APPROVAL_CHANGE(EventType.OPEN_APPROVAL_CHANGE, "", "审批状态通知事件"),
    SYS_APPROVAL_CHANGE(EventType.SYS_APPROVAL_CHANGE, "", "审批申请状态变化回调通知"),
    TASK_CARD_CLICK(EventType.TASK_CARD_CLICK, "", "任务卡片事件推送"),
    SHARE_AGENT_CHANGE(EventType.SHARE_AGENT_CHANGE, "", "共享应用事件回调"),

    MODIFY_CALENDAR(EventType.MODIFY_CALENDAR, "", "修改日历事件"),
    DELETE_CALENDAR(EventType.DELETE_CALENDAR, "", "删除日历事件"),
    ADD_SCHEDULE(EventType.ADD_SCHEDULE, "", "添加日程事件"),
    MODIFY_SCHEDULE(EventType.MODIFY_SCHEDULE, "", "修改日程事件"),
    DELETE_SCHEDULE(EventType.DELETE_SCHEDULE, "", "删除日程事件"),
    LIVING_STATUS_CHANGE(EventType.LIVING_STATUS_CHANGE, "", "直播回调事件"),
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
