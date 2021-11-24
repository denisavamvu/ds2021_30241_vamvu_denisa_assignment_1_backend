package ro.tuc.ds2020.controllers;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint("/notification/{client_id}")
@Component
public class WebSocketNotification {
    private static final Map<String, Session> clients =
            Collections.synchronizedMap(new HashMap<>());

    public static void sendMsg(String userId, String message) throws IOException {
        Session session = clients.get(userId);
        if (session == null) {
            System.out.println("Session doesn't exist for client with clientId: " + userId);
        } else {
            session.getBasicRemote().sendText(message);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Got message from client: "+message);
        if(message.equals("")){
            session.getBasicRemote().sendText("");
        }
    }

    @OnOpen
    public void onOpen(@PathParam("client_id") String clientId, Session session) {
        System.out.println("Created connection for user " + clientId);
        clients.put(clientId, session);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        session.close();
        clients.values().remove(session);
    }

    @OnError
    public void onError(Session session, Throwable thr) {

    }
}