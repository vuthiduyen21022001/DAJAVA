
package qltt;

import Dialog.dialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class QuenMKController implements Initializable {

    @FXML
    private TextField txtTK;
    @FXML
    private TextField txtSDT;
    @FXML
    private TextField txtCH1;

    private Connection con;
   private PreparedStatement pst =null;
    String ans;
    String pass;
    @FXML
    private TextField txtCTL;
    @FXML
    private Button btngetMK;
    @FXML
    private Button btnTTK;
    @FXML
    private TextField txtpass;
    @FXML
    private Label errorLb;
    @FXML
    private Label errorAnswer;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = dba.DBConnection.qlttConnection();
    }    

    @FXML
    private void KiemTraLaiMK(ActionEvent event) {
          if(ans.equals(txtCTL.getText().trim())){
            txtpass.setText(pass);
        }
        else {
         dialog.display("Thông báo", "Câu trả lời không đúng, vui lòng thử lại");   
        }
            
    }

    @FXML
    private void TimTK(ActionEvent event) {

       try {
           String bdUrl = "jdbc:sqlserver://localhost:1433; databaseName=QLTT; integratedSecurity = true;";
            Connection conn = DriverManager.getConnection(bdUrl);
            txtpass.setText("");
            txtCTL.setText("");
            
            String u_name = txtTK.getText().trim();
            if(u_name.isEmpty()){
                errorLb.setText("Không tìm thấy tài khoản");
            }
            else {
                String sql = "select  SDT, CauHoi, CauTL, MatKhau from TaiKhoan where TaiKhoan=?";
                pst = con.prepareStatement(sql);
                pst.setString(1, u_name);
                
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    txtSDT.setText(rs.getString(1));
                    txtCH1.setText(rs.getString(2));
                    ans = rs.getString(3);
                    pass = rs.getString(4);
                    errorLb.setText("");
                    
                    pst.close();
                    rs.close();
                }
                else {
                    errorLb.setText("Error: Tài khoản không chính xác!");
                }
                
                
            }
            
        } catch (Exception ex) {
            System.out.println("something wrong" + ex);
        } 
            
    }

    @FXML
    private void TroLaiLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();
    }
    
}
