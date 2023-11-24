package sio.hlr.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.hlr.Entities.Demandes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicesMesDemandes {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;

    ServicesUsers servicesUsers = new ServicesUsers();

    public ServicesMesDemandes() {
        uneCnx = ConnexionBDD.getCnx();
    }

    public ObservableList<Demandes> GetAllMesDemandes() throws SQLException{

        ObservableList<Demandes> mesDemandes = FXCollections.observableArrayList();
        int idUser = servicesUsers.getIdUser();
        ps = uneCnx.prepareStatement("SELECT demande.id, demande.date_fin_demande, matiere.designation, demande.sous_matiere, demande.status FROM demande INNER JOIN matiere ON demande.id_matiere=matiere.id WHERE id_user = ?");
        ps.setInt(1,idUser);
        rs = ps.executeQuery();
        while(rs.next()){
            Demandes uneDemande = new Demandes(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getInt(5));
            mesDemandes.add(uneDemande);
        }
        return mesDemandes;
    }

}
