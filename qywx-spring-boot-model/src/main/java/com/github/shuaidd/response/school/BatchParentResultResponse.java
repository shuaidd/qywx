package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 家长批量操作结果响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class BatchParentResultResponse extends AbstractBaseResponse {

    /**
     * 失败或异常的家长操作结果列表
     */
    @JsonProperty("result_list")
    private List<ParentOperationResult> resultList;

    public List<ParentOperationResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<ParentOperationResult> resultList) {
        this.resultList = resultList;
    }

    /**
     * 家长批量操作结果明细
     */
    public static class ParentOperationResult {

        /**
         * 失败的家长userid
         */
        @JsonProperty("parent_userid")
        private String parentUserId;

        /**
         * 操作返回码
         */
        private Integer errcode;

        /**
         * 操作结果描述
         */
        private String errmsg;

        public String getParentUserId() {
            return parentUserId;
        }

        public void setParentUserId(String parentUserId) {
            this.parentUserId = parentUserId;
        }

        public Integer getErrcode() {
            return errcode;
        }

        public void setErrcode(Integer errcode) {
            this.errcode = errcode;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }
    }
}
