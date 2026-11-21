package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 客户朋友圈规则组权限配置
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentStrategyPrivilege {

    /**
     * 是否允许查看客户朋友圈发表记录
     */
    @JsonProperty("view_moment_list")
    private Boolean viewMomentList;

    /**
     * 是否允许发表客户朋友圈
     */
    @JsonProperty("send_moment")
    private Boolean sendMoment;

    /**
     * 是否允许管理客户朋友圈封面和签名
     */
    @JsonProperty("manage_moment_cover_and_sign")
    private Boolean manageMomentCoverAndSign;

    public Boolean getViewMomentList() {
        return viewMomentList;
    }

    public void setViewMomentList(Boolean viewMomentList) {
        this.viewMomentList = viewMomentList;
    }

    public Boolean getSendMoment() {
        return sendMoment;
    }

    public void setSendMoment(Boolean sendMoment) {
        this.sendMoment = sendMoment;
    }

    public Boolean getManageMomentCoverAndSign() {
        return manageMomentCoverAndSign;
    }

    public void setManageMomentCoverAndSign(Boolean manageMomentCoverAndSign) {
        this.manageMomentCoverAndSign = manageMomentCoverAndSign;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("viewMomentList", viewMomentList)
                .append("sendMoment", sendMoment)
                .append("manageMomentCoverAndSign", manageMomentCoverAndSign)
                .toString();
    }
}
