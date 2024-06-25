package com.example.chartist.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class ChatHandler extends TextWebSocketHandler {
    private static final Map<WebSocketSession, String> map = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        for(WebSocketSession sess: map.keySet()) {
            sess.sendMessage(message);
        }
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String nickname = "익명" + session.getId();
        map.put(session, nickname);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        map.remove(session);
    }
}