/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PostData;
import Model.CheckIn;
import Model.Timekeeping;
import com.jfoenix.controls.JFXDrawer;
import hrm.postee.Temp;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class PayslipController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    //Tab buttons
    @FXML
    private AnchorPane btnInfo;
    @FXML
    private AnchorPane btnHR;
    @FXML
    private AnchorPane btnSignOut;
    @FXML
    private AnchorPane btnMess;

    //Menu
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane menu; 
    @FXML
    private AnchorPane menubar;
    
    //Text Boxes
    @FXML
    private AnchorPane paneTotal;
    @FXML
    private TextField txtTotal;
    @FXML
    private AnchorPane paneBase;
    @FXML
    private TextField txtBase;
    @FXML
    private AnchorPane paneSalary;
    @FXML
    private TextField txtSalary;
    
    //Table
    @FXML
    private TableView<Timekeeping> tableview;
    @FXML
    private TableColumn<Timekeeping, String> columnDate;
    @FXML
    private TableColumn<Timekeeping, String> columnCheckin;
    @FXML
    private TableColumn<Timekeeping, String> columnCheckout;
    @FXML
    private TableColumn<Timekeeping, Double> columnWork;
    
    ObservableList<Timekeeping> listDay = FXCollections.observableArrayList();
    
    
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
    private void Messenger(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Messenger.fxml"));
        Scene scene = new Scene(parent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    private void HRApp(MouseEvent event) throws IOException {
//        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/HRApp.fxml"));
//        Scene scene = new Scene(parent);
//        
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        
//        window.setScene(scene);
//        window.show();
    }
    
    @FXML
    private void SignOut(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/SignIn.fxml"));
        Scene scene = new Scene(parent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drawer.setSidePane(menu);
        
        if (Temp.getType().equals("ADMIN"))
            btnHR.setVisible(true);
        else if (Temp.getType().equals("USER"))
            btnHR.setVisible(false);
        
        //Columns
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnCheckin.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        columnCheckout.setCellValueFactory(new PropertyValueFactory<>("checkout"));
        columnWork.setCellValueFactory(new PropertyValueFactory<>("workday"));
        
        setTable();
    }    
    
    public void setTable() {
        PostData postData = new PostData();
        try {
            ArrayList<CheckIn> arrayTime = postData.GetTimeData(Temp.getAuthorization()); 
            listDay.clear();       
            for (int i = 0; i < arrayTime.size(); i++) {
                CheckIn checkin = arrayTime.get(i);
                listDay.add(new Timekeeping(checkin.getDay()+"/"+checkin.getMonth()+"/"+checkin.getYear(),checkin.getCheckin(),"17:00",checkin.getTotal()));
                
                //Table set
                tableview.setItems(listDay);
                tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                
                //Box set
                double total = 0.0;
                for (Timekeeping time : tableview.getItems()) {
                    total = total + time.getWorkday();
                }
                txtTotal.setText("Total workdays: " + total);
                txtBase.setText("Base Salary: ");
                txtSalary.setText("Salary: ");
            }
        } catch (Exception ex) {
        }
    }
}
