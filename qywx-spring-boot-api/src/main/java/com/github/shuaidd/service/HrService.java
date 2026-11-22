package com.github.shuaidd.service;

import com.github.shuaidd.client.HrClient;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.hr.StaffFieldConfigResponse;
import com.github.shuaidd.response.hr.StaffInfoResponse;
import com.github.shuaidd.response.hr.UpdateStaffInfoResponse;
import com.github.shuaidd.resquest.hr.GetStaffInfoRequest;
import com.github.shuaidd.resquest.hr.StaffFieldId;
import com.github.shuaidd.resquest.hr.StaffFieldValue;
import com.github.shuaidd.resquest.hr.StaffGroupDelete;
import com.github.shuaidd.resquest.hr.StaffGroupInsert;
import com.github.shuaidd.resquest.hr.UpdateStaffInfoRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 人事助手花名册服务
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Service
public class HrService extends AbstractBaseService {

    private final HrClient hrClient;

    public HrService(HrClient hrClient) {
        this.hrClient = hrClient;
    }

    /**
     * 获取员工字段配置
     *
     * @param applicationName 应用名称
     * @return 字段配置
     */
    public StaffFieldConfigResponse getStaffFields(String applicationName) {
        checkApplication(applicationName);
        return hrClient.getStaffFields(applicationName);
    }

    /**
     * 获取员工花名册信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 员工花名册信息
     */
    public StaffInfoResponse getStaffInfo(GetStaffInfoRequest request, String applicationName) {
        checkApplication(applicationName);
        checkStaffInfoRequest(request);
        return hrClient.getStaffInfo(request, applicationName);
    }

    /**
     * 更新员工花名册信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 更新结果
     */
    public UpdateStaffInfoResponse updateStaffInfo(UpdateStaffInfoRequest request, String applicationName) {
        checkApplication(applicationName);
        checkUpdateStaffInfoRequest(request);
        return hrClient.updateStaffInfo(request, applicationName);
    }

    private void checkStaffInfoRequest(GetStaffInfoRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        if (StringUtils.isBlank(request.getUserid())) {
            throw new ParamCheckException("userid 不能为空，请检查！！！");
        }
        boolean getAll = Boolean.TRUE.equals(request.getGetAll());
        if (!getAll && CollectionUtils.isEmpty(request.getFieldIds())) {
            throw new ParamCheckException("fieldids 不能为空，请检查！！！");
        }
        if (!getAll && request.getFieldIds().stream().anyMatch(this::isInvalidFieldId)) {
            throw new ParamCheckException("fieldids.fieldid 不能为空，请检查！！！");
        }
    }

    private void checkUpdateStaffInfoRequest(UpdateStaffInfoRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        if (StringUtils.isBlank(request.getUserid())) {
            throw new ParamCheckException("userid 不能为空，请检查！！！");
        }
        List<StaffFieldValue> updateItems = request.getUpdateItems();
        List<StaffGroupDelete> removeItems = request.getRemoveItems();
        List<StaffGroupInsert> insertItems = request.getInsertItems();
        if (CollectionUtils.isEmpty(updateItems)
                && CollectionUtils.isEmpty(removeItems)
                && CollectionUtils.isEmpty(insertItems)) {
            throw new ParamCheckException("update_items、remove_items 和 insert_items 不能同时为空，请检查！！！");
        }
        if (CollectionUtils.isNotEmpty(updateItems) && updateItems.stream().anyMatch(item ->
                Objects.isNull(item) || Objects.isNull(item.getFieldId()))) {
            throw new ParamCheckException("update_items.fieldid 不能为空，请检查！！！");
        }
        if (CollectionUtils.isNotEmpty(removeItems) && removeItems.stream().anyMatch(item ->
                Objects.isNull(item) || Objects.isNull(item.getGroupType()) || Objects.isNull(item.getSubIdx()))) {
            throw new ParamCheckException("remove_items.group_type 和 remove_items.sub_idx 不能为空，请检查！！！");
        }
        if (CollectionUtils.isNotEmpty(insertItems) && insertItems.stream().anyMatch(item ->
                Objects.isNull(item) || Objects.isNull(item.getGroupType()))) {
            throw new ParamCheckException("insert_items.group_type 不能为空，请检查！！！");
        }
    }

    private boolean isInvalidFieldId(StaffFieldId fieldId) {
        return Objects.isNull(fieldId) || Objects.isNull(fieldId.getFieldId());
    }
}
