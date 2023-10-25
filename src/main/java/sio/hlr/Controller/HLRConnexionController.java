package sio.hlr.Controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sio.hlr.Tools.ConnexionBDD;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            maCnx = new ConnexionBDD();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void btnConnexionClicked(Event event) {
        if(txtLogin.getText().equals("")){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez votre login");
            alert.setHeaderText("");
            alert.showAndWait();
        } else if(pfMdp.getText().equals("")){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir votre mot de passe");
            alert.setHeaderText("");
            alert.showAndWait();
        }
    }
}
