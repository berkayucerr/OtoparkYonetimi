package Otopark.Arac;

import Otopark.Factory.AracFactory;
import util.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Arac extends DBConnection implements AracFactory {
    PreparedStatement pst;
    protected int donus = 0;

    @Override
    public int AracEkle(AracAbstract arac) {
        switch (arac.getClass().getName()) {
            case "Otopark.Arac.Otomobil":
                donus = SQLInsert(arac, "Otomobil");
                break;
            case "Otopark.Arac.Kamyon":
                donus = SQLInsert(arac, "Kamyon");
                break;
            case "Otopark.Arac.Motosiklet":
                donus = SQLInsert(arac, "Motosiklet");
                break;
            case "Otopark.Arac.Atv":
                donus = SQLInsert(arac, "Atv");
                break;
            default:
                System.out.println("yanlış giriş");
                break;
        }
        return donus;
    }

    private int kontrol = 0;

    @Override
    public AracAbstract AracOlustur(String arac_tipi, int id, String model, String renk, String plaka) {
        AracAbstract arac = null;
        switch (arac_tipi) {
            case "Otomobil":
                kontrol = 1;
                arac = new Otomobil(new Arac());
                break;
            case "Kamyon":
                kontrol = 1;
                arac = new Kamyon(new Arac());
                break;
            case "Motosiklet":
                kontrol = 1;
                arac = new Motosiklet(new Arac());
                break;
            case "Atv":
                kontrol = 1;
                arac = new Atv(new Arac());
                break;
            default:
                System.out.println("yanlış giriş");
                break;
        }
        if (kontrol == 1) {
            arac.setId(id);
            arac.setRenk(renk);
            arac.setModel(model);
            arac.setPlaka(plaka);
        }
        return arac;
    }

    private int SQLInsert(AracAbstract arac, String aracsekli) {
        ResultSet rs;
        int car_id = 0;
        try {
            pst = this.connect().prepareStatement("insert into Arac (model,renk,plaka,arac_sekli) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, arac.getModel());
            pst.setString(2, arac.getRenk());
            pst.setString(3, arac.getPlaka());
            pst.setString(4, aracsekli);
            pst.executeUpdate();

            pst = this.connect().prepareStatement("select * from Arac where model=? and plaka=?", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, arac.getModel());
            pst.setString(2, arac.getPlaka());
            rs = pst.executeQuery();
            while (rs.next()) {
                car_id = rs.getInt("id_arac");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return car_id;
    }
}
