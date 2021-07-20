package com.github.shuaidd.dto.oa.formcontrol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * 描述 审批表单控件
 *
 * @author ddshuai
 * date 2021-07-16 14:25
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public interface ApplyFormControl extends Serializable {

    /**
     * 控件名称
     *
     * @return 控件名称
     */
    String formControlName();
}
