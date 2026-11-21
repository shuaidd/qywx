package com.github.shuaidd.response.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 客服帐号企业汇总数据响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class GetCorpStatisticResponse extends AbstractBaseResponse {

    /**
     * 企业汇总统计数据列表；按统计日期返回
     */
    @JsonProperty("statistic_list")
    private List<StatisticItem> statisticList;

    public List<StatisticItem> getStatisticList() {
        return statisticList;
    }

    public void setStatisticList(List<StatisticItem> statisticList) {
        this.statisticList = statisticList;
    }

    /**
     * 单个统计日期的企业汇总数据
     */
    public static class StatisticItem {

        /**
         * 数据统计日期；当天0点的Unix时间戳，单位秒
         */
        @JsonProperty("stat_time")
        private Long statTime;

        /**
         * 企业汇总统计指标；官方约定该数据不保证必返，查询不到时为空
         */
        @JsonProperty("statistic")
        private CorpStatistic statistic;

        public Long getStatTime() {
            return statTime;
        }

        public void setStatTime(Long statTime) {
            this.statTime = statTime;
        }

        public CorpStatistic getStatistic() {
            return statistic;
        }

        public void setStatistic(CorpStatistic statistic) {
            this.statistic = statistic;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("statTime", statTime)
                    .append("statistic", statistic)
                    .toString();
        }
    }

    /**
     * 企业微信客服汇总统计指标
     */
    public static class CorpStatistic {

        /**
         * 咨询会话次数；客户主动发起的会话数量
         */
        @JsonProperty("session_cnt")
        private Long sessionCnt;

        /**
         * 咨询客户数；发起咨询的去重客户数量
         */
        @JsonProperty("customer_cnt")
        private Long customerCnt;

        /**
         * 客户发送消息数；咨询客户发送的消息总量
         */
        @JsonProperty("customer_msg_cnt")
        private Long customerMsgCnt;

        /**
         * 升级服务客户数；被推荐给专员或客户群服务的客户数量
         */
        @JsonProperty("upgrade_service_customer_cnt")
        private Long upgradeServiceCustomerCnt;

        /**
         * AI接待会话次数；由AI接待处理的会话数量
         */
        @JsonProperty("ai_session_reply_cnt")
        private Long aiSessionReplyCnt;

        /**
         * AI转人工率；AI接待会话中转接人工的比例
         */
        @JsonProperty("ai_transfer_rate")
        private Double aiTransferRate;

        /**
         * AI知识命中率；AI接待会话命中知识库的比例
         */
        @JsonProperty("ai_knowledge_hit_rate")
        private Double aiKnowledgeHitRate;

        /**
         * 发送消息被拒客户数；因客户拒收导致消息发送受限的客户数量
         */
        @JsonProperty("msg_rejected_customer_cnt")
        private Long msgRejectedCustomerCnt;

        public Long getSessionCnt() {
            return sessionCnt;
        }

        public void setSessionCnt(Long sessionCnt) {
            this.sessionCnt = sessionCnt;
        }

        public Long getCustomerCnt() {
            return customerCnt;
        }

        public void setCustomerCnt(Long customerCnt) {
            this.customerCnt = customerCnt;
        }

        public Long getCustomerMsgCnt() {
            return customerMsgCnt;
        }

        public void setCustomerMsgCnt(Long customerMsgCnt) {
            this.customerMsgCnt = customerMsgCnt;
        }

        public Long getUpgradeServiceCustomerCnt() {
            return upgradeServiceCustomerCnt;
        }

        public void setUpgradeServiceCustomerCnt(Long upgradeServiceCustomerCnt) {
            this.upgradeServiceCustomerCnt = upgradeServiceCustomerCnt;
        }

        public Long getAiSessionReplyCnt() {
            return aiSessionReplyCnt;
        }

        public void setAiSessionReplyCnt(Long aiSessionReplyCnt) {
            this.aiSessionReplyCnt = aiSessionReplyCnt;
        }

        public Double getAiTransferRate() {
            return aiTransferRate;
        }

        public void setAiTransferRate(Double aiTransferRate) {
            this.aiTransferRate = aiTransferRate;
        }

        public Double getAiKnowledgeHitRate() {
            return aiKnowledgeHitRate;
        }

        public void setAiKnowledgeHitRate(Double aiKnowledgeHitRate) {
            this.aiKnowledgeHitRate = aiKnowledgeHitRate;
        }

        public Long getMsgRejectedCustomerCnt() {
            return msgRejectedCustomerCnt;
        }

        public void setMsgRejectedCustomerCnt(Long msgRejectedCustomerCnt) {
            this.msgRejectedCustomerCnt = msgRejectedCustomerCnt;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("sessionCnt", sessionCnt)
                    .append("customerCnt", customerCnt)
                    .append("customerMsgCnt", customerMsgCnt)
                    .append("upgradeServiceCustomerCnt", upgradeServiceCustomerCnt)
                    .append("aiSessionReplyCnt", aiSessionReplyCnt)
                    .append("aiTransferRate", aiTransferRate)
                    .append("aiKnowledgeHitRate", aiKnowledgeHitRate)
                    .append("msgRejectedCustomerCnt", msgRejectedCustomerCnt)
                    .toString();
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("statisticList", statisticList)
                .toString();
    }
}
