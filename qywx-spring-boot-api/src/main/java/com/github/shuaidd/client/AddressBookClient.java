package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.addressbook.*;
import com.github.shuaidd.response.auth.AuthenticationResponse;
import com.github.shuaidd.resquest.CursorPageRequest;
import com.github.shuaidd.resquest.addressbook.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 通讯录接口
 *
 * @author ddshuai
 * date 2022-09-01 09:03
 **/
@FeignClient(name = "AddressBookClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface AddressBookClient extends CommonClient {

    /**
     * 创建成员
     *
     * @param request 请求参数
     * @param app     应用
     * @return BaseResponse
     */
    @PostMapping(value = "user/create", headers = HEAD)
    BaseResponse createUser(CreateUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 读取成员信息
     *
     * @param app    应用名
     * @param userId 成员编号
     * @return WeChatUserResponse
     */
    @GetMapping(value = "user/get", headers = HEAD)
    WeChatUserResponse getUser(@RequestParam("userid") String userId, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新成员信息【根据userID更新，必传】
     *
     * @param app     应用名
     * @param request 更新请求参数
     * @return BaseResponse
     */
    @PostMapping(value = "user/update", headers = HEAD)
    BaseResponse updateUser(UpdateUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除成员
     *
     * @param userId 成员编号
     * @param app    应用
     * @return BaseResponse
     */
    @GetMapping(value = "user/delete", headers = HEAD)
    BaseResponse deleteUser(@RequestParam("userid") String userId, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量删除成员
     *
     * @param request 请求体
     * @param app     应用
     * @return BaseResponse
     */
    @PostMapping(value = "user/batchdelete", headers = HEAD)
    BaseResponse batchDelete(BatchDeleteUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取部门成员[应用须拥有指定部门的查看权限。]
     *
     * @param app          应用名
     * @param departmentId 获取的部门id
     * @param fetchChild   1/0：是否递归获取子部门下面的成员
     * @return DepartmentUserResponse
     */
    @GetMapping(value = "user/simplelist", headers = HEAD)
    DepartmentUserResponse getDepartmentUser(@RequestParam("department_id") Integer departmentId, @RequestParam("fetch_child") Integer fetchChild, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取部门成员详情[应用须拥有指定部门的查看权限。]
     *
     * @param app          应用名
     * @param departmentId 获取的部门id
     * @param fetchChild   1/0：是否递归获取子部门下面的成员
     * @return DepartmentUserResponse
     */
    @GetMapping(value = "user/list", headers = HEAD)
    DepartmentUserResponse getDepartmentUserDetail(@RequestParam("department_id") Integer departmentId, @RequestParam("fetch_child") Integer fetchChild, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取成员ID列表
     *
     * @param request 请求
     * @param app     应用名
     * @return SimpleDeptUserResponse
     */
    @PostMapping(value = "user/list_id", headers = HEAD)
    SimpleDeptUserResponse getUserIds(CursorPageRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * userid转openid
     *
     * @param request 请求体
     * @param app     应用名
     * @return ConvertUserIdOpenIdResponse
     */
    @PostMapping(value = "user/convert_to_openid", headers = HEAD)
    ConvertUserIdOpenIdResponse convertToOpenId(ConvertUserIdOpenIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * openid转userid
     *
     * @param request 请求体
     * @param app     应用名
     * @return ConvertUserIdOpenIdResponse
     */
    @PostMapping(value = "user/convert_to_userid", headers = HEAD)
    ConvertUserIdOpenIdResponse convertToUserId(ConvertUserIdOpenIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 二次验证
     *
     * @param userId 用户编号
     * @param app    应用名
     * @return BaseResponse
     */
    @GetMapping(value = "user/authsucc", headers = HEAD)
    BaseResponse authSucc(@RequestParam("userid") String userId, @RequestParam(HEAD_KEY) String app);

    /**
     * 邀请成员
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "batch/invite", headers = HEAD)
    BaseResponse invite(InviteUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建部门
     *
     * @param app     应用
     * @param request 请求体
     * @return CreateDepartmentResponse
     */
    @PostMapping(value = "department/create", headers = HEAD)
    CreateDepartmentResponse createDepartment(DepartmentRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新部门
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "department/update", headers = HEAD)
    BaseResponse updateDepartment(DepartmentRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除部门【注：不能删除根部门；不能删除含有子部门、成员的部门】
     *
     * @param id  企业微信部门id
     * @param app 应用名
     * @return BaseResponse
     */
    @GetMapping(value = "department/delete", headers = HEAD)
    BaseResponse deleteDepartment(@RequestParam(value = "id", required = false) Integer id, @RequestParam(HEAD_KEY) String app);

    /**
     * 权限说明：
     * 只能拉取token对应的应用的权限范围内的部门列表
     * <p>
     * 获取部门列表【获取指定部门及其下的子部门。 如果不填，默认获取全量组织架构】
     *
     * @param id  部门id
     * @param app 应用名
     * @return DepartmentListResponse
     */
    @GetMapping(value = "department/list", headers = HEAD)
    DepartmentListResponse departmentList(@RequestParam(value = "id", required = false) Integer id, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取子部门ID列表
     *
     * @param id  部门id
     * @param app 应用名
     * @return SimpleDepartmentListResponse
     */
    @GetMapping(value = "department/simplelist", headers = HEAD)
    SimpleDepartmentListResponse departmentSimpleList(@RequestParam(value = "id", required = false) Integer id, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建标签【创建的标签属于该应用，只有该应用才可以增删成员】
     * 注意，标签总数不能超过3000个。
     *
     * @param request 请求体
     * @param app     应用名
     * @return CreateTagResponse
     */
    @PostMapping(value = "tag/create", headers = HEAD)
    CreateTagResponse createTag(TagRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 权限说明：
     * 调用者必须是指定标签的创建者
     * <p>
     * 更新标签名字【长度限制为32个字（汉字或英文字母），标签不可与其他标签重名】
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "tag/update", headers = HEAD)
    BaseResponse updateTag(TagRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除标签
     *
     * @param id  标签ID
     * @param app 应用名
     * @return BaseResponse
     */
    @GetMapping(value = "tag/delete", headers = HEAD)
    BaseResponse deleteTag(@RequestParam("tagid") Integer id, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取标签成员
     *
     * @param id  标签id
     * @param app 应用名
     * @return QueryTagUserResponse
     */
    @GetMapping(value = "tag/get", headers = HEAD)
    QueryTagUserResponse getTagUser(@RequestParam("tagid") Integer id, @RequestParam(HEAD_KEY) String app);

    /**
     * 增加标签成员
     *
     * @param request 请求体
     * @param app     应用名
     * @return TagUserResponse
     */
    @PostMapping(value = "tag/addtagusers", headers = HEAD)
    TagUserResponse addTagUsers(TagUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除标签成员
     *
     * @param request 请求体
     * @param app     应用名
     * @return TagUserResponse
     */
    @PostMapping(value = "tag/deltagusers", headers = HEAD)
    TagUserResponse deleteTagUsers(TagUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取标签列表
     *
     * @param app 应用名
     * @return TagListResponse
     */
    @GetMapping(value = "tag/list", headers = HEAD)
    TagListResponse getTagList(@RequestParam(HEAD_KEY) String app);

    /**
     * 注意事项：
     * <p>
     * 模板中的部门需填写部门ID，多个部门用分号分隔，部门ID必须为数字，根部门的部门id默认为1
     * 文件中存在、通讯录中也存在的成员，更新成员在文件中指定的字段值
     * 文件中存在、通讯录中不存在的成员，执行添加操作
     * 通讯录中存在、文件中不存在的成员，保持不变
     * 成员字段更新规则：可自行添加扩展字段。文件中有指定的字段，以指定的字段值为准；文件中没指定的字段，不更新
     * <p>
     * <p>
     * 增量更新成员
     *
     * @param request 请求体
     * @param app     应用名
     * @return AsyncJobResponse
     */
    @PostMapping(value = "batch/syncuser", headers = HEAD)
    AsyncJobResponse asyncBatchUpdateUser(AsyncJobRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 注意事项：
     * <p>
     * 模板中的部门需填写部门ID，多个部门用分号分隔，部门ID必须为数字，根部门的部门id默认为1
     * 文件中存在、通讯录中也存在的成员，完全以文件为准
     * 文件中存在、通讯录中不存在的成员，执行添加操作
     * 通讯录中存在、文件中不存在的成员，执行删除操作。出于安全考虑，下面两种情形系统将中止导入并返回相应的错误码。
     * 需要删除的成员多于50人，且多于现有人数的20%以上
     * 需要删除的成员少于50人，且多于现有人数的80%以上
     * 成员字段更新规则：可自行添加扩展字段。文件中有指定的字段，以指定的字段值为准；文件中没指定的字段，不更新
     * <p>
     * 全量覆盖成员
     *
     * @param request 请求体
     * @param app     应用名
     * @return AsyncJobResponse
     */
    @PostMapping(value = "batch/replaceuser", headers = HEAD)
    AsyncJobResponse fullCoverUser(AsyncJobRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 注意事项：
     * <p>
     * 文件中存在、通讯录中也存在的部门，执行修改操作
     * 文件中存在、通讯录中不存在的部门，执行添加操作
     * 文件中不存在、通讯录中存在的部门，当部门下没有任何成员或子部门时，执行删除操作
     * 文件中不存在、通讯录中存在的部门，当部门下仍有成员或子部门时，暂时不会删除，当下次导入成员把人从部门移出后自动删除
     * CSV文件中，部门名称、部门ID、父部门ID为必填字段，部门ID必须为数字，根部门的部门id默认为1；排序为可选字段，置空或填0不修改排序, order值大的排序靠前。
     * <p>
     * 全量覆盖部门
     *
     * @param request 请求体
     * @param app     应用名
     * @return AsyncJobResponse
     */
    @PostMapping(value = "batch/replaceparty", headers = HEAD)
    AsyncJobResponse fullCoverDepartment(AsyncJobRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取异步任务结果
     *
     * @param jobId 任务id
     * @param app   应用名
     * @return AsyncJobResultResponse
     */
    @GetMapping(value = "batch/getresult", headers = HEAD)
    AsyncJobResultResponse jobResult(@RequestParam("jobid") String jobId, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取访问用户身份
     *
     * @param code 授权码
     * @param app  应用名
     * @return AuthenticationResponse
     */
    @GetMapping(value = "user/getuserinfo", headers = HEAD)
    AuthenticationResponse getAuthentication(@RequestParam("code") String code, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取企业活跃成员数
     *
     * @param request 请求体
     * @param app     应用名
     * @return 企业活跃成员数
     */
    @PostMapping(value = "user/get_active_stat", headers = HEAD)
    ActiveStatResponse getActiveStat(ActiveStatRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 导出成员
     *
     * @param request 请求体
     * @param app     应用名
     * @return 异步任务编号
     */
    @PostMapping(value = "export/simple_user", headers = HEAD)
    AsyncJobResponse exportUser(AddressBookExportRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 导出成员详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return 异步任务编号
     */
    @PostMapping(value = "export/user", headers = HEAD)
    AsyncJobResponse exportUserDetail(AddressBookExportRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 导出部门
     *
     * @param request 请求体
     * @param app     应用名
     * @return 异步任务编号
     */
    @PostMapping(value = "export/department", headers = HEAD)
    AsyncJobResponse exportDepartment(AddressBookExportRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 导出标签成员
     *
     * @param request 请求体
     * @param app     应用名
     * @return 异步任务编号
     */
    @PostMapping(value = "export/taguser", headers = HEAD)
    AsyncJobResponse exportTagUser(AddressBookExportRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取加入企业二维码
     *
     * @param sizeType qrcode尺寸类型
     * @param app      应用名
     * @return 二维码地址
     */
    @GetMapping(value = "corp/get_join_qrcode", headers = HEAD)
    JoinQrCodeResponse getJoinQrCode(@RequestParam("size_type") Integer sizeType, @RequestParam(HEAD_KEY) String app);
}
