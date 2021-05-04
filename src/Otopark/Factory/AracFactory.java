package Otopark.Factory;

import Otopark.Arac.AracAbstract;

public interface AracFactory {

    int AracEkle(AracAbstract arac);
    AracAbstract AracOlustur(String arac_tipi);

}
