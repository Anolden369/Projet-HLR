package sio.hlr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HLRApplication extends Application {

    private static Scene mainScene;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HLRApplication.class.getResource("hlr-connexion-view.fxml"));
        mainScene = new Scene(fxmlLoader.load());
        stage.setTitle("Help Le Rebours ");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Scene getMainScene() {
        return mainScene;
    }
}