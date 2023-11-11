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

    public ServicesUsers()
    {
        uneCnx = ConnexionBDD.getCnx();
    }

    public User verifLogin(String email, String password) throws SQLException, IOException {
        User user = null;
        String query = "SELECT email,password,role FROM user WHERE email=? AND password=?";
        try (PreparedStatement ps = uneCnx.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setContentText("Veuillez saisir les bons identifiants !");
                alert.setHeaderText("");
                alert.showAndWait();
            } else {
                user = new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
                if ((resultSet.getString(3)).equals("Etudiant")) {
                    // Fermer la scène actuelle
                    Stage stage = (Stage) HLRApplication.getMainScene().getWindow();
                    stage.close();

                    // Ouvrir une nouvelle scène
                    FXMLLoader loader = new FXMLLoader(HLRApplication.class.getResource("menu-etudiant-view.fxml"));
                    Parent root = loader.load();
                    Stage newStage = new Stage();
                    newStage.setTitle("Menu Etudiant");
                    newStage.setScene(new Scene(root));
                    newStage.show();
                } else {
                    Stage stage = (Stage) HLRApplication.getMainScene().getWindow();
                    stage.close();

                    // Ouvrir une nouvelle scène
                    FXMLLoader loader = new FXMLLoader(HLRApplication.class.getResource("menu-admin-view.fxml"));
                    Parent root = loader.load();
                    Stage newStage = new Stage();
                    newStage.setTitle("Menu Administrateur");
                    newStage.setScene(new Scene(root));
                    newStage.show();
                }
            }
            return user;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
