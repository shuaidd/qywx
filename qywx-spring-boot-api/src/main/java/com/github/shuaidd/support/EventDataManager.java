package com.github.shuaidd.support;

import com.github.shuaidd.enums.ChangeType;
import com.github.shuaidd.event.BaseEventData;
import com.github.shuaidd.event.CommonEventData;
import com.github.shuaidd.event.DeleteUserEventData;
import com.github.shuaidd.event.UserEventData;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Objects;

/**
 * 描述 企业微信 事件回调数据管理
 *
 * @author ddshuai
 * @date 2021-07-13 16:41
 **/
public class EventDataManager {
    private static final HashMap<String, Class<? extends BaseEventData>> DATA_TYPE_MAP = new HashMap<>();

    static {
        DATA_TYPE_MAP.put(ChangeType.CREATE_USER.getChangeType(), UserEventData.class);
        DATA_TYPE_MAP.put(ChangeType.UPDATE_USER.getChangeType(), UserEventData.class);
        DATA_TYPE_MAP.put(ChangeType.DELETE_USER.getChangeType(), DeleteUserEventData.class);
    }

    public static void register(String changeType, Class<? extends BaseEventData> cls) {
        Objects.requireNonNull(changeType);
        Objects.requireNonNull(cls);
        DATA_TYPE_MAP.putIfAbsent(changeType, cls);
    }

    public static <T> T getXmlData(String xml, Class<T> tClass) {
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
        if (StringUtils.isEmpty(commonEventData.getChangeType())) {
            return null;
        }

        Class<? extends BaseEventData> cls = DATA_TYPE_MAP.get(commonEventData.getChangeType());

        Objects.requireNonNull(cls);
        return XMLUtil.convertXmlStrToObject(cls, xml);
    }
}
