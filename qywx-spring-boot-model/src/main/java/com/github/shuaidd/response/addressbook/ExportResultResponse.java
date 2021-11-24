package com.github.shuaidd.response.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.StringJoiner;

/**
 * 获取导出结果
 *
 * @author ddshuai
 * @date 2021-11-24 13:51
 **/
public class ExportResultResponse extends AbstractBaseResponse {

    /**
     * 任务状态:0-未处理，1-处理中，2-完成，3-异常失败
     */
    private Integer status;

    @JsonProperty("data_list")
    private List<ExportResultData> dataList;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ExportResultData> getDataList() {
        return dataList;
    }

    public void setDataList(List<ExportResultData> dataList) {
        this.dataList = dataList;
    }



   public static class ExportResultData {

        /**
         * 数据下载链接,支持指定Range头部分段下载。有效期2个小时
         */
        private String url;

        /**
         * 密文数据大小
         */
        private Integer size;

        /**
         * 密文数据md5
         */
        private String md5;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", ExportResultData.class.getSimpleName() + "[", "]")
                    .add("url='" + url + "'")
                    .add("size=" + size)
                    .add("md5='" + md5 + "'")
                    .toString();
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ExportResultResponse.class.getSimpleName() + "[", "]")
                .add("status=" + status)
                .add("dataList=" + dataList)
                .toString();
    }
}
