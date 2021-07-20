package com.github.shuaidd.dto.oa.formcontrol;

/**
 * 描述 公式控件
 *
 * @author ddshuai
 * date 2021-07-16 17:57
 **/
public class FormulaFormControl implements ApplyFormControl {

    private ValueData formula;

    public ValueData getFormula() {
        return formula;
    }

    public void setFormula(ValueData formula) {
        this.formula = formula;
    }

    @Override
    public String formControlName() {
        return "Formula";
    }
}
