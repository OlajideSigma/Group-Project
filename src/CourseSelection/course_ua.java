package CourseSelection;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class course_ua extends JFrame {

    public course_ua() {
        setTitle("Brunel University Career Portal");
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

        JPanel rightNavPanel = new JPanel();
        rightNavPanel.setBackground(new Color(0, 51, 102));

        String[] navItems = {"Home", "Courses", "Appointments", "Guidance", "Contact"};
        for (String item : navItems) {
            JButton btn = new JButton(item);
            btn.setFont(new Font("Arial", Font.PLAIN, 14));
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(0, 51, 102));
            rightNavPanel.add(btn);

            btn.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, item + " clicked!");
            });
        }

        headerPanel.add(rightNavPanel, BorderLayout.EAST); 
        add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  
        buttonPanel.setBackground(new Color(0, 51, 102)); 

        String[] quickActions = {"Book Appointment", "Explore Courses", "Get Guidance"};
        for (String action : quickActions) {
            JButton btn = new JButton(action);
            btn.setFont(new Font("Arial", Font.PLAIN, 14));
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(0, 102, 255));
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);  

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 2));  

        JPanel appointmentsPanel = new JPanel();
        appointmentsPanel.setLayout(new BoxLayout(appointmentsPanel, BoxLayout.Y_AXIS));
        JLabel lblUpcomingAppointments = new JLabel("Upcoming Appointments");
        lblUpcomingAppointments.setFont(new Font("Arial", Font.BOLD, 16));
        appointmentsPanel.add(lblUpcomingAppointments);

        String[] appointmentColumnNames = {"Date", "Time", "Attendee", "Action"};
        Object[][] appointmentData = {
            {"12/13/2023", "10:00 AM", "Dr. Sarah Johnson", "View"},
            {"12/15/2023", "02:00 PM", "Mr. John Smith", "View"}
        };

        DefaultTableModel model = new DefaultTableModel(appointmentData, appointmentColumnNames) {
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Only "Action" column is editable
            }
        };

        JTable appointmentTable = new JTable(model);

        // White button renderer
        appointmentTable.getColumn("Action").setCellRenderer(new TableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JButton button = new JButton("View");
                button.setForeground(Color.BLACK);
                button.setBackground(Color.WHITE);
                return button;
            }
        });

        // White button editor
        appointmentTable.getColumn("Action").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            private JButton button = new JButton("View");

            {
                button.setForeground(Color.BLACK);
                button.setBackground(Color.WHITE);

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int row = appointmentTable.getSelectedRow();
                        String date = (String) appointmentTable.getValueAt(row, 0);
                        String time = (String) appointmentTable.getValueAt(row, 1);
                        String attendee = (String) appointmentTable.getValueAt(row, 2);

                        JOptionPane.showMessageDialog(button,
                                "Appointment Details:\nDate: " + date + "\nTime: " + time + "\nAttendee: " + attendee);
                        fireEditingStopped();
                    }
                });
            }

            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                return button;
            }

            public Object getCellEditorValue() {
                return "View";
            }
        });

        JScrollPane appointmentScrollPane = new JScrollPane(appointmentTable);
        appointmentsPanel.add(appointmentScrollPane);
        contentPanel.add(appointmentsPanel); 

        JPanel courseExplorerPanel = new JPanel();
        courseExplorerPanel.setLayout(new BoxLayout(courseExplorerPanel, BoxLayout.Y_AXIS));
        JLabel lblCourseExplorer = new JLabel("Course Explorer");
        lblCourseExplorer.setFont(new Font("Arial", Font.BOLD, 16));
        courseExplorerPanel.add(lblCourseExplorer);

        String[] courseNames = {"Computer Science", "Business Management", "Engineering", "Psychology"};
        for (String courseName : courseNames) {
            JPanel coursePanel = new JPanel();
            coursePanel.setLayout(new BorderLayout());

            JLabel lblCourse = new JLabel(courseName);
            lblCourse.setFont(new Font("Arial", Font.PLAIN, 14));
            coursePanel.add(lblCourse, BorderLayout.WEST);

            JButton btnViewDetails = new JButton("View Details");
            btnViewDetails.setForeground(Color.RED);
            btnViewDetails.setBackground(Color.WHITE);
            coursePanel.add(btnViewDetails, BorderLayout.EAST);

            btnViewDetails.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Showing details for " + courseName);
                // You can open another frame here if needed
            });

            courseExplorerPanel.add(coursePanel);
        }

        contentPanel.add(courseExplorerPanel);  
        add(contentPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(0, 51, 102)); 
        footerPanel.setLayout(new GridLayout(1, 2)); 

        JPanel contactPanel = new JPanel();
        contactPanel.setBackground(new Color(0, 51, 102));
        contactPanel.setLayout(new GridLayout(2, 1)); 
        JLabel lblContactTitle = new JLabel("Contact Us");
        lblContactTitle.setForeground(Color.WHITE);
        contactPanel.add(lblContactTitle);

        JLabel lblContactInfo = new JLabel("<html>Brunel University, Uxbridge, London<br>Phone: +44 1895 274000</html>");
        lblContactInfo.setForeground(Color.WHITE);
        contactPanel.add(lblContactInfo);
        footerPanel.add(contactPanel);

        JPanel quickLinksPanel = new JPanel();
        quickLinksPanel.setBackground(new Color(0, 51, 102));
        quickLinksPanel.setLayout(new GridLayout(3, 1));
        JLabel lblQuickLinks = new JLabel("Quick Links");
        lblQuickLinks.setForeground(Color.WHITE);
        quickLinksPanel.add(lblQuickLinks);

        JLabel lblPrivacyPolicy = new JLabel("Privacy Policy");
        lblPrivacyPolicy.setForeground(Color.WHITE);
        quickLinksPanel.add(lblPrivacyPolicy);

        JLabel lblTermsOfService = new JLabel("Terms of Service");
        lblTermsOfService.setForeground(Color.WHITE);
        quickLinksPanel.add(lblTermsOfService);
        footerPanel.add(quickLinksPanel);

        add(footerPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        new course_ua();
    }
}
