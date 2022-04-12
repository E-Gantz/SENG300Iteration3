package gui.CheckoutStation;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartScreen extends JFrame {
	private JPanel contentPane;
	public JButton btnStartButton;
	public ScanningScreen scanScreen;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public StartScreen(DataPasser dataPass) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		btnStartButton = new JButton("Start");
		btnStartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					scanScreen = new ScanningScreen(dataPass);
					scanScreen.setVisible(true);
					setVisible(false);
					dataPass.setFound(1);
			}
		});
		contentPane.add(btnStartButton, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("CartMart SuperCenter SelfCheckoutStation");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}

}
