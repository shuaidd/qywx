package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.ConclusionData;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-02 15:43
 **/
public class UpdateContactWayRequest {

    @JsonProperty("config_id")
    private String configId;
    /**
     * 在小程序中联系时使用的控件样式
     */
    private Integer style;

    /**
     * 联系方式的备注信息，用于助记，不超过30个字符
     */
    private String remark;

    /**
     * 外部客户添加时是否无需验证，默认为true
     */
    @JsonProperty("skip_verify")
    private Boolean skipVerify;

    /**
     * 企业自定义的state参数，用于区分不同的添加渠道，在调用“获取外部联系人详情”时会返回该参数值，不超过30个字符
     */
    private String state;

    /**
     * 使用该联系方式的用户userID列表，在type为1时为必填，且只能有一个
     */
    @JsonProperty("user")
    private List<String> users;

    /**
     * 使用该联系方式的部门id列表，只在type为2时有效
     */
    private List<Integer> party;

    /**
     * 临时会话二维码有效期，以秒为单位。该参数仅在is_temp为true时有效，默认7天
     */
    @JsonProperty("expires_in")
    private Integer expiresIn;

    /**
     * 临时会话有效期，以秒为单位。该参数仅在is_temp为true时有效，默认为添加好友后24小时
     */
    @JsonProperty("chat_expires_in")
    private Integer chatExpiresIn;

    /**
     * 可进行临时会话的客户unionid，该参数仅在is_temp为true时有效，如不指定则不进行限制
     */
    @JsonProperty("unionid")
    private String unionId;

    @JsonProperty("conclusions")
    private ConclusionData conclusions;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getSkipVerify() {
        return skipVerify;
    }

    public void setSkipVerify(Boolean skipVerify) {
        this.skipVerify = skipVerify;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public List<Integer> getParty() {
        return party;
    }

    public void setParty(List<Integer> party) {
        this.party = party;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Integer getChatExpiresIn() {
        return chatExpiresIn;
    }

    public void setChatExpiresIn(Integer chatExpiresIn) {
        this.chatExpiresIn = chatExpiresIn;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public ConclusionData getConclusions() {
        return conclusions;
    }

    public void setConclusions(ConclusionData conclusions) {
        this.conclusions = conclusions;
    }
}
