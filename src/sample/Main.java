package sample;

import KayitGiris.KayitGirisKontrol;
import Otopark.Arac.Arac;
import Otopark.Arac.Otomobil;
import Otopark.Insan.Insan;
import Otopark.Park.Kat_Bolum;
import Otopark.Park.Park;
import Otopark.Park.ParkGirisCikis;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/
    }


    public static void main(String[] args) {
        //launch(args);
        Park p=new Park();
        p.setGirisSaati("19.20");
        Kat_Bolum k=new Kat_Bolum();
        k.setBolum("38bs192");
        k.setKat("3");
        k.setDolu_mu(1);
        p.setKat_bolum(k);
        ParkGirisCikis park=new ParkGirisCikis();
        park.ParkEt("5545035227","123456789",p);






    }
}
