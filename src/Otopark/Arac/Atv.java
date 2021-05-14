package Otopark.Arac;

import Otopark.Factory.AracFactory;

public class Atv extends AracAbstract{

    public Atv(String model, String renk, String plaka) {
        super(model, renk, plaka);
    }

    public Atv(AracFactory a) {
        super(a);
    }


    public Atv(int id, String model, String renk, String plaka, AracFactory factory) {
        super(id, model, renk, plaka, factory);
    }

    @Override
    public int InsertDB() {
        return this.factory.AracEkle(this);
    }
}
