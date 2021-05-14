package Otopark.Arac;

import Otopark.Factory.AracFactory;

public class Otomobil extends AracAbstract {

    public Otomobil(String model, String renk, String plaka) {
        super(model, renk, plaka);
    }

    public Otomobil(AracFactory factory) {
        super(factory);
    }


    @Override
    public int InsertDB() {
        return this.factory.AracEkle(this);

    }

    public Otomobil() {

    }
}
