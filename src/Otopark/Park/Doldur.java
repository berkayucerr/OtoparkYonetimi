package Otopark.Park;

public class Doldur implements DoldurBosalt
{
    private Kat_Bolum kat_bolum;

    public Doldur(Kat_Bolum kat_bolum) {
        this.kat_bolum = kat_bolum;
    }

    @Override
    public void Execute() {
        this.kat_bolum.setDolu();
    }
}
