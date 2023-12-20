package sio.hlr.Tools;

import java.sql.SQLException;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.hlr.Entities.Matiere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ServicesStatistiques {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;
    ServicesUsers servicesUsers = new ServicesUsers();

    public HashMap<String, Double> GetDatasGraphique1(String nomUser) {
        HashMap<String, Double> datas = new HashMap();

        try {
            uneCnx = ConnexionBDD.getCnx();
            ps = uneCnx.prepareStatement("");
            ps.setString(1, nomUser);
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
}
