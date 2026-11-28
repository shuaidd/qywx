package com.github.shuaidd.resquest.externalcontact;

import com.github.shuaidd.resquest.CursorPageRequest;

/**
 * 获取获客链接 ID 列表请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionLinkListRequest extends CursorPageRequest {

    public CustomerAcquisitionLinkListRequest() {
        setLimit(100);
    }
}