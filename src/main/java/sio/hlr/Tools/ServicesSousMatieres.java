package sio.hlr.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.hlr.Entities.Matiere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ServicesSousMatieres {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicesSousMatieres() {uneCnx = ConnexionBDD.getCnx();}

    public ObservableList<Matiere> GetSousMatiereAnglais() throws SQLException {
        ObservableList<Matiere> sousMatiereAnglais = FXCollections.observableArrayList();
        ps = uneCnx.prepareStatement("SELECT matiere.sous_matiere\n" +
                "from matiere\n" +
                "WHERE matiere.designation=\"Anglais\"");

        rs = ps.executeQuery();

        while (rs.next()) {
            String sousMatieres = rs.getString("sous_matiere");
            String[] sousMatieresArray = sousMatieres.split("#");

            for (String sousMatiereString : sousMatieresArray) {
                Matiere sousMatiere = new Matiere();
                sousMatiere.setSousMatiere(sousMatiereString);
                sousMatiereAnglais.add(sousMatiere);
            }
        }

        return sousMatiereAnglais;
    }

    public ObservableList<Matiere> GetAllSousMatieres(String designation) throws SQLException {
        ObservableList<Matiere> lesSousMatieres = FXCollections.observableArrayList();
        ps = uneCnx.prepareStatement("SELECT matiere.sous_matiere from matiere WHERE matiere.designation= ?");
        ps.setString(1, designation);
        rs = ps.executeQuery();

        while (rs.next()) {
            String sousMatieres = rs.getString("sous_matiere");
            String[] sousMatieresArray = sousMatieres.split("#");
            for (String sousMatiereString : sousMatieresArray) {
                Matiere sousMatiere = new Matiere();
                sousMatiere.setSousMatiere(sousMatiereString);
                lesSousMatieres.add(sousMatiere);
            }
        }
        lesSousMatieres.remove(0);
        return lesSousMatieres;
    }

    public String GetSousMatiere(String nomMatiere) throws SQLException {
        String lesSousMatieres;
        ps = uneCnx.prepareStatement("SELECT matiere.sous_matiere\n" +
                "from matiere\n" +
                "WHERE matiere.designation=?");
        ps.setString(1,nomMatiere);
        rs = ps.executeQuery();
        rs.next();
        lesSousMatieres = rs.getString(1);
        return lesSousMatieres;
    }


}