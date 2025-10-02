package Finance;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.Dimension;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import java.awt.Button;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Canvas;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.List;
import javax.swing.BoxLayout;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchField;
	private JButton btnNewButton;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GUI frame = new GUI();
		frame.setVisible(true);
	}

	/**
	 * Create the frame: for GUI
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1264, 600); // where on the screen it opens, resolution
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setPreferredSize(new Dimension(2000, 1400)); // adjust height to fit all content

		JScrollPane pageScrollPane = new JScrollPane(contentPane);
		setContentPane(pageScrollPane);
		
		
		JButton btnNewButton_1 = new JButton("Add Invoice");
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI3 addInvoice = new GUI3();
				addInvoice.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(26, 944, 204, 87);
		contentPane.add(btnNewButton_1);
		
		JPanel panelBackground = new JPanel();
		panelBackground.setBounds(267, 164, 1621, 867);
		contentPane.add(panelBackground);
		panelBackground.setLayout(new BoxLayout(panelBackground, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panelBackground.add(scrollPane);
		
		searchField = new JTextField();
		searchField.setToolTipText("Search");
		searchField.setBounds(1270, 60, 462, 50);
		contentPane.add(searchField);
		searchField.setColumns(10);
		JButton searchButton = new JButton("Search");
		searchButton.setContentAreaFilled(false);
		searchButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
		table = new JTable();
		//table.setColumnSelectionAllowed(true); //Keep this disabled, allows me to select the row
		table.getTableHeader().setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		String[] columnNames = {"Student Name", "Date" ,"Course Costs" ,"Sports Costs" ,"Food Costs"};
		DefaultTableModel TableModel = new DefaultTableModel(null, columnNames);
		table.setModel(TableModel);
		table.setDragEnabled(false);
		table.setRowHeight(27);
		scrollPane.setViewportView(table);
				
//SQL_DB: Getting an output in the table
				boolean hasResults = false; //Error Checking
				try 
				{
					ResultSet results = SQL_DB.ReadFromDB(searchField.getText());
					
					//panelBackground.removeAll(); //This line removes the panel
		            panelBackground.revalidate();
		            panelBackground.repaint();

		            	while (results.next()) {
		            		String Name = results.getString("Student_Name");
							String CourseCost = results.getString("Course_Costs");
							String SportsCost = results.getString("Sports_Costs");
							String FoodCost = results.getString("Food_Costs");
							String DateOfInvoice = results.getString("Date_Of_Invoice");
							
							hasResults = true;
							
							//JTextArea label = new JTextArea(Name + (" ") + CourseCost + (" ") + SportsCost + (" ") + FoodCost + (" ") + DateOfInvoice);
							//label.setEditable(false);
							//panelBackground.add(label);
							
							Object[] row = {Name, DateOfInvoice, CourseCost, SportsCost, FoodCost};
							TableModel.addRow(row);
							
							//System.out.println(Name + (" ") + CourseCost + (" ") + SportsCost + (" ") + FoodCost + (" ") + DateOfInvoice); //Prints in console, Testing Output
		            	}
						
						if (!hasResults) { //Error Checking
							new ErrorMessage();
							System.out.println("There was no information found for the searched Query. Check if the searched term exists, or try a different term");
						}
				
					panelBackground.revalidate();
		            panelBackground.repaint();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		searchButton.setBounds(1744, 61, 144, 50);
		contentPane.add(searchButton);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Finances - Dashboard");
		lblNewJgoodiesTitle.setFont(new Font("Lucida Grande", Font.BOLD, 48));
		lblNewJgoodiesTitle.setBounds(66, 34, 719, 102);
		contentPane.add(lblNewJgoodiesTitle);
		
		JPanel titleBackground = new JPanel();
		titleBackground.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		titleBackground.setBackground(new Color(217, 217, 217));
		titleBackground.setBounds(26, 34, 1875, 102);
		contentPane.add(titleBackground);
		
		//This is the Panel with options to navigate to other courses. This hasn't been fully implemented yet, but will be in the future.
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(new Color(217, 217, 217));
		panel_1.setBounds(26, 148, 204, 784);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JTextPane txtpnSelectAnOption = new JTextPane();
		txtpnSelectAnOption.setEditable(false);
		txtpnSelectAnOption.setBackground(Color.LIGHT_GRAY);
		txtpnSelectAnOption.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtpnSelectAnOption.setBounds(6, 6, 192, 68);
		txtpnSelectAnOption.setText("Select an option below to navigate to a different section");
		panel_1.add(txtpnSelectAnOption);
		
		JButton CourseSelectionButton = new JButton("Course Selection");
		CourseSelectionButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		CourseSelectionButton.setBounds(0, 86, 204, 82);
		panel_1.add(CourseSelectionButton);
		
		JButton ApplicantDetailsButton = new JButton("Applicant Details");
		ApplicantDetailsButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		ApplicantDetailsButton.setBounds(1, 180, 203, 82);
		panel_1.add(ApplicantDetailsButton);
		
		JButton AttendanceMonitoringButton = new JButton("Attendance Monitoring");
		AttendanceMonitoringButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		AttendanceMonitoringButton.setBounds(1, 274, 203, 82);
		panel_1.add(AttendanceMonitoringButton);
		
		JButton SportsSchoolButton = new JButton("Sports School");
		SportsSchoolButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		SportsSchoolButton.setBounds(1, 368, 203, 82);
		panel_1.add(SportsSchoolButton);
		
		JButton RestaurantButton = new JButton("Restaurant");
		RestaurantButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		RestaurantButton.setBounds(1, 462, 203, 82);
		panel_1.add(RestaurantButton);
		
		JButton FinancesButton = new JButton("Finances");
		FinancesButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		FinancesButton.setBounds(0, 556, 204, 82);
		panel_1.add(FinancesButton);
	}
}