package sio.hlr.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    //modif

}
