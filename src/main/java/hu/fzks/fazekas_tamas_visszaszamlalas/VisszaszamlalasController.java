package hu.fzks.fazekas_tamas_visszaszamlalas;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.lang.reflect.Type;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class VisszaszamlalasController {

    @FXML
    private TextField inputTextField;
    @FXML
    private Button inditButton;
    @FXML
    private Label visszaszamlalasLabel;

    private static Alert alert = new Alert(Alert.AlertType.WARNING);
    private LocalDateTime datum;
    Timeline timeline;
    @FXML
    public void indit(ActionEvent actionEvent) {
        String datumSzoveg = inputTextField.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm:ss");
        if (datumSzoveg.equals("")){
            alert.setContentText("A dátumot meg kell adnia!");
            alert.show();
        } else {
            try {
                datum = LocalDateTime.parse(datumSzoveg, formatter);
                timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), event -> {
                    Duration hatralevoIdo = Duration.between(LocalDateTime.now(), datum);
                    if (hatralevoIdo.isNegative()){
                        timeline.stop();
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setContentText("Lejárt az idő!");
                        alert.show();
                    } else {
                        LocalDateTime jelenlegiIdo = LocalDateTime.now();
                        Duration idotartamIdo = Duration.between(jelenlegiIdo, datum);
                        long ev = idotartamIdo.toDays() / 365;
                        long honap = (idotartamIdo.toDays() % 365) / 30;
                        long nap = idotartamIdo.toDays() % 30;
                        long ora = idotartamIdo.toHours() % 24;
                        long perc = idotartamIdo.toMinutes() % 60;
                        long masodperc = idotartamIdo.getSeconds() % 60;
                        String label = String.format("%d év %d hónap %d nap %02d:%02d:%02d", ev, honap, nap, ora, perc, masodperc);
                        visszaszamlalasLabel.setText(String.valueOf(label));
                    }
                }));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
            } catch (DateTimeException e){
                System.out.println(e);
                alert.setContentText("Nem megfelelő formátumban adta meg a dátumot");
                alert.show();
            }
        }
    }
}