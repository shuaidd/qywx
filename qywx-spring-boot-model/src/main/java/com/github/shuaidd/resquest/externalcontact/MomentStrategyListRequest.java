package com.github.shuaidd.resquest.externalcontact;

import com.github.shuaidd.resquest.CursorPageRequest;

/**
 * 获取客户朋友圈规则组列表请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentStrategyListRequest extends CursorPageRequest {

    public MomentStrategyListRequest() {
        setLimit(1000);
    }
}
