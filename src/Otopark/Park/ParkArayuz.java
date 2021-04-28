package Otopark.Park;

import Otopark.Arac.Arac;
import Otopark.Arac.AracAbstract;

import java.util.LinkedList;
import java.util.List;

public interface ParkArayuz {
    public void ParkEt(String numara,String sifre,Park park);
    public String ParkCikis(String numara,String sifre);
    public LinkedList<AracAbstract> Araclar();
    public LinkedList<Park> Parklar();

}
