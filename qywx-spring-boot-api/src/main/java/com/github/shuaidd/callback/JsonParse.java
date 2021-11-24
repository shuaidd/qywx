package com.github.shuaidd.callback;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * JsonParse class
 * <p>
 * 提供提取消息格式中的密文及生成回复消息格式的接口.
 */
class JsonParse {

    /**
     * 提取出 JSON 包中的加密消息
     *
     * @param jsontext 待提取的json字符串
     * @return 提取出的加密消息字符串
     * @throws AesException
     */
    public static Object[] extract(String jsontext) throws AesException {
        Object[] result = new Object[3];
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonText json = objectMapper.readValue(jsontext, JsonText.class);

            result[0] = json.getToUserName();
            result[1] = json.getEncryptMsg();
            result[2] = json.getAgentId();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.PARSE_JSON_ERROR);
        }
    }

    /**
     * 生成json消息
     *
     * @param encrypt   加密后的消息密文
     * @param signature 安全签名
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     * @return 生成的json字符串
     */
    public static String generate(String encrypt, String signature, String timestamp, String nonce) {
        String format = "{\"encrypt\":\"%1$s\",\"msgsignature\":\"%2$s\",\"timestamp\":\"%3$s\",\"nonce\":\"%4$s\"}";
        return String.format(format, encrypt, signature, timestamp, nonce);
    }
}
