package com.github.shuaidd.response.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-11-25 09:15
 **/
public class ServicerResponse extends AbstractBaseResponse {

    @JsonProperty("result_list")
    private List<ResultDetail> resultList;

    public static class ResultDetail extends AbstractBaseResponse {

        @JsonProperty("userid")
        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", ResultDetail.class.getSimpleName() + "[", "]")
                    .add("userId='" + userId + "'")
                    .toString();
        }
    }

    public List<ResultDetail> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultDetail> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ServicerResponse.class.getSimpleName() + "[", "]")
                .add("resultList=" + resultList)
                .toString();
    }
}
