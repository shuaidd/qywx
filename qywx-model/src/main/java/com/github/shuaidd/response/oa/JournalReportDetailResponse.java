package com.github.shuaidd.response.oa;

import com.github.shuaidd.dto.oa.JournalReportDetail;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-20 09:47
 **/
public class JournalReportDetailResponse extends AbstractBaseResponse {

    private JournalReportDetail info;

    public JournalReportDetail getInfo() {
        return info;
    }

    public void setInfo(JournalReportDetail info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JournalReportDetailResponse.class.getSimpleName() + "[", "]")
                .add("info=" + info)
                .toString();
    }
}
