package sio.hlr.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import sio.hlr.Entities.Demandes;
import sio.hlr.Entities.Matiere;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ServicesMesDemandes {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    LocalDate DateActuelle = LocalDate.now();
    ServicesUsers servicesUsers = new ServicesUsers();
    ServicesMatieres servicesMatieres = new ServicesMatieres();

    public ServicesMesDemandes() {
        uneCnx = ConnexionBDD.getCnx();
    }

    public ObservableList<Demandes> GetAllMesDemandes() throws SQLException{

        ObservableList<Demandes> mesDemandes = FXCollections.observableArrayList();
        int idUser = servicesUsers.getIdUser();
        LocalDate dateActuelle = LocalDate.now();
        Date date = Date.valueOf(dateActuelle);
        ps = uneCnx.prepareStatement("SELECT demande.id, demande.date_fin_demande, matiere.designation, demande.sous_matiere, demande.status FROM demande INNER JOIN matiere ON demande.id_matiere=matiere.id WHERE id_user = ? AND demande.date_fin_demande >= ?");
        ps.setInt(1,idUser);
        ps.setDate(2, date);
        rs = ps.executeQuery();
        while(rs.next()){
            Demandes uneDemande = new Demandes(rs.getInt(1), rs.getDate(2).toLocalDate(),rs.getString(3),rs.getString(4),rs.getInt(5));
            mesDemandes.add(uneDemande);
        }
        return mesDemandes;
    }

    public void creerDemande(ArrayList<String> lesSousMatieres, DatePicker dateFinDemande, String nomMatiere) throws SQLException {
        int idUser = servicesUsers.getIdUser();
        int statut = 1;
        int idMatiere = servicesMatieres.GetIdMatiere(nomMatiere);
        String sousMatiere = "";
        for (String uneSousMatiere:lesSousMatieres) {
            sousMatiere = sousMatiere + "#" + uneSousMatiere;
        }
        ps = uneCnx.prepareStatement("INSERT INTO demande (id, date_updated, date_fin_demande, sous_matiere, id_user, id_matiere, status) VALUES (NULL, ?, ?, ?, ?, ?, ?)");
        ps.setDate(1, Date.valueOf(DateActuelle));
        ps.setDate(2, Date.valueOf(dateFinDemande.getValue()));
        ps.setString(3,sousMatiere);
        ps.setInt(4, idUser);
        ps.setInt(5, idMatiere);
        ps.setInt(6, statut);
        ps.executeUpdate();

    }

    public void modifierDemande(int idDemande, ArrayList<String> lesSousMatieres, DatePicker dateFinDemande, String nomMatiere) throws SQLException {
        int idUser = servicesUsers.getIdUser();
        int idMatiere = servicesMatieres.GetIdMatiere(nomMatiere);
        String sousMatiere = "";
        for (String uneSousMatiere:lesSousMatieres) {
            sousMatiere = sousMatiere + "#" + uneSousMatiere;
        }
        ps = uneCnx.prepareStatement("UPDATE demande SET date_updated = ?, date_fin_demande = ?, sous_matiere = ?, id_matiere = ? WHERE demande.id = ? AND demande.id_user = ?");
        ps.setDate(1, Date.valueOf(DateActuelle));
        ps.setDate(2, Date.valueOf(dateFinDemande.getValue()));
        ps.setString(3,sousMatiere);
        ps.setInt(4, idMatiere);
        ps.setInt(5, idDemande);
        ps.setInt(6, idUser);
        ps.executeUpdate();

    }

}
