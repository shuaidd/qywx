package com.github.shuaidd.service;

import com.github.shuaidd.QywxContext;
import com.github.shuaidd.client.WechatDocClient;
import com.github.shuaidd.dto.smartsheet.FieldGroupChild;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.client.WechatSmartSheetClient;
import com.github.shuaidd.response.smartsheet.*;
import com.github.shuaidd.response.wedoc.CreateDocResponse;
import com.github.shuaidd.resquest.smartsheet.*;
import com.github.shuaidd.resquest.wedoc.CreateDocRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

public class SmartSheetService extends AbstractBaseService {

    private final WechatSmartSheetClient smartSheetClient;
    private final WechatDocClient wechatDocClient;

    public SmartSheetService(QywxContext context, WechatSmartSheetClient smartSheetClient, WechatDocClient wechatDocClient) {
        super(context);
        this.smartSheetClient = smartSheetClient;
        this.wechatDocClient = wechatDocClient;
    }

    /**
     * 创建智能表格
     * @param request req
     * @param app app
     * @return CreateDocResponse
     */
    public CreateDocResponse createSmartSheet(CreateDocRequest request, String app){
        if (Objects.nonNull(request)) {
            request.setDocType(10);
        }
        return wechatDocClient.createDoc(request, app);
    }

    /**
     * 添加子表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public AddSheetResponse addSheet(AddSheetRequest request, String app) {
        return smartSheetClient.addSheet(request, app);
    }

    /**
     * 删除子表
     *
     * @param request req
     * @param app     app
     */
    public void delSheet(DeleteSheetRequest request, String app) {
        smartSheetClient.delSheet(request, app);
    }

    /**
     * 更新子表
     *
     * @param request req
     * @param app     app
     */
    public void updateSheet(UpdateSheetRequest request, String app) {
        smartSheetClient.updateSheet(request, app);
    }

    /**
     * 添加视图
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public AddViewResponse addView(AddViewRequest request, String app) {
        return smartSheetClient.addView(request, app);
    }

    /**
     * 删除视图
     *
     * @param request req
     * @param app     app
     */
    public void delView(DeleteViewRequest request, String app) {
        smartSheetClient.delView(request, app);
    }

    /**
     * 更新视图
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public UpdateViewResponse updateView(UpdateViewRequest request, String app) {
        return smartSheetClient.updateView(request, app);
    }

    /**
     * 添加字段
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public AddFieldResponse addField(AddFieldRequest request, String app) {
        return smartSheetClient.addField(request, app);
    }

    /**
     * 更新字段
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public UpdateFieldResponse updateField(UpdateFieldRequest request, String app) {
        return smartSheetClient.updateField(request, app);
    }

    /**
     * 删除字段
     *
     * @param request req
     * @param app     app
     */
    public void delField(DeleteFieldRequest request, String app) {
        smartSheetClient.delField(request, app);
    }

    /**
     * 添加编组
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public AddFieldGroupResponse addFieldGroup(AddFieldGroupRequest request, String app) {
        checkFieldGroupBase(request);
        if (StringUtils.isEmpty(request.getName())) {
            throw new ParamCheckException("编组名称不能为空，请检查！！！");
        }
        checkFieldGroupChildren(request.getChildren());
        return smartSheetClient.addFieldGroup(request, app);
    }

    /**
     * 更新编组
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public UpdateFieldGroupResponse updateFieldGroup(UpdateFieldGroupRequest request, String app) {
        checkFieldGroupBase(request);
        if (StringUtils.isEmpty(request.getFieldGroupId())) {
            throw new ParamCheckException("field_group_id 不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getName()) && CollectionUtils.isEmpty(request.getChildren())) {
            throw new ParamCheckException("编组名称和字段列表至少填写一项，请检查！！！");
        }
        checkFieldGroupChildren(request.getChildren());
        return smartSheetClient.updateFieldGroup(request, app);
    }

    /**
     * 删除编组
     *
     * @param request req
     * @param app     app
     */
    public void delFieldGroups(DeleteFieldGroupsRequest request, String app) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getDocId())) {
            throw new ParamCheckException("docid 不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getSheetId())) {
            throw new ParamCheckException("sheet_id 不能为空，请检查！！！");
        }
        if (CollectionUtils.isEmpty(request.getFieldGroupIds())) {
            throw new ParamCheckException("field_group_ids 不能为空，请检查！！！");
        }
        smartSheetClient.delFieldGroups(request, app);
    }

    /**
     * 获取编组
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public GetFieldGroupsResponse getFieldGroups(GetFieldGroupsRequest request, String app) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getDocId())) {
            throw new ParamCheckException("docid 不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getSheetId())) {
            throw new ParamCheckException("sheet_id 不能为空，请检查！！！");
        }
        if (Objects.nonNull(request.getOffset()) && request.getOffset() < 0) {
            throw new ParamCheckException("offset 不能小于0，请检查！！！");
        }
        if (Objects.nonNull(request.getLimit()) && request.getLimit() < 1) {
            throw new ParamCheckException("limit 最小为1，请检查！！！");
        }
        return smartSheetClient.getFieldGroups(request, app);
    }

    private void checkFieldGroupBase(AddFieldGroupRequest request) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getDocId())) {
            throw new ParamCheckException("docid 不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getSheetId())) {
            throw new ParamCheckException("sheet_id 不能为空，请检查！！！");
        }
    }

    private void checkFieldGroupBase(UpdateFieldGroupRequest request) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getDocId())) {
            throw new ParamCheckException("docid 不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getSheetId())) {
            throw new ParamCheckException("sheet_id 不能为空，请检查！！！");
        }
    }

    private void checkFieldGroupChildren(List<FieldGroupChild> children) {
        if (CollectionUtils.isEmpty(children)) {
            return;
        }
        if (children.size() > 150) {
            throw new ParamCheckException("编组最多支持150个字段，请检查！！！");
        }
        for (FieldGroupChild child : children) {
            if (Objects.isNull(child) || StringUtils.isEmpty(child.getFieldId())) {
                throw new ParamCheckException("children.field_id 不能为空，请检查！！！");
            }
        }
    }

    /**
     * 添加记录
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public AddRecordResponse addRecords(AddRecordRequest request, String app) {
        return smartSheetClient.addRecords(request, app);
    }

    /**
     * 更新记录
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public UpdateRecordResponse updateRecords(UpdateRecordRequest request, String app) {
        return smartSheetClient.updateRecords(request, app);
    }

    /**
     * 删除记录
     *
     * @param request req
     * @param app     app
     */
    public void delRecords(DelRecordRequest request, String app) {
        smartSheetClient.delRecords(request, app);
    }

    /**
     * 查询子表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public GetSheetResponse getSheet(GetSheetRequest request, String app) {
        return smartSheetClient.getSheet(request, app);
    }

    /**
     * 查询视图
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public GetViewResponse getViews(GetViewRequest request, String app) {
        return smartSheetClient.getViews(request, app);
    }

    /**
     * 查询字段
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public GetFieldResponse getFields(GetFieldRequest request, String app) {
        return smartSheetClient.getFields(request, app);
    }

    /**
     * 查询记录
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public GetRecordResponse getRecords(GetRecordRequest request, String app) {
        return smartSheetClient.getRecords(request, app);
    }

    /**
     * 查询智能表格子表权限
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public GetSheetPrivResponse getSheetPriv(GetSheetPrivRequest request, String app) {
        return smartSheetClient.getSheetPriv(request, app);
    }

    /**
     * 更新智能表格子表权限
     *
     * @param request req
     * @param app     app
     */
    public void updateSheetPriv(UpdateSheetPrivRequest request, String app) {
        smartSheetClient.updateSheetPriv(request, app);
    }

    /**
     * 新增智能表格指定成员额外权限
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public CreateRuleResponse createRule(CreateRuleRequest request, String app) {
        return smartSheetClient.createRule(request, app);
    }

    /**
     * 更新智能表格指定成员额外权限
     *
     * @param request req
     * @param app     app
     */
    public void modRuleMember(ModRuleMemberRequest request, String app) {
        smartSheetClient.modRuleMember(request, app);
    }

    /**
     * 删除智能表格指定成员额外权限
     *
     * @param request req
     * @param app     app
     */
    public void delRule(DeleteRuleRequest request, String app) {
        smartSheetClient.delRule(request, app);
    }

    /**
     * 查询智能表格自动化创建的群聊列表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    public AutomationGroupChatListResponse getAutomationGroupChatList(AutomationGroupChatListRequest request, String app) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getDocId())) {
            throw new ParamCheckException("docid 不能为空，请检查！！！");
        }
        if (Objects.nonNull(request.getLimit()) && (request.getLimit() < 1 || request.getLimit() > 200)) {
            throw new ParamCheckException("limit 最小为1，最大为200，请检查！！！");
        }
        return smartSheetClient.getAutomationGroupChatList(request, app);
    }
}
