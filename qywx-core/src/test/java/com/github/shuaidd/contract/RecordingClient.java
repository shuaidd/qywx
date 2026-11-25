package com.github.shuaidd.contract;

import feign.Client;
import feign.Request;
import feign.Response;
import feign.Util;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

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
     * 预置一个响应；下一次请求优先消费这里面的响应
     *
     * @param status HTTP 状态码
     * @param body   响应体；null 表示没有响应体
     * @return this
     */
    public RecordingClient respond(int status, String body) {
        pending.add(new Stub(status, body));
        return this;
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        requests.add(request);
        Stub stub = pending.poll();
        int status = stub == null ? 200 : stub.status;
        String body = stub == null ? DEFAULT_BODY : stub.body;

        Response.Builder builder = Response.builder()
                .protocolVersion(Request.ProtocolVersion.HTTP_1_1)
                .status(status)
                .reason("contract")
                .headers(Collections.emptyMap())
                .request(request);
        if (request.requestTemplate() != null) {
            builder.requestTemplate(request.requestTemplate());
        }
        if (body != null) {
            builder.body(body, Util.UTF_8);
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
        private final String body;

        private Stub(int status, String body) {
            this.status = status;
            this.body = body;
        }
    }
}
