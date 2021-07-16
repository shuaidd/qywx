package com.github.shuaidd.dto.oa.formcontrol;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-16 17:29
 **/
public class TableChildren {

    private List<TableChildrenDetail> list;

    public List<TableChildrenDetail> getList() {
        return list;
    }

    public void setList(List<TableChildrenDetail> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TableChildren.class.getSimpleName() + "[", "]")
                .add("list=" + list)
                .toString();
    }
}
