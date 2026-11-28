package com.github.shuaidd;

import com.github.shuaidd.client.AddressBookClient;
import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.dto.addressbook.ContactRule;
import com.github.shuaidd.dto.addressbook.Department;
import com.github.shuaidd.dto.addressbook.DeptUser;
import com.github.shuaidd.dto.addressbook.Tag;
import com.github.shuaidd.dto.addressbook.WeChatUser;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.resquest.CursorPageRequest;
import com.github.shuaidd.resquest.addressbook.GetUserIdByEmailRequest;
import com.github.shuaidd.resquest.addressbook.GetUserIdByMobileRequest;
import com.github.shuaidd.response.addressbook.UserIdResponse;
import com.github.shuaidd.response.addressbook.WeChatUserResponse;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import com.github.shuaidd.service.AddressBookService;
import com.github.shuaidd.token.InMemoryTokenStore;
import com.github.shuaidd.token.TokenEntry;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * 描述 通讯录接口迁移后的真实企业微信联调（只读）：用真实 corpId + 通讯录 secret 把 6.0.0 的 qywx-core 跑一遍
 * <p>
 * 默认跳过；只有显式设置 {@code QYWX_LIVE=true} 时才执行，避免普通构建依赖网络与凭据。读取环境变量：
 * {@code QYWX_CORP_ID}（企业号）、{@code QYWX_SECRET}（通讯录 secret）、{@code QYWX_APP}（应用名，默认 address-book）。
 * <p>
 * 只调用只读接口：部门/成员/标签的查询、userid 与 openid 互转、活跃成员数、加入企业二维码、隐藏规则列表，
 * 以及两条必然失败的错误解码路径（非法 oauth code、不存在的异步任务）。创建、更新、删除、导出类接口不在真实企业上执行，
 * 它们由契约回放 {@code ClientContractReplayTest} 覆盖。所有输出只打印数量与错误码，不打印令牌与成员个人信息。
 * <p>
 * 凭据权限范围导致的 {@code 48002 api forbidden} / {@code 48009}（通讯录同步新增 IP 禁读）按「跳过」处理并打出错误码：
 * 这是企业微信的授权策略（例如 list_id、加入企业二维码、通讯录规则仅限通讯录同步 secret），不是 SDK 缺陷；其余异常照常让用例失败。
 * <p>
 * 两套凭据能力互补（2026-11 实测）：通讯录同步 secret 可调 list_id、加入企业二维码、活跃成员数与通讯录规则，
 * 但 2022-08-15 后新增 IP 读部门/成员详情会被 48009 拒绝；自建应用正相反（前者 48002）。各跑一遍即可覆盖全部只读接口。
 * <p>
 * 运行（仓库根目录，PowerShell）：
 * <pre>
 * $env:QYWX_LIVE='true'; $env:QYWX_CORP_ID='企业号'; $env:QYWX_SECRET='通讯录secret'
 * mvn -B -pl qywx-core -am test "-Dtest=AddressBookLiveTest" "-Dsurefire.failIfNoSpecifiedTests=false"
 * </pre>
 *
 * @author ddshuai
 * date 2026-11-28
 **/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnabledIfEnvironmentVariable(named = "QYWX_LIVE", matches = "true")
@ExtendWith(AddressBookLiveTest.PermissionDeniedHandler.class)
public class AddressBookLiveTest {

    private static final String DEFAULT_APP = "address-book";

    /** 凭据权限范围不足的错误码：48002 api forbidden、48009 通讯录同步新增 IP 禁读 */
    private static final List<String> PERMISSION_DENIED = Arrays.asList("48002", "48009");

    private String corpId;

    private String secret;

    private String app;

    private QywxClient client;

    private AddressBookService addressBookService;

    private List<Department> departments;

    private Integer rootDeptId;

    private String sampleUserId;

    private WeChatUserResponse sampleUser;

    private List<Tag> tags;

    private List<WeChatUser> departmentUsers;

    @BeforeAll
    void setUp() {
        corpId = System.getenv("QYWX_CORP_ID");
        secret = System.getenv("QYWX_SECRET");
        app = System.getenv("QYWX_APP");
        if (isBlank(app)) {
            app = DEFAULT_APP;
        }
        assumeTrue(!isBlank(corpId) && !isBlank(secret), "未设置 QYWX_CORP_ID / QYWX_SECRET，跳过真实企业联调");

        ApplicationProperties application = new ApplicationProperties();
        application.setApplicationName(app);
        application.setSecret(secret);
        application.setCorpId(corpId);

        client = QywxClient.create(QywxConfig.builder()
                .corpId(corpId)
                .application(application)
                .build());
        addressBookService = client.weChatManager().addressBookService();
        System.out.println("[live] app=" + app + " baseUrl=" + client.config().baseUrl());
        probeTrustedIp();
    }

    /**
     * 48002 / 48009 是凭据权限范围所致而不是 SDK 缺陷：这类错误按「跳过」处理并打出错误码，其余异常原样抛出让用例失败。
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
     * 直接走 client 层取 access_token：迁移后 /gettoken 的路径、参数名与响应映射必须正确
     */
    @Test
    public void getAccessTokenReturnsNonBlankToken() {
        AccessTokenResponse response = client.weChatClient().getAccessToken(corpId, secret);

        assertNotNull(response, "gettoken 响应为空");
        assertFalse(isBlank(response.getAccessToken()), "access_token 为空");
        assertNotNull(response.getExpiresIn(), "expires_in 为空");
        assertTrue(response.getExpiresIn() > 0, "expires_in 应大于 0");
        System.out.println("[live] gettoken ok, tokenLength=" + response.getAccessToken().length()
                + ", expiresIn=" + response.getExpiresIn());
    }

    /**
     * TokenManager 的进程内缓存：同一应用两次取号必须命中缓存返回同一令牌
     */
    @Test
    public void accessTokenIsCachedWithinClient() {
        String first = client.tokenService().getAccessToken(app);
        String second = client.tokenService().getAccessToken(app);

        assertFalse(isBlank(first), "首次取号为空");
        assertEquals(first, second, "第二次取号应命中缓存");
        assertEquals(first, client.tokenManager().getAccessToken(app), "TokenManager 与 TokenService 应共享缓存");
        System.out.println("[live] token cache ok, tokenLength=" + first.length());
    }

    @Test
    public void departmentListReturnsWholeTree() {
        List<Department> list = departments();

        for (Department department : list) {
            assertNotNull(department.getId(), "部门 id 为空");
            assertNotNull(department.getName(), "部门名称为空");
        }
        System.out.println("[live] departmentList(null) size=" + list.size());
    }

    @Test
    public void departmentListByIdReturnsSubtree() {
        Integer rootId = rootDeptId();
        List<Department> subtree = addressBookService.departmentList(rootId, app);

        assertNotNull(subtree, "子部门列表为空");
        assertFalse(subtree.isEmpty(), "子部门列表不应为空");
        assertTrue(subtree.stream().anyMatch(d -> rootId.equals(d.getId())), "子树应包含请求的部门本身");
        assertTrue(subtree.size() <= departments().size(), "子树不应大于全量");
        System.out.println("[live] departmentList(root) size=" + subtree.size());
    }

    @Test
    public void simpleDepartmentListMatchesDepartmentList() {
        List<Department> simple = addressBookService.simpleDepartmentList(null, app);

        assertNotNull(simple, "简单部门列表为空");
        assertEquals(departments().size(), simple.size(), "simplelist 与 list 的全量部门数应一致");
        // simplelist（department_id）只返回 id/parentid/order，不返回名称；用全量 list 按 id 交叉核对
        Map<Integer, Department> full = new HashMap<>();
        for (Department department : departments()) {
            full.put(department.getId(), department);
        }
        for (Department department : simple) {
            assertNotNull(department.getId(), "部门 id 为空");
            assertNotNull(department.getParentId(), "部门 parentid 为空");
            assertTrue(full.containsKey(department.getId()), "simplelist 返回的部门 " + department.getId() + " 不在全量列表中");
        }
        System.out.println("[live] simpleDepartmentList(null) size=" + simple.size());
    }

    @Test
    public void getDepartmentDetailMatchesRequestedId() {
        Integer rootId = rootDeptId();
        Department detail = addressBookService.getDepartmentDetail(rootId, app);

        assertNotNull(detail, "部门详情为空");
        assertEquals(rootId, detail.getId(), "部门详情 id 不一致");
        assertFalse(isBlank(detail.getName()), "部门详情名称为空");
        System.out.println("[live] getDepartmentDetail ok, hasParentId=" + (detail.getParentId() != null));
    }

    @Test
    public void getDepartmentUserSimpleListHasUserIds() {
        List<WeChatUser> users = addressBookService.getDepartmentUser(rootDeptId(), false, app);

        assertNotNull(users, "部门成员（简易）为空");
        for (WeChatUser user : users) {
            assertFalse(isBlank(user.getUserId()), "成员 userId 为空");
        }
        System.out.println("[live] getDepartmentUser(root, fetchChild=false) size=" + users.size());
    }

    @Test
    public void getDepartmentUserDetailListHasUserIds() {
        List<WeChatUser> users = addressBookService.getDepartmentUserDetail(rootDeptId(), false, app);

        assertNotNull(users, "部门成员（详情）为空");
        for (WeChatUser user : users) {
            assertFalse(isBlank(user.getUserId()), "成员 userId 为空");
            assertNotNull(user.getName(), "成员 name 为空");
        }
        System.out.println("[live] getDepartmentUserDetail(root, fetchChild=false) size=" + users.size());
    }

    @Test
    public void getDepartmentUserFetchChildIsNotSmaller() {
        List<WeChatUser> direct = addressBookService.getDepartmentUser(rootDeptId(), false, app);
        List<WeChatUser> withChild = addressBookService.getDepartmentUser(rootDeptId(), true, app);

        assertNotNull(withChild, "递归部门成员为空");
        assertTrue(withChild.size() >= direct.size(), "fetch_child=1 的结果不应少于 fetch_child=0");
        System.out.println("[live] getDepartmentUser(root, fetchChild=true) size=" + withChild.size());
    }

    @Test
    public void getDepartmentUserDetailFetchChildIsNotSmaller() {
        List<WeChatUser> direct = addressBookService.getDepartmentUserDetail(rootDeptId(), false, app);
        List<WeChatUser> withChild = addressBookService.getDepartmentUserDetail(rootDeptId(), true, app);

        assertNotNull(withChild, "递归部门成员详情为空");
        assertTrue(withChild.size() >= direct.size(), "fetch_child=1 的详情结果不应少于 fetch_child=0");
        for (WeChatUser user : withChild) {
            assertFalse(isBlank(user.getUserId()), "成员 userId 为空");
            assertNotNull(user.getName(), "成员 name 为空");
        }
        System.out.println("[live] getDepartmentUserDetail(root, fetchChild=true) size=" + withChild.size()
                + ", withMobile=" + withChild.stream().filter(u -> !isBlank(u.getMobile())).count()
                + ", withEmail=" + withChild.stream().filter(u -> !isBlank(u.getEmail())).count());
    }

    /**
     * 官方文档 96067：list_id「仅支持通过通讯录同步 secret 调用」；自建应用凭据得到 48002 时由 handler 跳过
     */
    @Test
    public void getUserIdsReturnsFirstCursorPage() {
        CursorPageRequest request = new CursorPageRequest();
        List<DeptUser> deptUsers = addressBookService.getUserIds(request, app);

        assertNotNull(deptUsers, "list_id 响应为空");
        assertTrue(deptUsers.size() <= 100, "单页不应超过 limit");
        for (DeptUser deptUser : deptUsers) {
            assertFalse(isBlank(deptUser.getUserId()), "dept_user.userId 为空");
            assertNotNull(deptUser.getDepartment(), "dept_user.department 为空");
        }
        System.out.println("[live] getUserIds(limit=100) size=" + deptUsers.size());
    }

    @Test
    public void getUserDetailMatchesRequestedUserId() {
        String userId = sampleUserId();
        assumeTrue(!isBlank(userId), "企业没有可读取的成员，跳过");

        WeChatUserResponse user = sampleUser();
        assertEquals(userId, user.getUserId(), "成员详情 userId 不一致");
        assertFalse(isBlank(user.getName()), "成员详情 name 为空");
        assertTrue(user.getDepartment() != null && !user.getDepartment().isEmpty(), "成员详情 department 为空");
        System.out.println("[live] getUser ok, hasMobile=" + !isBlank(user.getMobile())
                + ", hasEmail=" + !isBlank(user.getEmail()));
    }

    @Test
    public void clientProxyGetUserAgreesWithService() {
        String userId = sampleUserId();
        assumeTrue(!isBlank(userId), "企业没有可读取的成员，跳过");

        WeChatUserResponse viaClient = client.client(AddressBookClient.class).getUser(userId, app);

        assertEquals(sampleUser().getUserId(), viaClient.getUserId(), "client 直调与 service 结果不一致");
        assertEquals(sampleUser().getName(), viaClient.getName(), "client 直调与 service 姓名不一致");
        System.out.println("[live] AddressBookClient.getUser direct call ok");
    }

    @Test
    public void convertToOpenIdRoundTripsBackToUserId() {
        String userId = sampleUserId();
        assumeTrue(!isBlank(userId), "企业没有可读取的成员，跳过");

        String openId = addressBookService.convertToOpenId(userId, app);

        assertFalse(isBlank(openId), "openid 为空");
        String back = addressBookService.convertToUserId(openId, app);
        assertEquals(userId, back, "openid 转回 userid 不一致");
        System.out.println("[live] convertToOpenId/convertToUserId round trip ok");
    }

    @Test
    public void getTagListDecodesResponse() {
        List<Tag> list = tags();

        for (Tag tag : list) {
            assertNotNull(tag.getTagId(), "标签 id 为空");
        }
        System.out.println("[live] getTagList size=" + list.size());
    }

    @Test
    public void getTagUserReturnsFirstTagMembers() {
        List<Tag> list = tags();
        assumeTrue(!list.isEmpty(), "企业没有标签，跳过");

        com.github.shuaidd.response.addressbook.QueryTagUserResponse tagUser =
                addressBookService.getTagUser(list.get(0).getTagId(), app);

        assertNotNull(tagUser, "标签成员为空");
        assertFalse(isBlank(tagUser.getTagName()), "标签名称为空");
        assertNotNull(tagUser.getUserList(), "标签成员列表为空");
        for (WeChatUser user : tagUser.getUserList()) {
            assertFalse(isBlank(user.getUserId()), "标签成员 userId 为空");
        }
        System.out.println("[live] getTagUser(first) size=" + tagUser.getUserList().size()
                + ", partyList=" + (tagUser.getPartyList() == null ? 0 : tagUser.getPartyList().size()));
    }

    /**
     * 官方文档 92714 已从目录下架，权限要求无法取证；凭据被 48002 拒绝时由 handler 跳过
     */
    @Test
    public void getActiveStatReturnsCount() {
        Date yesterday = new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1));
        Integer activeCnt = addressBookService.getActiveStat(yesterday, app);

        assertNotNull(activeCnt, "活跃成员数为空");
        assertTrue(activeCnt >= 0, "活跃成员数不应为负");
        System.out.println("[live] getActiveStat(yesterday) activeCnt=" + activeCnt);
    }

    /**
     * 官方文档 91714：须拥有通讯录管理权限并使用通讯录同步 Secret；自建应用凭据得到 48002 时由 handler 跳过
     */
    @Test
    public void getCorpQrCodeReturnsUrl() {
        String qrCode = addressBookService.getCorpQrCode(1, app);

        assertFalse(isBlank(qrCode), "加入企业二维码为空");
        assertTrue(qrCode.startsWith("http"), "加入企业二维码应为 URL：" + qrCode);
        System.out.println("[live] getCorpQrCode ok, length=" + qrCode.length());
    }

    /**
     * 官方文档 101537：规则列表「仅允许通讯录同步应用调用」；自建应用凭据得到 48002 时由 handler 跳过
     */
    @Test
    public void getContactRulesDecodesResponse() {
        List<ContactRule> rules = addressBookService.getContactRules(app);

        System.out.println("[live] getContactRules ok, rules="
                + (rules == null ? "null(企业无隐藏规则)" : rules.size()));
    }

    /**
     * 2022-06-20 之后新创建的自建应用不再返回手机号，可见成员都没有手机号时跳过
     */
    @Test
    public void getUserIdByMobileMatchesSampleUser() {
        WeChatUser user = firstUserWithMobile();
        assumeTrue(user != null, "可见成员都没有手机号，跳过");

        GetUserIdByMobileRequest request = new GetUserIdByMobileRequest();
        request.setMobile(user.getMobile());
        UserIdResponse response = addressBookService.getUserIdByMobile(request, app);

        assertNotNull(response, "getuserid 响应为空");
        assertEquals(user.getUserId(), response.getUserId(), "按手机号查到的 userid 不一致");
        System.out.println("[live] getUserIdByMobile(positive) ok");
    }

    @Test
    public void getUserIdByMobileWithInvalidMobileThrowsWeChatException() {
        GetUserIdByMobileRequest request = new GetUserIdByMobileRequest();
        request.setMobile("0");
        WeChatException exception = weChatError(() -> addressBookService.getUserIdByMobile(request, app));

        System.out.println("[live] getUserIdByMobile(invalid) errcode=" + exception.getCode());
    }

    /**
     * 2022-06-20 之后新创建的自建应用不再返回邮箱，可见成员都没有邮箱时跳过
     */
    @Test
    public void getUserIdByEmailMatchesSampleUser() {
        WeChatUser user = firstUserWithEmail();
        assumeTrue(user != null, "可见成员都没有邮箱，跳过");

        GetUserIdByEmailRequest request = new GetUserIdByEmailRequest();
        request.setEmail(user.getEmail());
        request.setEmailType(1);
        UserIdResponse response = addressBookService.getUserIdByEmail(request, app);

        assertNotNull(response, "get_userid_by_email 响应为空");
        assertEquals(user.getUserId(), response.getUserId(), "按邮箱查到的 userid 不一致");
        System.out.println("[live] getUserIdByEmail(positive) ok");
    }

    @Test
    public void getUserIdByEmailWithInvalidEmailThrowsWeChatException() {
        GetUserIdByEmailRequest request = new GetUserIdByEmailRequest();
        request.setEmail("not-an-email");
        request.setEmailType(1);
        WeChatException exception = weChatError(() -> addressBookService.getUserIdByEmail(request, app));

        System.out.println("[live] getUserIdByEmail(invalid) errcode=" + exception.getCode());
    }

    @Test
    public void getAuthenticationWithInvalidCodeThrowsWeChatException() {
        AddressBookClient addressBookClient = client.client(AddressBookClient.class);
        WeChatException exception = weChatError(() -> addressBookClient.getAuthentication("invalid-oauth-code", app));

        System.out.println("[live] getAuthentication(invalid) errcode=" + exception.getCode());
    }

    @Test
    public void jobResultWithUnknownJobThrowsWeChatException() {
        AddressBookClient addressBookClient = client.client(AddressBookClient.class);
        WeChatException exception = weChatError(() -> addressBookClient.jobResult("nonexistent-jobid-for-live-test", app));

        System.out.println("[live] jobResult(unknown) errcode=" + exception.getCode());
    }

    /**
     * 缓存里是一个无效令牌时：首次调用被企业微信拒绝（40014），TokenRetryInvocationHandlerFactory 清缓存重试一次后成功
     */
    @Test
    public void invalidCachedTokenIsRefreshedAndRetried() {
        InMemoryTokenStore store = new InMemoryTokenStore();
        String bogus = "invalid-token-for-live-test";
        store.put(app, TokenEntry.of(bogus, 7200, System.currentTimeMillis()));

        ApplicationProperties application = new ApplicationProperties();
        application.setApplicationName(app);
        application.setSecret(secret);
        application.setCorpId(corpId);

        QywxClient staleClient = QywxClient.create(QywxConfig.builder()
                .corpId(corpId)
                .application(application)
                .tokenStore(store)
                .build());

        // 探测接口随凭据而定：通讯录同步 secret 用 list_id（其它详情类接口会被 48009 拒绝），自建应用用根部门成员数（list_id 会 48002）
        int expected = countMembers(addressBookService);
        int actual = countMembers(staleClient.weChatManager().addressBookService());

        assertTrue(expected > 0, "正常客户端拿不到任何成员，无法比对");
        assertEquals(expected, actual, "无效令牌重试后的成员数与正常客户端不一致");
        TokenEntry refreshed = store.get(app);
        assertNotNull(refreshed, "重试后缓存应写入新令牌");
        assertNotEquals(bogus, refreshed.getAccessToken(), "重试后缓存仍是无效令牌");
        assertFalse(isBlank(refreshed.getAccessToken()), "重试后缓存的新令牌为空");
        System.out.println("[live] invalid token -> retry ok, refreshedTokenLength=" + refreshed.getAccessToken().length());
    }

    /**
     * 企业微信先校验调用方出口 IP（errcode 60020 不在可信 IP）再做业务校验：不在白名单时所有业务接口都会被拒，
     * 这里用一次最轻量的读请求提前探明并把出口 IP 报出来，避免整组用例都报成同一个看不懂的错
     */
    private void probeTrustedIp() {
        try {
            addressBookService.getTagList(app);
            System.out.println("[live] trusted-ip probe ok");
        } catch (RuntimeException e) {
            WeChatException weChatException = findWeChatException(e);
            if (weChatException != null && "60020".equals(weChatException.getCode())) {
                fail("企业微信拒绝了本机出口 IP（errcode 60020）：" + weChatException.getMessage()
                        + "。请让企业管理员把该出口 IP 加入「通讯录同步 / 对应应用」的可信 IP 后重跑；"
                        + "加白前只能验证取号链路，业务接口无法联调。");
            }
            // 非 IP 问题（例如当前凭据不能读标签）不阻断：交给各用例各自报告
            System.out.println("[live] trusted-ip probe errcode=" + (weChatException == null ? "n/a" : weChatException.getCode())
                    + "，非 60020，继续执行");
        }
    }

    /**
     * 与 5.x 一致：业务错误（errcode≠0）由 decoder 抛出 WeChatException，Feign 再把它包成 DecodeException，
     * 这里沿 cause 链取出 WeChatException 返回给断言
     */
    private static WeChatException weChatError(Executable call) {
        try {
            call.execute();
        } catch (Throwable throwable) {
            WeChatException weChatException = findWeChatException(throwable);
            assertNotNull(weChatException, "应抛出 WeChatException，实际为：" + throwable);
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

    private List<Department> departments() {
        if (departments == null) {
            departments = addressBookService.departmentList(null, app);
            assertNotNull(departments, "部门列表为空");
            assertFalse(departments.isEmpty(), "部门列表不应为空");
        }
        return departments;
    }

    /**
     * 根部门优先取 id=1（企业微信固定为根部门），否则取 parentid 为 0/空的部门，再否则取第一个
     */
    private Integer rootDeptId() {
        if (rootDeptId == null) {
            rootDeptId = departments().stream()
                    .filter(d -> Objects.equals(d.getId(), 1))
                    .map(Department::getId)
                    .findFirst()
                    .orElseGet(() -> departments().stream()
                            .filter(d -> d.getParentId() == null || d.getParentId() == 0)
                            .map(Department::getId)
                            .findFirst()
                            .orElse(departments().get(0).getId()));
        }
        return rootDeptId;
    }

    /**
     * 样本成员：优先取 list_id 的第一个（企业通讯录的天然顺序），无权调用时退回根部门递归成员详情的第一个
     */
    private String sampleUserId() {
        if (sampleUserId == null) {
            sampleUserId = firstUserIdFromListId();
            if (isBlank(sampleUserId)) {
                sampleUserId = departmentUsers().stream()
                        .map(WeChatUser::getUserId)
                        .filter(id -> !isBlank(id))
                        .findFirst()
                        .orElse(null);
            }
        }
        return sampleUserId;
    }

    /**
     * list_id 只有通讯录同步 secret 能调（文档 96067）；自建应用会得到 48002，此时打日志并返回 null 让调用方退回部门成员列表
     */
    private String firstUserIdFromListId() {
        try {
            return addressBookService.getUserIds(new CursorPageRequest(), app).stream()
                    .map(DeptUser::getUserId)
                    .filter(id -> !isBlank(id))
                    .findFirst()
                    .orElse(null);
        } catch (RuntimeException e) {
            WeChatException weChatException = findWeChatException(e);
            if (weChatException != null && PERMISSION_DENIED.contains(weChatException.getCode())) {
                System.out.println("[live] list_id errcode=" + weChatException.getCode() + "，样本成员改从部门成员详情列表取");
                return null;
            }
            throw e;
        }
    }

    /**
     * 根部门递归的成员详情列表（user/list + fetch_child=1）：样本成员与手机号/邮箱正向用例都从这里取
     */
    private List<WeChatUser> departmentUsers() {
        if (departmentUsers == null) {
            departmentUsers = addressBookService.getDepartmentUserDetail(rootDeptId(), true, app);
            assertNotNull(departmentUsers, "部门成员（详情）为空");
        }
        return departmentUsers;
    }

    private WeChatUser firstUserWithMobile() {
        return departmentUsers().stream()
                .filter(u -> !isBlank(u.getUserId()) && !isBlank(u.getMobile()))
                .findFirst()
                .orElse(null);
    }

    private WeChatUser firstUserWithEmail() {
        return departmentUsers().stream()
                .filter(u -> !isBlank(u.getUserId()) && !isBlank(u.getEmail()))
                .findFirst()
                .orElse(null);
    }

    /**
     * 当前凭据可用的最轻量成员计数：优先 list_id 首页，无权时退回根部门直接成员数（fetch_child=0）
     */
    private int countMembers(AddressBookService service) {
        try {
            return service.getUserIds(new CursorPageRequest(), app).size();
        } catch (RuntimeException e) {
            WeChatException weChatException = findWeChatException(e);
            if (weChatException != null && PERMISSION_DENIED.contains(weChatException.getCode())) {
                return service.getDepartmentUser(rootDeptId(), false, app).size();
            }
            throw e;
        }
    }

    private WeChatUserResponse sampleUser() {
        if (sampleUser == null) {
            sampleUser = addressBookService.getUser(sampleUserId(), app);
        }
        assertNotNull(sampleUser, "成员详情为空");
        return sampleUser;
    }

    private List<Tag> tags() {
        if (tags == null) {
            tags = addressBookService.getTagList(app);
            assertNotNull(tags, "标签列表为空");
        }
        return tags;
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
