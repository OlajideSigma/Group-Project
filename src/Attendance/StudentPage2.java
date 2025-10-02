package Attendance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
public class StudentPage2 {
	static String driver = "jdbc:sqlite";
	static String db = "db/ums2.db"; 
	static String url = driver + ":" + db;
    
    public static DefaultTableModel performSearch(String input)throws SQLException {
		// TODO Auto-generated method stub
				
				Connection c = DriverManager.getConnection(url);
				
				 String sql = "SELECT ATTENDANCE.StudentID, ATTENDANCE.CourseID, " +
	                     "KISCOURSE.TITLE, ATTENDANCE.SessionDate, ATTENDANCE.Attendance " +
	                     "FROM ATTENDANCE " +
	                     "INNER JOIN KISCOURSE ON ATTENDANCE.CourseID = KISCOURSE.KISCOURSEID " +
	                     "WHERE ATTENDANCE.StudentID LIKE ?";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, "%" + input + "%");  // Search for StudentID
				ResultSet results = ps.executeQuery();
				
				// Create a DefaultTableModel to hold the results
		        DefaultTableModel model = new DefaultTableModel();
		        model.addColumn("Student ID");
		        model.addColumn("Course Name");
		        model.addColumn("Attendance");
		        model.addColumn("Session Date");
		        

				
				boolean studentFound = false;
				while(results.next())
				{
					studentFound = true;
					String studentID = results.getString("StudentID");
					String courseName = results.getString("TITLE");
					String attendStatus = results.getString("Attendance");
					String date = results.getString("SessionDate");
					model.addRow(new Object[]{studentID, courseName, attendStatus, date}); // Add row to the table model
					

			        
					System.out.println(studentID +" has an attendance of "+ attendStatus +" in the course "+courseName+  " on " + date);
				}
				
				if (!studentFound) {
			        System.out.println("Error: Student not found.");
			        JOptionPane.showMessageDialog(null, "Error: Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
			    }
				
				 results.close();
			     ps.close();
			     c.close();

			     return model;
				  	    
	}
    

}




