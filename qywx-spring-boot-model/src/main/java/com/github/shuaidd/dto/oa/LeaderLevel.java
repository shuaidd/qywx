package com.github.shuaidd.dto.oa;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-20 10:15
 **/
public class LeaderLevel {

    private Long level;

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LeaderLevel.class.getSimpleName() + "[", "]")
                .add("level=" + level)
                .toString();
    }
}
