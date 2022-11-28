/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrm.postee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ADMIN
 */
    public class HRMPostee extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignIn.fxml"));
        
        Scene scene = new Scene(root);
        
        Image icon = new Image("/ico/logo.png");
        
        stage.getIcons().add(icon);
        stage.setTitle("Chat Postee");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setFullScreen(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
