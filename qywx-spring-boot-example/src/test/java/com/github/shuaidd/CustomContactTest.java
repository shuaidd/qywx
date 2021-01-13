package com.github.shuaidd;

import com.github.shuaidd.response.AddContactWayResponse;
import com.github.shuaidd.response.FollowUserResponse;
import com.github.shuaidd.response.UnassignedListResponse;
import com.github.shuaidd.resquest.AddContactWayRequest;
import com.github.shuaidd.resquest.PageRequest;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-13 13:43
 **/
public class CustomContactTest extends AbstractTest {

    /**
     * 客户联系功能编码 与配置文件内定义的别名【application-name】一致
     */
    public static final String appName = "customer-contact";

    @Test
    public void unassignedList() {
        PageRequest request = new PageRequest();
        UnassignedListResponse response = weChatManager.customContactService().unassignedList(request, appName);
        logger.info("{}", response);
    }

    @Test
    public void getFollowUserList() {
        FollowUserResponse followUserResponse = weChatManager.customContactService().getFollowUserList(appName);
        logger.info("{}", followUserResponse);
    }

    @Test
    public void addContactWay() {
        AddContactWayRequest request = new AddContactWayRequest();
        request.setType(2);
        request.setScene(2);
        request.setRemark("渠道客户");
        request.setState("channel-code");
        request.setUsers(Arrays.asList("CaoMuJiangDeXiaoLu","20170410022717"));
        AddContactWayResponse response = weChatManager.customContactService().addContactWay(request, appName);
        logger.info("{}", response);
    }

    @Test
    public void a() {

    }
}
