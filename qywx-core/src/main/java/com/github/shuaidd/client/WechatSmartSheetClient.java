package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.smartsheet.AddFieldGroupResponse;
import com.github.shuaidd.response.smartsheet.AddFieldResponse;
import com.github.shuaidd.response.smartsheet.AddRecordResponse;
import com.github.shuaidd.response.smartsheet.AddSheetResponse;
import com.github.shuaidd.response.smartsheet.AddViewResponse;
import com.github.shuaidd.response.smartsheet.AutomationGroupChatListResponse;
import com.github.shuaidd.response.smartsheet.CreateRuleResponse;
import com.github.shuaidd.response.smartsheet.GetFieldGroupsResponse;
import com.github.shuaidd.response.smartsheet.GetFieldResponse;
import com.github.shuaidd.response.smartsheet.GetRecordResponse;
import com.github.shuaidd.response.smartsheet.GetSheetPrivResponse;
import com.github.shuaidd.response.smartsheet.GetSheetResponse;
import com.github.shuaidd.response.smartsheet.GetViewResponse;
import com.github.shuaidd.response.smartsheet.UpdateFieldGroupResponse;
import com.github.shuaidd.response.smartsheet.UpdateFieldResponse;
import com.github.shuaidd.response.smartsheet.UpdateRecordResponse;
import com.github.shuaidd.response.smartsheet.UpdateViewResponse;
import com.github.shuaidd.resquest.smartsheet.AddFieldGroupRequest;
import com.github.shuaidd.resquest.smartsheet.AddFieldRequest;
import com.github.shuaidd.resquest.smartsheet.AddRecordRequest;
import com.github.shuaidd.resquest.smartsheet.AddSheetRequest;
import com.github.shuaidd.resquest.smartsheet.AddViewRequest;
import com.github.shuaidd.resquest.smartsheet.AutomationGroupChatListRequest;
import com.github.shuaidd.resquest.smartsheet.CreateRuleRequest;
import com.github.shuaidd.resquest.smartsheet.DeleteFieldGroupsRequest;
import com.github.shuaidd.resquest.smartsheet.DeleteFieldRequest;
import com.github.shuaidd.resquest.smartsheet.DeleteRuleRequest;
import com.github.shuaidd.resquest.smartsheet.DeleteSheetRequest;
import com.github.shuaidd.resquest.smartsheet.DeleteViewRequest;
import com.github.shuaidd.resquest.smartsheet.DelRecordRequest;
import com.github.shuaidd.resquest.smartsheet.GetFieldGroupsRequest;
import com.github.shuaidd.resquest.smartsheet.GetFieldRequest;
import com.github.shuaidd.resquest.smartsheet.GetRecordRequest;
import com.github.shuaidd.resquest.smartsheet.GetSheetPrivRequest;
import com.github.shuaidd.resquest.smartsheet.GetSheetRequest;
import com.github.shuaidd.resquest.smartsheet.GetViewRequest;
import com.github.shuaidd.resquest.smartsheet.ModRuleMemberRequest;
import com.github.shuaidd.resquest.smartsheet.UpdateFieldGroupRequest;
import com.github.shuaidd.resquest.smartsheet.UpdateFieldRequest;
import com.github.shuaidd.resquest.smartsheet.UpdateRecordRequest;
import com.github.shuaidd.resquest.smartsheet.UpdateSheetPrivRequest;
import com.github.shuaidd.resquest.smartsheet.UpdateSheetRequest;
import com.github.shuaidd.resquest.smartsheet.UpdateViewRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 智能表格
 */
public interface WechatSmartSheetClient extends CommonClient {

    /**
     * 添加子表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/add_sheet?app={app}")
    @Headers(HEAD)
    AddSheetResponse addSheet(AddSheetRequest request, @Param("app") String app);

    /**
     * 删除子表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/delete_sheet?app={app}")
    @Headers(HEAD)
    BaseResponse delSheet(DeleteSheetRequest request, @Param("app") String app);

    /**
     * 更新子表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/update_sheet?app={app}")
    @Headers(HEAD)
    BaseResponse updateSheet(UpdateSheetRequest request, @Param("app") String app);

    /**
     * 添加视图
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/add_view?app={app}")
    @Headers(HEAD)
    AddViewResponse addView(AddViewRequest request, @Param("app") String app);

    /**
     * 删除视图
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/delete_views?app={app}")
    @Headers(HEAD)
    BaseResponse delView(DeleteViewRequest request, @Param("app") String app);


    /**
     * 更新视图
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/update_view?app={app}")
    @Headers(HEAD)
    UpdateViewResponse updateView(UpdateViewRequest request, @Param("app") String app);

    /**
     * 添加字段
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/add_fields?app={app}")
    @Headers(HEAD)
    AddFieldResponse addField(AddFieldRequest request, @Param("app") String app);

    /**
     * 更新字段
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/update_fields?app={app}")
    @Headers(HEAD)
    UpdateFieldResponse updateField(UpdateFieldRequest request, @Param("app") String app);

    /**
     * 删除字段
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/delete_fields?app={app}")
    @Headers(HEAD)
    BaseResponse delField(DeleteFieldRequest request, @Param("app") String app);

    /**
     * 添加编组
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/add_field_group?app={app}")
    @Headers(HEAD)
    AddFieldGroupResponse addFieldGroup(AddFieldGroupRequest request, @Param("app") String app);

    /**
     * 更新编组
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/update_field_group?app={app}")
    @Headers(HEAD)
    UpdateFieldGroupResponse updateFieldGroup(UpdateFieldGroupRequest request, @Param("app") String app);

    /**
     * 删除编组
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/delete_field_groups?app={app}")
    @Headers(HEAD)
    BaseResponse delFieldGroups(DeleteFieldGroupsRequest request, @Param("app") String app);

    /**
     * 获取编组
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/get_field_groups?app={app}")
    @Headers(HEAD)
    GetFieldGroupsResponse getFieldGroups(GetFieldGroupsRequest request, @Param("app") String app);

    /**
     * 添加记录
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/add_records?app={app}")
    @Headers(HEAD)
    AddRecordResponse addRecords(AddRecordRequest request, @Param("app") String app);

    /**
     * 更新记录
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/update_records?app={app}")
    @Headers(HEAD)
    UpdateRecordResponse updateRecords(UpdateRecordRequest request, @Param("app") String app);

    /**
     * 删除记录
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/delete_records?app={app}")
    @Headers(HEAD)
    BaseResponse delRecords(DelRecordRequest request, @Param("app") String app);

    /**
     * 查询子表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/get_sheet?app={app}")
    @Headers(HEAD)
    GetSheetResponse getSheet(GetSheetRequest request, @Param("app") String app);

    /**
     * 查询视图
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/get_views?app={app}")
    @Headers(HEAD)
    GetViewResponse getViews(GetViewRequest request, @Param("app") String app);


    /**
     * 查询字段
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/get_fields?app={app}")
    @Headers(HEAD)
    GetFieldResponse getFields(GetFieldRequest request, @Param("app") String app);

    /**
     * 查询记录
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/get_records?app={app}")
    @Headers(HEAD)
    GetRecordResponse getRecords(GetRecordRequest request, @Param("app") String app);

    /**
     * 查询智能表格子表权限
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/content_priv/get_sheet_priv?app={app}")
    @Headers(HEAD)
    GetSheetPrivResponse getSheetPriv(GetSheetPrivRequest request, @Param("app") String app);

    /**
     * 更新智能表格子表权限
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/content_priv/update_sheet_priv?app={app}")
    @Headers(HEAD)
    BaseResponse updateSheetPriv(UpdateSheetPrivRequest request, @Param("app") String app);

    /**
     * 新增智能表格指定成员额外权限
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/content_priv/create_rule?app={app}")
    @Headers(HEAD)
    CreateRuleResponse createRule(CreateRuleRequest request, @Param("app") String app);

    /**
     * 更新智能表格指定成员额外权限
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/content_priv/mod_rule_member?app={app}")
    @Headers(HEAD)
    BaseResponse modRuleMember(ModRuleMemberRequest request, @Param("app") String app);

    /**
     * 删除智能表格指定成员额外权限
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/content_priv/delete_rule?app={app}")
    @Headers(HEAD)
    BaseResponse delRule(DeleteRuleRequest request, @Param("app") String app);

    /**
     * 查询智能表格自动化创建的群聊列表
     *
     * @param request req
     * @param app     app
     * @return res
     */
    @RequestLine("POST /wedoc/smartsheet/groupchat/list?app={app}")
    @Headers(HEAD)
    AutomationGroupChatListResponse getAutomationGroupChatList(AutomationGroupChatListRequest request, @Param("app") String app);
}
