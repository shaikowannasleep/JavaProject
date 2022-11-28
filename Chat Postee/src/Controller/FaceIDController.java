///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Controller;
//
//
//import DAO.PostData;
//import java.awt.image.BufferedImage;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import javafx.application.Platform;
//import javafx.beans.property.ObjectProperty;
//import javafx.beans.property.SimpleObjectProperty;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.concurrent.Task;
//import javafx.embed.swing.SwingFXUtils;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.FlowPane;
//
//import com.github.sarxos.webcam.Webcam;
//import com.github.sarxos.webcam.WebcamUtils;
//import static com.github.sarxos.webcam.WebcamUtils.capture;
//import com.github.sarxos.webcam.util.ImageUtils;
//import hrm.postee.Temp;
//import java.awt.Toolkit;
//import java.awt.datatransfer.Clipboard;
//import java.awt.datatransfer.StringSelection;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Iterator;
//import java.util.List;
//import javafx.scene.control.Label;
//import javafx.stage.FileChooser;
//import javax.imageio.ImageIO;
//import org.openimaj.image.DisplayUtilities;
//
//import org.openimaj.image.FImage;
//import org.openimaj.image.ImageUtilities;
//import org.openimaj.image.MBFImage;
//import org.openimaj.image.colour.RGBColour;
//import org.openimaj.image.colour.Transforms;
//import org.openimaj.image.processing.face.detection.*;
//
//
///**
// * FXML Controller class
// *
// * @author ADMIN
// */
//public class FaceIDController implements Initializable {
//
//    /**
//     * Initializes the controller class.
//     * @param url
//     * @param rb
//     */
//    
//    @FXML
//    private BorderPane paneFaceID;
//    
//    //Buttons
//    @FXML
//    Button btnStartCamera;
//    @FXML
//    Button btnStopCamera;
//    @FXML
//    Button btnDisposeCamera;
//
//    @FXML
//    ComboBox<WebCamInfo> cbCameraOptions;
//
//    @FXML
//    BorderPane bpWebCamPaneHolder;
//
//    @FXML
//    FlowPane fpBottomPane;
//
//    @FXML
//    ImageView imgWebCamCapturedImage;
//
//    @FXML
//    private Label txtStatus;
//
//    @FXML
//    private Label txtSelCam;
//    
//    
//    private class WebCamInfo {
//
//            private String webCamName;
//            private int webCamIndex;
//
//            public String getWebCamName() {
//                return webCamName;
//            }
//
//            public void setWebCamName(String webCamName) {
//                this.webCamName = webCamName;
//            }
//
//            public int getWebCamIndex() {
//                return webCamIndex;
//            }
//
//            public void setWebCamIndex(int webCamIndex) {
//                this.webCamIndex = webCamIndex;
//            }
//
//            @Override
//            public String toString() {
//                return webCamName;
//            }
//    }
//
//    private BufferedImage grabbedImage;
//    private Webcam selWebCam = null;
//    private boolean stopCamera = false;
//    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
//
//    private String cameraListPromptText = "Choose Camera";
//
//    @Override
//    public void initialize(URL arg0, ResourceBundle arg1) {
//        //Buttons visibility
//        btnStartCamera.setVisible(false);
//        btnDisposeCamera.setVisible(false);
//        
//        fpBottomPane.setDisable(true);
//        ObservableList<WebCamInfo> options = FXCollections.observableArrayList();
//        int webCamCounter = 0;
//        for (Webcam webcam : Webcam.getWebcams()) {
//            WebCamInfo webCamInfo = new WebCamInfo();
//            webCamInfo.setWebCamIndex(webCamCounter);
//            webCamInfo.setWebCamName(webcam.getName());
//            options.add(webCamInfo);
//            webCamCounter++;
//        }
//        cbCameraOptions.setItems(options);
//        cbCameraOptions.setPromptText(cameraListPromptText);
//        cbCameraOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WebCamInfo>() {
//
//            @Override
//            public void changed(ObservableValue<? extends WebCamInfo> arg0, WebCamInfo arg1, WebCamInfo arg2) {
//                if (arg2 != null) {
////                    System.out.println("WebCam Index: " + arg2.getWebCamIndex() + ": WebCam Name:" + arg2.getWebCamName());
//                    initializeWebCam(arg2.getWebCamIndex());
//                }
//            }
//        });
//        Platform.runLater(new Runnable() {
//
//            @Override
//            public void run() {
//                setImageViewSize();
//            }
//        });
//
//    }
//
//    protected void setImageViewSize() {
//
//        double height = bpWebCamPaneHolder.getHeight();
//        double width = bpWebCamPaneHolder.getWidth();
//        imgWebCamCapturedImage.setFitHeight(height);
//        imgWebCamCapturedImage.setFitWidth(width);
//        imgWebCamCapturedImage.prefHeight(height);
//        imgWebCamCapturedImage.prefWidth(width);
//        imgWebCamCapturedImage.setPreserveRatio(true);
//
//    }
//
//    protected void initializeWebCam(final int webCamIndex) {
//
//        Task<Void> webCamIntilizer = new Task<Void>() {
//
//            @Override
//            protected Void call() throws Exception {
//
//                if (selWebCam == null) {
//                    selWebCam = Webcam.getWebcams().get(webCamIndex);
//                    selWebCam.open();
//                } else {
//                    closeCamera();
//                    selWebCam = Webcam.getWebcams().get(webCamIndex);
//                    selWebCam.open();
//                }
//                startWebCamStream();
//                return null;
//            }
//
//        };
//
//        new Thread(webCamIntilizer).start();
//        fpBottomPane.setDisable(false);
//        btnStartCamera.setDisable(true);
//    }
//
//    protected void startWebCamStream() {
//
//        stopCamera = false;
//        Task<Void> task = new Task<Void>() {
//
//            @Override
//            protected Void call() throws Exception {
//
//                while (!stopCamera) {
//                    try {
//                        if ((grabbedImage = selWebCam.getImage()) != null) {
//
//                            Platform.runLater(new Runnable() {
//
//                                @Override
//                                public void run() {
//                                    final Image mainiamge = SwingFXUtils.toFXImage(grabbedImage, null);
//                                    imageProperty.set(mainiamge);
//                                    Webcam.getDiscoveryService().setEnabled(false);
//                                    Webcam.getDiscoveryService().stop();
//                                    cbCameraOptions.setVisible(false);
//                                    txtSelCam.setVisible(false);
//                                }
//                            });
//
//                            grabbedImage.flush();
//
//                        }
//                    } catch (Exception e) {
//                            e.printStackTrace();
//                    }
//                }
//
//                return null;
//            }
//
//        };
//        Thread th = new Thread(task);
//        th.setDaemon(true);
//        th.start();
//        imgWebCamCapturedImage.imageProperty().bind(imageProperty);
//
//    }
//
//    private void closeCamera() {
//        if (selWebCam != null) {
//                selWebCam.close();
//        }
//    }
//
//    
//    BufferedImage imgSave;
//    
//    @FXML
//    public void stopCamera(ActionEvent event) {
//        stopCamera = true;
//        btnStartCamera.setDisable(false);
//        btnStopCamera.setDisable(true);
//        btnStartCamera.setVisible(true);
//
//        
//        //Get image
//        imgSave = selWebCam.getImage();
//        
//        
//        final MBFImage image = ImageUtilities.createMBFImage(imgSave, true);
//
//        FaceDetector<DetectedFace, FImage> fd = new HaarCascadeDetector(150);
//        List<DetectedFace> faces = fd.detectFaces(Transforms.calculateIntensity(image));
//        
//        if (faces.size() == 1) {
//            btnDisposeCamera.setVisible(true);        
//            txtStatus.setText("Face Scanned!");
//        }
//
//        faces.forEach((face) -> {
//            image.drawShape(face.getBounds(), RGBColour.RED);
//        });
//
//        grabbedImage = ImageUtilities.createBufferedImageForDisplay(image);
//    }
//
//    @FXML
//    public void startCamera(ActionEvent event) {
//        stopCamera = false;
//        startWebCamStream();
//        btnStartCamera.setDisable(true);
//        btnStopCamera.setDisable(false);
//        btnDisposeCamera.setVisible(false);
//        btnStartCamera.setVisible(false);
//    }
//
//    @FXML
//    public void disposeCamera(ActionEvent event) throws IOException, Exception {
//        stopCamera = true;
//        btnDisposeCamera.setVisible(false);
//        btnStartCamera.setVisible(false);
//        btnStopCamera.setDisable(true);
//        btnStartCamera.setDisable(true);
//        
//        closeCamera();
//
//        File f = File.createTempFile("img", ".png");
//        
//        //If file is not null, write to file using output stream.
//        
//        ImageIO.write(imgSave, "PNG", f);
//        String encodedImg = PostData.encodeBase64(f.getAbsolutePath());
//
////            StringSelection selection = new StringSelection(encodedImg);
////            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
////            clipboard.setContents(selection, selection);
//        
//        String code = null;
//    
//        if("CheckIn".equals(Temp.getMode()))
//            code = PostData.CheckIn(Temp.getAuthorization(), encodedImg);
//            if ("200".equals(code))
//                txtStatus.setText("Successful!");
//            else
//                txtStatus.setText("Error!");
//        if("Setup".equals(Temp.getMode()))
//            code = PostData.UploadFace(Temp.getAuthorization(), encodedImg);
//            if ("200".equals(code))
//                txtStatus.setText("Successful!");
//            else
//                txtStatus.setText("Error!");
//        if("Login".equals(Temp.getMode()))
//            code = PostData.LoginFace(encodedImg);
//            if ("200".equals(code))
//                txtStatus.setText("Successful!");
//            else
//                txtStatus.setText("Error!");
//    }
//}
