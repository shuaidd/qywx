package com.github.shuaidd;

import com.github.shuaidd.client.MiniProgramClient;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-12-22 16:03
 **/
public class MiniClientTest extends AbstractTest  {

    @Autowired
    private MiniProgramClient programClient;

    /**
     * 52_dhhGFZNuJFztS-ft0DbSNjPdeS4jJSnpkbhNTRzwNwF6Da6K_5q-HK6DzV13u7Y9WI8XUJiJ__cnJb_naywSZMTgO6sFsaa-uV6YqgxNciJNJw8YY8Wa80Fa0FTmwrZ6XXMOnRdCP1n3IWLRVWChAIAPQW
     */
    @Test
    public void accessToken(){
        AccessTokenResponse response = programClient.getToken("wxc9245849a884c30a","a5ae0f2129a8f0e828438c861153b3a0");
        System.out.println(response.getAccessToken());
    }

    @Test
    public void send(){
        String token = "52_dhhGFZNuJFztS-ft0DbSNjPdeS4jJSnpkbhNTRzwNwF6Da6K_5q-HK6DzV13u7Y9WI8XUJiJ__cnJb_naywSZMTgO6sFsaa-uV6YqgxNciJNJw8YY8Wa80Fa0FTmwrZ6XXMOnRdCP1n3IWLRVWChAIAPQW";
        Map<String,Object> map = Maps.newHashMap();
        map.put("touser","oVrPP5RCiaqAgwCIeHtOfG6WYOGg");
        map.put("msgtype","text");
        Map<String,Object> content = Maps.newHashMap();
        content.put("content","你好-来自后台的消息");
        map.put("text",content);
        programClient.sendMsg(map,token);

    }
}
