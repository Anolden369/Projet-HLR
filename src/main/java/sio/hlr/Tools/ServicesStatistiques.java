package sio.hlr.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.hlr.Entities.Matiere;
import sio.hlr.Entities.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

import java.sql.SQLException;
public class ServicesStatistiques {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    ServicesUsers servicesUsers = new ServicesUsers();
    LocalDate DateActuelle = LocalDate.now();
    public ServicesStatistiques() {uneCnx = ConnexionBDD.getCnx();}

    //Par défault des soutiens ont été insérer dans la base de données pour réaliser ces statistiques

    // Le nombre de demandes effectuées par un utilisateur pour chaque matière

    public HashMap<String, Integer> GetDatasGraphique1() throws SQLException {
        HashMap<String, Integer> datas = new HashMap();
        int idUser = servicesUsers.getIdUser();

        try {
            ps = uneCnx.prepareStatement("SELECT  matiere.designation,COUNT(demande.id) FROM demande\n" +
                    "JOIN matiere ON matiere.id=demande.id_matiere\n" +
                    "WHERE demande.id_user = ?\n" +
                    "GROUP BY matiere.designation;");
            ps.setInt(1, idUser);
            rs = ps.executeQuery();

            while(rs.next()) {
                datas.put(rs.getString(1), rs.getInt(2));
            }

            rs.close();
            return datas;
        } catch (SQLException var4) {
            throw new RuntimeException(var4);
        }
    }

    public ObservableList GetPourcentageDatasGraphique1() throws SQLException {
        ObservableList<Matiere> datas = FXCollections.observableArrayList();
        int idUser = servicesUsers.getIdUser();

        try {
            ps = uneCnx.prepareStatement("SELECT matiere.designation,\n" +
                    "       ROUND((COUNT(demande.id) * 100.0 / total_demandes), 2) AS pourcentage\n" +
                    "FROM demande\n" +
                    "JOIN matiere ON matiere.id = demande.id_matiere\n" +
                    "JOIN (SELECT COUNT(id) AS total_demandes FROM demande WHERE id_user = ?) AS total_demandes_table\n" +
                    "WHERE demande.id_user = ?\n" +
                    "GROUP BY matiere.designation;\n");
            ps.setInt(1, idUser);
            ps.setInt(2, idUser);
            rs = ps.executeQuery();

            while(rs.next()) {
                Matiere matiere = new Matiere(rs.getString(1), rs.getDouble(2));
                datas.add(matiere);
            }

            rs.close();
            return datas;
        } catch (SQLException var4) {
            throw new RuntimeException(var4);
        }
    }


    // Le nombre de soutiens réalisés par un utilisateur pour chaque matière (un soutien est réalisé lorsque le statut est à 3 donc statut = salle attribuée)*

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

    // Nombre de soutiens acceptés par niveaux

    public HashMap<String, Integer> GetDatasGraphique3() throws SQLException {
        HashMap<String, Integer> datas = new HashMap();
        int idUser = servicesUsers.getIdUser();

        try {
            ps = uneCnx.prepareStatement("SELECT niveau.designation AS niveau, COUNT(soutien.id) AS nombre_soutiens_acceptes\n" +
                    "FROM niveau\n" +
                    "INNER JOIN user ON niveau.id = user.id_niveau\n" +
                    "INNER JOIN demande ON user.id = demande.id_user\n" +
                    "INNER JOIN soutien ON demande.id = soutien.id_demande\n" +
                    "WHERE soutien.id_competence IN (SELECT competence.id FROM competence WHERE competence.id_user = ?) AND soutien.status IN (2, 3) AND demande.status IN (2, 3)\n" +
                    "GROUP BY niveau.designation;\n");

            ps.setInt(1, idUser);
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

    public HashMap<String, Integer> GetDatasGraphique4() throws SQLException {
        HashMap<String, Integer> datas = new HashMap();

        try {
            ps = uneCnx.prepareStatement("SELECT niveau.designation AS niveau, COUNT(demande.id) AS nombre_demandes\n" +
                    "FROM demande\n" +
                    "INNER JOIN user ON demande.id_user = user.id\n" +
                    "INNER JOIN niveau ON user.id_niveau = niveau.id\n" +
                    "GROUP BY niveau.designation;");
            rs = ps.executeQuery();

            while(rs.next()) {
                datas.put(rs.getString(1), rs.getInt(2));
            }

            rs.close();
            return datas;
        } catch (SQLException var4) {
            throw new RuntimeException(var4);
        }
    }


    // Le nombre de soutiens réalisés par un utilisateur pour chaque matière (un soutien est réalisé lorsque le statut est à 3 donc statut = salle attribuée)*

    public HashMap<String, Integer> GetDatasGraphique5() throws SQLException {
        HashMap<String, Integer> datas = new HashMap();

        try {
            ps = uneCnx.prepareStatement("SELECT CONCAT(u.nom, ' ', u.prenom) AS nom_prenom, COUNT(s.id) AS nombre_soutiens\n" +
                    "FROM user u\n" +
                    "INNER JOIN competence c ON u.id = c.id_user\n" +
                    "INNER JOIN soutien s ON c.id = s.id_competence\n" +
                    "GROUP BY u.id\n" +
                    "ORDER BY nombre_soutiens DESC\n" +
                    "LIMIT 10;\n");
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

    public HashMap<String, Integer> GetDatasGraphique6() throws SQLException {
        HashMap<String, Integer> datas = new HashMap();

        try {
            ps = uneCnx.prepareStatement("SELECT sous_matiere, COUNT(*) AS nombres_sollicites\n" +
                    "FROM (\n" +
                    "    SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(c.sous_matiere, '#', numbers.n), '#', -1) AS sous_matiere\n" +
                    "    FROM competence c\n" +
                    "    JOIN (\n" +
                    "        SELECT 1 + units.i + tens.i * 10 AS n\n" +
                    "        FROM\n" +
                    "            (SELECT 0 AS i UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS units\n" +
                    "            CROSS JOIN\n" +
                    "            (SELECT 0 AS i UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS tens\n" +
                    "    ) AS numbers\n" +
                    "    ON CHAR_LENGTH(c.sous_matiere) - CHAR_LENGTH(REPLACE(c.sous_matiere, '#', '')) >= numbers.n - 1\n" +
                    "\n" +
                    "    UNION ALL\n" +
                    "\n" +
                    "    SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(d.sous_matiere, '#', numbers.n), '#', -1) AS sous_matiere\n" +
                    "    FROM demande d\n" +
                    "    JOIN (\n" +
                    "        SELECT 1 + units.i + tens.i * 10 AS n\n" +
                    "        FROM\n" +
                    "            (SELECT 0 AS i UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS units\n" +
                    "            CROSS JOIN\n" +
                    "            (SELECT 0 AS i UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS tens\n" +
                    "    ) AS numbers\n" +
                    "    ON CHAR_LENGTH(d.sous_matiere) - CHAR_LENGTH(REPLACE(d.sous_matiere, '#', '')) >= numbers.n - 1\n" +
                    ") AS submatieres\n" +
                    "WHERE sous_matiere <> ''  -- Filtrer les sous-matières vides\n" +
                    "GROUP BY sous_matiere\n" +
                    "ORDER BY nombres_sollicites DESC\n" +
                    "LIMIT 20;\n");
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

    public ObservableList GetPourcentageDatasGraphique5() throws SQLException {
        ObservableList<User> datas = FXCollections.observableArrayList();
        try {
            ps = uneCnx.prepareStatement("SELECT CONCAT(u.nom, ' ', u.prenom) AS nom_prenom, \n" +
                    "       ROUND(COUNT(s.id) * 100.0 / totalSoutiens, 2) AS pourcentage_soutiens\n" +
                    "FROM user u\n" +
                    "INNER JOIN competence c ON u.id = c.id_user\n" +
                    "INNER JOIN soutien s ON c.id = s.id_competence\n" +
                    "CROSS JOIN (SELECT COUNT(*) AS totalSoutiens FROM soutien) total\n" +
                    "GROUP BY u.id\n" +
                    "ORDER BY pourcentage_soutiens DESC\n" +
                    "LIMIT 10;\n");
            rs = ps.executeQuery();


            while(rs.next()) {
                User user = new User(rs.getString(1), rs.getDouble(2));
                datas.add(user);
            }

            rs.close();
            return datas;
        } catch (SQLException var3) {
            throw new RuntimeException(var3);
        }
    }

}