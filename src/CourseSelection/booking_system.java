package CourseSelection;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;  // Added this import
import java.awt.event.ActionEvent;    // Added this import

public class booking_system extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    booking_system frame = new booking_system();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public booking_system() {
        setTitle("Course Selection and Career Guidance System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setLocationRelativeTo(null); 
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 51, 102));
        headerPanel.setBounds(0, 0, 800, 60);
        contentPane.add(headerPanel);
        headerPanel.setLayout(new BorderLayout());

        
        JLabel lblUniversityName = new JLabel(" Brunel University");
        lblUniversityName.setFont(new Font("Arial", Font.BOLD, 20));
        lblUniversityName.setForeground(Color.WHITE);
        headerPanel.add(lblUniversityName, BorderLayout.WEST);

        
        JLabel lblWelcomeMessage = new JLabel("Welcome to the Course Selection and Career Guidance Booking System");
        lblWelcomeMessage.setFont(new Font("Arial", Font.PLAIN, 16));
        lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcomeMessage.setBounds(100, 70, 600, 30);
        contentPane.add(lblWelcomeMessage);

        
        JPanel coursePanel = new JPanel();
        coursePanel.setBounds(50, 150, 300, 200);
        coursePanel.setBackground(new Color(238, 238, 238));
        coursePanel.setLayout(null);
        contentPane.add(coursePanel);

        JLabel lblCourseImage = new JLabel(new ImageIcon("Images/courseselection.png"));
        lblCourseImage.setBounds(10, 10, 280, 120);
        coursePanel.add(lblCourseImage);

        JLabel lblCourseTitle = new JLabel("Course Selection");
        lblCourseTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblCourseTitle.setBounds(90, 120, 200, 30);
        coursePanel.add(lblCourseTitle);

        JButton btnExploreCourses = new JButton("Explore Courses");
        btnExploreCourses.setBounds(50, 160, 200, 30);
        coursePanel.add(btnExploreCourses);

        btnExploreCourses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                CourseSelection courseSelectionFrame = new CourseSelection();
                courseSelectionFrame.setVisible(true);  
                dispose();  
            }
        });

       
        JPanel appointmentPanel = new JPanel();
        appointmentPanel.setBounds(450, 150, 300, 200);
        appointmentPanel.setBackground(new Color(238, 238, 238));
        appointmentPanel.setLayout(null);
        contentPane.add(appointmentPanel);

        JLabel lblAppointmentImage = new JLabel(new ImageIcon("Images/appointment.png"));
        lblAppointmentImage.setBounds(10, 10, 280, 120);
        appointmentPanel.add(lblAppointmentImage);

        JLabel lblAppointmentTitle = new JLabel("Appointment Booking");
        lblAppointmentTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblAppointmentTitle.setBounds(69, 120, 200, 30);
        appointmentPanel.add(lblAppointmentTitle);

        JButton btnBookAppointment = new JButton("Book Appointment");
        btnBookAppointment.setBounds(50, 160, 200, 30);
        appointmentPanel.add(btnBookAppointment);

      
        btnBookAppointment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                bk_appnt bookingFrame = new bk_appnt();  
                bookingFrame.setVisible(true);  
                dispose();  
            }
        });
    }
}
