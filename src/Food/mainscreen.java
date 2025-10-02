package Food;



import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class mainscreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mainscreen frame = new mainscreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @param data 
     */
    public mainscreen(String data) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204));
        headerPanel.setBounds(0, 0, 1184, 80);
        contentPane.add(headerPanel);
        headerPanel.setLayout(null);

        JLabel UniversityName = new JLabel("New label");
        UniversityName.setBounds(306, 0, 868, 80);
        UniversityName.setFont (new Font("Tahoma", Font.BOLD, 21));
        headerPanel.add(UniversityName);
        UniversityName.setText(data);
     
        
        // Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setBackground(new Color(240, 240, 240));
        navPanel.setBounds(0, 80, 250, 581);
        contentPane.add(navPanel);
        navPanel.setLayout(null);

        JButton btnDashboard = new JButton("Dashboard");
        btnDashboard.setBounds(10, 10, 230, 40);
        btnDashboard.setFont(new Font("Arial", Font.PLAIN, 16));
        navPanel.add(btnDashboard);

        JButton btnStudents = new JButton("Students");
        btnStudents.setBounds(10, 60, 230, 40);
        btnStudents.setFont(new Font("Arial", Font.PLAIN, 16));
        navPanel.add(btnStudents);

        JButton btnCourses = new JButton("Courses");
        btnCourses.setBounds(10, 110, 230, 40);
        btnCourses.setFont(new Font("Arial", Font.PLAIN, 16));
        navPanel.add(btnCourses);

        JButton btnFaculty = new JButton("Faculty");
        btnFaculty.setBounds(10, 160, 230, 40);
        btnFaculty.setFont(new Font("Arial", Font.PLAIN, 16));
        navPanel.add(btnFaculty);

        JButton btnSettings = new JButton("Settings");
        btnSettings.setBounds(10, 210, 230, 40);
        btnSettings.setFont(new Font("Arial", Font.PLAIN, 16));
        navPanel.add(btnSettings);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 494, 230, 2);
        navPanel.add(separator);

        JButton btnLogout = new JButton("Login...");
        btnLogout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
        	}
        });
        btnLogout.setBounds(10, 520, 230, 40);
        btnLogout.setFont(new Font("Arial", Font.PLAIN, 16));
        navPanel.add(btnLogout);
        
        JButton btnRestaurent = new JButton("Restaurent");
        btnRestaurent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		restT2 frame = new restT2();
				frame.setVisible(true);
        	}
        });
        btnRestaurent.setFont(new Font("Arial", Font.PLAIN, 16));
        btnRestaurent.setBounds(10, 269, 230, 40);
        navPanel.add(btnRestaurent);

        // Main Content Panel
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBackground(Color.WHITE);
        mainContentPanel.setBounds(250, 80, 934, 581);
        contentPane.add(mainContentPanel);
        mainContentPanel.setLayout(null);

        JLabel lblWelcome = new JLabel("Welcome back");
        lblWelcome.setForeground(new Color(0, 0, 0));
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 30));
        lblWelcome.setBounds(44, 38, 360, 94);
        mainContentPanel.add(lblWelcome);

        JTextArea txtAnnouncements = new JTextArea();
        txtAnnouncements.setText("Announcements\r\n\r\n1. Semester exams start next week.\r\n2. Faculty meeting on Friday.\r\n3. New courses added for the next semester.");
        txtAnnouncements.setFont(new Font("Arial", Font.PLAIN, 14));
        txtAnnouncements.setBounds(631, 38, 303, 108);
        txtAnnouncements.setEditable(false);
        mainContentPanel.add(txtAnnouncements);

        JLabel lblQuickLinks = new JLabel("Quick Links:");
        lblQuickLinks.setForeground(new Color(255, 255, 0));
        lblQuickLinks.setFont(new Font("Arial", Font.BOLD, 18));
        lblQuickLinks.setBounds(774, 384, 150, 20);
        mainContentPanel.add(lblQuickLinks);

        JButton btnViewGrades = new JButton("View Grades");
        btnViewGrades.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnViewGrades.setFont(new Font("Arial", Font.PLAIN, 14));
        btnViewGrades.setBounds(765, 420, 150, 30);
        mainContentPanel.add(btnViewGrades);

        JButton btnViewSchedule = new JButton("View Schedule");
        btnViewSchedule.setFont(new Font("Arial", Font.PLAIN, 14));
        btnViewSchedule.setBounds(765, 460, 150, 30);
        mainContentPanel.add(btnViewSchedule);

        JButton btnViewAttendance = new JButton("View Attendance");
        btnViewAttendance.setFont(new Font("Arial", Font.PLAIN, 14));
        btnViewAttendance.setBounds(765, 500, 150, 30);
        mainContentPanel.add(btnViewAttendance);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("src\\Food\\chrc_Eastern_Gateway_building_1_30.jpg"));
        lblNewLabel.setBounds(10, 10, 924, 561);
        mainContentPanel.add(lblNewLabel);
        
        JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Announcements\r\n\r\n1. Semester exams start next week.\r\n2. Faculty meeting on Friday.\r\n3. New courses added for the next semester.");
        lblNewJgoodiesLabel.setBounds(478, 175, 313, 108);
        mainContentPanel.add(lblNewJgoodiesLabel);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(0, 102, 204));
        footerPanel.setBounds(0, 661, 1184, 20);
        contentPane.add(footerPanel);

        JLabel lblFooter = new JLabel("© 2023 University Management System. All rights reserved.");
        lblFooter.setForeground(Color.WHITE);
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 12));
        footerPanel.add(lblFooter);
    }

	public mainscreen() {
		// TODO Auto-generated constructor stub
	}
}
