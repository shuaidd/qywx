package com.github.shuaidd.example;

import com.github.shuaidd.QywxClient;
import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.response.addressbook.WeChatUserResponse;
import com.github.shuaidd.support.WeChatManager;

/**
 * 纯 Java 环境下使用 qywx-core 的最小示例：不需要 Spring，也不需要任何容器。
 * <p>
 * 与 Spring 示例（qywx-spring-boot-example）的差别只在装配方式：Spring 下由 starter 按 qywx.* 配置自动装配出
 * {@link WeChatManager}；这里由使用者自己把配置交给 {@link QywxConfig.Builder}，再 {@link QywxClient#create(QywxConfig)}
 * 得到同一个入口。之后的调用方式完全一致：{@code client.weChatManager().xxxService()...}。
 * <p>
 * 运行（仓库根目录；qywx-example 不在根 reactor 里，需先把 6.0.0 构件装进本地仓库：mvn -B -DskipTests install）：
 * <pre>
 * # Windows PowerShell
 * $env:QYWX_CORP_ID="企业号"; $env:QYWX_SECRET="应用secret"; $env:QYWX_APPLICATION_NAME="my-app"
 * mvn -f qywx-example/pom.xml compile exec:exec
 * # Linux / macOS
 * QYWX_CORP_ID=企业号 QYWX_SECRET=应用secret QYWX_APPLICATION_NAME=my-app mvn -f qywx-example/pom.xml compile exec:exec
 * </pre>
 * 环境变量：
 * <ul>
 * <li>QYWX_CORP_ID（必填）企业号</li>
 * <li>QYWX_SECRET（必填）应用 secret</li>
 * <li>QYWX_APPLICATION_NAME（可选）应用名，默认 example；多个应用时在 {@link QywxConfig.Builder} 上多次调用 application()</li>
 * <li>QYWX_AGENT_ID（可选）应用 agentId</li>
 * <li>QYWX_USER_ID（可选）设置后额外调用通讯录接口读取该成员姓名；不设置则只取 access_token</li>
 * </ul>
 * 缺少必填项时打印用法并以退出码 1 结束，不会发起任何网络请求。
 *
 * @author ddshuai
 * date 2026-11-28
 **/
public class StandaloneExample {

    public static void main(String[] args) {
        String corpId = System.getenv("QYWX_CORP_ID");
        String secret = System.getenv("QYWX_SECRET");
        if (isBlank(corpId) || isBlank(secret)) {
            System.err.println("用法：设置环境变量 QYWX_CORP_ID（企业号）与 QYWX_SECRET（应用 secret）后重新运行；"
                    + "可选项：QYWX_APPLICATION_NAME（默认 example）、QYWX_AGENT_ID、QYWX_USER_ID。");
            System.exit(1);
        }
        String applicationName = defaultIfBlank(System.getenv("QYWX_APPLICATION_NAME"), "example");

        ApplicationProperties application = new ApplicationProperties();
        application.setApplicationName(applicationName);
        application.setSecret(secret);
        application.setCorpId(corpId);
        application.setAgentId(System.getenv("QYWX_AGENT_ID"));

        QywxConfig config = QywxConfig.builder()
                .corpId(corpId)
                .application(application)
                .build();

        QywxClient client = QywxClient.create(config);
        WeChatManager manager = client.weChatManager();

        String token = manager.tokenService().getAccessToken(applicationName);
        System.out.println("access_token 获取成功，长度=" + token.length());

        String userId = System.getenv("QYWX_USER_ID");
        if (!isBlank(userId)) {
            WeChatUserResponse user = manager.addressBookService().getUser(userId, applicationName);
            System.out.println("成员 " + userId + " 的姓名：" + user.getName());
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static String defaultIfBlank(String value, String fallback) {
        return isBlank(value) ? fallback : value;
    }
}
