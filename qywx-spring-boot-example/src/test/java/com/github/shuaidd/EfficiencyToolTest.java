package com.github.shuaidd;

import com.github.shuaidd.dto.tool.CalendarData;
import com.github.shuaidd.dto.tool.DialRecord;
import com.github.shuaidd.dto.tool.ReminderData;
import com.github.shuaidd.dto.checkin.ScheduleData;
import com.github.shuaidd.dto.addressbook.UserId;
import com.github.shuaidd.response.tool.AddScheduleResponse;
import com.github.shuaidd.response.tool.CalendarResponse;
import com.github.shuaidd.response.tool.GetCalendarResponse;
import com.github.shuaidd.resquest.oa.CalendarRequest;
import com.github.shuaidd.resquest.tool.DialRecordRequest;
import com.github.shuaidd.resquest.tool.GetCalendarRequest;
import com.github.shuaidd.resquest.tool.ScheduleRequest;
import com.github.shuaidd.service.EfficiencyToolService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

/**
 * 描述 效率工具
 *
 * @author ddshuai
 * date 2021-01-14 10:10
 **/
public class EfficiencyToolTest extends AbstractTest {
    private EfficiencyToolService toolService;

    /**
     * 日程 应用别名
     */
    public static final String APP_NAME = "schedule";

    /*公费电话应用*/
    public static final String PUBLIC_TELEPHONE = "public-telephone";

    @Before
    public void init() {
        toolService = weChatManager.efficiencyTool();
    }

    /**
     * wcCRbQBwAA3tCVTMpnmDlbKWS-I2nRVQ
     */
    @Test
    public void addCalendar() {
        CalendarRequest request = new CalendarRequest();
        CalendarData calendarData = new CalendarData();
        calendarData.setDescription("测试日程");
        calendarData.setReadonly(1);
        calendarData.setOrganizer("20170410022717");
        calendarData.setSummary("明天开会");
        calendarData.setColor("#0000FF");
        request.setCalendar(calendarData);
        CalendarResponse response = toolService.addCalendar(request, APP_NAME);
        logger.info("{}", response);
    }

    @Test
    public void updateCalendar() {
        CalendarRequest request = new CalendarRequest();
        CalendarData calendarData = new CalendarData();
        calendarData.setCalId("wcCRbQBwAA3tCVTMpnmDlbKWS-I2nRVQ");
        calendarData.setDescription("测试日程-1");
        calendarData.setReadonly(1);
        calendarData.setOrganizer("20170410022717");
        calendarData.setSummary("明天开会咯");
        calendarData.setColor("#0000FF");
        request.setCalendar(calendarData);
        toolService.updateCalendar(request, APP_NAME);
    }

    @Test
    public void getCalendar() {
        GetCalendarRequest request = new GetCalendarRequest();
        request.setCalIdList(Collections.singletonList("wcCRbQBwAA3tCVTMpnmDlbKWS-I2nRVQ"));
        GetCalendarResponse response = toolService.getCalendar(request, APP_NAME);
        logger.info("{}", response);
    }

    /**
     * 40d6a94c5d907efeeaae19fce3b450d5
     *
     * @throws ParseException
     */
    @Test
    public void addSchedule() throws ParseException {
        ScheduleRequest request = new ScheduleRequest();

        ScheduleData scheduleData = new ScheduleData();
        scheduleData.setOrganizer("20170410022717");
        scheduleData.setCalId("wcCRbQBwAA3tCVTMpnmDlbKWS-I2nRVQ");
        scheduleData.setStartTime(DateUtils.parseDate("2021-01-15 12:00:00", "yyyy-MM-dd HH:mm:ss"));
        scheduleData.setEndTime(DateUtils.parseDate("2021-01-15 14:00:00", "yyyy-MM-dd HH:mm:ss"));
        scheduleData.setSummary("需求评审会议");
        scheduleData.setDescription("2.0版本需求初步评审");
        scheduleData.setLocation("广州国际媒体港10楼1005会议室");

        ReminderData reminderData = new ReminderData();
        reminderData.setIsRemind(1);
        scheduleData.setReminders(reminderData);

        UserId userId = new UserId();
        userId.setUserId("SiGuangMeng");
        scheduleData.setAttendees(Collections.singletonList(userId));

        request.setSchedule(scheduleData);
        AddScheduleResponse scheduleResponse = toolService.addSchedule(request, APP_NAME);
        logger.info("{}", scheduleResponse);
    }

    @Test
    public void getDialRecord() {
        DialRecordRequest recordRequest = new DialRecordRequest();
        List<DialRecord> dialRecords = weChatManager.efficiencyTool().getDialRecord(recordRequest, PUBLIC_TELEPHONE);
        logger.info("获取到的公费电话拨打记录数据--{}", dialRecords);
    }
}
