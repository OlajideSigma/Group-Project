package Attendance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import javax.swing.*;
public class CoursePage2 {
	static String driver = "jdbc:sqlite";
	static String db = "db/ums2.db"; 
	static String url = driver + ":" + db;
    
    public static DefaultTableModel courseSearch(String courseID, String startDate, String endDate)throws SQLException {
		// TODO Auto-generated method stub
				
				Connection c = DriverManager.getConnection(url);
				
				String sql = "SELECT ATTENDANCE.StudentID, ATTENDANCE.CourseID, KISCOURSE.TITLE, " +
		                 "INSTITUTION.LEGAL_NAME, ATTENDANCE.SessionDate, ATTENDANCE.Attendance " +
		                 "FROM ATTENDANCE " +
		                 "INNER JOIN KISCOURSE ON ATTENDANCE.CourseID = KISCOURSE.KISCOURSEID " +
		                 "INNER JOIN INSTITUTION ON KISCOURSE.UKPRN = INSTITUTION.UKPRN " +
		                 "WHERE ATTENDANCE.CourseID LIKE ?";
				
				
				// Add date filtering if both dates are provided
		        if (startDate != null && endDate != null) {
		            sql += " AND ATTENDANCE.SessionDate BETWEEN ? AND ?";
		        }
				PreparedStatement ps = c.prepareStatement(sql);
				 ps.setString(1, courseID + "%");
				    // Set date parameters if provided
				    if (startDate != null && endDate != null) {
				        ps.setString(2, startDate); // Pass the start date as a string
				        ps.setString(3, endDate);   // Pass the end date as a string
				    }
				ResultSet results = ps.executeQuery();
				
				// Create a DefaultTableModel to hold the results
		        DefaultTableModel model = new DefaultTableModel();
		        model.addColumn("Course ID");
		        model.addColumn("Course Name");
		        model.addColumn("Institution");
		        model.addColumn("Attendance");
		        model.addColumn("Session Date");
		        

				
				boolean courseFound = false;
				while(results.next())
				{
					courseFound = true;
					String coursID = results.getString("CourseID");
					String courseName = results.getString("TITLE");
					String institution = results.getString("LEGAL_NAME");
					String attendStatus = results.getString("Attendance");
					String date = results.getString("SessionDate");
			        // Convert SQL Date to String (dd-MM-yyyy format)


					model.addRow(new Object[]{coursID, courseName, institution, attendStatus, date}); // Add row to the table model

					System.out.println(coursID +" has an attendance of "+ attendStatus +" in the course "+courseName+  " on " + date);
				}
				
				if (!courseFound) {
			        System.out.println("Error: Student not found.");
			        JOptionPane.showMessageDialog(null, "Error: Course not found.", "Error", JOptionPane.ERROR_MESSAGE);
			    }
				
				 results.close();
			     ps.close();
			     c.close();

			     return model;
    }		  	    

}
