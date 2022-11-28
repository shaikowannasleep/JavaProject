/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PostData;
import animatefx.animation.*;
import com.jfoenix.controls.JFXDrawer;
import hrm.postee.Temp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class SignInController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;
    
    //FaceID pane
    @FXML
    private JFXDrawer drawer;
    @FXML
    private StackPane paneFaceId;
    @FXML
    private Button closeCam;
    
    //Buttons
    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnSignUp;
    @FXML
    private AnchorPane btnFaceId;
    @FXML
    private Label btnForgot;
    
    //Inputs
    @FXML
    private AnchorPane userpane;
    @FXML
    private TextField txtUser;
    @FXML
    private AnchorPane passpane;
    @FXML
    private PasswordField txtPass;
    
    
    //Error messages
    @FXML
    private Label error;
    
    @FXML
    private void FaceID(MouseEvent event) throws IOException {
        Temp.setMode("Login");
        closeCam.setVisible(true);
        drawer.open();
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/FXML/FaceID.fxml"));
        paneFaceId.getChildren().add(newLoadedPane);
    }
    
    @FXML
    private void closeCam(MouseEvent event) {
        closeCam.setVisible(false);
        drawer.close();
    }
    
    @FXML
    private void SignIn(ActionEvent event) throws IOException {
        new Pulse(btnSignIn).play();
        try {
            PostData postData = new PostData();
            String authorization = postData.Login(txtUser.getText(), txtPass.getText());
            Temp.setAuthorization(authorization);
            
            if(authorization  == null) {
                error.setText("*Incorrect username or password!");
                txtUser.setText("");
                txtPass.setText("");
            }else {
                postData.GetInforByAuthorization(authorization);
                Temp.setUser(txtUser.getText());
                Temp.setPass(txtPass.getText());
                
                Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Info.fxml"));
                Scene scene = new Scene(parent);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                window.setScene(scene);
                window.show();
            }
        } catch (Exception e1) {
        }
    }
    
    @FXML
    void Enter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER){
            new Pulse(btnSignIn).play();
        try {
            PostData postData = new PostData();
            String authorization = postData.Login(txtUser.getText(), txtPass.getText());
            Temp.setAuthorization(authorization);
            
            if(authorization  == null) {
                error.setText("*Incorrect username or password!");
                txtUser.setText("");
                txtPass.setText("");
            }else {
                postData.GetInforByAuthorization(authorization);
                Temp.setUser(txtUser.getText());
                Temp.setPass(txtPass.getText());

                Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Info.fxml"));
                Scene scene = new Scene(parent);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
        } catch (Exception e1) {
        }
        }
    }

    
    @FXML
    private void SignUp(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/SignUp.fxml"));
        Scene scene = new Scene(parent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void ForgotPass(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/ForgotPass.fxml"));
        Scene scene = new Scene(parent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        (paneFaceId).setVisible(false);
        closeCam.setVisible(false);
        drawer.setSidePane(paneFaceId);
        
        txtUser.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(userpane).play();
                }
            }
        });
        
        txtPass.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(passpane).play();
                }
            }
        });
    }    
    
}
