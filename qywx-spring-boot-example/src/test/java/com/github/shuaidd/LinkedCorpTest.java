package com.github.shuaidd;

import com.github.shuaidd.client.LinkedCorpClient;
import com.github.shuaidd.service.AddressBookService;
import com.github.shuaidd.support.WeChatManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ddshuai
 * date 2022-09-04 21:57
 **/
public class LinkedCorpTest extends AbstractTest {

    @Test
    public void test() {
        weChatManager.addressBookService().getDeptList(null,"");
        weChatManager.addressBookService().getDeptUserList(null,"");
        weChatManager.addressBookService().getPermList("");
        weChatManager.addressBookService().getDeptUser(null,"");
        weChatManager.addressBookService().getUserDetail(null,"");
    }
}
