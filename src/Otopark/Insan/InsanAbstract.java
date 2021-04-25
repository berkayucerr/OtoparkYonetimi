package Otopark.Insan;

import Otopark.Arac.Arac;
import util.DBConnection;

public abstract class InsanAbstract extends DBConnection {
    protected int id;
    protected String isimSoyisim,numara,sifre,yetki;
    protected Arac arac;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "InsanAbstract{" +
                "id=" + id +
                ", isimSoyisim='" + isimSoyisim + '\'' +
                ", numara='" + numara + '\'' +
                ", sifre='" + sifre + '\'' +
                ", yetki='" + yetki + '\'' +
                ", arac=" + arac +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsimSoyisim() {
        return isimSoyisim;
    }

    public void setIsimSoyisim(String isimSoyisim) {
        this.isimSoyisim = isimSoyisim;
    }

    public String getNumara() {
        return numara;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getYetki() {
        return yetki;
    }

    public void setYetki(String yetki) {
        this.yetki = yetki;
    }

    public InsanAbstract() {
    }

    public Arac getArac() {

        return arac;
    }

    public void setArac(Arac arac) {
        this.arac = arac;
    }
}
