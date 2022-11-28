/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Model.ListMessage;
import MongodbHelper.DataHelper;
import Model.Message;
import static java.lang.String.format;

import Model.MessageDecoder;
import Model.MessageEncoder;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import org.json.JSONObject;

/**
 *
 * @author mac
 */
@javax.websocket.ClientEndpoint(encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ClientEndpoint {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(format("Connection established. session id: " + session.getId()));
    }

    @OnMessage
    public void onMessage(String messageJson, Session session) {
        Gson gson = new Gson();
        Message message = gson.fromJson(messageJson, Message.class);
        System.out.println("đã nhận tin");
        System.out.println(gson.toJson(message));
    }
}
