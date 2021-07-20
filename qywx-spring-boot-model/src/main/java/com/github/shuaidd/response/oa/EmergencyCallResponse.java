package com.github.shuaidd.response.oa;

import com.github.shuaidd.dto.oa.EmergencyCallState;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-20 13:34
 **/
public class EmergencyCallResponse extends AbstractBaseResponse {

    private List<EmergencyCallState> states;

    public List<EmergencyCallState> getStates() {
        return states;
    }

    public void setStates(List<EmergencyCallState> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EmergencyCallResponse.class.getSimpleName() + "[", "]")
                .add("states=" + states)
                .toString();
    }
}
