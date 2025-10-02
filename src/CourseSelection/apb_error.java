package CourseSelection;
import javax.swing.*;
import java.awt.*;

public class apb_error extends JFrame {

    public apb_error() {
       
        setTitle("Error Handling and Validation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);  // Set the size of the frame
        setLocationRelativeTo(null); // Center the frame on the screen

       
        setLayout(new BorderLayout());

        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 51, 102));
        headerPanel.setLayout(new BorderLayout());

        
        JLabel lblUniversityName = new JLabel("Brunel University", JLabel.LEFT);
        lblUniversityName.setFont(new Font("Arial", Font.BOLD, 20));
        lblUniversityName.setForeground(Color.WHITE);
        headerPanel.add(lblUniversityName, BorderLayout.WEST);

        
        JPanel navPanel = new JPanel();
        navPanel.setBackground(new Color(0, 51, 102));
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

        JButton btnAdvisors = new JButton("Advisors");
        btnAdvisors.setForeground(Color.WHITE);
        btnAdvisors.setBackground(new Color(0, 51, 102));
        btnAdvisors.setFont(new Font("Arial", Font.PLAIN, 14));
        navPanel.add(btnAdvisors);

        JButton btnContact = new JButton("Contact");
        btnContact.setForeground(Color.WHITE);
        btnContact.setBackground(new Color(0, 51, 102));
        btnContact.setFont(new Font("Arial", Font.PLAIN, 14));
        navPanel.add(btnContact);

        headerPanel.add(navPanel, BorderLayout.EAST);

        
        add(headerPanel, BorderLayout.NORTH);

        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 2));  

        
        JPanel appointmentPanel = new JPanel();
        appointmentPanel.setLayout(new BoxLayout(appointmentPanel, BoxLayout.Y_AXIS));
        JLabel lblAppointmentTitle = new JLabel("Appointment Booking");
        lblAppointmentTitle.setFont(new Font("Arial", Font.BOLD, 16));
        appointmentPanel.add(lblAppointmentTitle);

        JLabel lblName = new JLabel("Name:");
        JTextField tfName = new JTextField();
        appointmentPanel.add(lblName);
        appointmentPanel.add(tfName);

        JLabel lblDate = new JLabel("Date:");
        JTextField tfDate = new JTextField();
        appointmentPanel.add(lblDate);
        appointmentPanel.add(tfDate);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(new Color(0, 102, 255));
        btnSubmit.setForeground(Color.WHITE);
        appointmentPanel.add(btnSubmit);

        
        JButton btnBack = new JButton("Back");
        btnBack.setBackground(new Color(255, 99, 71));
        btnBack.setForeground(Color.WHITE);
        appointmentPanel.add(btnBack);

        contentPanel.add(appointmentPanel);  
        
        JPanel errorPanel = new JPanel();
        errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
        errorPanel.setBackground(new Color(255, 204, 204));  

        JLabel lblErrorTitle = new JLabel("Errors and Warnings");
        lblErrorTitle.setFont(new Font("Arial", Font.BOLD, 16));
        errorPanel.add(lblErrorTitle);

       
        JLabel lblErrorMessage = new JLabel("");
        lblErrorMessage.setForeground(Color.RED);
        errorPanel.add(lblErrorMessage);

        contentPanel.add(errorPanel);  
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

        JLabel lblContactInfo = new JLabel("<html>Brunel University, Uxbridge, London<br>Email: careers@brunel.ac.uk<br>Phone: +44 1895 274000</html>");
        lblContactInfo.setForeground(Color.WHITE);
        contactPanel.add(lblContactInfo);

        footerPanel.add(contactPanel, BorderLayout.WEST);

              JPanel quickLinksPanel = new JPanel();
        quickLinksPanel.setBackground(new Color(0, 51, 102));
        quickLinksPanel.setLayout(new GridLayout(3, 1)); 
        JLabel lblQuickLinks = new JLabel("Quick Links");
        lblQuickLinks.setForeground(Color.WHITE);
        quickLinksPanel.add(lblQuickLinks);

        JLabel lblPrivacyPolicy = new JLabel("Privacy Policy");
        lblPrivacyPolicy.setForeground(Color.WHITE);
        quickLinksPanel.add(lblPrivacyPolicy);

        JLabel lblFaq = new JLabel("FAQ");
        lblFaq.setForeground(Color.WHITE);
        quickLinksPanel.add(lblFaq);

        footerPanel.add(quickLinksPanel, BorderLayout.EAST);

        add(footerPanel, BorderLayout.SOUTH);

        
        errorPanel.setVisible(false);

        btnSubmit.addActionListener(e -> {
            String name = tfName.getText().trim();
            String date = tfDate.getText().trim();

            
            lblErrorMessage.setText("");

            
            errorPanel.setVisible(true);

            
            if (name.isEmpty() || date.isEmpty()) {
                lblErrorMessage.setText("❌ Name and Date cannot be empty.");
            } else if (!isValidName(name) || !isValidDate(date)) {
                lblErrorMessage.setText("❌ Details are incorrect.");
            }
        });

       
        btnBack.addActionListener(e -> {
            
            dispose();
           
            booking_system bookingSystemFrame = new booking_system();
            bookingSystemFrame.setVisible(true);
        });

       
        btnHome.addActionListener(e -> {
            
            dispose();
            
            booking_system bookingSystemFrame = new booking_system();
            bookingSystemFrame.setVisible(true);
        });

        
        btnCourses.addActionListener(e -> {
            
            dispose();
            
            CourseSelection courseSelectionFrame = new CourseSelection();
            courseSelectionFrame.setVisible(true);
        });

        
        setVisible(true);
    }

   
    private boolean isValidName(String name) {
        
        return !name.matches(".*\\d.*");
    }

    
    private boolean isValidDate(String date) {
        
        return date.contains("/");
    }

    public static void main(String[] args) {
        
        new apb_error();
    }
}
