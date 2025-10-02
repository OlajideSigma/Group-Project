package CourseSelection;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;  
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class explore_cr extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfStudentName, tfEmail, tfSelectDate;
    private JButton btnBookAppointment, btnLearnMore1, btnLearnMore2, btnLearnMore3, btnBack;

   
    private JLabel lblQuickLinks;
    private JButton btnStudentPortal, btnLibrary, btnSupport;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    explore_cr frame = new explore_cr();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   
    public explore_cr() {
        setTitle("Back-End Integration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 844);  
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setLocationRelativeTo(null); 

        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 51, 102));
        headerPanel.setBounds(0, 0, 1000, 60);
        contentPane.add(headerPanel);
        headerPanel.setLayout(new BorderLayout());

       
        JLabel lblUniversityName = new JLabel("  Brunel University");
        lblUniversityName.setFont(new Font("Arial", Font.BOLD, 20));
        lblUniversityName.setForeground(Color.WHITE);
        headerPanel.add(lblUniversityName, BorderLayout.WEST);

        
        JPanel navPanel = new JPanel();
        navPanel.setBackground(new Color(0, 51, 102));
        headerPanel.add(navPanel, BorderLayout.EAST);

        JButton btnHome = new JButton("Home");
        btnHome.setForeground(Color.WHITE);
        btnHome.setBackground(new Color(0, 51, 102));
        btnHome.setFont(new Font("Arial", Font.PLAIN, 14));
        navPanel.add(btnHome);

        JButton btnCourses = new JButton("Courses");
        btnCourses.setForeground(Color.WHITE);
        btnCourses.setBackground(new Color(0, 51, 102));
        btnCourses.setFont(new Font("Arial", Font.PLAIN, 14));
        navPanel.add(btnCourses);

        JButton btnAppointments = new JButton("Appointments");
        btnAppointments.setForeground(Color.WHITE);
        btnAppointments.setBackground(new Color(0, 51, 102));
        btnAppointments.setFont(new Font("Arial", Font.PLAIN, 14));
        navPanel.add(btnAppointments);

        JButton btnSupportNav = new JButton("Support");
        btnSupportNav.setForeground(Color.WHITE);
        btnSupportNav.setBackground(new Color(0, 51, 102));
        btnSupportNav.setFont(new Font("Arial", Font.PLAIN, 14));
        navPanel.add(btnSupportNav);

       
        JLabel lblTitle = new JLabel("Explore Courses");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(-309, 71, 800, 30);
        contentPane.add(lblTitle);

        
        JPanel coursePanel = new JPanel();
        coursePanel.setBounds(50, 120, 900, 250); 
        coursePanel.setLayout(new GridLayout(1, 3, 20, 20));
        contentPane.add(coursePanel);

        
        JPanel course1 = new JPanel();
        course1.setBackground(new Color(240, 240, 240));
        course1.setLayout(new BoxLayout(course1, BoxLayout.Y_AXIS));
        JLabel lblCourse1 = new JLabel("Computer Science");
        lblCourse1.setFont(new Font("Arial", Font.BOLD, 16));

        
        JLabel lblImage1 = new JLabel("Image Placeholder");
        lblImage1.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage1.setPreferredSize(new Dimension(150, 150)); 
        JButton btnLearnMore1 = new JButton("Learn More");
        btnLearnMore1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
               
                Crs_Details crsDetailsFrame = new Crs_Details("Computer Science");
                crsDetailsFrame.setVisible(true);
                dispose(); 
        	}
        });
        btnLearnMore1.setBackground(Color.WHITE);
        btnLearnMore1.setForeground(new Color(0, 51, 102));
        course1.add(lblImage1);
        course1.add(lblCourse1);
        course1.add(btnLearnMore1);
        coursePanel.add(course1);

       
        JPanel course2 = new JPanel();
        course2.setBackground(new Color(240, 240, 240));
        course2.setLayout(new BoxLayout(course2, BoxLayout.Y_AXIS));
        JLabel lblCourse2 = new JLabel("Business Management");
        lblCourse2.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblImage2 = new JLabel("Image Placeholder");
        lblImage2.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage2.setPreferredSize(new Dimension(150, 150)); 
        JButton btnLearnMore2 = new JButton("Learn More");
        btnLearnMore2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
               
                Crs_Details crsDetailsFrame = new Crs_Details("Business Management");
                crsDetailsFrame.setVisible(true);
                dispose(); 
        	}
        });
        btnLearnMore2.setBackground(Color.WHITE);
        btnLearnMore2.setForeground(new Color(0, 51, 102));
        course2.add(lblImage2);
        course2.add(lblCourse2);
        course2.add(btnLearnMore2);
        coursePanel.add(course2);

        
        JPanel course3 = new JPanel();
        course3.setBackground(new Color(240, 240, 240));
        course3.setLayout(new BoxLayout(course3, BoxLayout.Y_AXIS));
        JLabel lblCourse3 = new JLabel("Art and Design");
        lblCourse3.setFont(new Font("Arial", Font.BOLD, 16));

        
        JLabel lblImage3 = new JLabel("Image Placeholder");
        lblImage3.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage3.setPreferredSize(new Dimension(150, 150)); 
        JButton btnLearnMore3 = new JButton("Learn More");
        btnLearnMore3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                
                Crs_Details crsDetailsFrame = new Crs_Details("Art and Design");
                crsDetailsFrame.setVisible(true);
                dispose(); 
        	}
        });
        btnLearnMore3.setBackground(Color.WHITE);
        btnLearnMore3.setForeground(new Color(0, 51, 102));
        course3.add(lblImage3);
        course3.add(lblCourse3);
        course3.add(btnLearnMore3);
        coursePanel.add(course3);

       
        JLabel lblBookAppointments = new JLabel("Book Appointments");
        lblBookAppointments.setFont(new Font("Arial", Font.BOLD, 16));
        lblBookAppointments.setBounds(10, 383, 250, 25);
        contentPane.add(lblBookAppointments);

        JLabel lblStudentName = new JLabel("Student Name");
        lblStudentName.setFont(new Font("Arial", Font.PLAIN, 14));
        lblStudentName.setBounds(10, 411, 100, 25);
        contentPane.add(lblStudentName);

        tfStudentName = new JTextField();
        tfStudentName.setBounds(10, 433, 250, 25);
        contentPane.add(tfStudentName);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        lblEmail.setBounds(10, 472, 100, 25);
        contentPane.add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(10, 497, 250, 25);
        contentPane.add(tfEmail);

        JLabel lblSelectDate = new JLabel("Select Date");
        lblSelectDate.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSelectDate.setBounds(10, 533, 100, 25);
        contentPane.add(lblSelectDate);

        tfSelectDate = new JTextField();
        tfSelectDate.setBounds(10, 560, 250, 25);
        contentPane.add(tfSelectDate);

        btnBookAppointment = new JButton("Book Appointment");
        btnBookAppointment.setBounds(378, 610, 200, 30);
        btnBookAppointment.setBackground(new Color(0, 102, 255));
        btnBookAppointment.setForeground(Color.WHITE);
        contentPane.add(btnBookAppointment);

       
        btnBack = new JButton("Back");
        btnBack.setBounds(618, 610, 100, 30);  
        btnBack.setBackground(new Color(255, 51, 51));
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

       
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                CourseSelection courseSelectionFrame = new CourseSelection();  
                courseSelectionFrame.setVisible(true);
            }
        });

        
        btnBookAppointment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentName = tfStudentName.getText().trim();
                String email = tfEmail.getText().trim();
                String date = tfSelectDate.getText().trim();

               
                if (studentName.equals("Admin") && email.equals("Admin") && date.equals("10/10/10")) {
                    
                    bk_appnt bookingFrame = new bk_appnt();
                    bookingFrame.setVisible(true);
                    dispose();  
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect details. Please try again.");
                }
            }
        });

        
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                booking_system homeFrame = new booking_system();
                homeFrame.setVisible(true);
                dispose();  
            }
        });

        btnCourses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                CourseSelection courseSelectionFrame = new CourseSelection();
                courseSelectionFrame.setVisible(true);
                dispose();  
            }
        });

        btnAppointments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                bk_appnt bookingFrame = new bk_appnt();
                bookingFrame.setVisible(true);
                dispose(); 
            }
        });
    }
}
