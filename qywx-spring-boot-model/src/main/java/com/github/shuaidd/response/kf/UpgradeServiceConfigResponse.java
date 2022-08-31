package com.github.shuaidd.response.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.kf.GroupChatRange;
import com.github.shuaidd.dto.kf.MemberRange;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-08-31 12:58
 **/
public class UpgradeServiceConfigResponse extends AbstractBaseResponse {

    /**
     * 专员服务配置范围
     */
    @JsonProperty(value = "member_range")
    private MemberRange memberRange;

    /**
     * 客户群配置范围
     */
    @JsonProperty(value = "groupchat_range")
    private GroupChatRange groupChatRange;

    public MemberRange getMemberRange() {
        return memberRange;
    }

    public void setMemberRange(MemberRange memberRange) {
        this.memberRange = memberRange;
    }

    public GroupChatRange getGroupChatRange() {
        return groupChatRange;
    }

    public void setGroupChatRange(GroupChatRange groupChatRange) {
        this.groupChatRange = groupChatRange;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UpgradeServiceConfigResponse.class.getSimpleName() + "[", "]")
                .add("memberRange=" + memberRange)
                .add("groupChatRange=" + groupChatRange)
                .toString();
    }
}
