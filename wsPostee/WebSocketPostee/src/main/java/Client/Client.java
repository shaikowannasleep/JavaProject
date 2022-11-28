/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author mac
 */
import MongodbHelper.DataHelper;
import Functions.PostData;
import static JsonUtil.JsonUtil.formatMessage;
import MesageObject.MessageObject;
import Model.Message;
import MongodbHelper.MongoHelper;
import com.google.gson.Gson;

import java.net.URI;
import java.util.Date;
import java.util.Scanner;
import javax.websocket.Session;
import org.glassfish.tyrus.client.ClientManager;

public class Client {

    public static final String SERVER = "ws://42.112.189.240:7777/ws/chat";//42.117.107.203:6652

    public static void main(String[] args) throws Exception {
        ClientManager client = ClientManager.createClient();

        // connect to server
        Scanner scanner = new Scanner(System.in);
        PostData postData = new PostData();
        MongoHelper mongoHelper = new MongoHelper();
        
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        
        String autho = postData.Login(username, password);
        String idOnServer = postData.GetIDByAuthorization(autho);
        //String autho = postData.Login("admin","1234");
        System.out.println("Welcome to Tiny Chat!"+autho);
        //String user = postData.GetUsername(autho);
        //String username = scanner.nextLine();

        Session session = client.connectToServer(ClientEndpoint.class, new URI(SERVER+"/"+autho));
       
        Message sendMessage = new Message();
        System.out.println("Nhập người nhận");
        String nguoiNhan = scanner.next();
        // repeatedly read a message and send it to the server (until quit)
        boolean check = true;
        while (check) {
                System.out.println("Nhập tin nhắn");
                String s = scanner.nextLine();
                 Message message1 = new Message();
            message1.setMessage("Gửi Message");
            message1.setType(0);
            //message1.
            MessageObject msg = new MessageObject();
            msg.idReceive = nguoiNhan;
            msg.idSender = username;
            msg.content = s;
            msg.type = "k";
            msg.nameSender = "kkkkk";
            message1.setData(msg);
            //System.out.println(formatMessage("You are logged in as: " + username, username));
            System.out.println("You are logged in as: " + username);
            //session.getBasicRemote().sendText(formatMessage((new Gson()).toJson(message1), username));
            API_AUTHEN_CLIENT.sendMessage(message1,session);
        }
    }

}
