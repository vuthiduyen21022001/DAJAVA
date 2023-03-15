
package qltt;

import Dialog.dialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class DoipassController implements Initializable {

    @FXML
    private Button btnDMK;
    @FXML
    private TextField txtMK;
    @FXML
    private TextField txtTK;

    private Connection con;
    private PreparedStatement pst =null;
    private ResultSet rs =null;
    private ObservableList<DsMK> data;
    @FXML
    private Label erTK;
    @FXML
    private Label erMK;
    @FXML
    private PasswordField txtMKC;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      con = dba.DBConnection.qlttConnection();
        data = FXCollections.observableArrayList();
        LoadDataFromDatabase();
    }    

    @FXML
    private void DMK(ActionEvent event) throws IOException, SQLException {
       boolean isTKEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtTK, erTK, "Trống hoặc không hợp lệ!");
       boolean isMKEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMK, erMK, "Trống hoặc không hợp lệ!");
        if(isTKEmpty && isMKEmpty){ 
       String sql="Update TaiKhoan set MatKhau = ? where TaiKhoan =?";
           try {
            String tk = txtTK.getText();
            String mkm = txtMK.getText();         
            pst = con.prepareStatement(sql);
            pst.setString(1, mkm);
            pst.setString(2, tk);;
            int i = pst.executeUpdate();
        if(i==1)
            dialog.display("Thông báo", "Cập nhật mật khẩu thành công!");
            LoadDataFromDatabase();  
               TL(event);
        } catch (SQLException ex) {
            dialog.display("Thông báo", "Cập nhật mật khẩu không thành công!");   
            Logger.getLogger(DoipassController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
     private void LoadDataFromDatabase(){
        data.clear();
        try {
            pst = con.prepareStatement("Select * from TaiKhoan");
            rs = pst.executeQuery();
            
            while (rs.next()){
                data.add(new DsMK(rs.getString(1),rs.getString(2) ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoipassController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
     
    private void TL(ActionEvent event) throws IOException {
      Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
       Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
       Scene scene = new Scene(root); 
       stage.setScene(scene);
       stage.show();
    }

    @FXML
    private void TroLaiMenu(ActionEvent event) throws IOException {
       Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
       Parent root = FXMLLoader.load(getClass().getResource("MenuChinh.fxml"));
       Scene scene = new Scene(root); 
       stage.setScene(scene);
       stage.show(); 
    }
    
}


