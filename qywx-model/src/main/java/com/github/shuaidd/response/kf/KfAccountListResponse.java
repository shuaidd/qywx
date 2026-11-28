package com.github.shuaidd.response.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-11-24 17:14
 **/
public class KfAccountListResponse extends AbstractBaseResponse {

    @JsonProperty("account_list")
    private List<AccountData> accountList;

    public static class AccountData {

        private String avatar;

        private String name;

        @JsonProperty("open_kfid")
        private String openKfId;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOpenKfId() {
            return openKfId;
        }

        public void setOpenKfId(String openKfId) {
            this.openKfId = openKfId;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", AccountData.class.getSimpleName() + "[", "]")
                    .add("avatar='" + avatar + "'")
                    .add("name='" + name + "'")
                    .add("openKfId='" + openKfId + "'")
                    .toString();
        }
    }

    public List<AccountData> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<AccountData> accountList) {
        this.accountList = accountList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", KfAccountListResponse.class.getSimpleName() + "[", "]")
                .add("accountList=" + accountList)
                .toString();
    }
}
