
package report;


public class NVlist {
    private String manv;
    private String tennv;
    private String gt;
    private String ns;
    private String sdt;
    private String dc;

    public NVlist(String manv, String tennv, String gt, String ns, String sdt, String dc) {
        this.manv = manv;
        this.tennv = tennv;
        this.gt = gt;
        this.ns = ns;
        this.sdt = sdt;
        this.dc = dc;
    }

    /**
     * @return the manv
     */
    public String getManv() {
        return manv;
    }

    /**
     * @param manv the manv to set
     */
    public void setManv(String manv) {
        this.manv = manv;
    }

    /**
     * @return the tennv
     */
    public String getTennv() {
        return tennv;
    }

    /**
     * @param tennv the tennv to set
     */
    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    /**
     * @return the gt
     */
    public String getGt() {
        return gt;
    }

    /**
     * @param gt the gt to set
     */
    public void setGt(String gt) {
        this.gt = gt;
    }

    /**
     * @return the ns
     */
    public String getNs() {
        return ns;
    }

    /**
     * @param ns the ns to set
     */
    public void setNs(String ns) {
        this.ns = ns;
    }

    /**
     * @return the sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     * @param sdt the sdt to set
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    /**
     * @return the dc
     */
    public String getDc() {
        return dc;
    }

    /**
     * @param dc the dc to set
     */
    public void setDc(String dc) {
        this.dc = dc;
    }

}