/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
public class SetPassController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    //Inputs
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass1;
    @FXML
    private PasswordField txtPass2;
    
    //Page switching buttons
    @FXML
    private Button btnNext;
    @FXML
    private Button btnCancel;
    
    //Text panes
    @FXML
    private AnchorPane paneUser;
    @FXML
    private AnchorPane panePass1;
    @FXML
    private AnchorPane panePass2;
    
    //Error messages
    @FXML
    private Label userError;
    @FXML
    private Label passError1;
    @FXML
    private Label passError2;
    
    @FXML
    private void Next(ActionEvent event) throws IOException {
        
        if(PassValid(txtPass1.getText()) && PassValid(txtPass2.getText()) && txtPass1.getText().equals(txtPass2.getText()) && UserValid(txtUser.getText()))  {

            Temp.setUser(txtUser.getText());
            Temp.setPass(txtPass1.getText());
            
            int job = 0;
            if(Temp.getJob().equals("Marketing")) {
                job = 1;
            }if(Temp.getJob().equals("Developer")) {
                job = 2;
            }if(Temp.getJob().equals("Human Resource")) {
                job = 3;
            }

            PostData postData = new PostData();
            try {
                postData.CreateAcc(Temp.getFirstName(), Temp.getLastName(), 
                Temp.getDay(), Temp.getMonth(), Temp.getYear(),
                Temp.getGender(), Temp.getUser(), job, 1,
                Temp.getEmail(), Temp.getPhoneNumber(), Temp.getPass(), Temp.getAddress());
                
                if(Temp.getCode().equals("901")){
                    userError.setText("*Username already exists !");
                    txtUser.setText("");
                    txtPass1.setText("");
                    txtPass2.setText("");
                }else   {
                    Parent parent = FXMLLoader.load(getClass().getResource("/FXML/SignIn.fxml"));
                    Scene scene = new Scene(parent);

                    //This line gets the Stage information
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(scene);
                    window.show();
                }
            } catch (Exception e) {
            }
            
            
        }else {

            if(UserValid(txtUser.getText()) == false) {
                userError.setText("*Username invalid !");
                txtUser.setText("");
                txtPass1.setText("");
                txtPass2.setText("");
            }else{
                userError.setText("");
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
        System.out.println("Cancelled!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtUser.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(paneUser).play();
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
    
    
    public static boolean PassValid(String password) 
    {
        if (!((password.length() >= 8) && (password.length() <= 15))) { 
            return false; 
        } 
        
        if (password.contains(" ")) { 
            return false; 
        } 
        if (true) { 
            int count = 0; 
 
            for (int i = 0; i <= 9; i++) { 

                String str1 = Integer.toString(i); 
  
                if (password.contains(str1)) { 
                    count = 1; 
                } 
            } 
            if (count == 0) { 
                return false; 
            } 
        } 

        if ((password.contains("@") || password.contains("#") 
              || password.contains("!") || password.contains("~") 
              || password.contains("$") || password.contains("%") 
              || password.contains("^") || password.contains("&") 
              || password.contains("*") || password.contains("(") 
              || password.contains(")") || password.contains("-") 
              || password.contains("+") || password.contains("/") 
              || password.contains(":") || password.contains(".") 
              || password.contains(", ") || password.contains("<") 
              || password.contains(">") || password.contains("?") 
              || password.contains("|"))) { 
            return false; 
        } 
  
        if (true) { 
            int count = 0; 

            for (int i = 65; i <= 90; i++) { 

                char c = (char)i; 
  
                String str1 = Character.toString(c); 
                if (password.contains(str1)) { 
                    count = 1; 
                } 
            } 
            if (count == 0) { 
                return false; 
            } 
        } 
  
        if (true) { 
            int count = 0; 

            for (int i = 90; i <= 122; i++) { 
 
                char c = (char)i; 
                String str1 = Character.toString(c); 
  
                if (password.contains(str1)) { 
                    count = 1; 
                } 
            } 
            if (count == 0) { 
                return false; 
            } 
        }
        return true; 
    } 
    
    public static boolean UserValid(String username) 
    {
        if (!((username.length() >= 6) && (username.length() <= 12))) { 
            return false; 
        } 
        
        if (username.contains(" ")) { 
            return false; 
        }

        if ((username.contains("@") || username.contains("#") 
              || username.contains("!") || username.contains("~") 
              || username.contains("$") || username.contains("%") 
              || username.contains("^") || username.contains("&") 
              || username.contains("*") || username.contains("(") 
              || username.contains(")") || username.contains("-") 
              || username.contains("+") || username.contains("/") 
              || username.contains(":") || username.contains(".") 
              || username.contains(", ") || username.contains("<") 
              || username.contains(">") || username.contains("?") 
              || username.contains("|"))) { 
            return false; 
        } 
  
        if (true) { 
            int count = 0; 

            for (int i = 65; i <= 90; i++) { 

                char c = (char)i; 
  
                String str1 = Character.toString(c); 
                if (username.contains(str1)) { 
                    count = 1; 
                } 
            } 
            if (count != 0) { 
                return false; 
            } 
        } 
  
        if (true) { 
            int count = 0; 

            for (int i = 90; i <= 122; i++) { 
 
                char c = (char)i; 
                String str1 = Character.toString(c); 
  
                if (username.contains(str1)) { 
                    count = 1; 
                } 
            } 
            if (count == 0) { 
                return false; 
            } 
        }
        
    return true; 
    }
    
}
