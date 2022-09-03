package com.github.shuaidd;

import com.github.shuaidd.dto.tool.CalendarData;
import com.github.shuaidd.dto.tool.DialRecord;
import com.github.shuaidd.dto.tool.ReminderData;
import com.github.shuaidd.dto.checkin.ScheduleData;
import com.github.shuaidd.dto.addressbook.UserId;
import com.github.shuaidd.dto.wedrive.AuthItem;
import com.github.shuaidd.dto.wedrive.SpaceInfo;
import com.github.shuaidd.response.tool.AddScheduleResponse;
import com.github.shuaidd.response.tool.CalendarResponse;
import com.github.shuaidd.response.tool.GetCalendarResponse;
import com.github.shuaidd.response.wedrive.*;
import com.github.shuaidd.resquest.oa.CalendarRequest;
import com.github.shuaidd.resquest.tool.DialRecordRequest;
import com.github.shuaidd.resquest.tool.GetCalendarRequest;
import com.github.shuaidd.resquest.tool.ScheduleRequest;
import com.github.shuaidd.resquest.wedrive.*;
import com.github.shuaidd.service.EfficiencyToolService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.ByteBuffer;
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

    /*微盘*/
    public static final String MICRO_DISK = "micro-disk";

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
        List<DialRecord> dialRecords = toolService.getDialRecord(recordRequest, PUBLIC_TELEPHONE);
        logger.info("获取到的公费电话拨打记录数据--{}", dialRecords);
    }

    /**
     * s.ww36e0a51aab349a7d.6620425469yR
     */
    @Test
    public void createSpace() {
        CreateSpaceRequest request = new CreateSpaceRequest();
        request.setUserId("20170410022717");
        request.setSpaceSubType(0);
        request.setSpaceName("日常支持");
        String spaceId = toolService.createSpace(request, MICRO_DISK);
        logger.info("创建的空间编号--{}", spaceId);
    }

    @Test
    public void renameSpace() {
        RenameSpaceRequest request = new RenameSpaceRequest();
        request.setSpaceId("s.ww36e0a51aab349a7d.6620425469yR");
        request.setSpaceName("产品文档");
        request.setUserId("20170410022717");
        toolService.renameSpace(request, MICRO_DISK);
    }

    @Test
    public void dismissSpace() {
        DismissSpaceRequest request = new DismissSpaceRequest();
        request.setSpaceId("");
        request.setUserId("20170410022717");
        toolService.dismissSpace(request, MICRO_DISK);
    }

    @Test
    public void spaceInfo() {
        SpaceInfoRequest request = new SpaceInfoRequest();
        request.setSpaceId("s.ww36e0a51aab349a7d.6620425469yR");
        request.setUserId("20170410022717");
        SpaceInfo spaceInfo = toolService.spaceInfo(request, MICRO_DISK);
        logger.info("空间信息--{}", spaceInfo);
    }

    @Test
    public void addSpaceAcl() {
        SpaceAclRequest request = new SpaceAclRequest();
        request.setSpaceId("s.ww36e0a51aab349a7d.6620425469yR");
        request.setUserId("20170410022717");

        AuthItem spaceAuthItem = new AuthItem();
        spaceAuthItem.setAuth(1);
        spaceAuthItem.setType(1);
        spaceAuthItem.setUserId("20170516024090");

        request.setAuthInfo(Collections.singletonList(spaceAuthItem));
        toolService.addSpaceAcl(request, MICRO_DISK);
    }

    @Test
    public void delSpaceAcl() {
        SpaceAclRequest request = new SpaceAclRequest();
        request.setSpaceId("s.ww36e0a51aab349a7d.6620425469yR");
        request.setUserId("20170410022717");
        AuthItem spaceAuthItem = new AuthItem();
        spaceAuthItem.setType(1);
        spaceAuthItem.setUserId("20170516024090");
        toolService.delSpaceAcl(request, MICRO_DISK);
    }

    @Test
    public void spaceSetting() {
        SpaceSettingRequest request = new SpaceSettingRequest();
        request.setSpaceId("s.ww36e0a51aab349a7d.6620425469yR");
        request.setUserId("20170410022717");
        request.setEnableWatermark(false);
        toolService.spaceSetting(request, MICRO_DISK);
    }

    @Test
    public void spaceShare() {
        CommonSpaceRequest request = new CommonSpaceRequest();
        request.setSpaceId("s.ww36e0a51aab349a7d.6620425469yR");
        request.setUserId("20170410022717");
        String url = toolService.spaceShare(request, MICRO_DISK);
        logger.info("分享链接--{}", url);
    }

    /**
     * todo userid not found
     */
    @Test
    public void listFile() {
        QueryFileRequest request = new QueryFileRequest();
        request.setFatherId("s.ww36e0a51aab349a7d.6620425469yR");
        request.setSpaceId("s.ww36e0a51aab349a7d.6620425469yR");
        request.setUserId("20170410022717");
        request.setSortType(1);
        request.setLimit(100);
        request.setStart(0);

        SpaceFileResponse response = toolService.listFile(request, MICRO_DISK);
        logger.info("文件列表--{}", response);
    }

    /**
     * s.ww36e0a51aab349a7d.6620425469yR_d.6621109333Btp
     */
    @Test
    public void createFile() {
        CreateFileRequest request = new CreateFileRequest();
        request.setFileName("日常支持");
        request.setFileType(1);
        request.setFatherId("s.ww36e0a51aab349a7d.6620425469yR");
        request.setSpaceId("s.ww36e0a51aab349a7d.6620425469yR");
        request.setUserId("20170410022717");
        CreateFileResponse response = toolService.createFile(request, MICRO_DISK);
        logger.info("创建成功--{}", response);
    }

    /**
     * s.ww36e0a51aab349a7d.6620425469yR_f.662112027qfyY
     *
     * @throws Exception e
     */
    @Test
    public void uploadFile() throws Exception {
        File file = ResourceUtils.getFile("classpath:image/20659972.png");
        String fileContent = Base64.encodeBase64String(IOUtils.toByteArray(new FileInputStream(file)));
        UploadFileRequest request = new UploadFileRequest();
        request.setFileName("20659972.png");
        request.setFatherId("s.ww36e0a51aab349a7d.6620425469yR_d.6621109333Btp");
        request.setSpaceId("s.ww36e0a51aab349a7d.6620425469yR");
        request.setUserId("20170410022717");
        request.setFileBase64Content(fileContent);
        CreateFileResponse response = toolService.uploadFile(request, MICRO_DISK);
        logger.info("创建成功--{}", response);
    }

    @Test
    public void downloadFile() throws Exception {
        DownloadFileRequest request = new DownloadFileRequest();
        request.setFileId("s.ww36e0a51aab349a7d.6620425469yR_f.662112027qfyY");
        request.setUserId("20170410022717");
        DownloadFileResponse response = toolService.downloadFile(request, MICRO_DISK);
        logger.info("下载成功--{}", response);

        /*简单的下载到本地*/
        File file = ResourceUtils.getFile("classpath:download/20659972.png");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", response.getCookieName() + "=" + response.getCookieValue());
        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(response.getDownloadUrl(), HttpMethod.GET, entity, byte[].class);
        IOUtils.write(responseEntity.getBody(), new FileOutputStream(file));
    }

    @Test
    public void renameFile() {
        RenameFileRequest request = new RenameFileRequest();
        request.setUserId("20170410022717");
        request.setFileId("s.ww36e0a51aab349a7d.6620425469yR_f.662112027qfyY");
        request.setNewName("头像.png");

        RenameFileResponse response = toolService.renameFile(request, MICRO_DISK);
        logger.info("操作成功--{}", response);
    }

    @Test
    public void moveFile() {
        MoveFileRequest request = new MoveFileRequest();
        request.setUserId("20170410022717");
        request.setFileId(Collections.singletonList("s.ww36e0a51aab349a7d.6620425469yR_f.662112027qfyY"));
        request.setFatherId("s.ww36e0a51aab349a7d.6620425469yR");
        request.setReplace(false);
        MoveFileResponse response = toolService.moveFile(request, MICRO_DISK);
        logger.info("操作成功--{}", response);
    }

    @Test
    public void deleteFile() {
        DeleteFileRequest request = new DeleteFileRequest();
        request.setUserId("20170410022717");
        request.setFileId(Collections.singletonList("s.ww36e0a51aab349a7d.6620425469yR_f.662112027qfyY"));
        toolService.deleteFile(request, MICRO_DISK);
        logger.info("操作成功");
    }

    @Test
    public void fileInfo() {
        FileInfoRequest request = new FileInfoRequest();
        request.setUserId("20170410022717");
        request.setFileId("s.ww36e0a51aab349a7d.6620425469yR_f.662112027qfyY");
        FileInfoResponse response = toolService.fileInfo(request, MICRO_DISK);
        logger.info("操作成功: {}", response.getFileInfo());
    }

    @Test
    public void addFileAcl() {
        FileAclRequest request = new FileAclRequest();
        request.setUserId("20170410022717");
        request.setFileId("s.ww36e0a51aab349a7d.6620425469yR_f.662157955op7B");

        AuthItem authItem = new AuthItem();
        authItem.setType(1);
        authItem.setAuth(1);
        authItem.setUserId("20170516024090");

        request.setAuthInfo(Collections.singletonList(authItem));
        toolService.addFileAcl(request, MICRO_DISK);
        logger.info("操作成功");
    }

    @Test
    public void delFileAcl() {
        FileAclRequest request = new FileAclRequest();
        request.setUserId("20170410022717");
        request.setFileId("s.ww36e0a51aab349a7d.6620425469yR_f.662157955op7B");

        AuthItem authItem = new AuthItem();
        authItem.setUserId("20170516024090");
        request.setAuthInfo(Collections.singletonList(authItem));
        toolService.delFileAcl(request, MICRO_DISK);
        logger.info("操作成功");
    }

    @Test
    public void fileSetting() {
        FileSettingRequest request = new FileSettingRequest();
        request.setUserId("20170410022717");
        request.setFileId("s.ww36e0a51aab349a7d.6620425469yR_f.662157955op7B");
        request.setAuthScope(2);
        request.setAuth(1);
        toolService.fileSetting(request, MICRO_DISK);
        logger.info("操作成功");
    }

    @Test
    public void getFileShare() {
        FileInfoRequest request = new FileInfoRequest();
        request.setUserId("20170410022717");
        request.setFileId("s.ww36e0a51aab349a7d.6620425469yR_f.662157955op7B");
        FileShareResponse response = toolService.getFileShare(request, MICRO_DISK);
        logger.info("操作成功--{}", response);
    }

    @Test
    public void proInfo() {
        ProInfoRequest request = new ProInfoRequest();
        request.setUserId("20170410022717");
        ProInfoResponse response = toolService.proInfo(request, MICRO_DISK);
        logger.info("操作成功--{}", response);
    }

    @Test
    public void capacity() {
        ProInfoRequest request = new ProInfoRequest();
        request.setUserId("20170410022717");
        CapacityResponse response = toolService.capacity(request, MICRO_DISK);
        logger.info("操作成功--{}", response);
    }

    @Test
    public void initUploadFile() throws Exception {
        File file = ResourceUtils.getFile("classpath:image/IMG_20190919_131404.jpg");
        File tempDir = ResourceUtils.getFile("classpath:upload");
        try (FileInputStream inputStream = new FileInputStream(file);) {
            byte[] bytes = new byte[2097152];//2M
            int loopCount = 0;
            while (inputStream.read(bytes) != -1) {
                loopCount++;
                IOUtils.write(bytes, new FileOutputStream(new File(tempDir.toString() + "/temp" + loopCount)));
            }

            //todo 待测试分块上传
            InitUploadFileRequest request = new InitUploadFileRequest();
            toolService.initUploadFile(request, MICRO_DISK);
        }
    }

    @Test
    public void mergeFile() throws Exception {
        File file = ResourceUtils.getFile("classpath:upload/temp1");
        File file1 = ResourceUtils.getFile("classpath:upload/temp2");
        File out = new File(file1.getParent() + "/IMG_20190919_131404.jpg");
        try (FileInputStream inputStream = new FileInputStream(file); FileInputStream inputStream1 = new FileInputStream(file1); FileOutputStream outputStream = new FileOutputStream(out)) {
            IOUtils.copy(inputStream, outputStream);
            IOUtils.copy(inputStream1, outputStream);
        }
    }

    @Test
    public void fileUploadPart() {
        FileUploadPartRequest request = new FileUploadPartRequest();
        toolService.fileUploadPart(request, MICRO_DISK);
    }

    @Test
    public void fileUploadFinish() {
        FileUploadFinishRequest request = new FileUploadFinishRequest();
        toolService.fileUploadFinish(request, MICRO_DISK);
    }
}
