package com.github.shuaidd.client.config;

/**
 * 描述 应用授权信息配置
 *
 * @author ddshuai
 * date 2019-04-03 16:02
 **/

public class ApplicationProperties {

    /**
     * 应用归属企业 默认取 qywx.corpId
     */
    private String corpId;

    /**
     * 应用密匙
     */
    private String secret;

    /**
     * 应用的编号
     */
    private String agentId;

    /**
     * 应用别名
     */
    private String applicationName;

    /**
     * 应用描述
     */
    private String applicationDesc;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationDesc() {
        return applicationDesc;
    }

    public void setApplicationDesc(String applicationDesc) {
        this.applicationDesc = applicationDesc;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }
}
