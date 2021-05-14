package Otopark.Arac;

import Otopark.Factory.AracFactory;

public class Kamyon extends AracAbstract{

    public Kamyon(String model, String renk, String plaka) {
        super(model, renk, plaka);
    }

    public Kamyon(int id, String model, String renk, String plaka, AracFactory factory) {
        super(id, model, renk, plaka, factory);
    }

    @Override
    public int InsertDB() {
        return this.factory.AracEkle(this);
    }

    public Kamyon(AracFactory factory) {
        super(factory);
    }

    public Kamyon() {
    }
}
