package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;

public class AddOwnBag extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddOwnBag frame = new AddOwnBag();
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
	public AddOwnBag() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblAddOwnBags = new JLabel("Please put your own bags on the scale now.");
		contentPane.add(lblAddOwnBags, BorderLayout.CENTER);
		
		JButton btnBackToScanning = new JButton("Go Back");
		btnBackToScanning.setForeground(Color.YELLOW);
		btnBackToScanning.setBackground(Color.RED);
		contentPane.add(btnBackToScanning, BorderLayout.NORTH);
	}

}
