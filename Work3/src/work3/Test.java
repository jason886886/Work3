package work3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author jason
 */
public class Test {
    public static void main(String[] args) {
        Connection con = null;
        Statement stat = null;
        ResultSet res = null;
        try {
            con = JdbcUtils.getConnection();
            stat = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
