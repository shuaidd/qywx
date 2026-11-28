package com.github.shuaidd.resquest.school;

import java.util.List;

/**
 * 批量创建或更新家长的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class BatchParentOperationRequest {

    /**
     * 家长列表，每次最多100个
     */
    private List<ParentItem> parents;

    public List<ParentItem> getParents() {
        return parents;
    }

    public void setParents(List<ParentItem> parents) {
        this.parents = parents;
    }
}
