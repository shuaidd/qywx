package com.github.shuaidd.event;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.StringJoiner;

/**
 * 描述 用户信息基础类
 *
 * @author ddshuai
 * date 2021-07-13 15:45
 **/
@XmlRootElement(name = "xml")
public class UserEventData extends BaseEventData {

    @XmlElement(name = "UserID")
    private String userId;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Department")
    private String department;

    @XmlElement(name = "MainDepartment")
    private String mainDepartment;

    @XmlElement(name = "IsLeaderInDept")
    private String isLeaderInDept;

    @XmlElement(name = "Position")
    private String position;

    @XmlElement(name = "Mobile")
    private String mobile;

    @XmlElement(name = "Gender")
    private String gender;

    @XmlElement(name = "Email")
    private String email;

    @XmlElement(name = "Status")
    private String status;

    @XmlElement(name = "Avatar")
    private String avatar;

    @XmlElement(name = "Alias")
    private String alias;

    @XmlElement(name = "Telephone")
    private String telephone;

    @XmlElement(name = "Address")
    private String address;

    @XmlElement(name = "ExtAttr")
    private ExtAttrData extAttrData;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMainDepartment() {
        return mainDepartment;
    }

    public void setMainDepartment(String mainDepartment) {
        this.mainDepartment = mainDepartment;
    }

    public String getIsLeaderInDept() {
        return isLeaderInDept;
    }

    public void setIsLeaderInDept(String isLeaderInDept) {
        this.isLeaderInDept = isLeaderInDept;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ExtAttrData getExtAttrData() {
        return extAttrData;
    }

    public void setExtAttrData(ExtAttrData extAttrData) {
        this.extAttrData = extAttrData;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserEventData.class.getSimpleName() + "[", "]")
                .add("userId='" + userId + "'")
                .add("name='" + name + "'")
                .add("department='" + department + "'")
                .add("mainDepartment='" + mainDepartment + "'")
                .add("isLeaderInDept='" + isLeaderInDept + "'")
                .add("position='" + position + "'")
                .add("mobile='" + mobile + "'")
                .add("gender='" + gender + "'")
                .add("email='" + email + "'")
                .add("status='" + status + "'")
                .add("avatar='" + avatar + "'")
                .add("alias='" + alias + "'")
                .add("telephone='" + telephone + "'")
                .add("address='" + address + "'")
                .add("extAttrData=" + extAttrData)
                .toString();
    }
}
