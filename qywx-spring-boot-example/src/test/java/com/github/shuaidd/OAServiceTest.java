package com.github.shuaidd;

import com.github.shuaidd.dto.checkin.CheckInData;
import com.github.shuaidd.dto.checkin.CheckInRule;
import com.github.shuaidd.dto.DialRecord;
import com.github.shuaidd.response.CheckInDayReportResponse;
import com.github.shuaidd.response.CheckInOptionResponse;
import com.github.shuaidd.resquest.CheckInDataRequest;
import com.github.shuaidd.resquest.CheckInRuleRequest;
import com.github.shuaidd.resquest.CommonOaRequest;
import com.github.shuaidd.resquest.DialRecordRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

/**
 * 描述 OA 接口测试用例
 *
 * @author ddshuai
 * date 2021-07-15 09:16
 **/
public class OAServiceTest extends AbstractTest {

    private static long unixStartTime = 0;
    private static long unixEndTime = 0;

    static {
        try {
            unixStartTime = DateUtils.parseDate("2021-07-15 00:00:00", "yyyy-MM-dd HH:mm:ss").getTime() / 1000;
            unixEndTime = DateUtils.parseDate("2021-07-16 23:59:59", "yyyy-MM-dd HH:mm:ss").getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /*打卡应用*/
    public static final String CHECK_IN = "check-in";

    /*公费电话应用*/
    public static final String PUBLIC_TELEPHONE = "public-telephone";

    @Test
    public void getCorpCheckInOption() {
        CheckInOptionResponse checkInOptionResponse = weChatManager.oaService().getCorpCheckInOption(CHECK_IN);
        logger.info("获取到的打卡数据--{}", checkInOptionResponse);
    }

    @Test
    public void getDialRecord() {
        DialRecordRequest recordRequest = new DialRecordRequest();
        List<DialRecord> dialRecords = weChatManager.oaService().getDialRecord(recordRequest, PUBLIC_TELEPHONE);
        logger.info("获取到的公费电话拨打记录数据--{}", dialRecords);
    }

    @Test
    public void getCheckInData() {
        CheckInDataRequest request = new CheckInDataRequest();
        request.setOpenCheckInDataType(3);
        request.setStartTime(unixStartTime);
        request.setEndTime(unixEndTime);
        request.setUserIdList(Collections.singletonList("20170410022717"));
        List<CheckInData> checkInData = weChatManager.oaService().getCheckInData(request, CHECK_IN);
        logger.info("获取到的打卡数据--{}", checkInData);
    }

    @Test
    public void getCheckInOption() {
        CheckInRuleRequest request = new CheckInRuleRequest();
        request.setUserIdList(Collections.singletonList("20170410022717"));
        request.setDateTime(unixStartTime);
        List<CheckInRule> checkInRules = weChatManager.oaService().getCheckInOption(request, CHECK_IN);
        logger.info("获取到的员工打卡规则--{}", checkInRules);
    }

    @Test
    public void getCheckInDayData() throws ParseException {
        CommonOaRequest request = new CommonOaRequest();
        request.setStartTime(DateUtils.parseDate("2021-07-15 00:00:00", "yyyy-MM-dd HH:mm:ss").getTime() / 1000);
        request.setEndTime(DateUtils.parseDate("2021-07-16 00:00:00", "yyyy-MM-dd HH:mm:ss").getTime() / 1000);
        request.setUserIdList(Collections.singletonList("20170410022717"));
        CheckInDayReportResponse reportResponse = weChatManager.oaService().getCheckInDayData(request, CHECK_IN);
        logger.info("获取打卡日报数据--{}", reportResponse);
    }
}
