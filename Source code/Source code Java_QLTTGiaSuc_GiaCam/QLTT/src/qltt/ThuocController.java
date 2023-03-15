
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
import report.Tlist;


public class ThuocController implements Initializable {

    @FXML
    private Label label1;
    @FXML
    private Label label;
    @FXML
    private TableColumn<?, ?> colMaThuoc;
    @FXML
    private TableColumn<?, ?> colTenThuoc;
    @FXML
    private TableColumn<?, ?> colCD;
    @FXML
    private TableColumn<?, ?> colSLThuoc;
    @FXML
    private TableColumn<?, ?> colGiaThuoc;
    @FXML
    private TableColumn<?, ?> colNCCT;
    @FXML
    private TextField txtMaT;
    @FXML
    private Label erMaT;
    @FXML
    private TextField txtTenT;
    @FXML
    private Label erTenT;
    @FXML
    private TextField txtCD;
    @FXML
    private Label erCD;
    @FXML
    private TextField txtSLT;
    @FXML
    private Label erSLT;
    @FXML
    private TextField txtGiaT;
    @FXML
    private Label erGiaT;
    @FXML
    private TextField txtNCCT;
    @FXML
    private Label erNCCT;
    @FXML
    private TableView<DsThuoc> tableThuoc;
    @FXML
    private Button btnSuaT;
    @FXML
    private Button btnLamMoiT;
    @FXML
    private Button btnXoaT;
    @FXML
    private Button btnThemT;

    private Connection con;
    private PreparedStatement pst =null;
    private ResultSet rs =null;
    private ObservableList<DsThuoc> data; 
    @FXML
    private TextField txtTKT;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = dba.DBConnection.qlttConnection();
        data = FXCollections.observableArrayList();
        setCellTable();
        LoadDataFromDatabase();
        setCellValueFromTableToTextField();
        searchdsT();
    }    

    @FXML
    private void SuaT(ActionEvent event) {
        String sql="Update Thuoc set TenThuoc = ?, TacDung = ?,SoLuongT = ?, GiaThuoc = ?, NhaCCT = ? where MaThuoc =?";
        try {
            String maThuoc = txtMaT.getText();
            String tenThuoc = txtTenT.getText();
            String tacDung = txtCD.getText();
            double soLuongT = Double.valueOf(txtSLT.getText());
            double giaT = Double.valueOf(txtGiaT.getText());
            String nhaCCT = txtNCCT.getText();
            pst = con.prepareStatement(sql);        
            pst.setString(1, tenThuoc);
            pst.setString(2, tacDung);
            pst.setDouble(3, soLuongT);
            pst.setDouble(4, giaT);
            pst.setString(5, nhaCCT);
             pst.setString(6, maThuoc);
            
            int i = pst.executeUpdate();
        if(i==1)
            dialog.display("Thông báo", "Cập nhật thành công!");
            LoadDataFromDatabase();     
            LamMoiT(event);
        } catch (SQLException ex) {
            dialog.display("Thông báo", "Không thành công!");    
            Logger.getLogger(ThuocController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LamMoiT(ActionEvent event) {
        txtMaT.clear();
        txtTenT.clear();
        txtCD.clear();
        txtSLT.clear();
        txtGiaT.clear();
        txtNCCT.clear();
    }

    @FXML
    private void XoaT(ActionEvent event) {
        boolean isMaTEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaT, erMaT, "Trống hoặc không hợp lệ!");
        if(isMaTEmpty){
            String sql = "delete from Thuoc Where MaThuoc = ?";
            try {
                pst = con.prepareStatement(sql);
                pst.setString(1, txtMaT.getText());
                int i = pst.executeUpdate();
                if(i==1){
                    dialog.display("Thông báo", "Xoá thành công!");
                    LoadDataFromDatabase();
                    LamMoiT(event);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThuocController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void ThemT(ActionEvent event) throws SQLException {
       boolean isMaTEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaT, erMaT, "Trống hoặc không hợp lệ!");
       boolean isTenTEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtTenT, erTenT, "Trống hoặc không hợp lệ!");
       boolean isTDEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtCD, erCD, "Trống hoặc không hợp lệ!");
       boolean isSLTEmpty = validation.txtFValidation.isTextFieldTypeNuber(txtSLT, erSLT, "Trống hoặc không hợp lệ!");
       boolean isGiaTEmpty = validation.txtFValidation.isTextFieldTypeNuber(txtGiaT, erGiaT, "Trống hoặc không hợp lệ!");
       boolean isNhaCCTEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtNCCT, erNCCT, "Trống hoặc không hợp lệ!");
       
       if(isMaTEmpty && isTenTEmpty && isTDEmpty && isSLTEmpty && isGiaTEmpty && isNhaCCTEmpty){
       String sql = "Insert into Thuoc(MaThuoc,TenThuoc,TacDung,SoLuongT,GiaThuoc,NhaCCT) values(?,?,?,?,?,?)";
       String maThuoc = txtMaT.getText();
       String tenThuoc = txtTenT.getText();
       String tacDung = txtCD.getText();
       double soLuongT = Double.valueOf(txtSLT.getText());
       double giaT = Double.valueOf(txtGiaT.getText());
       String nhaCCT = txtNCCT.getText();
       
               
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, maThuoc);
            pst.setString(2, tenThuoc);
            pst.setString(3, tacDung);
            pst.setDouble(4, soLuongT);
            pst.setDouble(5, giaT);
            pst.setString(6, nhaCCT);
            
        int i = pst.executeUpdate();                   
        if(i==1)
            //System.out.println("Data intert Successfull");
            dialog.display("Thông báo", "Đã thêm thành công!");    
            LoadDataFromDatabase();
            LamMoiT(event);
        
        } catch (SQLException ex) {
            dialog.display("Thông báo", "Không thành công!");    
            Logger.getLogger(ThuocController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        pst.close();
        }  
       }
    }
    
    private void searchdsT(){
        txtTKT.setOnKeyReleased(e -> {
            if(txtTKT.getText().equals("")){
                LoadDataFromDatabase();
            }
            else {
                data.clear();
                String sql = "select * from Thuoc where MaThuoc LIKE '%"+txtTKT.getText()+"%'"
                        + "UNION select * from Thuoc where TenThuoc LIKE '%"+txtTKT.getText()+"%'"
                        + "UNION select * from Thuoc where TacDung LIKE '%"+txtTKT.getText()+"%'"
                        + "UNION select * from Thuoc where SoLuongT LIKE '%"+txtTKT.getText()+"%'"
                        + "UNION select * from Thuoc where GiaThuoc LIKE '%"+txtTKT.getText()+"%'"
                        + "UNION select * from Thuoc where NhaCCT LIKE '%"+txtTKT.getText()+"%'";

                try {
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if(rs.next()){
                        System.out.println(rs.getString(1));
                        data.add(new DsThuoc(rs.getString(1),rs.getString(2),rs.getString(3) ,""+rs.getDouble(4),
                        ""+rs.getString(5),rs.getString(6)));
                    }
                    tableThuoc.setItems(data);
                            } catch (SQLException ex) {
                    Logger.getLogger(ThuocController.class.getName()).log(Level.SEVERE, null, ex);
            
                }
            }
        });
    }
    
    private void setCellTable(){
        
        colMaThuoc.setCellValueFactory(new PropertyValueFactory<>("MaThuoc"));
        colTenThuoc.setCellValueFactory(new PropertyValueFactory<>("TenThuoc"));
        colCD.setCellValueFactory(new PropertyValueFactory<>("TacDung"));
        colSLThuoc.setCellValueFactory(new PropertyValueFactory<>("SoLuongT"));
        colGiaThuoc.setCellValueFactory(new PropertyValueFactory<>("GiaThuoc"));
        colNCCT.setCellValueFactory(new PropertyValueFactory<>("NhaCCT"));     
    } 
    

    private void LoadDataFromDatabase(){
        data.clear();
        try {
            pst = con.prepareStatement("Select * from Thuoc");
            rs = pst.executeQuery();
            
            while (rs.next()){
                data.add(new DsThuoc(rs.getString(1),rs.getString(2),rs.getString(3) ,""+rs.getDouble(4),
                        ""+rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThuocController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableThuoc.setItems(data);
      
    }

    private void setCellValueFromTableToTextField(){
        tableThuoc.setOnMouseClicked(e -> {
            DsThuoc dst = tableThuoc.getItems().get(tableThuoc.getSelectionModel().getSelectedIndex());
            txtMaT.setText(dst.getMaThuoc());
            txtTenT.setText(dst.getMaThuoc());
            txtCD.setText(dst.getTacDung());
            txtSLT.setText(dst.getSoLuongT());
            txtGiaT.setText(dst.getGiaThuoc());
            txtNCCT.setText(dst.getNhaCCT());

        });
    }

    @FXML
    private void InT(ActionEvent event) throws SQLException {
      Tvoice();
    }
    private void Tvoice() throws SQLException{
        String souceFile = "E:\\QLTT\\src\\report\\RThuoc.jrxml";
        try {
            JasperReport jr = JasperCompileManager.compileReport(souceFile);
            HashMap<String, Object> para = new HashMap<>();
                para.put("cashier","Thuốc");
           ArrayList<Tlist> tl = new ArrayList<>();
           for (DsThuoc r: data){
                 tl.add(new Tlist(r.getMaThuoc(), r.getTenThuoc(),r.getTacDung(),
                         ""+r.getSoLuongT(),""+r.getGiaThuoc(),r.getNhaCCT()));
           }
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(tl);
            JasperPrint jp = JasperFillManager.fillReport(jr, para, jcs);
            JasperViewer.viewReport(jp);                              
        } catch (JRException ex) {
            Logger.getLogger(QTNuoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
