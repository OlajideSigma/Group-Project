package Finance;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

public class ErrorMessage extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ErrorMessage dialog = new ErrorMessage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ErrorMessage() {
		showError();
	}
	
	public void showError() {
		JFrame frame = null;
		JOptionPane.showMessageDialog(frame, "The Information you are looking for cannot be found.\n"
				+ "Please use different Keywords or Dates","Error",JOptionPane.ERROR_MESSAGE);

		
		//I expected this to be more lines
		
			}
		
	}

