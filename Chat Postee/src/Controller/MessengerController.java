/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bubble.BubbleSpec;
import Bubble.BubbledLabel;
import Client.API_AUTHEN_CLIENT;
import Messages.*;
import MessageObject.*;
import Client.Client;
import Client.ClientEndpoint;
import Decoder.BASE64Decoder;
import MongodbHelper.MongoHelper;
import animatefx.animation.*;
import com.google.gson.Gson;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import hrm.postee.Temp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import static java.lang.String.format;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import org.glassfish.tyrus.client.ClientManager;
import org.imgscalr.Scalr;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
@javax.websocket.ClientEndpoint(encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class MessengerController implements Initializable {
        
    //Tab buttons
    @FXML
    private AnchorPane btnInfo;
    @FXML
    private AnchorPane btnPay;
    @FXML
    private AnchorPane btnSignOut;
    @FXML
    private TextField filterField;

    @FXML
    private ListView listUser;
    @FXML
    private TextField txtMsg;
    @FXML
    private ListView chatScr = new ListView();
    @FXML
    private ImageView btnSend;
    public static ArrayList messageList = new ArrayList();
    
    //Menu
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane menu; 
    @FXML
    private AnchorPane menubar;
    
    @FXML
    void OpenMenu(MouseEvent event) {
        drawer.open();
        menubar.setVisible(false);
    }
    
    @FXML
    void CloseMenu(MouseEvent event) {
        drawer.close();
        menubar.setVisible(true);
    }
    
    @FXML
    private void Info(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Info.fxml"));
        Scene scene = new Scene(parent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
    
        
    @FXML
    private void Payslip(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Payslip.fxml"));
        Scene scene = new Scene(parent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void HRApp(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/HRApp.fxml"));
        Scene scene = new Scene(parent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void SignOut(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/SignIn.fxml"));
        Scene scene = new Scene(parent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    void listSel(MouseEvent event) {
        chatScr.getItems().clear();
        
        try
        {
            for(int i=0; i<listUser.getItems().size();i++)
            {
                //listUser.getItems().get(i)
                if(((Text)((TextFlow)((HBox)listUser.getItems().get(i)).getChildren().get((2))).getChildren().get(0)).getText().equals(indexC))
                {
                    ((Text)((TextFlow)((HBox)listUser.getItems().get(i)).getChildren().get((2))).getChildren().get(0)).setFill(Color.RED);
                }
            }
            TextFlow txtFl = (TextFlow)((HBox)listUser.getSelectionModel().getSelectedItem()).getChildren().get((2));
            ((Text)((TextFlow)((HBox)listUser.getSelectionModel().getSelectedItem()).getChildren().get((2))).getChildren().get(0)).setFill(Color.GREEN);
            String id = ((Text)((TextFlow)((HBox)listUser.getSelectionModel().getSelectedItem()).getChildren().get((2))).getChildren().get(0)).getText();
            ArrayList<MessageObject> ListMsg = (ArrayList<MessageObject>)htb.get(id);
            System.out.println("Size is: "+ListMsg.size());
            for(int i=0;i<ListMsg.size();i++)
            {
                Image image = new Image(getClass().getClassLoader().getResource(urlAvt).toString());
                ImageView profileImage = new ImageView(image);
                profileImage.setFitHeight(32);
                profileImage.setFitWidth(32);
                System.out.println("TEST DATA"+ ListMsg.get(i).content);
                if(!ListMsg.get(i).idSender.equals(Temp.getUser()))
                {
                    BubbledLabel bl = new BubbledLabel(BubbleSpec.FACE_LEFT_CENTER);
                    bl.relocate(10, 50);
                    bl.setText(ListMsg.get(i).content);
                    bl.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, null, null)));
                    HBox x = new HBox();
                    x.setMaxWidth(chatScr.getWidth() - 20);
                    x.setAlignment(Pos.CENTER_LEFT);
                    x.getChildren().addAll(profileImage, bl);
                    chatScr.getItems().add(x);
                    new SlideInUp(x).play();
                }
                else
                {
                    BubbledLabel bl = new BubbledLabel(BubbleSpec.FACE_RIGHT_CENTER);
                    bl.relocate(10, 50);
                    bl.setText(ListMsg.get(i).content);
                    bl.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, null, null)));
                    HBox x = new HBox();
                    x.setMaxWidth(chatScr.getWidth() - 20);
                    x.setAlignment(Pos.CENTER_RIGHT);
                    x.getChildren().addAll(bl, profileImage);
                    chatScr.getItems().add(x);
                    new SlideInUp(x).play();
                }
            }

            change(id);
        }
        catch(Exception ex)
        {

        }
    }
    public void change(String id)
    {
        indexC = id;
        
    }
    @FXML
    void ChatTo(KeyEvent event) throws Exception {
        if (event.getCode() == KeyCode.ENTER){
            String id = filterField.getText();
            indexC = id;Image image = new Image(getClass().getClassLoader().getResource("ico/ava.png").toString());
            ImageView profileImage = new ImageView(image);
            profileImage.setFitHeight(40);
            profileImage.setFitWidth(40);
            Label blank = new Label();
            blank.setText("     ");
            TextFlow textFlowPane = new TextFlow();
            Text redText = new Text(id);
            redText.setFill(Color.RED);
            textFlowPane.getChildren().addAll(redText);
            HBox hb = new HBox();
            hb.getChildren().addAll(profileImage, blank, textFlowPane);
            listUser.getItems().add(hb);
        }
    }
    @FXML
    void SendMsg(KeyEvent event) throws Exception {
        if (event.getCode() == KeyCode.ENTER){
            String msg = txtMsg.getText();
            if(!"".equals(msg)){
                Message message1 = new Message();
                message1.setMessage("Gửi Message");
                message1.setType(0);
                
                MessageObject msgObj = new MessageObject();
                msgObj.idReceive = indexC;
                msgObj.idSender = Temp.getUser();
                msgObj.content = msg;
                msgObj.type = "k";
                msgObj.nameSender = "kkkkk";
                message1.setData(msgObj);
                
                API_AUTHEN_CLIENT.sendMessage(message1, Temp.getSession());
                
                        Thread thread = new Thread(){
                public void run(){
                  ArrayList<MessageObject> arr = new ArrayList<MessageObject>();
                        if(htb.containsKey(msgObj.idReceive))
                        {
                            arr = (ArrayList<MessageObject>)htb.get(msgObj.idReceive);
                            htb.remove(msgObj.idReceive);
                        }
                        arr.add(msgObj);
                        htb.put(msgObj.idReceive, arr);
                        System.out.println("SIZE HASHTABLE: "+htb.size());
                }
              };
             thread.start();
                txtMsg.setText("");
                Image image = new Image(getClass().getClassLoader().getResource("ico/ava.png").toString());
                ImageView profileImage = new ImageView(image);
                profileImage.setFitHeight(32);
                profileImage.setFitWidth(32);
                BubbledLabel bl = new BubbledLabel(BubbleSpec.FACE_RIGHT_CENTER);
                bl.relocate(10, 50);
                bl.setText(msg);
                bl.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, null, null)));
                HBox x = new HBox();
                x.setMaxWidth(chatScr.getWidth() - 20);
                x.setAlignment(Pos.CENTER_RIGHT);
                x.getChildren().addAll(bl, profileImage);
                chatScr.getItems().add(x);
                new SlideInUp(x).play();
            }
        }
    }
    
    
    @FXML
    void Send(MouseEvent event) {
        Image image = new Image(getClass().getClassLoader().getResource("ico/ava.png").toString());
        ImageView profileImage = new ImageView(image);
        profileImage.setFitHeight(40);
        profileImage.setFitWidth(40);
        Label blank = new Label();
        blank.setText("     ");
        Label msg = new Label();
        msg.setText("hello");
        HBox hb = new HBox();
        hb.getChildren().addAll(profileImage, blank, msg);
        listUser.getItems().add(hb);
       
//        new Pulse(btnSend).play();
//        String msg = txtMsg.getText();
//        if(!"".equals(msg)){
//            txtMsg.setText("");
//            Image image = new Image(getClass().getClassLoader().getResource("ico/ava.png").toString());
//            ImageView profileImage = new ImageView(image);
//            profileImage.setFitHeight(32);
//            profileImage.setFitWidth(32);
//
//            BubbledLabel bl = new BubbledLabel(BubbleSpec.FACE_LEFT_CENTER);
//            bl.relocate(10, 50);
//            bl.setText(msg);
//            bl.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, null, null)));
//
//            HBox x = new HBox();
//            x.setMaxWidth(chatScr.getWidth() - 20);
//            x.setAlignment(Pos.CENTER_LEFT);
//            x.getChildren().addAll(profileImage, bl);
//            chatScr.getItems().add(x);
//            new SlideInUp(x).play();
//        }
    }
    
    @OnOpen
    public void onOpen(Session session) {
        session.setMaxTextMessageBufferSize(20000);
        session.setMaxBinaryMessageBufferSize(20000);
        System.out.println(format("Connection established. session id: " + session.getId()));
    }
    
    @OnMessage(maxMessageSize = 1024000)
    public void onMessage(String messageJson, Session session) {
        System.out.println("NHẬN TIN");
        session.setMaxTextMessageBufferSize(20000);
        session.setMaxBinaryMessageBufferSize(20000);
        messageList.add(messageJson);
        System.out.println(messageJson+" "+messageList.size());
    }
    
    public static String indexC = "quocbao123";
    public static Hashtable htb = new Hashtable();
    public static ArrayList listIdChat = new ArrayList();
    public static String urlAvt="ico/ava.png";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drawer.setSidePane(menu);
        Platform.setImplicitExit(false);
        Thread thread = new Thread(){
                public void run(){
                  while(true)
                     {
                         //System.out.println(MessengerController.messageList.size());
                         if(messageList.size()>0)
                            {
                                   Gson gson = new Gson();
                                   Message message = gson.fromJson(messageList.get(0).toString(), Message.class);
                                   messageList.remove(0);
                                   System.out.println("đã nhận tin");
                                   System.out.println(gson.toJson(message));
                                   MessageObject msgObj = gson.fromJson(gson.toJson(message.getData()),MessageObject.class);
                                   ArrayList<MessageObject> arr = new ArrayList<MessageObject>();
                                   if(!htb.containsKey(msgObj.idSender))
                                   {
                                       listIdChat.add(msgObj.idSender);
                                       Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                Image image = new Image(getClass().getClassLoader().getResource("ico/ava.png").toString());
                                                ImageView profileImage = new ImageView(image);
                                                profileImage.setFitHeight(40);
                                                profileImage.setFitWidth(40);

                                                Label blank = new Label();
                                                blank.setText("     ");
                                                TextFlow textFlowPane = new TextFlow();
                                                Text redText = new Text(msgObj.idSender);
                                                redText.setFill(Color.RED);
                                                textFlowPane.getChildren().addAll(redText);
//                                                Label msg = new Label();
//                                                msg.setText(msgObj.idSender);

                                                HBox hb = new HBox();
                                                hb.getChildren().addAll(profileImage, blank, textFlowPane);
                                                listUser.getItems().add(hb);
                                            }
                                        });
                                       
                                   }
                                   else
                                   {
                                       arr = (ArrayList<MessageObject>)htb.get(msgObj.idSender);
                                       htb.remove(msgObj.idSender);
                                   }
                                   arr.add(msgObj);
                                   htb.put(msgObj.idSender, arr);
                                   String msg = msgObj.content;
                                   System.out.println((new Gson()).toJson(htb));
                                   if(msgObj.idSender.equals(indexC))
                                   {
                                       if(msgObj.type.equals("image"))
                                       {
                                           Image image = new Image(getClass().getClassLoader().getResource(urlAvt).toString());
                                            ImageView profileImage = new ImageView(image);
                                            profileImage.setFitHeight(32);
                                            profileImage.setFitWidth(32);

                                            BufferedImage bi;
                                           try {
                                               bi = Scalr.resize(decodeBase64(msgObj.content), Scalr.Mode.FIT_EXACT, 250, 300);
                                                WritableImage wi = new WritableImage(250, 300);
                                                Image imgMsg = SwingFXUtils.toFXImage(bi,wi);
                                                ImageView imgViewSend = new ImageView(image);
                                                HBox x = new HBox();
                                                x.setMaxWidth(chatScr.getWidth() - 20);
                                                x.setAlignment(Pos.CENTER_LEFT);
                                                x.getChildren().addAll(profileImage, imgViewSend);
                                                chatScr.getItems().add(x);
                                                new SlideInUp(x).play();
                                           } catch (IOException ex) {
                                               Logger.getLogger(MessengerController.class.getName()).log(Level.SEVERE, null, ex);
                                           }
                                       }
                                       else
                                       {
                                            Image image = new Image(getClass().getClassLoader().getResource(urlAvt).toString());
                                            ImageView profileImage = new ImageView(image);
                                            profileImage.setFitHeight(32);
                                            profileImage.setFitWidth(32);

                                            BubbledLabel bl = new BubbledLabel(BubbleSpec.FACE_LEFT_CENTER);
                                            bl.relocate(10, 50);
                                            bl.setText(msg);
                                            bl.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, null, null)));

                                            HBox x = new HBox();
                                            x.setMaxWidth(chatScr.getWidth() - 20);
                                            x.setAlignment(Pos.CENTER_LEFT);
                                            x.getChildren().addAll(profileImage, bl);
                                            chatScr.getItems().add(x);
                                            new SlideInUp(x).play();
                                       }
                                   }
                             }
                      try {
                          Thread.sleep(10);
                      } catch (InterruptedException ex) {
                          Logger.getLogger(MessengerController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                     }
                }
              };
             thread.start();
        
        String SERVER = "ws://42.113.213.148:6652/ws/chat";
        ClientManager client = ClientManager.createClient();
        try {
            MongoHelper mongoHelper = new MongoHelper();
            Session session = client.connectToServer(MessengerController.class, new URI(SERVER + "/" + Temp.getAuthorization()));
            session.setMaxTextMessageBufferSize(300*1024 * 1024);
            session.setMaxBinaryMessageBufferSize(300*1024 * 1024);
        
            Temp.setSession(session);
        } catch (Exception ex) {
        }
    }
    public BufferedImage decodeBase64(String base64) throws IOException	{
        try
        {
            BufferedImage image = null;
            byte[] imageByte;
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(base64);
            try (ByteArrayInputStream bis = new ByteArrayInputStream(imageByte)) {
                image = ImageIO.read(bis);
            }
            File outputfile = new File("image.png");
            ImageIO.write(image, "png", outputfile);
            return image;
        }
        catch(IOException ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }
}
