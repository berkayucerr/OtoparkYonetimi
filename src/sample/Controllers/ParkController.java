package sample.Controllers;

import Otopark.Insan.Insan;
import Otopark.Park.Kat_Bolum;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ParkController implements Initializable {

    Insan insan;
    Park p=new Park();
    Kat_Bolum k=new Kat_Bolum();
    ParkGirisCikis park=new ParkGirisCikis();
    LinkedList<Kat_Bolum> a;

    @FXML
    ListView<String> liste;



    public void setInsan(Insan insan) {
        this.insan = insan;
    }

    public Insan getInsan() {
        return insan;
    }

    private void ParkGirisi(int secim){

        p.setGirisSaati(String.valueOf(LocalTime.now().getHour())+":"+String.valueOf(LocalTime.now().getMinute()));
        park.ParkEt(getInsan().getNumara(),getInsan().getSifre(),p,secim);

    }

    @FXML
    public void tik(ActionEvent event)throws IOException {

        a=park.KatBolumKontrol();
        for (int i = 0; i <a.size() ; i++) {
            if(a.get(i).getDolu_mu()==0)
            liste.getItems().add(a.get(i).getKat()+a.get(i).getBolum());
            else
                liste.getItems().add("DOLU");
        }
    }
    @FXML
    public void parksec(ActionEvent event)throws IOException {
        if(a.get(liste.getSelectionModel().getSelectedIndex()).getDolu_mu()==1){
            Exception e = new Exception("Dolu Park Yeri");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Burası Dolu!");
            alert.getDialogPane();
            alert.showAndWait();
        }else{
            ParkGirisi(liste.getSelectionModel().getSelectedIndex());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../arayuz/anasayfa.fxml"));
            Parent root = loader.load();
            AnasayfaController scene3Controller = loader.getController();
            scene3Controller.setInsan(insan);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





    }
}
