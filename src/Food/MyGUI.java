package Food;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MyGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField SearchField;
    private JTable table;
    private JLabel lblNewLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyGUI frame = new MyGUI();
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
    public MyGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 897, 573);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        JButton btnNewButton = new JButton("Search");
        btnNewButton.setBounds(445, 153, 96, 24);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hiii");
                try {
                    DefaultTableModel m = new DefaultTableModel();
                    m.addColumn("Institute Name");
                    m.addColumn("UKPRN");
                    ResultSet results = INSTITUTION.ReadFromDB(SearchField.getText());

                    while (results.next()) {
                        String name = results.getString("LEGAL_NAME");
                        String id = results.getString("UKPRN");

                        Object[] row = {name, id};
                        m.addRow(row);
                    }
                    table.setModel(m);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        contentPane.setLayout(null);
        contentPane.add(btnNewButton);

        SearchField = new JTextField();
        SearchField.setBounds(23, 154, 375, 24);
        contentPane.add(SearchField);
        SearchField.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 218, 462, 297);
        contentPane.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                String instituteName = (String) table.getValueAt(row, 0); // Get the institute name from the selected row
                openRestT2Frame(instituteName); // Open the NEXT frame with the institute name
            }
        });
        scrollPane.setViewportView(table);
        
        lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("src\\Food\\1.png"));
        lblNewLabel.setBounds(0, 0, 883, 536);
        contentPane.add(lblNewLabel);
    }

    private void openRestT2Frame(String instituteName) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                   mainscreen frame = new mainscreen (instituteName);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}