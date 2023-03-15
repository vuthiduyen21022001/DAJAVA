
package qltt;


public class DsXB {
   private String MaBan;
   private String MaVatNuoi;
   private String SLBan;
   private String TLBan;
   private String GiaBan;
   private String NgayBan;

    public DsXB(String MaBan, String MaVatNuoi, String SLBan, String TLBan, String GiaBan, String NgayBan) {
        this.MaBan = MaBan;
        this.MaVatNuoi = MaVatNuoi;
        this.SLBan = SLBan;
        this.TLBan = TLBan;
        this.GiaBan = GiaBan;
        this.NgayBan = NgayBan;
    }

    /**
     * @return the MaBan
     */
    public String getMaBan() {
        return MaBan;
    }

    /**
     * @param MaBan the MaBan to set
     */
    public void setMaBan(String MaBan) {
        this.MaBan = MaBan;
    }

    /**
     * @return the MaVatNuoi
     */
    public String getMaVatNuoi() {
        return MaVatNuoi;
    }

    /**
     * @param MaVatNuoi the MaVatNuoi to set
     */
    public void setMaVatNuoi(String MaVatNuoi) {
        this.MaVatNuoi = MaVatNuoi;
    }

    /**
     * @return the SLBan
     */
    public String getSLBan() {
        return SLBan;
    }

    /**
     * @param SLBan the SLBan to set
     */
    public void setSLBan(String SLBan) {
        this.SLBan = SLBan;
    }

    /**
     * @return the TLBan
     */
    public String getTLBan() {
        return TLBan;
    }

    /**
     * @param TLBan the TLBan to set
     */
    public void setTLBan(String TLBan) {
        this.TLBan = TLBan;
    }

    /**
     * @return the GiaBan
     */
    public String getGiaBan() {
        return GiaBan;
    }

    /**
     * @param GiaBan the GiaBan to set
     */
    public void setGiaBan(String GiaBan) {
        this.GiaBan = GiaBan;
    }

    /**
     * @return the NgayBan
     */
    public String getNgayBan() {
        return NgayBan;
    }

    /**
     * @param NgayBan the NgayBan to set
     */
    public void setNgayBan(String NgayBan) {
        this.NgayBan = NgayBan;
    }

}