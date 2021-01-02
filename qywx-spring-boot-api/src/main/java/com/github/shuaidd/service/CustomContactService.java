package com.github.shuaidd.service;

import com.github.shuaidd.response.FollowUserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述 客户联系
 *
 * @author ddshuai
 * @date 2021-01-02 09:47
 **/

@Service
public class CustomContactService extends AbstractBaseService {

    /* ---------企业服务人员管理---------- start */

    /**
     * 获取配置了客户联系功能的成员列表
     */
    public List<String> getFollowUserList(String applicationName) {
        FollowUserResponse response = weChatClient.getFollowUserList(applicationName);
        if (isSuccess(response)) {
            return response.getFollowUsers();
        }

        return null;
    }

    /* ---------企业服务人员管理---------- end */
}
