package com.github.shuaidd.response.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.WeChatUser;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-05 18:26
 **/
public class DepartmentUserResponse extends AbstractBaseResponse {

    @JsonProperty("userlist")
    private List<WeChatUser> weChatUserList;

    public List<WeChatUser> getWeChatUserList() {
        return weChatUserList;
    }

    public void setWeChatUserList(List<WeChatUser> weChatUserList) {
        this.weChatUserList = weChatUserList;
    }
}
