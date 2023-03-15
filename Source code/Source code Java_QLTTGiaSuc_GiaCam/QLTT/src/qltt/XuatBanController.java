/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
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
import report.xlist;

/**
 *
 * @author LamChau
 */
public class XuatBanController implements Initializable {
    
    private Label label;
    @FXML
    private TableView<DsNhap> tableTTVNB;
    @FXML
    private TableColumn<?, ?> colMaVN;
    @FXML
    private TableColumn<?, ?> colTenVN;
    @FXML
    private TableColumn<?, ?> colSLN;
    @FXML
    private TableColumn<?, ?> colTLN;
    @FXML
    private TableColumn<?, ?> colNN;
    @FXML
    private TableColumn<?, ?> colGN;
    @FXML
    private TextField txtMaVN;
    
    private Connection con;
    private PreparedStatement pst =null;
    private ResultSet rs =null;
    private ObservableList<DsNhap> data;
    private ObservableList<DsXB> data1;
    @FXML
    private TableView<DsXB> tableXB;
    @FXML
    private TableColumn<?, ?> colMaVNB;
    @FXML
    private TableColumn<?, ?> colMaB;
    @FXML
    private TableColumn<?, ?> colSLB;
    @FXML
    private TableColumn<?, ?> colTLB;
    @FXML
    private TableColumn<?, ?> colDGB;
    @FXML
    private TableColumn<?, ?> colNB;
    @FXML
    private TextField txtMaB;
    @FXML
    private TextField txtSLB;
    @FXML
    private TextField txtTLB;
    @FXML
    private TextField txtGB;
    @FXML
    private DatePicker txtNB;
    @FXML
    private Button btnThemB;
    @FXML
    private Button btnXoaXB;
    @FXML
    private Button btnSuaXB;
    @FXML
    private Button btnLMXB;
    @FXML
    private TextField txtTKTTB;
    @FXML
    private Label erMaB;
    @FXML
    private Label erSLB;
    @FXML
    private Label erTLB;
    @FXML
    private Label erDGB;
    @FXML
    private Label erNB;
    @FXML
    private TextField txtTKXB;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = dba.DBConnection.qlttConnection();
        data = FXCollections.observableArrayList();
        data1 = FXCollections.observableArrayList();
        setCellTable();
        LoadDataFromDatabase();
        setCellValueFromTableToTextField();
        searchTTXB();
        searchXB();
    }    
    private void setCellTable(){
        
        colMaVN.setCellValueFactory(new PropertyValueFactory<>("maVatNuoi"));
        colTenVN.setCellValueFactory(new PropertyValueFactory<>("TenVN"));
        colSLN.setCellValueFactory(new PropertyValueFactory<>("SLNhap"));
        colTLN.setCellValueFactory(new PropertyValueFactory<>("TLNhap"));
        colGN.setCellValueFactory(new PropertyValueFactory<>("GiaNhap"));
        colNN.setCellValueFactory(new PropertyValueFactory<>("NgayNhap"));
        
        colMaB.setCellValueFactory(new PropertyValueFactory<>("MaBan"));
        colMaVNB.setCellValueFactory(new PropertyValueFactory<>("MaVatNuoi"));
        colSLB.setCellValueFactory(new PropertyValueFactory<>("SLBan"));
        colTLB.setCellValueFactory(new PropertyValueFactory<>("TLBan"));
        colDGB.setCellValueFactory(new PropertyValueFactory<>("GiaBan"));
        colNB.setCellValueFactory(new PropertyValueFactory<>("NgayBan"));
       
       
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
            Logger.getLogger(XuatBanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableTTVNB.setItems(data);
        
        data1.clear();
        try {
            pst = con.prepareStatement("Select * from XuatBan");
            rs = pst.executeQuery();
            
            while (rs.next()){
                 data1.add(new DsXB(rs.getString(1),rs.getString(2),""+rs.getDouble(3) ,""+rs.getDouble(4),
                         ""+rs.getDouble(5),""+rs.getDate(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatBanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableXB.setItems(data1);
      
    }

    private void setCellValueFromTableToTextField(){
        tableTTVNB.setOnMouseClicked(e -> {
            DsNhap dsn = tableTTVNB.getItems().get(tableTTVNB.getSelectionModel().getSelectedIndex());
            txtMaVN.setText(dsn.getMaVatNuoi());
 
        });
        
        tableXB.setOnMouseClicked(e -> {
            DsXB dsxb = tableXB.getItems().get(tableXB.getSelectionModel().getSelectedIndex());
            txtMaVN.setText(dsxb.getMaVatNuoi());
            txtMaB.setText(dsxb.getMaBan());
            txtSLB.setText(dsxb.getSLBan());
            txtTLB.setText(dsxb.getTLBan());
            txtGB.setText(dsxb.getGiaBan());
            txtNB.setValue(LocalDate.parse(dsxb.getNgayBan()));
 
        });
    }
    
    private void searchTTXB(){
        txtTKTTB.setOnKeyReleased(e -> {
            if(txtTKTTB.getText().equals("")){
                LoadDataFromDatabase();
            }
            else {
                data.clear();
                String sql = "select * from NhapVN where MaVatNuoi LIKE '%"+txtTKTTB.getText()+"%'"                    
                        + "UNION select * from NhapVN where TenVN LIKE '%"+txtTKTTB.getText()+"%'"
                        + "UNION select * from NhapVN where SLNhap LIKE '%"+txtTKTTB.getText()+"%'"
                        + "UNION select * from NhapVN where TLNhap LIKE '%"+txtTKTTB.getText()+"%'"
                        + "UNION select * from NhapVN where GiaNhap LIKE '%"+txtTKTTB.getText()+"%'"
                        + "UNION select * from NhapVN where NgayNhap LIKE '%"+txtTKTTB.getText()+"%'";
                try {
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if(rs.next()){
                        System.out.println(rs.getString(1));
                       data.add(new DsNhap(rs.getString(1),rs.getString(2),rs.getString(3) ,""+rs.getDouble(4),
                        ""+rs.getDouble(5), ""+rs.getDouble(6), ""+rs.getDate(7),rs.getString(8)));
                    }
                    tableTTVNB.setItems(data);
                            } catch (SQLException ex) {
                    Logger.getLogger(NhapVatNuoiController.class.getName()).log(Level.SEVERE, null, ex);
            
                }
            }
        });
        
        
    }
    private void searchXB(){
        txtTKXB.setOnKeyReleased(e -> {
            if(txtTKXB.getText().equals("")){
                LoadDataFromDatabase();
            }
            else {
                data1.clear();
                String sql = "select * from XuatBan where MaXuat LIKE '%"+txtTKXB.getText()+"%'"                    
                        + "UNION select * from XuatBan where MaVatNuoi LIKE '%"+txtTKXB.getText()+"%'"
                        + "UNION select * from XuatBan where SLBan LIKE '%"+txtTKXB.getText()+"%'"
                        + "UNION select * from XuatBan where TLBan LIKE '%"+txtTKXB.getText()+"%'"
                        + "UNION select * from XuatBan where GiaBan LIKE '%"+txtTKXB.getText()+"%'"
                        + "UNION select * from XuatBan where NgayBan LIKE '%"+txtTKXB.getText()+"%'";
                try {
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if(rs.next()){
                        System.out.println(rs.getString(1));
                        data1.add(new DsXB(rs.getString(1),rs.getString(2),""+rs.getDouble(3) ,""+rs.getDouble(4),
                         ""+rs.getDouble(5),""+rs.getDate(6)));
                    }
                    tableXB.setItems(data1);
                            } catch (SQLException ex) {
                    Logger.getLogger(XuatBanController.class.getName()).log(Level.SEVERE, null, ex);
            
                }
            }
        });
    }
    @FXML
    private void ThemXB(ActionEvent event) throws SQLException {
       boolean isMaBEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaB, erMaB, "Trống hoặc không hợp lệ!");
       boolean isSLBEmpty = validation.txtFValidation.isTextFieldTypeNuber(txtSLB, erSLB, "Trống hoặc không hợp lệ!");
       boolean isTLBEmpty = validation.txtFValidation.isTextFieldTypeNuber(txtTLB, erTLB, "Trống hoặc không hợp lệ!");
       boolean isDGBEmpty = validation.txtFValidation.isTextFieldTypeNuber(txtGB, erDGB, "Trống hoặc không hợp lệ!");

       if(isMaBEmpty && isSLBEmpty && isTLBEmpty && isDGBEmpty ){
       String sql = "Insert into XuatBan(MaVatNuoi,MaXuat,SLBan,TLBan,NgayBan,GiaBan) values(?,?,?,?,?,?)";
       String maVN = txtMaVN.getText();
       String maBan = txtMaB.getText();
       double sLBan = Double.valueOf(txtSLB.getText());
       double tLBan = Double.valueOf(txtTLB.getText());
       Date ngayBan = Date.valueOf(txtNB.getValue());
       double giaBan = Double.valueOf(txtGB.getText());
               
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, maVN);
            pst.setString(2, maBan);
            pst.setDouble(3, sLBan);
            pst.setDouble(4, tLBan);            
            pst.setDate(5,  ngayBan);
            pst.setDouble(6, giaBan);
            
        int i = pst.executeUpdate();                   
        if(i==1)
            //System.out.println("Data intert Successfull");
            dialog.display("Thông báo", "Đã thêm thành công!");    
            LoadDataFromDatabase();
            LamMoiXB(event);
        
        } catch (SQLException ex) {
            dialog.display("Thông báo", "Không thành công!");    
            Logger.getLogger(XuatBanController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        finally{
        pst.close();
        }  
       }
    }

    @FXML
    private void XoaXB(ActionEvent event) {
        boolean isMaBEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaB, erMaB, "Trống hoặc không hợp lệ!");
        if(isMaBEmpty){
            String sql = "delete from XuatBan Where MaXuat = ?";
            try {
                pst = con.prepareStatement(sql);
                pst.setString(1, txtMaB.getText());
                int i = pst.executeUpdate();
                if(i==1){
                    dialog.display("Thông báo", "Xoá thành công!");
                    LoadDataFromDatabase();
                    LamMoiXB(event);
                }
            } catch (SQLException ex) {
                dialog.display("Thông báo", "Xoá không thành công!");
                Logger.getLogger(XuatBanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void SuaXB(ActionEvent event) {
     String sql="Update XuatBan set MaVatNuoi = ? ,SLBan =? ,TLBan =? ,NgayBan =? ,GiaBan =? where MaXuat =?";
     try {
       String maVN = txtMaVN.getText();
       String maBan = txtMaB.getText();
       double sLBan = Double.valueOf(txtSLB.getText());
       double tLBan = Double.valueOf(txtTLB.getText());
       Date ngayBan = Date.valueOf(txtNB.getValue());
       double giaBan = Double.valueOf(txtGB.getText());
            pst = con.prepareStatement(sql);
            pst.setString(1, maVN); 
            pst.setDouble(2, sLBan);
            pst.setDouble(3, tLBan);            
            pst.setDate(4,  ngayBan);
            pst.setDouble(5, giaBan);
            pst.setString(6, maBan);
            int i = pst.executeUpdate();
        if(i==1)
            dialog.display("Thông báo", "Da Sua Thanh Cong!");
            LoadDataFromDatabase();     
            LamMoiXB(event);
        } catch (SQLException ex) {
            dialog.display("Thông báo", "Không thành công!");    
            Logger.getLogger(NhapVatNuoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      @FXML
    private void LamMoiXB(ActionEvent event) {
        txtMaB.clear();
        txtMaVN.clear();
        txtSLB.clear();
        txtTLB.clear();
        txtGB.clear();
        txtNB.setValue(LocalDate.now());
    }

    @FXML
    private void InX(ActionEvent event) throws SQLException {
        Xvoice();
    }

    private void Xvoice() throws SQLException{
        String souceFile = "E:\\QLTT\\src\\report\\RXuat.jrxml";
        try {
            JasperReport jr = JasperCompileManager.compileReport(souceFile);
            HashMap<String, Object> para = new HashMap<>();
                para.put("cashier","Xuất vật nuôi");
           ArrayList<xlist> xl = new ArrayList<>();
           for (DsXB r: data1){
                 xl.add(new xlist(r.getMaBan(), r.getMaVatNuoi(),""+r.getSLBan(),
                         ""+r.getTLBan(),""+r.getGiaBan(),""+r.getNgayBan()));
           }
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(xl);
            JasperPrint jp = JasperFillManager.fillReport(jr, para, jcs);
            JasperViewer.viewReport(jp);                              
        } catch (JRException ex) {
            Logger.getLogger(QTNuoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

  