package Otopark.Arac;

import Otopark.Factory.AracFactory;

public class Motosiklet extends AracAbstract{
    public Motosiklet(String model, String renk, String plaka) {
        super(model, renk, plaka);
    }

    public Motosiklet(AracFactory factory) {
        super(factory);
    }

    public Motosiklet(int id, String model, String renk, String plaka, AracFactory factory) {
        super(id, model, renk, plaka, factory);
    }

    @Override
    public int InsertDB() {
        return this.factory.AracEkle(this);
    }

    public Motosiklet() {
    }
}
