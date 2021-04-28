package Otopark.Park;

import Otopark.Arac.Arac;
import Otopark.Arac.AracAbstract;

import java.util.List;

public interface ParkArayuz {
    public void ParkEt(String numara,String sifre,Park park);
    public String ParkCikis(String numara,String sifre);
    public List<Arac> Araclar();
    public List<ParkAbstract> Parklar();

}
