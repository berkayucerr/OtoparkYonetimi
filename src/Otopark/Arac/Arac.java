package Otopark.Arac;

import Otopark.Factory.AracFactory;
import util.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Arac extends DBConnection implements AracFactory {
    PreparedStatement pst;
    @Override
    public void AracEkle(AracAbstract arac) {
        switch (arac.getClass().getName()) {
            case "Otopark.Arac.Otomobil":
                SQLInsert(arac,"Otomobil");
                break;
            case "Otopark.Arac.Kamyon":
                SQLInsert(arac,"Kamyon");
                break;
            case "Otopark.Arac.Motosiklet":
                SQLInsert(arac,"Motosiklet");
                break;
            case "Otopark.Arac.Atv":
                SQLInsert(arac,"Arac.Atv");
                break;
            default:
                System.out.println("yanlış giriş");
                break;
        }
    }
    public void SQLInsert(AracAbstract arac,String aracsekli){
        try {
            pst = this.connect().prepareStatement("insert into Arac (model,renk,plaka,arac_sekli) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,arac.getModel());
            pst.setString(2,arac.getRenk());
            pst.setString(3,arac.getPlaka());
            pst.setString(4,aracsekli);
            pst.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
