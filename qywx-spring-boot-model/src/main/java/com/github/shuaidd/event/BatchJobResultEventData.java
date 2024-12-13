package com.github.shuaidd.event;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-13 17:57
 **/
@XmlRootElement(name = "xml")
public class BatchJobResultEventData extends BaseEventData {

    @XmlElement(name = "BatchJob")
    private BatchJobData batchJob;

    public BatchJobData getBatchJob() {
        return batchJob;
    }

    public void setBatchJob(BatchJobData batchJob) {
        this.batchJob = batchJob;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BatchJobResultEventData.class.getSimpleName() + "[", "]")
                .add("batchJob=" + batchJob)
                .toString();
    }
}
