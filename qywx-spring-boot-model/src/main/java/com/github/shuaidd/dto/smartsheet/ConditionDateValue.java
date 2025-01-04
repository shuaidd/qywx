package com.github.shuaidd.dto.smartsheet;

import lombok.Data;

import java.util.List;

@Data
public class ConditionDateValue {

    private String type;

    private List<String> value;
}
