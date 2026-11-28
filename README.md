# qywx

企业微信服务端 API 封装。6.0 起分为两层：

- `qywx-core`：核心实现，基于原生 OpenFeign，**不依赖 Spring**，纯 Java 项目可直接使用；
- `qywx-spring-boot-starter`：薄适配层，把 core 装配进 Spring Boot（绑定 `qywx.*`、注册 bean、接入 Spring 缓存）。

两种环境下的调用方式完全一致：`weChatManager.xxxService()...`。从 5.x 升级请先读 [MIGRATION-6.0.md](MIGRATION-6.0.md)（Java 17 基线、模块改名、文件类型等破坏性变更）。

范围：企业微信服务端 REST API。回调接入配置与前端 JSSDK 不在封装范围内；回调消息的验签、解密工具见 `CallBackManager`。

## 依赖

| 环境 | 坐标 | 说明 |
|---|---|---|
| 纯 Java（Java 17+） | `com.github.shuaidd:qywx-core:6.0.0` | 不依赖 Spring / Spring Cloud，不需要容器 |
| Spring Boot 3.x / 4.x | `com.github.shuaidd:qywx-spring-boot-starter:6.0.0` | 不再需要引入 `spring-cloud-starter-openfeign`；已在 Boot 4.1.3 上验证；Boot 3.5.16 实测通过（自动配置排序按名字声明，两代包名都生效；Jackson 版本随用户自己的 Boot BOM） |
| Spring Boot 2.7 起 | 同上 | 适配层经 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` 自动注册（Boot 2.7 起支持），未实测 |
| Spring Boot 2.x 旧用法 | `qywx-spring-boot-starter:4.4.2` | 仍走 Spring Cloud OpenFeign，不升级时使用 |

纯 Java：

```xml
<dependency>
    <groupId>com.github.shuaidd</groupId>
    <artifactId>qywx-core</artifactId>
    <version>6.0.0</version>
</dependency>
```

Spring Boot：

```xml
<dependency>
    <groupId>com.github.shuaidd</groupId>
    <artifactId>qywx-spring-boot-starter</artifactId>
    <version>6.0.0</version>
</dependency>
```

`qywx-core` 的日志走 SLF4J（`slf4j-api`）：纯 Java 项目自选一个绑定（示例用 `slf4j-simple`），Spring Boot 项目用自带的日志起步依赖即可。

## 模块结构

| 模块 | 内容 |
|---|---|
| `qywx-core` | 原生 OpenFeign client（`client/`）、service（`service/`）、`QywxClient` 装配入口、access_token 缓存 SPI、回调工具；零 Spring |
| `qywx-model` | DTO、枚举、异常与响应 |
| `qywx-spring-boot-autoconfigurator` | `qywx.*` 属性绑定与自动配置（内部模块，由 starter 引入） |
| `qywx-spring-boot-starter` | Spring Boot starter |
| `qywx-example` | 纯 Java 示例（不在根 reactor 内） |
| `qywx-spring-boot-example` | Spring Boot 示例与集成测试（不在根 reactor 内） |

## 纯 Java 用法

不需要 Spring 和容器：把配置交给 `QywxConfig.Builder`，`QywxClient.create` 得到入口，之后的调用与 Spring 下完全一致。

```java
ApplicationProperties application = new ApplicationProperties();
application.setApplicationName("my-app");
application.setSecret("应用的secret");

QywxConfig config = QywxConfig.builder()
        .corpId("企业号")
        .application(application)
        .build();

QywxClient client = QywxClient.create(config);
WeChatManager manager = client.weChatManager();

String token = manager.tokenService().getAccessToken("my-app");
WeChatUserResponse user = manager.addressBookService().getUser("userid", "my-app");
```

- 多个应用：在 builder 上多次调用 `application(...)`（或 `applicationList(...)`），回调应用同理 `callback(...)`；调用时用应用名指定以哪个应用的身份发请求。
- 可选项：`url(...)` / `publicPath(...)`（默认 `https://qyapi.weixin.qq.com` / `cgi-bin`）、`debugMode(true)`（请求追加 `debug=1` 并记录耗时）、`connectTimeoutMillis(...)` / `readTimeoutMillis(...)`（默认 10000 / 60000 毫秒）。
- access_token 默认缓存在进程内；多实例部署要共用时，实现 `TokenStore`（`get` / `put` / `evict`）后 `builder.tokenStore(...)`，缓存里存的是整条 `TokenEntry`（令牌 + 过期时刻）。
- HTTP 默认走 JDK `HttpURLConnection`，代理用 JVM 系统属性（`-Dhttps.proxyHost=... -Dhttps.proxyPort=...`）。
- 底层 27 个 client 接口用 `client.client(AddressBookClient.class)` 按类型获取。
- `QywxClient.create` 同时登记 `WeChatContextHolder` 静态入口与回调配置（`CallBackManager`），回调用法与 Spring 下一致。

完整示例见 `qywx-example` 的 `StandaloneExample`：仓库根目录先 `mvn -B -DskipTests install`，再 `mvn -f qywx-example/pom.xml compile exec:exec`（凭据从环境变量 `QYWX_CORP_ID` / `QYWX_SECRET` 读取）。
## Spring Boot 用法

配置 `application.yml`（键名与 5.x 完全一致）：

```yaml
qywx:
  corp-id: xxxx（企业号）
  application-list:
  - secret: Kx4sovYN5C0_MEzPY0cOymwbMhGmqdA9VjMFHrAKjdE
    agentId: 1000003
    application-name: little-helper
  - secret: DXB-FXVZNkLGUlLaIJy6CK67WD-dpN1HnPLIzNPo0N4
    agentId: 1000004
    application-name: reporter
  - secret: AfjvAed_ulqhK0OqTprDQ6xOSnqaT34ll2LsRe0D2NA
    application-name: address-book
  url: https://qyapi.weixin.qq.com
  public-path: cgi-bin
```

类路径上有 starter 即自动装配，直接注入 `WeChatManager`：

```java
@SpringBootTest(classes = Application.class)
public class WeChatTest {

    @Autowired
    private WeChatManager weChatManager;

    /**
     * 查询用户信息
     */
    @Test
    public void getUser() {
        weChatManager.addressBookService().getUser("13259220281", "address-book");
    }
}
```

- `@EnableWeChat` 不再是必需的（标注了也不会重复注册 bean）；`@EnableFeignClients`、`@EnableCaching`、`@EnableAspectJAutoProxy` 都不再需要。仍保留 `@EnableWeChat` 且自己注册了 `CacheManager` 时，请给它标 `@Primary`，原因见迁移指南 §5.1。
- 可选配置：`qywx.debug-mode`、`qywx.connect-timeout-millis` / `qywx.read-timeout-millis`（默认 10000 / 60000）。
- access_token 缓存：容器里有 `TokenStore` bean 直接用；没有则用 `CacheManager`（缓存名 `qywx`）；都没有则进程内缓存。接 Redis 等共享缓存时用 JDK 序列化（Boot 的 `RedisCacheConfiguration` 默认即是），不要用普通 JSON 序列化器：读回的不是 `TokenEntry` 会被视为未命中、每次重新取号。
- 上传 / 下载：`MediaService` 的入参、返回类型是 core 的 `QywxFile` / `FileDownload`；Controller 里用 `SpringFileBridge.toQywxFile(MultipartFile)` / `SpringFileBridge.toResponseEntity(FileDownload)` 互转（包 `com.github.shuaidd.autoconfigure`）。

实例可以查看 `qywx-spring-boot-example` 模块。

### 回调配置
![回调配置](https://upload-images.jianshu.io/upload_images/26817983-13eab16b4f158217.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 更新记录
```
2026-11-28 6.0.0 核心拆出不依赖 Spring 的 qywx-core（原生 OpenFeign），starter 降为薄适配层；Java 17 基线，升级见 MIGRATION-6.0.md
2026-11-28 依赖安全升级：Spring Boot 3.2.0 → 4.1.3，Jackson → 2.21.8（annotations 2.21），commons-lang3 3.17.0 → 3.20.0，commons-io 2.18.0 → 2.22.0，commons-codec 1.17.1 → 1.21.0
2025-01-03 文档管理，智能表格管理
2023-07-28 支持多企业号
2022-10-24 异步上传临时素材接口
2022-10-20 新增管理商品图册接口 管理聊天敏感词接口
2022-09-21 新增企业互联接口 电子发票接口，企业邮箱接口 【未实测，本人测试的企业微信没有权限-_-】
2022-09-04 新增企业微信微盘管理相关接口
2022-09-03 新增接口 支持知识库分组管理，支持知识库问答管理，支持企业为打卡人员补卡 
2022-08-31 新增获取子部门ID列表接口，获取成员ID列表接口，回调小优化返回解密后的xml
2022-01-12 优化细节，支持spring boot 高版本
2021-11-25 新增微信客服模块接口[客服账号管理，接待人员管理 会话分配 消息收发 客户信息获取等]
2021-11-24 新增通讯录异步导出接口-[导出成员，导出成员详情，导出部门，导出标签成员，获取导出结果，导出任务完成通知]，使用方式详见测试用例 AddressBookTest
2021-08-05 修复应用消息回调空指针问题 issue#9 
2021-07-20 统一回调处理 例子见example模块
2021-07-20 已实现模块 通讯录管理，客户联系，身份认证，应用管理，消息推送，素材管理，OA【除自建审批流外】，效率工具
2021-07-15 补充OA模块新增的接口
2021-07-14 处理企业微信回调数据统一解析处理
```
