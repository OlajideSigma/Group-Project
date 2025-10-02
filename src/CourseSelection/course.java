package CourseSelection;
import java.sql.*;

public class course {
    public static ResultSet ReadFromDB(String query) throws SQLException {
        String url = "jdbc:sqlite:db/UMS.db";
        Connection c = DriverManager.getConnection(url);
        PreparedStatement ps = c.prepareStatement(query);
        return ps.executeQuery();
    }
}
