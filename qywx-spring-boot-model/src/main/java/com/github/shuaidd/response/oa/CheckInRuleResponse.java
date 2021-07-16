package com.github.shuaidd.response.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.checkin.CheckInRule;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-11 12:46
 **/
public class CheckInRuleResponse extends AbstractBaseResponse {

    @JsonProperty("info")
    private List<CheckInRule> checkInRules;

    public List<CheckInRule> getCheckInRules() {
        return checkInRules;
    }

    public void setCheckInRules(List<CheckInRule> checkInRules) {
        this.checkInRules = checkInRules;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CheckInRuleResponse.class.getSimpleName() + "[", "]")
                .add("checkInRules=" + checkInRules)
                .toString();
    }
}
