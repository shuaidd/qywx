package com.github.shuaidd.service;

import com.github.shuaidd.QywxContext;
import com.github.shuaidd.client.GovernmentCommunicationClient;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.government.CreateGridResponse;
import com.github.shuaidd.response.government.GridListResponse;
import com.github.shuaidd.response.government.UpdateGridResponse;
import com.github.shuaidd.response.government.UserGridInfoResponse;
import com.github.shuaidd.resquest.government.CreateGridRequest;
import com.github.shuaidd.resquest.government.DeleteGridRequest;
import com.github.shuaidd.resquest.government.GridListRequest;
import com.github.shuaidd.resquest.government.UpdateGridRequest;
import com.github.shuaidd.resquest.government.UserGridInfoRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 政民沟通服务。REST 请求前完成网格基础业务校验，真实权限仍由企业微信平台控制。
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class GovernmentCommunicationService extends AbstractBaseService {

    private final GovernmentCommunicationClient governmentCommunicationClient;

    public GovernmentCommunicationService(QywxContext context, GovernmentCommunicationClient governmentCommunicationClient) {
        super(context);
        this.governmentCommunicationClient = governmentCommunicationClient;
    }

    /**
     * 添加网格
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 创建结果
     */
    public CreateGridResponse addGrid(CreateGridRequest request, String applicationName) {
        checkApplication(applicationName);
        checkCreateGrid(request);
        return governmentCommunicationClient.addGrid(request, applicationName);
    }

    /**
     * 编辑网格
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 编辑结果
     */
    public UpdateGridResponse updateGrid(UpdateGridRequest request, String applicationName) {
        checkApplication(applicationName);
        checkUpdateGrid(request);
        checkGridId(request.getGridId(), "grid_id");
        return governmentCommunicationClient.updateGrid(request, applicationName);
    }

    /**
     * 删除网格
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse deleteGrid(DeleteGridRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求不能为空，请检查！！！");
        }
        return governmentCommunicationClient.deleteGrid(request, applicationName);
    }

    /**
     * 获取网格列表
     *
     * @param request         请求；grid_id可选
     * @param applicationName 应用名称
     * @return 网格列表
     */
    public GridListResponse listGrid(GridListRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(request)) {
            request = new GridListRequest();
        }
        return governmentCommunicationClient.listGrid(request, applicationName);
    }

    /**
     * 获取用户负责及参与的网格列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 用户网格关系
     */
    public UserGridInfoResponse getUserGridInfo(UserGridInfoRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getUserid())) {
            throw new ParamCheckException("userid 不能为空，请检查！！！");
        }
        return governmentCommunicationClient.getUserGridInfo(request, applicationName);
    }

    private void checkCreateGrid(CreateGridRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getGridName()) || request.getGridName().length() > 30) {
            throw new ParamCheckException("grid_name 不能为空且长度不能超过30个字符，请检查！！！");
        }
        checkGridId(request.getGridParentId(), "grid_parent_id");
        checkAdmins(request.getGridAdmin());
        checkMembers(request.getGridMember());
    }

    private void checkUpdateGrid(UpdateGridRequest request) {
        checkGridId(request.getGridId(), "grid_id");
        if (StringUtils.isEmpty(request.getGridName()) || request.getGridName().length() > 30) {
            throw new ParamCheckException("grid_name 不能为空且长度不能超过30个字符，请检查！！！");
        }
        checkGridId(request.getGridParentId(), "grid_parent_id");
        checkAdmins(request.getGridAdmin());
        checkMembers(request.getGridMember());
    }

    private void checkAdmins(List<String> admins) {
        if (CollectionUtils.isEmpty(admins) || admins.size() > 20) {
            throw new ParamCheckException("grid_admin 必须包含1~20个账号，请检查！！！");
        }
        for (String admin : admins) {
            checkUserId(admin, "grid_admin 中的userid");
        }
    }

    private void checkMembers(List<String> members) {
        if (Objects.isNull(members)) {
            return;
        }
        if (members.size() > 100) {
            throw new ParamCheckException("grid_member 最多包含100个账号，请检查！！！");
        }
        for (String member : members) {
            checkUserId(member, "grid_member 中的userid");
        }
    }

    private void checkGridId(String gridId, String fieldName) {
        if (StringUtils.isEmpty(gridId)) {
            throw new ParamCheckException(fieldName + " 不能为空，请检查！！！");
        }
    }

    private void checkUserId(String userId, String fieldName) {
        if (StringUtils.isEmpty(userId) || userId.length() > 64) {
            throw new ParamCheckException(fieldName + " 不能为空且长度为1~64个字符，请检查！！！");
        }
    }
}
