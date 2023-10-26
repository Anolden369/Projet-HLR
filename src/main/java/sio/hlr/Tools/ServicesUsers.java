package sio.hlr.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.hlr.Entities.User;

import java.sql.*;

public class ServicesUsers {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicesUsers()
    {
        uneCnx = ConnexionBDD.getCnx();
    }

    public ObservableList<User> verifLogin(String email, String password) throws SQLException {
        ObservableList<User> lesUsers = FXCollections.observableArrayList();

        ps = uneCnx.prepareCall("SELECT * FROM user WHERE email = ? AND password = ?");
        ps.setString(1, email);
        ps.setString(2, password);

        rs = ps.executeQuery();

        while(rs.next())
        {
            User unUser = new User(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7),
                    rs.getInt(8));
            //l'ajouter à une collection
            lesUsers.add(unUser);
        }

        return lesUsers;
    }
    public boolean checkCredentials(String prenom, String password) throws SQLException {
        // Vérifiez les informations d'identification dans la base de données
        String query = "SELECT * FROM user WHERE prenom=? AND password=?";
        try (PreparedStatement ps = uneCnx.prepareStatement(query)) {
            ps.setString(1, prenom);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();

            // Si une correspondance est trouvée, l'utilisateur est valide
            boolean isValidUser = resultSet.next();

            // Fermez les ressources
            resultSet.close();
            return isValidUser;
        }
    }
    public boolean isUserClient(String prenom) throws SQLException {
        // Vérifiez dans la base de données si l'utilisateur est un client
        String query = "SELECT * FROM user WHERE prenom=? AND role='Etudiant'";
        try (PreparedStatement ps = uneCnx.prepareStatement(query)) {
            ps.setString(1, prenom);
            ResultSet resultSet = ps.executeQuery();

            // Si une correspondance est trouvée, l'utilisateur est un client
            boolean isClient = resultSet.next();

            // Fermez les ressources
            resultSet.close();
            return isClient;
        }
    }

    public boolean isUserAdmin(String prenom) throws SQLException {
        // Vérifiez dans la base de données si l'utilisateur est un administrateur
        String query = "SELECT * FROM user WHERE prenom=? AND role='admin'";
        try (PreparedStatement ps = uneCnx.prepareStatement(query)) {
            ps.setString(1, prenom);
            ResultSet resultSet = ps.executeQuery();

            // Si une correspondance est trouvée, l'utilisateur est un administrateur
            boolean isAdmin = resultSet.next();

            // Fermez les ressources
            resultSet.close();
            return isAdmin;
        }
    }



}
