/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrmws;

/**
 *
 * @author mac
 */
import Model.ListMessage;
import Model.User;
import MongodbHelper.DataHelper;
import static JsonUtil.JsonUtil.formatMessage;
import static java.lang.String.format;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import Model.Message;
import Model.MessageDecoder;
import Model.MessageEncoder;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.websocket.server.PathParam;
import org.json.JSONObject;

@javax.websocket.server.ServerEndpoint(value = "/chat/{token}", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ServerEndpoint {

    public static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    public static HashMap<String, Session> listUser = new HashMap();
    public static HashMap<String, String> listUserSS = new HashMap();
    @OnOpen
    public void onOpen(@PathParam("token") String token, Session session) throws IOException, EncodeException, Exception {
        String id = AUTHEN_API.getUsernameByAuthenticationToken(token);
        System.out.println((new Gson()).toJson(id));
        if(id!=null||!id.trim().equals(""))
        {
            session.setMaxBinaryMessageBufferSize(100*1024*1024);
            session.setMaxTextMessageBufferSize(300*1024*1024);
            listUser.put(id, session);
            listUserSS.put(session.getId(), id);
            peers.add(session);
        }
        else
        {
            System.out.println("close");
            session.close();
        }
    }

    @OnMessage
    public void onMessage(String messageJson, Session session) throws IOException, EncodeException {
        if(listUserSS.containsKey(session.getId()))
        {
            Message message = (new Gson()).fromJson(messageJson, Message.class);
            System.out.println(messageJson);
            AUTHEN_API.decocdeMessage(message, session);
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        System.out.println(session.getId() + " left the chat");
        DataHelper.remove_From_DB_Postee("sessionId", session.getId(), "userData");
        peers.remove(session);
        listUser.remove(listUserSS.get(session.getId()));
        listUserSS.remove(session.getId());
//        for (Session peer : peers) {
//            Message chatMessage = new Message();
//            chatMessage.setSender("Server");
//            chatMessage.setContent(format("%s left the chat.", (String) session.getUserProperties().get("user")));
//            chatMessage.setReceived(peer.getId());
//            peer.getBasicRemote().sendObject(chatMessage);
//        }
    }
}
