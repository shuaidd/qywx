package com.github.shuaidd.response;


import com.github.shuaidd.dto.JobResultDetail;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2019-04-06 20:49
 **/
public class AsyncJobResultResponse extends AbstractBaseResponse {
    /**
     * 1表示任务开始，2表示任务进行中，3表示任务已完成
     */
    private Integer status;
    /**
     * 1. sync_user(增量更新成员) 2. replace_user(全量覆盖成员)3. replace_party(全量覆盖部门)
     */
    private String type;

    private Integer total;

    private Integer percentage;

    private List<JobResultDetail> result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public List<JobResultDetail> getResult() {
        return result;
    }

    public void setResult(List<JobResultDetail> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AsyncJobResultResponse.class.getSimpleName() + "[", "]")
                .add("status=" + status)
                .add("type='" + type + "'")
                .add("total=" + total)
                .add("percentage=" + percentage)
                .toString();
    }
}
