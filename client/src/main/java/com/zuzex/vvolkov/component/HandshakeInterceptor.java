package com.zuzex.vvolkov.component;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    public final List<String> hostList = new ArrayList<>();

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception
    {
        System.err.println("### Remote host: " + request.getRemoteAddress().getHostName());
        hostList.add(request.getRemoteAddress().getHostName());

        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
}
