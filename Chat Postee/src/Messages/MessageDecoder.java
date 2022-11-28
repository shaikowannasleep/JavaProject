/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messages;

import com.google.gson.Gson;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author mac
 */
public class MessageDecoder implements Decoder.Text<Message>{

    @Override
    public Message decode(final String textMessage) throws DecodeException {
        Message message = new Message();
        JsonObject jsonObject = Json.createReader(new StringReader(textMessage)).readObject();
        message.setType(Integer.parseInt(jsonObject.getString("type")));
        message.setMessage(jsonObject.getString("message"));
        Object obj = (new Gson()).fromJson(jsonObject.getString("data"), Object.class);
        message.setData(obj);
        return message;
    }

    @Override
    public boolean willDecode(String arg0) {
        return true;
    }

    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
    
}
