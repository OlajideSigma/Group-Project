package Attendance;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
public class MyGUI2 {
	static String driver = "jdbc:sqlite";
	static String db = "db/ums2.db";
	static String url = driver + ":" + db;

	
	public static int[] fetchAttendanceData(String institution) throws SQLException {
	    int present = 0, absent = 0, late = 0;
	    String sql = "SELECT Attendance, COUNT(*) as count FROM ATTENDANCE " +
	                 "INNER JOIN KISCOURSE ON ATTENDANCE.CourseID = KISCOURSE.KISCOURSEID " +
	                 "INNER JOIN INSTITUTION ON KISCOURSE.UKPRN = INSTITUTION.UKPRN";

	    if (institution != null) {
	        sql += " WHERE INSTITUTION.LEGAL_NAME = ?";
	    }

	    sql += " GROUP BY Attendance";

	    try (Connection c = DriverManager.getConnection(url);
	         PreparedStatement ps = c.prepareStatement(sql)) {
	        if (institution != null) {
	            ps.setString(1, institution);
	        }
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            String status = rs.getString("Attendance");
	            int count = rs.getInt("count");

	            switch (status) {
	                case "Yes": present = count; break;
	                case "Absent": absent = count; break;
	                case "Late": late = count; break;
	            }
	        }
	    }
	    return new int[]{present, absent, late};
	}

	
	public static JFreeChart createBarChart(int present, int absent, int late) {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    
	    // Use different series names instead of grouping all under "Attendance"
	    dataset.addValue(present, "Present", "Category");
	    dataset.addValue(absent, "Absent", "Category");
	    dataset.addValue(late, "Late", "Category");

	    JFreeChart barChart = ChartFactory.createBarChart(
	            "Attendance Report",
	            "Status",
	            "Count",
	            dataset
	    );

	    // Get the plot and set custom colors
	    CategoryPlot plot = (CategoryPlot) barChart.getPlot();
	    BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    
	    // Set custom colors for each series (matching their index)
	    renderer.setSeriesPaint(0, new Color(76, 159, 112));  // Green for Present
	    renderer.setSeriesPaint(1, new Color(214, 106, 106));    // Red for Absent
	    renderer.setSeriesPaint(2, new Color(230, 161, 87));  // Yellow for Late
	    
	    LegendTitle legend = barChart.getLegend();
	    legend.setPosition(RectangleEdge.BOTTOM);
	    barChart.getLegend().setVisible(true);

	    return barChart;
	}
	
	public boolean processCSV(File csvFile) {
	    boolean allSuccess = true;
	    int rowsInserted = 0;
	    int rowsSkipped = 0;

	    try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
	         Connection conn = DriverManager.getConnection(url)) {  // No login details

	        conn.setAutoCommit(false);  // Start transaction

	        String sql = "INSERT INTO ATTENDANCE (StudentID, CourseID, SessionDate, Attendance) VALUES (?, ?, ?, ?)";

	        try (PreparedStatement ps = conn.prepareStatement(sql)) {

	            String line;
	            br.readLine();  // Skip header row

	            int lineNumber = 1;
	            while ((line = br.readLine()) != null) {
	                lineNumber++;

	                String[] data = line.split(",");
	                if (data.length != 4) {
	                    System.out.println("Invalid CSV format at line " + lineNumber + ": " + line);
	                    rowsSkipped++;
	                    allSuccess = false;
	                    continue;
	                }

	                // Trim and validate data
	                String studentID = data[0].trim();
	                String courseID = data[1].trim();
	                String sessionDate = data[2].trim();
	                String attendanceStatus = data[3].trim();

	                if (studentID.isEmpty() || courseID.isEmpty() || sessionDate.isEmpty() || attendanceStatus.isEmpty()) {
	                    System.out.println("Empty field at line " + lineNumber);
	                    rowsSkipped++;
	                    allSuccess = false;
	                    continue;
	                }

	                // Insert data
	                ps.setString(1, studentID);
	                ps.setString(2, courseID);
	                ps.setString(3, sessionDate);
	                ps.setString(4, attendanceStatus);
	                ps.addBatch();
	                rowsInserted++;

	                if (rowsInserted % 100 == 0) {
	                    ps.executeBatch();
	                }
	            }

	            ps.executeBatch();
	            conn.commit();

	            System.out.println("Rows Inserted: " + rowsInserted);
	            System.out.println("Rows Skipped: " + rowsSkipped);

	        } catch (SQLException e) {
	            conn.rollback();
	            System.out.println("SQL Error: " + e.getMessage());
	            allSuccess = false;
	        }

	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	        allSuccess = false;
	    }

	    System.out.println("Import Complete - Rows Inserted: " + rowsInserted + ", Rows Skipped: " + rowsSkipped);
	    return allSuccess;
	}
	
	public static List<String> fetchInstitutionNames() throws SQLException {
	    List<String> institutions = new ArrayList<>();
	    String sql = "SELECT DISTINCT LEGAL_NAME FROM INSTITUTION";

	    try (Connection c = DriverManager.getConnection(url);
	         PreparedStatement ps = c.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        while (rs.next()) {
	            institutions.add(rs.getString("LEGAL_NAME"));
	        }
	    }
	    return institutions;
	}
	

}
