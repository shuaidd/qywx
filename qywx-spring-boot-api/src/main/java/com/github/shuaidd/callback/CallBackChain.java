package com.github.shuaidd.callback;

import com.github.shuaidd.event.BaseEventData;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-20 15:46
 **/
public interface CallBackChain {

    /**
     * 是否需要处理
     * @param eventData 回调数据
     * @param applicationName 应用名
     * @return 是否匹配
     */
    boolean match(String applicationName,BaseEventData eventData);

    /**
     * 业务逻辑处理
     * @param applicationName 应用名
     * @param eventData 回调数据
     */
    void handle(String applicationName,BaseEventData eventData);
}
