package com.github.shuaidd;

import org.junit.jupiter.api.Test;

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
