package Otopark.Park;

import Otopark.IndırımKontrol.ParkIslemleri;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalTime;

public class IndirimTest {
    ParkIslemleri parkIslemleri;
    ParkGirisCikis parkGirisCikis;
    Park park;
    String saatFiyat;
    double fiyat;
    int parkSayisi;

    @Before
    public void hazirlama() {
        parkGirisCikis = new ParkGirisCikis();
        parkIslemleri = new ParkIslemleri();
        park = new Park();
        park.setArac(parkGirisCikis.Arac_Bul(17));
        park.setKat_bolum(parkGirisCikis.Kat_Bolum_Bul(3));

    }

    @Test
    public void test1() {
        park.setGirisSaati(String.valueOf((LocalTime.now().getHour())+ ":" + LocalTime.now().getMinute()));
        parkGirisCikis.ParkEt("123", "123", park);
        saatFiyat = parkIslemleri.IndirimKontrol("123", "123");
        String [] a=saatFiyat.split("-");
        fiyat=Double.parseDouble(a[0]);
        parkSayisi=Integer.parseInt(a[1]);
        Assertions.assertEquals(fiyat, 7);
        Assertions.assertEquals(parkSayisi,1);
    }
    @Test
    public void test2() {
        park.setGirisSaati(String.valueOf((LocalTime.now().getHour()-1)+ ":" + LocalTime.now().getMinute()));
        parkGirisCikis.ParkEt("123", "123", park);
        saatFiyat = parkIslemleri.IndirimKontrol("123", "123");
        String [] a=saatFiyat.split("-");
        fiyat=Double.parseDouble(a[0]);
        parkSayisi=Integer.parseInt(a[1]);
        Assertions.assertEquals(fiyat, 7);
        Assertions.assertEquals(parkSayisi,2);
    }
    @Test
    public void test3() {
        park.setGirisSaati(String.valueOf((LocalTime.now().getHour()-2)+ ":" + LocalTime.now().getMinute()));
        parkGirisCikis.ParkEt("123", "123", park);
        saatFiyat = parkIslemleri.IndirimKontrol("123", "123");
        String [] a=saatFiyat.split("-");
        fiyat=Double.parseDouble(a[0]);
        parkSayisi=Integer.parseInt(a[1]);
        Assertions.assertEquals(fiyat, 9);
        Assertions.assertEquals(parkSayisi,3);
    }
    @Test
    public void test4() {
        park.setGirisSaati(String.valueOf((LocalTime.now().getHour()-3)+ ":" + LocalTime.now().getMinute()));
        parkGirisCikis.ParkEt("123", "123", park);
        saatFiyat = parkIslemleri.IndirimKontrol("123", "123");
        String [] a=saatFiyat.split("-");
        fiyat=Double.parseDouble(a[0]);
        parkSayisi=Integer.parseInt(a[1]);
        Assertions.assertEquals(fiyat, 11);
        Assertions.assertEquals(parkSayisi,4);
    }
    @Test
    public void test5() {
        park.setGirisSaati(String.valueOf((LocalTime.now().getHour()-4)+ ":" + LocalTime.now().getMinute()));
        parkGirisCikis.ParkEt("123", "123", park);
        saatFiyat = parkIslemleri.IndirimKontrol("123", "123");
        String [] a=saatFiyat.split("-");
        fiyat=Double.parseDouble(a[0]);
        parkSayisi=Integer.parseInt(a[1]);
        Assertions.assertEquals(fiyat, 13);
        Assertions.assertEquals(parkSayisi,5);
    }
    @Test
    public void test6() {
        park.setGirisSaati(String.valueOf((LocalTime.now().getHour()-5)+ ":" + LocalTime.now().getMinute()));
        parkGirisCikis.ParkEt("123", "123", park);
        saatFiyat = parkIslemleri.IndirimKontrol("123", "123");
        String [] a=saatFiyat.split("-");
        fiyat=Double.parseDouble(a[0]);
        parkSayisi=Integer.parseInt(a[1]);
        Assertions.assertEquals(fiyat, 15);
        Assertions.assertEquals(parkSayisi,6);
    }
    @Test
    public void test7() {
        park.setGirisSaati(String.valueOf((LocalTime.now().getHour()-6)+ ":" + LocalTime.now().getMinute()));
        parkGirisCikis.ParkEt("123", "123", park);
        saatFiyat = parkIslemleri.IndirimKontrol("123", "123");
        String [] a=saatFiyat.split("-");
        fiyat=Double.parseDouble(a[0]);
        parkSayisi=Integer.parseInt(a[1]);
        Assertions.assertEquals(fiyat, 17);
        Assertions.assertEquals(parkSayisi,7);
    }
    @Test
    public void test8() {
        park.setGirisSaati(String.valueOf((LocalTime.now().getHour()-7)+ ":" + LocalTime.now().getMinute()));
        parkGirisCikis.ParkEt("123", "123", park);
        saatFiyat = parkIslemleri.IndirimKontrol("123", "123");
        String [] a=saatFiyat.split("-");
        fiyat=Double.parseDouble(a[0]);
        parkSayisi=Integer.parseInt(a[1]);
        Assertions.assertEquals(fiyat, 19);
        Assertions.assertEquals(parkSayisi,8);
    }
    @Test
    public void test9() {
        park.setGirisSaati(String.valueOf((LocalTime.now().getHour()-8)+ ":" + LocalTime.now().getMinute()));
        parkGirisCikis.ParkEt("123", "123", park);
        saatFiyat = parkIslemleri.IndirimKontrol("123", "123");
        String [] a=saatFiyat.split("-");
        fiyat=Double.parseDouble(a[0]);
        parkSayisi=Integer.parseInt(a[1]);
        Assertions.assertEquals(fiyat, 21);
        Assertions.assertEquals(parkSayisi,9);
    }
    @Test
    public void testx() {
        park.setGirisSaati(String.valueOf((LocalTime.now().getHour()-9)+ ":" + LocalTime.now().getMinute()));
        parkGirisCikis.ParkEt("123", "123", park);
        saatFiyat = parkIslemleri.IndirimKontrol("123", "123");
        String [] a=saatFiyat.split("-");
        fiyat=Double.parseDouble(a[0]);
        parkSayisi=Integer.parseInt(a[1]);
        Assertions.assertEquals(fiyat, 0);
        Assertions.assertEquals(parkSayisi,10);
    }

}
