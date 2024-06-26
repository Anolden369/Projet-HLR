package sio.hlr.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import sio.hlr.Entities.Competences;
import sio.hlr.Entities.Demandes;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ServicesMesCompetences {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    LocalDate DateActuelle = LocalDate.now();
    ServicesUsers servicesUsers = new ServicesUsers();
    ServicesMatieres servicesMatieres = new ServicesMatieres();

    public ServicesMesCompetences() {
        uneCnx = ConnexionBDD.getCnx();
    }

    public ObservableList<Competences> GetAllMesCompetences() throws SQLException{

        ObservableList<Competences> mesCompetences = FXCollections.observableArrayList();
        int idUser = servicesUsers.getIdUser();
        ps = uneCnx.prepareStatement("SELECT competence.id,matiere.designation, competence.id_user, competence.sous_matiere, competence.statut FROM competence INNER JOIN matiere ON competence.id_matiere=matiere.id WHERE competence.id_user=?");
        ps.setInt(1,idUser);
        rs = ps.executeQuery();
        while(rs.next()){
            Competences uneCompetence = new Competences(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getInt(5));
            mesCompetences.add(uneCompetence);
        }
        return mesCompetences;
    }

    public ObservableList<Integer> GetAllExpireCompetencesByUser() throws SQLException {
        ObservableList<Integer> expireCompetencesId = FXCollections.observableArrayList();
        int idUser = servicesUsers.getIdUser();
        ps = uneCnx.prepareStatement("SELECT id FROM competence c WHERE c.id_user=? AND DATEDIFF(CURRENT_DATE, c.date)>=30");
        ps.setInt(1,idUser);
        rs = ps.executeQuery();
        while(rs.next()){
            expireCompetencesId.add(rs.getInt(1));
        }
        return  expireCompetencesId;
    }

    public void creerCompetence(ArrayList<String> lesSousMatieres, String nomMatiere) throws SQLException {
        int idUser = servicesUsers.getIdUser();
        int statut = 1;
        int idMatiere = servicesMatieres.GetIdMatiere(nomMatiere);

        String sousMatiere = "";
        for (String uneSousMatiere : lesSousMatieres) {
            sousMatiere += "#" + uneSousMatiere;
        }

        // Vérifier si la compétence existe déjà pour cet utilisateur dans cette matière
        boolean competenceExistante = verificationCompetenceByUser(idUser, idMatiere);

        if (competenceExistante) {
            // Mettre à jour la compétence existante avec les nouvelles sous-matières
            ps = uneCnx.prepareStatement("UPDATE competence SET sous_matiere = ? WHERE id_user = ? AND id_matiere = ?;");
            ps.setString(1, sousMatiere);
            ps.setInt(2, idUser);
            ps.setInt(3, idMatiere);
            ps.executeUpdate();
        } else {
            // Créer une nouvelle compétence avec les sous-matières
            ps = uneCnx.prepareStatement("INSERT INTO competence (id, id_matiere, id_user, sous_matiere, statut) VALUES (NULL, ?, ?, ?, ?);");
            ps.setInt(1, idMatiere);
            ps.setInt(2, idUser);
            ps.setString(3, sousMatiere);
            ps.setInt(4, statut);
            ps.executeUpdate();
        }
    }

    private boolean verificationCompetenceByUser(int idUser, int idMatiere) throws SQLException {
        ps = uneCnx.prepareStatement("SELECT COUNT(*) FROM competence WHERE id_user = ? AND id_matiere = ?;");
        ps.setInt(1, idUser);
        ps.setInt(2, idMatiere);
        rs = ps.executeQuery();
        if (rs.next()) {
            int verif = rs.getInt(1);
            return verif > 0;
        }
        return false;
    }

    public int getIdCompetenceByUser(String Matiere) throws SQLException {
        int idMatiere = servicesMatieres.GetIdMatiere(Matiere);
        ps = uneCnx.prepareCall("SELECT competence.id FROM competence WHERE competence.id_matiere=? AND competence.id_user=?");
        ps.setInt(1,idMatiere);
        ps.setInt(2,servicesUsers.getIdUser());
        rs = ps.executeQuery();
        rs.next();
        int idComp = rs.getInt(1);
        return idComp;
    }

}
