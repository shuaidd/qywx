package com.github.shuaidd.response.smartsheet;

import com.github.shuaidd.dto.smartsheet.View;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddViewResponse extends AbstractBaseResponse {

    private View view;
}
