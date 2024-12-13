package com.github.shuaidd.event;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-13 17:58
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class BatchJobData {

    @XmlElement(name = "JobId")
    private String jobId;

    @XmlElement(name = "JobType")
    private String jobType;

    @XmlElement(name = "ErrCode")
    private String errCode;

    @XmlElement(name = "ErrMsg")
    private String errMsg;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BatchJobData.class.getSimpleName() + "[", "]")
                .add("jobId='" + jobId + "'")
                .add("jobType='" + jobType + "'")
                .add("errCode='" + errCode + "'")
                .add("errMsg='" + errMsg + "'")
                .toString();
    }
}
