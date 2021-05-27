package Otopark.Park;

public class Kat_Bolum {
    private String kat,bolum;
    private int dolu_mu,id_Kat_Bolum;

    public void setDolu(){
        this.setDolu_mu(1);
    }
    public void setBos(){
        this.setDolu_mu(0);
    }

    public String getKat() {
        return kat;
    }

    public void setKat(String kat) {
        this.kat = kat;
    }

    public int getId_Kat_Bolum() {
        return id_Kat_Bolum;
    }

    public void setId_Kat_Bolum(int id_Kat_Bolum) {
        this.id_Kat_Bolum = id_Kat_Bolum;
    }

    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
    }

    public int getDolu_mu() {
        return dolu_mu;
    }

    public void setDolu_mu(int dolu_mu) {
        this.dolu_mu = dolu_mu;}

}
