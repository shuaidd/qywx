package com.github.shuaidd.dto.oa.formcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述 关联审批单控件
 *
 * @author ddshuai
 * date 2021-07-16 17:52
 **/
public class RelatedApprovalFormControl implements ApplyFormControl {

    @JsonProperty("related_approval")
    private List<RelatedApprovalData> relatedApproval;

    public List<RelatedApprovalData> getRelatedApproval() {
        return relatedApproval;
    }

    public void setRelatedApproval(List<RelatedApprovalData> relatedApproval) {
        this.relatedApproval = relatedApproval;
    }

    @Override
    public String formControlName() {
        return "RelatedApproval";
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RelatedApprovalFormControl.class.getSimpleName() + "[", "]")
                .add("relatedApproval=" + relatedApproval)
                .toString();
    }
}
