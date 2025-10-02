package CourseSelection;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class Crs_Details extends JFrame {

    public Crs_Details(String courseName) {
        
        setTitle(courseName + " - Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);  
        setLocationRelativeTo(null); 

       
        setLayout(new BorderLayout());

        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 51, 102)); 
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1000, 60)); 

        
        JLabel lblUniversityName = new JLabel("Brunel University", JLabel.LEFT);
        lblUniversityName.setFont(new Font("Arial", Font.BOLD, 20));
        lblUniversityName.setForeground(Color.WHITE);
        headerPanel.add(lblUniversityName, BorderLayout.WEST);

        
        JPanel navPanel = new JPanel();
        navPanel.setBackground(new Color(0, 51, 102)); 

      
        JButton btnHome = new JButton("Home");
        btnHome.setFont(new Font("Arial", Font.PLAIN, 14));
        btnHome.setForeground(Color.WHITE);
        btnHome.setBackground(new Color(0, 51, 102));
        navPanel.add(btnHome);

        JButton btnCourses = new JButton("Courses");
        btnCourses.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCourses.setForeground(Color.WHITE);
        btnCourses.setBackground(new Color(0, 51, 102));
        navPanel.add(btnCourses);

        JButton btnAppointments = new JButton("Appointments");
        btnAppointments.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAppointments.setForeground(Color.WHITE);
        btnAppointments.setBackground(new Color(0, 51, 102));
        navPanel.add(btnAppointments);

        JButton btnSupport = new JButton("Support");
        btnSupport.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSupport.setForeground(Color.WHITE);
        btnSupport.setBackground(new Color(0, 51, 102));
        navPanel.add(btnSupport);

        headerPanel.add(navPanel, BorderLayout.EAST); 

        
        add(headerPanel, BorderLayout.NORTH);

        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 2));  

        
        JPanel appointmentPanel = new JPanel();
        appointmentPanel.setLayout(new BoxLayout(appointmentPanel, BoxLayout.Y_AXIS));
        JLabel lblAppointmentTitle = new JLabel("Appointment Booking");
        lblAppointmentTitle.setFont(new Font("Arial", Font.BOLD, 16));
        appointmentPanel.add(lblAppointmentTitle);

        
        JLabel lblName = new JLabel("Student Name:");
        JTextField tfName = new JTextField();
        tfName.setPreferredSize(new Dimension(300, 30)); 
        appointmentPanel.add(lblName);
        appointmentPanel.add(tfName);

        // Date Field
        JLabel lblDate = new JLabel("Date:");
        JTextField tfDate = new JTextField();
        tfDate.setPreferredSize(new Dimension(300, 30)); 
        appointmentPanel.add(lblDate);
        appointmentPanel.add(tfDate);

        
        JPanel buttonPanel = new JPanel(); 
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  

        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(new Color(0, 102, 255));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setPreferredSize(new Dimension(150, 40)); 
        buttonPanel.add(btnSubmit);

        
        JButton btnBack = new JButton("Back");
        btnBack.setBackground(new Color(255, 51, 51));  
        btnBack.setForeground(Color.WHITE);
        btnBack.setPreferredSize(new Dimension(150, 40)); 
        buttonPanel.add(btnBack);

        
        appointmentPanel.add(buttonPanel);

        contentPanel.add(appointmentPanel); 

        
        JPanel courseDetailsPanel = new JPanel();
        courseDetailsPanel.setLayout(new BorderLayout());

        JLabel lblCourseDetails = new JLabel(courseName + " Course Details");
        lblCourseDetails.setFont(new Font("Arial", Font.BOLD, 16));
        courseDetailsPanel.add(lblCourseDetails, BorderLayout.NORTH);

        
        String[] columnNames = {"Course Code", "Course Name", "Institution"};
        Object[][] data = {}; 
        JTable courseTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        courseTable.setFillsViewportHeight(true); 
        courseDetailsPanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(courseDetailsPanel);  

        
        add(contentPanel, BorderLayout.CENTER);

        
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(0, 51, 102));
        footerPanel.setLayout(new BorderLayout());

        
        JPanel contactPanel = new JPanel();
        contactPanel.setBackground(new Color(0, 51, 102));
        contactPanel.setLayout(new GridLayout(2, 1)); 
        JLabel lblContactTitle = new JLabel("Contact Us");
        lblContactTitle.setForeground(Color.WHITE);
        contactPanel.add(lblContactTitle);

        JLabel lblContactInfo = new JLabel("<html>Brunel University, Uxbridge, London<br>Phone: +44 1895 274000</html>");
        lblContactInfo.setForeground(Color.WHITE);
        contactPanel.add(lblContactInfo);

        footerPanel.add(contactPanel, BorderLayout.WEST);

        
        JPanel quickLinksPanel = new JPanel();
        quickLinksPanel.setBackground(new Color(0, 51, 102));
        quickLinksPanel.setLayout(new GridLayout(4, 1));  
        JLabel lblQuickLinks = new JLabel("University Resources");
        lblQuickLinks.setForeground(Color.WHITE);
        quickLinksPanel.add(lblQuickLinks);

        JLabel lblLibrary = new JLabel("Library");
        lblLibrary.setForeground(Color.WHITE);
        quickLinksPanel.add(lblLibrary);

        JLabel lblStudentPortal = new JLabel("Student Portal");
        lblStudentPortal.setForeground(Color.WHITE);
        quickLinksPanel.add(lblStudentPortal);

        JLabel lblCareerServices = new JLabel("Career Services");
        lblCareerServices.setForeground(Color.WHITE);
        quickLinksPanel.add(lblCareerServices);

        footerPanel.add(quickLinksPanel, BorderLayout.EAST);

        add(footerPanel, BorderLayout.SOUTH);

        
        setVisible(true);

        
        btnHome.addActionListener(e -> {
            dispose();
            booking_system homeFrame = new booking_system();
            homeFrame.setVisible(true);
        });

        btnCourses.addActionListener(e -> {
            dispose();
            CourseSelection courseSelectionFrame = new CourseSelection();
            courseSelectionFrame.setVisible(true);
        });

        btnAppointments.addActionListener(e -> {
            dispose();
            bk_appnt bookingFrame = new bk_appnt();
            bookingFrame.setVisible(true);
        });

        
        btnSubmit.addActionListener(e -> {
            
            String studentName = "Admin"; // Default name
            String appointmentDate = "10/10/10"; // Default date

            
            String[][] courseData = {
                {"CS101", "Introduction to Computer Science", "Brunel University"},
                {"MA1021", "Advanced Engineering Mathematics", "Brunel University"},
                {"DS1031", "Statistics for Data Science", "Brunel University"}
            };

            
            DefaultTableModel courseTableModel = new DefaultTableModel(courseData, columnNames);
            courseTable.setModel(courseTableModel); // Update the table with course details
        });

        
        btnBack.addActionListener(e -> {
            dispose();  
            CourseSelection courseSelectionFrame = new CourseSelection();  
            courseSelectionFrame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        
        new Crs_Details("Computer Science");
    }
}
