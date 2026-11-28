package com.github.shuaidd.response.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 公共邮箱客户端专用密码列表响应。
 */
public class AuthCodeListResponse extends AbstractBaseResponse {

    /**
     * 客户端专用密码列表
     */
    @JsonProperty("auth_code_list")
    private List<AuthCodeDTO> authCodeList;

    public List<AuthCodeDTO> getAuthCodeList() {
        return authCodeList;
    }

    public void setAuthCodeList(List<AuthCodeDTO> authCodeList) {
        this.authCodeList = authCodeList;
    }

    /**
     * 客户端专用密码信息
     */
    public static class AuthCodeDTO {

        /**
         * 客户端专用密码 ID
         */
        @JsonProperty("auth_code_id")
        private Integer authCodeId;

        /**
         * 客户端专用密码创建时间戳
         */
        @JsonProperty("create_time")
        private Integer createTime;

        /**
         * 客户端专用密码最后使用时间戳，未使用过为 0
         */
        @JsonProperty("last_use_time")
        private Integer lastUseTime;

        /**
         * 客户端专用密码备注
         */
        @JsonProperty("remark")
        private String remark;

        public Integer getAuthCodeId() {
            return authCodeId;
        }

        public void setAuthCodeId(Integer authCodeId) {
            this.authCodeId = authCodeId;
        }

        public Integer getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Integer createTime) {
            this.createTime = createTime;
        }

        public Integer getLastUseTime() {
            return lastUseTime;
        }

        public void setLastUseTime(Integer lastUseTime) {
            this.lastUseTime = lastUseTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
