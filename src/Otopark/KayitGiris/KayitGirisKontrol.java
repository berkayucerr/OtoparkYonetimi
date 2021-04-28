package Otopark.KayitGiris;

import Otopark.Insan.Insan;
import util.DBConnection;

import java.sql.*;

public class KayitGirisKontrol extends DBConnection implements KayitGiris {

    Statement st;

    @Override
    public void kayit(Insan insan) {
            try {
                PreparedStatement pst = connect().prepareStatement("insert into kullanici (isim_soyisim,numara,password) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, insan.getIsimSoyisim());
                pst.setString(2, insan.getNumara());
                pst.setString(3, insan.getSifre());
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
                    temp.setId(rs.getInt("id_kullanici"));
                    temp.setIsimSoyisim(rs.getString("isim_soyisim"));
                    temp.setNumara(rs.getString("numara"));
                    temp.setYetki(rs.getString("yetki"));
                    temp.setSifre(rs.getString("password"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            temp.toString();
        return temp;
        }
    }

