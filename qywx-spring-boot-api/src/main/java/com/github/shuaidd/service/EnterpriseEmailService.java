package com.github.shuaidd.service;

import com.github.shuaidd.client.EnterpriseEmailClient;
import com.github.shuaidd.response.exmail.CreatePublicMailResponse;
import com.github.shuaidd.response.exmail.MailDetailResponse;
import com.github.shuaidd.response.exmail.MailGroupDetailResponse;
import com.github.shuaidd.response.exmail.SearchMailGroupResponse;
import com.github.shuaidd.response.vip.VipAccountListResponse;
import com.github.shuaidd.response.vip.VipBatchOperateResponse;
import com.github.shuaidd.resquest.exmail.*;
import com.github.shuaidd.resquest.vip.VipAccountListRequest;
import com.github.shuaidd.resquest.vip.VipUserBatchRequest;
import com.github.shuaidd.exception.ParamCheckException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 邮箱及群组管理
 */
@Service
public class EnterpriseEmailService extends AbstractBaseService {

    /**
     * 高级功能账号单次批量操作人数上限
     */
    private static final int MAX_VIP_BATCH_USER_COUNT = 100;

    /**
     * 高级功能账号列表单页数据上限
     */
    private static final int MAX_VIP_LIST_LIMIT = 200;


    private final EnterpriseEmailClient emailClient;

    public EnterpriseEmailService(EnterpriseEmailClient emailClient) {
        this.emailClient = emailClient;
    }

    /**
     * 创建邮件群组
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void createMailGroup(MailGroupRequest request, String applicationName) {
        emailClient.createMailGroup(request, applicationName);
    }

    /**
     * 更新邮件群组
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void updateMailGroup(MailGroupRequest request, String applicationName) {
        emailClient.updateMailGroup(request, applicationName);
    }

    /**
     * 删除邮件群组
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void deleteMailGroup(DeleteMailGroupRequest request, String applicationName) {
        emailClient.deleteMailGroup(request, applicationName);
    }

    /**
     * 获取邮件群组详情
     *
     * @param groupId         群组编号
     * @param applicationName 应用
     * @return 群组详情
     */
    public MailGroupDetailResponse getMailGroupDetail(String groupId, String applicationName) {
        return emailClient.getMailGroupDetail(groupId, applicationName);
    }

    /**
     * @param groupId         群组编号
     * @param fuzzy           1开启模糊搜索，0获取全部邮件群组
     * @param applicationName 应用
     * @return 查询结果
     */
    public SearchMailGroupResponse searchGroup(String groupId, int fuzzy, String applicationName) {
        return emailClient.searchGroup(groupId, fuzzy, applicationName);
    }

    /**
     * 创建业务邮箱
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 业务邮箱ID
     */
    public CreatePublicMailResponse createPublicMail(CreatePublicMailRequest request, String applicationName) {
        return emailClient.createPublicMail(request, applicationName);
    }

    /**
     * 更新业务邮箱
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void updatePublicMail(UpdatePublicMailRequest request, String applicationName) {
        emailClient.updatePublicMail(request, applicationName);
    }

    /**
     * 删除业务邮箱
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void deletePublicMail(DeletePublicMailRequest request, String applicationName) {
        emailClient.deletePublicMail(request, applicationName);
    }

    /**
     * 获取业务邮箱详情
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 详情信息
     */
    public MailDetailResponse getMailDetail(GetMailDetailRequest request, String applicationName) {
        return emailClient.mailDetail(request, applicationName);
    }

    /**
     * 批量分配高级功能账号
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 分配结果
     */
    public VipBatchOperateResponse batchAddVipAccounts(VipUserBatchRequest request, String applicationName) {
        checkApplication(applicationName);
        checkVipBatchRequest(request);
        return emailClient.batchAddVipAccounts(request, applicationName);
    }

    /**
     * 批量撤销高级功能账号
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 撤销分配结果
     */
    public VipBatchOperateResponse batchDeleteVipAccounts(VipUserBatchRequest request, String applicationName) {
        checkApplication(applicationName);
        checkVipBatchRequest(request);
        return emailClient.batchDeleteVipAccounts(request, applicationName);
    }

    /**
     * 获取高级功能账号列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 高级功能账号列表
     */
    public VipAccountListResponse listVipAccounts(VipAccountListRequest request, String applicationName) {
        checkApplication(applicationName);
        checkVipListRequest(request);
        return emailClient.listVipAccounts(request, applicationName);
    }

    /**
     * 校验高级功能账号批量操作请求
     *
     * @param request 请求
     */
    private void checkVipBatchRequest(VipUserBatchRequest request) {
        if (Objects.isNull(request) || CollectionUtils.isEmpty(request.getUseridList())) {
            throw new ParamCheckException("userid_list 不能为空，请检查！！！");
        }
        if (request.getUseridList().size() > MAX_VIP_BATCH_USER_COUNT) {
            throw new ParamCheckException("userid_list 最多100个，请检查！！！");
        }
        for (String userid : request.getUseridList()) {
            if (StringUtils.isBlank(userid)) {
                throw new ParamCheckException("userid_list 不能包含空userid，请检查！！！");
            }
        }
    }

    /**
     * 校验高级功能账号列表查询请求
     *
     * @param request 请求
     */
    private void checkVipListRequest(VipAccountListRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        if (Objects.nonNull(request.getLimit())
                && (request.getLimit() < 1 || request.getLimit() > MAX_VIP_LIST_LIMIT)) {
            throw new ParamCheckException("limit 取值范围为 1~200，请检查！！！");
        }
    }
}
