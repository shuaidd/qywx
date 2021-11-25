package com.github.shuaidd.response.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 * @author ddshuai
 * @date 2021-11-25 11:43
 **/
public class GetCustomerResponse extends AbstractBaseResponse {

    @JsonProperty("invalid_external_userid")
    private List<String> invalidExternalUserId;

    @JsonProperty("customer_list")
    private List<CustomerInfo> customerList;

    public static class CustomerInfo {

        @JsonProperty("external_userid")
        private String externalUserId;

        private String nickname;

        private String avatar;

        private Integer gender;

        @JsonProperty("unionid")
        private String unionId;

        public String getExternalUserId() {
            return externalUserId;
        }

        public void setExternalUserId(String externalUserId) {
            this.externalUserId = externalUserId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public String getUnionId() {
            return unionId;
        }

        public void setUnionId(String unionId) {
            this.unionId = unionId;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", CustomerInfo.class.getSimpleName() + "[", "]")
                    .add("externalUserId='" + externalUserId + "'")
                    .add("nickname='" + nickname + "'")
                    .add("avatar='" + avatar + "'")
                    .add("gender=" + gender)
                    .add("unionId='" + unionId + "'")
                    .toString();
        }
    }

    public List<String> getInvalidExternalUserId() {
        return invalidExternalUserId;
    }

    public void setInvalidExternalUserId(List<String> invalidExternalUserId) {
        this.invalidExternalUserId = invalidExternalUserId;
    }

    public List<CustomerInfo> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerInfo> customerList) {
        this.customerList = customerList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GetCustomerResponse.class.getSimpleName() + "[", "]")
                .add("invalidExternalUserId=" + invalidExternalUserId)
                .add("customerList=" + customerList)
                .toString();
    }
}
