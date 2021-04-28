package Otopark.Park;

import util.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Indirim extends DBConnection {
    public void indirimYap(String numara,String sifre){
        PreparedStatement pst=null;
        try {
            pst=this.connect().prepareStatement("update kullanici set park_sayisi=? where numara=? and password=?");
            pst.setInt(1,0);
            pst.setString(2,numara);
            pst.setString(3,sifre);
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
