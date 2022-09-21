package com.github.shuaidd;

import com.github.shuaidd.resquest.exmail.DeleteMailGroupRequest;
import com.github.shuaidd.resquest.exmail.MailGroupRequest;
import com.github.shuaidd.service.EnterpriseEmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 企业邮箱单元测试 企业号没授权，暂时无法自测
 */
public class EnterpriseEmailServiceTest extends AbstractTest {
    public static final String EMAIL = "email";

    @Autowired
    EnterpriseEmailService emailService;

    @Test
    public void createMailGroup() {
        MailGroupRequest request = new MailGroupRequest();
        emailService.createMailGroup(request, EMAIL);
    }

    @Test
    public void updateMailGroup() {
        MailGroupRequest request = new MailGroupRequest();
        emailService.updateMailGroup(request, EMAIL);
    }

    @Test
    public void deleteMailGroup() {
        DeleteMailGroupRequest request = new DeleteMailGroupRequest();
        emailService.deleteMailGroup(request, EMAIL);
    }
}
