package com.github.shuaidd;

import com.github.shuaidd.resquest.*;
import org.junit.Test;

import java.util.Collections;

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
        weChatManager.addressBookService().getUser("2021041002271711", appName);
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
        weChatManager.addressBookService().departmentList(1499, appName);
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
        weChatManager.addressBookService().createTag(request,appName);
    }

    /**
     * 更新标签名称
     */
    @Test
    public void updateTagName() {
        weChatManager.addressBookService().updateTagName(3,"企微信探索",appName);
    }

    /**
     * 删除标签
     */
    @Test
    public void deleteTag() {
        weChatManager.addressBookService().deleteTag(2,appName);
    }

    /**
     * 删除标签成员
     */
    @Test
    public void deleteTagUsers() {
        TagUserRequest request = new TagUserRequest();
        request.setTagId(1);
        request.setUserList(Collections.singletonList("sigm"));
        weChatManager.addressBookService().deleteTagUsers(request,appName);
    }

    /**
     * 增加标签成员
     */
    @Test
    public void addTagUsers() {
        TagUserRequest request = new TagUserRequest();
        request.setTagId(3);
        request.setUserList(Collections.singletonList("20170410022717"));
        weChatManager.addressBookService().addTagUsers(request,appName);
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
}
