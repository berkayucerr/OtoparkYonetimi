package sample.Controllers;

import Otopark.Insan.Insan;
import Otopark.Park.Kat_Bolum;
import Otopark.Park.Park;
import Otopark.Park.ParkGirisCikis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ParkController implements Initializable {

    Insan insan;
    Park p = new Park();
    Kat_Bolum k = new Kat_Bolum();
    ParkGirisCikis park = new ParkGirisCikis();
    LinkedList<Kat_Bolum> a;
    FXMLLoader loader;
    Label top;

    @FXML
    ListView<Label> liste;


    public void setInsan(Insan insan) {
        this.insan = insan;
    }

    public Insan getInsan() {
        return insan;
    }

    private void ParkGirisi(Kat_Bolum kat_bolum) {

        p.setGirisSaati(String.valueOf(LocalTime.now().getHour()) + ":" + String.valueOf(LocalTime.now().getMinute()));
        //KatBolumDuzenle
        p.setKat_bolum(kat_bolum);
        park.ParkEt(getInsan().getNumara(), getInsan().getSifre(), p);

    }

    @FXML
    public void tik(ActionEvent event) throws IOException {
        AnasayfayaGec(event);
    }

    private void AnasayfayaGec(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("../arayuz/anasayfa.fxml"));
        Parent root = loader.load();
        AnasayfaController scene3Controller = loader.getController();
        scene3Controller.setInsan(insan);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void parksec(ActionEvent event) throws IOException {
        if (a.get(liste.getSelectionModel().getSelectedIndex()).getDolu_mu() == 1) {
            Exception e = new Exception("Dolu Park Yeri");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Burası Dolu!");
            alert.getDialogPane();
            alert.showAndWait();
        } else {
            ParkGirisi(a.get(liste.getSelectionModel().getSelectedIndex()));
            AnasayfayaGec(event);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        a = park.KatBolumKontrol();
        for (int i = 0; i < a.size(); i++) {
            top = new Label(a.get(i).getKat()+"-"+a.get(i).getBolum());
            top.setPadding(new Insets(10));
            top.setFont(Font.font(20));
            top.setTextFill(Color.WHITE);
            top.setMaxWidth(Double.POSITIVE_INFINITY);
            top.setAlignment(Pos.CENTER);
            if (a.get(i).getDolu_mu() == 0) {
                top.setStyle("-fx-background-color: Green");
            } else{
                top.setStyle("-fx-background-color: Red");
            }
            liste.getItems().add(top);
        }
    }
}

