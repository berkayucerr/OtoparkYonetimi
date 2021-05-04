package Otopark.Park;

import Otopark.Arac.*;
import util.DBConnection;

import java.sql.*;
import java.util.LinkedList;
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
            System.out.println("car_id="+car_id);
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
            System.out.println("Bir Kat Bölüm Seçiniz");
            int gecici=0;
            do {
                for (int i = 0; i <KatBolumListe.size() ; i++) {
                    System.out.println(KatBolumListe.get(i).getKat());
                    System.out.println(KatBolumListe.get(i).getBolum());
                    if(KatBolumListe.get(i).getDolu_mu()==1){
                        System.out.println("Burası dolu seçemezsiniz");
                    }

                }
                secim=s.nextInt();

            }while (KatBolumListe.get(secim).getDolu_mu()==1);

            kat_bolum=KatBolumListe.get(secim);
            //Yerleştirme
            pst = this.connect().prepareStatement("insert into Park (giris_saati,id_car,id_kat_bolum) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,park.getGirisSaati());
            pst.setInt(2,car_id);
            System.out.println("insert car_id:"+car_id);
            pst.setInt(3,kat_bolum.getId_Kat_Bolum());
            System.out.println(kat_bolum.getId_Kat_Bolum());
            pst.executeUpdate();

            int temp_park_sayisi=0;
            pst=this.connect().prepareStatement("select * from kullanici where numara=? and password=?");
            pst.setString(1,numara);
            pst.setString(2,sifre);
            rs=pst.executeQuery();
            while(rs.next()){
                temp_park_sayisi=rs.getInt("park_sayisi");
            }
            pst=this.connect().prepareStatement("update kullanici set park_sayisi=? where numara=? and password=?");
            pst.setInt(1,temp_park_sayisi+1);
            pst.setString(2,numara);
            pst.setString(3,sifre);
            pst.executeUpdate();

            pst=this.connect().prepareStatement("update Kat_Bolum set dolu_mu=? where id_kat_bolum=?");
            pst.setInt(1,1);
            pst.setInt(2,secim);
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public String ParkCikis(String numara,String sifre) {
        System.out.println("-Kontrol noktası giris saati-");
        String girisSaati = null,park_sayisi=null;

        try {
            pst=this.connect().prepareStatement("select * from kullanici where numara=? and password=?");
            pst.setString(1,numara);
            pst.setString(2,sifre);
            rs=pst.executeQuery();
            while(rs.next()){
                car_id=rs.getInt("id_car");
                park_sayisi=rs.getString("park_sayisi");
            }
            pst=this.connect().prepareStatement("select * from Park where id_car=? ");
            pst.setInt(1,car_id);
            rs=pst.executeQuery();
            int temp_kat_bolum = 0;
            while(rs.next()){
                temp_kat_bolum=rs.getInt("id_kat_bolum");
                girisSaati=rs.getString("giris_saati");
            }
            pst=this.connect().prepareStatement("delete from Park where id_car=? ");
            pst.setInt(1,car_id);
            pst.executeUpdate();

            pst=this.connect().prepareStatement("update Kat_Bolum set dolu_mu=? where id_kat_bolum=?");
            pst.setInt(1,0);
            pst.setInt(2,temp_kat_bolum);
            pst.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return (girisSaati+"/"+park_sayisi);
    }

    @Override
    public LinkedList<AracAbstract> Araclar() {
        LinkedList<AracAbstract> liste=new LinkedList<AracAbstract>();
        try {
            pst=this.connect().prepareStatement("select * from Arac");
            rs=pst.executeQuery();
            while (rs.next()){
                AracAbstract arac = null;
                switch (rs.getString("arac_sekli")) {
                    case "Otomobil":
                        arac=new Otomobil();
                        break;
                    case "Kamyon":
                        arac=new Kamyon();
                        break;
                    case "Motosiklet":
                        arac=new Motosiklet();
                        break;
                    case "Atv":
                        arac=new Atv();
                        break;
                    default:
                        System.out.println("yanlış giriş");
                        break;
                }
                arac.setId(rs.getInt("id_arac"));
                arac.setModel(rs.getString("model"));
                arac.setRenk(rs.getString("renk"));
                arac.setPlaka(rs.getString("plaka"));
                liste.add(arac);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return liste;
    }

    @Override
    public LinkedList<Park> Parklar() {
        LinkedList<Park> liste=new LinkedList<Park>();
        Park park;
        try {
            pst=this.connect().prepareStatement("select * from Park");
            rs=pst.executeQuery();
            while(rs.next()){
                park=new Park();
                park.setId_park(rs.getInt("id_park"));
                park.setGirisSaati(rs.getString("giris_saati"));
                park.setArac(Arac_Bul(rs.getInt("id_car")));
                park.setKat_bolum(Kat_Bolum_Bul(rs.getInt("id_kat_bolum")));
                liste.add(park);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return liste;
    }
    public Kat_Bolum Kat_Bolum_Bul(int id_kat_bolum){
        ResultSet rss;
        PreparedStatement pstt;
        Kat_Bolum k=null;
        try {
            pstt=this.connect().prepareStatement("select * from Kat_Bolum where id_kat_bolum=?");
            pstt.setInt(1,id_kat_bolum);
            rss=pstt.executeQuery();
            while(rss.next()){
                k=new Kat_Bolum();
                k.setId_Kat_Bolum(rss.getInt("id_kat_bolum"));
                k.setKat(rss.getString("kat"));
                k.setBolum(rss.getString("bolum"));
                k.setDolu_mu(rss.getInt("dolu_mu"));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return k;
    }
    public AracAbstract Arac_Bul(int id_carx){
        ResultSet rss;
        PreparedStatement pstt;
        Arac aracislemleri=new Arac();
        AracAbstract arac=null;
        try {
            pstt=this.connect().prepareStatement("select * from Arac where id_arac=?");
            pstt.setInt(1,id_carx);
            rss=pstt.executeQuery();
            while(rss.next()){
                arac=aracislemleri.AracOlustur(rss.getString("arac_sekli"));
                arac.setId(rss.getInt("id_arac"));
                arac.setModel(rss.getString("model"));
                arac.setRenk(rss.getString("renk"));
                arac.setPlaka(rss.getString("plaka"));
            }
    }catch (SQLException e){
            e.getMessage();
        }
        return arac;
    }
}


