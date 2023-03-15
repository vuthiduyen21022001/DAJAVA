
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
import report.QTNlist;


public class QTNuoiController implements Initializable {

   
    @FXML
    private Label label3;
    @FXML
    private Label label31;
    @FXML
    private Button btnLamMoiQT;
    @FXML
    private Button btnSuaQT;
    @FXML
    private Button btnXoaQT;
    @FXML
    private Button btnThemQT;
    @FXML
    private TableView<DsQTNuoi> tableQTN;
    @FXML
    private TableColumn<?, ?> colMaQT;
    @FXML
    private TableColumn<?, ?> colMaVN;
    @FXML
    private TableColumn<?, ?> colMaTA;
    @FXML
    private TableColumn<?, ?> colMaT;
    @FXML
    private TableColumn<?, ?> colMaNV;
    @FXML
    private TableColumn<?, ?> colThoiGian;
    @FXML
    private TableColumn<?, ?> colTTSK;
    @FXML
    private TableColumn<?, ?> colMoTa;
    @FXML
    private TextField txtMaQT;
    @FXML
    private TextField txtMaVN;
    @FXML
    private TextField txtMaTA;
    @FXML
    private TextField txtMaT;
    @FXML
    private TextField txtMaNV;
    @FXML
    private DatePicker txtThoiGian;
    @FXML
    private TextField txtTTSK;
    @FXML
    private Label erMaT;
    @FXML
    private TextField txtMoTa;
    @FXML
    private Label erMaT5;
    
    private Connection con;
    private PreparedStatement pst =null;
    private ResultSet rs =null;
    private ObservableList<DsQTNuoi> data; 
    @FXML
    private Label erMaQT;
    @FXML
    private Label erMaVN;
    @FXML
    private Label erMaTA;
    @FXML
    private Label erMaNV;
    @FXML
    private Label erTG;
    @FXML
    private Label erTTSK;
    @FXML
    private TableColumn<?, ?> colTenVN;
    @FXML
    private TextField txtTKQT;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = dba.DBConnection.qlttConnection();
        data = FXCollections.observableArrayList();
        setCellTable();
        LoadDataFromDatabase();
        setCellValueFromTableToTextField();
        searchdsQTN();
    }    

    @FXML
    private void LamMoiQT(ActionEvent event) {
       txtMaQT.clear();
       txtMaVN.clear();
       txtMaTA.clear();
       txtMaT.clear();
       txtMaNV.clear();
       txtThoiGian.setValue(LocalDate.now());
       txtTTSK.clear();
       txtMoTa.clear();
    }

    @FXML
    private void SuaQT(ActionEvent event) {
        String sql="Update QTNuoi set MaVatNuoi = ? ,MaTA =? ,MaThuoc =? ,MaNV =? ,ThoiGian =? ,TinhTrangSK =? ,MoTa =?  where MaQT =?";
        try {
             String maQT = txtMaQT.getText();
       String maVatNuoi = txtMaVN.getText();
       String maTA = txtMaTA.getText();
       String maThuoc = txtMaT.getText();
       String MaNV = txtMaNV.getText();
       Date thoiGian = Date.valueOf(txtThoiGian.getValue());
       String tTSK = txtTTSK.getText();
       String moTa = txtMoTa.getText();
            pst = con.prepareStatement(sql);
           
            pst.setString(1, maVatNuoi);
            pst.setString(2, maTA);
            pst.setString(3, maThuoc);
            pst.setString(4, MaNV);
            pst.setDate(5,  thoiGian);
            pst.setString(6, tTSK);
            pst.setString(7, moTa);
            pst.setString(8, maQT);
            int i = pst.executeUpdate();
        if(i==1)
            dialog.display("Thông báo", "Cập nhật thành công!");
            LoadDataFromDatabase();     
            LamMoiQT(event);
        } catch (SQLException ex) {
            dialog.display("Thông báo", "Không thành công!");    
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void XoaQT(ActionEvent event) {
        boolean isMaQTEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaQT, erMaQT, "Trống hoặc không hợp lệ!");
        if(isMaQTEmpty){
            String sql = "delete from QTNuoi Where MaQT = ?";
            try {
                pst = con.prepareStatement(sql);
                pst.setString(1, txtMaQT.getText());
                int i = pst.executeUpdate();
                if(i==1){
                    dialog.display("Thông báo", "Xoá thành công!");
                    LoadDataFromDatabase();
                    LamMoiQT(event);
                }
            } catch (SQLException ex) {
                dialog.display("Thông báo", "Xoá không thành công!");
                Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void ThemQT(ActionEvent event) throws SQLException {
       boolean isMaQTEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaQT, erMaQT, "Trống hoặc không hợp lệ!");
       boolean isMaVatNuoiEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaVN, erMaVN, "Trống hoặc không hợp lệ!");
       boolean isMaTAEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaTA, erMaTA, "Trống hoặc không hợp lệ!");
       boolean isMaTEmpty = validation.txtFValidation.isTextFieldTypeNuber(txtMaT, erMaT, "Trống hoặc không hợp lệ!");
       boolean isMaNVEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtMaNV, erMaNV, "Trống hoặc không hợp lệ!");
     //  boolean isThoiGianEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtThoiGian, erTG, "Trống hoặc không hợp lệ!");
       boolean isTTSKEmpty = validation.txtFValidation.isTextFieldNotEmpty(txtTTSK, erTTSK, "Trống hoặc không hợp lệ!");
       
       if(isMaQTEmpty && isMaVatNuoiEmpty && isMaTAEmpty && isMaTEmpty && isMaNVEmpty && isTTSKEmpty){
       String sql = "Insert into QTNuoi(MaQT,MaVatNuoi,MaTA,MaThuoc,MaNV,ThoiGian,TinhTrangSK,MoTa) values(?,?,?,?,?,?,?,?)";
       String maQT = txtMaQT.getText();
       String maVatNuoi = txtMaVN.getText();
       String maTA = txtMaTA.getText();
       String maThuoc = txtMaT.getText();
       String MaNV = txtMaNV.getText();
       Date thoiGian = Date.valueOf(txtThoiGian.getValue());
       String tTSK = txtTTSK.getText();
       String moTa = txtMoTa.getText();
               
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, maQT);
            pst.setString(2, maVatNuoi);
            pst.setString(3, maTA);
            pst.setString(4, maThuoc);
            pst.setString(5, MaNV);
            pst.setDate(6,  thoiGian);
            pst.setString(7, tTSK);
            pst.setString(8, moTa);                   
        int i = pst.executeUpdate();                   
        if(i==1)
            //System.out.println("Data intert Successfull");
            dialog.display("Thông báo", "Đã thêm thành công!");    
            LoadDataFromDatabase();
            LamMoiQT(event);
        
        } catch (SQLException ex) {
            dialog.display("Thông báo", "Không thành công!");    
            Logger.getLogger(QTNuoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        pst.close();
        } 
       }
    }
    private void searchdsQTN(){
        txtTKQT.setOnKeyReleased(e -> {
        if(txtTKQT.getText().equals("")){
             LoadDataFromDatabase();
        }
        else {
                data.clear();
                String sql = "select * from QTNuoi where MaQT LIKE '%"+txtTKQT.getText()+"%'"
                        + "UNION select * from QTNuoi  where MaVatNuoi LIKE '%"+txtTKQT.getText()+"%'"
                        + "UNION select * from QTNuoi  where MaTA LIKE '%"+txtTKQT.getText()+"%'"
                        + "UNION select * from QTNuoi where MaThuoc LIKE '%"+txtTKQT.getText()+"%'"
                        + "UNION select * from QTNuoi  where MaNV LIKE '%"+txtTKQT.getText()+"%'"
                        + "UNION select * from QTNuoi  where ThoiGian LIKE '%"+txtTKQT.getText()+"%'"
                        + "UNION select * from QTNuoi   where TinhTrangSK LIKE '%"+txtTKQT.getText()+"%'"
                        + "UNION select * from QTNuoi   where MoTa LIKE '%"+txtTKQT.getText()+"%'";

                try {
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if(rs.next()){
                        System.out.println(rs.getString(1));
                        data.add(new DsQTNuoi(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4) , rs.getString(5) ,
                       ""+rs.getDate(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                    }
                    tableQTN.setItems(data);
                            } catch (SQLException ex) {
                    Logger.getLogger(QTNuoiController.class.getName()).log(Level.SEVERE, null, ex);
            
                }
            }
        });
    }
    
    private void setCellTable(){
        colMaQT.setCellValueFactory(new PropertyValueFactory<>("MaQT"));
        colMaVN.setCellValueFactory(new PropertyValueFactory<>("MaVatNuoi"));        
        colMaTA.setCellValueFactory(new PropertyValueFactory<>("MaTA"));
        colMaT.setCellValueFactory(new PropertyValueFactory<>("MaThuoc"));
        colMaNV.setCellValueFactory(new PropertyValueFactory<>("MaNV"));
        colThoiGian.setCellValueFactory(new PropertyValueFactory<>("ThoiGian"));   
        colTTSK.setCellValueFactory(new PropertyValueFactory<>("TinhTrangSK")); 
        colMoTa.setCellValueFactory(new PropertyValueFactory<>("MoTa")); 
        colTenVN.setCellValueFactory(new PropertyValueFactory<>("TenVN")); 
        
    } 
    

    private void LoadDataFromDatabase(){
        data.clear();
        try {
            pst = con.prepareStatement("select q.*, n.TenVN	from QTNuoi q Inner join NhapVN n on q.MaVatNuoi= n.MaVatNuoi");
            //pst = con.prepareStatement("Select *    from QTNuoi");
            rs = pst.executeQuery();
            
            while (rs.next()){
                data.add(new DsQTNuoi(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4) , rs.getString(5) ,
                       ""+rs.getDate(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QTNuoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableQTN.setItems(data);
    }

    private void setCellValueFromTableToTextField(){
        tableQTN.setOnMouseClicked(e -> {
        DsQTNuoi dsqtn = tableQTN.getItems().get(tableQTN.getSelectionModel().getSelectedIndex());
        txtMaQT.setText(dsqtn.getMaQT());
        txtMaVN.setText(dsqtn.getMaVatNuoi());
        txtMaTA.setText(dsqtn.getMaTA());
        txtMaT.setText(dsqtn.getMaThuoc());
        txtMaNV.setText(dsqtn.getMaNV());
        txtThoiGian.setValue(LocalDate.parse(dsqtn.getThoiGian()));
        txtTTSK.setText(dsqtn.getTinhTrangSK());
        txtMoTa.setText(dsqtn.getMoTa());
        });
    }

    @FXML
    private void InQTN(ActionEvent event) throws SQLException {
        QTNvoice();
    }
    
     private void QTNvoice() throws SQLException{
        String souceFile = "E:\\QLTT\\src\\report\\RQTNuoi.jrxml";
        try {
            JasperReport jr = JasperCompileManager.compileReport(souceFile);
            HashMap<String, Object> para = new HashMap<>();
                para.put("cashier","Quá trình nuôi");
           ArrayList<QTNlist> qtnl = new ArrayList<>();
           for (DsQTNuoi r: data){
                 qtnl.add(new QTNlist(r.getMaQT(), r.getMaVatNuoi(), r.getTenVN(), r.getMaTA(), r.getMaThuoc(),
                        r.getMaNV(),""+r.getThoiGian(), r.getTinhTrangSK(),r.getMoTa()));
           }
            JRBeanCollectionDataSource jcs = new JRBeanCollectionDataSource(qtnl);
            JasperPrint jp = JasperFillManager.fillReport(jr, para, jcs);
            JasperViewer.viewReport(jp);                              
        } catch (JRException ex) {
            Logger.getLogger(QTNuoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

