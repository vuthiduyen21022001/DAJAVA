
package validation;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class txtFValidation {
    public static boolean isTextFieldNotEmpty(TextField tf){
        boolean b= false;
        if(tf.getText().length() != 0 || !tf.getText().isEmpty())
            b=true;
        return b;
    }
    
     public static boolean isTextFieldNotEmpty(TextField tf, Label lb, String errorMessage){
        boolean b= true;
        String msg = null;
        tf.getStyleClass().remove("error");
        if(!isTextFieldNotEmpty(tf)){
            b=false;
            msg = errorMessage;
            tf.getStyleClass().add("error");
        }
        lb.setText(msg);
        return b;
     }
     
     public static boolean isTextFieldTypeNuber(TextField tf){
        boolean b= false;
        if(tf.getText().matches("([0-9]+(\\.[0-9]+)?)+"))
            b=true;
        return b;
     }
     
     public static boolean isTextFieldTypeNuber(TextField tf, Label lb, String errorMessage){
        boolean b= true;
        String msg = null;
        tf.getStyleClass().remove("error");
        if(!isTextFieldTypeNuber(tf)){
            b=true;
            msg = errorMessage;
            tf.getStyleClass().add("error");
        }
        lb.setText(msg);
        return b;
     }

    public static boolean isTextFieldNotEmpty(DatePicker txtThoiGian, Label erTG, String chưa_nhập_hoặc_nhập_không_hợp_lệ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
