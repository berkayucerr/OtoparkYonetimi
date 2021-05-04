package Otopark.KayitGiris;

import Otopark.Arac.AracAbstract;
import Otopark.Arac.Otomobil;
import Otopark.Insan.Insan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KayitGirisKontrolTest {
    KayitGirisKontrol k;
    Insan i,i2;

    @BeforeEach
    public void Hazirlama(){
        k=new KayitGirisKontrol();
        i=new Insan();
        i2=new Insan();
        AracAbstract a=new Otomobil();
        a.setId(17);
        i.setArac(a);
        i.setIsimSoyisim("asd");
        i.setNumara("123");
        i.setSifre("1234");
        i.setYetki("0");
    }

    @Test
    public void KayitTest(){
        k.kayit(i);
        i2=k.Giris(i.getNumara(),i.getSifre());
        Assertions.assertEquals(i2.getNumara(),i.getNumara());
        Assertions.assertEquals(i2.getArac().getId(),i.getArac().getId());
        Assertions.assertEquals(i2.getIsimSoyisim(),i.getIsimSoyisim());
        Assertions.assertEquals(i2.getSifre(),i.getSifre());
        Assertions.assertEquals(i2.getYetki(),i.getYetki());
    }

    @Test
    public void GirisTest(){
        i2=k.Giris("123","123");
        Assertions.assertEquals(i2.getNumara(),"123");
        Assertions.assertEquals(i2.getYetki(),"1");
        Assertions.assertEquals(i2.getIsimSoyisim(),"3");
        Assertions.assertEquals(i2.getSifre(),"123");
        Assertions.assertEquals(i2.getArac().getId(),17);
        Assertions.assertEquals(i2.getId(),13);
    }





}