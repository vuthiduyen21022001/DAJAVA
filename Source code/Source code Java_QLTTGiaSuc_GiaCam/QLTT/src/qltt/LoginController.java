
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


public class LoginController implements Initializable {

    @FXML
    private Button btnThoat;
    @FXML
    private Button btnLogin;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUserName;
    @FXML
    private MediaView mediaView;
    @FXML
    private AnchorPane PaneDN;

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = dba.DBConnection.qlttConnection();  
        Media media = new Media("file:///E:/QLTT/src/qltt/images/DnBg.mp4");
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(0);
        player.play();

    }    

    @FXML
    private void Thoat(ActionEvent event) {
        Stage stage =(Stage)PaneDN.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void Login(ActionEvent event) {
        if (txtUserName.getText().equals("")){
          dialog.display("Thông báo", "Nhập tài khoản");   
        }else if(txtPassword.getText().equals("")){
           dialog.display("Thông báo", "Nhập Mật khẩu");   
        }
        else {
            try{
            String bdUrl = "jdbc:sqlserver://localhost:1433; databaseName=QLTT; integratedSecurity = true;";
            Connection conn = DriverManager.getConnection(bdUrl);
            String sql ="SELECT * FROM TaiKhoan\n" + 
                    "WHERE TaiKhoan=? AND MatKhau=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtUserName.getText());
            ps.setString(2, txtPassword.getText());
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
            dialog.display("Thông báo", "Đăng nhập thành công");   
            Stage stage =(Stage)PaneDN.getScene().getWindow();
            stage.close(); 
            Parent root = FXMLLoader.load(getClass().getResource("MenuChinh.fxml"));
            Scene scene = new Scene(root); 
            stage.setScene(scene);
            stage.show();    
                
            }
            else{
               dialog.display("Thông báo", "Đăng nhập thất bại, sai tài khoản hoặc mật khẩu!");   
            }
        }catch ( Exception e){
            System.out.print(e);
            
        }
        }
    }

    @FXML
    private void QMK(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("QuenMK.fxml"));
        Scene scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();
    }
    
}
