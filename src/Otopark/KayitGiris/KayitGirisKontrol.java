package Otopark.KayitGiris;

import Otopark.Arac.*;
import Otopark.Insan.Insan;
import Otopark.Park.ParkGirisCikis;
import util.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KayitGirisKontrol extends DBConnection implements KayitGiris {

    private Statement st;


    @Override
    public void kayit(Insan insan) {
            try {
                PreparedStatement pst = connect().prepareStatement("insert into kullanici (isim_soyisim,numara,password,id_car) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, insan.getIsimSoyisim());
                pst.setString(2, insan.getNumara());
                pst.setString(3, insan.getSifre());
                pst.setInt(4,insan.getArac().getId());
                pst.executeUpdate();

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

    }
    Insan temp ;
    @Override
    public Insan Giris(String numara,String sifre) {

        PreparedStatement pst;
        ResultSet rs;
            try {
                pst = this.connect().prepareStatement("select * from kullanici where numara=? and password=?");
                pst.setString(1, numara);
                pst.setString(2, sifre);
                rs = pst.executeQuery();
                while (rs.next()) {
                    temp = new Insan();
                    AracAbstract arac;
                    int id_car=rs.getInt("id_car");
                    ParkGirisCikis p=new ParkGirisCikis();
                    temp.setArac(p.Arac_Bul(id_car));
                    temp.setId(rs.getInt("id_kullanici"));
                    temp.setIsimSoyisim(rs.getString("isim_soyisim"));
                    temp.setNumara(rs.getString("numara"));
                    temp.setYetki(rs.getString("yetki"));
                    temp.setSifre(rs.getString("password"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return temp;
        }
    public Boolean Kullanici_Bul(String numara){
        ResultSet rsq;
        PreparedStatement psq;
        boolean deger=false;
        try {
            psq=this.connect().prepareStatement("select count(*) as count from kullanici where numara=?");
            psq.setString(1,numara);
            rsq=psq.executeQuery();
            while (rsq.next()) {
                int count = rsq.getInt("count");
                if (count >= 1) {
                    deger=true;
                }
            }
        }catch (SQLException e){
            e.getMessage();
        }
        return deger;
    }
    }

