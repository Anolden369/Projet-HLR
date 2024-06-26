package sio.hlr.Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    ServicesUsers servicesUsers = new ServicesUsers();
    ConnexionBDD maCnx;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField pfMdp;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnConnexion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    // Redirige l'utilisateur qui se connecte soit vers la page 'administrateur', soit vers la page 'étudiant'.
    // Vérifie donc si les informations saisies dans le formulaire (login, mdp) sont correctes et redirige l'utilisateur en fonction du statut.
    @FXML
    public void onBtnConnexionClicked(ActionEvent actionEvent) {
        try{
            if(txtLogin.getText().equals("")){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setContentText("Veuillez saisir votre login");
                alert.setHeaderText("");
                alert.showAndWait();
            } else if(pfMdp.getText().equals("")){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setContentText("Veuillez saisir votre mot de passe");
                alert.setHeaderText("");
                alert.showAndWait();
            } else {
                maCnx = new ConnexionBDD();
                servicesUsers = new ServicesUsers();
                servicesUsers.verifLogin(txtLogin.getText(), pfMdp.getText());

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Quitte l’utilisateur de l’application.
    @FXML
    public void onBtnExitClicked(Event event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
