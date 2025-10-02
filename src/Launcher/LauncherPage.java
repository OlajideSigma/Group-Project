package Launcher;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import Attendance.MYGUI;
import Attendance.CoursePage;
import Attendance.StudentPage;
import Finance.GUI;
import Finance.ErrorMessage;
import Finance.GUI3;
import Finance.SQL_DB;
import CourseSelection.booking_system;
import Food.MyGUI;

public class LauncherPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private BufferedImage originalImage;
    private JLabel background;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LauncherPage frame = new LauncherPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Failed to launch application: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public LauncherPage() {
        setTitle("UNIVERSITY MANAGEMENT SYSTEM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);

        // Try multiple ways to load the image with debug output
        originalImage = loadImageWithFallbacks();
        
        // Create background panel
        background = createBackgroundPanel();
        setContentPane(background);
        
        // Setup UI components
        setupUI();
    }

    private BufferedImage loadImageWithFallbacks() {
        String[] possiblePaths = {
            "/chrc_Eastern_Gateway_building_1_30.jpg",          // Resource path
            "chrc_Eastern_Gateway_building_1_30.jpg",            // Relative path
            "C:\\Users\\olade\\Downloads\\chrc_Eastern_Gateway_building_1_32.jpg", // Absolute path
            "src/Images/chrc_Eastern_Gateway_building_1_30.jpg"  // Project source path
        };

        for (String path : possiblePaths) {
            System.out.println("Trying to load image from: " + path);
            try {
                // Try loading as resource first
                if (path.startsWith("/")) {
                    InputStream is = getClass().getResourceAsStream(path);
                    if (is != null) {
                        BufferedImage img = ImageIO.read(is);
                        if (img != null) {
                            System.out.println("Successfully loaded image from resources: " + path);
                            return img;
                        }
                    }
                }
                
                // Try loading as file
                File file = new File(path);
                if (file.exists()) {
                    BufferedImage img = ImageIO.read(file);
                    if (img != null) {
                        System.out.println("Successfully loaded image from file: " + path);
                        return img;
                    }
                }
            } catch (IOException | IllegalArgumentException e) {
                System.err.println("Failed to load image from " + path + ": " + e.getMessage());
            }
        }

        // If all attempts fail, create a placeholder
        System.err.println("All image loading attempts failed. Using fallback background.");
        return createFallbackImage();
    }

    private BufferedImage createFallbackImage() {
        BufferedImage img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        
        // Create gradient background
        GradientPaint gradient = new GradientPaint(
            0, 0, new Color(0, 51, 102), 
            img.getWidth(), img.getHeight(), new Color(0, 102, 204));
        
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
        
        // Add text indicating missing image
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        String message = "Background Image Not Found";
        int textWidth = g2d.getFontMetrics().stringWidth(message);
        g2d.drawString(message, (img.getWidth() - textWidth)/2, img.getHeight()/2);
        
        g2d.dispose();
        return img;
    }

    private JLabel createBackgroundPanel() {
        return new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                // Set high-quality rendering hints
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, 
                                    RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Calculate scaling
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                
                if (originalImage != null) {
                    double imgRatio = (double)originalImage.getWidth() / originalImage.getHeight();
                    double panelRatio = (double)panelWidth / panelHeight;
                    
                    int drawWidth, drawHeight;
                    
                    if (panelRatio > imgRatio) {
                        drawHeight = panelHeight;
                        drawWidth = (int)(drawHeight * imgRatio);
                    } else {
                        drawWidth = panelWidth;
                        drawHeight = (int)(drawWidth / imgRatio);
                    }
                    
                    int x = (panelWidth - drawWidth) / 2;
                    int y = (panelHeight - drawHeight) / 2;
                    
                    g2d.drawImage(originalImage, x, y, drawWidth, drawHeight, this);
                }
            }
        };
    }

    private void setupUI() {
        background.setLayout(new BorderLayout());
        
        // Title label
        JLabel title = new JLabel("UNIVERSITY MANAGEMENT SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Agency FB", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(100, 149, 237));
        background.add(title, BorderLayout.NORTH);

        // Button panel
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(2, 3, 20, 20));
        contentPane.setOpaque(false);
        contentPane.setBackground(new Color(0, 0, 0, 100));
        contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Add buttons
        addLauncherButton("Course Selection", e -> openNewPage(this, new booking_system()));
        addLauncherButton("Applicant Details", e -> openNewPage(this, new StudentPage()));
        addLauncherButton("Attendance Monitoring", e -> {
            try {
                openNewPage(this, new MYGUI());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        addLauncherButton("Sports School", e -> openNewPage(this, new StudentPage()));
        addLauncherButton("Restaurant", e -> openNewPage(this, new MyGUI()));
        addLauncherButton("Finances", e -> openNewPage(this, new GUI()));

        // Center panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(contentPane);
        background.add(centerPanel, BorderLayout.CENTER);
        
        // Resize listener
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                background.repaint();
            }
        });
    }

    private void addLauncherButton(String label, ActionListener action) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(150, 150));
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 144, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });

        button.addActionListener(action);
        contentPane.add(button);
    }

    private void openNewPage(JFrame currentFrame, JFrame newFrame) {
        currentFrame.setVisible(false);
        newFrame.setVisible(true);
    }
}