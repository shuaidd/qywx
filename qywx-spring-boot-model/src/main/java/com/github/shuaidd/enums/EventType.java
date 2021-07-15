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
    CHANGE_EXTERNAL_CHAT("change_external_chat","客户群变更通知"),
    CHANGE_EXTERNAL_TAG("change_external_tag","企业客户标签变更事件"),
    SUBSCRIBE("subscribe","成员关注事件"),
    UNSUBSCRIBE("unsubscribe","成员取消关注事件"),
    LOCATION("LOCATION","上报地理位置"),
    CLICK("click","菜单事件"),
    VIEW("view","菜单事件"),
    ENTER_AGENT("enter_agent","进入应用"),
    SCAN_CODE_PUSH("scancode_push","扫码推事件的事件推送"),
    SCAN_CODE_WAIT_MSG("scancode_waitmsg","扫码推事件且弹出“消息接收中”提示框的事件推送"),
    PIC_SYS_PHOTO("pic_sysphoto","弹出系统拍照发图的事件推送"),
    PIC_PHOTO_OR_ALBUM("pic_photo_or_album","弹出拍照或者相册发图的事件推送"),
    PIC_WEI_XIN("pic_weixin","弹出拍照或者相册发图的事件推送"),
    LOCATION_SELECT("location_select","弹出地理位置选择器的事件推送"),
    OPEN_APPROVAL_CHANGE("open_approval_change","审批状态通知事件"),
    TASK_CARD_CLICK("taskcard_click","任务卡片事件推送"),
    SHARE_AGENT_CHANGE("share_agent_change","共享应用事件回调"),
    MODIFY_CALENDAR("modify_calendar","修改日历事件"),
    DELETE_CALENDAR("delete_calendar","删除日历事件"),
    ADD_SCHEDULE("add_schedule","添加日程事件"),
    MODIFY_SCHEDULE("modify_schedule","修改日程事件"),
    DELETE_SCHEDULE("delete_schedule","删除日程事件"),
    LIVING_STATUS_CHANGE("living_status_change","直播回调事件"),
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
