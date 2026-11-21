package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 客户群加入群聊配置请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class GroupChatJoinWayRequest {

    /**
     * 配置 ID；创建时不填，更新时必填
     */
    @JsonProperty("config_id")
    private String configId;

    /**
     * 入群场景；1-群的小程序插件，2-群的二维码
     */
    @JsonProperty("scene")
    private Integer scene;

    /**
     * 配置备注，用于助记；超过 30 个字符会被官方截断
     */
    @JsonProperty("remark")
    private String remark;

    /**
     * 群满后是否自动新建群；0-否，1-是，默认 1
     */
    @JsonProperty("auto_create_room")
    private Integer autoCreateRoom;

    /**
     * 自动建群使用的群名前缀；autoCreateRoom 为 1 时有效，最长 40 个 UTF-8 字符
     */
    @JsonProperty("room_base_name")
    private String roomBaseName;

    /**
     * 自动建群的起始序号；autoCreateRoom 为 1 时有效
     */
    @JsonProperty("room_base_id")
    private Integer roomBaseId;

    /**
     * 使用该配置的客户群 ID 列表，最多 5 个
     */
    @JsonProperty("chat_id_list")
    private List<String> chatIdList;

    /**
     * 企业自定义渠道标识，用于区分入群渠道；不超过 30 个 UTF-8 字符
     */
    @JsonProperty("state")
    private String state;

    /**
     * 是否把客户添加来源标记为本应用创建的加入群聊；默认 true，仅营销获客应用生效
     */
    @JsonProperty("mark_source")
    private Boolean markSource;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public Integer getScene() {
        return scene;
    }

    public void setScene(Integer scene) {
        this.scene = scene;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAutoCreateRoom() {
        return autoCreateRoom;
    }

    public void setAutoCreateRoom(Integer autoCreateRoom) {
        this.autoCreateRoom = autoCreateRoom;
    }

    public String getRoomBaseName() {
        return roomBaseName;
    }

    public void setRoomBaseName(String roomBaseName) {
        this.roomBaseName = roomBaseName;
    }

    public Integer getRoomBaseId() {
        return roomBaseId;
    }

    public void setRoomBaseId(Integer roomBaseId) {
        this.roomBaseId = roomBaseId;
    }

    public List<String> getChatIdList() {
        return chatIdList;
    }

    public void setChatIdList(List<String> chatIdList) {
        this.chatIdList = chatIdList;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getMarkSource() {
        return markSource;
    }

    public void setMarkSource(Boolean markSource) {
        this.markSource = markSource;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("configId", configId)
                .append("scene", scene)
                .append("remark", remark)
                .append("autoCreateRoom", autoCreateRoom)
                .append("roomBaseName", roomBaseName)
                .append("roomBaseId", roomBaseId)
                .append("chatIdList", chatIdList)
                .append("state", state)
                .append("markSource", markSource)
                .toString();
    }
}
