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
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.swing.JOptionPane;

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
import javax.swing.JFormattedTextField;

public class GUI3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GUI frame = new GUI();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public GUI3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080); // where on the screen it opens, resolution
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setPreferredSize(new Dimension(2000, 1400)); // adjust height to fit all content

		JScrollPane pageScrollPane = new JScrollPane(contentPane);
		setContentPane(pageScrollPane);
		
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Finances - Add New Invoice");
		lblNewJgoodiesTitle.setFont(new Font("Lucida Grande", Font.BOLD, 48));
		lblNewJgoodiesTitle.setBounds(66, 34, 719, 102);
		contentPane.add(lblNewJgoodiesTitle);
		
		JPanel titleBackground = new JPanel();
		titleBackground.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		titleBackground.setBackground(new Color(217, 217, 217));
		titleBackground.setBounds(26, 34, 1875, 102);
		contentPane.add(titleBackground);
		
		//Navigation Panel - Not Configured
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(new Color(217, 217, 217));
		panel_1.setBounds(26, 148, 204, 789);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JTextPane txtpnSelectAnOption = new JTextPane();
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
		
		//Back Button
		
		JButton FinancesButton_1 = new JButton("Back");
		FinancesButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		FinancesButton_1.setBounds(26, 949, 204, 82);
		contentPane.add(FinancesButton_1);
		GUI dashboard = new GUI();
		dashboard.setVisible(true);
		dispose();
		
		//TextField Names
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel(" Student Name *");
		lblNewJgoodiesLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewJgoodiesLabel.setBounds(290, 263, 193, 30);
		contentPane.add(lblNewJgoodiesLabel);
		
		JLabel lblNewJgoodiesLabel_2 = DefaultComponentFactory.getInstance().createLabel("Cost Type *");
		lblNewJgoodiesLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewJgoodiesLabel_2.setBounds(298, 501, 120, 38);
		contentPane.add(lblNewJgoodiesLabel_2);
		
		JLabel lblNewJgoodiesLabel_3 = DefaultComponentFactory.getInstance().createLabel(" Cost Amount *");
		lblNewJgoodiesLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewJgoodiesLabel_3.setBounds(1142, 501, 138, 30);
		contentPane.add(lblNewJgoodiesLabel_3);
		
		JLabel lblNewJgoodiesLabel_4 = DefaultComponentFactory.getInstance().createLabel(" Cost Date *");
		lblNewJgoodiesLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewJgoodiesLabel_4.setBounds(1142, 263, 120, 30);
		contentPane.add(lblNewJgoodiesLabel_4);
		
		JFormattedTextField TextBoxStudentName = new JFormattedTextField();
		TextBoxStudentName.setToolTipText("Value");
		TextBoxStudentName.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		TextBoxStudentName.setBounds(290, 297, 743, 50);
		contentPane.add(TextBoxStudentName);
		
		JFormattedTextField TextBoxCostType = new JFormattedTextField();
		TextBoxCostType.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		TextBoxCostType.setBounds(290, 533, 743, 50);
		contentPane.add(TextBoxCostType);
		
		JFormattedTextField TextBoxCostAmount = new JFormattedTextField();
		TextBoxCostAmount.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		TextBoxCostAmount.setBounds(1134, 533, 743, 50);
		contentPane.add(TextBoxCostAmount);
		
		JFormattedTextField TextBoxCostDate = new JFormattedTextField();
		TextBoxCostDate.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		TextBoxCostDate.setBounds(1142, 305, 743, 50);
		contentPane.add(TextBoxCostDate);
		
		JButton CreateInvoice = new JButton("Create Invoice");
		CreateInvoice.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		CreateInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					//String sqlInvoice = "INSERT INTO INVOICES (Student_Name, Course_Costs, Sports_Costs, Food_Costs, Date_Of_Invoice) VALUES (?, ?, ?, ?, ?)";
					Connection c = SQL_DB.dataConnection();
					//PreparedStatement statement = c.prepareStatement("INSERT INTO INVOICES (Student_Name, Course_Costs, Sports_Costs, Food_Costs, Date_Of_Invoice) VALUES (?, ?, ?, ?, ?)");
				
				PreparedStatement statement = SQL_DB.Invoice(c);
				statement.setString(1, TextBoxStudentName.getText());
				statement.setString(5, TextBoxCostDate.getText());
				
				statement.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Record Saved Sucessfully!");
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error saving record sucessfully", "Error!", JOptionPane.ERROR_MESSAGE);
				}

			}
			
		});
		CreateInvoice.setBounds(1684, 944, 204, 87);
		contentPane.add(CreateInvoice);
		
		//Import text fields into database
		//SQL_DB.Invoice() test = new SQL_DB.Invoice();

	}
}
