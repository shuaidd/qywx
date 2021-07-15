package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
}
