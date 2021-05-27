package sample.Controllers;

import Otopark.IndırımKontrol.ParkIslemleri;
import Otopark.Insan.Insan;
import Otopark.Park.Park;
import Otopark.Park.ParkGirisCikis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class AnasayfaController implements Initializable {

    Insan insan;

    public Insan getInsan() {
        return insan;
    }

    public void setInsan(Insan insan) {
        this.insan = insan;
    }

    @FXML
    public Button parkcikisi, parketme, arackaydi;


    @FXML
    public void cikis(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../arayuz/ilkSayfa.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void parkcikis(ActionEvent event) throws IOException {
        Exception e;
        Alert alert;
        ParkIslemleri parkIslemleri = new ParkIslemleri();
        ParkGirisCikis parkGirisCikis = new ParkGirisCikis();
        if (arama(parkGirisCikis.Parklar())) {
            e = new Exception("Çıkışınız Yapıldı. Yine Bekleriz....");
            alert = new Alert(Alert.AlertType.WARNING);
            //indirim ve park sayısı ayrıştırma
            String a=String.valueOf(parkIslemleri.IndirimKontrol(getInsan().getNumara(), getInsan().getSifre()));
            String [] dizi=a.split("-");
            if(dizi[1].equals("10")){
                alert.setHeaderText("Tebrikler Bedava Park Kazandınız, Ücret Bulunmamaktadır.");
            }else{
                alert.setHeaderText("Fiyat ="+dizi[0]+ "Park Sayınız ="+dizi[1]);
            }
            alert.getDialogPane();
            alert.showAndWait();
        } else {
            e = new Exception("Park Bulunamadı");
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Park Yerinde Aracınızı Bulamadım.");
            alert.getDialogPane();
            alert.showAndWait();
        }
    }

    @FXML
    public void parket(ActionEvent event) throws IOException {
        Exception e;
        Alert alert;
        ParkIslemleri parkIslemleri = new ParkIslemleri();
        ParkGirisCikis parkGirisCikis = new ParkGirisCikis();
        if (arama(parkGirisCikis.Parklar())) {
            e = new Exception("Park Var.");
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Zaten Aracınızı Park Ettiniz.");
            alert.getDialogPane();
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../arayuz/parketme.fxml"));
            Parent root = loader.load();
            ParkController scene3Controller = loader.getController();
            scene3Controller.setInsan(insan);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }








    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    private boolean arama(LinkedList<Park> liste) {
        for (int i = 0; i < liste.size(); i++) {
            if (this.getInsan().getArac().getId() == liste.get(i).getArac().getId())
                return true;
        }
        return false;
    }
}
