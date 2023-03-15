
package qltt;


public class DsNhap {
   private String maVatNuoi;
   private String LoaiVN;
   private String TenVN;
   private String SLNhap;
   private String TLNhap;
   private String GiaNhap;
   private String NgayNhap;
   private String GhiChu;

    public DsNhap(String maVatNuoi, String LoaiVN, String TenVN, String SLNhap, String TLNhap, String GiaNhap, String NgayNhap, String GhiChu) {
        this.maVatNuoi = maVatNuoi;
        this.LoaiVN = LoaiVN;
        this.TenVN = TenVN;
        this.SLNhap = SLNhap;
        this.TLNhap = TLNhap;
        this.GiaNhap = GiaNhap;
        this.NgayNhap = NgayNhap;
        this.GhiChu = GhiChu;
    }

    /**
     * @return the maVatNuoi
     */
    public String getMaVatNuoi() {
        return maVatNuoi;
    }

    /**
     * @param maVatNuoi the maVatNuoi to set
     */
    public void setMaVatNuoi(String maVatNuoi) {
        this.maVatNuoi = maVatNuoi;
    }

    /**
     * @return the LoaiVN
     */
    public String getLoaiVN() {
        return LoaiVN;
    }

    /**
     * @param LoaiVN the LoaiVN to set
     */
    public void setLoaiVN(String LoaiVN) {
        this.LoaiVN = LoaiVN;
    }

    /**
     * @return the TenVN
     */
    public String getTenVN() {
        return TenVN;
    }

    /**
     * @param TenVN the TenVN to set
     */
    public void setTenVN(String TenVN) {
        this.TenVN = TenVN;
    }

    /**
     * @return the SLNhap
     */
    public String getSLNhap() {
        return SLNhap;
    }

    /**
     * @param SLNhap the SLNhap to set
     */
    public void setSLNhap(String SLNhap) {
        this.SLNhap = SLNhap;
    }

    /**
     * @return the TLNhap
     */
    public String getTLNhap() {
        return TLNhap;
    }

    /**
     * @param TLNhap the TLNhap to set
     */
    public void setTLNhap(String TLNhap) {
        this.TLNhap = TLNhap;
    }

    /**
     * @return the GiaNhap
     */
    public String getGiaNhap() {
        return GiaNhap;
    }

    /**
     * @param GiaNhap the GiaNhap to set
     */
    public void setGiaNhap(String GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    /**
     * @return the NgayNhap
     */
    public String getNgayNhap() {
        return NgayNhap;
    }

    /**
     * @param NgayNhap the NgayNhap to set
     */
    public void setNgayNhap(String NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    /**
     * @return the GhiChu
     */
    public String getGhiChu() {
        return GhiChu;
    }

    /**
     * @param GhiChu the GhiChu to set
     */
    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

}