
package qltt;


public class DsMK {
    private String TaiKhoan;
    private String MatKhauM;

    public DsMK(String TaiKhoan, String MatKhauM) {
        this.TaiKhoan = TaiKhoan;
        this.MatKhauM = MatKhauM;
    }

    /**
     * @return the TaiKhoan
     */
    public String getTaiKhoan() {
        return TaiKhoan;
    }

    /**
     * @param TaiKhoan the TaiKhoan to set
     */
    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }



    /**
     * @return the MatKhauM
     */
    public String getMatKhauM() {
        return MatKhauM;
    }

    /**
     * @param MatKhauM the MatKhauM to set
     */
    public void setMatKhauM(String MatKhauM) {
        this.MatKhauM = MatKhauM;
    }
    
}
