package CourseSelection;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Launcher.LauncherPage;

import java.awt.Color;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseSelection extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField SearchField;
    private JLabel lblErrorMessage;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CourseSelection frame = new CourseSelection();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CourseSelection() {
        setTitle("Course Selection");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(540, 60, 120, 30);
        contentPane.add(btnSearch);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 120, 650, 250);
        contentPane.add(scrollPane);

        SearchField = new JTextField();
        SearchField.setBounds(10, 60, 150, 30);
        contentPane.add(SearchField);
        SearchField.setColumns(10);

        lblErrorMessage = new JLabel("");
        lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setBounds(10, 100, 400, 20);
        contentPane.add(lblErrorMessage);

        btnSearch.addActionListener(e -> loadData());

       
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(10, 380, 120, 30);  
        contentPane.add(btnBack);

        
        btnBack.addActionListener(e -> {
        	new booking_system().setVisible(true);
            dispose();  
            
        });
    }

    private void loadData() {
        try {
            DefaultTableModel m = new DefaultTableModel();
            
            
            m.addColumn("PUBUKPRN");
            m.addColumn("UKPRN");
            m.addColumn("ASSURL");
            m.addColumn("CRSECSTURL");
            m.addColumn("CRSEURL");
            m.addColumn("EMPLOYURL");
            m.addColumn("FOUNDATION");
            m.addColumn("HONOURS");
            m.addColumn("KISCOURSEID");
            m.addColumn("KISMODE");
            m.addColumn("NUMSTAGE");
            m.addColumn("SANDWICH");
            m.addColumn("SUPPORTURL");
            m.addColumn("TITLE");
            m.addColumn("UCASPROGID");
            m.addColumn("UKPRNAPPLY");
            m.addColumn("YEARABROAD");
            m.addColumn("KISAIMCODE");
            m.addColumn("KISLEVEL");

            String searchTerm = SearchField.getText().trim();
            String query = "SELECT * FROM KISCOURSE WHERE PUBUKPRN LIKE '%" + searchTerm + "%'";

            ResultSet results = course.ReadFromDB(query);

            if (!results.isBeforeFirst()) {
                lblErrorMessage.setText("No records found for the given search term.");
            } else {
                while (results.next()) {
                   
                    String pubukprn = results.getString("PUBUKPRN");
                    String ukprn = results.getString("UKPRN");
                    String assurl = results.getString("ASSURL");
                    String crsecsturl = results.getString("CRSECSTURL");
                    String crseurl = results.getString("CRSEURL");
                    String employurl = results.getString("EMPLOYURL");
                    String foundation = results.getString("FOUNDATION");
                    String honours = results.getString("HONOURS");
                    String kiscourseid = results.getString("KISCOURSEID");
                    String kismode = results.getString("KISMODE");
                    String numstage = results.getString("NUMSTAGE");
                    String sandwich = results.getString("SANDWICH");
                    String supporturl = results.getString("SUPPORTURL");
                    String title = results.getString("TITLE");
                    String ucasprogid = results.getString("UCASPROGID");
                    String ukprnappl = results.getString("UKPRNAPPLY");
                    String yearabroad = results.getString("YEARABROAD");
                    String kisaimcode = results.getString("KISAIMCODE");
                    String kislevel = results.getString("KISLEVEL");

                   
                    Object[] row = {
                        pubukprn, ukprn, assurl, crsecsturl, crseurl, 
                        employurl, foundation, honours, kiscourseid, kismode, 
                        numstage, sandwich, supporturl, title, ucasprogid, 
                        ukprnappl, yearabroad, kisaimcode, kislevel
                    };
                    m.addRow(row);
                }
                table.setModel(m);
                lblErrorMessage.setText("");  
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            lblErrorMessage.setText("Database error occurred. Please try again.");
        }
    }
}
