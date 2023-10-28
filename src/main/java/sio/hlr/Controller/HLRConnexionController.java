package sio.hlr.Controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sio.hlr.Tools.ConnexionBDD;
import sio.hlr.Tools.ServicesUsers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HLRConnexionController implements Initializable {
    ConnexionBDD maCnx;
    @FXML
    private TextField txtLogin;
    @FXML
    private Button btnConnexion;
    @FXML
    private PasswordField pfMdp;

    @FXML
    public void btnConnexionClicked(Event event) {
        String username = txtLogin.getText();
        String password = pfMdp.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir votre login et votre mot de passe");
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            try {
                ServicesUsers servicesUsers = new ServicesUsers();
                boolean isValidUser = servicesUsers.checkCredentials(username, password);

                if (isValidUser) {
                    if (servicesUsers.isUserClient(username)) {
                        loadClientScene();
                    } else if (servicesUsers.isUserAdmin(username)) {
                        loadAdminScene();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de connexion");
                    alert.setContentText("Identifiants incorrects. Veuillez réessayer.");
                    alert.setHeaderText("");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadClientScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-etudiant-view.fxml"));
            Parent root = loader.load();
            Scene clientScene = new Scene(root);
            Stage stage = (Stage) btnConnexion.getScene().getWindow();
            stage.setScene(clientScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAdminScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-admin-view.fxml"));
            Parent root = loader.load();
            Scene adminScene = new Scene(root);
            Stage stage = (Stage) btnConnexion.getScene().getWindow();
            stage.setScene(adminScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}