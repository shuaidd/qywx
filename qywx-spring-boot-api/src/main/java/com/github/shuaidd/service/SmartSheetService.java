package com.github.shuaidd.service;

import com.github.shuaidd.client.WechatDocClient;
import com.github.shuaidd.client.WechatSmartSheetClient;
import com.github.shuaidd.response.smartsheet.*;
import com.github.shuaidd.response.wedoc.CreateDocResponse;
import com.github.shuaidd.resquest.smartsheet.*;
import com.github.shuaidd.resquest.wedoc.CreateDocRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SmartSheetService extends AbstractBaseService {

    private final WechatSmartSheetClient smartSheetClient;
    private final WechatDocClient wechatDocClient;

    public SmartSheetService(WechatSmartSheetClient smartSheetClient, WechatDocClient wechatDocClient) {
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
}
