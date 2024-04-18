package com.github.shuaidd;

import com.github.shuaidd.dto.tool.FrontEndTicket;
import org.junit.jupiter.api.Test;

/**
 * 描述 前端SDK 接口测试用例
 *
 * @author huangweilong
 **/
public class FrontEndToolServiceTest extends AbstractTest {

    /*ticket应用*/
    public static final String APP = "app-ticket";

    @Test
    public void getTicket() {
        FrontEndTicket appTicket = weChatManager.frontEndToolService().getAppTicket(APP);
        logger.info("获取到的Ticket数据--{}", appTicket);
    }
}
