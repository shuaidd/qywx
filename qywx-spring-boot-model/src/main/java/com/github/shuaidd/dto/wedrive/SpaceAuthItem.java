package com.github.shuaidd.dto.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-01 22:08
 **/
public class SpaceAuthItem {

    private Integer type;

    private Integer auth;

    @JsonProperty(value = "userid")
    private String userId;

    @JsonProperty(value = "departmentid")
    private Integer departmentId;

    @JsonProperty(value = "customize_auth")
    private CustomizeAuth customizeAuth;

    public static class CustomizeAuth {

        @JsonProperty(value = "enable_operation_upload")
        private Boolean enableOperationUpload;

        @JsonProperty(value = "enable_operation_delete")
        private Boolean enableOperationDelete;

        public Boolean getEnableOperationUpload() {
            return enableOperationUpload;
        }

        public void setEnableOperationUpload(Boolean enableOperationUpload) {
            this.enableOperationUpload = enableOperationUpload;
        }

        public Boolean getEnableOperationDelete() {
            return enableOperationDelete;
        }

        public void setEnableOperationDelete(Boolean enableOperationDelete) {
            this.enableOperationDelete = enableOperationDelete;
        }
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public CustomizeAuth getCustomizeAuth() {
        return customizeAuth;
    }

    public void setCustomizeAuth(CustomizeAuth customizeAuth) {
        this.customizeAuth = customizeAuth;
    }
}
