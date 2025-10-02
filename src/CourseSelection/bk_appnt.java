package CourseSelection;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Launcher.LauncherPage;

public class bk_appnt extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfFullName, tfContactDetails, tfCourseSelected, tfDateEntryExam, tfDateCareerGuidance, tfBookingID;

    private JFrame previousPage;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                bk_appnt frame = new bk_appnt(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public bk_appnt() {
        this(null);
    }

    public bk_appnt(JFrame previousPage) {
        this.previousPage = previousPage;

        setTitle("Appointment Booking Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 805, 639);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(null);

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 800, 60);
        headerPanel.setBackground(new Color(0, 51, 102));
        contentPane.add(headerPanel);
        headerPanel.setLayout(new BorderLayout());

        JLabel lblUniversityName = new JLabel(" Brunel University");
        lblUniversityName.setFont(new Font("Arial", Font.BOLD, 20));
        lblUniversityName.setForeground(Color.WHITE);
        headerPanel.add(lblUniversityName, BorderLayout.WEST);

        JPanel navPanel = new JPanel();
        navPanel.setBackground(new Color(0, 51, 102));
        headerPanel.add(navPanel, BorderLayout.EAST);

        addNavButton(navPanel, "Home", e -> {
            dispose();
            new LauncherPage().setVisible(true);
        });

        addNavButton(navPanel, "Courses", e -> {
            dispose();
            new CourseSelection().setVisible(true);
        });

        addNavButton(navPanel, "Appointments", e -> {});
        addNavButton(navPanel, "Contact", null);

        JLabel lblTitle = new JLabel("Book an Appointment");
        lblTitle.setBounds(0, 71, 800, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitle);

        // Input Fields with Placeholders
        tfFullName = createPlaceholderField("Full Name", 10, 120, 250);
        tfContactDetails = createPlaceholderField("Contact Details", 312, 120, 250);
        tfCourseSelected = createPlaceholderField("Course Selected", 10, 160, 532);
        tfDateEntryExam = createPlaceholderField("Date & Time for Entry Exam", 30, 200, 250);
        tfDateCareerGuidance = createPlaceholderField("Date & Time for Career Guidance", 292, 200, 250);

        JButton btnGenerateBookingID = new JButton("Generate Booking ID");
        btnGenerateBookingID.setBounds(30, 243, 200, 30);
        btnGenerateBookingID.setBackground(new Color(0, 102, 255));
        btnGenerateBookingID.setForeground(Color.WHITE);
        contentPane.add(btnGenerateBookingID);

        tfBookingID = createPlaceholderField("Enter Booking ID", 30, 331, 250);

        btnGenerateBookingID.addActionListener(e -> tfBookingID.setText("190"));

        JButton btnSearchAppointment = new JButton("Search");
        btnSearchAppointment.setBounds(312, 331, 100, 30);
        btnSearchAppointment.setBackground(new Color(0, 102, 255));
        btnSearchAppointment.setForeground(Color.WHITE);
        contentPane.add(btnSearchAppointment);

        btnSearchAppointment.addActionListener(e -> {
            new course_ua().setVisible(true);
            dispose();
        });

        JTextField tfModifyBookingID = createPlaceholderField("Booking ID", 30, 408, 512);
        JTextField tfNewDateEntryExam = createPlaceholderField("New Date for Entry Exam", 30, 452, 250);
        JTextField tfNewDateCareerGuidance = createPlaceholderField("New Date for Career Guidance", 292, 452, 250);

        JButton btnUpdateAppointment = new JButton("Update Appointment");
        btnUpdateAppointment.setBounds(80, 505, 150, 30);
        btnUpdateAppointment.setBackground(new Color(0, 102, 255));
        btnUpdateAppointment.setForeground(Color.WHITE);
        contentPane.add(btnUpdateAppointment);

        JButton btnCancelAppointment = new JButton("Cancel Appointment");
        btnCancelAppointment.setBounds(262, 505, 150, 30);
        btnCancelAppointment.setBackground(new Color(255, 51, 51));
        btnCancelAppointment.setForeground(Color.WHITE);
        contentPane.add(btnCancelAppointment);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(422, 505, 100, 30);
        btnBack.setBackground(new Color(255, 99, 71));
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            dispose();
            new CourseSelection().setVisible(true);
        });

        btnUpdateAppointment.addActionListener(e -> {
            String bookingID = tfModifyBookingID.getText();
            String newEntryExamDate = tfNewDateEntryExam.getText();
            String newCareerGuideDate = tfNewDateCareerGuidance.getText();

            if ("Admin".equals(bookingID) && "10/10/10".equals(newEntryExamDate) && "10/10/10".equals(newCareerGuideDate)) {
                JOptionPane.showMessageDialog(null, "Update Successful!");
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect details. Please try again.");
            }
        });

        btnCancelAppointment.addActionListener(e -> {
            String bookingID = tfModifyBookingID.getText();
            String newEntryExamDate = tfNewDateEntryExam.getText();
            String newCareerGuideDate = tfNewDateCareerGuidance.getText();

            if ("Admin".equals(bookingID) && "10/10/10".equals(newEntryExamDate) && "10/10/10".equals(newCareerGuideDate)) {
                JOptionPane.showMessageDialog(null, "Cancel Successful!");
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect details. Please try again.");
            }
        });
    }

    private JTextField createPlaceholderField(String placeholder, int x, int y, int width) {
        JTextField field = new JTextField(placeholder);
        field.setForeground(Color.GRAY);
        field.setBounds(x, y, width, 25);

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                }
            }
        });

        contentPane.add(field);
        return field;
    }

    private void addNavButton(JPanel panel, String title, ActionListener action) {
        JButton button = new JButton(title);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 51, 102));
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        if (action != null) button.addActionListener(action);
        panel.add(button);
    }
}
