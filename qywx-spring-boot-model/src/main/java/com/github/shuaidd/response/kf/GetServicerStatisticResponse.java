package com.github.shuaidd.response.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 客服帐号接待人员明细数据响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class GetServicerStatisticResponse extends AbstractBaseResponse {

    /**
     * 接待人员统计数据列表；按统计日期返回
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
     * 单个统计日期的接待人员数据
     */
    public static class StatisticItem {

        /**
         * 数据统计日期；当天0点的Unix时间戳，单位秒
         */
        @JsonProperty("stat_time")
        private Long statTime;

        /**
         * 接待人员统计指标；官方约定该数据不保证必返，查询不到时为空
         */
        @JsonProperty("statistic")
        private ServicerStatistic statistic;

        public Long getStatTime() {
            return statTime;
        }

        public void setStatTime(Long statTime) {
            this.statTime = statTime;
        }

        public ServicerStatistic getStatistic() {
            return statistic;
        }

        public void setStatistic(ServicerStatistic statistic) {
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
     * 微信客服接待人员统计指标
     */
    public static class ServicerStatistic {

        /**
         * 咨询会话次数；接待人员处理的会话数量
         */
        @JsonProperty("session_cnt")
        private Long sessionCnt;

        /**
         * 咨询客户数；接待人员服务的去重客户数量
         */
        @JsonProperty("customer_cnt")
        private Long customerCnt;

        /**
         * 客户发送消息数；接待人员服务的客户发送的消息总量
         */
        @JsonProperty("customer_msg_cnt")
        private Long customerMsgCnt;

        /**
         * 消息回复率；接待人员已回复消息占客户发送消息的比例
         */
        @JsonProperty("reply_rate")
        private Double replyRate;

        /**
         * 首次回复平均时长；接待人员首次回复客户所需的平均秒数
         */
        @JsonProperty("first_reply_average_sec")
        private Double firstReplyAverageSec;

        /**
         * 发起满意度调查次数；接待人员向客户发起满意度调查的数量
         */
        @JsonProperty("satisfaction_investgate_cnt")
        private Long satisfactionInvestgateCnt;

        /**
         * 满意度参与率；参与满意度调查的客户占被调查客户的比例
         */
        @JsonProperty("satisfaction_participation_rate")
        private Double satisfactionParticipationRate;

        /**
         * 满意占比；满意度评价为“满意”的比例
         */
        @JsonProperty("satisfied_rate")
        private Double satisfiedRate;

        /**
         * 一般占比；满意度评价为“一般”的比例
         */
        @JsonProperty("middling_rate")
        private Double middlingRate;

        /**
         * 不满意占比；满意度评价为“不满意”的比例
         */
        @JsonProperty("dissatisfied_rate")
        private Double dissatisfiedRate;

        /**
         * 升级服务客户数；被推荐给专员或客户群服务的客户数量
         */
        @JsonProperty("upgrade_service_customer_cnt")
        private Long upgradeServiceCustomerCnt;

        /**
         * 邀请添加企业成员客户数；被邀请添加专员作为企业联系人的客户数量
         */
        @JsonProperty("upgrade_service_member_invite_cnt")
        private Long upgradeServiceMemberInviteCnt;

        /**
         * 添加企业成员客户数；成功添加专员作为企业联系人的客户数量
         */
        @JsonProperty("upgrade_service_member_customer_cnt")
        private Long upgradeServiceMemberCustomerCnt;

        /**
         * 邀请加入客户群次数；向客户发出加入客户群邀请的数量
         */
        @JsonProperty("upgrade_service_groupchat_invite_cnt")
        private Long upgradeServiceGroupchatInviteCnt;

        /**
         * 加入客户群客户数；成功加入客户群的客户数量
         */
        @JsonProperty("upgrade_service_groupchat_customer_cnt")
        private Long upgradeServiceGroupchatCustomerCnt;

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

        public Double getReplyRate() {
            return replyRate;
        }

        public void setReplyRate(Double replyRate) {
            this.replyRate = replyRate;
        }

        public Double getFirstReplyAverageSec() {
            return firstReplyAverageSec;
        }

        public void setFirstReplyAverageSec(Double firstReplyAverageSec) {
            this.firstReplyAverageSec = firstReplyAverageSec;
        }

        public Long getSatisfactionInvestgateCnt() {
            return satisfactionInvestgateCnt;
        }

        public void setSatisfactionInvestgateCnt(Long satisfactionInvestgateCnt) {
            this.satisfactionInvestgateCnt = satisfactionInvestgateCnt;
        }

        public Double getSatisfactionParticipationRate() {
            return satisfactionParticipationRate;
        }

        public void setSatisfactionParticipationRate(Double satisfactionParticipationRate) {
            this.satisfactionParticipationRate = satisfactionParticipationRate;
        }

        public Double getSatisfiedRate() {
            return satisfiedRate;
        }

        public void setSatisfiedRate(Double satisfiedRate) {
            this.satisfiedRate = satisfiedRate;
        }

        public Double getMiddlingRate() {
            return middlingRate;
        }

        public void setMiddlingRate(Double middlingRate) {
            this.middlingRate = middlingRate;
        }

        public Double getDissatisfiedRate() {
            return dissatisfiedRate;
        }

        public void setDissatisfiedRate(Double dissatisfiedRate) {
            this.dissatisfiedRate = dissatisfiedRate;
        }

        public Long getUpgradeServiceCustomerCnt() {
            return upgradeServiceCustomerCnt;
        }

        public void setUpgradeServiceCustomerCnt(Long upgradeServiceCustomerCnt) {
            this.upgradeServiceCustomerCnt = upgradeServiceCustomerCnt;
        }

        public Long getUpgradeServiceMemberInviteCnt() {
            return upgradeServiceMemberInviteCnt;
        }

        public void setUpgradeServiceMemberInviteCnt(Long upgradeServiceMemberInviteCnt) {
            this.upgradeServiceMemberInviteCnt = upgradeServiceMemberInviteCnt;
        }

        public Long getUpgradeServiceMemberCustomerCnt() {
            return upgradeServiceMemberCustomerCnt;
        }

        public void setUpgradeServiceMemberCustomerCnt(Long upgradeServiceMemberCustomerCnt) {
            this.upgradeServiceMemberCustomerCnt = upgradeServiceMemberCustomerCnt;
        }

        public Long getUpgradeServiceGroupchatInviteCnt() {
            return upgradeServiceGroupchatInviteCnt;
        }

        public void setUpgradeServiceGroupchatInviteCnt(Long upgradeServiceGroupchatInviteCnt) {
            this.upgradeServiceGroupchatInviteCnt = upgradeServiceGroupchatInviteCnt;
        }

        public Long getUpgradeServiceGroupchatCustomerCnt() {
            return upgradeServiceGroupchatCustomerCnt;
        }

        public void setUpgradeServiceGroupchatCustomerCnt(Long upgradeServiceGroupchatCustomerCnt) {
            this.upgradeServiceGroupchatCustomerCnt = upgradeServiceGroupchatCustomerCnt;
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
                    .append("replyRate", replyRate)
                    .append("firstReplyAverageSec", firstReplyAverageSec)
                    .append("satisfactionInvestgateCnt", satisfactionInvestgateCnt)
                    .append("satisfactionParticipationRate", satisfactionParticipationRate)
                    .append("satisfiedRate", satisfiedRate)
                    .append("middlingRate", middlingRate)
                    .append("dissatisfiedRate", dissatisfiedRate)
                    .append("upgradeServiceCustomerCnt", upgradeServiceCustomerCnt)
                    .append("upgradeServiceMemberInviteCnt", upgradeServiceMemberInviteCnt)
                    .append("upgradeServiceMemberCustomerCnt", upgradeServiceMemberCustomerCnt)
                    .append("upgradeServiceGroupchatInviteCnt", upgradeServiceGroupchatInviteCnt)
                    .append("upgradeServiceGroupchatCustomerCnt", upgradeServiceGroupchatCustomerCnt)
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
