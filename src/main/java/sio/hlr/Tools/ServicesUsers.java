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
    private static Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicesUsers()
    {
        uneCnx = ConnexionBDD.getCnx();
    }

    public static boolean verifLogin(String email, String password) throws SQLException {

        boolean statut = false;
        String query = "SELECT count(*) FROM user WHERE email=? AND password=?";
        try (PreparedStatement ps = uneCnx.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt(1) == 1) {
                    statut = true;
                    String query1 = "SELECT count(*) FROM user WHERE email=? AND password=? AND role='Etudiant'";
                    try (PreparedStatement ps1 = uneCnx.prepareStatement(query1)) {
                        ps1.setString(1, email);
                        ps1.setString(2, password);
                        ResultSet resultSet1 = ps1.executeQuery();
                        while (resultSet1.next()) {
                            if (resultSet1.getInt(1) == 1) {
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
                            } else if (resultSet1.getInt(1) == 0) {
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
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de connexion");
                    alert.setContentText("Veuillez saisir les bons identifiants !");
                    alert.setHeaderText("");
                    alert.showAndWait();
                }
            }
            resultSet.close();
            return statut;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
