package com.github.shuaidd;

import com.github.shuaidd.event.*;
import com.github.shuaidd.support.XMLUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-14 09:47
 **/
public class MainTest {
    public static void main(String[] args) throws ParseException {
        System.out.println(DateUtils.parseDate("2020-01-14 8:00:00", "yyyy-MM-dd HH:mm:ss").getTime());
        System.out.println(DateUtils.parseDate("2020-01-14 10:00:00", "yyyy-MM-dd HH:mm:ss").getTime());
        System.out.println(DateFormatUtils.format(new Date(1571274600), "yyyy-MM-dd HH:mm:ss"));

        UserEventData eventData = new UserEventData();
        eventData.setAddress("111");
        eventData.setAlias("2222");
        ExtAttrData extAttrData = new ExtAttrData();

        ExtAttrItemData extAttrItemData = new ExtAttrItemData();
        extAttrItemData.setName("222");
        ExtAttrItemTextData extAttrItemTextData = new ExtAttrItemTextData();
        extAttrItemTextData.setValue("sdsds");
        extAttrItemData.setTextData(extAttrItemTextData);

        ExtAttrItemData extAttrItemData1 = new ExtAttrItemData();
        extAttrItemData1.setName("222");
        ExtAttrItemTextData extAttrItemTextData1 = new ExtAttrItemTextData();
        extAttrItemTextData1.setValue("sdsds");
        extAttrItemData1.setTextData(extAttrItemTextData);

        extAttrData.setItemData(Lists.newArrayList(extAttrItemData, extAttrItemData1));
        eventData.setExtAttrData(extAttrData);

        String xml = XMLUtil.convertToXml(eventData);
        System.out.println(xml);

        CommonEventData eventData1 = XMLUtil.convertXmlStrToObject(CommonEventData.class, "<xml>\n" +
                "    <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "    <FromUserName><![CDATA[sys]]></FromUserName> \n" +
                "    <CreateTime>1403610513</CreateTime>\n" +
                "    <MsgType><![CDATA[event]]></MsgType>\n" +
                "    <Event><![CDATA[change_contact]]></Event>\n" +
                "    <ChangeType>delete_user</ChangeType>\n" +
                "    <UserID><![CDATA[zhangsan]]></UserID>\n" +
                "</xml>\n");
        System.out.println(eventData1);
    }
}
