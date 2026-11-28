package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.resquest.RequestFilter;

import java.util.List;

/**
 * 描述 1 接口频率限制 600次/分钟
 * 2 请求的参数endtime需要大于startime， 起始时间跨度不能超过31天；
 *
 * @author ddshuai
 * date 2021-07-19 09:36
 **/
public class GetApprovalNoRequest {

    @JsonProperty("starttime")
    private Long startTime;

    @JsonProperty("endtime")
    private Long endTime;

    private Integer cursor;

    private Integer size;

    /**
     * key 筛选类型，包括：
     * template_id - 模板类型/模板id；
     * creator - 申请人；
     * department - 审批单提单者所在部门；
     * sp_status - 审批状态;
     * record_type - 审批单类型属性，1-请假；2-打卡补卡；3-出差；4-外出；5-加班； 6- 调班；7-会议室预定；8-退款审批；9-红包报销审批
     * <p>
     * 注意:
     * 1、仅“部门”支持同时配置多个筛选条件。
     * 2、不同类型的筛选条件之间为“与”的关系，同类型筛选条件之间为“或”的关系
     * 3、record_type筛选类型仅支持2021/05/31以后新提交的审批单，历史单不支持表单类型属性过滤
     * <p>
     * value筛选值，
     * 对应为：template_id-模板id；
     * creator-申请人userid ；
     * department-所在部门id；
     * sp_status-审批单状态（1-审批中；2-已通过；3-已驳回；4-已撤销；6-通过后撤销；7-已删除；10-已支付）
     */
    private List<RequestFilter> filters;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getCursor() {
        return cursor;
    }

    public void setCursor(Integer cursor) {
        this.cursor = cursor;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<RequestFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<RequestFilter> filters) {
        this.filters = filters;
    }
}
