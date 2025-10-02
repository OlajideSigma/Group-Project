package Attendance;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchField;
	private JFrame frame;
	private JTable resultsTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentPage frame = new StudentPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1262, 696);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(213, 243, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		Menubar sidebar = new Menubar(this);
		sidebar.setBounds(5, 5, 236, 653);
		contentPane.add(sidebar);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		initialize(); // Call the search UI setup
		}

		private void initialize() {
			JPanel searchPanel = new JPanel();
			searchPanel.setBounds(327, 113, 840, 35);
	        searchPanel.setLayout(null);

	        searchField = new JTextField(20);
	        searchField.setBounds(97, 9, 581, 20);
	        searchPanel.add(searchField);

	        JButton searchButton = new JButton("Search");
	        searchButton.setBounds(688, 5, 142, 23);
	        searchPanel.add(searchButton);

	        contentPane.add(searchPanel); // Add to the top
	        
	        JLabel lblStudentId = new JLabel("Student ID:");
	        lblStudentId.setBounds(10, 9, 77, 20);
	        searchPanel.add(lblStudentId);

	        // Create table for results
	        resultsTable = new JTable();
	        JScrollPane scrollPane = new JScrollPane(resultsTable);
	        scrollPane.setBounds(327, 159, 840, 390);
	        contentPane.add(scrollPane); // Add to the center
	        
	        JLabel lblStudentAttendance = new JLabel("Student Attendance");
	        lblStudentAttendance.setForeground(new Color(0, 0, 128));
	        lblStudentAttendance.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
	        lblStudentAttendance.setBounds(327, 29, 236, 52);
	        contentPane.add(lblStudentAttendance);
	        
	        JLabel lblAttendancePercentage = new JLabel("Total Attendance: ");
	        lblAttendancePercentage.setFont(new Font("Arial", Font.BOLD, 16));
	        lblAttendancePercentage.setBounds(327, 560, 300, 30);
	        contentPane.add(lblAttendancePercentage);
	        
	        

	        // Action Listener for search button
	        searchButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try {
	                	 DefaultTableModel model = StudentPage2.performSearch(searchField.getText());
	                     resultsTable.setModel(model);
	                     
	                  // Calculate the attendance percentage based on the data in the table
	                     double attendancePercentage = calculateAttendancePercentage(model);
	                     lblAttendancePercentage.setText("Total Attendance for student: " + String.format("%.2f", attendancePercentage) + "%");
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	            }
	        });
	    
	}
		
		
		//Calculate attendance percentage
	    public double calculateAttendancePercentage(DefaultTableModel model) {
	        int attendedCount = 0;
	        int totalSessions = model.getRowCount();

	        // Loop through the table data and count "Yes" entries in the "Attendance" column (index 2)
	        for (int i = 0; i < totalSessions; i++) {
	            String attendanceStatus = (String) model.getValueAt(i, 2);  // Attendance column is index 2
	            if ("Yes".equals(attendanceStatus)) {
	                attendedCount++;
	            }
	        }
	        
	        if (totalSessions == 0) {
	            return 0.0;  // Avoid division by zero if there are no sessions
	        }

	        return (attendedCount / (double) totalSessions) * 100;

		}
}
