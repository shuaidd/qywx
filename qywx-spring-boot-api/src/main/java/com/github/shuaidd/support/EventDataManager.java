package com.github.shuaidd.support;

import com.github.shuaidd.enums.ChangeType;
import com.github.shuaidd.enums.MsgType;
import com.github.shuaidd.event.*;
import com.github.shuaidd.exception.WeChatException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Objects;

/**
 * 描述 企业微信 事件回调数据管理
 *
 * @author ddshuai
 * date 2021-07-13 16:41
 **/
public class EventDataManager {

    private static final Logger logger = LoggerFactory.getLogger(EventDataManager.class);
    private static final HashMap<ChangeType, Class<? extends BaseEventData>> DATA_TYPE_MAP = new HashMap<>();

    static {

        DATA_TYPE_MAP.put(ChangeType.CREATE_USER, UserEventData.class);
        DATA_TYPE_MAP.put(ChangeType.UPDATE_USER, UserEventData.class);
        DATA_TYPE_MAP.put(ChangeType.DELETE_USER, DeleteUserEventData.class);
        DATA_TYPE_MAP.put(ChangeType.CREATE_PARTY, UnitEventData.class);
        DATA_TYPE_MAP.put(ChangeType.UPDATE_PARTY, UnitEventData.class);
        DATA_TYPE_MAP.put(ChangeType.DELETE_PARTY, UnitEventData.class);
        DATA_TYPE_MAP.put(ChangeType.UPDATE_TAG, TagChangeEventData.class);
        DATA_TYPE_MAP.put(ChangeType.BATCH_JOB_RESULT, BatchJobResultEventData.class);

        DATA_TYPE_MAP.put(ChangeType.ADD_EXTERNAL_CONTACT, ExternalContactEventData.class);
        DATA_TYPE_MAP.put(ChangeType.EDIT_EXTERNAL_CONTACT, ExternalContactEventData.class);
        DATA_TYPE_MAP.put(ChangeType.ADD_HALF_EXTERNAL_CONTACT, ExternalContactEventData.class);
        DATA_TYPE_MAP.put(ChangeType.DEL_EXTERNAL_CONTACT, ExternalContactEventData.class);
        DATA_TYPE_MAP.put(ChangeType.DEL_FOLLOW_USER, ExternalContactEventData.class);
        DATA_TYPE_MAP.put(ChangeType.TRANSFER_FAIL, ExternalContactEventData.class);

        DATA_TYPE_MAP.put(ChangeType.CREATE_CHAT, ExternalContactChatEventData.class);
        DATA_TYPE_MAP.put(ChangeType.UPDATE_CHAT, ExternalContactChatEventData.class);
        DATA_TYPE_MAP.put(ChangeType.DISMISS_CHAT, ExternalContactChatEventData.class);

        DATA_TYPE_MAP.put(ChangeType.CREATE_EXTERNAL_TAG, ExternalTagEventData.class);
        DATA_TYPE_MAP.put(ChangeType.UPDATE_EXTERNAL_TAG, ExternalTagEventData.class);
        DATA_TYPE_MAP.put(ChangeType.DELETE_EXTERNAL_TAG, ExternalTagEventData.class);

        DATA_TYPE_MAP.put(ChangeType.SUBSCRIBE, CommonEventData.class);
        DATA_TYPE_MAP.put(ChangeType.UNSUBSCRIBE, CommonEventData.class);
        DATA_TYPE_MAP.put(ChangeType.ENTER_AGENT, CommonEventData.class);
        DATA_TYPE_MAP.put(ChangeType.LOCATION, LocationEventData.class);

        DATA_TYPE_MAP.put(ChangeType.CLICK, CommonEventData.class);
        DATA_TYPE_MAP.put(ChangeType.VIEW, CommonEventData.class);

        DATA_TYPE_MAP.put(ChangeType.SCAN_CODE_PUSH, ScanCodeEventData.class);
        DATA_TYPE_MAP.put(ChangeType.SCAN_CODE_WAIT_MSG, ScanCodeEventData.class);

        DATA_TYPE_MAP.put(ChangeType.PIC_SYS_PHOTO, PicSysPhotoEventData.class);
        DATA_TYPE_MAP.put(ChangeType.PIC_PHOTO_OR_ALBUM, PicSysPhotoEventData.class);
        DATA_TYPE_MAP.put(ChangeType.PIC_WEI_XIN, PicSysPhotoEventData.class);
        DATA_TYPE_MAP.put(ChangeType.LOCATION_SELECT, LocationSelectEventData.class);
        DATA_TYPE_MAP.put(ChangeType.OPEN_APPROVAL_CHANGE, OpenApprovalChangeEventData.class);
        DATA_TYPE_MAP.put(ChangeType.SYS_APPROVAL_CHANGE, SysApprovalChangeEventData.class);
        DATA_TYPE_MAP.put(ChangeType.TASK_CARD_CLICK, TaskCardEventData.class);
        DATA_TYPE_MAP.put(ChangeType.SHARE_AGENT_CHANGE, CommonEventData.class);

        DATA_TYPE_MAP.put(ChangeType.MODIFY_CALENDAR, CalendarEventData.class);
        DATA_TYPE_MAP.put(ChangeType.DELETE_CALENDAR, CalendarEventData.class);

        DATA_TYPE_MAP.put(ChangeType.ADD_SCHEDULE, ScheduleEventData.class);
        DATA_TYPE_MAP.put(ChangeType.MODIFY_SCHEDULE, ScheduleEventData.class);
        DATA_TYPE_MAP.put(ChangeType.DELETE_SCHEDULE, ScheduleEventData.class);

        DATA_TYPE_MAP.put(ChangeType.LIVING_STATUS_CHANGE, LivingStatusChangeEventData.class);

        DATA_TYPE_MAP.put(ChangeType.KF_MSG_OR_EVENT,KfMsgOrEventData.class);
    }

    public static <T extends BaseEventData> T getXmlData(String xml, Class<T> tClass) {
        if (StringUtils.isEmpty(xml)) {
            return null;
        }

        CommonEventData commonEventData = XMLUtil.convertXmlStrToObject(CommonEventData.class, xml);
        if (StringUtils.isEmpty(commonEventData.getChangeType())) {
            return null;
        }

        return XMLUtil.convertXmlStrToObject(tClass, xml);
    }

    public static BaseEventData getXmlData(String xml) {
        if (StringUtils.isEmpty(xml)) {
            return null;
        }

        CommonEventData commonEventData = XMLUtil.convertXmlStrToObject(CommonEventData.class, xml);

        Class<? extends BaseEventData> cls = null;
        if (MsgType.TEXT.getType().equals(commonEventData.getMsgType())) {
            //文本消息
            cls = TextMsgEventData.class;
        }

        if (MsgType.IMAGE.getType().equals(commonEventData.getMsgType())) {
            //图片消息
            cls = ImageMsgEventData.class;
        }

        if (MsgType.VOICE.getType().equals(commonEventData.getMsgType())) {
            //语音消息
            cls = VoiceMsgEventData.class;
        }

        if (MsgType.VIDEO.getType().equals(commonEventData.getMsgType())) {
            //视频消息
            cls = VideoMsgEventData.class;
        }

        if (MsgType.LOCATION.getType().equals(commonEventData.getMsgType())) {
            //位置消息
            cls = LocationMsgEventData.class;
        }

        if (MsgType.LINK.getType().equals(commonEventData.getMsgType())) {
            //链接消息
            cls = LinkMsgEventData.class;
        }

        if (MsgType.EVENT.getType().equals(commonEventData.getMsgType())) {
            for (ChangeType changeType : DATA_TYPE_MAP.keySet()) {
                if (changeType.getEventType().getEvent().equals(commonEventData.getEvent())) {
                    if (StringUtils.isNotEmpty(changeType.getChangeType())) {
                        if (changeType.getChangeType().equals(commonEventData.getChangeType())) {
                            cls = DATA_TYPE_MAP.get(changeType);
                        }
                    } else {
                        cls = DATA_TYPE_MAP.get(changeType);
                    }
                }
            }
        }

        if (cls == null) {
            logger.error("--------无法获取转换对象-------");
            throw new WeChatException("无法获取转换对象");
        }
        return XMLUtil.convertXmlStrToObject(cls, xml);
    }
}
