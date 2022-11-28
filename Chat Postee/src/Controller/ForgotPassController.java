/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.SetPassController.PassValid;
import static Controller.SignUpController.EmailValid;
import DAO.PostData;
import animatefx.animation.Pulse;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class ForgotPassController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    
    //Page switching buttons
    @FXML
    private Button btnNext;
    @FXML
    private Button btnCancel;
    
    //Inputs
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPass1;
    @FXML
    private PasswordField txtPass2;
    
    //Error messages
    @FXML
    private Label emailError;
    @FXML
    private Label passError1;
    @FXML
    private Label passError2;
    
    //Text panes
    @FXML
    private AnchorPane paneEmail;
    @FXML
    private AnchorPane panePass1;
    @FXML
    private AnchorPane panePass2;
    
    
    @FXML
    private void Send(ActionEvent event) throws IOException, Exception {

        if(PassValid(txtPass1.getText()) && PassValid(txtPass2.getText()) && txtPass1.getText().equals(txtPass2.getText()) && EmailValid(txtEmail.getText()))  {


            PostData postData = new PostData();
            postData.GetNewPass(txtEmail.getText(), txtPass1.getText());
            
                if(Temp.getCode().equals("801")){ 
                    emailError.setText("*Email doesn't exists !");
                    txtEmail.setText("");
                    txtPass1.setText("");
                    txtPass2.setText("");
                }else   {
                    Parent parent = FXMLLoader.load(getClass().getResource("/FXML/SignIn.fxml"));
                    Scene scene = new Scene(parent);

                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(scene);
                    window.show(); 
                }
            
            
        }else {

            if(EmailValid(txtEmail.getText()) == false) {
                emailError.setText("*Email invalid !");
                txtEmail.setText("");
                txtPass1.setText("");
                txtPass2.setText("");
            }else{
                emailError.setText("");
            } 
            
            if(PassValid(txtPass1.getText()) == false) {
                passError1.setText("*Password invalid !");
                txtPass1.setText("");
                txtPass2.setText("");
            }else{
                passError1.setText("");
            }
            if(!txtPass2.getText().equals(txtPass1.getText()) == false) {
                passError2.setText("* Password doesn't match !");
                txtPass1.setText("");
                txtPass2.setText("");
            }else{
                passError2.setText("");
            }
        }
    }
    
    @FXML
    private void Cancel(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/SignIn.fxml"));
        Scene scene = new Scene(parent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(paneEmail).play();
                }
            }
        }); 
        txtPass1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(panePass1).play();
                }
            }
        });
        txtPass2.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(panePass2).play();
                }
            }
        });
    }    
    
}
