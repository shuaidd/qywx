package com.github.shuaidd;

import org.junit.Test;

/**
 * 描述 通讯录单元测试
 *
 * @author ddshuai
 * date 2021-01-02 09:23
 **/
public class AddressBookTest extends AbstractTest {

    public static final String appName = "address-book";

    /**
     * 获取企业微信用户信息
     */
    @Test
    public void getUser() {
        weChatManager.addressBookService().getUser("20170410022717", appName);
    }

    /**
     * 获取企业微信部门用户信息列表
     */
    @Test
    public void getDepartmentUser() {
        weChatManager.addressBookService().getDepartmentUser(1499,true, appName);
    }

    /**
     * 获取企业微信部门用户信息详情
     */
    @Test
    public void getDepartmentUserDetail() {
        weChatManager.addressBookService().getDepartmentUserDetail(108673,true, appName);
    }
}
