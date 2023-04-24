package hu.fzks.fazekas_tamas_visszaszamlalas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VisszaszamlalasApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VisszaszamlalasApplication.class.getResource("visszaszamlalas-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 150);
        stage.setTitle("Visszaszámlálás");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}