package Otopark.Arac;

import Otopark.IndırımKontrol.ParkIslemleri;
import Otopark.Insan.Insan;
import Otopark.KayitGiris.KayitGirisKontrol;
import Otopark.Park.Kat_Bolum;
import Otopark.Park.Park;
import Otopark.Park.ParkGirisCikis;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

public class AracGirisTest {
    ParkIslemleri pq;
    Arac a;
    AracAbstract arac;
    ParkGirisCikis park;
    Park p;
    Insan insan;
    KayitGirisKontrol k;
    private int secim=3;
    AracAbstract aracim;
    Kat_Bolum kat_bolum;

    @Before
    public void Hazirlama(){
         a=new Arac();
        insan=new Insan();;
         park=new ParkGirisCikis();
         p=new Park();
         k=new KayitGirisKontrol();
        pq=new ParkIslemleri();
        insan.setIsimSoyisim("asdw");
        insan.setNumara("111");
        insan.setSifre("123");
        arac=a.AracOlustur("Otomobil",0,"model","renk","plaka");
        arac.setId(arac.InsertDB());
        insan.setArac(arac);
        k.kayit(insan);
        insan=k.Giris(insan.getNumara(),insan.getSifre());
        p.setGirisSaati(String.valueOf(LocalTime.now().getHour()) + ":" + String.valueOf(LocalTime.now().getMinute()));
        park.ParkEt(insan.getNumara(), insan.getSifre(), p, secim);
        kat_bolum=park.Kat_Bolum_Bul(secim);
        aracim = park.Arac_Bul(arac.getId());

    }

    @Test
    public void giris(){

        Assert.assertEquals(aracim.getId(),arac.getId());
        Assert.assertEquals(aracim.getModel(),arac.getModel());
        Assert.assertEquals(aracim.getPlaka(),arac.getPlaka());
        Assert.assertEquals(aracim.getRenk(),arac.getRenk());
        Assert.assertEquals(kat_bolum.getDolu_mu(),1);


    }

}
