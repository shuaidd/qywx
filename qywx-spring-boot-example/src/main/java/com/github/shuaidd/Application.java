package com.github.shuaidd;

import com.github.shuaidd.autoconfigure.EnableWeChat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 描述 企业微信接口测试模块
 *
 * @author ddshuai
 * @date 2021-01-02 09:19
 **/
@SpringBootApplication
@EnableWeChat
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
