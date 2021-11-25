package com.github.shuaidd.response.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-11-25 09:43
 **/
public class ServiceStateResponse extends AbstractBaseResponse {

    /**
     * 0	未处理	新会话接入（客户发信咨询）。可选择：1.直接用API自动回复消息。2.放进待接入池等待接待人员接待。3.指定接待人员（接待人员须处于“正在接待”中，下同）进行接待
     * 1	由智能助手接待	可使用API回复消息。可选择转入待接入池或者指定接待人员处理。
     * 2	待接入池排队中	在待接入池中排队等待接待人员接入。可选择转为指定人员接待
     * 3	由人工接待	人工接待中。可选择转接给其他接待人员处理或者结束会话。
     * 4	已结束	会话已经结束或未开始。不允许通过API变更会话状态，客户重新发信咨询后会话状态变为“未处理”。接待人员通过客户端在已结束会话中成功发送消息后，会话状态变为“由人工接待”，此时会产生会话状态变更回调事件（4-重新接入已结束/已转接会话）。
     */
    @JsonProperty("service_state")
    private Integer serviceState;

    @JsonProperty("servicer_userid")
    private String servicerUserId;

    public Integer getServiceState() {
        return serviceState;
    }

    public void setServiceState(Integer serviceState) {
        this.serviceState = serviceState;
    }

    public String getServicerUserId() {
        return servicerUserId;
    }

    public void setServicerUserId(String servicerUserId) {
        this.servicerUserId = servicerUserId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ServiceStateResponse.class.getSimpleName() + "[", "]")
                .add("serviceState=" + serviceState)
                .add("servicerUserId='" + servicerUserId + "'")
                .toString();
    }
}
