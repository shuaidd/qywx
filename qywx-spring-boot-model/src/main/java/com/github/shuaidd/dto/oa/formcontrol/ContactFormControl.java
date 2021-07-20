package com.github.shuaidd.dto.oa.formcontrol;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述 成员控件/部门控件
 *
 * @author ddshuai
 * date 2021-07-16 14:25
 **/
public class ContactFormControl implements ApplyFormControl {

    private List<ContactMember> members;

    private List<ContactDepartment> departments;

    public List<ContactMember> getMembers() {
        return members;
    }

    public void setMembers(List<ContactMember> members) {
        this.members = members;
    }

    public List<ContactDepartment> getDepartments() {
        return departments;
    }

    public void setDepartments(List<ContactDepartment> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ContactFormControl.class.getSimpleName() + "[", "]")
                .add("members=" + members)
                .add("departments=" + departments)
                .toString();
    }

    @Override
    public String formControlName() {
        return "Contact";
    }
}
