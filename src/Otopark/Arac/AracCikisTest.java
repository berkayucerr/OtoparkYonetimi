package Otopark.Arac;

import Otopark.Insan.Insan;
import Otopark.Park.Park;
import Otopark.Park.ParkGirisCikis;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

public class AracCikisTest {
    Insan insan;
    ParkGirisCikis park;
    int once,sonra;
    LinkedList<Park> parkListesi;
    String saat;
    @Before
    public void Hazirlama(){
        park=new ParkGirisCikis();
        insan=new Insan();
        insan.setNumara("111");
        insan.setSifre("123");
        parkListesi=park.Parklar();
        once=parkListesi.size();
        park.ParkCikis(insan.getNumara(),insan.getSifre());
        parkListesi=park.Parklar();
        sonra=parkListesi.size();

    }
    @Test
    public void Cikis(){
        Assert.assertEquals(once,sonra+1);
    }
}
