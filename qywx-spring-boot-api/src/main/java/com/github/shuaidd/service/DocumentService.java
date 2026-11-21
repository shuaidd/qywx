package com.github.shuaidd.service;

import com.github.shuaidd.client.WechatDocClient;
import com.github.shuaidd.dto.wedoc.FormInfo;
import com.github.shuaidd.dto.wedoc.FormQuestionItem;
import com.github.shuaidd.dto.wedoc.SmartDocPageInfo;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.wedoc.*;
import com.github.shuaidd.resquest.wedoc.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 文档管理
 *
 * @author ddshuai
 */
@Service
public class DocumentService extends AbstractBaseService {

    /**
     * 全量修改收集表问题
     */
    private static final int OPER_MODIFY_QUESTION = 1;

    /**
     * 全量修改收集表设置
     */
    private static final int OPER_MODIFY_SETTING = 2;

    /**
     * 收集表问题最大数量
     */
    private static final int MAX_FORM_QUESTION_COUNT = 200;

    /**
     * 收集表分页最大数量
     */
    private static final int MAX_FORM_STATISTIC_LIMIT = 10000;

    /**
     * 收集表答案单批最大数量
     */
    private static final int MAX_FORM_ANSWER_ID_COUNT = 100;

    /**
     * 仅查询收集表统计结果
     */
    private static final int FORM_STATISTIC_TYPE_ONLY = 1;

    /**
     * 查询收集表已提交列表
     */
    private static final int FORM_STATISTIC_TYPE_SUBMITTED = 2;

    /**
     * 查询收集表未提交列表
     */
    private static final int FORM_STATISTIC_TYPE_UNSUBMITTED = 3;

    /**
     * 智能文档默认布局
     */
    private static final int SMART_DOC_PAGE_LAYOUT_DEFAULT = 1;

    /**
     * 智能文档纸张布局
     */
    private static final int SMART_DOC_PAGE_LAYOUT_PAPER = 2;

    /**
     * 智能文档全宽布局
     */
    private static final int SMART_DOC_PAGE_LAYOUT_FULL_WIDTH = 3;

    private final WechatDocClient wechatDocClient;

    /**
     * DocumentService
     * @param wechatDocClient client
     */
    public DocumentService(WechatDocClient wechatDocClient) {
        this.wechatDocClient = wechatDocClient;
    }

    /**
     * 获取文档数据
     *
     * @param docId       document id
     * @param application application
     * @return res
     */
    public DocumentContentResponse getDocContent(String docId, String application) {
        DocIdRequest request = new DocIdRequest();
        request.setDocId(docId);
        if (StringUtils.isEmpty(docId)) {
            throw new ParamCheckException("docId 不能为空，请检查！！！");
        }
        return wechatDocClient.getDocumentContent(request, application);
    }

    /**
     * 新建文档
     *
     * @param request     request
     * @param application application
     * @return res
     */
    public CreateDocResponse createDocument(CreateDocRequest request, String application) {
        return wechatDocClient.createDoc(request, application);
    }

    /**
     * 重命名文档
     *
     * @param request     request
     * @param application application
     */
    public void renameDoc(RenameDocRequest request, String application) {
        wechatDocClient.renameDoc(request, application);
    }

    /**
     * 删除文档
     *
     * @param request     request
     * @param application application
     */
    public void delDoc(DocIdFormIdRequest request, String application) {
        wechatDocClient.delDoc(request, application);
    }

    /**
     * 获取文档基础信息
     *
     * @param docId     docId
     * @param application application
     */
    public GetDocBaseResponse getDocBaseInfo(String docId, String application) {
        DocIdRequest request = new DocIdRequest();
        request.setDocId(docId);
        return wechatDocClient.getDocBaseInfo(request, application);
    }

    /**
     * 分享文档
     *
     * @param request     request
     * @param application application
     */
    public ShareDocResponse shareDoc(DocIdFormIdRequest request, String application) {
        return wechatDocClient.shareDoc(request, application);
    }

    /**
     * 获取表格行列信息
     *
     * @param docId       docId
     * @param application application
     * @return props
     */
    public GetSheetPropertiesResponse getSheetProps(String docId, String application) {
        DocIdRequest request = new DocIdRequest();
        request.setDocId(docId);
        if (StringUtils.isEmpty(docId)) {
            throw new ParamCheckException("docId 不能为空，请检查！！！");
        }
        return wechatDocClient.getSheetProps(request, application);
    }

    /**
     * 获取表格数据
     *
     * @param request     request
     * @param application application
     * @return props
     */
    public SheetDataResponse getSheetData(SheetDataRequest request, String application) {
        return wechatDocClient.getSheetData(request, application);
    }

    /**
     * 编辑文档内容
     *
     * @param request     request
     * @param application application
     * @return props
     */
    public BaseResponse batchUpdate(BatchUpdateDocRequest request, String application) {
        return wechatDocClient.batchUpdate(request, application);
    }

    /**
     * 编辑表格内容
     *
     * @param request     request
     * @param application application
     * @return props
     */
    public UpdateSheetResponse batchUpdateSheet(BatchUpdateSheetRequest request, String application) {
        return wechatDocClient.batchUpdateSheet(request, application);
    }

    /**
     * 获取文档权限信息
     *
     * @param docId       docId
     * @param application application
     * @return props
     */
    public DocAuthResponse getDocAuth(String docId, String application) {
        DocIdRequest request = new DocIdRequest();
        request.setDocId(docId);
        return wechatDocClient.getDocAuth(request, application);
    }

    /**
     * 修改文档查看规则
     *
     * @param request     request
     * @param application application
     */
    public void modDocAuth(ModDocRuleRequest request, String application) {
        wechatDocClient.modDocAuth(request, application);
    }

    /**
     * 修改文档通知范围及权限
     *
     * @param request     request
     * @param application application
     */
    public void modDocMember(ModDocMemberRequest request, String application) {
        wechatDocClient.modDocMember(request, application);
    }

    /**
     * 修改文档安全设置
     *
     * @param request     request
     * @param application application
     */
    public void modDocSafetySetting(ModDocSafetySettingRequest request, String application) {
        wechatDocClient.modDocSafetySetting(request, application);
    }


    /**
     * 创建收集表
     *
     * @param request     request
     * @param application application
     * @return res
     */
    public CreateFormResponse createForm(CreateFormRequest request, String application) {
        checkFormInfo(request);
        return wechatDocClient.createForm(request, application);
    }

    /**
     * 编辑收集表
     *
     * @param request     request
     * @param application application
     * @return res
     */
    public BaseResponse modifyForm(ModifyFormRequest request, String application) {
        if (Objects.isNull(request) || Objects.isNull(request.getOper())
                || (request.getOper() != OPER_MODIFY_QUESTION && request.getOper() != OPER_MODIFY_SETTING)) {
            throw new ParamCheckException("oper 只能为1或2，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getFormId())) {
            throw new ParamCheckException("formid 不能为空，请检查！！！");
        }
        if (OPER_MODIFY_QUESTION == request.getOper()) {
            checkFormInfo(request);
        } else if (Objects.isNull(request.getFormInfo()) || Objects.isNull(request.getFormInfo().getFormSetting())
                || Objects.isNull(request.getFormInfo().getFormSetting().getFillOutAuth())) {
            throw new ParamCheckException("formSetting.fillOutAuth 不能为空，请检查！！！");
        }
        return wechatDocClient.modifyForm(request, application);
    }

    /**
     * 获取收集表信息
     *
     * @param request     request
     * @param application application
     * @return res
     */
    public GetFormInfoResponse getFormInfo(FormIdRequest request, String application) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getFormId())) {
            throw new ParamCheckException("formid 不能为空，请检查！！！");
        }
        return wechatDocClient.getFormInfo(request, application);
    }

    /**
     * 查询收集表统计信息
     *
     * @param request     request
     * @param application application
     * @return res
     */
    public FormStatisticResponse getFormStatistic(FormStatisticRequest request, String application) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getRepeatedId())) {
            throw new ParamCheckException("repeated_id 不能为空，请检查！！！");
        }
        if (Objects.isNull(request.getReqType())
                || (request.getReqType() != FORM_STATISTIC_TYPE_ONLY
                && request.getReqType() != FORM_STATISTIC_TYPE_SUBMITTED
                && request.getReqType() != FORM_STATISTIC_TYPE_UNSUBMITTED)) {
            throw new ParamCheckException("req_type 只能为1、2或3，请检查！！！");
        }
        if (FORM_STATISTIC_TYPE_SUBMITTED == request.getReqType()
                && (Objects.isNull(request.getStartTime()) || Objects.isNull(request.getEndTime()))) {
            throw new ParamCheckException("start_time 和 end_time 不能为空，请检查！！！");
        }
        if (Objects.nonNull(request.getLimit()) && request.getLimit() > MAX_FORM_STATISTIC_LIMIT) {
            throw new ParamCheckException("limit 不能大于10000，请检查！！！");
        }
        return wechatDocClient.getFormStatistic(request, application);
    }

    /**
     * 读取收集表答案
     *
     * @param request     request
     * @param application application
     * @return res
     */
    public FormAnswerResponse getFormAnswer(FormAnswerRequest request, String application) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getRepeatedId())) {
            throw new ParamCheckException("repeated_id 不能为空，请检查！！！");
        }
        if (CollectionUtils.isEmpty(request.getAnswerIds())) {
            throw new ParamCheckException("answer_ids 不能为空，请检查！！！");
        }
        if (request.getAnswerIds().size() > MAX_FORM_ANSWER_ID_COUNT) {
            throw new ParamCheckException("answer_ids 数量不能超过100，请检查！！！");
        }
        return wechatDocClient.getFormAnswer(request, application);
    }

    /**
     * 获取智能文档页面结构
     *
     * @param request     request
     * @param application application
     * @return res
     */
    public GetSmartDocPageHierarchyResponse getSmartDocPageHierarchy(DocIdRequest request, String application) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getDocId())) {
            throw new ParamCheckException("docid 不能为空，请检查！！！");
        }
        return wechatDocClient.getSmartDocPageHierarchy(request, application);
    }

    /**
     * 添加智能文档页面
     *
     * @param request     request
     * @param application application
     * @return res
     */
    public SmartDocPageResponse addSmartDocPage(SmartDocPageRequest request, String application) {
        checkSmartDocPage(request, false);
        return wechatDocClient.addSmartDocPage(request, application);
    }

    /**
     * 更新智能文档页面
     *
     * @param request     request
     * @param application application
     * @return res
     */
    public SmartDocPageResponse updateSmartDocPage(SmartDocPageRequest request, String application) {
        checkSmartDocPage(request, true);
        return wechatDocClient.updateSmartDocPage(request, application);
    }

    /**
     * 删除智能文档页面
     *
     * @param request     request
     * @param application application
     * @return res
     */
    public BaseResponse delSmartDocPage(DeleteSmartDocPageRequest request, String application) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getDocId())) {
            throw new ParamCheckException("docid 不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(request.getPageId())) {
            throw new ParamCheckException("page_id 不能为空，请检查！！！");
        }
        return wechatDocClient.delSmartDocPage(request, application);
    }

    /**
     * 校验添加或更新智能文档页面请求
     *
     * @param request request
     * @param update  是否为更新操作
     */
    private void checkSmartDocPage(SmartDocPageRequest request, boolean update) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getDocId())) {
            throw new ParamCheckException("docid 不能为空，请检查！！！");
        }
        if (Objects.isNull(request.getInfo())) {
            throw new ParamCheckException("info 不能为空，请检查！！！");
        }
        if (update && StringUtils.isEmpty(request.getInfo().getPageId())) {
            throw new ParamCheckException("page_id 不能为空，请检查！！！");
        }
        checkSmartDocPageLayoutMode(request.getInfo());
    }

    /**
     * 校验智能文档页面布局模式
     *
     * @param pageInfo pageInfo
     */
    private void checkSmartDocPageLayoutMode(SmartDocPageInfo pageInfo) {
        Integer layoutMode = pageInfo.getLayoutMode();
        if (Objects.nonNull(layoutMode)
                && layoutMode != SMART_DOC_PAGE_LAYOUT_DEFAULT
                && layoutMode != SMART_DOC_PAGE_LAYOUT_PAPER
                && layoutMode != SMART_DOC_PAGE_LAYOUT_FULL_WIDTH) {
            throw new ParamCheckException("layout_mode 只能为1、2或3，请检查！！！");
        }
    }

    /**
     * 校验创建收集表请求
     *
     * @param request request
     */
    private void checkFormInfo(CreateFormRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("formInfo 不能为空，请检查！！！");
        }
        checkFormInfo(request.getFormInfo());
    }

    /**
     * 校验全量修改收集表问题请求
     *
     * @param request request
     */
    private void checkFormInfo(ModifyFormRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("formInfo 不能为空，请检查！！！");
        }
        checkFormInfo(request.getFormInfo());
    }

    /**
     * 校验创建或全量修改问题时的收集表信息
     *
     * @param formInfo formInfo
     */
    private void checkFormInfo(FormInfo formInfo) {
        if (Objects.isNull(formInfo)) {
            throw new ParamCheckException("formInfo 不能为空，请检查！！！");
        }
        if (StringUtils.isEmpty(formInfo.getFormTitle())) {
            throw new ParamCheckException("formInfo.formTitle 不能为空，请检查！！！");
        }
        if (Objects.isNull(formInfo.getFormQuestion()) || CollectionUtils.isEmpty(formInfo.getFormQuestion().getItems())) {
            throw new ParamCheckException("formInfo.formQuestion.items 不能为空，请检查！！！");
        }
        if (formInfo.getFormQuestion().getItems().size() > MAX_FORM_QUESTION_COUNT) {
            throw new ParamCheckException("formInfo.formQuestion.items 数量不能超过200，请检查！！！");
        }
        checkFormQuestions(formInfo.getFormQuestion().getItems());
    }

    /**
     * 校验收集表问题
     *
     * @param questions questions
     */
    private void checkFormQuestions(List<FormQuestionItem> questions) {
        for (FormQuestionItem question : questions) {
            if (Objects.isNull(question) || Objects.isNull(question.getQuestionId())) {
                throw new ParamCheckException("questionId 不能为空，请检查！！！");
            }
            if (StringUtils.isEmpty(question.getTitle())) {
                throw new ParamCheckException("title 不能为空，请检查！！！");
            }
            if (Objects.isNull(question.getPos())) {
                throw new ParamCheckException("pos 不能为空，请检查！！！");
            }
            if (Objects.isNull(question.getStatus())) {
                throw new ParamCheckException("status 不能为空，请检查！！！");
            }
            if (Objects.isNull(question.getReplyType())) {
                throw new ParamCheckException("replyType 不能为空，请检查！！！");
            }
            if (Objects.isNull(question.getMustReply())) {
                throw new ParamCheckException("mustReply 不能为空，请检查！！！");
            }
        }
    }
}
