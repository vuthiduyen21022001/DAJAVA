
package qltt;


public class DsTA {
    private String MaTA;
    private String TenTA;
    private String SoLuongTA;
    private String GiaTA;
    private String NhaCC;

    public DsTA(String MaTA, String TenTA, String SoLuongTA, String GiaTA, String NhaCC) {
        this.MaTA = MaTA;
        this.TenTA = TenTA;
        this.SoLuongTA = SoLuongTA;
        this.GiaTA = GiaTA;
        this.NhaCC = NhaCC;
    }

    /**
     * @return the MaTA
     */
    public String getMaTA() {
        return MaTA;
    }

    /**
     * @param MaTA the MaTA to set
     */
    public void setMaTA(String MaTA) {
        this.MaTA = MaTA;
    }

    /**
     * @return the TenTA
     */
    public String getTenTA() {
        return TenTA;
    }

    /**
     * @param TenTA the TenTA to set
     */
    public void setTenTA(String TenTA) {
        this.TenTA = TenTA;
    }

    /**
     * @return the SoLuongTA
     */
    public String getSoLuongTA() {
        return SoLuongTA;
    }

    /**
     * @param SoLuongTA the SoLuongTA to set
     */
    public void setSoLuongTA(String SoLuongTA) {
        this.SoLuongTA = SoLuongTA;
    }

    /**
     * @return the GiaTA
     */
    public String getGiaTA() {
        return GiaTA;
    }

    /**
     * @param GiaTA the GiaTA to set
     */
    public void setGiaTA(String GiaTA) {
        this.GiaTA = GiaTA;
    }

    /**
     * @return the NhaCC
     */
    public String getNhaCC() {
        return NhaCC;
    }

    /**
     * @param NhaCC the NhaCC to set
     */
    public void setNhaCC(String NhaCC) {
        this.NhaCC = NhaCC;
    }
    
}
