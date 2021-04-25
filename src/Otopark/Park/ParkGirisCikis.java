package Otopark.Park;

import Otopark.Arac.Arac;
import Otopark.Arac.AracAbstract;
import Otopark.Insan.Insan;
import util.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ParkGirisCikis extends DBConnection implements ParkArayuz{
    PreparedStatement pst;
    ResultSet rs;
    int car_id;
    Kat_Bolum kat_bolum;
    LinkedList<Kat_Bolum> KatBolumListe=new LinkedList<Kat_Bolum>();
    @Override
    public void ParkEt(String numara,String sifre,Park park) {

        try {

            //Kullanıcı kontrolü
            pst=this.connect().prepareStatement("select * from kullanici where numara=? and password=?");
            pst.setString(1,numara);
            pst.setString(2,sifre);
            rs = pst.executeQuery();
            while (rs.next()) {
                car_id=(rs.getInt("id_car"));
            }

            System.out.println(car_id);
            //Kat bolum Kontrolü
            pst=this.connect().prepareStatement("select * from Kat_Bolum");
            rs = pst.executeQuery();

            while (rs.next()) {
                kat_bolum=new Kat_Bolum();
                kat_bolum.setId_Kat_Bolum(rs.getInt("id_kat_bolum"));
                kat_bolum.setKat(rs.getString("kat"));
                kat_bolum.setBolum(rs.getString("bolum"));
                kat_bolum.setDolu_mu(rs.getInt("dolu_mu"));
                KatBolumListe.add(kat_bolum);
            }

            Scanner s=new Scanner(System.in);
            int secim=0;
            System.out.println("Kontrol noktası");
            secim=s.nextInt();
            kat_bolum=KatBolumListe.get(secim);
            //Yerleştirme
            pst = this.connect().prepareStatement("insert into Park (giris_saati,id_car,id_kat_bolum) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,park.getGirisSaati());
            pst.setInt(2,car_id);
            pst.setInt(3,kat_bolum.getId_Kat_Bolum());
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void ParkCikis() {

    }

    @Override
    public List<Arac> Araclar() {
        return null;
    }

    @Override
    public List<ParkAbstract> Parklar() {
        return null;
    }
}

