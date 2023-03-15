
package qltt;


public class DsNV {
    private String MaNV;
    private String TenNV;
    private String GT;
    private String NS;
    private String SDT;
    private String DC;

    public DsNV(String MaNV, String TenNV, String GT, String NS, String SDT, String DC) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.GT = GT;
        this.NS = NS;
        this.SDT = SDT;
        this.DC = DC;
    }

    /**
     * @return the MaNV
     */
    public String getMaNV() {
        return MaNV;
    }

    /**
     * @param MaNV the MaNV to set
     */
    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    /**
     * @return the TenNv
     */
    public String getTenNV() {
        return TenNV;
    }

    /**
     * @param TenNv the TenNv to set
     */
    public void setTenNV(String TenNv) {
        this.TenNV = TenNV;
    }

    /**
     * @return the GT
     */
    public String getGT() {
        return GT;
    }

    /**
     * @param GT the GT to set
     */
    public void setGT(String GT) {
        this.GT = GT;
    }

    /**
     * @return the NS
     */
    public String getNS() {
        return NS;
    }

    /**
     * @param NS the NS to set
     */
    public void setNS(String NS) {
        this.NS = NS;
    }

    /**
     * @return the SDT
     */
    public String getSDT() {
        return SDT;
    }

    /**
     * @param SDT the SDT to set
     */
    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    /**
     * @return the DC
     */
    public String getDC() {
        return DC;
    }

    /**
     * @param DC the DC to set
     */
    public void setDC(String DC) {
        this.DC = DC;
    }

    String getNs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
