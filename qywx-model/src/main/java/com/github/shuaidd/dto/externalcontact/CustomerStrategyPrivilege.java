package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * 客户联系规则组权限配置
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyPrivilege {

    /**
     * 是否可以查看客户列表；基础权限，不可取消
     */
    @JsonProperty("view_customer_list")
    private Boolean viewCustomerList;

    /**
     * 是否可以查看客户统计数据；基础权限，不可取消
     */
    @JsonProperty("view_customer_data")
    private Boolean viewCustomerData;

    /**
     * 是否可以查看群聊列表；基础权限，不可取消
     */
    @JsonProperty("view_room_list")
    private Boolean viewRoomList;

    /**
     * 是否可以使用联系我配置；基础权限，不可取消
     */
    @JsonProperty("contact_me")
    private Boolean contactMe;

    /**
     * 是否可以加入群聊；基础权限，不可取消
     */
    @JsonProperty("join_room")
    private Boolean joinRoom;

    /**
     * 是否允许成员把客户分享给其他成员
     */
    @JsonProperty("share_customer")
    private Boolean shareCustomer;

    /**
     * 是否允许分配离职成员的客户
     */
    @JsonProperty("oper_resign_customer")
    private Boolean operResignCustomer;

    /**
     * 是否允许分配离职成员的客户群
     */
    @JsonProperty("oper_resign_group")
    private Boolean operResignGroup;

    /**
     * 是否允许成员给企业客户发送消息
     */
    @JsonProperty("send_customer_msg")
    private Boolean sendCustomerMsg;

    /**
     * 是否允许配置欢迎语
     */
    @JsonProperty("edit_welcome_msg")
    private Boolean editWelcomeMsg;

    /**
     * 是否允许查看成员联系客户统计数据
     */
    @JsonProperty("view_behavior_data")
    private Boolean viewBehaviorData;

    /**
     * 是否允许查看群聊数据统计
     */
    @JsonProperty("view_room_data")
    private Boolean viewRoomData;

    /**
     * 是否允许发送消息到企业的客户群
     */
    @JsonProperty("send_group_msg")
    private Boolean sendGroupMsg;

    /**
     * 是否允许对企业客户群进行去重
     */
    @JsonProperty("room_deduplication")
    private Boolean roomDeduplication;

    /**
     * 是否允许配置快捷回复
     */
    @JsonProperty("rapid_reply")
    private Boolean rapidReply;

    /**
     * 是否允许转接在职成员的客户
     */
    @JsonProperty("onjob_customer_transfer")
    private Boolean onjobCustomerTransfer;

    /**
     * 是否允许编辑企业成员防骚扰规则
     */
    @JsonProperty("edit_anti_spam_rule")
    private Boolean editAntiSpamRule;

    /**
     * 是否允许导出客户列表
     */
    @JsonProperty("export_customer_list")
    private Boolean exportCustomerList;

    /**
     * 是否允许导出成员客户统计数据
     */
    @JsonProperty("export_customer_data")
    private Boolean exportCustomerData;

    /**
     * 是否允许导出客户群列表
     */
    @JsonProperty("export_customer_group_list")
    private Boolean exportCustomerGroupList;

    /**
     * 是否允许配置企业客户标签
     */
    @JsonProperty("manage_customer_tag")
    private Boolean manageCustomerTag;

    public Boolean getViewCustomerList() {
        return viewCustomerList;
    }

    public void setViewCustomerList(Boolean viewCustomerList) {
        this.viewCustomerList = viewCustomerList;
    }

    public Boolean getViewCustomerData() {
        return viewCustomerData;
    }

    public void setViewCustomerData(Boolean viewCustomerData) {
        this.viewCustomerData = viewCustomerData;
    }

    public Boolean getViewRoomList() {
        return viewRoomList;
    }

    public void setViewRoomList(Boolean viewRoomList) {
        this.viewRoomList = viewRoomList;
    }

    public Boolean getContactMe() {
        return contactMe;
    }

    public void setContactMe(Boolean contactMe) {
        this.contactMe = contactMe;
    }

    public Boolean getJoinRoom() {
        return joinRoom;
    }

    public void setJoinRoom(Boolean joinRoom) {
        this.joinRoom = joinRoom;
    }

    public Boolean getShareCustomer() {
        return shareCustomer;
    }

    public void setShareCustomer(Boolean shareCustomer) {
        this.shareCustomer = shareCustomer;
    }

    public Boolean getOperResignCustomer() {
        return operResignCustomer;
    }

    public void setOperResignCustomer(Boolean operResignCustomer) {
        this.operResignCustomer = operResignCustomer;
    }

    public Boolean getOperResignGroup() {
        return operResignGroup;
    }

    public void setOperResignGroup(Boolean operResignGroup) {
        this.operResignGroup = operResignGroup;
    }

    public Boolean getSendCustomerMsg() {
        return sendCustomerMsg;
    }

    public void setSendCustomerMsg(Boolean sendCustomerMsg) {
        this.sendCustomerMsg = sendCustomerMsg;
    }

    public Boolean getEditWelcomeMsg() {
        return editWelcomeMsg;
    }

    public void setEditWelcomeMsg(Boolean editWelcomeMsg) {
        this.editWelcomeMsg = editWelcomeMsg;
    }

    public Boolean getViewBehaviorData() {
        return viewBehaviorData;
    }

    public void setViewBehaviorData(Boolean viewBehaviorData) {
        this.viewBehaviorData = viewBehaviorData;
    }

    public Boolean getViewRoomData() {
        return viewRoomData;
    }

    public void setViewRoomData(Boolean viewRoomData) {
        this.viewRoomData = viewRoomData;
    }

    public Boolean getSendGroupMsg() {
        return sendGroupMsg;
    }

    public void setSendGroupMsg(Boolean sendGroupMsg) {
        this.sendGroupMsg = sendGroupMsg;
    }

    public Boolean getRoomDeduplication() {
        return roomDeduplication;
    }

    public void setRoomDeduplication(Boolean roomDeduplication) {
        this.roomDeduplication = roomDeduplication;
    }

    public Boolean getRapidReply() {
        return rapidReply;
    }

    public void setRapidReply(Boolean rapidReply) {
        this.rapidReply = rapidReply;
    }

    public Boolean getOnjobCustomerTransfer() {
        return onjobCustomerTransfer;
    }

    public void setOnjobCustomerTransfer(Boolean onjobCustomerTransfer) {
        this.onjobCustomerTransfer = onjobCustomerTransfer;
    }

    public Boolean getEditAntiSpamRule() {
        return editAntiSpamRule;
    }

    public void setEditAntiSpamRule(Boolean editAntiSpamRule) {
        this.editAntiSpamRule = editAntiSpamRule;
    }

    public Boolean getExportCustomerList() {
        return exportCustomerList;
    }

    public void setExportCustomerList(Boolean exportCustomerList) {
        this.exportCustomerList = exportCustomerList;
    }

    public Boolean getExportCustomerData() {
        return exportCustomerData;
    }

    public void setExportCustomerData(Boolean exportCustomerData) {
        this.exportCustomerData = exportCustomerData;
    }

    public Boolean getExportCustomerGroupList() {
        return exportCustomerGroupList;
    }

    public void setExportCustomerGroupList(Boolean exportCustomerGroupList) {
        this.exportCustomerGroupList = exportCustomerGroupList;
    }

    public Boolean getManageCustomerTag() {
        return manageCustomerTag;
    }

    public void setManageCustomerTag(Boolean manageCustomerTag) {
        this.manageCustomerTag = manageCustomerTag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("viewCustomerList", viewCustomerList)
                .append("viewCustomerData", viewCustomerData)
                .append("viewRoomList", viewRoomList)
                .append("contactMe", contactMe)
                .append("joinRoom", joinRoom)
                .append("shareCustomer", shareCustomer)
                .append("operResignCustomer", operResignCustomer)
                .append("operResignGroup", operResignGroup)
                .append("sendCustomerMsg", sendCustomerMsg)
                .append("editWelcomeMsg", editWelcomeMsg)
                .append("viewBehaviorData", viewBehaviorData)
                .append("viewRoomData", viewRoomData)
                .append("sendGroupMsg", sendGroupMsg)
                .append("roomDeduplication", roomDeduplication)
                .append("rapidReply", rapidReply)
                .append("onjobCustomerTransfer", onjobCustomerTransfer)
                .append("editAntiSpamRule", editAntiSpamRule)
                .append("exportCustomerList", exportCustomerList)
                .append("exportCustomerData", exportCustomerData)
                .append("exportCustomerGroupList", exportCustomerGroupList)
                .append("manageCustomerTag", manageCustomerTag)
                .toString();
    }
}