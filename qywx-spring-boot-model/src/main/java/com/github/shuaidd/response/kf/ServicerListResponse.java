package com.github.shuaidd.response.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-11-25 09:24
 **/
public class ServicerListResponse extends AbstractBaseResponse {

    @JsonProperty("servicer_list")
    private List<Servicer> servicerList;

    public static class Servicer {

        /**
         * 接待人员的接待状态。0:接待中,1:停止接待。第三方应用需具有“管理帐号、分配会话和收发消息”权限才可获取
         */
        private Integer status;

        @JsonProperty("userid")
        private String userId;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Servicer.class.getSimpleName() + "[", "]")
                    .add("status=" + status)
                    .add("userId='" + userId + "'")
                    .toString();
        }
    }

    public List<Servicer> getServicerList() {
        return servicerList;
    }

    public void setServicerList(List<Servicer> servicerList) {
        this.servicerList = servicerList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ServicerListResponse.class.getSimpleName() + "[", "]")
                .add("servicerList=" + servicerList)
                .toString();
    }
}
