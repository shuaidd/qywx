package com.github.shuaidd.resquest.externalcontact;

import com.github.shuaidd.resquest.CursorPageRequest;

/**
 * 获取客户联系规则组列表请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyListRequest extends CursorPageRequest {

    public CustomerStrategyListRequest() {
        setLimit(1000);
    }
}