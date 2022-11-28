/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Messages.Message;
import com.google.gson.Gson;
import java.io.IOException;
import javax.websocket.EncodeException;
import javax.websocket.Session;

/**
 *
 * @author memof
 */
public class API_AUTHEN_CLIENT {
    public static void sendMessage(Message m, Session session) throws IOException, EncodeException
    {
        session.getBasicRemote().sendText((new Gson()).toJson(m));
    }
}
