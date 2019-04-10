package com.github.shuaidd.client.config;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-03 16:02
 **/
public class ApplicationProperties {
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
}
