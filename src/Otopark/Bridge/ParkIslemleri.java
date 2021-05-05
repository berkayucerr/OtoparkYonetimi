package Otopark.Bridge;


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
    Park p=new Park();
    Kat_Bolum k=new Kat_Bolum();
    ParkGirisCikis park=new ParkGirisCikis();
    KayitGirisKontrol kayitGirisKontrol=new KayitGirisKontrol();
    Scanner s=new Scanner(System.in);
    Arac aracislemleri=new Arac();
    Insan insan;
    AracAbstract arac;
    private String[] geciciSaatParkSayisi;
    private int saat=0,dakika=0,menuSecenek=0;
    public ParkIslemleri(){
        String numara = null,sifre=null;
        String secenek="a";

        while (menuSecenek!=3) {
            System.out.println("kayit icin 1 giris icin 2 cikis icin 3 giriniz");
            menuSecenek=s.nextInt();
            if (menuSecenek==1) {
                System.out.println("Araç tipini giriniz...(Otomobil,Kamyon,Motosiklet,Atv)");
                secenek = s.next();
                arac=aracislemleri.AracOlustur(secenek);
                System.out.println("renk giriniz");
                arac.setRenk(s.next());
                System.out.println("plaka giriniz");
                arac.setPlaka(s.next());
                System.out.println("model giriniz");
                arac.setModel(s.next());
                insan = new Insan();
                arac.setId(aracislemleri.AracEkle(arac));
                insan.setArac(arac);
                System.out.println("isim soyisim giriniz");
                insan.setIsimSoyisim(s.next());
                System.out.println("numara giriniz");
                insan.setNumara(s.next());
                System.out.println("Şifre giriniz");
                insan.setSifre(s.next());
                kayitGirisKontrol.kayit(insan);
            } else if (menuSecenek==2) {
                System.out.println("numaranizi giriniz...");
                numara = s.next();
                System.out.println("sifrenizi giriniz...");
                sifre = s.next();
                Insan i= kayitGirisKontrol.Giris(numara,sifre);
                if(i!=null){menu(i);}else{
                    System.out.println("Lütfen Kayit olun...");
                }
            }
        }
    }

    private void menu(Insan i){
        int islemSecenegi=0;
        while(islemSecenegi!=3) {
            System.out.println("islem seciniz Arac girisi icin--1 cikisi icin--2");
            islemSecenegi = s.nextInt();
            if (islemSecenegi == 1) {
                ParkGirisi(i.getNumara(), i.getSifre());
            } else if (islemSecenegi == 2) {
                IndirimKontrol(i.getNumara(), i.getSifre());
            }
        }

    }

    private void ParkGirisi(String numara, String sifre){
        p.setGirisSaati(String.valueOf(LocalTime.now().getHour())+":"+String.valueOf(LocalTime.now().getMinute()));
        park.ParkEt(numara,sifre,p);

    }
    private void IndirimKontrol(String numara,String sifre){

        String saatParkSayisi=park.ParkCikis(numara,sifre);
        System.out.println(saatParkSayisi);
        geciciSaatParkSayisi= saatParkSayisi.split("/");
        for (int i = 0; i <geciciSaatParkSayisi.length ; i++) {
            System.out.println(geciciSaatParkSayisi[i]);
        }
        if(Integer.parseInt(geciciSaatParkSayisi[1])>9){
            Indirim i=new Indirim();
            i.indirimYap(numara,sifre);
            System.out.println("bedava hak kazandınız");
        }else{
            geciciSaatParkSayisi=geciciSaatParkSayisi[0].split(":");
            saat=LocalTime.now().getHour()-Integer.parseInt(geciciSaatParkSayisi[0]);
            dakika=LocalTime.now().getMinute()-Integer.parseInt(geciciSaatParkSayisi[1]);
            dakika=(saat*60)+dakika;
            if(dakika<60){
                System.out.println("Ücret = 7TL");
            }else if(dakika>60){
                Double sure=Double.parseDouble(String.valueOf(dakika))/60;
                System.out.println(sure+(sure*2));
            }

        }
    }

}
