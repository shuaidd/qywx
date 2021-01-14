package com.github.shuaidd.resquest;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-11 15:01
 **/
public class OffsetPageRequest {
    private Integer offset;
    private Integer limit;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
