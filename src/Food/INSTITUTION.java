package Food;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
		import java.sql.SQLException;

		public class INSTITUTION {



		        public static ResultSet ReadFromDB (String input) throws SQLException {
		                String driver = "jdbc:sqlite";
		                String db = "db/singh.db";
		                String url = driver + ":" + db;
		                Connection c = DriverManager.getConnection(url);

		                System.out.println("Made connection to db");

		                String sql = "SELECT * FROM INSTITUTION WHERE LEGAL_NAME Like " + " '% " + input + "%'";
		                PreparedStatement ps = c.prepareStatement(sql);
		                ResultSet results = ps.executeQuery();

		               
		                return results;
		        }
		

	}


