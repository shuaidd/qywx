package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UpdateInterceptRuleRequest {

    /**
     * ruleId
     */
    @JsonProperty("rule_id")
    private String ruleId;
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
     * extraRule
     */
    @JsonProperty("extra_rule")
    private ExtraRuleDTO extraRule;
    /**
     * interceptType
     */
    @JsonProperty("intercept_type")
    private Integer interceptType;
    /**
     * addApplicableRange
     */
    @JsonProperty("add_applicable_range")
    private AddApplicableRangeDTO addApplicableRange;
    /**
     * removeApplicableRange
     */
    @JsonProperty("remove_applicable_range")
    private RemoveApplicableRangeDTO removeApplicableRange;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

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

    public ExtraRuleDTO getExtraRule() {
        return extraRule;
    }

    public void setExtraRule(ExtraRuleDTO extraRule) {
        this.extraRule = extraRule;
    }

    public Integer getInterceptType() {
        return interceptType;
    }

    public void setInterceptType(Integer interceptType) {
        this.interceptType = interceptType;
    }

    public AddApplicableRangeDTO getAddApplicableRange() {
        return addApplicableRange;
    }

    public void setAddApplicableRange(AddApplicableRangeDTO addApplicableRange) {
        this.addApplicableRange = addApplicableRange;
    }

    public RemoveApplicableRangeDTO getRemoveApplicableRange() {
        return removeApplicableRange;
    }

    public void setRemoveApplicableRange(RemoveApplicableRangeDTO removeApplicableRange) {
        this.removeApplicableRange = removeApplicableRange;
    }

    public static class ExtraRuleDTO {
        /**
         * semanticsList
         */
        @JsonProperty("semantics_list")
        private List<Integer> semanticsList;

        public List<Integer> getSemanticsList() {
            return semanticsList;
        }

        public void setSemanticsList(List<Integer> semanticsList) {
            this.semanticsList = semanticsList;
        }
    }

    public static class AddApplicableRangeDTO {
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

    public static class RemoveApplicableRangeDTO {
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
