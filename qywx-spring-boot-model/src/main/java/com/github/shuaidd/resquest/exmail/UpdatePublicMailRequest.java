package com.github.shuaidd.resquest.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UpdatePublicMailRequest {

    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("userid_list")
    private UseridListDTO useridList;

    @JsonProperty("department_list")
    private DepartmentListDTO departmentList;

    @JsonProperty("tag_list")
    private TagListDTO tagList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UseridListDTO getUseridList() {
        return useridList;
    }

    public void setUseridList(UseridListDTO useridList) {
        this.useridList = useridList;
    }

    public DepartmentListDTO getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(DepartmentListDTO departmentList) {
        this.departmentList = departmentList;
    }

    public TagListDTO getTagList() {
        return tagList;
    }

    public void setTagList(TagListDTO tagList) {
        this.tagList = tagList;
    }

    public static class UseridListDTO {

        @JsonProperty("list")
        private List<String> list;

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
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
}
