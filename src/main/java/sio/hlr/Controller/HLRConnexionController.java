package sio.hlr.Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    @FXML
    public void btnConnexionClicked(Event event) {
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

    @FXML
    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();

    }
}
