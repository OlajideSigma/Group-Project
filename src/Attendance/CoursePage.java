package Attendance;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;

public class CoursePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField courseSearchField;
    private JTable resultsTable;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoursePage frame = new CoursePage();
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
	public CoursePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1264, 697);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(213, 243, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		Menubar sidebar = new Menubar(this);
		sidebar.setBounds(5, 5, 236, 653);
		contentPane.add(sidebar);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCourseAttendance = new JLabel("Course Attendance");
		lblCourseAttendance.setForeground(new Color(0, 0, 128));
		lblCourseAttendance.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		lblCourseAttendance.setBounds(259, 11, 236, 52);
		contentPane.add(lblCourseAttendance);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.setBounds(303, 85, 840, 35);
		contentPane.add(searchPanel);
		
		JLabel lblCourse = new JLabel("Course ID:");
        lblCourse.setBounds(10, 10, 67, 20);
        searchPanel.add(lblCourse);

        courseSearchField = new JTextField();
        courseSearchField.setBounds(87, 9, 210, 20);
        searchPanel.add(courseSearchField);
		
		JLabel lblStartDate = new JLabel("Start Date:");
        lblStartDate.setBounds(318, 9, 72, 20);
        searchPanel.add(lblStartDate);

        JDateChooser startDatePicker = new JDateChooser();
        startDatePicker.setBounds(400, 10, 100, 20);
        searchPanel.add(startDatePicker);

        JLabel lblEndDate = new JLabel("End Date:");
        lblEndDate.setBounds(524, 9, 66, 20);
        searchPanel.add(lblEndDate);

        JDateChooser endDatePicker = new JDateChooser();
        endDatePicker.setBounds(600, 10, 100, 20);
        searchPanel.add(endDatePicker);
        
		
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(710, 10, 123, 18);
		searchPanel.add(searchButton);
		
		resultsTable = new JTable();  // Make sure it's initialized
		resultsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable copy = (JTable)e.getSource();
				int row = copy.getSelectedRow();
				if (row >= 0) {
		            String data = copy.getModel().getValueAt(row, 0).toString(); // Extract Course ID
		            Subcourse frame = new Subcourse(data);
		            frame.setVisible(true);
		        }
			}
		});
		JScrollPane scrollPane = new JScrollPane(resultsTable);
		scrollPane.setBounds(303, 141, 840, 390);
		contentPane.add(scrollPane);
		
		searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String courseID = courseSearchField.getText();


                    // Get the selected dates from JDateChooser
                    java.util.Date startDateUtil = startDatePicker.getDate();
                    java.util.Date endDateUtil = endDatePicker.getDate();
                    
                    // Format the start and end date into the format dd-MM-yyyy
                    String startDateString = null, endDateString = null;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                    if (startDateUtil != null) {
                        startDateString = dateFormat.format(startDateUtil); // Format start date to dd-MM-yyyy
                    }

                    if (endDateUtil != null) {
                        endDateString = dateFormat.format(endDateUtil); // Format end date to dd-MM-yyyy
                    }


                    if (courseID.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter a Course ID.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    DefaultTableModel model = CoursePage2.courseSearch(courseID, startDateString, endDateString);
                    resultsTable.setModel(model);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error fetching data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

	}
}
