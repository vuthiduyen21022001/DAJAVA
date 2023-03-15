
package report;


public class Tlist {
    private String mathuoc;
    private String tenthuoc;
    private String tacdung;
    private String slthuoc;
    private String giathuoc;
    private String nhacct; 

    public Tlist(String mathuoc, String tenthuoc, String tacdung, String slthuoc, String giathuoc, String nhacct) {
        this.mathuoc = mathuoc;
        this.tenthuoc = tenthuoc;
        this.tacdung = tacdung;
        this.slthuoc = slthuoc;
        this.giathuoc = giathuoc;
        this.nhacct = nhacct;
    }

    /**
     * @return the mathuoc
     */
    public String getMathuoc() {
        return mathuoc;
    }

    /**
     * @param mathuoc the mathuoc to set
     */
    public void setMathuoc(String mathuoc) {
        this.mathuoc = mathuoc;
    }

    /**
     * @return the tenthuoc
     */
    public String getTenthuoc() {
        return tenthuoc;
    }

    /**
     * @param tenthuoc the tenthuoc to set
     */
    public void setTenthuoc(String tenthuoc) {
        this.tenthuoc = tenthuoc;
    }

    /**
     * @return the tacdung
     */
    public String getTacdung() {
        return tacdung;
    }

    /**
     * @param tacdung the tacdung to set
     */
    public void setTacdung(String tacdung) {
        this.tacdung = tacdung;
    }

    /**
     * @return the slthuoc
     */
    public String getSlthuoc() {
        return slthuoc;
    }

    /**
     * @param slthuoc the slthuoc to set
     */
    public void setSlthuoc(String slthuoc) {
        this.slthuoc = slthuoc;
    }

    /**
     * @return the giathuoc
     */
    public String getGiathuoc() {
        return giathuoc;
    }

    /**
     * @param giathuoc the giathuoc to set
     */
    public void setGiathuoc(String giathuoc) {
        this.giathuoc = giathuoc;
    }

    /**
     * @return the nhacct
     */
    public String getNhacct() {
        return nhacct;
    }

    /**
     * @param nhacct the nhacct to set
     */
    public void setNhacct(String nhacct) {
        this.nhacct = nhacct;
    }
}
