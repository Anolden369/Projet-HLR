package sio.hlr.Tools;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.hlr.Entities.Matiere;

import java.sql.SQLException;
public class ServicesStatistiques {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    ServicesUsers servicesUsers = new ServicesUsers();
    LocalDate DateActuelle = LocalDate.now();
    public ServicesStatistiques() {
        uneCnx = ConnexionBDD.getCnx();
    }

    public HashMap<String, Double> GetDatasGraphique1() throws SQLException {
        HashMap<String, Double> datas = new HashMap();
        int idUser = servicesUsers.getIdUser();

        try {
            ps = uneCnx.prepareStatement("SELECT  matiere.designation,COUNT(demande.id) FROM demande\n" +
                    "JOIN matiere ON matiere.id=demande.id_matiere\n" +
                    "WHERE demande.id_user = ?\n" +
                    "GROUP BY matiere.designation;");
            ps.setInt(1, idUser);
            rs = ps.executeQuery();

            while(rs.next()) {
                datas.put(rs.getString(1), rs.getDouble(2));
            }

            rs.close();
            return datas;
        } catch (SQLException var4) {
            throw new RuntimeException(var4);
        }
    }
    // Le nombre de soutiens réalisés par un utilisateur pour chaque matière (un soutien est réalisé lorsque le statut est à 3 donc statut = salle attribuée)

    //Par défault des soutiens ont été insérer dans la base de données pour réaliser ces statistiques
    public HashMap<String, Integer> GetDatasGraphique2() throws SQLException {
        HashMap<String, Integer> datas = new HashMap();
        int idUser = servicesUsers.getIdUser();

        try {
            ps = uneCnx.prepareStatement("SELECT matiere.designation, COUNT(soutien.id) \n" +
                    "FROM soutien \n" +
                    "INNER JOIN competence ON soutien.id_competence = competence.id \n" +
                    "INNER JOIN matiere ON competence.id_matiere = matiere.id\n" +
                    "WHERE competence.id_user = ? \n" +
                    "AND soutien.status = 3 \n" +
                    "AND soutien.date_du_soutien < ? \n" +
                    "GROUP BY matiere.designation");

            ps.setInt(1, idUser);
            ps.setDate(2, Date.valueOf(DateActuelle));
            rs = ps.executeQuery();

            while(rs.next()) {
                datas.put(rs.getString(1), rs.getInt(2));
            }

            rs.close();
            return datas;
        } catch (SQLException var3) {
            throw new RuntimeException(var3);
        }
    }
}
