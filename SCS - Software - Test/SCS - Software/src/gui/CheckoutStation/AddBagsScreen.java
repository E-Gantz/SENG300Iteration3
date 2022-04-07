package gui.CheckoutStation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class AddBagsScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textBagNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBagsScreen frame = new AddBagsScreen();
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
	public AddBagsScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBackToCheckout = new JButton("Go Back");
		btnBackToCheckout.setForeground(Color.YELLOW);
		btnBackToCheckout.setBackground(Color.RED);
		contentPane.add(btnBackToCheckout);
		
		JLabel lbAddPlastic = new JLabel("Please enter the number of plastic bags used:");
		contentPane.add(lbAddPlastic);
		
		textBagNumber = new JTextField();
		contentPane.add(textBagNumber);
		textBagNumber.setColumns(10);
		
		JButton btnEnterBags = new JButton("Enter");
		contentPane.add(btnEnterBags);
	}

}
