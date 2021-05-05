package sample.Controllers;

import Otopark.Arac.AracAbstract;
import Otopark.Insan.Insan;
import Otopark.KayitGiris.KayitGirisKontrol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KayitController implements Initializable {


    @FXML
    private TextField isimsoyisim, telno, sifre;


    KayitGirisKontrol kayitGirisKontrol = new KayitGirisKontrol();
    Insan insan = new Insan();
    AracAbstract arac;

    public AracAbstract getArac() {
        return arac;
    }

    public void setArac(AracAbstract arac) {
        this.arac = arac;
    }

    public KayitController() {
    }


    @FXML
    public void kayitol(ActionEvent event) throws IOException {
        insan = new Insan();
        insan.setIsimSoyisim(isimsoyisim.getText());
        insan.setSifre(sifre.getText());
        insan.setNumara(telno.getText());
        insan.setArac(arac);
        kayitGirisKontrol.kayit(insan);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../arayuz/anasayfa.fxml"));
        Parent root = loader.load();
        AnasayfaController scene2Controller = loader.getController();
        scene2Controller.setInsan(insan);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
