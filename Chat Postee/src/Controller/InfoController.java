/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.SignUpController.EmailValid;
import DAO.PostData;
import hrm.postee.Temp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

import com.google.gson.Gson;

import DAO.Base64Response;
import DAO.PostData;
import DAO.Response;
import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXDrawer;
import java.util.Calendar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.util.Duration;
import org.imgscalr.Scalr;


/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class InfoController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    
    //Tabs
    @FXML
    private AnchorPane btnPay;
    @FXML
    private AnchorPane btnHR;
    @FXML
    private AnchorPane btnSignOut;
    @FXML
    private AnchorPane btnMess;
    
    //Buttons
    @FXML
    private Button btnSave;
    @FXML
    private Button btnEdit;
    
    //Info
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
    @FXML
    private TextField txtDOB;
    @FXML
    private TextField txtJob;
    @FXML
    private TextField txtGender;
    @FXML
    private TextField txtID;

    //Date of birth edit
    @FXML
    private DatePicker DOB;

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
    private Label addressError;
    @FXML
    private Label phoneError;

    //Changeable Image
    @FXML
    private ImageView imgAva;
    @FXML
    private ImageView imgGender;
    
    //Editable
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
    
    
    //Uneditable
    @FXML 
    private AnchorPane paneGender;
    @FXML
    private AnchorPane paneJob;
    @FXML
    private AnchorPane paneID;
    
    //Editable indicator
    @FXML
    private ImageView editable;
    @FXML
    private ImageView editable1;
    @FXML
    private ImageView editable2;
    @FXML
    private ImageView editable3;
    
    //Clock
    @FXML
    private AnchorPane clockpane;
    private Clock clock;
    
    //FaceID
    @FXML
    private JFXDrawer drawerFID;
    @FXML
    private AnchorPane btnFaceId;
    @FXML
    private Button btnSetFaceID;
    @FXML
    private StackPane paneFaceId;
    @FXML
    private Button closeCam;
    
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
    private void Messenger(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Messenger.fxml"));
        Scene scene = new Scene(parent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
    
//    @FXML
//    private void Payslip(MouseEvent event) throws IOException {
//        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Payslip.fxml"));
//        Scene scene = new Scene(parent);
//        
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        
//        window.setScene(scene);
//        window.show();
//    }
    
//    @FXML
//    private void HRApp(MouseEvent event) throws IOException {
////        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/HRApp.fxml"));
////        Scene scene = new Scene(parent);
////        
////        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
////        
////        window.setScene(scene);
////        window.show();
//    }
    
    @FXML
    private void SignOut(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/SignIn.fxml"));
        Scene scene = new Scene(parent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void FaceID(MouseEvent event) throws IOException {
        Temp.setMode("CheckIn");
        closeCam.setVisible(true);
        drawerFID.open();
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/FXML/FaceID.fxml"));
        paneFaceId.getChildren().add(newLoadedPane);
    }
    
        
    @FXML
    private void SetFaceID(MouseEvent event) throws IOException {
        Temp.setMode("Setup");
        closeCam.setVisible(true);
        drawerFID.open();
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/FXML/FaceID.fxml"));
        paneFaceId.getChildren().add(newLoadedPane);
    }
    
    @FXML
    private void closeCam(MouseEvent event) {
        closeCam.setVisible(false);
        drawerFID.close();
    }
    
    @FXML
    private void ChangeAva(ActionEvent event) throws MalformedURLException, Exception {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.jpg", "*.png"));
        
        File f = fc.showOpenDialog(null);
        
        if(f != null)    {
            String imagepath = f.toURI().toURL().toString();
            Image ava = new Image(imagepath, 250, 300, false, false);
            String jsonResponse = PostData.UploadAva(Temp.getAuthorization(), PostData.encodeBase64(f.getAbsolutePath()));
            setAva(ava);
            
        }else   {
            System.out.println("File invalid!");
        }
    }
    
    @FXML
    private void Edit(ActionEvent event) {
        //Edit availability
        txtFirstname.setEditable(true);
        txtLastname.setEditable(true);
        txtEmail.setEditable(true);
        txtPhone.setEditable(true);
        txtAddress.setEditable(true);
        txtFirstname.setEditable(true);
        txtDOB.setVisible(false);
        
        //Buttons visibility
        btnEdit.setVisible(false);
        btnSave.setVisible(true);
        
        //Editable indication
        editable.setVisible(true);
        editable1.setVisible(true);
        editable2.setVisible(true);
        editable3.setVisible(true);
    }
    
    @FXML
    private void Save(ActionEvent event) {
        if (DOB.getValue() == null || txtFirstname.getText().length() == 0 || txtLastname.getText().length() == 0
            || txtAddress.getText().length() == 0 || txtPhone.getText().length() != 10 || EmailValid(txtEmail.getText()) == false) {
            
            //Specific error messages
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
            Temp.setDay(DOB.getValue().getDayOfMonth());
            Temp.setMonth(DOB.getValue().getMonthValue());
            Temp.setYear(DOB.getValue().getYear());
            
            PostData postData = new PostData();
            try {
                postData.UpdateInfo(Temp.getAuthorization(), Temp.getFirstName(), Temp.getLastName(),
                        Temp.getDay(), Temp.getMonth(), Temp.getYear(), Temp.getGender(),
                        Temp.getUser(), 0, 0, Temp.getEmail(), Temp.getPhoneNumber(), Temp.getPass(), Temp.getAddress());
            } catch (Exception ex) {
            }
            
            //Edit availability
            txtFirstname.setEditable(false);
            txtLastname.setEditable(false);
            txtEmail.setEditable(false);
            txtPhone.setEditable(false);
            txtAddress.setEditable(false);
            txtFirstname.setEditable(false);
            txtDOB.setVisible(true);

            //Buttons visibility
            btnEdit.setVisible(true);
            btnSave.setVisible(false);

            //Editable indication
            editable.setVisible(false);
            editable1.setVisible(false);
            editable2.setVisible(false);
            editable3.setVisible(false);
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drawer.setSidePane(menu);
        drawerFID.setSidePane(paneFaceId);
        closeCam.setVisible(false);
        if (Temp.getType().equals("ADMIN"))
            btnHR.setVisible(true);
        else if (Temp.getType().equals("USER"))
            btnHR.setVisible(false);

        //Digital clock
        clock = new Clock(Color.ORANGERED, Color.rgb(50,50,50));
        clock.setLayoutX(30);
        clock.setLayoutY(125);
        clock.getTransforms().add(new Scale(0.75f, 0.75f, 0, 0));
        clockpane.getChildren().addAll(clock);
        
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
        txtDOB.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(paneDOB).play();
                }
            }
        });
        txtID.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(paneID).play();
                }
            }
        });
        txtJob.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(paneJob).play();
                }
            }
        });
        txtGender.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)    {
                if (newPropertyValue)   {
                    new Pulse(paneGender).play();
                }
            }
        });
        
        
        
        //Editable initiation 
        editable.setVisible(false);
        editable1.setVisible(false);
        editable2.setVisible(false);
        editable3.setVisible(false);
        
        //HR App available
        if (Temp.getType().equals("ADMIN"))
            btnHR.setVisible(true);
        else if (Temp.getType().equals("USER"))
            btnHR.setVisible(false);
        
        
        //Get info
        txtFirstname.setText(Temp.getFirstName());
        txtLastname.setText(Temp.getLastName());
        txtEmail.setText(Temp.getEmail());
        txtPhone.setText(Temp.getPhoneNumber());
        txtDOB.setText(Temp.getDay() + "/" + Temp.getMonth() + "/" + Temp.getYear());
        txtJob.setText(Temp.getJob());
        txtAddress.setText(Temp.getAddress());
        txtGender.setText(Temp.getGender());
        txtID.setText(Temp.getId());
        
        //Get Ava
        PostData postData = new PostData();
        try {
            Response obj = (new Gson()).fromJson(postData.GetAva(Temp.getAuthorization()), Response.class);
            if(obj.code == 200) {
                Base64Response resBase64 = (new Gson()).fromJson((new Gson()).toJson(obj.data), Base64Response.class);
                String base64 = resBase64.base64;

                BufferedImage bi = Scalr.resize(postData.decodeBase64(base64), Scalr.Mode.FIT_EXACT, 250, 300);
                WritableImage wi = new WritableImage(250, 300);
                Image ava = SwingFXUtils.toFXImage(bi,wi);

                setAva(ava);
            }
            else {
                Image im = new Image("/ico/ava.png", 250, 300, false, false);//Default Ava
                setAva(im);
            }
        } catch (Exception e2) {
        }
        
        //Set gender icon
        if(Temp.getGender().equals("Male")) {
            imgGender.setImage(new Image("/ico/male.png", 30, 30, false, false));
        }if(Temp.getGender().equals("Female")) {
            imgGender.setImage(new Image("/ico/female.png", 30, 30, false, false));
        }if(Temp.getGender().equals("Other")) {
        }
            
        //DatePicker default value
        DOB.setValue(LocalDate.of(Temp.getYear(), Temp.getMonth(), Temp.getDay()));
        
   }    
    
    public void setAva (Image im){
        imgAva.setImage(im);
        
        Rectangle clip = new Rectangle();    
        clip.setWidth(250.0f); 
        clip.setHeight(300.0f); 
        clip.setArcHeight(40);
        clip.setArcWidth(40);
        clip.setStroke(Color.BLACK);
        imgAva.setClip(clip);
        //Snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = imgAva.snapshot(parameters, null);
        //Remove the rounding clip so that our effect can show through.
        imgAva.setClip(null);
        //Apply a shadow effect.
        imgAva.setEffect(new DropShadow(15, Color.BLACK));
        //Store the rounded corner image in the imageView.
        imgAva.setImage(image);
    }
    
    public static class Clock extends Parent {
        private Calendar calendar = Calendar.getInstance();
        private Digit[] digits;
        private Timeline delayTimeline, secondTimeline;

        public Clock(Color onColor, Color offColor) {
            // create effect for on LEDs
            Glow onEffect = new Glow(1.7f);
            onEffect.setInput(new InnerShadow());
            // create effect for on dot LEDs
            Glow onDotEffect = new Glow(1.7f);
            onDotEffect.setInput(new InnerShadow(5,Color.BLACK));
            // create effect for off LEDs
            InnerShadow offEffect = new InnerShadow();
            // create digits
            digits = new Digit[7];
            for (int i = 0; i < 6; i++) {
                Digit digit = new Digit(onColor, offColor, onEffect, offEffect);
                digit.setLayoutX(i * 80 + ((i + 1) % 2) * 20);
                digits[i] = digit;
                getChildren().add(digit);
            }
            // create dots
            Group dots = new Group(
                    new Circle(80 + 54 + 20, 44, 6, onColor),
                    new Circle(80 + 54 + 17, 64, 6, onColor),
                    new Circle((80 * 3) + 54 + 20, 44, 6, onColor),
                    new Circle((80 * 3) + 54 + 17, 64, 6, onColor));
            dots.setEffect(onDotEffect);
            getChildren().add(dots);
            // update digits to current time and start timer to update every second
            refreshClocks();
            play();
        }

        private void refreshClocks() {
            calendar.setTimeInMillis(System.currentTimeMillis());
            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);
            int seconds = calendar.get(Calendar.SECOND);
            digits[0].showNumber(hours / 10);
            digits[1].showNumber(hours % 10);
            digits[2].showNumber(minutes / 10);
            digits[3].showNumber(minutes % 10);
            digits[4].showNumber(seconds / 10);
            digits[5].showNumber(seconds % 10);
        }

        public void play() {
            // wait till start of next second then start a timeline to call refreshClocks() every second
            delayTimeline = new Timeline();
            delayTimeline.getKeyFrames().add(
                    new KeyFrame(new Duration(1000 - (System.currentTimeMillis() % 1000)), new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent event) {
                            if (secondTimeline != null) {
                                secondTimeline.stop();
                            }
                            secondTimeline = new Timeline();
                            secondTimeline.setCycleCount(Timeline.INDEFINITE);
                            secondTimeline.getKeyFrames().add(
                                    new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                                        @Override public void handle(ActionEvent event) {
                                            refreshClocks();
                                        }
                                    }));
                            secondTimeline.play();
                        }
                    })
            );
            delayTimeline.play();
        }

        public void stop(){
            delayTimeline.stop();
            if (secondTimeline != null) {
                secondTimeline.stop();
            }
        }
    }

    public static final class Digit extends Parent {
        private static final boolean[][] DIGIT_COMBINATIONS = new boolean[][]{
                new boolean[]{true, false, true, true, true, true, true},
                new boolean[]{false, false, false, false, true, false, true},
                new boolean[]{true, true, true, false, true, true, false},
                new boolean[]{true, true, true, false, true, false, true},
                new boolean[]{false, true, false, true, true, false, true},
                new boolean[]{true, true, true, true, false, false, true},
                new boolean[]{true, true, true, true, false, true, true},
                new boolean[]{true, false, false, false, true, false, true},
                new boolean[]{true, true, true, true, true, true, true},
                new boolean[]{true, true, true, true, true, false, true}};
        private final Polygon[] polygons = new Polygon[]{
                new Polygon(2, 0, 52, 0, 42, 10, 12, 10),
                new Polygon(12, 49, 42, 49, 52, 54, 42, 59, 12f, 59f, 2f, 54f),
                new Polygon(12, 98, 42, 98, 52, 108, 2, 108),
                new Polygon(0, 2, 10, 12, 10, 47, 0, 52),
                new Polygon(44, 12, 54, 2, 54, 52, 44, 47),
                new Polygon(0, 56, 10, 61, 10, 96, 0, 106),
                new Polygon(44, 61, 54, 56, 54, 106, 44, 96)};
        private final Color onColor;
        private final Color offColor;
        private final Effect onEffect;
        private final Effect offEffect;

        public Digit(Color onColor, Color offColor, Effect onEffect, Effect offEffect) {
            this.onColor = onColor;
            this.offColor = offColor;
            this.onEffect = onEffect;
            this.offEffect = offEffect;
            getChildren().addAll(polygons);
            getTransforms().add(new Shear(-0.1,0));
            showNumber(0);
        }

        public void showNumber(Integer num) {
            if (num < 0 || num > 9) num = 0; // default to 0 for non-valid numbers
            for (int i = 0; i < 7; i++) {
                polygons[i].setFill(DIGIT_COMBINATIONS[num][i] ? onColor : offColor);
                polygons[i].setEffect(DIGIT_COMBINATIONS[num][i] ? onEffect : offEffect);
            }
        }
    }
}
