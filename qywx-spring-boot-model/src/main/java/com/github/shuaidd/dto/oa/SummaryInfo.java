package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.template.TemplateText;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-16 13:45
 **/
public class SummaryInfo {

    @JsonProperty("summary_info")
    private List<TemplateText> summaryInfo;

    public List<TemplateText> getSummaryInfo() {
        return summaryInfo;
    }

    public void setSummaryInfo(List<TemplateText> summaryInfo) {
        this.summaryInfo = summaryInfo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SummaryInfo.class.getSimpleName() + "[", "]")
                .add("summaryInfo=" + summaryInfo)
                .toString();
    }
}
