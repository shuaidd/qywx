package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 可在「学校通知-学校应用」使用的家长范围
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SchoolAgentAllowScope {

    /**
     * 可使用该应用的学生范围
     */
    @JsonProperty("students")
    private StudentAllowScope students;

    /**
     * 可使用该应用的部门范围
     */
    @JsonProperty("departments")
    private DepartmentAllowScope departments;

    public StudentAllowScope getStudents() {
        return students;
    }

    public void setStudents(StudentAllowScope students) {
        this.students = students;
    }

    public DepartmentAllowScope getDepartments() {
        return departments;
    }

    public void setDepartments(DepartmentAllowScope departments) {
        this.departments = departments;
    }

    /**
     * 学生范围明细
     */
    public static class StudentAllowScope {

        /**
         * 可使用该应用的学生 userid 列表
         */
        @JsonProperty("userid")
        private List<String> userIds;

        public List<String> getUserIds() {
            return userIds;
        }

        public void setUserIds(List<String> userIds) {
            this.userIds = userIds;
        }
    }

    /**
     * 部门范围明细
     */
    public static class DepartmentAllowScope {

        /**
         * 可使用该应用的家校部门 partyid 列表
         */
        @JsonProperty("partyid")
        private List<Integer> partyIds;

        public List<Integer> getPartyIds() {
            return partyIds;
        }

        public void setPartyIds(List<Integer> partyIds) {
            this.partyIds = partyIds;
        }
    }
}
