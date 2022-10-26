package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddInterceptRuleRequest {

    /**
     * ruleName
     */
    @JsonProperty("rule_name")
    private String ruleName;

    /**
     * wordList
     */
    @JsonProperty("word_list")
    private List<String> wordList;

    /**
     * semanticsList
     */
    @JsonProperty("semantics_list")
    private List<Integer> semanticsList;

    /**
     * interceptType
     */
    @JsonProperty("intercept_type")
    private Integer interceptType;

    /**
     * applicableRange
     */
    @JsonProperty("applicable_range")
    private ApplicableRangeDTO applicableRange;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public List<String> getWordList() {
        return wordList;
    }

    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
    }

    public List<Integer> getSemanticsList() {
        return semanticsList;
    }

    public void setSemanticsList(List<Integer> semanticsList) {
        this.semanticsList = semanticsList;
    }

    public Integer getInterceptType() {
        return interceptType;
    }

    public void setInterceptType(Integer interceptType) {
        this.interceptType = interceptType;
    }

    public ApplicableRangeDTO getApplicableRange() {
        return applicableRange;
    }

    public void setApplicableRange(ApplicableRangeDTO applicableRange) {
        this.applicableRange = applicableRange;
    }

    public static class ApplicableRangeDTO {
        /**
         * userList
         */
        @JsonProperty("user_list")
        private List<String> userList;
        /**
         * departmentList
         */
        @JsonProperty("department_list")
        private List<Integer> departmentList;

        public List<String> getUserList() {
            return userList;
        }

        public void setUserList(List<String> userList) {
            this.userList = userList;
        }

        public List<Integer> getDepartmentList() {
            return departmentList;
        }

        public void setDepartmentList(List<Integer> departmentList) {
            this.departmentList = departmentList;
        }
    }
}
