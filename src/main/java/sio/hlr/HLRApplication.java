package sio.hlr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
public class HLRApplication extends Application {
    private static Stage mainStage;
    @Override
    public void start(Stage StagePrincipale) throws Exception {
        mainStage = StagePrincipale;
        LoginScene();
    }

    public static void LoginScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(HLRApplication.class.getResource("hlr-connexion-view.fxml"));
        Parent root = loader.load();
        mainStage.setTitle("Help Le Rebours - Connexion");
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }

    public static void EtudiantScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(HLRApplication.class.getResource("menu-etudiant-view.fxml"));
        Parent root = loader.load();
        mainStage.setTitle("Help Le Rebours - Menu Etudiant");
        mainStage.setScene(new Scene(root));
    }

    public static void AdminScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(HLRApplication.class.getResource("menu-admin-view.fxml"));
        Parent root = loader.load();
        mainStage.setTitle("Help Le Rebours - Menu Administrateur");
        mainStage.setScene(new Scene(root));
    }
}
