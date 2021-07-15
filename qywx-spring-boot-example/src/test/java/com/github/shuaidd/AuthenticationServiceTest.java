package com.github.shuaidd;

import com.github.shuaidd.response.AuthenticationResponse;
import org.junit.Test;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-15 15:00
 **/
public class AuthenticationServiceTest extends AbstractTest {

    public static final String appName = "address-book";

    /**
     * 101.226.129.166, 101.89.18.158, 112.60.18.78, 112.60.18.81, 116.128.138.160, 116.128.164.38, 117.184.242.103, 121.51.130.85, 121.51.140.149,
     * 121.51.86.66, 140.207.189.106, 157.255.173.237,
     * 180.97.117.89, 182.254.11.176, 182.254.78.66, 183.192.202.172, 183.3.224.149, 183.3.234.106, 203.205.219.41, 203.205.255.254, 58.251.80.106
     */
    @Test
    public void apiDomainIp() {
        List<String> ip = weChatManager.authenticationService().apiDomainIp(appName);
        logger.info("获取到的IP列表--{}", ip);
    }

    /**
     * 101.226.233.*, 101.227.139.*, 117.144.245.*, 140.207.54.*, 14.17.43.*, 112.90.75.*, 183.61.51.*, 14.215.153.*, 163.177.87.246, 58.251.62.* ,
     * 14.17.44.*, 121.51.139.*, 163.177.84.*, 183.232.98.*, 183.3.235.*, 203.205.151.*, 157.255.192.*, 121.51.162.*, 59.36.121.*, 223.166.222.100,
     * 223.166.222.101, 223.166.222.102, 223.166.222.103, 223.166.222.104, 223.166.222.105, 223.166.222.106, 223.166.222.107, 223.166.222.111, 223.166.222.112,
     * 223.166.222.117,
     * 223.166.222.118, 101.91.60.80, 101.91.60.81, 121.51.66.16, 121.51.66.19, 121.51.66.26, 121.51.66.120, 121.51.162.*, 157.255.192.*, 59.36.121.*
     */
    @Test
    public void callBackIp() {
        List<String> ip = weChatManager.authenticationService().callBackIp(appName);
        logger.info("获取到的回调服务器的IP列表--{}", ip);
    }

    @Test
    public void getAuthentication() {
        weChatManager.authenticationService().getAuthentication("sdwwwdssds", appName);
    }
}
