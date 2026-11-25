package com.github.shuaidd.contract;

import feign.Client;
import feign.Request;
import feign.Response;
import feign.Util;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述 契约回放用的假 HTTP 客户端：记录 Feign 实际发出的请求，并按预置顺序返回响应
 *
 * @author ddshuai
 * date 2026-11-26 00:30
 **/
public class RecordingClient implements Client {

    public static final String PUBLIC_PATH = "/cgi-bin";

    public static final String TARGET_URL = "http://contract.invalid" + PUBLIC_PATH;

    public static final String DEFAULT_BODY = "{\"errcode\":0,\"errmsg\":\"ok\"}";

    private final List<Request> requests = new ArrayList<>();

    private final Deque<Stub> pending = new ArrayDeque<>();

    /**
     * 预置一个文本响应；下一次请求优先消费这里面的响应
     *
     * @param status HTTP 状态码
     * @param body   响应体；null 表示没有响应体
     * @return this
     */
    public RecordingClient respond(int status, String body) {
        return respond(status, body == null ? null : body.getBytes(Util.UTF_8), null);
    }

    /**
     * 预置一个带自定义响应头的响应（下载场景需要 Content-Disposition、error-code 等）
     *
     * @param status  HTTP 状态码
     * @param body    响应体；null 表示没有响应体
     * @param headers 响应头；null 表示没有响应头
     * @return this
     */
    public RecordingClient respond(int status, byte[] body, Map<String, ? extends Collection<String>> headers) {
        pending.add(new Stub(status, body, headers));
        return this;
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        requests.add(request);
        Stub stub = pending.poll();
        int status = stub == null ? 200 : stub.status;
        byte[] body = stub == null ? DEFAULT_BODY.getBytes(Util.UTF_8) : stub.body;
        Map<String, Collection<String>> headers = new LinkedHashMap<>();
        if (stub != null && stub.headers != null) {
            headers.putAll(stub.headers);
        }

        Response.Builder builder = Response.builder()
                .protocolVersion(Request.ProtocolVersion.HTTP_1_1)
                .status(status)
                .reason("contract")
                .headers(headers)
                .request(request);
        if (request.requestTemplate() != null) {
            builder.requestTemplate(request.requestTemplate());
        }
        if (body != null) {
            builder.body(body);
        }
        return builder.build();
    }

    public Request request(int index) {
        return requests.get(index);
    }

    public List<Request> requests() {
        return Collections.unmodifiableList(requests);
    }

    public int requestCount() {
        return requests.size();
    }

    private static final class Stub {
        private final int status;
        private final byte[] body;
        private final Map<String, ? extends Collection<String>> headers;

        private Stub(int status, byte[] body, Map<String, ? extends Collection<String>> headers) {
            this.status = status;
            this.body = body;
            this.headers = headers;
        }
    }
}
