package com.github.shuaidd.dto.oa;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-16 13:40
 **/
public class ApplyData {
    private List<ApplyDataContent> contents;

    public List<ApplyDataContent> getContents() {
        return contents;
    }

    public void setContents(List<ApplyDataContent> contents) {
        this.contents = contents;
    }
}
