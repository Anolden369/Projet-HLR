package sio.hlr.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sio.hlr.Entities.User;
import sio.hlr.HLRApplication;

import java.io.IOException;
import java.sql.*;

public class ServicesUsers {
    private  Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    private static String emailUser;
    private static String passwordUser;

    public ServicesUsers()
    {
        uneCnx = ConnexionBDD.getCnx();
    }

    public User verifLogin(String email, String password) throws SQLException, IOException {
        User user = null;
        String query = "SELECT email,password,role FROM user WHERE email=? AND password=?";
        ps = uneCnx.prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2, password);
        rs = ps.executeQuery();
        if (!rs.next()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setContentText("Veuillez saisir les bons identifiants !");
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            user = new User(rs.getString(1),rs.getString(2),rs.getString(3));
            emailUser = user.getEmail();
            passwordUser = user.getPassword();
            if ((rs.getString(3)).equals("Etudiant")) {
                HLRApplication.EtudiantScene();
            } else {
                HLRApplication.AdminScene();
            }
        }
        return user;
    }

    public int getIdUser() throws SQLException {
        int userId = 0;
        ps = uneCnx.prepareStatement("SELECT user.id FROM user WHERE user.email=? AND user.password= ?");
        ps.setString(1, emailUser);
        ps.setString(2, passwordUser);
        rs = ps.executeQuery();
        rs.next();
        userId = rs.getInt(1);
        return userId;
    }
}
