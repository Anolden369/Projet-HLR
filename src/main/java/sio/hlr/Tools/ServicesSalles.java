package sio.hlr.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.hlr.Entities.Demandes;
import sio.hlr.Entities.Salle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicesSalles {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    public ServicesSalles(){uneCnx = ConnexionBDD.getCnx();}

    public int getSalle() throws SQLException {
        ps = uneCnx.prepareStatement("SELECT MAX(`id`)+1 FROM `salle`;");
        rs = ps.executeQuery();
        rs.next();
        int numSalle =rs.getInt(1);
        return numSalle;
    }

    public ObservableList<Salle> getAllSalle() throws SQLException {
        ObservableList<Salle> lesSalles= FXCollections.observableArrayList();
        ps = uneCnx.prepareStatement("SELECT `id`,`code_salle`,`etage` FROM `salle`;");
        rs = ps.executeQuery();
        while (rs.next())
        {
            Salle uneSalle = new Salle(rs.getInt(1),rs.getString(2),rs.getInt(3));
            lesSalles.add(uneSalle);
        }

        return lesSalles;
    }

    public int getIdSalle(String codeSalle) throws SQLException {
        ps = uneCnx.prepareStatement("SELECT id FROM `salle` where code_salle = ?;");
        ps.setString(1,codeSalle);
        rs = ps.executeQuery();
        rs.next();
        int idSalle =rs.getInt(1);
        return idSalle;
    }

    public int crerSalle(int numSalle,String numSalle2) throws SQLException {
        ps = uneCnx.prepareStatement("INSERT INTO `salle`(`id`, `code_salle`) VALUES (?,?)");
        ps.setInt(1,numSalle);
        ps.setString(2,numSalle2);
        ps.executeUpdate();
        return numSalle;
    }

}
