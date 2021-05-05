package sample.Controllers;

import Otopark.Arac.Arac;
import Otopark.Arac.AracAbstract;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AracKaydiController implements Initializable {





    @FXML
    private TextField plaka;

    @FXML
    private TextField model;

    @FXML
    private TextField renk;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private Button gir;

    Arac aracislemleri =new Arac();
    AracAbstract arac;


    @FXML
    public void gir(ActionEvent e) throws IOException {
        arac=aracislemleri.AracOlustur(combobox.getSelectionModel().getSelectedItem());
        arac.setRenk(renk.getText());
        arac.setPlaka(plaka.getText());
        arac.setModel(model.getText());
        arac.setId(aracislemleri.AracEkle(arac));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../arayuz/kayitol2.fxml"));
        Parent root = loader.load();
        KayitController scene2Controller = loader.getController();
        scene2Controller.setArac(arac);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combobox.getItems().removeAll();
        combobox.getItems().addAll("Otomobil","Motosiklet","Kamyon","Atv");
    }
}
