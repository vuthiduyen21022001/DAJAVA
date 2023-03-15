
package qltt;


public class DsThuoc {
    private String MaThuoc;
    private String TenThuoc;
    private String TacDung;
    private String SoLuongT;
    private String GiaThuoc;
    private String NhaCCT;

    public DsThuoc(String MaThuoc, String TenThuoc, String TacDung, String SoLuongT, String GiaThuoc, String NhaCCT) {
        this.MaThuoc = MaThuoc;
        this.TenThuoc = TenThuoc;
        this.TacDung = TacDung;
        this.SoLuongT = SoLuongT;
        this.GiaThuoc = GiaThuoc;
        this.NhaCCT = NhaCCT;
    }

    /**
     * @return the MaThuoc
     */
    public String getMaThuoc() {
        return MaThuoc;
    }

    /**
     * @param MaThuoc the MaThuoc to set
     */
    public void setMaThuoc(String MaThuoc) {
        this.MaThuoc = MaThuoc;
    }

    /**
     * @return the TenThuoc
     */
    public String getTenThuoc() {
        return TenThuoc;
    }

    /**
     * @param TenThuoc the TenThuoc to set
     */
    public void setTenThuoc(String TenThuoc) {
        this.TenThuoc = TenThuoc;
    }

    /**
     * @return the TacDung
     */
    public String getTacDung() {
        return TacDung;
    }

    /**
     * @param TacDung the TacDung to set
     */
    public void setTacDung(String TacDung) {
        this.TacDung = TacDung;
    }

    /**
     * @return the SoLuong
     */
    public String getSoLuongT() {
        return SoLuongT;
    }

    /**
     * @param SoLuong the SoLuong to set
     */
    public void setSoLuongT(String SoLuong) {
        this.SoLuongT = SoLuongT;
    }

    /**
     * @return the GiaTuoc
     */
    public String getGiaThuoc() {
        return GiaThuoc;
    }

    /**
     * @param GiaTuoc the GiaTuoc to set
     */
    public void setGiaThuoc(String GiaTuoc) {
        this.GiaThuoc = GiaTuoc;
    }

    /**
     * @return the NhaCCT
     */
    public String getNhaCCT() {
        return NhaCCT;
    }

    /**
     * @param NhaCCT the NhaCCT to set
     */
    public void setNhaCCT(String NhaCCT) {
        this.NhaCCT = NhaCCT;
    }
    
}
