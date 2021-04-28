package Otopark.Bridge;

import Otopark.Park.Kat_Bolum;
import Otopark.Park.Park;
import Otopark.Park.ParkGirisCikis;

import javax.sound.midi.Soundbank;
import java.sql.Date;
import java.time.LocalTime;

public class ParkIslemleri {

    public ParkIslemleri(){
        Park p=new Park();
        p.setGirisSaati(String.valueOf(LocalTime.now().getHour())+":"+String.valueOf(LocalTime.now().getMinute()));
        Kat_Bolum k=new Kat_Bolum();

        ParkGirisCikis park=new ParkGirisCikis();
        //park.ParkEt("5545035227","123456789",p);
        String giris_saati=park.ParkCikis("5545035227","123456789");
//        System.out.println(LocalTime.now());
//        System.out.println(LocalTime.now().getHour()+":"+LocalTime.now().getMinute());
    }

}
