/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrmws;

import Functions.PostData;
import MesageObject.MessageObject;
import Model.Message;
import static com.hrmws.ServerEndpoint.listUserSS;
import com.google.gson.Gson;
import java.io.IOException;
import javax.websocket.EncodeException;
import javax.websocket.Session;

/**
 *
 * @author memof
 */
public class AUTHEN_API {
    public static String getUsernameByAuthenticationToken(String token) throws Exception
    {
        PostData postData = new PostData();
        return postData.GetUsername(token);
    }
    public static String getNameById(String id)
    {
        return "";
    }
    public static void sendMessage(Message message, Session sessionSender) throws IOException, EncodeException
    {
        MessageObject  msg = (new Gson()).fromJson((new Gson()).toJson(message.getData()), MessageObject.class);
        System.out.println("Sessions:--------"+msg.idReceive+" "+ServerEndpoint.listUser.containsKey(msg.idReceive));
        msg.idSender = ServerEndpoint.listUserSS.get(sessionSender.getId());
        Session sessions = ServerEndpoint.listUser.get(msg.idReceive);
        sessions.setMaxBinaryMessageBufferSize(100*1024*1024);
        sessions.setMaxTextMessageBufferSize(300*1024*1024);
        sessions.getBasicRemote().sendText((new Gson()).toJson(message));
    }
    public static void sendNotification(String idReceive, String content)
    {
        
    }
    public static void sendNotiRef(Message message, Session sessionsReceive, String type)
    {
        switch(type)
        {
            case "seen": {{
            
            
            }; break;}
            case "isReceived": {{
            
                
            }; break;}
        }
    }
    public static void decocdeMessage(Message message, Session session) throws IOException, EncodeException
    {
        session.setMaxBinaryMessageBufferSize(100*1024*1024);
            session.setMaxTextMessageBufferSize(300*1024*1024);
            
        System.out.println("hello1");
        switch(message.getType())
        {
            case 0: {
                System.out.println("hello2");
                sendMessage(message,session);
                break;
            }
            case 1:
            {
                sendNotiRef(message, session, "seen");
                break;
            }
            case 2:
            {
                sendNotiRef(message, session, "isReceived");
                break;
            }
        }
    }
}
