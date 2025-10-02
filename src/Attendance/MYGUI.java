package Attendance;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JDesktopPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;



public class MYGUI extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox<String> institutionDropdown;
	JLabel lblPresentCount; 
	JLabel lblAbsentCount;
	JLabel lblLateCount;
	JPanel chartPanel;
	
	// Inside MYGUI class
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MYGUI frame = new MYGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MYGUI() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1261, 695);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(213, 243, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		Menubar sidebar = new Menubar(this);
		sidebar.setBounds(5, 5, 236, 653);
		contentPane.add(sidebar);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setForeground(new Color(0, 0, 128));
		lblDashboard.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		lblDashboard.setBounds(261, 5, 236, 52);
		contentPane.add(lblDashboard);
		
	    // Institution Dropdown
	    JLabel lblInstitution = new JLabel("Select Institution:");
	    lblInstitution.setBounds(672, 19, 120, 30);
	    contentPane.add(lblInstitution);

	    institutionDropdown = new JComboBox<>();
	    institutionDropdown.setBounds(805, 19, 200, 30);
	    contentPane.add(institutionDropdown);

	    populateInstitutionDropdown();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(261, 57, 964, 137);
		contentPane.add(panel);

        
		JLabel lblAbsent = new JLabel("Total Absent: ");
        lblAbsent.setBounds(316, 9, 107, 119);
        lblAbsent.setFont(new Font("Arial", Font.BOLD, 16));
        lblAbsentCount = new JLabel("Loading...");
        lblAbsentCount.setBounds(433, 9, 135, 119);
        lblAbsentCount.setFont(new Font("Arial", Font.PLAIN, 16));
	    panel.setLayout(null);
        
        JLabel lblPresent = new JLabel("Total Present: ");
        lblPresent.setBounds(10, 9, 111, 119);
        lblPresent.setFont(new Font("Arial", Font.BOLD, 16));
        lblLateCount = new JLabel("Loading...");
        lblLateCount.setBounds(720, 11, 135, 115);
        lblLateCount.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JLabel lblLate = new JLabel("Total Late: ");
        lblLate.setBounds(599, 9, 111, 119);
        lblLate.setFont(new Font("Arial", Font.BOLD, 16));
        lblPresentCount = new JLabel("Loading...");
        lblPresentCount.setBounds(130, 11, 176, 115);
        lblPresentCount.setFont(new Font("Arial", Font.PLAIN, 16));
        
        panel.add(lblPresent);
        panel.add(lblAbsent);
        panel.add(lblLate);
        panel.add(lblAbsentCount);
        panel.add(lblLateCount);
        panel.add(lblPresentCount);
        
        
                
        // Fetch attendance data from DB

        
        JButton btnNewButton_1 = new JButton("IMPORT CSV");
        btnNewButton_1.setBounds(1074, 17, 151, 34);
        contentPane.add(btnNewButton_1);
        btnNewButton_1.addActionListener(e -> importCSV());



        
        chartPanel = new JPanel();
        chartPanel.setBounds(261, 210, 964, 448);  // Adjust the position and size as needed
        chartPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        chartPanel.setBackground(Color.WHITE);
        contentPane.add(chartPanel);

        institutionDropdown.addActionListener(e -> updateChartAndSummary());
        
        setVisible(true);
	}
	private void importCSV() {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Select CSV File");
	    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV Files", "csv"));

	    int userSelection = fileChooser.showOpenDialog(this);

	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();

	        // Ensure the file has a .csv extension
	        if (!selectedFile.getName().toLowerCase().endsWith(".csv")) {
	            JOptionPane.showMessageDialog(this, "Invalid file type! Please select a CSV file.", "File Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        MyGUI2 exampleObj = new MyGUI2();
	        boolean success = exampleObj.processCSV(selectedFile);

	        if (success) {
	            JOptionPane.showMessageDialog(this, "CSV Imported Successfully!");
	        } else {
	            JOptionPane.showMessageDialog(this, "Some rows failed to import. Please check the console log for skipped rows or invalid data.", "Partial Import", JOptionPane.WARNING_MESSAGE);
	        }
	    }
	}
	
	private void populateInstitutionDropdown() {
	    try {
	        institutionDropdown.addItem("All Institutions");
	        for (String institution : MyGUI2.fetchInstitutionNames()) {
	            institutionDropdown.addItem(institution);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error fetching institutions.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	private void updateChartAndSummary() {
	    try {
	        String selectedInstitution = institutionDropdown.getSelectedItem().toString();
	        int[] attendanceCounts;

	        if (selectedInstitution.equals("All Institutions")) {
	            attendanceCounts = MyGUI2.fetchAttendanceData(null);
	        } else {
	            attendanceCounts = MyGUI2.fetchAttendanceData(selectedInstitution);
	        }

	        lblPresentCount.setText("Present: " + attendanceCounts[0]);
	        lblAbsentCount.setText("Absent: " + attendanceCounts[1]);
	        lblLateCount.setText("Late: " + attendanceCounts[2]);

	        JFreeChart chart = MyGUI2.createBarChart(attendanceCounts[0], attendanceCounts[1], attendanceCounts[2]);
	        chartPanel.removeAll();
	        chartPanel.add(new ChartPanel(chart));
	        chartPanel.revalidate();
	        chartPanel.repaint();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error updating data.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

  
}
