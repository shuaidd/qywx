package com.github.shuaidd.response.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MailGroupDetailResponse {

    @JsonProperty("groupid")
    private String groupId;

    @JsonProperty("groupname")
    private String groupName;

    @JsonProperty("email_list")
    private EmailListDTO emailList;

    @JsonProperty("tag_list")
    private TagListDTO tagList;

    @JsonProperty("department_list")
    private DepartmentListDTO departmentList;

    @JsonProperty("group_list")
    private GroupListDTO groupList;

    @JsonProperty("allow_type")
    private Integer allowType;

    @JsonProperty("allow_emaillist")
    private AllowEmaillistDTO allowEmaillist;

    @JsonProperty("allow_departmentlist")
    private AllowDepartmentlistDTO allowDepartmentlist;

    @JsonProperty("allow_taglist")
    private AllowTaglistDTO allowTaglist;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public EmailListDTO getEmailList() {
        return emailList;
    }

    public void setEmailList(EmailListDTO emailList) {
        this.emailList = emailList;
    }

    public TagListDTO getTagList() {
        return tagList;
    }

    public void setTagList(TagListDTO tagList) {
        this.tagList = tagList;
    }

    public DepartmentListDTO getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(DepartmentListDTO departmentList) {
        this.departmentList = departmentList;
    }

    public GroupListDTO getGroupList() {
        return groupList;
    }

    public void setGroupList(GroupListDTO groupList) {
        this.groupList = groupList;
    }

    public Integer getAllowType() {
        return allowType;
    }

    public void setAllowType(Integer allowType) {
        this.allowType = allowType;
    }

    public AllowEmaillistDTO getAllowEmaillist() {
        return allowEmaillist;
    }

    public void setAllowEmaillist(AllowEmaillistDTO allowEmaillist) {
        this.allowEmaillist = allowEmaillist;
    }

    public AllowDepartmentlistDTO getAllowDepartmentlist() {
        return allowDepartmentlist;
    }

    public void setAllowDepartmentlist(AllowDepartmentlistDTO allowDepartmentlist) {
        this.allowDepartmentlist = allowDepartmentlist;
    }

    public AllowTaglistDTO getAllowTaglist() {
        return allowTaglist;
    }

    public void setAllowTaglist(AllowTaglistDTO allowTaglist) {
        this.allowTaglist = allowTaglist;
    }

    public static class EmailListDTO {
        @JsonProperty("list")
        private List<String> list;

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }

    public static class TagListDTO {
        @JsonProperty("list")
        private List<Integer> list;

        public List<Integer> getList() {
            return list;
        }

        public void setList(List<Integer> list) {
            this.list = list;
        }
    }

    public static class DepartmentListDTO {
        @JsonProperty("list")
        private List<Integer> list;

        public List<Integer> getList() {
            return list;
        }

        public void setList(List<Integer> list) {
            this.list = list;
        }
    }

    public static class GroupListDTO {
        @JsonProperty("list")
        private List<String> list;

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }

    public static class AllowEmaillistDTO {
        @JsonProperty("list")
        private List<String> list;

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }

    public static class AllowDepartmentlistDTO {
        @JsonProperty("list")
        private List<Integer> list;

        public List<Integer> getList() {
            return list;
        }

        public void setList(List<Integer> list) {
            this.list = list;
        }
    }

    public static class AllowTaglistDTO {
        @JsonProperty("list")
        private List<Integer> list;

        public List<Integer> getList() {
            return list;
        }

        public void setList(List<Integer> list) {
            this.list = list;
        }
    }
}
