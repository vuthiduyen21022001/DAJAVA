
package qltt;

import Dialog.dialog;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import report.QTNlist;
import report.TAlist;


public class ThucAnController implements Initializable {

    @FXML
    private Label label2;
    @FXML
    private TableColumn<?, ?> colMaTA;
    @FXML
    private TableColumn<?, ?> colTenTA;
    @FXML
    private TableColumn<?, ?> colSLTA;
    @FXML
    private TableColumn<?, ?> colGiaTA;
    @FXML
    private TableColumn<?, ?> colNCCTA;
    @FXML
    private TextField txtTKTA;
    @FXML
    private Button btnLamMoiTA;
    @FXML
    private Button btnSuaTA;
    @FXML
    private Button btnXoaTA;
    @FXML
    private Button btnThemTA;
    @FXML
    private TextField txtMaTA;
    @FXML
    private Label erMaTA;
    @FXML
    private TextField txtTenTA;
    @FXML
    private Label erTenTA;
    @FXML
    private TextField txtSLTA;
    @FXML
    private Label erSLTA;
    @FXML
    private TextField txtGiaTA;
    @FXML
    private Label erGiaTA;
    @FXML
    private TextField txtNCCTA;
    @FXML
    private Label erNCCTA;
    @FXML
    private TableView<DsTA> tableTA;

    private Connection con;
    private PreparedStatement pst =null;
    private ResultSet rs =null;
    private ObservableList<DsTA> data;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = dba.DBConnection.qlttConnection();
        data = FXCollections.observableArrayList();
        setCellTable();
        LoadDataFromDatabase();
        setCellValueFromTableToTextField();
        searchdsTA();
    }    

    @FXML
    private void LamMoiTA(ActionEvent event) {
        txtMaTA.clear();
        txtTenTA.clear();
        txtSLTA.clear();
        txtGiaTA.clear();
        txtNCCTA.clear();
    }

    @FXML
    private void SuaTA(ActionEvent event) {
        String sql="Update ThucAn set TenTA = ?, SoLuongTA = ?,GiaTA = ?, NhaCC = ? where MaTA =?";
        try {
            String maTA = txtMaTA.getText();
            String tenTA = txtTenTA.getText();
            double soLuongTA = Double.valueOf(txtSLTA.getText());
            double giaTA = Double.valueOf(txtGiaTA.getText());
            String nhaCC = txtNCCTA.getText();
            pst = con.prepareStatement(sql);
            pst.setString(1, tenTA);
            pst.setDouble(2, soLuongTA);
            pst.setDouble(3, giaTA);
            pst.setString(4, nhaCC);
            pst.setString(5, maTA);

            int i = pst.executeUpdate();
        if(i==1)
            dialog.display("Thông báo", "Cập nhật thành công!");
            LoadDataFromDatabase();     
            LamMoiTA(event);
        } catch (SQLException ex) {
            dialog.display("Thông báo", "Không thành công!");    
            Logger.getLogger(ThucAnController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void XoaTA(ActionEvent event) {
         boolean isMaTAEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaTA, erMaTA, "Trống hoặc không hợp lệ!");
        if(isMaTAEmpty){
            String sql = "delete from ThucAn Where MaTA = ?";
            try {
                pst = con.prepareStatement(sql);
                pst.setString(1, txtMaTA.getText());
                int i = pst.executeUpdate();
                if(i==1){
                    dialog.display("Thông báo", "Xoá thành công!");
                    LoadDataFromDatabase();
                    LamMoiTA(event);
                }
            } catch (SQLException ex) {
                dialog.display("Thông báo", "Xoá không thành công!");
                Logger.getLogger(ThucAnController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void ThemTA(ActionEvent event) throws SQLException {
       boolean isMaTAEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaTA, erMaTA, "Trống hoặc không hợp lệ!");
       boolean isTenTAEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtTenTA, erTenTA, "Trống hoặc không hợp lệ!");
       boolean isSoLuongTAEmpty = validation.txtFValidation.isTextFieldTypeNuber(txtSLTA, erSLTA, "Trống hoặc không hợp lệ!");
       boolean isGiaTAEmpty = validation.txtFValidation.isTextFieldTypeNuber(txtGiaTA, erGiaTA, "Trống hoặc không hợp lệ!");
       boolean isNhaCCEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtNCCTA, erNCCTA, "Trống hoặc không hợp lệ!");
       
       if(isMaTAEmpty && isTenTAEmpty && isSoLuongTAEmpty && isGiaTAEmpty && isNhaCCEmpty){
       String sql = "Insert into ThucAn (MaTA,TenTA,SoLuongTA,GiaTA,NhaCC) values(?,?,?,?,?)";
       String maTA = txtMaTA.getText();
       String tenTA = txtTenTA.getText();
       double soLuongTA = Double.valueOf(txtSLTA.getText());
       double giaTA = Double.valueOf(txtGiaTA.getText());
       String nhaCC = txtNCCTA.getText();
         
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, maTA);
            pst.setString(2, tenTA);
            pst.setDouble(3, soLuongTA);
            pst.setDouble(4, giaTA);
            pst.setString(5, nhaCC);
  
        int i = pst.executeUpdate();                   
        if(i==1)
            //System.out.println("Data intert Successfull");
            dialog.display("Thông báo", "Đã thêm thành công!");    
            LoadDataFromDatabase();
            LamMoiTA(event);
        
        } catch (SQLException ex) {
            dialog.display("Thông báo", "Không thành công!");    
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        pst.close();
        }  
       }
    }
    
    private void searchdsTA(){
        txtTKTA.setOnKeyReleased(e -> {
            if(txtTKTA.getText().equals("")){
                LoadDataFromDatabase();
            }
            else {
                data.clear();
                String sql = "select * from ThucAn where MaTA LIKE '%"+txtMaTA.getText()+"%'"
                        + "UNION select * from ThucAn where TenTA LIKE '%"+txtTenTA.getText()+"%'"
                        + "UNION select * from ThucAn where SoLuongTA LIKE '%"+txtSLTA.getText()+"%'"
                        + "UNION select * from ThucAn where GiaTA LIKE '%"+txtGiaTA.getText()+"%'"
                        + "UNION select * from ThucAn where NhaCC LIKE '%"+txtNCCTA.getText()+"%'";
   

                try {
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if(rs.next()){
                        System.out.println(rs.getString(1));
                       data.add(new DsTA(rs.getString(1),rs.getString(2),""+rs.getString(3) ,""+rs.getDouble(4),
                        rs.getString(5)));
                    }
                    tableTA.setItems(data);
                            } catch (SQLException ex) {
                    Logger.getLogger(ThucAnController.class.getName()).log(Level.SEVERE, null, ex);
            
                }
            }
        });
    }
    
    private void setCellTable(){
        
        colMaTA.setCellValueFactory(new PropertyValueFactory<>("MaTA"));
        colTenTA.setCellValueFactory(new PropertyValueFactory<>("TenTA"));
        colSLTA.setCellValueFactory(new PropertyValueFactory<>("SoLuongTA"));
        colGiaTA.setCellValueFactory(new PropertyValueFactory<>("GiaTA"));
        colNCCTA.setCellValueFactory(new PropertyValueFactory<>("NhaCC"));     
    } 
    

    private void LoadDataFromDatabase(){
        data.clear();
        try {
            pst = con.prepareStatement("Select * from ThucAn");
            rs = pst.executeQuery();
            
            while (rs.next()){
                data.add(new DsTA(rs.getString(1),rs.getString(2),""+rs.getString(3) ,""+rs.getDouble(4),
                        rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThucAnController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableTA.setItems(data);
      
    }

    private void setCellValueFromTableToTextField(){
        tableTA.setOnMouseClicked(e -> {
            DsTA dsta = tableTA.getItems().get(tableTA.getSelectionModel().getSelectedIndex());
            txtMaTA.setText(dsta.getMaTA());
            txtTenTA.setText(dsta.getTenTA());
            txtSLTA.setText(dsta.getSoLuongTA());
            txtGiaTA.setText(dsta.getGiaTA());
            txtNCCTA.setText(dsta.getNhaCC());

        });
    }

    @FXML
    private void InTA(ActionEvent event) throws SQLException {
       TAvoice();
    }
    private void TAvoice() throws SQLException{
        String souceFile = "E:\\QLTT\\src\\report\\RThucAn.jrxml";
        try {
            JasperReport jr = JasperCompileManager.compileReport(souceFile);
            HashMap<String, Object> para = new HashMap<>();
                para.put("cashier","Thức ăn");
           ArrayList<TAlist> tal = new ArrayList<>();
           for (DsTA r: data){
                 tal.add(new TAlist(r.getMaTA(), r.getTenTA(),""+r.getSoLuongTA(),""+r.getGiaTA(), r.getNhaCC()));
           }
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(tal);
            JasperPrint jp = JasperFillManager.fillReport(jr, para, jcs);
            JasperViewer.viewReport(jp);                              
        } catch (JRException ex) {
            Logger.getLogger(QTNuoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
