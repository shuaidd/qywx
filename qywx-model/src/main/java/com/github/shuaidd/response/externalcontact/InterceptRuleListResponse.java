package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.shuaidd.json.Long2DateDeserializer;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.Date;
import java.util.List;

public class InterceptRuleListResponse extends AbstractBaseResponse {

    /**
     * ruleList
     */
    @JsonProperty("rule_list")
    private List<RuleListDTO> ruleList;

    public List<RuleListDTO> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<RuleListDTO> ruleList) {
        this.ruleList = ruleList;
    }

    public static class RuleListDTO {
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
         * createTime
         */
        @JsonProperty("create_time")
        @JsonDeserialize(using = Long2DateDeserializer.class)
        private Date createTime;

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

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
    }
}
