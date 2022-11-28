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
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class SignUpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //Keyboard Inputs 
    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAddress;
    
    //Special Inputs
    @FXML
    private ComboBox<String> cbJob;
    @FXML
    private DatePicker DOB;
    @FXML
    private ToggleGroup genderGroup;    //Gender RadioButtons ToogleGroup
    @FXML
    private RadioButton rdbtn1;
    @FXML
    private RadioButton rdbtn2;
    @FXML
    private RadioButton rdbtn3;
    
    //Page switching buttons
    @FXML
    private Button btnNext;
    @FXML
    private Button btnCancel;
    
    //Text panes
    @FXML
    private AnchorPane paneName;
    @FXML
    private AnchorPane paneEmail;
    @FXML
    private AnchorPane panePhone;
    @FXML
    private AnchorPane paneAddress;
    @FXML
    private AnchorPane paneDOB;
    @FXML
    private AnchorPane paneGender;
    @FXML
    private AnchorPane paneJob;

    
    //Error messages
    @FXML
    private Label emailError;
    @FXML
    private Label nameError;
    @FXML
    private Label genderError;
    @FXML
    private Label dobError;
    @FXML
    private Label jobError;
    @FXML
    private Label addressError;
    @FXML
    private Label phoneError;
    
    
    
 
    @FXML
    private void Next(ActionEvent event) throws IOException {
        if (genderGroup.getSelectedToggle() == null || 
            cbJob.getSelectionModel().isEmpty() || DOB.getValue() == null
            || txtFirstname.getText().length() == 0 || txtLastname.getText().length() == 0
            || txtAddress.getText().length() == 0 || txtPhone.getText().length() != 10 || EmailValid(txtEmail.getText()) == false) {

            //Specific error messages
            if (genderGroup.getSelectedToggle() == null){
                genderError.setText("*Please choose your gender !");
            } else{
                genderError.setText("");
            }
            if(cbJob.getSelectionModel().isEmpty()){
                jobError.setText("*Please choose your job !");
            } else{
                jobError.setText("");
            }
            if(DOB.getValue() == null){
                dobError.setText("*Please choose your Date of Birth !");
            } else{
                dobError.setText("");
            }
            if(txtFirstname.getText().length() == 0 || txtLastname.getText().length() == 0){
                nameError.setText("*Please enter your name !");
            } else{
                nameError.setText("");
            }
            if(txtAddress.getText().length() == 0){
                addressError.setText("*Please enter your address !");
            } else{
                addressError.setText("");
            }
            if(EmailValid(txtEmail.getText()) == false){
                emailError.setText("*Please enter a valid email !");
            } else{
                emailError.setText("");
            }
            if(txtPhone.getText().length() != 10){
                phoneError.setText("*Please enter a valid phone number !");
            } else{
                phoneError.setText("");
            }
            
            
        }else{
            Temp.setLastName(txtLastname.getText());
            Temp.setFirstName(txtFirstname.getText());
            Temp.setEmail(txtEmail.getText());
            Temp.setPhoneNumber(txtPhone.getText());
            Temp.setAddress(txtAddress.getText());
            Temp.setGender(((RadioButton) genderGroup.getSelectedToggle()).getText());
            Temp.setJob(cbJob.getValue().trim());
            Temp.setDay(DOB.getValue().getDayOfMonth());
            Temp.setMonth(DOB.getValue().getMonthValue());
            Temp.setYear(DOB.getValue().getYear());

            PostData postData = new PostData();
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/FXML/SetPass.fxml"));
                Scene scene = new Scene(parent);

                //This line gets the Stage information
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                window.setScene(scene);
                window.show();
            } catch (Exception ex) {
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

    
    ObservableList<String> jobs = 
    FXCollections.observableArrayList(
        "Developer ",
        "Human Resource",
        "Marketing"
    );
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbJob.setItems(jobs);
        
        txtFirstname.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(paneName).play();
                }
            }
        });
        txtLastname.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(paneName).play();
                }
            }
        });
        txtEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(paneEmail).play();
                }
            }
        });
        txtPhone.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(panePhone).play();
                }
            }
        });
        txtAddress.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(paneAddress).play();
                }
            }
        });
        DOB.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(paneDOB).play();
                }
            }
        });
        cbJob.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(paneJob).play();
                }
            }
        });
        genderGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle,Toggle new_toggle)    {
                    new Pulse(paneGender).play();
            }
        });
        
        
        txtPhone.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtPhone.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 10) {
                txtPhone.setText(oldValue);
            } else {
                txtPhone.setText(newValue);
    }
        });
        
        
        DOB.setValue(LocalDate.of(2002, Month.DECEMBER, 31));
        
    }    
    
    
    static boolean EmailValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
