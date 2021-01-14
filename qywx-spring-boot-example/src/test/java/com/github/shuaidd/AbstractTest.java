package com.github.shuaidd;

import com.github.shuaidd.support.WeChatManager;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-02 09:23
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class AbstractTest {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    WeChatManager weChatManager;
}
