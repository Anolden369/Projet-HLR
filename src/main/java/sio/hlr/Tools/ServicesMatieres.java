package sio.hlr.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.hlr.Entities.Matiere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicesMatieres {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicesMatieres() {
        uneCnx = ConnexionBDD.getCnx();
    }

    public ObservableList<Matiere> GetAllMatiere() throws SQLException {
        ObservableList<Matiere> lesMatieres = FXCollections.observableArrayList();
        ps = uneCnx.prepareStatement("SELECT matiere.designation\n" +
                "FROM matiere");
        rs = ps.executeQuery();

        while (rs.next()) {
            String designation = rs.getString("designation");
            Matiere matiere = new Matiere();
            matiere.setDesignation(designation);
            lesMatieres.add(matiere);
        }

        return lesMatieres;
    }

    public int GetIdMatiere(String nomMatiere) throws SQLException {
        ps = uneCnx.prepareStatement("SELECT matiere.id FROM matiere WHERE matiere.designation=?");
        ps.setString(1,nomMatiere);
        rs = ps.executeQuery();
        rs.next();
        int idMatiere = rs.getInt(1);
        return idMatiere;
    }

    public String getNomMatiere(String nomMatiere) throws SQLException{
        String laMatiere;
        ps = uneCnx.prepareStatement("SELECT  `designation` FROM `matiere` WHERE matiere.designation=?");
        ps.setString(1,nomMatiere);
        rs = ps.executeQuery();
        rs.next();
        laMatiere = rs.getString(1);
        return laMatiere;

    }

    //Partie Menu Admin
    public void ajoutMatiereSousMatiere(String nomMatiere,String sousMatiere) throws SQLException {
        ps = uneCnx.prepareStatement("INSERT INTO matiere (id, designation, sous_matiere) VALUES (NULL,?,?)");
        ps.setString(1,nomMatiere);
        ps.setString(2,sousMatiere);
        ps.executeUpdate();

    }
    public void ModifMatiereSousMatiere(String txtNomNewMatiere,String sousMatiere,String nomMatiere) throws SQLException {
        ps = uneCnx.prepareStatement("UPDATE `matiere` SET `designation`=?,`sous_matiere`=? WHERE designation=?");
        ps.setString(1,txtNomNewMatiere);
        ps.setString(2,sousMatiere);
        ps.setString(3,nomMatiere);
        ps.executeUpdate();
    }


}