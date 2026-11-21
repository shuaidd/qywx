package com.github.shuaidd.response.tool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.tool.TmpExternalUserIdResult;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * tmp_external_userid 转换响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmpExternalUserIdConvertResponse extends AbstractBaseResponse {

    @JsonProperty("results")
    private List<TmpExternalUserIdResult> results;

    @JsonProperty("invalid_tmp_external_userid_list")
    private List<String> invalidTmpExternalUserIdList;

    public List<TmpExternalUserIdResult> getResults() {
        return results;
    }

    public void setResults(List<TmpExternalUserIdResult> results) {
        this.results = results;
    }

    public List<String> getInvalidTmpExternalUserIdList() {
        return invalidTmpExternalUserIdList;
    }

    public void setInvalidTmpExternalUserIdList(List<String> invalidTmpExternalUserIdList) {
        this.invalidTmpExternalUserIdList = invalidTmpExternalUserIdList;
    }
}
