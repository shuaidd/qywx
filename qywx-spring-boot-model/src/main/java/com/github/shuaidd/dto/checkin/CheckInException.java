package com.github.shuaidd.dto.checkin;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-15 13:22
 **/
public class CheckInException {
    private Integer count;
    private Integer duration;
    private Integer exception;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getException() {
        return exception;
    }

    public void setException(Integer exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CheckInException.class.getSimpleName() + "[", "]")
                .add("count=" + count)
                .add("duration=" + duration)
                .add("exception=" + exception)
                .toString();
    }
}
