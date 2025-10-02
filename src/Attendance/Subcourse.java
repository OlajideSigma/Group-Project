package Attendance;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;

import java.awt.*;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Subcourse extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
    private String courseIDInSubcourse;
	
	// Database connection details
    static String driver = "jdbc:sqlite";
    static String db = "db/ums2.db"; 
    static String url = driver + ":" + db;



	/**
	 * Create the frame.
	 * @param data 
	 */
	public Subcourse(String courseID) {
		
		this.courseIDInSubcourse = courseID;

        setTitle("Course Attendance - " + courseIDInSubcourse);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1264, 697);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(213, 243, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("New label");
		label.setFont(new Font("Yu Gothic Medium", Font.BOLD, 25));
		label.setBounds(204, 122, 842, 75);
		label.setText("ATTENDANCE BREAKDOWN FOR COURSE: " + courseID);
		contentPane.add(label);
		
		 // Table
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(204, 208, 750, 300);
        contentPane.add(scrollPane);

        // Load attendance data for this course
        loadCourseAttendance();
	}
	
	private void loadCourseAttendance() {
	    try (Connection c = DriverManager.getConnection(url)) {
	        String sql = "SELECT SessionDate, " +
	                "SUM(CASE WHEN Attendance = 'Yes' THEN 1 ELSE 0 END) AS TotalPresent, " +
	                "SUM(CASE WHEN Attendance = 'Late' THEN 1 ELSE 0 END) AS TotalLate, " +
	                "SUM(CASE WHEN Attendance = 'Absent' THEN 1 ELSE 0 END) AS TotalAbsent, " +
	                "ROUND(100.0 * SUM(CASE WHEN Attendance = 'Yes' THEN 1 ELSE 0 END) / COUNT(*), 2) AS AttendancePercentage " +
	                "FROM ATTENDANCE " +
	                "WHERE CourseID = ? " +
	                "GROUP BY SessionDate " +
	                "ORDER BY SessionDate ASC";

	        PreparedStatement ps = c.prepareStatement(sql);
	        ps.setString(1, courseIDInSubcourse);
	        ResultSet rs = ps.executeQuery();

	        // Create Table Model
	        DefaultTableModel model = new DefaultTableModel();
	        model.addColumn("Session Date");
	        model.addColumn("Total Present");
	        model.addColumn("Total Late");
	        model.addColumn("Total Absent");
	        model.addColumn("Attendance %");
	        model.addColumn("View Chart");

	        // Add data to table
	        while (rs.next()) {
	            Object[] rowData = {
	                    rs.getString("SessionDate"),
	                    rs.getInt("TotalPresent"),
	                    rs.getInt("TotalLate"),
	                    rs.getInt("TotalAbsent"),
	                    rs.getDouble("AttendancePercentage"),
	                    "View Chart"  // Placeholder for button
	            };
	            model.addRow(rowData);
	        }

	        table.setModel(model);
	        table.getColumn("View Chart").setCellRenderer(new ButtonRenderer());
	        table.getColumn("View Chart").setCellEditor(new ButtonEditor(new JCheckBox(), this));

	        rs.close();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error fetching course attendance.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
    private void showChart(String sessionDate, int present, int late, int absent) {
        JFrame chartFrame = new JFrame("Attendance Chart for " + sessionDate);
        chartFrame.setSize(800, 600);
        chartFrame.setLayout(new GridLayout(1, 2));

        JFreeChart pieChart = createPieChart(present, late, absent);
        JFreeChart barChart = createBarChart(present, late, absent);

        chartFrame.add(new ChartPanel(pieChart));
        chartFrame.add(new ChartPanel(barChart));

        chartFrame.setVisible(true);
    }
    
    private JFreeChart createPieChart(int present, int late, int absent) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Present", present);
        dataset.setValue("Late", late);
        dataset.setValue("Absent", absent);

        JFreeChart chart = ChartFactory.createPieChart("Attendance Distribution", dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Present", new Color(76, 159, 112));  // Soft Teal
        plot.setSectionPaint("Late", new Color(230, 161, 87));  // Warm Gold
        plot.setSectionPaint("Absent", new Color(214, 106, 106));  // Muted Red
        
        plot.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator(
        		"{0} = {1} ({2})"));
        return chart;
    }

    private JFreeChart createBarChart(int present, int late, int absent) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(present, "Present", "Attendance");
        dataset.addValue(late, "Late", "Attendance");
        dataset.addValue(absent, "Absent", "Attendance");

        JFreeChart chart = ChartFactory.createBarChart("Attendance Breakdown", "Category", "Count", dataset);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        
        renderer.setSeriesPaint(0, new Color(76, 159, 112));  
        renderer.setSeriesPaint(1, new Color(230, 161, 87));  
        renderer.setSeriesPaint(2, new Color(214, 106, 106));  
        return chart;
    }
    
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("View Chart");
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Update button appearance (e.g., change color when selected or hovered)
            setBackground(isSelected ? Color.CYAN : Color.WHITE);
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String sessionDate;
        private int present, late, absent;
        private Subcourse parentFrame;

        public ButtonEditor(JCheckBox checkBox, Subcourse parent) {
            super(checkBox);
            this.parentFrame = parent;
            button = new JButton("View Chart");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    parentFrame.showChart(sessionDate, present, late, absent);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            sessionDate = table.getValueAt(row, 0).toString();
            present = Integer.parseInt(table.getValueAt(row, 1).toString());
            late = Integer.parseInt(table.getValueAt(row, 2).toString());
            absent = Integer.parseInt(table.getValueAt(row, 3).toString());
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "View Chart"; // Editor value is fixed as "View Chart"
        }
    }
}

