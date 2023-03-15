
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
import report.NVlist;

public class NhanVienController implements Initializable {

    @FXML
    private TableColumn<?, ?> colMaNV;
    @FXML
    private TableColumn<?, ?> colTenNV;
    @FXML
    private TableColumn<?, ?> colGT;
    @FXML
    private TableColumn<?, ?> colNS;
    @FXML
    private TableColumn<?, ?> colSDT;
    @FXML
    private TableColumn<?, ?> colDC;
    @FXML
    private Label label11;
    @FXML
    private Button btnLamMoiNV;
    @FXML
    private Button btnXoaNV;
    @FXML
    private Button btnSuaNV;
    @FXML
    private Button btnThenNV;
    @FXML
    private TextField txtMaNV;
    @FXML
    private Label erMaNV;
    @FXML
    private TextField txtTenNV;
    @FXML
    private Label erTenNV;
    @FXML
    private TextField txtGT;
    @FXML
    private Label erGT;
    @FXML
    private TextField txtNS;
    @FXML
    private Label erNS;
    @FXML
    private TextField txtSDT;
    @FXML
    private Label erSDT;
    @FXML
    private TextField txtDC;
    @FXML
    private Label erDC;
    @FXML
    private TableView<DsNV> tableNV;
    @FXML
    private TextField txtTKNV;

    private Connection con;
    private PreparedStatement pst =null;
    private ResultSet rs =null;
    private ObservableList<DsNV> data;
    @FXML
    private Button btnInNV;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        con = dba.DBConnection.qlttConnection();
        data = FXCollections.observableArrayList();
        setCellTable();
        LoadDataFromDatabase();
        setCellValueFromTableToTextField();
        searchdsNV();
    }    

    @FXML
    private void LamMoiNV(ActionEvent event) {
        txtMaNV.clear();
        txtTenNV.clear();
        txtGT.clear();
        txtNS.clear();
        txtSDT.clear();
        txtDC.clear();
    }

    @FXML
    private void XoaNV(ActionEvent event) {
        boolean isMaNVEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaNV, erMaNV, "Trống hoặc không hợp lệ!");
        if(isMaNVEmpty){
            String sql = "delete from NhanVien Where MaNV = ?";
            try {
                pst = con.prepareStatement(sql);
                pst.setString(1, txtMaNV.getText());
                int i = pst.executeUpdate();
                if(i==1){
                    dialog.display("Thông báo", "Xoá thành công!");
                    LoadDataFromDatabase();
                    LamMoiNV(event);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void SuaNV(ActionEvent event) {
          String sql="Update NhanVien set TenNV = ?, GT = ?,NS = ?, SDT = ?, DC = ? where MaNV =?";
        try {
            String maNV = txtMaNV.getText();
            String tenNV = txtTenNV.getText();
            String GT = txtGT.getText();
            double NS = Double.valueOf(txtNS.getText());
            String SDT = txtSDT.getText();
            String DC = txtDC.getText();
            pst = con.prepareStatement(sql);
            pst.setString(1, tenNV);
            pst.setString(2, GT);
            pst.setDouble(3, NS);
            pst.setString(4, SDT);
            pst.setString(5, DC);
            pst.setString(6, maNV);
            int i = pst.executeUpdate();
        if(i==1)
            dialog.display("Thông báo", "Cập nhật thành công!");
            LoadDataFromDatabase();     
            LamMoiNV(event);
        } catch (SQLException ex) {
            dialog.display("Thông báo", "Không thành công!");    
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ThemNV(ActionEvent event) throws SQLException {
       boolean isMaNVEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaNV, erMaNV, "Trống hoặc không hợp lệ!");
       boolean isTenNVEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtTenNV, erTenNV, "Trống hoặc không hợp lệ!");
       boolean isGTEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtGT, erGT, "Trống hoặc không hợp lệ!");
       boolean isNSEmpty = validation.txtFValidation.isTextFieldTypeNuber(txtNS, erNS, "Trống hoặc không hợp lệ!");
       boolean isSDTEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtSDT, erSDT, "Trống hoặc không hợp lệ!");
       boolean isDCEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtDC, erDC, "Trống hoặc không hợp lệ!");
       
       if(isMaNVEmpty && isTenNVEmpty && isGTEmpty && isNSEmpty && isSDTEmpty && isDCEmpty){
       String sql = "Insert into NhanVien(MaNV,TenNV,GT,NS,SDT,DC) values(?,?,?,?,?,?)";
       String maNV = txtMaNV.getText();
       String tenNV = txtTenNV.getText();
       String GT = txtGT.getText();
       double NS = Double.valueOf(txtNS.getText());
       String SDT = txtSDT.getText();
       String DC = txtDC.getText();
       
               
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, maNV);
            pst.setString(2, tenNV);
            pst.setString(3, GT);
            pst.setDouble(4, NS);
            pst.setString(5, SDT);
            pst.setString(6, DC);
            
        int i = pst.executeUpdate();                   
        if(i==1)
            //System.out.println("Data intert Successfull");
            dialog.display("Thông báo", "Đã thêm thành công!");    
            LoadDataFromDatabase();
            LamMoiNV(event);
        
        } catch (SQLException ex) {
            dialog.display("Thông báo", "Không thành công!");    
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        pst.close();
        }  
       }
    }
    
    
    private void searchdsNV(){
        txtTKNV.setOnKeyReleased(e -> {
            if(txtTKNV.getText().equals("")){
                LoadDataFromDatabase();
            }
            else {
                data.clear();
                String sql = "select * from NhanVien where MaNV LIKE '%"+txtTKNV.getText()+"%'"
                        + "UNION select * from NhanVien where TenNV LIKE '%"+txtTKNV.getText()+"%'"
                        + "UNION select * from NhanVien where GT LIKE '%"+txtTKNV.getText()+"%'"
                        + "UNION select * from NhanVien where NS LIKE '%"+txtTKNV.getText()+"%'"
                        + "UNION select * from NhanVien where SDT LIKE '%"+txtTKNV.getText()+"%'"
                        + "UNION select * from NhanVien where DC LIKE '%"+txtTKNV.getText()+"%'";

                try {
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if(rs.next()){
                        System.out.println(rs.getString(1));
                        data.add(new DsNV(rs.getString(1),rs.getString(2),rs.getString(3) ,""+rs.getDouble(4),
                        rs.getString(5),rs.getString(6)));
                    }
                    tableNV.setItems(data);
                            } catch (SQLException ex) {
                    Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
            
                }
            }
        });
    }
    
    private void setCellTable(){
        
        colMaNV.setCellValueFactory(new PropertyValueFactory<>("MaNV"));
        colTenNV.setCellValueFactory(new PropertyValueFactory<>("TenNV"));
        colGT.setCellValueFactory(new PropertyValueFactory<>("GT"));
        colNS.setCellValueFactory(new PropertyValueFactory<>("NS"));
        colSDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        colDC.setCellValueFactory(new PropertyValueFactory<>("DC"));     
    } 
    

    private void LoadDataFromDatabase(){
        data.clear();
        try {
            pst = con.prepareStatement("Select * from NhanVien");
            rs = pst.executeQuery();
            
            while (rs.next()){
                data.add(new DsNV(rs.getString(1),rs.getString(2),rs.getString(3) ,""+rs.getDouble(4),
                        rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableNV.setItems(data);
      
    }

    private void setCellValueFromTableToTextField(){
        tableNV.setOnMouseClicked(e -> {
            DsNV dsnv = tableNV.getItems().get(tableNV.getSelectionModel().getSelectedIndex());
            txtMaNV.setText(dsnv.getMaNV());
            txtTenNV.setText(dsnv.getTenNV());
            txtGT.setText(dsnv.getGT());
            txtNS.setText(dsnv.getNS());
            txtSDT.setText(dsnv.getSDT());
            txtDC.setText(dsnv.getDC());

        });
    }

    private void NVvoice() throws SQLException{
         String souceFile = "E:\\QLTT\\src\\report\\RNhanVien.jrxml";
        try {
            JasperReport jr = JasperCompileManager.compileReport(souceFile);
            HashMap<String, Object> para = new HashMap<>();
                para.put("cashier","Nhân viên");
           ArrayList<NVlist> nvl = new ArrayList<>();
           for (DsNV oi: data){
                 nvl.add(new NVlist(oi.getMaNV(),oi.getTenNV(),oi.getGT(),""+oi.getNS(),""+oi.getSDT(),""+oi.getDC()));
           }
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(nvl);
            JasperPrint jp = JasperFillManager.fillReport(jr, para,jcs);
            JasperViewer.viewReport(jp);
                               
        } catch (JRException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void InNV(ActionEvent event) throws SQLException {    
        NVvoice();
    }
}
