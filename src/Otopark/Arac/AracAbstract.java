package Otopark.Arac;

import Otopark.Factory.AracFactory;
import util.DBConnection;

public abstract class AracAbstract extends DBConnection {
    protected int id;
    protected String model,renk,Plaka;
    protected AracFactory factory;
    public int getId() {
        return id;
    }
    AracAbstract(){

    }

    public AracAbstract(AracFactory factory) {
        this.factory = factory;
    }

    public AracAbstract(String model, String renk, String plaka) {
        this.model = model;
        this.renk = renk;
        Plaka = plaka;
    }

    public AracAbstract(int id, String model, String renk, String plaka, AracFactory factory) {
        this.id = id;
        this.model = model;
        this.renk = renk;
        Plaka = plaka;
        this.factory=factory;
    }

    public abstract int InsertDB();


    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    public String getPlaka() {
        return Plaka;
    }

    public void setPlaka(String plaka) {
        Plaka = plaka;
    }
}
