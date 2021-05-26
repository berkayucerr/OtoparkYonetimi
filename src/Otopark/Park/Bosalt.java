package Otopark.Park;

public class Bosalt implements DoldurBosalt
{
    Kat_Bolum kat_bolum;

    public Bosalt(Kat_Bolum kat_bolum) {
        this.kat_bolum = kat_bolum;
    }

    @Override
    public void Execute() {
        this.kat_bolum.Empty();
    }
}
