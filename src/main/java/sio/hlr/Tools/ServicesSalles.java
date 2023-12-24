package sio.hlr.Tools;

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
}
