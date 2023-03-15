
package report;


public class TAlist {
    private String mata;
    private String tenta;
    private String slta;
    private String giata;
    private String nhacc;

    public TAlist(String mata, String tenta, String slta, String giata, String nhacc) {
        this.mata = mata;
        this.tenta = tenta;
        this.slta = slta;
        this.giata = giata;
        this.nhacc = nhacc;
    }

    /**
     * @return the mata
     */
    public String getMata() {
        return mata;
    }

    /**
     * @param mata the mata to set
     */
    public void setMata(String mata) {
        this.mata = mata;
    }

    /**
     * @return the tenta
     */
    public String getTenta() {
        return tenta;
    }

    /**
     * @param tenta the tenta to set
     */
    public void setTenta(String tenta) {
        this.tenta = tenta;
    }

    /**
     * @return the slta
     */
    public String getSlta() {
        return slta;
    }

    /**
     * @param slta the slta to set
     */
    public void setSlta(String slta) {
        this.slta = slta;
    }

    /**
     * @return the giata
     */
    public String getGiata() {
        return giata;
    }

    /**
     * @param giata the giata to set
     */
    public void setGiata(String giata) {
        this.giata = giata;
    }

    /**
     * @return the nhacc
     */
    public String getNhacc() {
        return nhacc;
    }

    /**
     * @param nhacc the nhacc to set
     */
    public void setNhacc(String nhacc) {
        this.nhacc = nhacc;
    }
}
