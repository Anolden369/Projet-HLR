package sio.hlr.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import sio.hlr.Entities.Competences;
import sio.hlr.Entities.Demandes;
import sio.hlr.Entities.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ServicesLesDemandes {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    ServicesUsers servicesUsers = new ServicesUsers();
    ServicesMesCompetences servicesMesCompetences = new ServicesMesCompetences();

    public ServicesLesDemandes() {
        uneCnx = ConnexionBDD.getCnx();
    }

    public ObservableList<Demandes> GetAllLesDemandes() throws SQLException{

        ObservableList<Demandes> lesDemandes = FXCollections.observableArrayList();
        int idUser = servicesUsers.getIdUser();
        LocalDate dateActuelle = LocalDate.now();
        Date date = Date.valueOf(dateActuelle);
        ps = uneCnx.prepareStatement("SELECT demande.id, demande.date_updated, demande.date_fin_demande, matiere.designation, demande.sous_matiere, user.nom, demande.status " +
                "FROM demande " +
                "INNER JOIN matiere ON demande.id_matiere=matiere.id " +
                "INNER JOIN user ON demande.id_user=user.id " +
                "WHERE demande.id_user NOT IN (SELECT demande.id_user FROM demande WHERE demande.id_user=?);");
        ps.setInt(1,idUser);
        rs = ps.executeQuery();
        while(rs.next()){
            Demandes uneDemande = new Demandes(
                    rs.getInt(1),
                    rs.getDate(2),
                    rs.getDate(3).toLocalDate(),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7));
            lesDemandes.add(uneDemande);
        }
        return lesDemandes;
    }

    public ArrayList<Integer> GetAllNiveaux() throws SQLException{

        ArrayList<Integer> lesNiveaux = new ArrayList<>();
        ps = uneCnx.prepareStatement("SELECT niveau.id FROM niveau");
        rs = ps.executeQuery();
        while(rs.next()){
            lesNiveaux.add(rs.getInt(1));
        }
        return lesNiveaux;
    }

    public ObservableList<Demandes> getDemandesCorrespondantesCompetences() throws SQLException {
        ObservableList<Demandes> allLesDemandes = GetAllLesDemandes();
        ObservableList<Competences> mesCompetences = servicesMesCompetences.GetAllMesCompetences();
        ObservableList<Demandes> demandesCorrespondantes = FXCollections.observableArrayList();

        ArrayList<String> matieresCompetences = new ArrayList<>();
        ArrayList<ArrayList<String>> sousMatieresCompetences = new ArrayList<>();

        int niveauUser = servicesUsers.getUser(servicesUsers.getIdUser()).getNiveau();
        ArrayList<Integer> lesNiveaux = GetAllNiveaux();

        for (Competences competence : mesCompetences) {
            String[] sousMatieres = competence.getSousMatiere().split("#");
            ArrayList<String> sousMatieresList = new ArrayList<>(Arrays.asList(sousMatieres));

            if (!sousMatieresList.isEmpty()) {
                sousMatieresList.remove(0);
            }

            matieresCompetences.add(competence.getMatiere());
            sousMatieresCompetences.add(sousMatieresList);
        }

        for (Demandes uneDemande : allLesDemandes) {
            String matiereDemande = uneDemande.getMatiere();
            String[] sousMatieresDemande = uneDemande.getSousMatiere().split("#");
            ArrayList<String> sousMatieresDemandeList = new ArrayList<>(Arrays.asList(sousMatieresDemande));

            if (!sousMatieresDemandeList.isEmpty()) {
                sousMatieresDemandeList.remove(0);
            }

            if (matieresCompetences.contains(matiereDemande)) {
                int index = matieresCompetences.indexOf(matiereDemande);
                ArrayList<String> sousMatieresCompetence = sousMatieresCompetences.get(index);

                for (String sousMatiere : sousMatieresDemandeList) {
                    if (sousMatieresCompetence.contains(sousMatiere)) {
                        demandesCorrespondantes.add(uneDemande);
                        break;
                    }
                }
            }
        }

        ObservableList<Demandes> lesDemandesParNiveau = FXCollections.observableArrayList();

        int niveauMin = niveauUser + 2;

        for (Demandes demande : demandesCorrespondantes) {
            String nomUser = demande.getNomUser();
            int niveauDemande = servicesUsers.getUser(servicesUsers.getIdUserByNom(nomUser)).getNiveau();

            if (niveauDemande > niveauMin && lesNiveaux.contains(niveauDemande)) {
                lesDemandesParNiveau.add(demande);
            }
        }

        return lesDemandesParNiveau;
    }

}