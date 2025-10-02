package Finance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL_DB {

	protected static String name; //This part took 3 days, and so much trial and error
	//protected static String id; //This part ended up being the alternative method i used temporarily while i solved the issue with the while loop. ID ended up being irrelevant.

	public static ResultSet ReadFromDB(String user_input) throws SQLException {

		//Connects to Database
		//public static Connection dataConnection()
		String driver = "jdbc:sqlite";
		String db = "db/UMS_Assignment.db"; 
		String url = driver + ":" + db;
		Connection c = DriverManager.getConnection(url);
		

		//Shows Connection Successful
		System.out.println("Successfully Connected to Database");

		//SELECT ALL FROM INSTITUTION WHERE LEGAL_NAME LIKE "%user_input%" 		//SQL Statement (I took this note in class, no idea what this means)
		String sql = "SELECT * FROM INVOICES WHERE Student_Name LIKE '%" + user_input + "%'";
		//String sql2 = "SELECT * FROM INSTITUTION WHERE LEGAL_NAME LIKE '%" + user_input + "%'";
		PreparedStatement statement = c.prepareStatement(sql);
		ResultSet results = statement.executeQuery();
		
		
		//Prints name in console
		//while (results.next()) {
			//System.out.println("inside the loop");
			//name = results.getString("LEGAL_NAME"); //THIS PART TOOK ME 5 DAYS, DO NOT UNCOMMENT THIS PLEASE
			//id = results.getString("UKPRN");
		//}
		return results; 
	}
	
		public static Connection dataConnection() throws SQLException {
			String driver = "jdbc:sqlite";
			String db = "db/UMS_Assignment.db"; 
			String url = driver + ":" + db;
			Connection c = DriverManager.getConnection(url);
			return c;
			}
	
	public static PreparedStatement Invoice (Connection conn) throws SQLException{
		String sqlInvoice = "INSERT INTO INVOICES (Student_Name, Course_Costs, Sports_Costs, Food_Costs, Date_Of_Invoice) VALUES (?, ?, ?, ?, ?)";
		return conn.prepareStatement(sqlInvoice);
	}
}