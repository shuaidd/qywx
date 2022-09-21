package com.github.shuaidd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shuaidd.support.WeChatManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-02 09:23
 **/
@SpringBootTest(classes = Application.class)
public class AbstractTest {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WeChatManager weChatManager;

    protected final ObjectMapper objectMapper = new ObjectMapper();
}
