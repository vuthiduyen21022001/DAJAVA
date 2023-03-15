
package qltt;

import Dialog.dialog;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
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
import report.Nlist;



public class NhapVatNuoiController implements Initializable {

    @FXML
    private TextField txtMaVN;
    @FXML
    private TextField txtTenVN;
    @FXML
    private TextField txtSLVNN;
    @FXML
    private TextField txtTLVNN;
    @FXML
    private TextField txtGiaNhap;
    @FXML
    private Button btnThem;

    private Connection con;
    private PreparedStatement pst =null;
    private ResultSet rs =null;
    private ObservableList<DsNhap> data;

    
    @FXML
    private TextField txtLoaiVN;
    @FXML
    private TableColumn<?, ?> colMaNhap;
    @FXML
    private TableColumn<?, ?> colLoaiNhap;
    @FXML
    private TableColumn<?, ?> colTenNhap;
    @FXML
    private TableColumn<?, ?> colSoLuongNhap;
    @FXML
    private TableColumn<?, ?> colTrongLuongNhap;
    @FXML
    private TableColumn<?, ?> colGiaNhap;
    
    @FXML
    private Button btnXoa;
    @FXML
    private Button btnSua;
    @FXML
    private Label erMa;
    @FXML
    private Label erLoai;
    @FXML
    private Label erTen;
    @FXML
    private Label erSLNhap;
    @FXML
    private Label erTLNhap;
    @FXML
    private Label erGiaNhap;
    @FXML
    private Button brnLamMoi;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label erNgayNhap;
    @FXML
    private TextField txtGhiChu;
    @FXML
    private TableColumn<?, ?> colNgayNhap;
    @FXML
    private TableColumn<?, ?> colGhiChu;
    @FXML
    private DatePicker DPNgayNhap;
    @FXML
    private TableView<DsNhap> tableNVN;
    @FXML
    private Button btnIn;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = dba.DBConnection.qlttConnection();
        data = FXCollections.observableArrayList();
        setCellTable();
        LoadDataFromDatabase();
        setCellValueFromTableToTextField();
        searchdsNhap();
        
   
    }   

    
    @FXML
    private void Them(ActionEvent event) throws SQLException {
       boolean isMaEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaVN, erMa, "Trống hoặc không hợp lệ!");
       boolean isLoaiEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtLoaiVN, erLoai, "Trống hoặc không hợp lệ!");
       boolean isTenEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtTenVN, erTen, "Trống hoặc không hợp lệ!");
       boolean isSLNhapEmpty = validation.txtFValidation.isTextFieldTypeNuber(txtSLVNN, erSLNhap, "Trống hoặc không hợp lệ!");
       boolean isTLNhapEmpty = validation.txtFValidation.isTextFieldTypeNuber(txtTLVNN, erTLNhap, "Trống hoặc không hợp lệ!");
       boolean isGiaNhapEmpty = validation.txtFValidation.isTextFieldTypeNuber(txtGiaNhap, erGiaNhap, "Trống hoặc không hợp lệ!");

       if(isMaEmpty && isLoaiEmpty && isTenEmpty && isSLNhapEmpty && isTLNhapEmpty && isGiaNhapEmpty){
       String sql = "Insert into NhapVN(maVatNuoi,LoaiVN,TenVN,SLNhap,TLNhap,GiaNhap, NgayNhap,GhiChu) values(?,?,?,?,?,?,?,?)";
       String maVN = txtMaVN.getText();
       String loaiVN = txtLoaiVN.getText();
       String tenVN = txtTenVN.getText();
       double sLNhap = Double.valueOf(txtSLVNN.getText());
       double tLNhap = Double.valueOf(txtTLVNN.getText());
       double giaNhap = Double.valueOf(txtGiaNhap.getText());
       Date ngayNhap = Date.valueOf(DPNgayNhap.getValue());
       String ghiChu = String.valueOf(txtGhiChu.getText());
       
               
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, maVN);
            pst.setString(2, loaiVN);
            pst.setString(3, tenVN);
            pst.setDouble(4, sLNhap);
            pst.setDouble(5, tLNhap);
            pst.setDouble(6, giaNhap);
            pst.setDate(7,  ngayNhap);
           // pst.setDate(7, java.sql.Date.valueOf(DPNgayNhap.getValue()));
            pst.setString(8, ghiChu);
            
        int i = pst.executeUpdate();                   
        if(i==1)
            //System.out.println("Data intert Successfull");
            dialog.display("Thông báo", "Đã thêm thành công!");    
            LoadDataFromDatabase();
            LamMoi(event);
        
        } catch (SQLException ex) {
            dialog.display("Thông báo", "Không thành công!");    
            Logger.getLogger(NhapVatNuoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        pst.close();
        }  
       }
    }
    
      @FXML
    private void Xoa(ActionEvent event) {
        boolean isMaEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaVN, erMa, "Vui lòng nhập mã!");
        if(isMaEmpty){
            String sql = "delete from NhapVN Where MaVatNuoi = ?";
            try {
                pst = con.prepareStatement(sql);
                pst.setString(1, txtMaVN.getText());
                int i = pst.executeUpdate();
                if(i==1){
                    dialog.display("Thông báo", "Xoá thành công!");
                    LoadDataFromDatabase();
                    LamMoi(event);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NhapVatNuoiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void Sua(ActionEvent event) {
      
        String sql="Update NhapVN set LoaiVN = ?, TenVN = ?,SLNhap = ?, TLNhap = ?, GiaNhap = ?,NgayNhap = ?, GhiChu = ? where MaVatNuoi =?";
        try {
            String maVN = txtMaVN.getText();
            String loaiVN = txtLoaiVN.getText();
            String tenVN = txtTenVN.getText();
            double soLuongVN = Double.valueOf(txtSLVNN.getText());
            double trongLuongVN = Double.valueOf(txtTLVNN.getText());
            double giaNhap = Double.valueOf(txtGiaNhap.getText());
            Date ngayNhap = Date.valueOf(DPNgayNhap.getValue());
            String ghiChu = String.valueOf(txtGhiChu.getText());
            pst = con.prepareStatement(sql);
            pst.setString(1, loaiVN);
            pst.setString(2, tenVN);
            pst.setDouble(3, soLuongVN);
            pst.setDouble(4, trongLuongVN);
            pst.setDouble(5, giaNhap);
            pst.setDate(6,  ngayNhap);
           // pst.setDate(6, java.sql.Date.valueOf(DPNgayNhap.getValue()));
            pst.setString(7, ghiChu);
            pst.setString(8, maVN);
            int i = pst.executeUpdate();
        if(i==1)
            dialog.display("Thông báo", "Đã sửa thành công!");
            LoadDataFromDatabase();     
            LamMoi(event);
        } catch (SQLException ex) {
            dialog.display("Thông báo", "Không thành công!");    
            Logger.getLogger(NhapVatNuoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       @FXML
    private void LamMoi(ActionEvent event) {
        txtMaVN.clear();
        txtLoaiVN.clear();
        txtTenVN.clear();
        txtSLVNN.clear();
        txtTLVNN.clear();
        txtGiaNhap.clear();
        DPNgayNhap.setValue(LocalDate.now());
        txtGhiChu.clear();
    }
    
    private void setCellTable(){
        
        colMaNhap.setCellValueFactory(new PropertyValueFactory<>("maVatNuoi"));
        colLoaiNhap.setCellValueFactory(new PropertyValueFactory<>("LoaiVN"));
        colTenNhap.setCellValueFactory(new PropertyValueFactory<>("TenVN"));
        colSoLuongNhap.setCellValueFactory(new PropertyValueFactory<>("SLNhap"));
        colTrongLuongNhap.setCellValueFactory(new PropertyValueFactory<>("TLNhap"));
        colGiaNhap.setCellValueFactory(new PropertyValueFactory<>("GiaNhap"));
        colNgayNhap.setCellValueFactory(new PropertyValueFactory<>("NgayNhap"));
        colGhiChu.setCellValueFactory(new PropertyValueFactory<>("GhiChu"));
       
    } 
    

    private void LoadDataFromDatabase(){
        data.clear();
        try {
            pst = con.prepareStatement("Select * from NhapVN");
            rs = pst.executeQuery();
            
            while (rs.next()){
                data.add(new DsNhap(rs.getString(1),rs.getString(2),rs.getString(3) ,""+rs.getDouble(4),
                        ""+rs.getDouble(5), ""+rs.getDouble(6), ""+rs.getDate(7),rs.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhapVatNuoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableNVN.setItems(data);
      
    }

    private void setCellValueFromTableToTextField(){
        tableNVN.setOnMouseClicked(e -> {
            DsNhap dsn = tableNVN.getItems().get(tableNVN.getSelectionModel().getSelectedIndex());
            txtMaVN.setText(dsn.getMaVatNuoi());
            txtLoaiVN.setText(dsn.getLoaiVN());
            txtTenVN.setText(dsn.getTenVN());
            txtSLVNN.setText(dsn.getSLNhap());
            txtTLVNN.setText(dsn.getTLNhap());
            txtGiaNhap.setText(dsn.getGiaNhap());
            DPNgayNhap.setValue(LocalDate.parse(dsn.getNgayNhap()));
            txtGhiChu.setText(dsn.getGhiChu());
        });
    }

    private void searchdsNhap(){
        txtSearch.setOnKeyReleased(e -> {
            if(txtSearch.getText().equals("")){
                LoadDataFromDatabase();
            }
            else {
                data.clear();
                String sql = "select * from NhapVN where MaVatNuoi LIKE '%"+txtSearch.getText()+"%'"
                        + "UNION select * from NhapVN where LoaiVN LIKE '%"+txtSearch.getText()+"%'"
                        + "UNION select * from NhapVN where TenVN LIKE '%"+txtSearch.getText()+"%'"
                        + "UNION select * from NhapVN where SLNhap LIKE '%"+txtSearch.getText()+"%'"
                        + "UNION select * from NhapVN where TLNhap LIKE '%"+txtSearch.getText()+"%'"
                        + "UNION select * from NhapVN where GiaNhap LIKE '%"+txtSearch.getText()+"%'"
                        + "UNION select * from NhapVN where NgayNhap LIKE '%"+txtSearch.getText()+"%'"
                        + "UNION select * from NhapVN where GhiChu LIKE '%"+txtSearch.getText()+"%'";
                try {
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if(rs.next()){
                        System.out.println(rs.getString(1));
                        data.add(new DsNhap(rs.getString(1),rs.getString(2),rs.getString(3) ,""+rs.getDouble(4),
                        ""+rs.getDouble(5), ""+rs.getDouble(6), ""+rs.getDate(7),rs.getString(8)));
                    }
                    tableNVN.setItems(data);
                            } catch (SQLException ex) {
                    Logger.getLogger(NhapVatNuoiController.class.getName()).log(Level.SEVERE, null, ex);
            
                }
            }
        });
    }

    @FXML
    private void In(ActionEvent event) throws SQLException {
       NVNvoice();       
    }
    private void NVNvoice() throws SQLException{
        String souceFile = "E:\\QLTT\\src\\report\\RNhap.jrxml";
        try {
            JasperReport jr = JasperCompileManager.compileReport(souceFile);
            HashMap<String, Object> para = new HashMap<>();
                para.put("cashier","Vật Nuôi");
           ArrayList<Nlist> nl = new ArrayList<>();
           for (DsNhap r: data){
                 nl.add(new Nlist(r.getMaVatNuoi(),r.getLoaiVN(),r.getTenVN(),""+r.getSLNhap(),""+r.getTLNhap(),""+r.getGiaNhap(),""+r.getNgayNhap(),r.getGhiChu()));
           }
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(nl);
            JasperPrint jp = JasperFillManager.fillReport(jr, para, jcs);
            JasperViewer.viewReport(jp);  
        } catch (JRException ex) {
            Logger.getLogger(NhapVatNuoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
