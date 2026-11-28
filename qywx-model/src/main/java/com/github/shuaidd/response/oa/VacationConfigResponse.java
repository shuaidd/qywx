package com.github.shuaidd.response.oa;

import com.github.shuaidd.dto.oa.VacationConfig;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-19 17:17
 **/
public class VacationConfigResponse extends AbstractBaseResponse {

    private List<VacationConfig> lists;

    public List<VacationConfig> getLists() {
        return lists;
    }

    public void setLists(List<VacationConfig> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", VacationConfigResponse.class.getSimpleName() + "[", "]")
                .add("lists=" + lists)
                .toString();
    }
}
