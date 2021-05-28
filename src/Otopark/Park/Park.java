package Otopark.Park;


public class Park extends ParkAbstract {
    public Park(int id_park, int arac_id, String girisSaati, Kat_Bolum kat_bolum) {
        super(id_park, arac_id, girisSaati, kat_bolum);
    }

    public Park(int arac_id, String girisSaati, Kat_Bolum kat_bolum) {
        super(arac_id, girisSaati, kat_bolum);
    }

    public Park() {
    }
}
