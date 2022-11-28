/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import MessageObject.MessageObject;
import Messages.Message;
import static java.lang.String.format;

import Messages.MessageDecoder;
import Messages.MessageEncoder;
import com.google.gson.Gson;
import hrm.postee.Temp;
import java.text.SimpleDateFormat;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author mac
 */
@javax.websocket.ClientEndpoint(encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ClientEndpoint {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    public void onOpen(Session session) {
        session.setMaxBinaryMessageBufferSize(1 * 1024 * 1024);
        System.out.println(format("Connection established. session id: " + session.getId()));
    }

    @OnMessage(maxMessageSize = 1024 * 1024)
    public void onMessage(String messageJson, Session session) {
        session.setMaxTextMessageBufferSize(300*1024 * 1024);
        session.setMaxBinaryMessageBufferSize(300*1024 * 1024);
        Gson gson = new Gson();
        Message message = gson.fromJson(messageJson, Message.class);
        System.out.println("đã nhận tin");
        System.out.println(gson.toJson(message));
        MessageObject msgObj = gson.fromJson(gson.toJson(message.getData()),MessageObject.class);
        System.out.println(msgObj.content);
    }
}
