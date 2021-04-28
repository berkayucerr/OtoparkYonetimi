package Otopark.Park;

import Otopark.Arac.AracAbstract;

public abstract class ParkAbstract {
    protected int id_park,arac_id;
    protected String girisSaati;
    protected Kat_Bolum kat_bolum;
    protected AracAbstract arac;

    public ParkAbstract() {
    }

    public ParkAbstract(int id_park, int arac_id, String girisSaati, Kat_Bolum kat_bolum) {
        this.id_park = id_park;
        this.arac_id = arac_id;
        this.girisSaati = girisSaati;
        this.kat_bolum = kat_bolum;
    }

    public AracAbstract getArac() {
        return arac;
    }

    public void setArac(AracAbstract arac) {
        this.arac = arac;
    }

    public ParkAbstract(int arac_id, String girisSaati, Kat_Bolum kat_bolum) {
        this.arac_id = arac_id;
        this.girisSaati = girisSaati;
        this.kat_bolum = kat_bolum;
    }

    public Kat_Bolum getKat_bolum() {
        if (kat_bolum==null){
            kat_bolum=new Kat_Bolum();
        }
        return kat_bolum;
    }

    public void setKat_bolum(Kat_Bolum kat_bolum) {
        this.kat_bolum = kat_bolum;
    }

    public int getId_park() {
        return id_park;
    }

    public int getArac_id() {
        return arac_id;
    }

    public void setArac_id(int arac_id) {
        this.arac_id = arac_id;
    }

    public void setId_park(int id_park) {
        this.id_park = id_park;
    }

    public String getGirisSaati() {
        return girisSaati;
    }

    public void setGirisSaati(String girisSaati) {
        this.girisSaati = girisSaati;
    }
}
