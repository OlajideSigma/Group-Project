package Attendance;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import javax.swing.border.EtchedBorder;

import Launcher.LauncherPage;

public class Menubar extends JPanel { 

    private static final long serialVersionUID = 1L;

    /**
     * Create the Menubar panel.
     */
    public Menubar(JFrame currentFrame) {
        setLayout(null);
        setBounds(0, 0, 236, 653);
        setBackground(Color.WHITE);
        setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null)); 

        JButton btnDashboard = new JButton("Dashboard");
        btnDashboard.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnDashboard.setBounds(0, 29, 236, 23);
        add(btnDashboard);

        JLabel lblAnalyse = new JLabel("--- Analyse ---");
        lblAnalyse.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblAnalyse.setBounds(69, 76, 90, 14);
        add(lblAnalyse);

        JButton btnStudentAttendance = new JButton("Student Attendance");
        btnStudentAttendance.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnStudentAttendance.setBounds(0, 120, 236, 23);
        add(btnStudentAttendance);

        JButton btnCourseAttendance = new JButton("Course Attendance");
        btnCourseAttendance.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnCourseAttendance.setBounds(0, 164, 236, 23);
        add(btnCourseAttendance);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnLogout.setBounds(0, 208, 236, 23);
        add(btnLogout);

        // ✅ Add navigation functionality
        btnDashboard.addActionListener(e -> {
			try {
				openNewPage(currentFrame, new MYGUI());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        btnStudentAttendance.addActionListener(e -> openNewPage(currentFrame, new StudentPage()));
        btnCourseAttendance.addActionListener(e -> openNewPage(currentFrame, new CoursePage()));
        btnLogout.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Logging out...");

            // Close the current window
            JFrame logoutFrame = (JFrame) SwingUtilities.getWindowAncestor(btnLogout);
            logoutFrame.dispose();

            // Fully qualified reference to LauncherPage
            new LauncherPage().setVisible(true);
        });
    }

    private void openNewPage(JFrame currentFrame, JFrame newFrame) {
        currentFrame.dispose();  // Close the current window
        newFrame.setVisible(true); // Open the new page
    }
}