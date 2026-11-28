package com.github.shuaidd;

import com.github.shuaidd.client.WeChatMediaClient;
import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.file.DownloadContext;
import com.github.shuaidd.file.FileDownload;
import com.github.shuaidd.file.QywxFile;
import com.github.shuaidd.resquest.job.JobIdRequest;
import com.github.shuaidd.resquest.media.UploadByUrlRequest;
import com.github.shuaidd.response.material.UploadByUrlResultResponse;
import com.github.shuaidd.response.material.WeChatMediaUploadResponse;
import com.github.shuaidd.service.MediaService;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.io.TempDir;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * 描述 临时素材管理接口的真实企业微信联调：用自建应用的 corpId + secret 把 6.0.0 的 qywx-core 素材接口跑一遍
 * <p>
 * 默认跳过；只有显式设置 {@code QYWX_LIVE=true} 时才执行，避免普通构建依赖网络与凭据。读取环境变量：
 * {@code QYWX_CORP_ID}（企业号）、{@code QYWX_SECRET}（自建应用 secret）、{@code QYWX_APP}（应用名，默认 media-live）、
 * {@code QYWX_AGENT_ID}（应用 agentId，默认 1000002；只写进配置，qywx-core 不会把它带进请求）。
 * <p>
 * 覆盖 {@code WeChatMediaClient} / {@code MediaService} 的全部临时素材接口：上传临时素材（image / file，client 与 service
 * 两种入口、{@code QywxFile} 与 {@code File} 两种重载）、上传附件（朋友圈）、上传图片、内存下载、落盘下载（指定文件名 /
 * 目录 + 服务端文件名）、JSSDK 高清语音下载（只能用非法 id 验证错误解码）、url 异步上传及其结果轮询，
 * 以及非法 jobid、过小文件两条错误路径。
 * <p>
 * 上传的都是进程内生成的测试文件：临时素材的 media_id 三天后失效；唯一例外是 uploadimg，按接口注释它上传的是「永久图片」，
 * 会留下一个长期有效的图片地址（16x16 测试图，仅用于图文正文）；除此之外不写任何数据，也不发送消息；
 * 所有输出只打印数量、长度与错误码，不打印 secret、access_token、media_id 与图片地址。
 * <p>
 * 自建应用未开通对应权限时企业微信返回 48002 / 48004 等错误码：按「跳过」处理并打出错误码（这是企业微信的授权策略，不是 SDK 缺陷）；
 * 上传附件与 url 上传要求应用具备「客户联系」相关权限，默认没有时跳过属正常。其余异常照常让用例失败。
 * <p>
 * 运行（仓库根目录，PowerShell）：
 * <pre>
 * $env:QYWX_LIVE='true'; $env:QYWX_CORP_ID='企业号'; $env:QYWX_SECRET='自建应用secret'
 * mvn -B -pl qywx-core -am test "-Dtest=MediaLiveTest" "-Dsurefire.failIfNoSpecifiedTests=false"
 * </pre>
 *
 * @author ddshuai
 * date 2026-11-28
 **/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnabledIfEnvironmentVariable(named = "QYWX_LIVE", matches = "true")
@ExtendWith(MediaLiveTest.PermissionDeniedHandler.class)
public class MediaLiveTest {

    private static final String DEFAULT_APP = "media-live";

    private static final String DEFAULT_AGENT_ID = "1000002";

    /** 凭据权限范围不足的错误码：48002 api forbidden、48004 授权关系无效 */
    private static final List<String> PERMISSION_DENIED = Arrays.asList("48002", "48004");

    /** 文档 90253：所有文件大小必须大于 5 字节，这里放一段带时间戳的文本保证每次联调内容不同 */
    private static final byte[] TEXT_BYTES = ("qywx core 临时素材联调 " + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8);

    /** 非法的 media_id：不可能是真实素材，用来探 IP 白名单与验证错误解码 */
    private static final String BOGUS_MEDIA_ID = "invalid-media-id-for-live-test";

    /** 非法的 JSSDK 语音 media_id：高清语音只能由 JSSDK 的 uploadVoice 产生，联调环境没有，只能验证错误路径 */
    private static final String BOGUS_JS_MEDIA_ID = "invalid-jssdk-media-id-for-live-test";

    @TempDir
    static Path tempDir;

    private String corpId;

    private String secret;

    private String app;

    private QywxClient client;

    private MediaService mediaService;

    private WeChatMediaClient mediaClient;

    /** 进程内生成的一张 PNG：上传、下载、url 上传共用同一份字节 */
    private byte[] png;

    /** client 层上传的图片素材，懒加载后各用例共享（避免重复上传） */
    private WeChatMediaUploadResponse imageUpload;

    private String fileMediaId;

    private String imageUrl;

    @BeforeAll
    void setUp() {
        corpId = System.getenv("QYWX_CORP_ID");
        secret = System.getenv("QYWX_SECRET");
        app = System.getenv("QYWX_APP");
        if (isBlank(app)) {
            app = DEFAULT_APP;
        }
        String agentId = System.getenv("QYWX_AGENT_ID");
        if (isBlank(agentId)) {
            agentId = DEFAULT_AGENT_ID;
        }
        assumeTrue(!isBlank(corpId) && !isBlank(secret), "未设置 QYWX_CORP_ID / QYWX_SECRET，跳过真实企业联调");

        ApplicationProperties application = new ApplicationProperties();
        application.setApplicationName(app);
        application.setSecret(secret);
        application.setCorpId(corpId);
        application.setAgentId(agentId);

        client = QywxClient.create(QywxConfig.builder()
                .corpId(corpId)
                .application(application)
                .build());
        mediaService = client.weChatManager().mediaService();
        mediaClient = client.client(WeChatMediaClient.class);
        System.out.println("[live] app=" + app + " agentId=" + agentId + " baseUrl=" + client.config().baseUrl());
        probeTrustedIp();
    }

    /**
     * 48002 / 48004 是凭据权限范围所致而不是 SDK 缺陷：这类错误按「跳过」处理并打出错误码，其余异常原样抛出让用例失败。
     * JUnit 5.10 只有 TestExecutionExceptionHandler 接口（方法级注解要 5.13），所以用嵌套 Extension 实现
     */
    static class PermissionDeniedHandler implements TestExecutionExceptionHandler {

        @Override
        public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
            WeChatException weChatException = findWeChatException(throwable);
            if (weChatException != null && PERMISSION_DENIED.contains(weChatException.getCode())) {
                String message = "[skipped] errcode=" + weChatException.getCode() + " 当前凭据无权调用：" + weChatException.getMessage();
                System.out.println(message);
                Assumptions.abort(message);
            }
            throw throwable;
        }
    }

    /**
     * 企业微信先校验调用方出口 IP（errcode 60020 不在可信 IP）再做业务校验：这里用一次最轻量的读请求提前探明，
     * 避免整组用例都报成同一个看不懂的错；非 IP 问题（例如非法 media_id 的 40007）不阻断
     */
    private void probeTrustedIp() {
        try {
            mediaService.download(BOGUS_MEDIA_ID, app);
            System.out.println("[live] trusted-ip probe: 非法 media_id 没有报错，说明 media/get 的错误响应没带 error-code 头，"
                    + "decoder 可能把错误 JSON 当成了文件（见 downloadJsMedia* 用例）");
        } catch (RuntimeException e) {
            WeChatException weChatException = findWeChatException(e);
            if (weChatException != null && "60020".equals(weChatException.getCode())) {
                fail("企业微信拒绝了本机出口 IP（errcode 60020）：" + weChatException.getMessage()
                        + "。请让企业管理员把该出口 IP 加入自建应用的可信 IP 后重跑；"
                        + "加白前只能验证取号链路，业务接口无法联调。");
            }
            System.out.println("[live] trusted-ip probe errcode=" + (weChatException == null ? "n/a" : weChatException.getCode())
                    + "，非 60020，继续执行");
        }
    }

    /**
     * client 层上传临时图片素材（文档 90253）：返回 type、media_id、created_at
     */
    @Test
    public void uploadMaterialImageViaClientReturnsTypeMediaIdAndCreatedAt() throws IOException {
        WeChatMediaUploadResponse response = imageUpload();

        assertEquals(MediaService.IMAGE, response.getType(), "type 应为 image");
        assertFalse(isBlank(response.getMediaId()), "media_id 为空");
        assertNotNull(response.getCreatedAt(), "created_at 为空");
        long createdAtAge = Math.abs(System.currentTimeMillis() - response.getCreatedAt().getTime());
        assertTrue(createdAtAge < TimeUnit.DAYS.toMillis(1), "created_at 应为最近的时间（企业微信返回秒级时间戳），实际为：" + response.getCreatedAt());
        System.out.println("[live] media/upload image ok, mediaIdLength=" + response.getMediaId().length()
                + ", createdAt=" + response.getCreatedAt());
    }

    /**
     * service 层上传临时文件素材：QywxFile 与 File 两种重载都要能拿到 media_id
     */
    @Test
    public void uploadMaterialFileViaServiceReturnsMediaId() throws IOException {
        String byFile = mediaService.uploadMaterial(writeTempFile("live-file.txt", TEXT_BYTES), "text/plain", MediaService.FILE, app);

        assertFalse(isBlank(byFile), "File 重载未返回 media_id");
        assertNotEquals(fileMediaId(), byFile, "两次上传应得到不同的 media_id");
        System.out.println("[live] media/upload file ok, mediaIdLength=" + byFile.length());
    }

    /**
     * 上传图片（文档 90256）：返回图文正文可用的永久图片地址（不是 media_id）
     */
    @Test
    public void uploadImageViaServiceReturnsPermanentUrl() throws IOException {
        String url = imageUrl();

        assertTrue(url != null && url.startsWith("http"), "图片地址应以 http 开头");
        System.out.println("[live] media/uploadimg ok, urlLength=" + url.length() + ", host=" + host(url));
    }

    /**
     * 内存下载（文档 90254）：字节与上传前完全一致，并带回 Content-Type / Content-Disposition
     */
    @Test
    public void downloadReturnsUploadedBytesInMemory() {
        FileDownload download = mediaService.download(fileMediaId(), app);

        assertNotNull(download, "下载结果为空");
        assertArrayEquals(TEXT_BYTES, download.getContent(), "下载内容与上传内容不一致");
        assertFalse(isBlank(download.getContentType()), "Content-Type 为空");
        assertFalse(isBlank(download.getFileName()), "Content-Disposition 文件名为空");
        assertFalse(isBlank(download.getHeader(FileDownload.CONTENT_DISPOSITION)), "Content-Disposition 为空");
        System.out.println("[live] media/get ok, length=" + download.getContent().length + ", fileName=" + download.getFileName());
    }

    /**
     * 落盘下载到指定文件：调用结束后 DownloadContext 必须被清掉，避免线程复用串到别的请求
     */
    @Test
    public void downloadFileWritesToExplicitPath() throws IOException {
        Path target = tempDir.resolve("downloaded-image.png");
        File file = mediaService.downloadFile(imageMediaId(), target.toString(), app);

        assertNotNull(file, "下载文件为空");
        assertEquals(target.toAbsolutePath().normalize(), file.toPath().toAbsolutePath().normalize(), "落盘路径应为指定文件");
        assertArrayEquals(png(), Files.readAllBytes(target), "落盘内容与上传内容不一致");
        assertNull(DownloadContext.get(), "downloadFile 返回后 DownloadContext 应已恢复");
        System.out.println("[live] media/get -> explicit file ok, size=" + Files.size(target));
    }

    /**
     * 落盘下载到目录：文件名取服务端 Content-Disposition（文档 90254：attachment; filename="MEDIA_ID.jpg"）
     */
    @Test
    public void downloadFileUsesServerFileNameInDirectory() throws IOException {
        Path dir = Files.createDirectories(tempDir.resolve("server-name"));
        File file = mediaService.downloadFile(imageMediaId(), dir.toString(), app);

        assertNotNull(file, "下载文件为空");
        assertEquals(dir.toAbsolutePath().normalize(), file.toPath().getParent().toAbsolutePath().normalize(), "文件应保存在目标目录下");
        FileDownload inMemory = mediaService.download(imageMediaId(), app);
        assertEquals(inMemory.getFileName(), file.getName(), "文件名应与服务端 Content-Disposition 一致");
        try (Stream<Path> entries = Files.list(dir)) {
            assertEquals(1L, entries.count(), "目录下应只有一个下载文件");
        }
        assertArrayEquals(png(), Files.readAllBytes(file.toPath()), "落盘内容与上传内容不一致");
        assertNull(DownloadContext.get(), "downloadFile 返回后 DownloadContext 应已恢复");
        System.out.println("[live] media/get -> directory ok, fileName=" + file.getName());
    }

    /**
     * JSSDK 高清语音下载只能用 JSSDK uploadVoice 得到的 id（文档 90255）：这里只能用非法 id 验证错误解码
     */
    @Test
    public void downloadJsMediaWithInvalidIdThrowsWeChatException() {
        WeChatException error = weChatError(() -> mediaService.downloadJsMedia(BOGUS_JS_MEDIA_ID, app));

        assertFalse(isBlank(error.getCode()), "errcode 为空");
        System.out.println("[live] media/get/jssdk invalid id errcode=" + error.getCode());
    }

    /**
     * JSSDK 高清语音落盘下载（文档 90255）：出错时不创建目标文件，DownloadContext 也要恢复
     */
    @Test
    public void downloadJsMediaFileWithInvalidIdThrowsWeChatException() {
        Path target = tempDir.resolve("js-should-not-exist.dat");
        WeChatException error = weChatError(() -> mediaService.downloadJsMediaFile(BOGUS_JS_MEDIA_ID, target.toString(), app));

        assertFalse(isBlank(error.getCode()), "errcode 为空");
        assertFalse(Files.exists(target), "下载出错时不应创建目标文件");
        assertNull(DownloadContext.get(), "downloadJsMediaFile 返回后 DownloadContext 应已恢复");
        System.out.println("[live] media/get/jssdk -> file invalid id errcode=" + error.getCode());
    }

    /**
     * url 异步上传（文档 96219）：先拿 jobid，再轮询结果直到完成，最后下载校验字节
     * <p>
     * 文件地址用 uploadimg 得到的图片地址；md5 按文档取地址上文件的 md5（本地按 Range 取一次），取不到时退回上传前的字节
     */
    @Test
    public void uploadByUrlThenQueryResultUntilDone() throws Exception {
        String url = imageUrl();
        byte[] remote = fetchBytes(url);
        byte[] expected = remote == null ? png() : remote;
        String md5 = md5Hex(expected);

        UploadByUrlRequest request = new UploadByUrlRequest();
        request.setScene(1);
        request.setType(MediaService.FILE);
        request.setFileName("live-by-url.png");
        request.setUrl(url);
        request.setMd5(md5);
        String jobId = mediaService.uploadByUrl(request, app);
        assertFalse(isBlank(jobId), "jobid 为空");
        System.out.println("[live] media/upload_by_url ok, jobIdLength=" + jobId.length());

        JobIdRequest query = new JobIdRequest();
        query.setJobId(jobId);
        UploadByUrlResultResponse result = null;
        for (int i = 0; i < 10; i++) {
            result = mediaService.getUploadByUrlResult(query, app);
            assertNotNull(result, "异步任务结果为空");
            if (!Objects.equals(result.getStatus(), 1)) {
                break;
            }
            System.out.println("[live] upload_by_url status=1 处理中，2s 后重试（" + (i + 1) + "/10）");
            TimeUnit.SECONDS.sleep(2);
        }
        assertNotNull(result, "异步任务结果为空");
        System.out.println("[live] upload_by_url status=" + result.getStatus());
        if (Objects.equals(result.getStatus(), 3)) {
            String code = result.getDetail() == null ? null : result.getDetail().getErrCode();
            Assumptions.abort("企业微信服务端拉取文件失败（status=3, errcode=" + code + "）：url 上传由企业微信下载，环境相关，跳过");
        }
        assertEquals(Integer.valueOf(2), result.getStatus(), "异步任务应完成（status=2）");
        assertNotNull(result.getDetail(), "detail 为空");
        assertFalse(isBlank(result.getDetail().getMediaId()), "detail.media_id 为空");
        assertNotNull(result.getDetail().getCreatedAt(), "detail.created_at 为空");

        FileDownload download = mediaService.download(result.getDetail().getMediaId(), app);
        assertArrayEquals(expected, download.getContent(), "url 上传后的素材与地址上的文件不一致");
        System.out.println("[live] upload_by_url done, mediaIdLength=" + result.getDetail().getMediaId().length()
                + ", contentLength=" + download.getContent().length);
    }

    /**
     * 查询不存在的 jobid（全局错误码 40088）：错误解码路径
     */
    @Test
    public void getUploadByUrlResultWithUnknownJobThrowsWeChatException() {
        JobIdRequest request = new JobIdRequest();
        request.setJobId("invalid-jobid-for-live-test");
        WeChatException error = weChatError(() -> mediaService.getUploadByUrlResult(request, app));

        assertFalse(isBlank(error.getCode()), "errcode 为空");
        System.out.println("[live] media/get_upload_by_url_result unknown job errcode=" + error.getCode());
    }

    /**
     * 上传附件（文档 95098）：朋友圈附件（attachment_type=1）的图片，media_id 可经 media/get 取回
     */
    @Test
    public void uploadAttachmentImageReturnsDownloadableMediaId() throws IOException {
        String mediaId = mediaService.uploadAttachment(writeTempFile("live-attachment.png", png()), MediaService.IMAGE, 1, app);

        assertFalse(isBlank(mediaId), "media_id 为空");
        assertArrayEquals(png(), mediaService.download(mediaId, app).getContent(), "附件取回内容与上传内容不一致");
        System.out.println("[live] media/upload_attachment ok, mediaIdLength=" + mediaId.length());
    }

    /**
     * 小于 5 字节的文件（文档 90253：所有文件大小必须大于 5 字节）：企业微信应拒绝
     */
    @Test
    public void uploadMaterialWithTooSmallFileThrowsWeChatException() {
        WeChatException error = weChatError(() -> mediaService.uploadMaterial(
                new QywxFile("abcd".getBytes(StandardCharsets.UTF_8), "too-small.txt", "text/plain"), MediaService.FILE, app));

        assertFalse(isBlank(error.getCode()), "errcode 为空");
        System.out.println("[live] media/upload too small file errcode=" + error.getCode() + " (" + error.getMessage() + ")");
    }

    /**
     * 与 5.x 一致：业务错误（errcode≠0）由 decoder 抛出 WeChatException，Feign 再把它包成 DecodeException，
     * 这里沿 cause 链取出 WeChatException 返回给断言。若错误码是 48002 / 48004 这类授权问题，说明接口被拦在权限层，
     * 验证不到目标错误路径，按跳过处理而不是当成通过
     */
    private static WeChatException weChatError(Executable call) {
        try {
            call.execute();
        } catch (Throwable throwable) {
            WeChatException weChatException = findWeChatException(throwable);
            assertNotNull(weChatException, "应抛出 WeChatException，实际为：" + throwable);
            if (PERMISSION_DENIED.contains(weChatException.getCode())) {
                Assumptions.abort("errcode=" + weChatException.getCode() + " 当前凭据无权调用该接口，验证不到目标错误路径，跳过");
            }
            return weChatException;
        }
        throw new AssertionError("应抛出 WeChatException，但调用成功返回");
    }

    private static WeChatException findWeChatException(Throwable throwable) {
        Throwable current = throwable;
        for (int i = 0; current != null && i < 10; i++) {
            if (current instanceof WeChatException) {
                return (WeChatException) current;
            }
            current = current.getCause();
        }
        return null;
    }

    /**
     * client 层上传的图片素材：首次调用时上传，之后复用（各用例无顺序依赖）
     */
    private WeChatMediaUploadResponse imageUpload() throws IOException {
        if (imageUpload == null) {
            imageUpload = mediaClient.uploadMaterial(new QywxFile(png(), "live.png", "image/png"), MediaService.IMAGE, app);
        }
        return imageUpload;
    }

    private String imageMediaId() throws IOException {
        WeChatMediaUploadResponse response = imageUpload();
        assertFalse(isBlank(response.getMediaId()), "media_id 为空");
        return response.getMediaId();
    }

    /**
     * service 层（QywxFile 重载）上传的文本素材：首次调用时上传，之后复用
     */
    private String fileMediaId() {
        if (fileMediaId == null) {
            fileMediaId = mediaService.uploadMaterial(new QywxFile(TEXT_BYTES, "live.txt", "text/plain"), MediaService.FILE, app);
            assertFalse(isBlank(fileMediaId), "media_id 为空");
        }
        return fileMediaId;
    }

    /**
     * uploadimg 得到的图片地址：首次调用时上传，之后复用；url 上传用例也用它
     */
    private String imageUrl() throws IOException {
        if (imageUrl == null) {
            imageUrl = mediaService.uploadImage(writeTempFile("live.png", png()), "image/png", app);
        }
        return imageUrl;
    }

    /**
     * 进程内生成一张 16x16 的渐变 PNG（不手工拼字节，保证是合法图片），多个用例共用同一份字节
     */
    private byte[] png() throws IOException {
        if (png == null) {
            BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < 16; y++) {
                for (int x = 0; x < 16; x++) {
                    image.setRGB(x, y, (x * 17) << 16 | (y * 13) << 8 | ((x + y) * 7));
                }
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(image, "png", out);
            png = out.toByteArray();
        }
        return png;
    }

    private File writeTempFile(String name, byte[] content) throws IOException {
        Path path = tempDir.resolve(name);
        Files.write(path, content);
        return path.toFile();
    }

    /**
     * 按 Range 取地址上的文件，用来算 url 上传要求的 md5；取不到时返回 null 让调用方退回上传前的字节
     */
    private static byte[] fetchBytes(String urlString) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(20000);
            connection.setRequestProperty("Range", "bytes=0-");
            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK && status != HttpURLConnection.HTTP_PARTIAL) {
                System.out.println("[live] fetch url status=" + status + "，md5 改用上传前的字节计算");
                return null;
            }
            try (InputStream in = connection.getInputStream()) {
                byte[] bytes = IOUtils.toByteArray(in);
                System.out.println("[live] fetch url status=" + status + ", length=" + bytes.length);
                return bytes;
            }
        } catch (IOException e) {
            System.out.println("[live] fetch url failed: " + e.getClass().getSimpleName() + "，md5 改用上传前的字节计算");
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String md5Hex(byte[] content) throws NoSuchAlgorithmException {
        byte[] digest = MessageDigest.getInstance("MD5").digest(content);
        StringBuilder builder = new StringBuilder(digest.length * 2);
        for (byte b : digest) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    private static String host(String url) {
        try {
            return new URL(url).getHost();
        } catch (IOException e) {
            return "n/a";
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
