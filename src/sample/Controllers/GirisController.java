package sample.Controllers;

import Otopark.Insan.Insan;
import Otopark.KayitGiris.KayitGirisKontrol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GirisController implements Initializable {


    @FXML
    public TextField telno;
    @FXML
    public PasswordField sifre;
    @FXML
    public Button girisyap;


    KayitGirisKontrol kayitGirisKontrol=new KayitGirisKontrol();
    Insan insan;

    @FXML
    public void giris(ActionEvent event)throws IOException {

        insan=kayitGirisKontrol.Giris(telno.getText(),sifre.getText());
        if(insan==null){
            Exception e = new Exception("Kullanıcı Bulunamadı");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Kullanıcı Bulunamadı!");
            alert.getDialogPane();
            alert.showAndWait();
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../arayuz/anasayfa.fxml"));
            Parent root = loader.load();
            AnasayfaController scene2Controller = loader.getController();
            scene2Controller.setInsan(insan);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
