package com.github.shuaidd;

import com.github.shuaidd.dto.addressbook.DeptUser;
import com.github.shuaidd.response.addressbook.ExportResultResponse;
import com.github.shuaidd.resquest.CursorPageRequest;
import com.github.shuaidd.resquest.addressbook.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 描述 通讯录单元测试
 *
 * @author ddshuai
 * date 2021-01-02 09:23
 **/
public class AddressBookTest extends AbstractTest {

    /*通讯录应用*/
    public static final String appName = "address-book";

    /**
     * 创建用户
     */
    @Test
    public void createUser() {
        CreateUserRequest request = new CreateUserRequest();
        request.setAddress("北京市海定区北大路001号");
        request.setName("企业微信用户");
        request.setUserId("2021041002271711");
        request.setGender("2");
        request.setEmail("22@139.com");
        request.setMobile("15368671829");
        request.setAlias("jackzhang");
        request.setEnable(1);
        request.setDepartment(Collections.singletonList(1499));
        weChatManager.addressBookService().createUser(request, appName);
    }

    /**
     * 获取企业微信用户信息
     */
    @Test
    public void getUser() {
        weChatManager.addressBookService().getUser("20170410022717", appName);
    }

    /**
     * 更新企业微信用户信息
     */
    @Test
    public void updateUser() {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setUserId("2021041002271711");
        request.setName("企微户");
        weChatManager.addressBookService().updateUser(request, appName);
    }

    /**
     * 删除企业微信用户信息
     */
    @Test
    public void deleteUser() {
        weChatManager.addressBookService().deleteUser("2021041002271711", appName);
    }

    /**
     * userid转openid
     */
    @Test
    public void convertToOpenId() {
        weChatManager.addressBookService().convertToOpenId("20170410022717", appName);
    }

    /**
     * openid转userid
     */
    @Test
    public void convertToUserId() {
        weChatManager.addressBookService().convertToUserId("ojkeO5pBC07ETMUOk0LF-EEE-xVI", appName);
    }

    /**
     * 二次验证
     */
    @Test
    public void authSuccess() {
        weChatManager.addressBookService().authSuccess("20170410022717", appName);
    }

    /**
     * 邀请成员
     */
    @Test
    public void invite() {
        InviteUserRequest inviteUserRequest = new InviteUserRequest();
        inviteUserRequest.setUser(Collections.singletonList("ssssssssssss"));
        weChatManager.addressBookService().invite(inviteUserRequest, appName);
    }


    /**
     * 批量删除企业微信用户信息
     */
    @Test
    public void batchDeleteUser() {
        BatchDeleteUserRequest request = new BatchDeleteUserRequest();
        request.setUserIdList(Collections.singletonList("2021041002271711"));
        weChatManager.addressBookService().batchDeleteUser(request, appName);
    }

    /**
     * 创建部门
     */
    @Test
    public void createDepartment() {
        DepartmentRequest request = new DepartmentRequest();
        request.setName("企微广告部");
        request.setParentId(1);
        request.setOrder(10000);
        weChatManager.addressBookService().createDepartment(request, appName);
    }

    /**
     * 更新部门
     */
    @Test
    public void updateDepartment() {
        DepartmentRequest request = new DepartmentRequest();
        request.setId(110461);
        request.setName("企微广告一部");
        request.setParentId(1);
        request.setOrder(2);
        weChatManager.addressBookService().updateDepartment(request, appName);
    }

    /**
     * 删除部门
     */
    @Test
    public void deleteDepartment() {
        weChatManager.addressBookService().deleteDepartment(110461, appName);
    }

    /**
     * 部门列表
     */
    @Test
    public void departmentList() {
       List<?> list = weChatManager.addressBookService().departmentList(1499, appName);
       logger.info("{}",list);
    }

    @Test
    public void simpleDepartmentList() {
       List<?> list = weChatManager.addressBookService().simpleDepartmentList(1499, appName);
       logger.info("{}",list);
    }

    /**
     * 获取企业微信部门用户信息列表
     */
    @Test
    public void getDepartmentUser() {
        weChatManager.addressBookService().getDepartmentUser(1499, true, appName);
    }

    /**
     * 获取企业微信部门用户信息详情
     */
    @Test
    public void getDepartmentUserDetail() {
        weChatManager.addressBookService().getDepartmentUserDetail(108673, true, appName);
    }

    /**
     * 创建标签
     */
    @Test
    public void createTag() {
        TagRequest request = new TagRequest();
        request.setTagName("企业微信探索");
        weChatManager.addressBookService().createTag(request, appName);
    }

    /**
     * 更新标签名称
     */
    @Test
    public void updateTagName() {
        weChatManager.addressBookService().updateTagName(3, "企微信探索", appName);
    }

    /**
     * 删除标签
     */
    @Test
    public void deleteTag() {
        weChatManager.addressBookService().deleteTag(2, appName);
    }

    /**
     * 删除标签成员
     */
    @Test
    public void deleteTagUsers() {
        TagUserRequest request = new TagUserRequest();
        request.setTagId(1);
        request.setUserList(Collections.singletonList("sigm"));
        weChatManager.addressBookService().deleteTagUsers(request, appName);
    }

    /**
     * 增加标签成员
     */
    @Test
    public void addTagUsers() {
        TagUserRequest request = new TagUserRequest();
        request.setTagId(3);
        request.setUserList(Collections.singletonList("20170410022717"));
        weChatManager.addressBookService().addTagUsers(request, appName);
    }

    /**
     * 获取标签列表
     */
    @Test
    public void getTagList() {
        weChatManager.addressBookService().getTagList(appName);
    }

    /**
     * 获取标签成员
     */
    @Test
    public void getTagUser() {
        weChatManager.addressBookService().getTagUser(1, appName);
    }

    /**
     * 获取企业二维码地址
     */
    @Test
    public void getCorpQrCode() {
        String url = weChatManager.addressBookService().getCorpQrCode(4, appName);
        logger.info("企业二维码地址--{}", url);
    }

    /**
     * 获取企业活跃用户数
     */
    @Test
    public void getActiveStat() {
        Integer sum = weChatManager.addressBookService().getActiveStat(new Date(), appName);
        logger.info("获取企业活跃成员数--{}", sum);
    }

    /**
     * 导出用户
     */
    @Test
    public void exportUser() {
        AddressBookExportRequest request = new AddressBookExportRequest();
        request.setBlockSize(10000);
        request.setEncodingAesKey("QJqvdlS4M4FTPUNqSQrMg3VwNRyBCqTqtNStREy3Ahd");
        String jobId = weChatManager.addressBookService().exportUser(request, appName);
        logger.info("jobId--{}", jobId);
    }

    /**
     * 获取异步任务执行结果
     */
    @Test
    public void jobResult() {
        ExportResultResponse resultResponse = weChatManager.addressBookService().getExportResult("jobid_xNWq-TbeJrPiKG1YEW_44NlB2W1ILctq2CALviy8U_A", appName);
        logger.info("异步任务执行结果--{}", resultResponse);
    }

    /**
     * 获取解密数据
     */
    @Test
    public void getDecryptExportData() {
        String aesKey = "QJqvdlS4M4FTPUNqSQrMg3VwNRyBCqTqtNStREy3Ahd";
        String data = weChatManager.addressBookService().getDecryptExportData(aesKey, "https://szfront.wxwork.qq.com/downloadobject?fileid=080112043133303122093133313037373634312a0131322465623131363134632d656163652d346130302d623233302d30666165313862396566373538c00d4214def7096ba4682d9a80a18f75ea5a66be7de8cde748015802600768b8177207333030303030309001f7c7f78c069a0100a001c3c703&weixinnum=1773811417&authkey=7008001007186022604001a8b81eaa803920f9bbcb6a2cc2e37f1304b023c6c31f0eeb5ba6dc8715976d25ce9fb59829e3b824c9e4d1f65c01410fe150ad7f3bbc2e6b6e0708610615111cc7ce9db1020c50cf696624173772160d1bfe87cd45f284f9c15ef67dfe23&filename=data_0.json");
        logger.info("异步任务执行结果的解密后数据--{}", data);
    }

    /**
     * 导出用户详情
     */
    @Test
    public void exportUserDetail() {
        String aesKey = "QJqvdlS4M4FTPUNqSQrMg3VwNRyBCqTqtNStREy3Ahd";
        AddressBookExportRequest request = new AddressBookExportRequest();
        request.setBlockSize(10000);
        request.setEncodingAesKey(aesKey);
        String jobId = weChatManager.addressBookService().exportUserDetail(request, appName);
        logger.info("jobId--{}", jobId);
        try {
            //等待异步任务执行结束 实际使用可以根据 任务完成的回调通知来获取执行结果，这里是 测试数据模拟，几秒就跑完了
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExportResultResponse resultResponse = weChatManager.addressBookService().getExportResult(jobId, appName);
        logger.info("异步任务执行结果--{}", resultResponse);

        for (ExportResultResponse.ExportResultData exportResultData : resultResponse.getDataList()) {
            weChatManager.addressBookService().getDecryptExportData(aesKey, exportResultData.getUrl());
        }
    }

    /**
     * 导出部门
     */
    @Test
    public void exportDepartment() {
        String aesKey = "QJqvdlS4M4FTPUNqSQrMg3VwNRyBCqTqtNStREy3Ahd";
        AddressBookExportRequest request = new AddressBookExportRequest();
        request.setBlockSize(10000);
        request.setEncodingAesKey(aesKey);
        String jobId = weChatManager.addressBookService().exportDepartment(request, appName);
        logger.info("jobId--{}", jobId);
        try {
            //等待异步任务执行结束 实际使用可以根据 任务完成的回调通知来获取执行结果，这里是 测试数据模拟，几秒就跑完了
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExportResultResponse resultResponse = weChatManager.addressBookService().getExportResult(jobId, appName);
        logger.info("异步任务执行结果--{}", resultResponse);

        for (ExportResultResponse.ExportResultData exportResultData : resultResponse.getDataList()) {
            weChatManager.addressBookService().getDecryptExportData(aesKey, exportResultData.getUrl());
        }
    }

    /**
     * 导出标签成员
     */
    @Test
    public void exportTagUser() {
        String aesKey = "QJqvdlS4M4FTPUNqSQrMg3VwNRyBCqTqtNStREy3Ahd";
        AddressBookExportRequest request = new AddressBookExportRequest();
        request.setBlockSize(10000);
        request.setTagId(1L);
        request.setEncodingAesKey(aesKey);
        String jobId = weChatManager.addressBookService().exportTagUser(request, appName);
        logger.info("jobId--{}", jobId);
        try {
            //等待异步任务执行结束 实际使用可以根据 任务完成的回调通知来获取执行结果，这里是 测试数据模拟，几秒就跑完了
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExportResultResponse resultResponse = weChatManager.addressBookService().getExportResult(jobId, appName);
        logger.info("异步任务执行结果--{}", resultResponse);

        for (ExportResultResponse.ExportResultData exportResultData : resultResponse.getDataList()) {
            weChatManager.addressBookService().getDecryptExportData(aesKey, exportResultData.getUrl());
        }
    }

    @Test
    public void getUserIds() {
        CursorPageRequest request = new CursorPageRequest();
        List<DeptUser> response = weChatManager.addressBookService().getUserIds(request, appName);
        logger.info("{}",response);
    }
}
