package com.github.shuaidd.dto.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-01 22:08
 **/
public class SpaceAuthItem {

    /**
     * 成员类型 1:个人 2:部门
     */
    private Integer type;

    /**
     * 1:仅下载 2:可编辑 4:可预览 5:可上传下载6:管理员
     * 200:自定义权限 (可上传下载+仅预览权限，都是旧付费企业仅专业版企业可设置，
     * 新付费企业仅userid为专业帐号时才可设置；自定义权限都是仅userid为专业帐号时才可设置)
     */
    private Integer auth;

    @JsonProperty(value = "userid")
    private String userId;

    @JsonProperty(value = "departmentid")
    private Integer departmentId;

    /**
     * 代表权限自定义组合。当auth=200时候填写
     */
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
