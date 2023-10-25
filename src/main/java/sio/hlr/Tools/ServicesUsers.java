package sio.hlr.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.hlr.Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
