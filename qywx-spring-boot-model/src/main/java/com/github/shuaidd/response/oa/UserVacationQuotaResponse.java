package com.github.shuaidd.response.oa;

import com.github.shuaidd.dto.oa.UserVacationQuota;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-19 17:28
 **/
public class UserVacationQuotaResponse extends AbstractBaseResponse {

    private List<UserVacationQuota> lists;

    public List<UserVacationQuota> getLists() {
        return lists;
    }

    public void setLists(List<UserVacationQuota> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserVacationQuotaResponse.class.getSimpleName() + "[", "]")
                .add("lists=" + lists)
                .toString();
    }
}
