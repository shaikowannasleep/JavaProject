/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PostData;
import Model.ModelTable;
import com.jfoenix.controls.JFXDrawer;
import hrm.postee.Temp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.*;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class HRAppController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    
    //Tab buttons
    @FXML
    private AnchorPane btnInfo;
    @FXML
    private AnchorPane btnPay;
    @FXML
    private AnchorPane btnSignOut;
    @FXML
    private AnchorPane btnMess;

    //Table
    @FXML
    private TableView<ModelTable> tableview;
    @FXML
    private TableColumn<ModelTable, String> columnID;
    @FXML
    private TableColumn<ModelTable, String> columnFirst;
    @FXML
    private TableColumn<ModelTable, String> columnLast;
    @FXML
    private TableColumn<ModelTable, String> columnDOB;
    @FXML
    private TableColumn<ModelTable, String> columnGender;
    @FXML
    private TableColumn<ModelTable, String> columnJob;
    @FXML
    private TableColumn<ModelTable, String> columnSalary;
    @FXML
    private TextField filterField;
    
    //Buttons
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnExport;
    
    //Menu
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane menu;
    
    @FXML
    void OpenMenu(MouseEvent event) {
        drawer.open();
    }
    
    @FXML
    void CloseMenu(MouseEvent event) {
        drawer.close();
    }

    //More info
    @FXML
    private JFXDrawer info;
    @FXML
    private AnchorPane infopane;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtGender;
    @FXML
    private ImageView imgGender;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtDOB;
    @FXML
    private TextField txtJob;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAddress;
    
    
    //Accounts list
    ObservableList<ModelTable> listAcc = FXCollections.observableArrayList();
    

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
    private void Payslip(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Payslip.fxml"));
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
    private void DeleteAcc(MouseEvent event) throws IOException, Exception {
        String id = tableview.getSelectionModel().getSelectedItem().getId();
        PostData postData = new PostData();
        postData.Delete(Temp.getAuthorization(), id);
        
        setTable();
    }
    
    @FXML
    private void ExportList(MouseEvent event) throws IOException, Exception {
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            
            XSSFSheet sheet = wb.createSheet("Accounts List");
            XSSFRow header = sheet.createRow(7);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("First Name");
            header.createCell(2).setCellValue("Last Name");
            header.createCell(3).setCellValue("Date of Birth");
            header.createCell(4).setCellValue("Gender");
            header.createCell(5).setCellValue("Job");
            header.createCell(6).setCellValue("Salary");
            
            
            sheet.setZoom(100);//Scale 100%

            PostData postData = new PostData();
            ArrayList<Object[]> arrayAcc = postData.GetListAcc(Temp.getAuthorization());

            for (int i = 0; i < arrayAcc.size(); i++) {
                String info = Arrays.toString(arrayAcc.get(i)).replace("[","").replace("]", "");
                String[] part = info.split(", ");

                XSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(part[2]);
                row.createCell(1).setCellValue(part[1]);
                row.createCell(2).setCellValue(part[0]);
                row.createCell(3).setCellValue(part[3]);
                row.createCell(4).setCellValue(part[4]);
                row.createCell(5).setCellValue(part[5]);
                row.createCell(6).setCellValue(part[9]);
            }
            
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            
            FileChooser fc = new FileChooser();
            fc.setTitle("Save accounts list as");
            fc.setInitialDirectory(new File(System.getProperty("user.home")));
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel", "*xlsx"));
            fc.setInitialFileName("AccountsList");
            
            File file = fc.showSaveDialog(null);
            
            //If file is not null, write to file using output stream.
            if (file != null) {
                FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + ".xlsx");
                wb.write(fileOut);
            }
        }   catch(Exception ex){
            
        }
    }
    
    public static <S, T> void columnReorderDisable(TableView table, TableColumn<S, T> ...columns) {
        table.getColumns().addListener(new ListChangeListener() {
            public boolean suspended;

            @Override
            public void onChanged(Change change) {
                change.next();
                if (change.wasReplaced() && !suspended) {
                    this.suspended = true;
                    table.getColumns().setAll((Object[]) columns);
                    this.suspended = false;
              }
          }
      });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drawer.setSidePane(menu);
        info.setSidePane(infopane);
        
        //Columns
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnFirst.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnLast.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        columnJob.setCellValueFactory(new PropertyValueFactory<>("job"));
        columnSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        
        //Set Table contents
        setTable();
        
            
        tableview.setRowFactory(acc -> {
        TableRow<ModelTable> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                int index = tableview.getSelectionModel().selectedIndexProperty().get();
//                System.out.println("Double-clicked on line: " + (index+1));
                
                PostData postData = new PostData();
                try {
                    ArrayList<Object[]> arrayAcc = postData.GetListAcc(Temp.getAuthorization());
                    String info = Arrays.toString(arrayAcc.get(index)).replace("[","").replace("]", "");
                    String[] part = info.split(", ");
                    
                    txtID.setText(part[2]);
                    txtName.setText(part[1]+ " " + part[0]);
                    txtDOB.setText(part[3]);
                    txtGender.setText(part[4]);
                    txtJob.setText(part[5]);
                    txtEmail.setText(part[6]);
                    txtPhone.setText(part[7]);
                    txtAddress.setText(part[8]);
                    
                    //Set gender icon
                    if(part[4].equals("Male")) {
                        imgGender.setImage(new Image("/ico/male.png", 30, 30, false, false));
                    }if(part[4].equals("Female")) {
                        imgGender.setImage(new Image("/ico/female.png", 30, 30, false, false));
                    }if(part[4].equals("Other")) {
                    }
                } catch(Exception ex){
                }
                
                
                info.open();
                
                TimerTask task = new TimerTask() {
                public void run() {
                        info.close();
                    }
                };
                Timer timer = new Timer("Timer");

                long delay = 6000L;
                timer.schedule(task, delay);
                
            }
        });
            return row ;
        });
        
        //Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<ModelTable> filteredData = new FilteredList<>(listAcc, b -> true);
		
        //Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(listAcc -> {
                //If filter text is empty, display all persons.

            if (newValue == null || newValue.isEmpty()) {
                    return true;
            }

            //Compare firstname, lastname, id, job, gender, salary of every person with filter text.
            String lowerCaseFilter = newValue.toLowerCase();

            if (listAcc.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                return true;    //Filter matches firstname.
            }   else if (listAcc.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;    //Filter matches lastname.
            }   else if (String.valueOf(listAcc.getId()).indexOf(lowerCaseFilter)!=-1) {
                 return true;   //Filter matches id.
            }   else if (listAcc.getJob().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;    //Filter matches job.
            }   else if (listAcc.getGender().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;    //Filter matches gender.
            }   else if (String.valueOf(listAcc.getSalary()).indexOf(lowerCaseFilter)!=-1) {
                return true;    //Filter matches salary.
            }
            else  
                return false; // Does not match.
            });
        });

        //Wrap the FilteredList in a SortedList. 
        SortedList<ModelTable> sortedData = new SortedList<>(filteredData);

        //Bind the SortedList comparator to the TableView comparator.
        //Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        //Add sorted (and filtered) data to the table.
        tableview.setItems(sortedData);
        
 
    }    
    
    public void setTable() {
        PostData postData = new PostData();
        try {
            ArrayList<Object[]> arrayAcc = postData.GetListAcc(Temp.getAuthorization()); 
            listAcc.clear();       
            for (int i = 0; i < arrayAcc.size(); i++) {
//                System.out.println(Arrays.toString(arrayAcc.get(i)));
                String info = Arrays.toString(arrayAcc.get(i)).replace("[","").replace("]", "");
                String[] part = info.split(", ");
                
                listAcc.add(new ModelTable(part[2],part[1],part[0],part[3],part[4],part[5],part[9],part[6],part[7],part[8]));
                
                //Table set
                tableview.setItems(listAcc);
                tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                columnReorderDisable(tableview,columnID,columnFirst,columnLast,columnDOB,columnGender,columnJob,columnSalary);
            }
        } catch (Exception ex) {
        }
    }
}
