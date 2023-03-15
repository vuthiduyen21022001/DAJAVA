
package qltt;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;



public class MenuChinhController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    
    @FXML
    private ListView<String> listFormCG;
    @FXML
    private ListView<String> listFormQTN;
    @FXML
    private ListView<String> listFormXB;
    private ObservableList<String> subListFormCG;
    private ObservableList<String> subListFormQTN;
    private ObservableList<String> subListFormXB;
   

    
    @FXML
    private TabPane tabMain;
    @FXML
    private MediaView mediaView;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generateSubList();
        selectListForm();
        Media media = new Media("file:///E:/QLTT/src/qltt/images/videobg.webm");
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(0);
        player.play();
    }    

    private void generateSubList(){
        subListFormCG = FXCollections.observableArrayList();
        subListFormCG.add("Nhập con giống");                   
        listFormCG.setItems(subListFormCG);
        
        subListFormQTN = FXCollections.observableArrayList();
        subListFormQTN.add("Qua trình nuôi");
        subListFormQTN.add("Thức ăn");
        subListFormQTN.add("Thuốc");
        subListFormQTN.add("Nhân viên");
        listFormQTN.setItems(subListFormQTN);
        
        subListFormXB = FXCollections.observableArrayList();
        subListFormXB.add("Xuất");
        listFormXB.setItems(subListFormXB);
        
    }
    
    private void selectListForm(){
        listFormCG.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<?extends String> observable,String oldValue, String newCalue){
            int i = listFormCG.getSelectionModel().getSelectedIndex();
                if (i==0) {
                try {                    
                    Node NhapForm = FXMLLoader.load(getClass().getResource("NhapVatNuoi.fxml"));
                    Tab tab = new Tab("Nhập con giống",NhapForm);
                    tabMain.getTabs().add(tab);
                } catch (IOException ex) {
                    Logger.getLogger(MenuChinhController.class.getName()).log(Level.SEVERE, null, ex);
                }     
                }
            }
        });
        
        listFormQTN.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<?extends String> observable,String oldValue, String newCalue){
            int i = listFormQTN.getSelectionModel().getSelectedIndex();
                if (i==0) {
                try {                    
                    Node QTForm = FXMLLoader.load(getClass().getResource("QTNuoi.fxml"));
                    Tab tab = new Tab("Quá trình nuôi",QTForm);
                    tabMain.getTabs().add(tab);
                } catch (IOException ex) {
                    Logger.getLogger(MenuChinhController.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else if(i==1) {
                   try {                    
                    Node QTForm = FXMLLoader.load(getClass().getResource("ThucAn.fxml"));
                    Tab tab = new Tab("Thức ăn",QTForm);
                    tabMain.getTabs().add(tab);
                } catch (IOException ex) {
                    Logger.getLogger(MenuChinhController.class.getName()).log(Level.SEVERE, null, ex);
                }               
                } else if(i==2) {
                   try {                    
                    Node QTForm = FXMLLoader.load(getClass().getResource("Thuoc.fxml"));
                    Tab tab = new Tab("Thuốc",QTForm);
                    tabMain.getTabs().add(tab);
                } catch (IOException ex) {
                    Logger.getLogger(MenuChinhController.class.getName()).log(Level.SEVERE, null, ex);
                } 
                } else if(i==3) {
                   try {                    
                    Node QTForm = FXMLLoader.load(getClass().getResource("NhanVien.fxml"));
                    Tab tab = new Tab("Nhân viên",QTForm);
                    tabMain.getTabs().add(tab);
                } catch (IOException ex) {
                    Logger.getLogger(MenuChinhController.class.getName()).log(Level.SEVERE, null, ex);
                } 
                }
            }
        });
        
        listFormXB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<?extends String> observable,String oldValue, String newCalue){
            int i = listFormXB.getSelectionModel().getSelectedIndex();
                if (i==0) {
                try {                    
                    Node XBForm = FXMLLoader.load(getClass().getResource("XuatBan.fxml"));
                    Tab tab = new Tab("Xuất",XBForm);
                    tabMain.getTabs().add(tab);
                } catch (IOException ex) {
                    Logger.getLogger(MenuChinhController.class.getName()).log(Level.SEVERE, null, ex);
                }     
                }
            }
        });
    }
    
    
   /* @FXML
    private void Home(MouseEvent event) {
        bp.setCenter(ap);
    }

    @FXML
    private void ConGiong(MouseEvent event) {
        LoadPage("NhapVatNuoi");
    }

    @FXML
    private void Nuoi(MouseEvent event) {
          LoadPage("QTNuoi");
    }

    @FXML
    private void XuatBan(MouseEvent event) {
          LoadPage("XuatBan");
    }
    
      @FXML
    private void DangXuat(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login1.fxml"));
        Scene scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();    
    }

     private void LoadPage(String page){
        Parent root = null;
        try {
            
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MenuChinhController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
    }*/

    @FXML
    private void DangXuat(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();  
    }


    private void Thoát(ActionEvent event) {
        Stage stage =(Stage)bp.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void UpdateMK(ActionEvent event) throws IOException {
        Stage stage =(Stage)bp.getScene().getWindow();
       Parent root = FXMLLoader.load(getClass().getResource("DoiMK.fxml"));
       Scene scene = new Scene(root); 
       stage.setScene(scene);
       stage.show();
    }

    @FXML
    private void exit(ActionEvent event) {
          Stage stage =(Stage)bp.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage =(Stage)bp.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Doipass(ActionEvent event) throws IOException {
        Stage stage =(Stage)bp.getScene().getWindow();
       Parent root = FXMLLoader.load(getClass().getResource("DoiMK.fxml"));
       Scene scene = new Scene(root); 
       stage.setScene(scene);
       stage.show();
    }

}
