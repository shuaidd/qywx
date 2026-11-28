# 从 5.x 升级到 6.0

6.0 把核心实现拆成**不依赖 Spring** 的 `qywx-core`（原生 OpenFeign），`qywx-spring-boot-starter` 降为薄适配层。包名、类名、service 方法签名基本不变，但模块坐标、client 接口注解、文件类型、`@EnableWeChat` 语义等有破坏性变更，升级需要手工处理，不能只改版本号。

Spring 侧 `qywx.*` 的键名与 `WeChatManager` 的调用方式与 5.x 完全一致；纯 Java 用法是新增的（§6）。

## 1. 破坏性变更一览

| # | 变更 | 5.x | 6.0 | 详见 |
|---|---|---|---|---|
| 1 | Java 基线 | 源码按 Java 8 编译，但传递依赖里已有 Java 11 字节码，Java 8 运行时实际不可达 | Java 17 | §2 |
| 2 | 模块坐标 | `qywx-spring-boot-api`、`qywx-spring-boot-model` | `qywx-core`、`qywx-model`（包名 `com.github.shuaidd.*` 不变） | §2 |
| 3 | Feign 栈 | Spring Cloud OpenFeign + `feign-jackson` + `feign-form` + `commons-fileupload` | 只依赖 `feign-core`；client 不再是 `@FeignClient`，`@EnableFeignClients` 扫不到 | §2、§5.5 |
| 4 | `@EnableWeChat` | 自带 `@EnableCaching`、`@EnableAspectJAutoProxy`、`@EnableFeignClients(basePackages = "com.github.shuaidd.client")` | 只剩 `@Import(WeChatAutoConfiguration.class)`；Boot 2.7 起不标注也会自动装配 | §5.1 |
| 5 | client 接口注解 | `@FeignClient` + `@GetMapping`/`@PostMapping` + `@RequestParam` | `@RequestLine` + `@Headers` + `@Param` | §5.5 |
| 6 | 文件类型 | `org.springframework.http.MediaType`、`MultipartFile`、`ResponseEntity<byte[]>` | `String contentType`、`QywxFile`、`FileDownload` | §4 |
| 7 | service 构造 | `@Service` + `@Autowired` 注入，由 `@ComponentScan` 扫出 | 构造器注入 `QywxContext`，由 `QywxClient` 装配，类上没有 Spring 注解 | §5.6 |
| 8 | 令牌失效重试 | `WeChatClientAspect` 切面，只对能映射到枚举的错误码重试 | 调用处理器按响应原始 errcode（40014 / 41001 / 42001）清缓存后重试一次，不依赖 AOP | §5.1 |
| 9 | access_token 缓存 | `TokenService` 上的 `@Cacheable(value = "qywx", key = "'qywx_access_token_'+#applicationName")`，值是字符串 | `TokenStore` SPI；Spring 下自动用容器里的 `CacheManager`（缓存名 `qywx`，键 `access_token:<应用名>`，值是整条 `TokenEntry`） | §5.2 |
| 10 | bean 名 | client 为接口全限定名；`OAService`、`weChatCallCenterService` | client 为简单类名小驼峰；`oaService`、`callCenterService`；新增 `QywxClient`、`TokenManager` | §5.3 |
| 11 | 超时配置 | `feign.client.config.*`（Boot 3.x 为 `spring.cloud.openfeign.client.config.*`） | `qywx.connect-timeout-millis` / `qywx.read-timeout-millis`（默认 10000 / 60000，与 5.x Feign 默认相同） | §5.4 |

## 2. 升级步骤

1. **JDK 17+**。
2. **改坐标**：

   | 用途 | 5.x | 6.0 |
   |---|---|---|
   | Spring Boot 项目 | `com.github.shuaidd:qywx-spring-boot-starter:5.2.0` | `com.github.shuaidd:qywx-spring-boot-starter:6.0.0`（坐标不变，只改版本） |
   | 只用 DTO / 枚举 / 异常 | `qywx-spring-boot-model` | `qywx-model` |
   | 直接依赖实现模块 | `qywx-spring-boot-api` | `qywx-core` |
   | 纯 Java 项目（新增） | — | `qywx-core` |

3. **删依赖**：`spring-cloud-starter-openfeign`、`feign-jackson`，以及仅为它们引入的 `spring-cloud-dependencies` BOM。6.0 的 starter 不再需要 Spring Cloud；项目其它地方不用 Spring Cloud 时整个 BOM 都可以去掉。`feign-form`、`commons-fileupload` 不再由本库传递，项目自己用到才需要显式声明。
4. **删注解**：`@EnableFeignClients`（扫不到 6.0 的 client）、`@EnableAspectJAutoProxy`（项目自己用 AOP 则保留）、`@EnableCaching`（项目自己用 Spring Cache 则保留；适配层不依赖它）。`@EnableWeChat` 可留可删，保留时注意 §5.1。
5. **改文件类型**（§4）：`MediaType` → `String`，`MultipartFile` → `QywxFile`，`ResponseEntity<byte[]>` → `FileDownload`。
6. **迁超时配置**（§5.4）：`feign.client.config.*` → `qywx.connect-timeout-millis` / `qywx.read-timeout-millis`。
7. **编译并按 §5 核对**：按名字取 bean 的 `@Qualifier` / `@Resource`（§5.3）、自定义 `CacheManager`（§5.1、§5.2）、手工 `new` 的 service（§5.6）、直接调用 client 接口的代码（§5.5）。

## 3. 依赖矩阵

| 构件 | 5.x | 6.0 |
|---|---|---|
| Java | 8（名义上） | 17 |
| Spring Boot（配合 starter） | 2.x → 4.4.2，3.x → 5.x | 4.1.3 实测（BOM 基线）；3.5.16 实测通过（自动配置排序按名字声明，两代包名都生效）；2.7 起应可用，未实测 |
| Spring Cloud OpenFeign | 必须 | 不需要 |
| `feign-core` | 13.5（经 Spring Cloud 2023.0.4） | 13.5（`qywx-core` 直接依赖） |
| `feign-jackson` / `feign-form` / `commons-fileupload` | 11.9.1 / 13.5 / 1.5（经 starter 传递） | 已移除 |
| Jackson | 2.15.3（Boot BOM 3.2.0） | `qywx-core` / `qywx-model` 直接依赖 core / databind 2.21.8、annotations 2.21（根 pom `qywx.jackson.version` / `qywx.jackson-annotations.version`）；Spring 下由 Boot BOM 管理（4.1.3 → 同一套） |
| SLF4J | 经 Spring Boot | `slf4j-api` 2.0.9；Spring Boot 用自带日志起步依赖，纯 Java 项目自选绑定 |
| `commons-lang3` / `commons-collections4` / `commons-codec` / `commons-io` | 传递依赖 | 仍由 `qywx-core` 传递：3.20.0 / 4.1 / 1.21.0 / 2.22.0（根 pom 管理） |

## 4. 文件类型桥接

core 不依赖 spring-web，`MediaService` 里出现 Spring 类型的几个方法改了签名（包名、方法名、其余参数顺序都不变）：

| 5.x | 6.0 |
|---|---|
| `uploadMaterial(File, MediaType, String type, String app)` | `uploadMaterial(File, String contentType, String type, String app)` |
| `uploadMaterial(MultipartFile, String type, String app)` | `uploadMaterial(QywxFile, String type, String app)` |
| `uploadAttachment(MultipartFile, String mediaType, Integer attachmentType, String app)` | `uploadAttachment(QywxFile, String mediaType, Integer attachmentType, String app)` |
| `uploadImage(File, MediaType, String app)` | `uploadImage(File, String contentType, String app)` |
| `download(String mediaId, String app)` 返回 `ResponseEntity<byte[]>` | 返回 `FileDownload` |
| `downloadJsMedia(String mediaId, String app)` 返回 `ResponseEntity<byte[]>` | 返回 `FileDownload` |

`uploadAttachment(File, …)`、`downloadFile(…)`、`downloadJsMediaFile(…)`、`uploadByUrl(…)`、`getUploadByUrlResult(…)` 签名不变。`FileDownload` 提供 `getContent()`（`byte[]`）、`getHeaders()`、`getHeader(String)`、`getContentType()`、`getFileName()`（从 `Content-Disposition` 解析，没有时为 null）。

`contentType` 直接写字符串，例如 `MediaType.IMAGE_PNG` 对应 `"image/png"`；传 `null` 时方法返回 `null`。

Spring 项目：Controller 收到的 `MultipartFile` 用适配层的 `SpringFileBridge` 互转（包 `com.github.shuaidd.autoconfigure`；starter 已以 optional 方式引入 `spring-web`）：

```java
import com.github.shuaidd.autoconfigure.SpringFileBridge;
import com.github.shuaidd.file.FileDownload;
import org.springframework.http.ResponseEntity;

// 上传
String mediaId = weChatManager.mediaService()
        .uploadMaterial(SpringFileBridge.toQywxFile(file), "image", "my-app");

// 下载
FileDownload download = weChatManager.mediaService().download(mediaId, "my-app");
ResponseEntity<byte[]> response = SpringFileBridge.toResponseEntity(download);
```

`toQywxFile(MultipartFile)` 会把整个文件读进内存（`getBytes()`），文件名按「原始文件名 → 表单字段名 → `file`」回退；`toResponseEntity(FileDownload)` 固定返回 200，`Content-Type` / `Content-Disposition` 等头原样带过去。

纯 Java：

```java
// 本地文件：Content-Type 先按系统探测，探测不到再按文件名猜测
String mediaId = weChatManager.mediaService()
        .uploadMaterial(QywxFile.of(new File("/tmp/a.png")), "image", "my-app");

// 字节数组：可指定 Content-Type
QywxFile file = new QywxFile(bytes, "a.png", "image/png");

FileDownload download = weChatManager.mediaService().download(mediaId, "my-app");
byte[] content = download.getContent();
String filename = download.getFileName();
```

## 5. 行为差异（Spring Boot）

### 5.1 `@EnableWeChat` 与 `CacheManager` 的装配顺序

6.0 的 `@EnableWeChat` 只剩 `@Import(WeChatAutoConfiguration.class)`，不再自带 `@EnableCaching`、`@EnableAspectJAutoProxy`、`@EnableFeignClients`。Boot 2.7 起 starter 通过自动配置登记（`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`），不加注解也会装配。

自动配置路径下 `WeChatAutoConfiguration` 标了 `@AutoConfiguration(afterName = {"org.springframework.boot.cache.autoconfigure.CacheAutoConfiguration", "org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration"})`（按名字而不是 class：Boot 4 把 `CacheAutoConfiguration` 搬进了 `spring-boot-cache` 模块、换了包名，两代包名都列上才对 Boot 3.x / 4.x 都生效），一定排在 `CacheAutoConfiguration` 之后：项目自己的 `CacheManager`（Redis、Caffeine 等）先登记，适配层自带的 `ConcurrentMapCacheManager`（`@ConditionalOnMissingBean(CacheManager.class)`）让位。

经 `@EnableWeChat` 的 `@Import` 进来时 `after` 不生效，适配层的 `CacheManager` 可能先登记，于是两个 `CacheManager` 并存且都没有 `@Primary`：装配 `qywxClient` 时 `ObjectProvider#getIfAvailable()` 抛 `NoUniqueBeanDefinitionException`，启动失败（刻意不吞，5.x 用 `@EnableWeChat` + Redis 也会撞到同类问题）。

- 推荐直接删掉 `@EnableWeChat`；
- 一定要保留时，给自己的 `CacheManager` 标 `@Primary`。

另外令牌失效重试不再依赖 AOP：调用处理器按响应里的原始 errcode（40014 / 41001 / 42001）清缓存后重试一次，`WeChatException.unknown` 携带这些码时同样重试；`@EnableAspectJAutoProxy` 删掉不影响。

### 5.2 access_token 缓存

| | 5.x | 6.0 |
|---|---|---|
| 机制 | `TokenService` 方法上的 `@Cacheable` / `@CacheEvict` | `TokenStore` SPI（`get` / `put` / `evict`），`TokenManager` 负责取用与刷新 |
| Spring 下接线 | 需要 `@EnableCaching` | 容器里有 `TokenStore` bean 直接用；否则取 `CacheManager` 包成 `SpringCacheTokenStore`；都没有用进程内 `InMemoryTokenStore` |
| 缓存名 | `qywx` | `qywx` |
| 键 | `qywx_access_token_<应用名>` | `access_token:<应用名>` |
| 值 | 字符串 | 整条 `TokenEntry`（令牌 + 过期时刻） |

- 缓存命中后仍校验过期时刻：写入时把 `expires_in` 扣掉 300 秒缓冲（最少 60 秒），到期即重取。Spring 侧的 TTL 设不设都安全，建议 ≥ 7200 秒。
- 同一应用同一时刻只允许一个线程调 `gettoken`，其余线程等待后复用结果。
- 旧键不再读；滚动升级中新旧实例各用各的键，互不影响。
- 共享缓存的序列化：`TokenEntry` 实现了 `Serializable`，Boot 的 `RedisCacheConfiguration` 默认 JDK 序列化可直接用。换成普通 JSON 序列化器时读回的是 `LinkedHashMap`，不是 `TokenEntry` → 视为未命中、每次重取（不报错，但每次调用多一次取号请求）；要用 JSON 请选带类型信息的序列化器（如 `GenericJackson2JsonRedisSerializer`）。
- `CacheManager` 里没有名为 `qywx` 的缓存：打一条 warn 后退回进程内缓存（Boot 默认的 `ConcurrentMapCacheManager` 是动态模式，会按需建；适配层自带的只建 `qywx`）。
- 缓存操作抛出的异常原样抛出，不会静默退回进程内。
- 想完全自控：注册一个 `TokenStore` bean，适配层优先用它。

### 5.3 bean 名

| bean | 5.x | 6.0 |
|---|---|---|
| 27 个 client 接口 | 接口全限定名，如 `com.github.shuaidd.client.AddressBookClient` | 简单类名小驼峰，如 `addressBookClient` |
| `OAService` | `OAService` | `oaService` |
| `WeChatCallCenterService` | `weChatCallCenterService` | `callCenterService` |
| `QywxClient` | — | `qywxClient`（新增） |
| `TokenManager` | — | `tokenManager`（新增） |

按类型注入（`@Autowired` / 构造器注入）不受影响；按名字取的 `@Qualifier("...")` / `@Resource(name = "...")` 要改成新名字。其余 service 的 bean 名与 5.x 相同（简单类名小驼峰）。

### 5.4 配置键与默认值

| 键 | 5.x | 6.0 |
|---|---|---|
| `qywx.connect-timeout-millis` / `qywx.read-timeout-millis` | — | 新增，默认 10000 / 60000 |
| `feign.client.config.*`（Boot 3.x：`spring.cloud.openfeign.client.config.*`） | 控制超时等 | 对本库不再生效 |
| `qywx.url` / `qywx.public-path` 未配置时 | null（client 上的占位符兜底） | 直接是默认值 `https://qyapi.weixin.qq.com` / `cgi-bin`，请求地址不变 |
| `qywx.application-list` / `qywx.callback-list` 未配置时 | null | 空列表 |

其余键名（`corp-id`、`debug-mode`、`application-list[]` 下的 `application-name` / `secret` / `agent-id` / `application-desc` / `corp-id`）与宽松绑定规则不变（`agentId` 与 `agent-id` 等价）。`TokenStore` 是编程式 SPI，没有 `qywx.*` 键，Spring 下注册 bean 即可。`debug-mode: true` 仍是请求追加 `debug=1` 并在 DEBUG 级别记录耗时。

### 5.5 直接调用 client 接口

方法名、参数顺序、返回类型都没变，只是接口上的注解从 `@FeignClient` + `@PostMapping` + `@RequestParam` 换成了 `@RequestLine` + `@Headers` + `@Param`。调用代码不用改：

```java
// 5.x 与 6.0 都是这一行；Spring 下按类型注入 client 接口仍然可用
BaseResponse response = addressBookClient.createUser(request, "my-app");

// 6.0 新增：不经过 Spring 也能按类型取 client
AddressBookClient addressBookClient = qywxClient.client(AddressBookClient.class);
```

- 5.x 的 `com.github.shuaidd.client.config.WeChatConfiguration`（Feign `Decoder` / `Encoder` / `RequestInterceptor` 的装配类）已删除，`@FeignClient(configuration = ...)`、`@Import` 复用它的代码要去掉。
- 项目自己声明的 Feign `Encoder` / `Decoder` / `Contract` / `RequestInterceptor` bean 不再作用于本库，core 自带一套完整装配。
- `CommonClient` 仍是常量接口（`HEAD_KEY`、`HEAD`、`ACCESS_TOKEN` 等），27 个 client 接口仍 `extends CommonClient`。

### 5.6 手工 new service

5.x 的 service 是 `@Service` + `@Autowired` 字段注入，脱离容器 `new` 出来不可用。6.0 的 service 是普通类，构造器注入协作者，但**不需要手工 new**：Spring 下注入 `WeChatManager`（或具体 service）即可。确实需要独立实例时，从 `QywxClient` 取协作者：

```java
MediaService mediaService = new MediaService(
        qywxClient.context(), qywxClient.client(WeChatMediaClient.class));
```

需要另一套配置（不同企业 / 不同超时）时，更干净的做法是再 `QywxClient.create(anotherConfig)` 建一个完整客户端，注意它会覆盖 `WeChatContextHolder` 的静态入口（§7）。

## 6. 纯 Java 用法

依赖只需 `qywx-core`（`slf4j-api` 由它传递），再按项目选一个 SLF4J 绑定（`slf4j-simple` / `logback-classic` / `log4j-slf4j2-impl`），不加只会有一条 NOP 绑定警告。配置与调用方式和 Spring 下完全一致，只是配置从 `application.yml` 换成 builder：

```java
import com.github.shuaidd.QywxClient;
import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.response.addressbook.WeChatUserResponse;
import com.github.shuaidd.support.WeChatManager;

ApplicationProperties application = new ApplicationProperties();
application.setApplicationName("my-app");
application.setSecret("<应用 secret>");
application.setCorpId("<企业 corpId>");
application.setAgentId("<应用 agentId>");

QywxConfig config = QywxConfig.builder()
        .corpId("<企业 corpId>")
        .application(application)
        .build();

QywxClient client = QywxClient.create(config);
WeChatManager manager = client.weChatManager();

String token = manager.tokenService().getAccessToken("my-app");
WeChatUserResponse user = manager.addressBookService().getUser("userid", "my-app");
```

- 多个应用：在 builder 上多次调用 `application(...)`（或 `applicationList(...)`），回调应用同理 `callback(...)` / `callbackList(...)`；调用时用应用名指定以哪个应用的身份发请求。
- 可选项：`url(...)` / `publicPath(...)`（默认 `https://qyapi.weixin.qq.com` / `cgi-bin`）、`debugMode(true)`（请求追加 `debug=1` 并记录耗时）、`connectTimeoutMillis(...)` / `readTimeoutMillis(...)`（默认 10000 / 60000 毫秒）。
- access_token 默认缓存在进程内；多实例部署要共用时，实现 `TokenStore`（`get` / `put` / `evict`）后 `builder.tokenStore(...)`。缓存里存整条 `TokenEntry`：用 `TokenEntry.of(token, expiresInSeconds, nowMillis)` 构造，用 `isExpired(nowMillis)` 判过期。
- HTTP 默认走 JDK `HttpURLConnection`，代理用 JVM 系统属性（`-Dhttps.proxyHost=... -Dhttps.proxyPort=...`）。
- 底层 27 个 client 接口用 `client.client(AddressBookClient.class)` 按类型获取。
- `QywxClient.create` 同时登记 `WeChatContextHolder` 静态入口与回调配置（`CallBackManager`），回调用法与 Spring 下一致。

完整示例见 `qywx-example` 的 `StandaloneExample`：仓库根目录先 `mvn -B -DskipTests install`，再 `mvn -f qywx-example/pom.xml compile exec:exec`（凭据从环境变量 `QYWX_CORP_ID` / `QYWX_SECRET` 读取）。

## 7. 已知限制

- 自定义 HTTP 层未公开：`QywxClient.create(QywxConfig, feign.Client)` 是包内可见，公开入口只有 `create(QywxConfig)`。当前版本接不了自定义连接池 / 拦截器，代理只能走 JVM 系统属性。
- `QywxClient` 不是 `AutoCloseable`（底层 `HttpURLConnection` 没有需要显式关闭的资源）。
- 多实例：`QywxClient.create` 会覆盖 `WeChatContextHolder` 的静态入口与 `CallBackManager` 的回调配置，静态入口始终指向最后创建的那个。
- Spring Boot 2.7 只做了理论兼容（`@AutoConfiguration` 自 2.7 引入），未实测；3.2.0 实测通过。
- `TokenEntry` 没有默认构造器与 Jackson 注解，普通 JSON 序列化不友好（§5.2）。
- `QywxFile` 持有 `byte[]`：`SpringFileBridge.toQywxFile(MultipartFile)` 与 `QywxFile.of(File)` 都会把整个文件读进内存，本版本没有流式上传入口。
