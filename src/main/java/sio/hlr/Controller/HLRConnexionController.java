package sio.hlr.Controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sio.hlr.Tools.ConnexionBDD;
import sio.hlr.Tools.ServicesUsers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HLRConnexionController implements Initializable {
    //FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-etudiant-view.fxml"));
    ServicesUsers servicesUsers = new ServicesUsers();
    ConnexionBDD maCnx;
    @FXML
    private TextField txtLogin;
    @FXML
    private Button btnConnexion;
    @FXML
    private PasswordField pfMdp;

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
                servicesUsers.verifLogin(txtLogin.getText().toString(), pfMdp.getText().toString());

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
