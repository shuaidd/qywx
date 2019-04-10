package com.github.shuaidd.service;

import cn.hutool.core.date.DateUtil;
import com.github.shuaidd.dto.CallbackData;
import com.github.shuaidd.dto.Department;
import com.github.shuaidd.dto.Tag;
import com.github.shuaidd.dto.WeChatUser;
import com.github.shuaidd.response.*;
import com.github.shuaidd.resquest.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 企业微信通讯录管理
 *
 * @author ddshuai
 * @date 2019-04-04 14:22
 **/
@Service
public class AddressBookService extends AbstractBaseService {

    /**
     * 增量更新用户时上传的文件名称
     */
    private static final String ASYNC_BATCH_UPDATE_USER_FILE = "asyncBatchUpdateUser-%s.csv";

    /**
     * 全量覆盖用户时
     */
    private static final String FULL_COVER_USER_FILE = "fullCoverUser-%s.csv";

    /**
     * 批量更新用户
     */
    private static final String ASYNC_BATCH_UPDATE_USER = "asyncBatchUpdateUser";

    /**
     * 全量覆盖用户
     */
    private static final String FULL_COVER_USER = "fullCoverUser";

    /**
     * 更新用户时上传文件的title
     */
    private static final String ASYNC_BATCH_UPDATE_USER_FILE_TITLE = "姓名,帐号,手机号,邮箱,所在部门,职位,性别,是否部门内领导,排序,别名,地址,座机,禁用,禁用项说明：(0-启用;1-禁用)\n";


    private static final String FULL_COVER_DEPARTMENT_FILE_TITLE = "部门名称,部门ID,父部门ID,排序\n";

    /**
     * 全量覆盖部门
     */
    private static final String FULL_COVER_DEPARTMENT_FILE = "fullCoverUser-%s.csv";

    private final MediaService mediaService;

    @Autowired
    public AddressBookService(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    /**
     * 创建用户
     *
     * @param request
     */
    public void createUser(CreateUserRequest request, String applicationName) {
        if (Objects.nonNull(request)) {
            BaseResponse baseResponse = weChatClient.createUser(request, applicationName);
            if (isSuccess(baseResponse)) {
                logger.info("用户创建成功:{}", request);
            }
        }
    }

    /**
     * 读取成员信息
     *
     * @param userId          用户编号
     * @param applicationName 应用
     * @return WeChatUserResponse
     */
    public WeChatUserResponse getUser(String userId, String applicationName) {
        WeChatUserResponse weChatUserResponse = null;
        if (StringUtils.isNotEmpty(userId)) {
            weChatUserResponse = weChatClient.getUser(userId, applicationName);
            if (isSuccess(weChatUserResponse)) {
                if (logger.isInfoEnabled()) {
                    logger.info("读取到成员信息：{}", weChatUserResponse);
                }
            }
        }

        return weChatUserResponse;
    }

    /**
     * 更新成员信息
     *
     * @param request         更新信息
     * @param applicationName 应用
     */
    public void updateUser(UpdateUserRequest request, String applicationName) {
        if (Objects.nonNull(request) && StringUtils.isNotEmpty(request.getUserId())) {
            BaseResponse baseResponse = weChatClient.updateUser(request, applicationName);
            if (isSuccess(baseResponse)) {
                if (logger.isInfoEnabled()) {
                    logger.info("更新成员信息成功：userId-{},applicationName-{}", request.getUserId(), applicationName);
                }
            }
        }
    }

    /**
     * 删除成员
     *
     * @param userId
     * @param applicationName
     */
    public void deleteUser(String userId, String applicationName) {
        if (StringUtils.isNotEmpty(userId)) {
            BaseResponse response = weChatClient.deleteUser(userId, applicationName);
            if (isSuccess(response)) {
                if (logger.isInfoEnabled()) {
                    logger.info("删除成员成功：userId-{},applicationName-{}", userId, applicationName);
                }
            }
        }
    }

    /**
     * 批量删除成员
     *
     * @param request
     * @param applicationName
     */
    public void batchDeleteUser(BatchDeleteUserRequest request, String applicationName) {
        if (Objects.nonNull(request) && CollectionUtils.isNotEmpty(request.getUserIdList())) {
            BaseResponse response = weChatClient.batchDelete(request, applicationName);
            if (isSuccess(response)) {
                if (logger.isInfoEnabled()) {
                    logger.info("批量删除成员成功：{},applicationName-{}", request, applicationName);
                }
            }
        }
    }

    /**
     * 获取部门成员
     *
     * @param departmentId    企业微信端部门编号
     * @param fetchChild      是否获取子部门人员
     * @param applicationName 应用
     * @return
     */
    public List<WeChatUser> getDepartmentUser(Integer departmentId, boolean fetchChild, String applicationName) {
        List<WeChatUser> weChatUsers = new ArrayList<>(1);
        if (Objects.nonNull(departmentId)) {
            Integer getChild = fetchChild ? 1 : 0;
            DepartmentUserResponse departmentUserResponse = weChatClient.getDepartmentUser(departmentId, getChild, applicationName);
            if (isSuccess(departmentUserResponse)) {
                weChatUsers = departmentUserResponse.getWeChatUserList();

                if (logger.isInfoEnabled()) {
                    logger.info("获取部门成员成功：applicationName-{}", applicationName);
                }
            }
        }

        return weChatUsers;
    }

    /**
     * 获取部门成员详情
     *
     * @param departmentId    企业微信端部门编号
     * @param fetchChild      是否获取子部门人员
     * @param applicationName 应用
     * @return
     */
    public List<WeChatUser> getDepartmentUserDetail(Integer departmentId, boolean fetchChild, String applicationName) {
        List<WeChatUser> weChatUsers = new ArrayList<>(1);
        if (Objects.nonNull(departmentId)) {
            Integer getChild = fetchChild ? 1 : 0;
            DepartmentUserResponse departmentUserResponse = weChatClient.getDepartmentUserDetail(departmentId, getChild, applicationName);
            if (isSuccess(departmentUserResponse)) {
                weChatUsers = departmentUserResponse.getWeChatUserList();

                if (logger.isInfoEnabled()) {
                    logger.info("获取部门成员成功：applicationName-{}", applicationName);
                }
            }
        }

        return weChatUsers;
    }

    /**
     * userid转openid
     *
     * @param userId
     * @param applicationName
     * @return
     */
    public String convertToOpenId(String userId, String applicationName) {
        String openId = null;
        if (StringUtils.isNotEmpty(userId)) {
            ConvertUserIdOpenIdResponse response = weChatClient.convertToOpenId(new ConvertUserIdOpenIdRequest(userId, null), applicationName);
            if (isSuccess(response)) {
                openId = response.getOpenId();
                if (logger.isInfoEnabled()) {
                    logger.info("获取openId成功：openid-{}applicationName-{}", openId, applicationName);
                }
            }
        }

        return openId;
    }

    /**
     * openid转userid
     *
     * @param openId
     * @param applicationName
     * @return
     */
    public String convertToUserId(String openId, String applicationName) {
        String userId = null;
        if (StringUtils.isNotEmpty(openId)) {
            ConvertUserIdOpenIdResponse response = weChatClient.convertToUserId(new ConvertUserIdOpenIdRequest(null, openId), applicationName);
            if (isSuccess(response)) {
                userId = response.getUserId();
                if (logger.isInfoEnabled()) {
                    logger.info("获取用户ID成功：userId-{},applicationName-{}", userId, applicationName);
                }
            }
        }

        return userId;
    }

    /**
     * 二次验证
     *
     * @param userId
     * @param applicationName
     */
    public void authSuccess(String userId, String applicationName) {
        if (StringUtils.isNotEmpty(userId)) {
            BaseResponse response = weChatClient.authSucc(userId, applicationName);
            if (isSuccess(response)) {
                if (logger.isInfoEnabled()) {
                    logger.info("二次验证成功：userId-{},applicationName-{}", userId, applicationName);
                }
            }
        }
    }

    /**
     * 邀请成员
     *
     * @param request
     * @param applicationName
     */
    public void invite(InviteUserRequest request, String applicationName) {
        if (Objects.nonNull(request)) {
            if (CollectionUtils.isEmpty(request.getParty()) && CollectionUtils.isEmpty(request.getTag()) && CollectionUtils.isEmpty(request.getUser())) {
                return;
            }
            BaseResponse baseResponse = weChatClient.invite(request, applicationName);
            if (isSuccess(baseResponse)) {
                if (logger.isInfoEnabled()) {
                    logger.info("邀请成功：applicationName-{}", applicationName);
                }
            }
        }
    }

    /**
     * 创建部门
     *
     * @param request
     * @param applicationName
     * @return
     */
    public CreateDepartmentResponse createDepartment(DepartmentRequest request, String applicationName) {
        CreateDepartmentResponse response = null;
        if (Objects.nonNull(request) && StringUtils.isNotEmpty(request.getName())) {
            response = weChatClient.createDepartment(request, applicationName);
            if (isSuccess(response)) {
                if (logger.isInfoEnabled()) {
                    logger.info("创建部门成功：部门编号-{},applicationName-{}", response.getId(), applicationName);
                }
            }
        }

        return response;
    }

    /**
     * 更新部门信息
     *
     * @param request
     * @param applicationName
     */
    public void updateDepartment(DepartmentRequest request, String applicationName) {
        if (Objects.nonNull(request) && Objects.nonNull(request.getId())) {
            BaseResponse response = weChatClient.updateDepartment(request, applicationName);
            if (isSuccess(response)) {
                if (logger.isInfoEnabled()) {
                    logger.info("更新部门成功：更新信息-{},applicationName-{}", request, applicationName);
                }
            }
        }
    }

    /**
     * 删除部门
     *
     * @param id
     * @param applicationName
     */
    public void deleteDepartment(Integer id, String applicationName) {
        if (Objects.nonNull(id)) {
            BaseResponse response = weChatClient.deleteDepartment(id, applicationName);
            if (isSuccess(response)) {
                if (logger.isInfoEnabled()) {
                    logger.info("删除部门成功：部门编号-{},applicationName-{}", id, applicationName);
                }
            }
        }
    }

    /**
     * 拉取部门列表
     *
     * @param id
     * @param applicationName
     * @return
     */
    public List<Department> departmentList(Integer id, String applicationName) {
        List<Department> departments = new ArrayList<>(1);
        DepartmentListResponse response = weChatClient.departmentList(id, applicationName);
        if (isSuccess(response)) {
            departments = response.getDepartments();
            if (logger.isInfoEnabled()) {
                logger.info("拉取部门列表成功：部门数量-{},applicationName-{}", departments.size(), applicationName);
            }
        }

        return departments;
    }

    /**
     * 创建标签
     *
     * @param request
     * @param applicationName
     * @return
     */
    public Integer createTag(TagRequest request, String applicationName) {
        Integer tagId = null;
        if (Objects.nonNull(request) && StringUtils.isNotEmpty(request.getTagName())) {
            CreateTagResponse createTagResponse = weChatClient.createTag(request, applicationName);
            if (isSuccess(createTagResponse)) {
                tagId = createTagResponse.getTagId();
                if (logger.isInfoEnabled()) {
                    logger.info("创建标签成功：标签编号-{},applicationName-{}", tagId, applicationName);
                }
            }
        }

        return tagId;
    }

    /**
     * 更新标签名字
     *
     * @param tagId
     * @param tagName
     * @param applicationName
     */
    public void updateTagName(Integer tagId, String tagName, String applicationName) {
        if (Objects.nonNull(tagId) && StringUtils.isNotEmpty(tagName)) {
            TagRequest tagRequest = new TagRequest();
            tagRequest.setTagId(tagId);
            tagRequest.setTagName(tagName);
            BaseResponse response = weChatClient.updateTag(tagRequest, applicationName);
            if (isSuccess(response)) {
                if (logger.isInfoEnabled()) {
                    logger.info("更新标签名字成功：标签编号[{}],标签名称[{}],applicationName-{}", tagId, tagName, applicationName);
                }
            }
        }
    }

    /**
     * 删除标签
     *
     * @param tagId
     * @param applicationName
     */
    public void deleteTag(Integer tagId, String applicationName) {
        if (Objects.nonNull(tagId)) {
            BaseResponse response = weChatClient.deleteTag(tagId, applicationName);
            if (isSuccess(response)) {
                if (logger.isInfoEnabled()) {
                    logger.info("删除标签成功：标签编号[{}],applicationName-{}", tagId, applicationName);
                }
            }
        }
    }

    /**
     * 获取标签成员
     *
     * @param tagId
     * @param applicationName
     * @return
     */
    public QueryTagUserResponse getTagUser(Integer tagId, String applicationName) {
        QueryTagUserResponse response = null;
        if (Objects.nonNull(tagId)) {
            response = weChatClient.getTagUser(tagId, applicationName);
            if (isSuccess(response)) {
                if (logger.isInfoEnabled()) {
                    logger.info("获取标签成员成功：标签编号[{}],applicationName-{}", tagId, applicationName);
                }
            }
        }

        return response;
    }

    /**
     * 增加标签成员
     *
     * @param request
     * @param applicationName
     * @return
     */
    public TagUserResponse addTagUsers(TagUserRequest request, String applicationName) {
        TagUserResponse response = null;
        if (Objects.nonNull(request) && Objects.nonNull(request.getTagId())) {
            response = weChatClient.addTagUsers(request, applicationName);
            if (isSuccess(response)) {
                if (logger.isInfoEnabled()) {
                    logger.info("增加标签成员成功：标签编号[{}],applicationName-{}", request.getTagId(), applicationName);
                }
            }
        }
        return response;
    }

    /**
     * 删除标签成员
     *
     * @param request
     * @param applicationName
     * @return
     */
    public TagUserResponse deleteTagUsers(TagUserRequest request, String applicationName) {
        TagUserResponse response = null;
        if (Objects.nonNull(request) && Objects.nonNull(request.getTagId())) {
            response = weChatClient.deleteTagUsers(request, applicationName);
            if (isSuccess(response)) {
                if (logger.isInfoEnabled()) {
                    logger.info("删除标签成员成功：标签编号[{}],applicationName-{}", request.getTagId(), applicationName);
                }
            }
        }
        return response;
    }

    /**
     * 获取标签列表
     *
     * @param applicationName
     * @return
     */
    public List<Tag> getTagList(String applicationName) {
        List<Tag> tags = new ArrayList<>(1);
        TagListResponse response = weChatClient.getTagList(applicationName);
        if (isSuccess(response)) {
            tags = response.getTagList();
            if (logger.isInfoEnabled()) {
                logger.info("获取标签列表成功：applicationName-{}", applicationName);
            }
        }

        return tags;
    }

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
     * @param weChatUsers
     * @param applicationName
     * @return AsyncJobResponse
     */
    public String asyncBatchUpdateUser(List<WeChatUser> weChatUsers, Boolean toInvite, CallbackData callbackData, String applicationName) {
        return asyncHandleUser(weChatUsers, toInvite, callbackData, applicationName, ASYNC_BATCH_UPDATE_USER);
    }

    public String asyncBatchUpdateUser(List<WeChatUser> weChatUsers, Boolean toInvite, String applicationName) {
        return asyncBatchUpdateUser(weChatUsers, toInvite, null, applicationName);
    }

    public String fullCoverUser(List<WeChatUser> weChatUsers, Boolean toInvite, CallbackData callbackData, String applicationName) {
        return asyncHandleUser(weChatUsers, toInvite, callbackData, applicationName, FULL_COVER_USER);
    }

    public String fullCoverUser(List<WeChatUser> weChatUsers, Boolean toInvite, String applicationName) {
        return asyncHandleUser(weChatUsers, toInvite, null, applicationName, FULL_COVER_USER);
    }

    private String asyncHandleUser(List<WeChatUser> weChatUsers, Boolean toInvite, CallbackData callbackData, String applicationName, String type) {
        //上传待增量更新的文件
        String fileName = "";
        if (ASYNC_BATCH_UPDATE_USER.equals(type)) {
            fileName = ASYNC_BATCH_UPDATE_USER_FILE;
        } else if (FULL_COVER_USER.equals(type)) {
            fileName = FULL_COVER_USER_FILE;
        }
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                MediaType.TEXT_PLAIN.getType(), true, String.format(fileName, DateUtil.format(new Date(), "yyyyMMddhhmmss")));

        try (OutputStream os = fileItem.getOutputStream()) {
            if (CollectionUtils.isNotEmpty(weChatUsers)) {
                IOUtils.write(ASYNC_BATCH_UPDATE_USER_FILE_TITLE, os);
                /*
                 * csv文件格式
                 * 姓名,帐号,手机号,邮箱,所在部门,职位,性别,是否部门内领导,排序,别名,地址,座机,禁用,禁用项说明：(0-启用;1-禁用)
                 * */
                for (WeChatUser weChatUser : weChatUsers) {
                    String line = buildUserLine(weChatUser);
                    IOUtils.write(line, os);
                }
            }

            //上传
            MultipartFile multi = new CommonsMultipartFile(fileItem);
            String mediaId = mediaService.uploadMaterial(multi, MediaService.FILE, applicationName);
            AsyncJobRequest request = buildAsyncJobRequest(mediaId, toInvite, callbackData);
            AsyncJobResponse response = null;
            if (ASYNC_BATCH_UPDATE_USER.equals(type)) {
                response = weChatClient.asyncBatchUpdateUser(request, applicationName);
            } else if (FULL_COVER_USER.equals(type)) {
                response = weChatClient.fullCoverUser(request, applicationName);
            }
            if (isSuccess(response)) {
                return Objects.nonNull(response) ? response.getJobId() : "";
            }
        } catch (Exception e) {
            logger.error("异步更新用户信息异常:{}，type: {}", applicationName, type, e);
        }

        return null;
    }

    private String buildUserLine(WeChatUser weChatUser) {
        if (Objects.nonNull(weChatUser)) {
            return StringUtils.defaultString(weChatUser.getName(), "") + "," +
                    StringUtils.defaultString(weChatUser.getUserId(), "") + "," +
                    StringUtils.defaultString(weChatUser.getMobile(), "") + "," +
                    StringUtils.defaultString(weChatUser.getEmail(), "") + "," +
                    getStringFromList(weChatUser.getDepartment()) + "," +
                    StringUtils.defaultString(weChatUser.getPosition(), "") + "," +
                    getStringFromList(weChatUser.getIsLeaderInDept()) + "," +
                    getStringFromList(weChatUser.getOrder()) + "," +
                    StringUtils.defaultString(weChatUser.getAlias(), "") + "," +
                    StringUtils.defaultString(weChatUser.getAddress(), "") + "," +
                    StringUtils.defaultString(weChatUser.getTelephone(), "") + "," +
                    (weChatUser.getEnable() == null ? "" : String.valueOf(weChatUser.getEnable())) + ",\n";
        }

        return null;
    }

    private AsyncJobRequest buildAsyncJobRequest(String mediaId, Boolean toInvite, CallbackData callbackData) {
        AsyncJobRequest request = new AsyncJobRequest();
        request.setMediaId(mediaId);
        request.setToInvite(toInvite);
        request.setCallback(callbackData);
        return request;
    }

    private String getStringFromList(List<Integer> list) {
        StringBuilder result = new StringBuilder();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Integer data : list) {
                result.append(data).append(";");
            }
            if (result.toString().endsWith(";")) {
                result.deleteCharAt(result.length() - 1);
            }
        }
        return result.toString();
    }

    /**
     * 全量覆盖部门
     *
     * @param departments
     * @param callbackData
     * @param applicationName
     * @return
     */
    public String fullCoverDepartment(List<Department> departments, CallbackData callbackData, String applicationName) {
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                MediaType.TEXT_PLAIN.getType(), true, String.format(FULL_COVER_DEPARTMENT_FILE, DateUtil.format(new Date(), "yyyyMMddhhmmss")));

        try (OutputStream os = fileItem.getOutputStream()) {
            if (CollectionUtils.isNotEmpty(departments)) {
                IOUtils.write(FULL_COVER_DEPARTMENT_FILE_TITLE, os);
                /*
                 * csv文件格式
                 * 部门名称,部门ID,父部门ID,排序
                 * */
                for (Department department : departments) {
                    String line = StringUtils.defaultString(department.getName(), "") + "," +
                            (department.getId() == null ? "" : String.valueOf(department.getId())) + "," +
                            (department.getParentId() == null ? "" : String.valueOf(department.getParentId())) + "," +
                            (department.getOrder() == null ? "" : String.valueOf(department.getOrder())) + "\n";

                    IOUtils.write(line, os);
                }
            }

            //上传
            MultipartFile multi = new CommonsMultipartFile(fileItem);
            String mediaId = mediaService.uploadMaterial(multi, MediaService.FILE, applicationName);
            AsyncJobRequest request = buildAsyncJobRequest(mediaId, null, callbackData);
            AsyncJobResponse response = weChatClient.fullCoverDepartment(request, applicationName);

            if (isSuccess(response)) {
                return Objects.nonNull(response) ? response.getJobId() : "";
            }
        } catch (Exception e) {
            logger.error("全量覆盖部门异常:{}", applicationName, e);
        }

        return null;
    }

    public String fullCoverDepartment(List<Department> departments, String applicationName) {
        return fullCoverDepartment(departments, null, applicationName);
    }


    /**
     * 获取job执行结果
     *
     * @param jobId
     * @param applicationName
     * @return
     */
    public AsyncJobResultResponse jobResult(String jobId, String applicationName) {
        AsyncJobResultResponse resultResponse = null;
        if (StringUtils.isNotEmpty(jobId)) {
            resultResponse = weChatClient.jobResult(jobId, applicationName);
            if (isSuccess(resultResponse)) {
                logger.info("获取任务执行结果成功：{}", resultResponse);
            }
        }

        return resultResponse;
    }
}
