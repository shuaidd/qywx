package com.github.shuaidd;

import com.github.shuaidd.client.PaymentClient;
import com.github.shuaidd.client.PaymentUploadClient;
import com.github.shuaidd.client.WeChatMediaClient;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.contract.ContractFeign;
import com.github.shuaidd.contract.RecordingClient;
import com.github.shuaidd.dto.addressbook.Department;
import com.github.shuaidd.dto.addressbook.WeChatUser;
import com.github.shuaidd.dto.tool.CallbackData;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.file.FileDownload;
import com.github.shuaidd.file.QywxFile;
import com.github.shuaidd.response.addressbook.AsyncJobResultResponse;
import com.github.shuaidd.service.AbstractBaseService;
import com.github.shuaidd.service.AddressBookService;
import com.github.shuaidd.service.AdvancedFeatureService;
import com.github.shuaidd.service.ApplicationService;
import com.github.shuaidd.service.AuthenticationService;
import com.github.shuaidd.service.CommonService;
import com.github.shuaidd.service.CustomContactService;
import com.github.shuaidd.service.DocumentService;
import com.github.shuaidd.service.EfficiencyToolService;
import com.github.shuaidd.service.ElectronicInvoiceService;
import com.github.shuaidd.service.EnterpriseEmailService;
import com.github.shuaidd.service.EnterpriseInterConnectionService;
import com.github.shuaidd.service.FrontEndToolService;
import com.github.shuaidd.service.GovernmentCommunicationService;
import com.github.shuaidd.service.HrService;
import com.github.shuaidd.service.MeetingService;
import com.github.shuaidd.service.MediaService;
import com.github.shuaidd.service.MessageService;
import com.github.shuaidd.service.OAService;
import com.github.shuaidd.service.PaymentService;
import com.github.shuaidd.service.SchoolService;
import com.github.shuaidd.service.SecurityService;
import com.github.shuaidd.service.ServiceProviderService;
import com.github.shuaidd.service.SmartSheetService;
import com.github.shuaidd.service.TokenService;
import com.github.shuaidd.service.WeChatCallCenterService;
import com.github.shuaidd.support.RawDownloader;
import com.github.shuaidd.support.WeChatContextHolder;
import com.github.shuaidd.support.WeChatManager;
import com.github.shuaidd.token.TokenManager;
import feign.FeignException;
import feign.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.io.TempDir;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 4 个带特殊依赖的 service 的装配守卫与行为：MediaService、PaymentService、AddressBookService、TokenService，
 * 以及把它们汇总起来的 WeChatManager 与静态入口 WeChatContextHolder
 * <p>
 * 这 4 个 service 不能像其余 23 个那样只靠 client 接口反射装配：TokenService 依赖 TokenManager，AddressBookService 依赖
 * MediaService 与 RawDownloader。这里用反射锁住构造器形态，再用 RecordingClient 记录 Feign 实际发出的请求，
 * 核对 multipart 报文、CSV 内容、任务提交的 JSON、下载解密与 RawDownloader 的行为（5.x 快照即如此）。
 *
 * @author ddshuai
 * date 2026-11-28
 **/
public class SpecialServiceWiringTest {

    private static final String BASE = "http://contract.invalid/cgi-bin/";

    private static final String GET_TOKEN_URL = BASE + "gettoken?corpid=corp-contract&corpsecret=secret-contract";

    private static final String TOKEN = "tok-1";

    private static final String TOKEN_QUERY = "app=" + ContractFeign.APP + "&access_token=" + TOKEN;

    private static final Pattern MULTIPART_CONTENT_TYPE =
            Pattern.compile("^multipart/form-data; charset=UTF-8; boundary=----qywx[0-9a-f]{32}$");

    private static final Pattern CSV_FILE_NAME =
            Pattern.compile("filename=\"((?:asyncBatchUpdateUser|fullCoverUser)-\\d{14}\\.csv)\"");

    /**
     * 与 AddressBookService 的用户 CSV 标题行逐字一致（5.x 原样：有「性别」列，但数据行不写 gender）
     */
    private static final String USER_TITLE =
            "姓名,帐号,手机号,邮箱,所在部门,职位,性别,是否部门内领导,排序,别名,地址,座机,禁用,禁用项说明：(0-启用;1-禁用)\n";

    private static final String DEPARTMENT_TITLE = "部门名称,部门ID,父部门ID,排序\n";

    private static final Request.Options OPTIONS =
            new Request.Options(1000, TimeUnit.MILLISECONDS, 1000, TimeUnit.MILLISECONDS, true);

    /**
     * 4 个特殊 service 与各自构造器里 QywxContext 之后的参数（顺序即构造器参数顺序）
     */
    private static final Map<Class<?>, Class<?>[]> SPECIAL_SERVICES = new LinkedHashMap<>();

    static {
        SPECIAL_SERVICES.put(MediaService.class, new Class<?>[]{WeChatMediaClient.class});
        SPECIAL_SERVICES.put(PaymentService.class, new Class<?>[]{PaymentClient.class, PaymentUploadClient.class});
        SPECIAL_SERVICES.put(AddressBookService.class, new Class<?>[]{MediaService.class, RawDownloader.class});
        SPECIAL_SERVICES.put(TokenService.class, new Class<?>[]{TokenManager.class});
    }

    /**
     * 4 个特殊 service 恰有一个 public 构造器：首参 QywxContext，其余参数恰为各自的依赖，父类 AbstractBaseService；
     * 并按各自的装配方式实际实例化一遍
     */
    @Test
    public void specialServicesTakeContextFirstAndExtendBaseClass() {
        QywxClient client = QywxClient.create(ContractFeign.config(false), new RecordingClient());
        RecordingClient recording = new RecordingClient();
        List<Executable> checks = new ArrayList<>();
        for (Map.Entry<Class<?>, Class<?>[]> entry : SPECIAL_SERVICES.entrySet()) {
            checks.add(() -> verifyService(client, recording, entry.getKey(), entry.getValue()));
        }
        assertAll(checks);
    }

    /**
     * MediaService：QywxFile 与本地 File 两种上传都走 multipart（part 名 media，Content-Type 取调用方给的），
     * 下载经 WeChatMediaDecoder 落到 FileDownload；空参守卫不发请求
     */
    @Test
    public void mediaServiceUploadsMultipartAndDownloads(@TempDir Path tempDir) throws IOException {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);
        MediaService service = service(client, recording, MediaService.class);
        recording.respond(200, ContractFeign.tokenBody(TOKEN));
        recording.respond(200, mediaBody("media-1"));
        recording.respond(200, mediaBody("media-2"));
        recording.respond(200, ContractFeign.DOWNLOAD_CONTENT, ContractFeign.downloadHeaders());

        assertEquals("media-1", service.uploadMaterial(
                new QywxFile(bytes("PNG-BYTES"), "a.png", "image/png"), MediaService.IMAGE, ContractFeign.APP));

        assertEquals(GET_TOKEN_URL, recording.request(0).url());
        Request image = recording.request(1);
        assertEquals("POST", image.method());
        assertEquals(BASE + "media/upload?type=image&" + TOKEN_QUERY, image.url());
        assertTrue(MULTIPART_CONTENT_TYPE.matcher(header(image, "Content-Type")).matches(), header(image, "Content-Type"));
        String imageBody = new String(image.body(), StandardCharsets.UTF_8);
        assertTrue(imageBody.contains("name=\"media\"; filename=\"a.png\""), imageBody);
        assertTrue(imageBody.contains("Content-Type: image/png"), imageBody);
        assertTrue(imageBody.contains("PNG-BYTES"), imageBody);

        File local = tempDir.resolve("b.png").toFile();
        Files.write(local.toPath(), bytes("FILE-BYTES"));
        assertEquals("media-2", service.uploadMaterial(local, "image/png", MediaService.FILE, ContractFeign.APP));
        Request file = recording.request(2);
        assertEquals(BASE + "media/upload?type=file&" + TOKEN_QUERY, file.url());
        String fileBody = new String(file.body(), StandardCharsets.UTF_8);
        assertTrue(fileBody.contains("filename=\"b.png\""), fileBody);
        assertTrue(fileBody.contains("FILE-BYTES"), fileBody);

        FileDownload download = service.download("media-1", ContractFeign.APP);
        assertArrayEquals(ContractFeign.DOWNLOAD_CONTENT, download.getContent());
        assertEquals("contract.bin", download.getFileName());
        assertEquals("application/octet-stream", download.getContentType());
        Request getRequest = recording.request(3);
        assertEquals("GET", getRequest.method());
        assertEquals(BASE + "media/get?media_id=media-1&" + TOKEN_QUERY, getRequest.url());

        assertNull(service.uploadMaterial((File) null, "image/png", MediaService.IMAGE, ContractFeign.APP));
        assertNull(service.uploadMaterial(local, null, MediaService.IMAGE, ContractFeign.APP));
        assertNull(service.uploadMaterial((QywxFile) null, MediaService.IMAGE, ContractFeign.APP));
        assertNull(service.download("", ContractFeign.APP));
        assertNull(service.download(null, ContractFeign.APP));
        assertEquals(4, recording.requestCount());
    }

    /**
     * PaymentService：图片校验顺序与提示语同 5.x（空、大小、扩展名，扩展名比较前先转小写），校验不过不发请求；
     * 通过后经 paymentUploadClient 以 part 名 image 上传
     */
    @Test
    public void paymentServiceValidatesAndUploadsImage() {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);
        PaymentService service = service(client, recording, PaymentService.class);

        assertUploadRejected(service, null, "上传图片不能为空，请检查！！！");
        assertUploadRejected(service, new QywxFile(new byte[0], "a.png", "image/png"), "上传图片不能为空，请检查！！！");
        assertUploadRejected(service, new QywxFile(bytes("1234"), "a.png", "image/png"), "上传图片大小必须在5字节到2MB之间，请检查！！！");
        assertUploadRejected(service, new QywxFile(new byte[2 * 1024 * 1024 + 1], "a.png", "image/png"),
                "上传图片大小必须在5字节到2MB之间，请检查！！！");
        assertUploadRejected(service, new QywxFile(bytes("12345"), "a.txt", "image/png"),
                "上传图片仅支持JPG/JPEG、PNG、BMP格式，请检查！！！");
        assertEquals(0, recording.requestCount());

        recording.respond(200, ContractFeign.tokenBody(TOKEN));
        recording.respond(200, "{\"errcode\":0,\"errmsg\":\"ok\",\"open_wx_pay_media_id\":\"img-1\"}");
        assertEquals("img-1", service.uploadImage(
                new QywxFile(bytes("12345"), "a.PNG", "image/png"), ContractFeign.APP).getOpenWxPayMediaId());

        assertEquals(GET_TOKEN_URL, recording.request(0).url());
        Request request = recording.request(1);
        assertEquals("POST", request.method());
        assertEquals(BASE + "miniapppay/upload_image?" + TOKEN_QUERY, request.url());
        assertTrue(MULTIPART_CONTENT_TYPE.matcher(header(request, "Content-Type")).matches(), header(request, "Content-Type"));
        String body = new String(request.body(), StandardCharsets.UTF_8);
        assertTrue(body.contains("name=\"image\"; filename=\"a.PNG\""), body);
        assertTrue(body.contains("Content-Type: image/png"), body);
    }

    /**
     * AddressBookService 增量/全量更新成员：先把用户写成 CSV（UTF-8，part 内容为标题行 + 数据行）上传为临时素材，
     * 再以 media_id 提交异步任务；带 callback 时 JSON 含回调三要素，三参重载不带 callback
     */
    @Test
    public void addressBookServiceUploadsUserCsvThenSubmitsJob() {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);
        AddressBookService service = service(client, recording, AddressBookService.class);
        recording.respond(200, ContractFeign.tokenBody(TOKEN));
        recording.respond(200, mediaBody("csv-1"));
        recording.respond(200, jobBody("job-1"));
        recording.respond(200, mediaBody("csv-2"));
        recording.respond(200, jobBody("job-2"));
        recording.respond(200, mediaBody("csv-3"));
        recording.respond(200, jobBody("job-3"));
        String userLine = "张三,zhangsan,13800000000,zhangsan@example.com,1;2,工程师,0;1,10;20,zs,北京,010-1,1,\n";

        assertEquals("job-1", service.asyncBatchUpdateUser(
                Collections.singletonList(user()), true, callback(), ContractFeign.APP));

        assertEquals(GET_TOKEN_URL, recording.request(0).url());
        assertCsvUpload(recording.request(1), "asyncBatchUpdateUser-", USER_TITLE + userLine);
        Request sync = recording.request(2);
        assertEquals("POST", sync.method());
        assertEquals(BASE + "batch/syncuser?" + TOKEN_QUERY, sync.url());
        String syncBody = new String(sync.body(), StandardCharsets.UTF_8);
        assertTrue(syncBody.contains("\"media_id\" : \"csv-1\""), syncBody);
        assertTrue(syncBody.contains("\"to_invite\" : true"), syncBody);
        assertTrue(syncBody.contains("\"encodingaeskey\" : \"cb-aes\""), syncBody);
        assertFalse(syncBody.contains("null"), syncBody);

        assertEquals("job-2", service.fullCoverUser(Collections.singletonList(user()), false, ContractFeign.APP));
        assertCsvUpload(recording.request(3), "fullCoverUser-", USER_TITLE + userLine);
        Request replace = recording.request(4);
        assertEquals("POST", replace.method());
        assertEquals(BASE + "batch/replaceuser?" + TOKEN_QUERY, replace.url());
        String replaceBody = new String(replace.body(), StandardCharsets.UTF_8);
        assertTrue(replaceBody.contains("\"to_invite\" : false"), replaceBody);
        assertFalse(replaceBody.contains("callback"), replaceBody);

        assertNotNull(service.asyncBatchUpdateUser(new ArrayList<WeChatUser>(), true, ContractFeign.APP));
        assertCsvUpload(recording.request(5), "asyncBatchUpdateUser-", "");
        assertEquals(7, recording.requestCount());
    }

    /**
     * AddressBookService 全量覆盖部门：CSV 为「部门名称,部门ID,父部门ID,排序」，文件名沿用 5.x 的 fullCoverUser- 前缀
     */
    @Test
    public void addressBookServiceUploadsDepartmentCsv() {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);
        AddressBookService service = service(client, recording, AddressBookService.class);
        Department department = new Department();
        department.setName("研发部");
        department.setId(2);
        department.setParentId(1);
        department.setOrder(3);
        recording.respond(200, ContractFeign.tokenBody(TOKEN));
        recording.respond(200, mediaBody("csv-d"));
        recording.respond(200, jobBody("job-d"));

        assertEquals("job-d", service.fullCoverDepartment(
                Collections.singletonList(department), callback(), ContractFeign.APP));

        assertCsvUpload(recording.request(1), "fullCoverUser-", DEPARTMENT_TITLE + "研发部,2,1,3\n");
        Request request = recording.request(2);
        assertEquals("POST", request.method());
        assertEquals(BASE + "batch/replaceparty?" + TOKEN_QUERY, request.url());
        String body = new String(request.body(), StandardCharsets.UTF_8);
        assertTrue(body.contains("\"media_id\" : \"csv-d\""), body);
        assertTrue(body.contains("\"encodingaeskey\" : \"cb-aes\""), body);
        assertFalse(body.contains("to_invite"), body);
    }

    /**
     * AddressBookService 查询异步任务结果：jobId 为空直接返回 null，不发请求
     */
    @Test
    public void addressBookServiceQueriesJobResultOnlyWithJobId() {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);
        AddressBookService service = service(client, recording, AddressBookService.class);

        assertNull(service.jobResult("", ContractFeign.APP));
        assertNull(service.jobResult(null, ContractFeign.APP));
        assertEquals(0, recording.requestCount());

        recording.respond(200, ContractFeign.tokenBody(TOKEN));
        recording.respond(200, "{\"errcode\":0,\"errmsg\":\"ok\",\"status\":1,\"type\":\"syncuser\",\"total\":3,\"percentage\":100}");
        AsyncJobResultResponse result = service.jobResult("job-1", ContractFeign.APP);

        assertEquals(Integer.valueOf(1), result.getStatus());
        assertEquals(Integer.valueOf(100), result.getPercentage());
        assertEquals(GET_TOKEN_URL, recording.request(0).url());
        Request request = recording.request(1);
        assertEquals("GET", request.method());
        assertEquals(BASE + "batch/getresult?jobid=job-1&" + TOKEN_QUERY, request.url());
    }

    /**
     * AddressBookService 下载并解密导出结果：RawDownloader 原样 GET 该地址（无请求头、无请求体），
     * 密钥为 aesKey 补一个 = 后的 Base64 解码值，IV 取密钥前 16 字节，AES/CBC/NoPadding；没有响应体返回 null，非 2xx 抛出
     */
    @Test
    public void addressBookServiceDownloadsAndDecryptsExportData() throws Exception {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);
        AddressBookService service = service(client, recording, AddressBookService.class);
        byte[] key = new byte[32];
        for (int i = 0; i < key.length; i++) {
            key[i] = (byte) (i * 7 + 3);
        }
        String encoded = Base64.getEncoder().encodeToString(key);
        assertTrue(encoded.endsWith("="), encoded);
        //5.x 约定调用方传入的 aes_key 不带末尾的 =，service 内再补回一个
        String aesKey = encoded.substring(0, encoded.length() - 1);
        String plain = padToBlock("{\"total\":3,\"percentage\":100}");
        String url = "http://export.invalid/async-result.json";
        recording.respond(200, encrypt(key, plain), null);

        assertEquals(plain, service.getDecryptExportData(aesKey, url));

        assertEquals("GET", recording.request(0).method());
        assertEquals(url, recording.request(0).url());
        assertNull(recording.request(0).body());

        recording.respond(200, (String) null);
        assertNull(service.getDecryptExportData(aesKey, url));
        recording.respond(404, "not found");
        assertThrows(FeignException.class, () -> service.getDecryptExportData(aesKey, url));
        assertEquals(3, recording.requestCount());
    }

    /**
     * TokenService 只是把取号与清除委托给 TokenManager：缓存命中不取号，清除后重新取号
     */
    @Test
    public void tokenServiceDelegatesToTokenManager() {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);
        TokenService service = service(client, recording, TokenService.class);
        recording.respond(200, ContractFeign.tokenBody(TOKEN));

        assertEquals(TOKEN, service.getAccessToken(ContractFeign.APP));
        assertEquals(TOKEN, service.getAccessToken(ContractFeign.APP));
        assertEquals(1, recording.requestCount());
        assertEquals(GET_TOKEN_URL, recording.request(0).url());

        service.clearAccessToken(ContractFeign.APP);
        recording.respond(200, ContractFeign.tokenBody("tok-refreshed"));
        assertEquals("tok-refreshed", service.getAccessToken(ContractFeign.APP));
        assertEquals(2, recording.requestCount());
    }

    /**
     * WeChatManager 按 26 参构造器的顺序原样暴露 25 个 service 与配置（properties() 返回 QywxConfig），
     * WeChatContextHolder 的静态入口存取同一个实例，清除后为 null
     */
    @Test
    public void weChatManagerExposesEveryService() throws Exception {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);
        AddressBookService addressBook = service(client, recording, AddressBookService.class);
        TokenService token = service(client, recording, TokenService.class);
        AuthenticationService authentication = service(client, recording, AuthenticationService.class);
        ApplicationService application = service(client, recording, ApplicationService.class);
        MediaService media = service(client, recording, MediaService.class);
        MessageService message = service(client, recording, MessageService.class);
        OAService oa = service(client, recording, OAService.class);
        CustomContactService customContact = service(client, recording, CustomContactService.class);
        EfficiencyToolService efficiencyTool = service(client, recording, EfficiencyToolService.class);
        WeChatCallCenterService callCenter = service(client, recording, WeChatCallCenterService.class);
        CommonService common = service(client, recording, CommonService.class);
        ServiceProviderService serviceProvider = service(client, recording, ServiceProviderService.class);
        EnterpriseEmailService enterpriseEmail = service(client, recording, EnterpriseEmailService.class);
        ElectronicInvoiceService electronicInvoice = service(client, recording, ElectronicInvoiceService.class);
        EnterpriseInterConnectionService interConnection = service(client, recording, EnterpriseInterConnectionService.class);
        MeetingService meeting = service(client, recording, MeetingService.class);
        FrontEndToolService frontEndTool = service(client, recording, FrontEndToolService.class);
        DocumentService document = service(client, recording, DocumentService.class);
        SmartSheetService smartSheet = service(client, recording, SmartSheetService.class);
        PaymentService payment = service(client, recording, PaymentService.class);
        SchoolService school = service(client, recording, SchoolService.class);
        AdvancedFeatureService advancedFeature = service(client, recording, AdvancedFeatureService.class);
        GovernmentCommunicationService government = service(client, recording, GovernmentCommunicationService.class);
        HrService hr = service(client, recording, HrService.class);
        SecurityService security = service(client, recording, SecurityService.class);

        WeChatManager manager = new WeChatManager(addressBook, token, client.config(), authentication, application,
                media, message, oa, customContact, efficiencyTool, callCenter, common, serviceProvider, enterpriseEmail,
                electronicInvoice, interConnection, meeting, frontEndTool, document, smartSheet, payment, school,
                advancedFeature, government, hr, security);

        assertAll(
                () -> assertSame(addressBook, manager.addressBookService()),
                () -> assertSame(token, manager.tokenService()),
                () -> assertSame(client.config(), manager.properties()),
                () -> assertSame(authentication, manager.authenticationService()),
                () -> assertSame(media, manager.mediaService()),
                () -> assertSame(application, manager.applicationService()),
                () -> assertSame(message, manager.messageService()),
                () -> assertSame(oa, manager.oaService()),
                () -> assertSame(customContact, manager.customContactService()),
                () -> assertSame(efficiencyTool, manager.efficiencyTool()),
                () -> assertSame(callCenter, manager.callCenterService()),
                () -> assertSame(serviceProvider, manager.serviceProviderService()),
                () -> assertSame(enterpriseEmail, manager.enterpriseEmailService()),
                () -> assertSame(electronicInvoice, manager.electronicInvoiceService()),
                () -> assertSame(interConnection, manager.enterpriseInterConnectionService()),
                () -> assertSame(common, manager.commonService()),
                () -> assertSame(meeting, manager.meetingService()),
                () -> assertSame(frontEndTool, manager.frontEndToolService()),
                () -> assertSame(document, manager.documentService()),
                () -> assertSame(smartSheet, manager.smartSheetService()),
                () -> assertSame(payment, manager.paymentService()),
                () -> assertSame(school, manager.schoolService()),
                () -> assertSame(advancedFeature, manager.advancedFeatureService()),
                () -> assertSame(government, manager.governmentCommunicationService()),
                () -> assertSame(hr, manager.hrService()),
                () -> assertSame(security, manager.securityService()),
                () -> assertEquals(QywxConfig.class, WeChatManager.class.getMethod("properties").getReturnType()));

        try {
            WeChatContextHolder.setWeChatManager(manager);
            assertSame(manager, WeChatContextHolder.getWeChatManager());
        } finally {
            WeChatContextHolder.setWeChatManager(null);
        }
        assertNull(WeChatContextHolder.getWeChatManager());
    }

    /**
     * RawDownloader：原样 GET，2xx 返回响应体（没有响应体为 null），非 2xx 抛 FeignException，构造器拒绝 null
     */
    @Test
    public void rawDownloaderReturnsBodyOrNullAndRejectsNon2xx() throws IOException {
        RecordingClient recording = new RecordingClient();
        RawDownloader downloader = new RawDownloader(recording, OPTIONS);
        recording.respond(200, bytes("RAW-CONTENT"), null);
        recording.respond(200, (String) null);
        recording.respond(500, "boom");
        String url = "http://export.invalid/raw.bin";

        assertArrayEquals(bytes("RAW-CONTENT"), downloader.download(url));
        assertNull(downloader.download(url));
        assertThrows(FeignException.class, () -> downloader.download(url));

        assertEquals("GET", recording.request(0).method());
        assertEquals(url, recording.request(0).url());
        assertNull(recording.request(0).body());
        assertEquals(3, recording.requestCount());
        assertThrows(NullPointerException.class, () -> new RawDownloader(null, OPTIONS));
        assertThrows(NullPointerException.class, () -> new RawDownloader(recording, null));
    }

    /**
     * 按 service 各自的装配方式实例化：TokenService 用 client 的 TokenManager，MediaService 用上下文 + media client，
     * AddressBookService 用上下文 + MediaService + RawDownloader（RecordingClient 当 HTTP 实现），其余按构造器参数逐个 proxy
     */
    private static Object newService(QywxClient client, RecordingClient recording, Class<?> type) {
        if (type == TokenService.class) {
            return new TokenService(client.context(), client.tokenManager());
        }
        if (type == MediaService.class) {
            return new MediaService(client.context(), client.proxy(WeChatMediaClient.class));
        }
        if (type == AddressBookService.class) {
            MediaService mediaService = new MediaService(client.context(), client.proxy(WeChatMediaClient.class));
            return new AddressBookService(client.context(), mediaService, new RawDownloader(recording, OPTIONS));
        }
        try {
            Constructor<?>[] constructors = type.getConstructors();
            assertEquals(1, constructors.length, type.getSimpleName() + " 的 public 构造器数量");
            Constructor<?> constructor = constructors[0];
            Class<?>[] parameters = constructor.getParameterTypes();
            Object[] arguments = new Object[parameters.length];
            arguments[0] = client.context();
            for (int i = 1; i < parameters.length; i++) {
                arguments[i] = client.proxy(parameters[i]);
            }
            return constructor.newInstance(arguments);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(type.getName() + " 实例化失败", e);
        }
    }

    private static <T> T service(QywxClient client, RecordingClient recording, Class<T> type) {
        return type.cast(newService(client, recording, type));
    }

    /**
     * 构造器形态守卫：恰 1 个 public 构造器、首参 QywxContext、其余参数恰为 expected、父类 AbstractBaseService，并能实例化
     */
    private static void verifyService(QywxClient client, RecordingClient recording, Class<?> type, Class<?>[] expected) {
        String name = type.getSimpleName();
        Constructor<?>[] constructors = type.getConstructors();
        assertEquals(1, constructors.length, name + " 的 public 构造器数量");
        Class<?>[] parameters = constructors[0].getParameterTypes();
        assertTrue(parameters.length >= 1, name + " 构造器缺少 QywxContext");
        assertEquals(QywxContext.class, parameters[0], name + " 构造器首参");
        assertArrayEquals(expected, Arrays.copyOfRange(parameters, 1, parameters.length), name + " 构造器的依赖参数");
        assertEquals(AbstractBaseService.class, type.getSuperclass(), name + " 的父类");
        assertNotNull(newService(client, recording, type), name + " 实例化");
    }

    private static void assertUploadRejected(PaymentService service, QywxFile image, String message) {
        ParamCheckException error = assertThrows(ParamCheckException.class, () -> service.uploadImage(image, ContractFeign.APP));
        assertEquals(message, error.getMessage());
    }

    /**
     * 取请求头首个值，名称忽略大小写
     */
    private static String header(Request request, String name) {
        for (Map.Entry<String, Collection<String>> entry : request.headers().entrySet()) {
            if (entry.getKey() != null && entry.getKey().equalsIgnoreCase(name) && !entry.getValue().isEmpty()) {
                return entry.getValue().iterator().next();
            }
        }
        return null;
    }

    /**
     * 取 multipart 报文里 part 的内容：第一个空行之后、结束边界之前
     */
    private static String partContent(String multipart) {
        int start = multipart.indexOf("\r\n\r\n") + 4;
        int end = multipart.lastIndexOf("\r\n--");
        return multipart.substring(start, end);
    }

    /**
     * 断言一次 CSV 上传：POST media/upload?type=file、multipart 头、文件名前缀、part 的 Content-Type 为 text/plain、内容逐字相等
     */
    private static void assertCsvUpload(Request request, String prefix, String expectedContent) {
        assertEquals("POST", request.method());
        assertEquals(BASE + "media/upload?type=file&" + TOKEN_QUERY, request.url());
        assertTrue(MULTIPART_CONTENT_TYPE.matcher(header(request, "Content-Type")).matches(),
                "上传 CSV 的 Content-Type: " + header(request, "Content-Type"));
        String body = new String(request.body(), StandardCharsets.UTF_8);
        Matcher matcher = CSV_FILE_NAME.matcher(body);
        assertTrue(matcher.find(), "上传 CSV 的文件名: " + body.substring(0, Math.min(200, body.length())));
        String fileName = matcher.group(1);
        assertTrue(fileName.startsWith(prefix), "CSV 文件名前缀: " + fileName);
        assertTrue(body.contains("Content-Type: text/plain"), "CSV part 的 Content-Type");
        assertEquals(expectedContent, partContent(body));
    }

    private static String mediaBody(String mediaId) {
        return "{\"errcode\":0,\"errmsg\":\"ok\",\"media_id\":\"" + mediaId + "\"}";
    }

    private static String jobBody(String jobId) {
        return "{\"errcode\":0,\"errmsg\":\"ok\",\"jobid\":\"" + jobId + "\"}";
    }

    private static byte[] bytes(String text) {
        return text.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 一个字段齐全的用户：gender 只进对象不进 CSV（5.x 原样）
     */
    private static WeChatUser user() {
        WeChatUser user = new WeChatUser();
        user.setName("张三");
        user.setUserId("zhangsan");
        user.setMobile("13800000000");
        user.setEmail("zhangsan@example.com");
        user.setDepartment(Arrays.asList(1, 2));
        user.setPosition("工程师");
        user.setGender("1");
        user.setIsLeaderInDept(Arrays.asList(0, 1));
        user.setOrder(Arrays.asList(10, 20));
        user.setAlias("zs");
        user.setAddress("北京");
        user.setTelephone("010-1");
        user.setEnable(1);
        return user;
    }

    private static CallbackData callback() {
        CallbackData callback = new CallbackData();
        callback.setUrl("http://cb.invalid");
        callback.setToken("cb-token");
        callback.setEncodingAesKey("cb-aes");
        return callback;
    }

    /**
     * 用空格补到 16 字节的整倍数（AES/CBC/NoPadding 不补位）
     */
    private static String padToBlock(String text) {
        StringBuilder builder = new StringBuilder(text);
        while (builder.length() % 16 != 0) {
            builder.append(' ');
        }
        return builder.toString();
    }

    /**
     * 与 AddressBookService.getDecryptExportData 相同的密钥/IV 约定加密，供测试预置响应体
     */
    private static byte[] encrypt(byte[] key, String plain) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(Arrays.copyOfRange(key, 0, 16)));
        return cipher.doFinal(bytes(plain));
    }
}
