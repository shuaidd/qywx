package com.github.shuaidd;

import com.github.shuaidd.response.linkedcorp.*;
import org.junit.jupiter.api.Test;

/**
 * @author ddshuai
 * date 2022-09-04 21:57
 **/
public class LinkedCorpTest extends AbstractTest {

    @Test
    public void test() {
        LinkedCorpDeptResponse res1 = weChatManager.addressBookService().getDeptList(null,"");
        LinkedCorpDeptUserListResponse res2 = weChatManager.addressBookService().getDeptUserList(null,"");
        PermListResponse res3 = weChatManager.addressBookService().getPermList("");
        LinkedCorpDeptUserResponse res4 = weChatManager.addressBookService().getDeptUser(null,"");
        LinkedCorpUserResponse res5 = weChatManager.addressBookService().getUserDetail(null,"");
    }
}
