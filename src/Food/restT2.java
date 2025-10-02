package Food;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Label;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.SystemColor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Button;

public class restT2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					restT2 frame = new restT2();
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
	public restT2() {
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 599);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		// Button to open the 'Explore' page
        JButton btnExplore = new JButton("EXPLORE OUR RESTAURANTS");
        btnExplore.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 explor frame = new explor();
                 frame.setVisible(true);
        	}
        });
        btnExplore.setBounds(47, 223, 242, 43);

        // ActionListener to open ExploreFrame
       
				
		
		// home, life, shop,button  
        
		
		
		JButton home = new JButton("home ");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		home.setBounds(47, 29, 111, 21);
		home.setForeground(Color.BLACK);
		
		JButton life = new JButton("life");
		life.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainscreen frame = new mainscreen();
                frame.setVisible(true);
			}
		});
		life.setBounds(181, 29, 111, 21);
		
		JButton shope = new JButton("cart");
		shope.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CartScreen frame = new CartScreen(null, null);
				frame.setVisible(true);
				
			}
		});
		shope.setBounds(315, 29, 111, 21);
		
		JButton ufood = new JButton("Brunel Uni food ");
		ufood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				explor frame = new explor();
                frame.setVisible(true);
			}
		});
		ufood.setBounds(449, 29, 111, 21);
		
		JLabel lblNewLabel_1_1 = new JLabel("brunel uni");
		lblNewLabel_1_1.setBounds(5, 60, 1068, 368);
		lblNewLabel_1_1.setToolTipText("brunel university");
		lblNewLabel_1_1.setIcon(new ImageIcon("src\\Food\\2.png"));
		lblNewLabel_1_1.setBackground(new Color(0, 0, 0));
		
		JLabel lblNewLabel_2_1_1 = new JLabel("New label");
		lblNewLabel_2_1_1.setBounds(117, 438, 202, 122);
		lblNewLabel_2_1_1.setIcon(new ImageIcon("src\\Food\\Screenshot 2025-02-21 113947.png"));
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("New label");
		lblNewLabel_2_1_1_1.setBounds(422, 432, 225, 122);
		lblNewLabel_2_1_1_1.setBackground(UIManager.getColor("Button.disabledShadow"));
		lblNewLabel_2_1_1_1.setIcon(new ImageIcon("src\\Food\\Screenshot 2025-02-19 212603.png"));
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 1068, 53);
		panel.setBackground(new Color(0, 102, 51));
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("New label");
		lblNewLabel_2_1_1_1_1.setBounds(759, 432, 202, 122);
		lblNewLabel_2_1_1_1_1.setIcon(new ImageIcon("src\\Food\\Screenshot 2025-02-21 113947.png"));
		contentPane.setLayout(null);
		contentPane.add(home);
		contentPane.add(life);
		contentPane.add(shope);
		contentPane.add(ufood);
		contentPane.add(panel);
		contentPane.add(btnExplore);
		contentPane.add(lblNewLabel_1_1);
		contentPane.add(lblNewLabel_2_1_1);
		contentPane.add(lblNewLabel_2_1_1_1);
		contentPane.add(lblNewLabel_2_1_1_1_1);
	}
}
