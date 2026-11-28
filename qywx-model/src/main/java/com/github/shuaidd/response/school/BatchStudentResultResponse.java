package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 学生批量操作结果响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class BatchStudentResultResponse extends AbstractBaseResponse {

    /**
     * 失败或异常的学生操作结果列表
     */
    @JsonProperty("result_list")
    private List<StudentOperationResult> resultList;

    public List<StudentOperationResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<StudentOperationResult> resultList) {
        this.resultList = resultList;
    }

    /**
     * 学生批量操作结果明细
     */
    public static class StudentOperationResult {

        /**
         * 失败的学生userid
         */
        @JsonProperty("student_userid")
        private String studentUserId;

        /**
         * 操作返回码
         */
        private Integer errcode;

        /**
         * 操作结果描述
         */
        private String errmsg;

        public String getStudentUserId() {
            return studentUserId;
        }

        public void setStudentUserId(String studentUserId) {
            this.studentUserId = studentUserId;
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
