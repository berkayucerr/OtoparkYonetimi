package Otopark.Bridge;

import Otopark.Arac.Arac;
import Otopark.Arac.AracAbstract;
import Otopark.Park.Kat_Bolum;
import Otopark.Park.Park;
import Otopark.Park.ParkGirisCikis;

import javax.sound.midi.Soundbank;
import java.sql.Date;
import java.time.LocalTime;
import java.util.LinkedList;

public class ParkIslemleri {

    public ParkIslemleri(){
        Park p=new Park();
        p.setGirisSaati(String.valueOf(LocalTime.now().getHour())+":"+String.valueOf(LocalTime.now().getMinute()));
        Kat_Bolum k=new Kat_Bolum();

        ParkGirisCikis park=new ParkGirisCikis();
        //park.ParkEt("5545035227","123456789",p);

        /*LinkedList<AracAbstract>liste=new LinkedList<AracAbstract>();
        liste=park.Araclar();
        for (int i = 0; i <liste.size() ; i++) {
            System.out.println("id = "+liste.get(i).getId());
            System.out.println("Model = "+liste.get(i).getModel());
            System.out.println("Plaka = "+liste.get(i).getPlaka());
            System.out.println("Renk = "+liste.get(i).getRenk());

        }*/
        LinkedList<Park> liste2=new LinkedList<Park>();
        liste2=park.Parklar();
        for (int i = 0; i <liste2.size() ; i++) {
            System.out.println("id = "+liste2.get(i).getId_park());
            System.out.println("Giriş Saati = "+liste2.get(i).getGirisSaati());
            System.out.println("Arac id = "+liste2.get(i).getArac().getId());
            System.out.println("Kat = "+liste2.get(i).getKat_bolum().getKat()+" Bolum = "+liste2.get(i).getKat_bolum().getBolum());
            System.out.println("Arac model = "+liste2.get(i).getArac().getModel());
            System.out.println("Arac plaka = "+liste2.get(i).getArac().getPlaka());
            System.out.println("Arac Renk = "+liste2.get(i).getArac().getRenk());

        }
        String giris_saati=park.ParkCikis("5545035227","123456789");
        System.out.println(LocalTime.now());
        System.out.println(LocalTime.now().getHour()+":"+LocalTime.now().getMinute());
    }

}
