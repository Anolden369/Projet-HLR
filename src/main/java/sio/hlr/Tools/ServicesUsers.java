package sio.hlr.Tools;

import javafx.scene.control.Alert;
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

    public User getUser(int idUser) throws SQLException {
        ps = uneCnx.prepareStatement("SELECT user.id, user.nom, user.prenom, user.email, user.role, user.id_niveau FROM user WHERE user.id = ?");
        ps.setInt(1, idUser);
        rs = ps.executeQuery();
        rs.next();
        User unUser = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
        return unUser;
    }

    public int getIdUserByNom(String nomUser) throws SQLException {
        int userId = 0;
        ps = uneCnx.prepareStatement("SELECT user.id FROM user WHERE user.nom=?");
        ps.setString(1, nomUser);
        rs = ps.executeQuery();
        rs.next();
        userId = rs.getInt(1);
        return userId;
    }
}
