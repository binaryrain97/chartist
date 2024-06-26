package com.example.chartist.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private static final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nickname = (String)session.getAttributes().get("nickname");
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName(); // 현재 인증된 사용자의 이름 가져오기
        }
        for(WebSocketSession sess: sessions.values()) {
            String messageToSend = nickname + ":" + payload;
            sess.sendMessage(new TextMessage(messageToSend));
        }
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String nickname = "익명" + session.getId();
        session.getAttributes().put("nickname", nickname);
        sessions.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
    }
}