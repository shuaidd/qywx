package com.github.shuaidd.response.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.oa.ApprovalData;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-11 13:03
 **/
public class ApprovalDataResponse extends AbstractBaseResponse {

    private Integer total;
    private Integer count;
    @JsonProperty("next_spnum")
    private String nextSpNum;
    private List<ApprovalData> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNextSpNum() {
        return nextSpNum;
    }

    public void setNextSpNum(String nextSpNum) {
        this.nextSpNum = nextSpNum;
    }

    public List<ApprovalData> getData() {
        return data;
    }

    public void setData(List<ApprovalData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApprovalDataResponse.class.getSimpleName() + "[", "]")
                .add("total=" + total)
                .add("count=" + count)
                .add("nextSpNum='" + nextSpNum + "'")
                .add("data=" + data)
                .toString();
    }
}
