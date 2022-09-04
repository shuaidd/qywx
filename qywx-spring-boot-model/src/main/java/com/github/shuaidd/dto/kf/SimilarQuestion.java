package com.github.shuaidd.dto.kf;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-01 17:10
 **/
public class SimilarQuestion {

    private List<SimilarQuestionItem> items;

    public List<SimilarQuestionItem> getItems() {
        return items;
    }

    public void setItems(List<SimilarQuestionItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SimilarQuestion.class.getSimpleName() + "[", "]")
                .add("items=" + items)
                .toString();
    }
}
