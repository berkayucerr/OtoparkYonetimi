package Otopark.IndırımKontrol;


import Otopark.Arac.Arac;
import Otopark.Arac.AracAbstract;
import Otopark.Insan.Insan;
import Otopark.KayitGiris.KayitGirisKontrol;
import Otopark.Park.Indirim;
import Otopark.Park.Kat_Bolum;
import Otopark.Park.Park;
import Otopark.Park.ParkGirisCikis;

import java.time.LocalTime;
import java.util.Scanner;
public class ParkIslemleri {
    private Park p=new Park();
    private Kat_Bolum k=new Kat_Bolum();
    private ParkGirisCikis park=new ParkGirisCikis();
    private KayitGirisKontrol kayitGirisKontrol=new KayitGirisKontrol();
    private Scanner s=new Scanner(System.in);
    private Arac aracislemleri=new Arac();
    private Insan insan;
    private AracAbstract arac;
    private String[] geciciSaatParkSayisi;
    private int saat=0,dakika=0,menuSecenek=0;
    public ParkIslemleri(){

    }

    public String IndirimKontrol(String numara,String sifre){
        Double sure=0.0;
        String saatParkSayisi=park.ParkCikis(numara,sifre);
        System.out.println(saatParkSayisi);
        geciciSaatParkSayisi= saatParkSayisi.split("/");
        for (int i = 0; i <geciciSaatParkSayisi.length ; i++) {
            System.out.println(geciciSaatParkSayisi[i]);
        }
        int parkSayisi=Integer.parseInt(geciciSaatParkSayisi[1]);

        if(Integer.parseInt(geciciSaatParkSayisi[1])>9){
            Indirim i=new Indirim();
            i.indirimYap(numara,sifre);
            System.out.println("bedava hak kazandınız");
        }else{
            geciciSaatParkSayisi=geciciSaatParkSayisi[0].split(":");
            saat=LocalTime.now().getHour()-Integer.parseInt(geciciSaatParkSayisi[0]);
            dakika=LocalTime.now().getMinute()-Integer.parseInt(geciciSaatParkSayisi[1]);
            if (dakika < 0)
                dakika = -dakika;
            if (saat < 0)
                saat = -saat;
            dakika=(saat*60)+dakika;
            if(dakika<60){
                sure=7.0;
            }else {
                sure=Double.parseDouble(String.valueOf(dakika))/60;
                sure=((sure-1)*2);
                sure=sure+7;
            }
        }
        return String.valueOf(sure)+"-"+String.valueOf(parkSayisi);
    }

}
