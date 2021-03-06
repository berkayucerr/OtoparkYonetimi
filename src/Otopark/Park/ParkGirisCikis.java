package Otopark.Park;

import Otopark.Arac.Arac;
import Otopark.Arac.AracAbstract;
import util.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class ParkGirisCikis extends DBConnection implements ParkArayuz{
    private PreparedStatement pst;
    private ResultSet rs;
    private int car_id;
    private Kat_Bolum kat_bolum;
    private LinkedList<Kat_Bolum> KatBolumListe=new LinkedList<Kat_Bolum>();
    private Invoker invoker=new Invoker();

    public LinkedList<Kat_Bolum> KatBolumKontrol(){
        try {

            //Kullanıcı kontrolü

            //Kat bolum Kontrolü
            pst = this.connect().prepareStatement("select * from Kat_Bolum");
            rs = pst.executeQuery();

            while (rs.next()) {
                kat_bolum = new Kat_Bolum();
                kat_bolum.setId_Kat_Bolum(rs.getInt("id_kat_bolum"));
                kat_bolum.setKat(rs.getString("kat"));
                kat_bolum.setBolum(rs.getString("bolum"));
                kat_bolum.setDolu_mu(rs.getInt("dolu_mu"));
                KatBolumListe.add(kat_bolum);
            }


        }catch (Exception e){
            e.getMessage();
        }

        return KatBolumListe;
    }
    @Override
    public void ParkEt(String numara,String sifre,Park park) {

        try {
            pst = this.connect().prepareStatement("select * from kullanici where numara=? and password=?");
            pst.setString(1, numara);
            pst.setString(2, sifre);
            rs = pst.executeQuery();
            while (rs.next()) {
                car_id = (rs.getInt("id_car"));
            }
            //Yerleştirme
            pst = this.connect().prepareStatement("insert into Park (giris_saati,id_car,id_kat_bolum) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,park.getGirisSaati());
            pst.setInt(2,car_id);
            System.out.println("insert car_id:"+car_id);
            pst.setInt(3,park.getKat_bolum().getId_Kat_Bolum());
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
            invoker.setCommand(new Doldur(park.getKat_bolum()));
            invoker.Execute();
            KatBolumUpdate(park.getKat_bolum());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void KatBolumUpdate(Kat_Bolum kat_bolum){
        try {
            pst=this.connect().prepareStatement("update Kat_Bolum set dolu_mu=? where id_kat_bolum=?");
            pst.setInt(1,kat_bolum.getDolu_mu());
            pst.setInt(2,kat_bolum.getId_Kat_Bolum());
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
            Kat_Bolum KaBo=Kat_Bolum_Bul(temp_kat_bolum);
            pst=this.connect().prepareStatement("delete from Park where id_car=? ");
            pst.setInt(1,car_id);
            pst.executeUpdate();

            invoker.setCommand(new Bosalt(KaBo));
            invoker.Execute();
            KatBolumUpdate(KaBo);

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
                AracAbstract arac;
                Arac a=new Arac();
                arac=a.AracOlustur(rs.getString("arac_sekli"),rs.getInt("id_arac"),rs.getString("model"),rs.getString("renk"),rs.getString("plaka"));
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
                arac=aracislemleri.AracOlustur(rss.getString("arac_sekli"),rss.getInt("id_arac"),rss.getString("model"),rss.getString("renk"),rss.getString("plaka"));

            }
    }catch (SQLException e){
            e.getMessage();
        }
        return arac;
    }

}


