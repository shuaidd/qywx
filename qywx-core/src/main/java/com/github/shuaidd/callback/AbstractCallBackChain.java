package com.github.shuaidd.callback;

import com.github.shuaidd.event.BaseEventData;
import com.github.shuaidd.support.CallBackManager;

import java.util.Objects;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-20 15:51
 **/
public abstract class AbstractCallBackChain implements CallBackChain {

    private AbstractCallBackChain next;

    public AbstractCallBackChain() {
        CallBackManager.registerCallBack(this);
    }

    /**
     * 处理实际业务回调逻辑
     *
     * @param applicationName 回调应用名
     * @param eventData       回调数据
     */
    public void handleData(String applicationName, BaseEventData eventData) {
        if (match(applicationName, eventData)) {
            handle(applicationName, eventData);
        }

        if (Objects.nonNull(next)) {
            next.handleData(applicationName, eventData);
        }
    }

    public AbstractCallBackChain getNext() {
        return next;
    }

    public void setNext(AbstractCallBackChain callBackChain) {
        this.next = callBackChain;
    }
}
