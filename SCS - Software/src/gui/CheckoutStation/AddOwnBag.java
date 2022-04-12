package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AddOwnBag extends JFrame {

	private JPanel contentPane;
	public JButton btnBackToScanning;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AddOwnBag(DataPasser dataPass,ScanningScreen scanScreen) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblAddOwnBags = new JLabel("Please put your own bags on the scale now.");
		lblAddOwnBags.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAddOwnBags, BorderLayout.CENTER);
		
		btnBackToScanning = new JButton("Go Back");
		btnBackToScanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dataPass.setFound(7);
				scanScreen.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnBackToScanning.setForeground(Color.YELLOW);
		btnBackToScanning.setBackground(Color.RED);
		contentPane.add(btnBackToScanning, BorderLayout.NORTH);
	}

}
